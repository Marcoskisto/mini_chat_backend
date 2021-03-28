package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.mini_chat.entity.Grupo;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.GrupoRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@Service
public class AdministracaoServiceImp implements AdministracaoService{
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	GrupoRepository grupoRepo;	
	
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
	public Grupo criarGrupo(String titulo, String descricao) {
		Grupo grupo = new Grupo();
		grupo = grupoRepo.findByTituloIgnoreCase(titulo);
		
		if (grupo == null) {
			return null;
		}
		
		grupo.setTitulo(titulo);
		grupo.setDescricao(descricao);
		grupoRepo.save(grupo);
		return grupo;
	}

	@Override
	public List<Grupo> excluirGrupo(String titulo) {
		Grupo grupo = new Grupo();
		grupo = grupoRepo.findByTituloIgnoreCase(titulo);
		if (grupo != null) {
			grupoRepo.delete(grupo);
		}
		return grupoRepo.findAll();
	}
		

	@Override
	public Grupo incluirUsuarioNoGrupo(String usuarioNick, String grupoTitulo) {
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(usuarioNick, grupoTitulo);
		grupo = grupoRepo.findByTituloIgnoreCase(grupoTitulo);
		
		if (usuario != null) {
			grupo.getUsuarios().add(usuario);		
			grupoRepo.save(grupo);
		}
		return grupo;
	}

	@Override
	public Grupo removerUsuarioDoGrupo(String usuarioNick, String grupoTitulo) {
		
		Usuario usuario = new Usuario();
		Grupo grupo = new Grupo();
		
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(usuarioNick, grupoTitulo);
		grupo = grupoRepo.findByTituloIgnoreCase(grupoTitulo);
		
		if (usuario != null) {
			grupo.getUsuarios().remove(usuario);		
			grupoRepo.save(grupo);
		}
		return grupo;
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
