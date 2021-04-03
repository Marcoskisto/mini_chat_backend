package br.gov.sp.fatec.mini_chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.service.ComunicacaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/conversa")
public class ConversaController {
	
	@Autowired
	ComunicacaoService comunicService;
	
	@GetMapping
	public List<Conversa> buscaTodasConversas(){
		return comunicService.buscaTodasConversas();
	}
	
	@GetMapping(value="/{id}")
	public Conversa buscaConversaId(@PathVariable("id") Long id) {
		return comunicService.buscaConversaPorId(id);
	}
	
	@PostMapping
	public List<Conversa> criaConversa(@RequestBody Conversa conversa){
		comunicService.criaConversa(conversa.getTitulo());
		return comunicService.buscaTodasConversas();
	}
	@GetMapping(value="/addNaConversa")
	public Conversa addNaConversa(
			@RequestParam(value="usuario") String usuario, 
			@RequestParam(value="conversa") String conversa){
		
		return comunicService.incluiUsuarioNaConversa(usuario, conversa);
	}
	
	
}
