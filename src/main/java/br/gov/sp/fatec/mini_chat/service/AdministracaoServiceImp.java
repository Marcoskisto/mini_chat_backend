package br.gov.sp.fatec.mini_chat.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.mini_chat.entity.Credencial;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.mini_chat.repository.ConversaRepository;
import br.gov.sp.fatec.mini_chat.repository.CredencialRepository;
import br.gov.sp.fatec.mini_chat.repository.MensagemRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@Service
public class AdministracaoServiceImp implements AdministracaoService{
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	ConversaRepository conversaRepo;
	
	@Autowired
	MensagemRepository msgRepo;
	
	@Autowired
	CredencialRepository credencialRepo;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	ComunicacaoService comunicacaoService;
	
	@Override
	@Transactional
	public Usuario criarUsuario(String nickname, String email, String senha, String credencial) {
		Usuario usuario = new Usuario();
		usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase(nickname, email);
		
		if (usuario == null){
			return null;
		}
		
		Credencial cred = credencialRepo.findByNome(credencial);
		if (cred == null) {
			cred = new Credencial();
			cred.setNome(credencial);
			credencialRepo.save(cred);
		}
		
		usuario.setNickname(nickname);
		usuario.setEmail(email);
		usuario.setSenha(passEncoder.encode(senha));
		usuario.setCredenciais(new HashSet<Credencial>());
		usuario.getCredenciais().add(cred);
		usuarioRepo.save(usuario);
		return usuario;
	}
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Usuario> buscaTodosUsuarios() {
		return usuarioRepo.findAll();
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Usuario> buscaListaDeUsuarios() {
		return usuarioRepo.findAll();
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public Usuario buscaUsuarioByid(Long id) {
		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		if (usuarioOp.isPresent()){
			return usuarioOp.get();
		}
		throw new RegistroNaoEncontradoException("Usuario não encontrado");
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByEmailContainsIgnoreCase(email);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário inexistente");
		}
		return User.withUsername(email).password(usuario.getSenha())
				.authorities(usuario.getCredenciais().stream()
						.map(Credencial::getNome).collect(Collectors.toList())
						.toArray(new String[usuario.getCredenciais().size()]))
				.build();
	}

	@Override
	public Usuario buscaUsuarioPorEmail(String email) {
		return usuarioRepo.findByEmailContainsIgnoreCase(email);
		
	}
	
	public boolean isUsuarioRemententeDaMensagem(Long mensagemId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();
		
		Long usuarioAutenticadoId = usuarioRepo.findByEmailContainsIgnoreCase(emailAuth).getId();
		
		Mensagem mensagem = msgRepo.findById(mensagemId).get();
		if (mensagem.getRemetente().getId() == usuarioAutenticadoId) {
			return true;
		}
		return true;
	}
		
	
	
}
