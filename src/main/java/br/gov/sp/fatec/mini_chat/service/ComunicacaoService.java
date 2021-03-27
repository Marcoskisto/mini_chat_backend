package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface ComunicacaoService {
	
	public List<Mensagem> enviaMensagemUsuario(String remetente, String destinatario, String msgTexto);
	
	public List<Mensagem> enviaMensagemGrupo(String remetente, String tituloGrupo, String msgTexto);
	
	public List<Mensagem> recuperaConversa(String remetente, String destinatario);
	
	public List<Mensagem> recuperaReuniao(String remetente, String tituloGrupo);
}
