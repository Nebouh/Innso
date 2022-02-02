package com.innso.exercice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;
import com.innso.exercice.service.ClientFolderService;

@Service
public class ClientFolderServiceImpl implements ClientFolderService{

	@Override
	public ClientFolder createClientFolder(Message message, String reference) {
		ClientFolder clientFolder = new ClientFolder(message.getAutorName(), LocalDate.now(), reference, new ArrayList<>());
		clientFolder.getListMessage().add(message);
		return clientFolder;
	}

	@Override
	public ClientFolder getClientFolderByReference(List<ClientFolder> listClientFolder, String reference) {
		Optional<ClientFolder> optClientFolder = listClientFolder.stream().filter(client -> reference.equals(client.getReference())).findFirst();
		
		if(optClientFolder.isPresent()) {
			return optClientFolder.get();
		}
		
		return null;
	}
	
	@Override
	public ClientFolder updateClientFolder(ClientFolder clientFolderToModify, String referenceToSet) {

		clientFolderToModify.setReference(referenceToSet);
		return clientFolderToModify;
	}

	@Override
	public Boolean isExistClientFolder(List<ClientFolder> listClientFolder, String reference) {
		if(StringUtils.isBlank(reference)) {
			return Boolean.FALSE;
		}
		return listClientFolder.stream().anyMatch(client -> reference.equals(client.getReference()));
	}
}
