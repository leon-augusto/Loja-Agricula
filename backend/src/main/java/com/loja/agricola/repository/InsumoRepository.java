package com.loja.agricola.repository;

import com.loja.agricola.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InsumoRepository extends JpaRepository<Insumo, Long>{
	
}
