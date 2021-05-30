package br.gov.sp.fatec.mini_chat.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.ConversaRepository;
import br.gov.sp.fatec.mini_chat.repository.MensagemRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@Service
public class ComunicacaoServiceImp implements ComunicacaoService{
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	ConversaRepository conversaRepo;
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public Conversa criaConversa(String conversaTitulo) {
		Usuario bot = usuarioRepo.findByNicknameIgnoreCase("bot");
		Conversa conversa = conversaRepo.findByTituloIgnoreCase(conversaTitulo);
		
		if(conversa == null) {
			conversa = new Conversa();
			conversa.setTitulo(conversaTitulo);
			conversa.setUsuarios(new HashSet<Usuario>());
			conversa.getUsuarios().add(bot);
			conversaRepo.save(conversa);
		}
		
		return conversa;
	}
	
	@Override
	@Transactional
	@PreAuthorize("isAuthenticated()")
	public Conversa incluiUsuarioNaConversa(String nickname, String conversaTitulo) {
		
		Usuario usuario = usuarioRepo.findByNicknameIgnoreCase(nickname);
		
		if(usuario == null) {
			throw new RuntimeException("Usuário não cadastrado!");
		}
		
		Conversa conversa = this.criaConversa(conversaTitulo);
		
		if(!(conversa.getUsuarios().contains(usuario))) {
			conversa.getUsuarios().add(usuario);
		}	

		conversaRepo.save(conversa);
		
		String msgBoasVindas = String.format("%s entrou na conversa...", nickname);
		conversa = this.enviaMensagemEmConversa("Bot", conversaTitulo, msgBoasVindas);
		
		return conversa;
	}
	
	@Override
	@Transactional
	@PreAuthorize("isAuthenticated()")
	public Conversa enviaMensagemEmConversa(String nickRemetente, String conversaTitulo, String msgTexto) {

		Usuario remetente = usuarioRepo.findByNicknameIgnoreCase(nickRemetente);
		
		if(remetente == null) {
			throw new RuntimeException("Usuário não cadastrado!");
		}
		
		Conversa conversa = this.criaConversa(conversaTitulo);
		
		if(!(conversa.getUsuarios().contains(remetente))){
			this.incluiUsuarioNaConversa(remetente.getNickname(), conversaTitulo);
		}
		
		conversaRepo.save(conversa);		
		
		Mensagem mensagem = new Mensagem();
		mensagem.setRemetente(remetente);
		mensagem.setDescription(msgTexto);
		mensagem.setConversa(conversa);
		mensagemRepo.save(mensagem);
		
		return conversa;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Mensagem> buscaConversaPorRemetente(String nickRemetente) {
		return mensagemRepo.findByRemetenteNickname(nickRemetente);
	}

	@Override
	public Conversa buscaConversaPorId(Long id) {
		return conversaRepo.findById(id).get();
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Conversa> buscaTodasConversas() {
		return conversaRepo.findAll();
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Set<Usuario> buscaUsuariosDaConversa(String titulo) {
		return usuarioRepo.buscaUsuariosPorConversaQuery(titulo);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Conversa buscaConversaPorTitulo(String titulo) {
		
		return conversaRepo.findByTituloIgnoreCase(titulo);
	}

	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Conversa> excluirConversa(Long id) {
		Conversa conversa = new Conversa();
		conversa = conversaRepo.findById(id).get();
		if (conversa != null) {
			conversaRepo.delete(conversa);
		}
		return conversaRepo.findAll();
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public void excluirMensagem(Long id) {
		mensagemRepo.deleteById(id);
	}

	@Override
	@PreAuthorize("hasPermission(#id, 'role_admin')")
	public Mensagem updateMensagem(Long id, String description) {
		
		Mensagem mensagem = mensagemRepo.findById(id).get();
		if(mensagem != null) {
			mensagem.setDescription(description);
			mensagemRepo.save(mensagem);
		}
		return mensagem;
	}
		
	/*	
	@Override
	public Conversa removerUsuarioDaConversa(String usuarioNick, String conversaTitulo) {
		
		Usuario usuario = new Usuario();
		Conversa conversa = new Conversa();
		
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(usuarioNick, conversaTitulo);
		conversa = conversaRepo.findByTituloIgnoreCase(conversaTitulo);
		
		if (usuario != null) {
			conversa.getUsuarios().remove(usuario);		
			conversaRepo.save(conversa);
		}
		return conversa;
	}
	 */

}
