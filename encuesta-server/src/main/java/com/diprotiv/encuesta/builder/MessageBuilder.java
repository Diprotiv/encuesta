package com.diprotiv.encuesta.builder;

import com.diprotiv.encuesta.model.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Interface for the message-builder.
 */
public interface MessageBuilder {

    /**
     * Method to save the message in the database.
     * @param userId userId
     * @param userMessage userMessage
     */
    boolean saveMessage(final String userId, final String userMessage) throws InterruptedException, ExecutionException;

    /**
     * Method to get all the messages from the database.
     * @param userId userId
     * @return messageList
     */
    List<Message> getMessages(final String userId) throws InterruptedException, ExecutionException;
}
