package br.gov.sp.fatec.mini_chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.service.AdministracaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/usuario")
public class UsuarioController {
	@Autowired
	AdministracaoService adminService;
	
	@JsonView(View.Usuario.class)
	@GetMapping(value="/dados")
	public List<Usuario> buscarTodosUsuarios(){
		return adminService.buscaTodosUsuarios();
	}
	
	@JsonView(View.UsuarioResumo.class)
	@GetMapping(value="/lista")
	public List<Usuario> buscarListaDeUsuarios(){
		return adminService.buscaListaDeUsuarios();
	}
	
	@JsonView(View.Usuario.class)
	@GetMapping(value="/{id}")
	public Usuario buscaUsuarioId(@PathVariable("id") Long id) {
		return adminService.buscaUsuarioByid(id);
	}
	
	@JsonView(View.Usuario.class)
	@PostMapping
	public Usuario cadastraUsuario(@RequestBody Usuario usuario) {
		return adminService.criarUsuario(
				usuario.getNickname(),
				usuario.getEmail(),
				usuario.getSenha(), 
				usuario.getCredenciais().toString()
			);
	}
}