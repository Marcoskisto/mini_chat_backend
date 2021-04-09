package br.gov.sp.fatec.mini_chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long>{
	
	Optional<Conversa> findById(Long id);
	
	List<Conversa> findByTituloContainsIgnoreCase(String titulo);
	
	Conversa findByTituloIgnoreCase(String titulo);
		
}
