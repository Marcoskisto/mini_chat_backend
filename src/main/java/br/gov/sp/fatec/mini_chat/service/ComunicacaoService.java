package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface ComunicacaoService {
	
	public List<Mensagem> enviaMensagemConversa(String nickRemetente, String conversaTitulo, String msgTexto);
		
    public List<Mensagem> recuperaConversaRemetente(String nickRemetente);
	
}
