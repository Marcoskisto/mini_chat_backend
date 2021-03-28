package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.mini_chat.entity.Grupo;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.GrupoRepository;
import br.gov.sp.fatec.mini_chat.repository.MensagemRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

public class ComunicacaoServiceImp implements ComunicacaoService{
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	GrupoRepository grupoRepo;
	
	@Override
	public List<Mensagem> enviaMensagemUsuario(String remetenteNick, String destinatarioNick, String msgTexto) {
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		Usuario destinatario = new Usuario();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(remetenteNick);
		destinatario = usuarioRepo.findByNicknameIgnoreCase(destinatarioNick);
		
		mensagem.setRemetente(remetente);
		mensagem.setDestinatario(destinatario);
		mensagem.setDescription(msgTexto);
		mensagemRepo.save(mensagem);
		
		return mensagemRepo.findByRemetenteId(remetente.getId());
	}

	@Override
	public List<Mensagem> enviaMensagemGrupo(String remetenteNick, String tituloGrupo, String msgTexto) {
		Mensagem mensagem = new Mensagem();
		Usuario remetente = new Usuario();
		Grupo grupo = new Grupo();
		
		remetente = usuarioRepo.findByNicknameIgnoreCase(remetenteNick);
		grupo = grupoRepo.findByTituloIgnoreCase(tituloGrupo);
		
		mensagem.setRemetente(remetente);
		mensagem.setGrupo(grupo);
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
