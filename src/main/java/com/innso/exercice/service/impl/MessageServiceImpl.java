package com.innso.exercice.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ClientFolderService clientFolderService;

	@Override
	public ClientFolder newMessage(List<ClientFolder> listClientFolder, Message message, String reference) {
		
		ClientFolder clientFolder = null;
		
		message.setMessageLocalDateTime(LocalDateTime.now());
		
		if(StringUtils.isBlank(reference) && !clientFolderService.isExistClientFolder(listClientFolder, reference)) {
			
			clientFolder = clientFolderService.createClientFolder(message, createReference(message.getAutorName()));
		} else {
			clientFolder = clientFolderService.getClientFolderByReference(listClientFolder, reference);
			
			if(clientFolder == null) {
				//FIXME Throw Exception here ?
				return null;
			}
			
			if(CollectionUtils.isEmpty(clientFolder.getListMessage())) {
				clientFolder.setListMessage(new ArrayList<>());
			}
			clientFolder.getListMessage().add(message);
		}
		
		return clientFolder;
	}
	
	private String createReference(String name) {
		return "new-"+name+LocalDate.now().toString();
	}
}
