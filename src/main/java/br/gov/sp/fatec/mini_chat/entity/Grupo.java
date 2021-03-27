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

@Entity
@Table(name = "grp_grupo")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grp_id")
	private Long id;
	
	@Column(name="grp_titulo")
	private String titulo;
	
	@Column(name="grp_descricao")
	private String descricao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ugu_grupo_usuario",
			joinColumns = {@JoinColumn(name = "grp_id")},
			inverseJoinColumns = {@JoinColumn(name = "usr_id")}
			)
	private Set<Usuario> usuarios;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupo", cascade = CascadeType.REMOVE)
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Set<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(Set<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}
