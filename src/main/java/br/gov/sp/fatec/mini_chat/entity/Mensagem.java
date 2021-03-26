package br.gov.sp.fatec.mini_chat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "msg_mensagem")
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "msg_id")
	private Long id;
	
	@Column(name = "msg_description")
	private String description;
	
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_origin_id")
	private Long userOriginId;
	
	@ManyToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "usr_destin_id")
	private Long userDestinId;
	
	@ManyToOne(targetEntity = Grupo.class , fetch = FetchType.EAGER)
	@JoinColumn(name = "grp_destin_id")
	private Long groupDestinId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;		
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getOriginId() {
		return userOriginId;
	}
	public void setOriginId(Long originId) {
		this.userOriginId = originId;
	}
	public Long getDestinId() {
		return userDestinId;
	}
	public void setDestinId(Long destinId) {
		this.userDestinId = destinId;
	}
	public Long getGroupDestinId() {
		return groupDestinId;
	}
	public void setGroupDestinId(Long groupDestinId) {
		this.groupDestinId = groupDestinId;
	}
}
