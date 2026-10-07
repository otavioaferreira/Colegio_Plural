
package com.example.demo.controllers;

import com.example.demo.models.Responsavel;
import com.example.demo.services.ResponsavelService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelService service;

    public ResponsavelController(ResponsavelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Responsavel> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Responsavel buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Responsavel cadastrar(@RequestBody Responsavel responsavel) {
        return service.cadastrar(responsavel);
    }

    @PutMapping("/{id}")
    public Responsavel atualizar(
            @PathVariable Long id,
            @RequestBody Responsavel responsavel) {

        return service.atualizar(id, responsavel);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }
}
