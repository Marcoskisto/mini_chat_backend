package br.gov.sp.fatec.mini_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	Grupo findByTituloContainsIgnoreCase(String titulo);
}
