package com.innso.exercice.dto;

import java.time.LocalDateTime;

public class Message {

	private LocalDateTime messageLocalDateTime;
	private String autorName;
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
}
