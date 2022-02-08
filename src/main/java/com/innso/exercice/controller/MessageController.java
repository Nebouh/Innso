package com.innso.exercice.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;
import com.innso.exercice.service.ClientFolderService;
import com.innso.exercice.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private ClientFolderService clientFolderService;
	
	@PostMapping("/message/new")
	public ClientFolder newMessage(@RequestBody Message message, @RequestParam(required = false) String reference) {
		ClientFolder clientFolder = messageService.newMessage(message, reference);
		return clientFolder;
	}
	
	@PostMapping("/update")
	public ClientFolder updateClientFolder(@RequestBody ClientFolder clientFolder, @RequestParam String referenceToSet) {
		
		return clientFolderService.updateClientFolder(clientFolder, referenceToSet);
	}
	
	@GetMapping("/all")
	@ResponseBody
	public List<ClientFolder> getAllClientFolder() {
		return clientFolderService.getAllClientFolder();
	}
}
