package br.gov.sp.fatec.mini_chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.mini_chat.entity.Conversa;
import br.gov.sp.fatec.mini_chat.entity.Mensagem;
import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.ConversaRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class MiniChatApplicationTests {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ConversaRepository conversaRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testaGrupo() {
		Usuario usuario = usuarioRepo.findById(1L).get();
		assertEquals("grupo_A", usuario.getConversas().iterator().next().getTitulo());	
	}
	
	@Test
	void testaUsuario() {
		Conversa conversa = conversaRepo.findById(1L).get();
		assertEquals("joselito", conversa.getUsuarios().iterator().next().getNickname());
	}
	
	@Test
	void testaMensagemEnviada() {
		Usuario usuario = usuarioRepo.findById(1L).get();
		Iterator<Mensagem> mensagems = usuario.getMensagensEnviadas().iterator();
		assertEquals("ola_maria", (mensagems.next()).getDescription());
	}
	
	@Test
	void testaConversa() {
		Conversa conversa = conversaRepo.findById(1L).get();
		Iterator<Mensagem> mensagems = conversa.getMensagens().iterator();
		assertEquals("ola_maria", (mensagems.next()).getDescription());
	}
		
	@Test
	void testaInsercaoUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNickname("avatar");
		usuario.setEmail("avatar@teste.com");
		usuario.setConversas(new HashSet<Conversa>());
		
		Conversa conversa = new Conversa();
		conversa.setTitulo("GRUPO_B");
		conversaRepo.save(conversa);
		
		usuario.getConversas().add(conversa);
		usuarioRepo.save(usuario);
		assertNotNull(usuario.getConversas().iterator().next().getId());
	}
	@Test
	void testaBuscaUsuarioNickAndEmail() {
		Usuario usuario = usuarioRepo.findByNicknameAndEmail("joselito", "jose@teste.com");
		assertEquals(1, usuario.getId());
		
	}
	
	@Test
	void testaBuscaUsuarioNickOrEmail() {
		Usuario usuario = usuarioRepo.findByNicknameOrEmailContainsIgnoreCase("joselito", "jose@teste.com");
		assertEquals(1, usuario.getId());
		
	}
	@Test
	void testaBuscaUsuarioPorGrupoQuery() {
		List<Usuario> usuarios = usuarioRepo.buscaUsuarioPorConversaQuery("grupo_A");
		assertFalse(usuarios.isEmpty());
	}
}
