package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface ComunicacaoService {
	
	public Mensagem enviaMensagemUsuario(String destinatario, String msgTexto);
	
	public Mensagem enviaMensagemGrupo(String tituloGrupo, String msgTexto);
	
	public List<Mensagem> recuperaConversa(String remetente, String destinatario);
	
	public List<Mensagem> recuperaReuniao(String remetente, String tituloGrupo);
	
	public List<Mensagem> excluiMensagem(String rementente, String usuarioOuGrupo);
	
	public void excluirMensagensDeGrupo(Long grupoId);
}
