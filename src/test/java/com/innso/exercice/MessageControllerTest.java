package com.innso.exercice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.innso.exercice.controller.MessageController;
import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageControllerTest {

	@Autowired
	private MessageController messageController;
	
	@MockBean
	private MessageService messageService;
	
	@MockBean
	private ClientFolderService clientFolderService;
	
	//@Test
	public void testNewMessage() {
		Message message = new Message(LocalDateTime.now(), "test", "test message", "MAIL");
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "test");
		clientFolder.getListMessage().add(message);
		
		Mockito.when(messageService.newMessage(Mockito.any(Message.class), Mockito.anyString())).thenReturn(clientFolder);
		
		ClientFolder result = messageController.newMessage(message, null);
		
		Assert.assertEquals("test", result.getClientName());
		Assert.assertEquals(1, result.getListMessage().size());
	}
	
	//@Test
	public void testUpdateClientFolder() {
		ClientFolder clientFolder = new ClientFolder("test", LocalDate.now(), "testRef");
		
		List<ClientFolder> test = messageController.getAllClientFolder();
		test.add(clientFolder);
		
		ClientFolder result = messageController.updateClientFolder(clientFolder, "testRefBis");
		//Assert.assertEquals(1, result);
	}
	
	//@Test
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
