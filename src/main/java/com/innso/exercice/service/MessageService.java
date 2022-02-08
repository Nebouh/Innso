package com.innso.exercice.service;

import com.innso.exercice.entity.ClientFolder;
import com.innso.exercice.entity.Message;

public interface MessageService {

	ClientFolder newMessage(Message message, String reference);
}
