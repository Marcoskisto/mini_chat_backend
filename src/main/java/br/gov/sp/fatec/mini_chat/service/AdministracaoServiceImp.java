package br.gov.sp.fatec.mini_chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
