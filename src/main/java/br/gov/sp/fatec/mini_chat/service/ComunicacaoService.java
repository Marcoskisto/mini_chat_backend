package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Set;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface ComunicacaoService {
	
	public Conversa criaConversa(String conversaTitulo);

	public Conversa incluiUsuarioNaConversa(String nickname, String conversaTitulo);
	
	public Conversa enviaMensagemEmConversa(String nickRemetente, String conversaTitulo, String msgTexto);
		
    public List<Mensagem> buscaConversaPorRemetente(String nickRemetente);
    
    public Conversa buscaConversaPorId(Long id);
    
    public List<Conversa> buscaTodasConversas();
    
    public Set<Usuario> buscaUsuariosDaConversa(String tituloConversa);

	public Conversa buscaConversaPorTitulo(String titulo);
	
    
    //public Conversa removerUsuarioDaConversa(String usuarioNick, String grupoTitulo);

	public List<Conversa> excluirConversa(Long id);
	
	public void excluirMensagem(Long id);

	public Mensagem updateMensagem(Long id, String description);
	
}
