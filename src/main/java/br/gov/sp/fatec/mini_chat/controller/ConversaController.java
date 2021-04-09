package br.gov.sp.fatec.mini_chat.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.service.ComunicacaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/conversa")
public class ConversaController {
	
	@Autowired
	ComunicacaoService comunicService;
	
	@JsonView(View.ConversaResumo.class)
	@GetMapping
	public List<Conversa> buscaTodasConversas(){
		return comunicService.buscaTodasConversas();
	}
	
	@JsonView(View.Conversa.class)
	@GetMapping(value="/id/{id}")
	public Conversa buscaConversaId(@PathVariable("id") Long id) {
		return comunicService.buscaConversaPorId(id);
	}
	
	@JsonView(View.Conversa.class)
	@GetMapping(value="/titulo/{titulo}")
	public Conversa buscaConversaId(@PathVariable("titulo") String titulo) {
		return comunicService.buscaConversaPorTitulo(titulo);
	}
	
	@JsonView(View.ConversaResumo.class)
	@PostMapping
	public ResponseEntity<List<Conversa>> criaConversa(@RequestBody Conversa conversa,
			UriComponentsBuilder uriBuilder){
		
		conversa = comunicService.criaConversa(conversa.getTitulo());
		List<Conversa> conversas = comunicService.buscaTodasConversas();
		
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.setLocation(
				uriBuilder.path(
						"/conversa/id/"+ conversa.getId()
					).build().toUri()
				);
		return new ResponseEntity<List<Conversa>>(conversas, responseHeader, HttpStatus.CREATED);
	}
	
	@JsonView(View.Conversa.class)
	@GetMapping(value="/addNaConversa")
	public Conversa addNaConversa(
			@RequestParam(value="usuario") String usuario, 
			@RequestParam(value="conversa") String conversa){
		
		return comunicService.incluiUsuarioNaConversa(usuario, conversa);
	}
	
	@JsonView(View.ConversaResumo.class)
	@GetMapping(value="/usrconv/{titulo}")
	public Set<Usuario> buscaUsuariosDaConversa(@PathVariable("titulo") String titulo) {
		return comunicService.buscaUsuariosDaConversa(titulo);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Conversa> removeConversa(
			@PathVariable Long id,
			UriComponentsBuilder uriBuilder){
		
		comunicService.excluirConversa(id);
		
		return ResponseEntity.noContent().build();
	}
}
