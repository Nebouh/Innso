package com.innso.exercice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.innso.exercice.dto.Canal;
import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;
import com.innso.exercice.service.ClientFolderService;

@SpringBootTest
public class ClientFolderServiceTest {
	
	@Autowired
	private ClientFolderService clientFolderService;

	@Test
	public void testCreateClientFolder() {
		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		ClientFolder result = clientFolderService.createClientFolder(message, "test");
		
		Assert.assertNotNull(result);
		Assert.assertEquals("test", result.getClientName());
		Assert.assertEquals("test", result.getReference());
		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Assert.assertNotNull(result.getListMessage().get(0));
		Assert.assertEquals("test message", result.getListMessage().get(0).getMessage());
	}
	
	@Test
	public void testGetClientFolderByReference() {
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);
		
		ClientFolder result = clientFolderService.getClientFolderByReference(listClientFolder, "testRef");
		
		Assert.assertNotNull(result);
		Assert.assertEquals("testRef", result.getReference());
	}
	
	@Test
	public void testUpdateClientFolder() {
		ClientFolder clientFolderToModify = new ClientFolder("test", LocalDate.now(), "testRef", null);
		
		ClientFolder result = clientFolderService.updateClientFolder(clientFolderToModify, "KA-18B6");
		
		Assert.assertEquals("KA-18B6", clientFolderToModify.getReference());
		Assert.assertEquals("KA-18B6", result.getReference());
	}
	
	@Test
	public void testIsExistClientFolderTRUE() {
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);
		
		Boolean result = clientFolderService.isExistClientFolder(listClientFolder, "testRef");
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsExistClientFolderFALSE() {
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);
		
		Boolean result = clientFolderService.isExistClientFolder(listClientFolder, "test");
		Assert.assertFalse(result);
	}
	
	@Test
	public void testIsExistClientFolderEmptyList() {
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);
		
		Boolean result = clientFolderService.isExistClientFolder(listClientFolder, "test");
		Assert.assertFalse(result);
	}
}
