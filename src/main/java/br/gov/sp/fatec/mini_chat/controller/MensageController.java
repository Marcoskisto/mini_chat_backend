package br.gov.sp.fatec.mini_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.service.ComunicacaoService;

@RestController
@CrossOrigin
@RequestMapping(value="/mensagem")
public class MensageController {
	
	@Autowired
	ComunicacaoService comunicService;
	
	@JsonView(View.Conversa.class)
	@PostMapping
	public Conversa enviaMensagem(@RequestBody Mensagem mensagem) {
		Conversa conversa = comunicService.enviaMensagemEmConversa(
				mensagem.getRemetente().getNickname(), 
				mensagem.getConversa().getTitulo(), 
				mensagem.getDescription()
				);
		return conversa;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Mensagem> removeMensagem(
			@PathVariable Long id,
			UriComponentsBuilder uriBuilder
			){
		
		comunicService.excluirMensagem(id);

		return ResponseEntity.noContent().build();
	}
	
	@JsonView(View.MensagemResumo.class)
	@PutMapping("/update/{id}")
	public ResponseEntity<Mensagem> updateMensagem(@RequestBody Mensagem mensagem, @PathVariable Long id) {
	    Mensagem updatedMensagem = comunicService.updateMensagem(id, mensagem.getDescription());
	    if (updatedMensagem == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(updatedMensagem);
	    }
	}
	
}
