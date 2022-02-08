package com.innso.exercice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.repository.ClientRepository;
import com.innso.exercice.service.ClientFolderService;

@Service
public class ClientFolderServiceImpl implements ClientFolderService{
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientFolder createClientFolder(Message message, String reference) {
		ClientFolder clientFolder = new ClientFolder(message.getAutorName(), LocalDate.now(), reference, new ArrayList<>());
		clientFolder.getListMessage().add(message);
		
		ClientFolder savedClientFolder = clientRepository.save(clientFolder);
		
		return savedClientFolder;
	}

	@Override
	public ClientFolder getClientFolderByReference(String reference) {
		Optional<ClientFolder> optClientFolder = clientRepository.findByReference(reference);
		
		if(optClientFolder.isPresent()) {
			return optClientFolder.get();
		}
		
		return null;
	}
	
	@Override
	public Integer updateClientFolder(ClientFolder clientFolderToModify, String referenceToSet) {
		clientFolderToModify.setReference(referenceToSet);
		return clientRepository.updateReference(clientFolderToModify.getReference(), referenceToSet);
	}

	@Override
	public Boolean isExistClientFolder(String reference) {
		
		return clientRepository.isExistReference(reference);
	}

	@Override
	public List<ClientFolder> getAllClientFolder() {
		List<ClientFolder> listClientFolder = clientRepository.findAll();
		return listClientFolder;
	}

	@Override
	public ClientFolder saveOrUpdate(ClientFolder clientFolder) {
		return clientRepository.save(clientFolder);
	}
}
