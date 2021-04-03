package br.gov.sp.fatec.mini_chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.service.ComunicacaoService;

@RestController
@CrossOrigin
@RequestMapping(value="mensagem")
public class MensageController {
	
	@Autowired
	ComunicacaoService comunicService;
	
	@PostMapping
	public Conversa enviaMensagem(@RequestBody Mensagem mensagem) {
		Conversa conversa = comunicService.enviaMensagemEmConversa(
				mensagem.getRemetente().getNickname(), 
				mensagem.getConversa().getTitulo(), 
				mensagem.getDescription()
				);
		return conversa;
	}
}
