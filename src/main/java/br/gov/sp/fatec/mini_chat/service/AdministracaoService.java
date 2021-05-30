package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface AdministracaoService extends UserDetailsService{
	
	public Usuario criarUsuario(String nickname, String email, String senha, String credencial);
	
	public List<Usuario> buscaTodosUsuarios();
	
	public List<Usuario> buscaListaDeUsuarios();
	
	public Usuario buscaUsuarioByid(Long id);
	
	public Usuario buscaUsuarioPorEmail(String email);
}
