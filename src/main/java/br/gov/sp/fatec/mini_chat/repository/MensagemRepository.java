package br.gov.sp.fatec.mini_chat.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	public List<Mensagem> findByConversaId(Long conversaId);
    
    public List<Mensagem> findByConversaTitulo(Long conversaTitulo);

	public List<Mensagem> findByRemetenteId(Long usuarioId);

	public List<Mensagem> findByRemetenteNickname(String remetenteNickame);

	public Optional<Mensagem> findById(Long mensagemId);
	
	@Query("select u from Usuario u inner join u.mensagensEnviadas m inner join m.conversa c inner join c.destinatarios d where d.nickname = ?1")
	public Usuario BuscaDestinatarioConversa(String destinatarioNickname);
}
