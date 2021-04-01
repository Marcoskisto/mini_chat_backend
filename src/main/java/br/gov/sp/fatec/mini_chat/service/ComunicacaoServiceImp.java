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
	public List<Mensagem> enviaMensagemConversa(String nickRemetente, String conversaTitulo, String msgTexto) {
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(nickRemetente);
		
		mensagem.setRemetente(remetente);
		mensagem.setDescription(msgTexto);
		mensagemRepo.save(mensagem);
		
		return mensagemRepo.findByRemetenteId(remetente.getId());
	}


	@Override
	public List<Mensagem> recuperaConversaRemetente(String nickRemetente) {
		
		return mensagemRepo.findByRemetenteNickname(nickRemetente);
	
	}
}
