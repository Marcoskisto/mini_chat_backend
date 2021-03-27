package br.gov.sp.fatec.mini_chat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	public List<Mensagem> findByGrupoId(Long grupoId);
	
	public List<Mensagem> findByRemetenteId(Long usuarioId);

	public List<Mensagem> findByRemetenteNickAndDestinatarioNick(String remetenteNick, String destinatarioNick);

	public List<Mensagem> findByRemetenteNickAndGrupoTitulo(String remetenteNick, String grupoTitulo);
}
