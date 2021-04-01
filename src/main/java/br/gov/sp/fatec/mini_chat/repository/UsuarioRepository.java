package br.gov.sp.fatec.mini_chat.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface UsuarioRepository extends  JpaRepository<Usuario, Long>{
	
	public Set<Usuario> findByNicknameContainsIgnoreCase(String nickname);
	
	public Set<Usuario> findByEmailContainsIgnoreCase(String email);
	
	public Usuario findByNicknameAndEmail(String nickname, String email);
	
	public Usuario findByNicknameOrEmailContainsIgnoreCase(String nickname, String email);
	
	@Query("select u from Usuario u inner join c.conversas c where c.titulo = ?1")
	public List<Usuario> buscaUsuarioPorConversaQuery(String titulo);

	public Usuario findByNicknameIgnoreCase(String remetenteNick);
	
	
}
