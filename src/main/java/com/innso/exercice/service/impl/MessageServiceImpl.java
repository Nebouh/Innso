package com.innso.exercice.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.repository.MessageRepository;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ClientFolderService clientFolderService;
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public ClientFolder newMessage(Message message, String reference) {
		
		ClientFolder clientFolder = null;
		
		message.setMessageLocalDateTime(LocalDateTime.now()); 
		
		if(StringUtils.isBlank(reference) && !clientFolderService.isExistClientFolder(reference)) {
			
			clientFolder = clientFolderService.createClientFolder(message, createReference(message.getAutorName()));
		} else {
			
			clientFolder = clientFolderService.getClientFolderByReference(reference);
			
			clientFolder.getListMessage().add(message);
			
			//messageRepository.addNewMessageToClientFolder(message.getAutorName(), message.getCanal(), reference, message.getMessageLocalDateTime(), clientFolder.getId());
			clientFolder = clientFolderService.save(clientFolder);
		}
		
		return clientFolder;
	}
	
	private String createReference(String name) {
		return "new-"+name+LocalDate.now().toString();
	}
}
