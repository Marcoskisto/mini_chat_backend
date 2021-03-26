package br.gov.sp.fatec.mini_chat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

import javax.persistence.Column;

@Entity
@Table(name = "usr_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private long id;
	
	@Column(name = "usr_nickname")
	private String nickname;
	
	@Column(name = "usr_email")
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="usuarios")
	private Set<Grupo> grupos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remetente")
	private Set<Mensagem> mensagensEnviadas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatario")
	private Set<Mensagem> mensagensRecebidas;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Set<Mensagem> getMensagensEnviadas() {
		return mensagensEnviadas;
	}

	public void setMensagensEnviadas(Set<Mensagem> mensagensEnviadas) {
		this.mensagensEnviadas = mensagensEnviadas;
	}

	public Set<Mensagem> getMensagensRecebidas() {
		return mensagensRecebidas;
	}

	public void setMensagensRecebidas(Set<Mensagem> mensagensRecebidas) {
		this.mensagensRecebidas = mensagensRecebidas;
	}
	
	
}
