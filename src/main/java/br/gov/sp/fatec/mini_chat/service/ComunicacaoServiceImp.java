package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.ConversaRepository;
import br.gov.sp.fatec.mini_chat.repository.MensagemRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

public class ComunicacaoServiceImp implements ComunicacaoService{
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	ConversaRepository conversaRepo;
	
	@Override
	public List<Mensagem> enviaMensagemUsuario(String remetenteNick, String destinatarioNick, String msgTexto) {
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(remetenteNick);
		
		mensagem.setRemetente(remetente);
		mensagem.setDescription(msgTexto);
		mensagemRepo.save(mensagem);
		
		return mensagemRepo.findByRemetenteId(remetente.getId());
	}

	@Override
	public List<Mensagem> enviaMensagemGrupo(String remetenteNick, String tituloConversa, String msgTexto) {
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		Conversa conversa = new Conversa();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(remetenteNick);
		conversa = conversaRepo.findByTituloIgnoreCase(tituloConversa);
		
		mensagem.setRemetente(remetente);
		mensagem.setConversa(conversa);
		mensagem.setDescription(msgTexto);
		mensagemRepo.save(mensagem);
		
		return mensagemRepo.findByGrupoId(remetente.getId());
	}

	@Override
	public List<Mensagem> recuperaConversa(String remetenteNick, String destinatarioNick) {
		
		return mensagemRepo.findByRemetenteNicknameAndDestinatarioNickname(remetenteNick, destinatarioNick);
	
	}

	@Override
	public List<Mensagem> recuperaReuniao(String remetenteNick, String tituloGrupo) {
		
		return mensagemRepo.findByRemetenteNicknameAndGrupoTitulo(remetenteNick, tituloGrupo);
		
	}
}
