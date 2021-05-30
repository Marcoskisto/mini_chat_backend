package br.gov.sp.fatec.mini_chat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.mini_chat.controller.View;

import java.util.Set;

import javax.persistence.Column;

@Entity
@Table(name = "usr_usuario")
public class Usuario {
	
	@JsonView({View.Usuario.class, View.UsuarioResumo.class})
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private long id;
	
	@JsonView({View.Conversa.class, View.Usuario.class, View.UsuarioResumo.class})
	@Column(name = "usr_nickname")
	private String nickname;
	
	@JsonView(View.Usuario.class)
	@Column(name = "usr_email")
	private String email;
	
	@Column(name = "usr_senha")
	private String senha;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="destinatarios")
	private Set<Conversa> conversas;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remetente")
	private Set<Mensagem> mensagensEnviadas;
	
	@JsonView(View.Usuario.class)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cuc_credencial_usuario",
			joinColumns = {@JoinColumn(name = "cuc_usr_id")},
			inverseJoinColumns = {@JoinColumn(name = "cuc_crd_id")}
			)
	private Set<Credencial> credenciais;
	
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

	public Set<Credencial> getCredenciais() {
		return credenciais;
	}

	public void setCredenciais(Set<Credencial> credenciais) {
		this.credenciais = credenciais;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
