package br.gov.sp.fatec.mini_chat.service;

import java.util.List;

import br.gov.sp.fatec.mini_chat.entity.Grupo;
import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface AdministracaoService {
	
	public Usuario criarUsuario(String nickname, String email);
	
	public Grupo criarGrupo(String titulo, String descricao);
	
	public Grupo incluirUsuarioNoGrupo(String usuarioNick, String grupoTitulo);
	
	public Grupo removerUsuarioDoGrupo(String usuarioNick, String grupoTitulo);

	public List<Grupo> excluirGrupo(String titulo);
	
}
