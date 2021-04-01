package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.ConversaRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@Service
public class AdministracaoServiceImp implements AdministracaoService{
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	ConversaRepository conversaRepo;	
	
	ComunicacaoService comunicacaoService;
	
	@Override
	public Usuario criarUsuario(String nickname, String email) {
		Usuario usuario = new Usuario();
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(nickname, email);
		
		if (usuario == null){
			return null;
		}
		
		usuario.setNickname(nickname);
		usuario.setEmail(email);
		usuarioRepo.save(usuario);
		return usuario;
	}

	@Override
	public Conversa criarConversa(String titulo) {
		Conversa conversa = new Conversa();
		conversa = conversaRepo.findByTituloIgnoreCase(titulo);
		
		if (conversa == null) {
			return null;
		}
		
		conversa.setTitulo(titulo);
		conversaRepo.save(conversa);
		return conversa;
	}

	@Override
	public List<Conversa> excluirConversa(String titulo) {
		Conversa conversa = new Conversa();
		conversa = conversaRepo.findByTituloIgnoreCase(titulo);
		if (conversa != null) {
			conversaRepo.delete(conversa);
		}
		return conversaRepo.findAll();
	}
		

	@Override
	public Conversa incluirUsuarioNaConversa(String usuarioNick, String conversaTitulo) {
		Usuario usuario = new Usuario();
		Conversa conversa = new Conversa();
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(usuarioNick, conversaTitulo);
		conversa = conversaRepo.findByTituloIgnoreCase(conversaTitulo);
		
		if (usuario != null) {
			conversa.getUsuarios().add(usuario);		
			conversaRepo.save(conversa);
		}
		return conversa;
	}

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

	@Override
	public List<Usuario> buscaTodosUsuarios() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario buscaUsuarioByid(Long id) {
		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		if (usuarioOp.isPresent()){
			return usuarioOp.get();
		}
		throw new RuntimeException("NoUserFound");
	}
}
