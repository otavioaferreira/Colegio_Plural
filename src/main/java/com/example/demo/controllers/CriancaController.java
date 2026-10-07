
package com.example.demo.controllers;

import com.example.demo.models.Crianca;
import com.example.demo.services.CriancaService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/criancas")
public class CriancaController {

    private final CriancaService service;

    public CriancaController(CriancaService service) {
        this.service = service;
    }

    // Listar todas as crianças
    @GetMapping
    public List<Crianca> listar() {
        return service.listarTodos();
    }

    // Buscar criança pelo ID
    @GetMapping("/{id}")
    public Crianca buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Cadastrar criança
    @PostMapping
    public Crianca cadastrar(@RequestBody Crianca crianca) {
        return service.cadastrar(crianca);
    }

    // Atualizar criança
    @PutMapping("/{id}")
    public Crianca atualizar(
            @PathVariable Long id,
            @RequestBody Crianca crianca) {

        return service.atualizar(id, crianca);
    }

    // Excluir criança
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }
}
