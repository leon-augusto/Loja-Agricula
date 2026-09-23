package com.loja.agricola.controller;

import com.loja.agricola.model.Ferramenta;
import com.loja.agricola.repository.FerramentaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ferramentas")
@CrossOrigin(origins = "*")
public class FerramentaController {

    private final FerramentaRepository repository;

    public FerramentaController(FerramentaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ferramenta> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Ferramenta salvar(@RequestBody Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}