package br.gov.sp.fatec.mini_chat.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	public List<Mensagem> findByConversaId(Long conversaId);
    
    public List<Mensagem> findByConversaTitulo(Long conversaTitulo);

	public List<Mensagem> findByRemetenteId(Long usuarioId);

	public List<Mensagem> findByRemetenteNickname(String remetenteNickame);
}
