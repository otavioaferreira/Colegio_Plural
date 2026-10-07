package com.example.demo.controllers;

import com.example.demo.models.Atendimento;
import com.example.demo.services.AtendimentoService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService service;

    public AtendimentoController(AtendimentoService service) {
        this.service = service;
    }

    // Listar atendimentos por data
    @GetMapping
    public List<Atendimento> listar() {
        return service.listarTodos();
    }

    // Buscar atendimento pelo ID
    @GetMapping("/{id}")
    public Atendimento buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Cadastrar atendimento
    @PostMapping
    public Atendimento cadastrar(@RequestBody Atendimento atendimento) {
        return service.cadastrar(atendimento);
    }

    // Atualizar atendimento
    @PutMapping("/{id}")
    public Atendimento atualizar(
            @PathVariable Long id,
            @RequestBody Atendimento atendimento) {

        return service.atualizar(id, atendimento);
    }

    // Excluir atendimento
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }
}