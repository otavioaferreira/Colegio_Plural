
package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Crianca;
import com.example.demo.repositories.CriancaRepository;
import com.example.demo.repositories.ResponsavelRepository;

@Service
public class CriancaService {

    private final CriancaRepository criancaRepository;
    private final ResponsavelRepository responsavelRepository;

    public CriancaService(
            CriancaRepository criancaRepository,
            ResponsavelRepository responsavelRepository) {

        this.criancaRepository = criancaRepository;
        this.responsavelRepository = responsavelRepository;
    }

    public List<Crianca> listarTodos() {
        return criancaRepository.findAll();
    }

    public Crianca buscarPorId(Long id) {
        return criancaRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Criança não encontrada"));
    }

    public Crianca cadastrar(Crianca crianca) {
        Long idResponsavel = crianca.getResponsavel().getIdResponsavel();

        crianca.setResponsavel(
                responsavelRepository.findById(idResponsavel)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Responsável não encontrado"))
        );

        return criancaRepository.save(crianca);
    }

    public Crianca atualizar(Long id, Crianca dadosAtualizados) {
        Crianca crianca = buscarPorId(id);

        crianca.setNome(dadosAtualizados.getNome());
        crianca.setCpf(dadosAtualizados.getCpf());
        crianca.setDataNascimento(dadosAtualizados.getDataNascimento());

        if (dadosAtualizados.getResponsavel() != null) {
            Long idResponsavel = dadosAtualizados.getResponsavel().getIdResponsavel();

            crianca.setResponsavel(
                    responsavelRepository.findById(idResponsavel)
                            .orElseThrow(() ->
                                    new IllegalArgumentException("Responsável não encontrado"))
            );
        }

        return criancaRepository.save(crianca);
    }

    public void deletar(Long id) {
        Crianca crianca = buscarPorId(id);
        criancaRepository.delete(crianca);
    }
}
