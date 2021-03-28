package br.gov.sp.fatec.mini_chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.service.AdministracaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/usuario")
public class UsuarioController {
	@Autowired
	AdministracaoService adminService;
	
	@GetMapping
	public List<Usuario> buscarTodosUsuarios(){
		return adminService.buscaTodosUsuarios();
	}
	
	@GetMapping(value="/{id}")
	public Usuario buscaUsuarioId(@PathVariable("id") Long id) {
		return adminService.buscaUsuarioByid(id);
	}
}
