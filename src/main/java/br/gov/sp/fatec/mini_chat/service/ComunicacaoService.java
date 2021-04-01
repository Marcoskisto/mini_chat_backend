package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Set;

import br.gov.sp.fatec.mini_chat.entity.Mensagem;

public interface ComunicacaoService {
	
	public Set<Mensagem> enviaMensagemConversa(String nickRemetente, String conversaTitulo, String msgTexto);
		
    public List<Mensagem> recuperaConversaRemetente(String nickRemetente);
	
}
