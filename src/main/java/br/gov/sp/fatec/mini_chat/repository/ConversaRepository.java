package br.gov.sp.fatec.mini_chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long>{

	List<Conversa> findByTituloContainsIgnoreCase(String titulo);
	
	Conversa findByTituloIgnoreCase(String titulo);
	
}
