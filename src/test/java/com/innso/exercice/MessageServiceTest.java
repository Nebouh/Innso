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
import org.springframework.util.CollectionUtils;

import com.innso.exercice.dto.Canal;
import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@Mock
	private ClientFolderService clientFolderService;

	@Test
	public void testNewMessageClientExist() {
		
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", null);
		listClientFolder.add(clientFolder);;

		Mockito.when(clientFolderService.isExistClientFolder(Mockito.anyList(), Mockito.anyString())).thenReturn(Boolean.TRUE);

		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		ClientFolder result = messageService.newMessage(listClientFolder, message, "testRef");

		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Message resultMessage = result.getListMessage().get(0);
		Assert.assertEquals("test", resultMessage.getAutorName());
		Assert.assertEquals("test message", resultMessage.getMessage());
		Assert.assertEquals(Canal.FACEBOOK, resultMessage.getCanal());
	}
	
	@Test
	public void testNewMessageClientListEmpty() {

		List<ClientFolder> listClientFolder = new ArrayList<>();
		Mockito.when(clientFolderService.isExistClientFolder(Mockito.anyList(), Mockito.anyString())).thenReturn(Boolean.TRUE);

		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		ClientFolder result = messageService.newMessage(listClientFolder, message, null);

		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Message resultMessage = result.getListMessage().get(0);
		Assert.assertEquals("test", resultMessage.getAutorName());
		Assert.assertEquals("test message", resultMessage.getMessage());
		Assert.assertEquals(Canal.FACEBOOK, resultMessage.getCanal());
	}
	
	@Test
	public void testNewMessageClientNoClientFolder() {
		
		List<ClientFolder> listClientFolder = new ArrayList<>();
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "test", null);
		listClientFolder.add(clientFolder);

		Mockito.when(clientFolderService.isExistClientFolder(Mockito.anyList(), Mockito.anyString())).thenReturn(Boolean.FALSE);

		Message message = new Message(LocalDateTime.now(), "test", "test message", Canal.FACEBOOK);
		ClientFolder result = messageService.newMessage(listClientFolder, message, null);

		Assert.assertFalse(CollectionUtils.isEmpty(result.getListMessage()));
		Message resultMessage = result.getListMessage().get(0);
		Assert.assertEquals("test", resultMessage.getAutorName());
		Assert.assertEquals("test message", resultMessage.getMessage());
		Assert.assertEquals(Canal.FACEBOOK, resultMessage.getCanal());
	}
}
