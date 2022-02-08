package com.innso.exercice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.repository.ClientRepository;
import com.innso.exercice.service.ClientFolderService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientFolderServiceTest {
	
	@Autowired
	private ClientFolderService clientFolderService;
	
	@MockBean
	private ClientRepository clientRepository;

	@Test
	public void testCreateClientFolder() {
		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "test");
		clientFolder.getListMessage().add(message);
		
		Mockito.when(clientRepository.save(Mockito.any())).thenReturn(clientFolder);
		
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
		ClientFolder clientFolder = new ClientFolder();
		clientFolder.setReference("testRef");
		
		Optional<ClientFolder> optClientFolder = Optional.of(clientFolder);
		
		Mockito.when(clientRepository.findByReference(Mockito.anyString())).thenReturn(optClientFolder);
		ClientFolder result = clientFolderService.getClientFolderByReference("testRef");
		
		Assert.assertNotNull(result);
		Assert.assertEquals("testRef", result.getReference());
	}
	
	@Test
	public void testUpdateClientFolder() {
		ClientFolder clientFolderToModify = new ClientFolder("test", LocalDate.now(), "testRef");
		
		Mockito.when(clientRepository.updateReference("", "")).thenReturn(1);

		ClientFolder result = clientFolderService.updateClientFolder(clientFolderToModify, "KA-18B6");
		
		//Assert.assertEquals("KA-18B6", clientFolderToModify.getReference());
	}
	
	@Test
	public void testIsExistClientFolderTRUE() {
		
		Mockito.when(clientRepository.isExistReference(Mockito.anyString())).thenReturn(Boolean.TRUE);
		Boolean result = clientFolderService.isExistClientFolder("testRef");
		Assert.assertTrue(result);
	}
	
	@Test
	public void testIsExistClientFolderFALSE() {
		Mockito.when(clientRepository.isExistReference(Mockito.anyString())).thenReturn(Boolean.FALSE);
		Boolean result = clientFolderService.isExistClientFolder("test");
		Assert.assertFalse(result);
	}
}
