package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.repository.MensagemRepository;

public class ComunicacaoServiceImp implements ComunicacaoService{
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Override
	public Mensagem enviaMensagemUsuario(String destinatario, String msgTexto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem enviaMensagemGrupo(String tituloGrupo, String msgTexto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mensagem> recuperaConversa(String remetente, String destinatario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mensagem> recuperaReuniao(String remetente, String tituloGrupo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mensagem> excluiMensagem(String rementente, String usuarioOuGrupo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluirMensagensDeGrupo(Long grupoId) {
		List<Mensagem> mensagens = mensagemRepo.findByGrupoId(grupoId);
		mensagemRepo.deleteAll(mensagens);	
	}

}
