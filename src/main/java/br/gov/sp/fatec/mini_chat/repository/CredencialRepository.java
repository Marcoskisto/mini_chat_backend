package br.gov.sp.fatec.mini_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.mini_chat.entity.Credencial;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
	
	public Credencial findByNome(String nome);

}
