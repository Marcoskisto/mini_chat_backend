package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface AdministracaoService {
	
	public Usuario criarUsuario(String nickname, String email);
	
	public List<Usuario> buscaTodosUsuarios();
	
	public Usuario buscaUsuarioByid(Long id);
}
