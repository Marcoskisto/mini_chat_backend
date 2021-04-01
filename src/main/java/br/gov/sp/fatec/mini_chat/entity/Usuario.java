package br.gov.sp.fatec.mini_chat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Set<Conversa> conversas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remetente")
	private Set<Mensagem> mensagensEnviadas;
	
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

	public Set<Conversa> getConversas() {
		return conversas;
	}

	public void setConversas(Set<Conversa> conversas) {
		this.conversas = conversas;
	}

	public Set<Mensagem> getMensagensEnviadas() {
		return mensagensEnviadas;
	}

	public void setMensagensEnviadas(Set<Mensagem> mensagensEnviadas) {
		this.mensagensEnviadas = mensagensEnviadas;
	}
}
