package com.innso.exercice.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innso.exercice.entity.Canal;
import com.innso.exercice.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	@Query(value = "INSERT INTO Message(autor_name, canal, message, message_date, message_id) VALUES (:autor, :canal, :message, :messageDate, :idMessage)", nativeQuery = true)
	Message addNewMessageToClientFolder(@Param("autor") String autor, @Param("canal") Canal canal, @Param("message") String message, @Param("messageDate") LocalDateTime messageDate, @Param("idMessage") Integer idMessage);
}
