package com.innso.exercice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.repository.MessageRepository;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@MockBean
	private ClientFolderService clientFolderService;
	
	@MockBean
	private MessageRepository messageRepository;

	@Test
	public void testNewMessageClientExist() {
		
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);
		
		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);

		Mockito.when(clientFolderService.isExistClientFolder(Mockito.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(clientFolderService.getClientFolderByReference(Mockito.anyString())).thenReturn(clientFolder);
		Mockito.when(clientFolderService.saveOrUpdate(Mockito.any(ClientFolder.class))).thenReturn(clientFolder);
		Mockito.when(clientFolderService.saveOrUpdate(Mockito.any(ClientFolder.class))).thenReturn(clientFolder);
		Mockito.when(messageRepository.addNewMessageToClientFolder("", Canal.SMS, "", LocalDateTime.now(), 1)).thenReturn(message);

		
		ClientFolder result = messageService.newMessage(message, "testRef");

		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Message resultMessage = result.getListMessage().get(0);
		Assert.assertEquals("test", resultMessage.getAutorName());
		Assert.assertEquals("test message", resultMessage.getMessage());
		Assert.assertEquals(Canal.FACEBOOK, resultMessage.getCanal());
	}
	
	@Test
	public void testNewMessageClientNoClientFolder() {
		
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "test", new ArrayList<>());
		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		clientFolder.getListMessage().add(message);
		listClientFolder.add(clientFolder);

		Mockito.when(clientFolderService.isExistClientFolder(Mockito.anyString())).thenReturn(Boolean.FALSE);
		Mockito.when(clientFolderService.createClientFolder(Mockito.any(Message.class), Mockito.anyString())).thenReturn(clientFolder);

		
		ClientFolder result = messageService.newMessage(message, null);

		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Message resultMessage = result.getListMessage().get(0);
		Assert.assertEquals("test", resultMessage.getAutorName());
		Assert.assertEquals("test message", resultMessage.getMessage());
		Assert.assertEquals(Canal.FACEBOOK, resultMessage.getCanal());
	}
}
