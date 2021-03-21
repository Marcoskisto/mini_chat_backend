package br.gov.sp.fatec.mini_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Usuario;

public interface UsuarioRepository extends  JpaRepository<Usuario, Long>{

}
