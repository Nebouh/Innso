package com.innso.exercice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.innso.exercice.controller.MessageController;
import com.innso.exercice.dto.Canal;
import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

	@Autowired
	private MessageController messageController;
	
	@Mock
	private MessageService messageService;
	
	@Mock
	private ClientFolderService clientFolderService;
	
	@Test
	public void testNewMessage() {
		Message message = new Message(LocalDateTime.now(), "test", "test message", "MAIL");
		
		ClientFolder result = messageController.newMessage(message, null);
		
		Assert.assertEquals("test", result.getClientName());
		Assert.assertEquals(1, result.getListMessage().size());
		
		Message responseMessage = new Message(LocalDateTime.now(), "testResponse", "test message", Canal.MAIL);
		System.out.println(result.getReference());
		ClientFolder resultResponse = messageController.newMessage(responseMessage, result.getReference());
		
		Assert.assertEquals("test", resultResponse.getClientName());
		Assert.assertEquals(2, resultResponse.getListMessage().size());
		Assert.assertEquals("testResponse", resultResponse.getListMessage().get(1).getAutorName());
	}
	
	@Test
	public void testUpdateClientFolder() {
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef", new ArrayList<>());
		
		List<ClientFolder> test = messageController.getAllClientFolder();
		test.add(clientFolder);
		
		ClientFolder result = messageController.updateClientFolder(clientFolder, "testRefBis");
		Assert.assertEquals("testRefBis", result.getReference());
	}
	
	@Test
	public void testJsonToEnum() throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("canal", "SMS");
		
		Canal canal = Canal.valueOf(jsonObj.getString("canal"));
		
		Assert.assertEquals(Canal.SMS, canal);
	}
	
	/*
	 * private LocalDateTime messageLocalDateTime;
	private String autorName;
	private String message;
	private Enum<Canal> canal;
	 */
}
