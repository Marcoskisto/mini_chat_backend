package br.gov.sp.fatec.mini_chat.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Entity
@Table(name = "cnv_conversa")
public class Conversa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cnv_id")
	private Long id;
	
	@Column(name="cnv_titulo")
	private String titulo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ucu_conversa_usuario",
			joinColumns = {@JoinColumn(name = "ucu_cnv_id")},
			inverseJoinColumns = {@JoinColumn(name = "ucu_usr_id")}
			)
	private Set<Usuario> destinatarios;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "conversa", cascade = CascadeType.REMOVE)
	private Set<Mensagem> mensagens;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Set<Usuario> getUsuarios() {
		return destinatarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.destinatarios = usuarios;
	}
	public Set<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(Set<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}
