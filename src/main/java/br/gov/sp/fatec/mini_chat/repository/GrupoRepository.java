package br.gov.sp.fatec.mini_chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	List<Grupo> findByTituloContainsIgnoreCase(String titulo);
	
	Grupo findByTituloIgnoreCase(String titulo);
	
}
