package br.gov.sp.fatec.mini_chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.mini_chat.entity.Grupo;
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
	void testaGrupo() {
		Usuario usuario = usuarioRepo.findById(1L).get();
		assertEquals("grupo_A", usuario.getGrupos().iterator().next().getTitulo());	
	}
	
	@Test
	void testaUsuario() {
		Grupo grupo = grupoRepo.findById(1L).get();
		assertEquals("joselito", grupo.getUsuarios().iterator().next().getNickname());
	}
	
	@Test
	void testaInsercaoUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNickname("avatar");
		usuario.setEmail("avatar@teste.com");
		usuario.setGrupos(new HashSet<Grupo>());
		
		Grupo grupo = new Grupo();
		grupo.setTitulo("GRUPO_B");
		grupoRepo.save(grupo);
		
		usuario.getGrupos().add(grupo);
		usuarioRepo.save(usuario);
		assertNotNull(usuario.getGrupos().iterator().next().getId());
	}
}
