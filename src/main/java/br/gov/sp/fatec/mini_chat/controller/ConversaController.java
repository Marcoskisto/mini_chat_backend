package br.gov.sp.fatec.mini_chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.service.AdministracaoService;
import br.gov.sp.fatec.mini_chat.service.ComunicacaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/conversa")
public class ConversaController {
	
	ComunicacaoService comunicacaoService;
	
	@GetMapping
	public List<Conversa> buscaTodasConversas(){
		return comunicacaoService.buscaTodasConversas();
	}
	
	@GetMapping(value="/{id}")
	public Conversa buscaConversaId(@PathVariable("id") Long id) {
		return comunicacaoService.buscaConversaPorId(id);
	}
}
