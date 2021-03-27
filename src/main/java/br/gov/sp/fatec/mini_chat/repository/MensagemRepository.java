package br.gov.sp.fatec.mini_chat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	public List<Mensagem> findByGrupoId(Long grupoId);
}
