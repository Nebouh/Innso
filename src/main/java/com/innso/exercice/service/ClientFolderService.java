package com.innso.exercice.service;

import java.util.List;

import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;

public interface ClientFolderService {
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	ClientFolder createClientFolder(Message message, String reference);
	
	/**
	 * 
	 * @param reference
	 * @return
	 */
	ClientFolder getClientFolderByReference(String reference);
	
	/**
	 * Update a client Folder with datas sent
	 * @param clientFolderOld
	 * @param clientFolder
	 * @param reference
	 * @return
	 */
	Integer updateClientFolder(ClientFolder clientFolderToModify, String referenceToSet);
	
	/**
	 * 
	 * @param reference
	 * @return
	 */
	Boolean isExistClientFolder(String reference);
	
	List<ClientFolder> getAllClientFolder();
	
	ClientFolder saveOrUpdate(ClientFolder clientFolder);
}
