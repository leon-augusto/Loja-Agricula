package com.loja.agricola.controller;

import com.loja.agricola.model.Unidade;
import com.loja.agricola.repository.UnidadeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@CrossOrigin(origins = "*")
public class UnidadeController {

    private final UnidadeRepository repository;

    public UnidadeController(UnidadeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Unidade> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Unidade salvar(@RequestBody Unidade unidade) {
        return repository.save(unidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}