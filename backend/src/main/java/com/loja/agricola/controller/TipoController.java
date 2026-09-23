package com.loja.agricola.controller;

import com.loja.agricola.model.Tipo;
import com.loja.agricola.repository.TipoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoController {

    private final TipoRepository repository;

    public TipoController(TipoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tipo> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Tipo salvar(@RequestBody Tipo tipo) {
        return repository.save(tipo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}