
package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado"));
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setSenha(gerarHash(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());

        if (dados.getSenha() != null && !dados.getSenha().isBlank()) {
            usuario.setSenha(gerarHash(dados.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    public boolean verificarLogin(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        return usuario.isPresent() &&
                usuario.get().getSenha().equals(gerarHash(senha));
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    private String gerarHash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Erro ao gerar hash da senha", e);
        }
    }
}
