package com.innso.exercice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message", schema = "public")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial", name = "id")
	private Integer id;
	
	@Column(name = "message_date")
	private LocalDateTime messageLocalDateTime;
	
	@Column(name = "autor_name")
	private String autorName;
	
	@Column(name = "message")
	private String message;
	private Canal canal;

	public Message() {}
	
	public Message(LocalDateTime messageLocalDateTime, String autorName, String message, String canal) {
		super();
		this.messageLocalDateTime = messageLocalDateTime;
		this.autorName = autorName;
		this.message = message;
		this.canal = Canal.valueOf(canal);
	}

	public Message(LocalDateTime messageLocalDateTime, String autorName, String message, Canal canal) {
		super();
		this.messageLocalDateTime = messageLocalDateTime;
		this.autorName = autorName;
		this.message = message;
		this.canal = canal;
	}
	
	public Message(Integer id, LocalDateTime messageLocalDateTime, String autorName, String message, Canal canal) {
		super();
		this.id = id;
		this.messageLocalDateTime = messageLocalDateTime;
		this.autorName = autorName;
		this.message = message;
		this.canal = canal;
	}

	public LocalDateTime getMessageLocalDateTime() {
		return messageLocalDateTime;
	}
	
	public void setMessageLocalDateTime(LocalDateTime messageLocalDateTime) {
		this.messageLocalDateTime = messageLocalDateTime;
	}
	
	public String getAutorName() {
		return autorName;
	}
	
	public void setAutorName(String autorName) {
		this.autorName = autorName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
