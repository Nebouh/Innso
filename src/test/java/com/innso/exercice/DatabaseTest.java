package com.innso.exercice;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.repository.ClientRepository;
import com.innso.exercice.repository.MessageRepository;


@DataJpaTest
@Sql(scripts = "/create-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseTest {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	
	//@Test
	public void saveValueTest() {
		ClientFolder clientFolder = new ClientFolder("TestDataClient", LocalDate.now(), "TestDataReference", new ArrayList<>());
		Message message = new Message(LocalDateTime.now(), "TestDataClient", "TestMessage", Canal.SMS);
		clientFolder.getListMessage().add(message);
		//clientFolderDao.saveClientFolder(clientFolder);

		clientRepository.save(clientFolder);
		
		long test = messageRepository.count();
		Assert.assertEquals(1L, test);
	}
}
