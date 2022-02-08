package com.innso.exercice.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "client", schema = "public")
public class ClientFolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial", name = "id")
	private Integer id;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "open_date")
	private LocalDate openDate;
	
	@Column(name = "reference")
	private String reference;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "message_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Message> listMessage;
	
	public ClientFolder() {}

	public ClientFolder(String clientName, LocalDate openDate, String reference, List<Message> listMessage) {
		super();
		this.clientName = clientName;
		this.openDate = openDate;
		this.reference = reference;
		this.listMessage = listMessage;
	}

	public ClientFolder(Integer id, String clientName, LocalDate openDate, String reference,
			List<Message> listMessage) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.openDate = openDate;
		this.reference = reference;
		this.listMessage = listMessage;
	}

	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public LocalDate getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<Message> getListMessage() {
		if(listMessage == null) {
			return new ArrayList<>();
		}
		return listMessage;
	}
	
	public void setListMessage(List<Message> listMessage) {
		this.listMessage = listMessage;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientName, listMessage, openDate, reference);
	}	
}
