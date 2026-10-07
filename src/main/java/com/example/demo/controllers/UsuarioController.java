
package com.example.demo.controllers;

import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Cadastrar usuário
    @PostMapping
    public ResponseEntity<Map<String, String>> cadastrar(
            @RequestBody Usuario usuario) {

        service.cadastrar(usuario);

        return ResponseEntity.ok(
                Map.of("mensagem", "Usuário cadastrado com sucesso")
        );
    }

    // Verificar login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest dados) {

        boolean valido = service.verificarLogin(
                dados.email(), dados.senha()
        );

        if (valido) {
            return ResponseEntity.ok(
                    Map.of("mensagem", "Credenciais válidas")
            );
        }

        return ResponseEntity.status(401).body(
                Map.of("mensagem", "Email ou senha incorretos")
        );
    }

    public record LoginRequest(String email, String senha) {
    }
}
