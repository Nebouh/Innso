package com.innso.exercice.service;

import java.util.List;

import com.innso.exercice.dto.ClientFolder;
import com.innso.exercice.dto.Message;

public interface MessageService {

	ClientFolder newMessage(List<ClientFolder> listClientFolder, Message message, String reference);
}
