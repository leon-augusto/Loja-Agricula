package com.loja.agricola.controller;

import com.loja.agricola.model.Insumo;
import com.loja.agricola.repository.InsumoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")

public class InsumoController{
	private final InsumoRepository repository;

	public InsumoController(InsumoRepository repository){
		this.repository = repository;
	}

	@GetMapping
	public List<Insumo> listarTodos(){
		return repository.findAll();
	}
	@PostMapping
	public Insumo salvar(@RequestBody InsumoController insumo){
		return repository.save(insumo);
	}
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id){
		repository.deleteById(id);
	}
}