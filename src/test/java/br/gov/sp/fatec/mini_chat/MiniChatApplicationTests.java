package br.gov.sp.fatec.mini_chat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.mini_chat.entity.Usuario;
import br.gov.sp.fatec.mini_chat.repository.GrupoRepository;
import br.gov.sp.fatec.mini_chat.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class MiniChatApplicationTests {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private GrupoRepository grupoRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testaInsert() {
		Usuario usuario = new Usuario();
		usuario.setNickname("maria");
		usuario.setEmail("maria@maria.com.br");
		usuarioRepo.save(usuario);
		
		assertNotNull(usuario.getId());	
	}
	
	@Test
	void testaUsuario() {
		Usuario usuario = usuarioRepo.findById(13L).get();
		assertNotNull(usuario);
	}
}
