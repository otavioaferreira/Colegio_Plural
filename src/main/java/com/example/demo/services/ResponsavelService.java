package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Responsavel;
import com.example.demo.repositories.ResponsavelRepository;

@Service 
public class ResponsavelService {
    
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(Long id) {
        return responsavelRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
    }

    public Responsavel cadastrar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public Responsavel atualizar(Long id, Responsavel dadosAtualizados) {
        Responsavel responsavel = buscarPorId(id);
        responsavel.setNome(dadosAtualizados.getNome());
        responsavel.setCpf(dadosAtualizados.getCpf());
        responsavel.setTelefone(dadosAtualizados.getTelefone());
    responsavel.setEmail(dadosAtualizados.getEmail());
    responsavel.setEndereco(dadosAtualizados.getEndereco());

    return responsavelRepository.save(responsavel);
    }

    public void deletar(Long id) {
        Responsavel responsavel = buscarPorId(id);
        responsavelRepository.delete(responsavel);
    }
}

