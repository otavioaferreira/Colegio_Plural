
package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Atendimento;
import com.example.demo.repositories.AtendimentoRepository;
import com.example.demo.repositories.CriancaRepository;
import com.example.demo.repositories.ResponsavelRepository;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final CriancaRepository criancaRepository;
    private final ResponsavelRepository responsavelRepository;

    public AtendimentoService(
            AtendimentoRepository atendimentoRepository,
            CriancaRepository criancaRepository,
            ResponsavelRepository responsavelRepository) {

        this.atendimentoRepository = atendimentoRepository;
        this.criancaRepository = criancaRepository;
        this.responsavelRepository = responsavelRepository;
    }

    // Listar atendimentos em ordem de data
    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAllByOrderByDataAtendimentoAsc();
    }

    // Buscar atendimento pelo ID
    public Atendimento buscarPorId(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Atendimento não encontrado"));
    }

    // Cadastrar atendimento
    public Atendimento cadastrar(Atendimento atendimento) {
        validarRelacionamentos(atendimento);
        return atendimentoRepository.save(atendimento);
    }

    // Atualizar atendimento
    public Atendimento atualizar(Long id, Atendimento dados) {
        Atendimento atendimento = buscarPorId(id);

        atendimento.setDataAtendimento(dados.getDataAtendimento());
        atendimento.setDescricao(dados.getDescricao());

        if (dados.getCrianca() != null) {
            atendimento.setCrianca(dados.getCrianca());
        }

        if (dados.getResponsavel() != null) {
            atendimento.setResponsavel(dados.getResponsavel());
        }

        validarRelacionamentos(atendimento);

        return atendimentoRepository.save(atendimento);
    }

    // Excluir atendimento
    public void deletar(Long id) {
        Atendimento atendimento = buscarPorId(id);
        atendimentoRepository.delete(atendimento);
    }

    // Conferir se a criança e o responsável existem
    private void validarRelacionamentos(Atendimento atendimento) {

        if (atendimento.getCrianca() == null ||
                atendimento.getCrianca().getIdCrianca() == null) {
            throw new IllegalArgumentException("Informe uma criança válida");
        }

        if (atendimento.getResponsavel() == null ||
                atendimento.getResponsavel().getIdResponsavel() == null) {
            throw new IllegalArgumentException("Informe um responsável válido");
        }

        atendimento.setCrianca(
                criancaRepository.findById(atendimento.getCrianca().getIdCrianca())
                        .orElseThrow(() ->
                                new IllegalArgumentException("Criança não encontrada"))
        );

        atendimento.setResponsavel(
                responsavelRepository.findById(atendimento.getResponsavel().getIdResponsavel())
                        .orElseThrow(() ->
                                new IllegalArgumentException("Responsável não encontrado"))
        );
    }
}
