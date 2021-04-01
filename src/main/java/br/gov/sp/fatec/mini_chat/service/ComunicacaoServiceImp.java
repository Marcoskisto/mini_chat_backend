package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Set<Mensagem> enviaMensagemConversa(String nickRemetente, String conversaTitulo, String msgTexto) {
		
		Conversa conversa = new Conversa();
		conversa = conversaRepo.findByTituloIgnoreCase(conversaTitulo);
		
		if(conversa == null) {
			conversa.setTitulo(conversaTitulo);
			conversaRepo.save(conversa);		
		}
		
		
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(nickRemetente);
		
		mensagem.setRemetente(remetente);
		mensagem.setDescription(msgTexto);
		mensagem.setConversa(conversa);
		mensagemRepo.save(mensagem);
		
		return conversa.getMensagens();
	}


	@Override
	public List<Mensagem> recuperaConversaRemetente(String nickRemetente) {
		
		return mensagemRepo.findByRemetenteNickname(nickRemetente);
	
	}
}
