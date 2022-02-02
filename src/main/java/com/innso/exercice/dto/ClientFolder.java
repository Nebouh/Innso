package com.innso.exercice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientFolder {

	private String clientName;
	private LocalDate openDate;
	private String reference;
	private List<Message> listMessage;
	
	public ClientFolder() {}

	public ClientFolder(String clientName, LocalDate openDate, String reference, List<Message> listMessage) {
		super();
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

	@Override
	public int hashCode() {
		return Objects.hash(clientName, listMessage, openDate, reference);
	}	
}
