package com.diprotiv.encuesta.service;

import com.diprotiv.encuesta.builder.MessageBuilder;
import com.diprotiv.encuesta.model.Message;
import com.diprotiv.encuesta.model.request.GetMessagesForUserRequest;
import com.diprotiv.encuesta.model.response.GetMessagesForUserResponse;
import com.diprotiv.encuesta.model.request.SaveMessageRequest;
import com.diprotiv.encuesta.model.response.GetUserResponse;
import com.diprotiv.encuesta.model.response.SaveMessageResponse;
import com.diprotiv.encuesta.model.User;
import com.diprotiv.encuesta.model.exception.EncuestaException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class MessageService {

    @Autowired
    MessageBuilder messageBuilder;

    public SaveMessageResponse saveMessage(final SaveMessageRequest saveMessageRequest) {
        validateSaveMessageRequest(saveMessageRequest);
        final String message = saveMessageRequest.getUserMessage();
        final String userId = saveMessageRequest.getUserId();
        try {
            log.info("Saving the message details for userId: {}", userId);
            boolean status = messageBuilder.saveMessage(userId, message);
            return SaveMessageResponse.builder()
                    .status(status).build();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to save user details for userId: {}", userId);
            throw new EncuestaException("Unable to save user details for userId: " + userId);
        }
    }

    public GetMessagesForUserResponse getMessagesForUser(final String userId) {
        Validate.notNull(userId, "userId cannot be null");
        try {
            log.info("Getting the user details for userId: {}", userId);
            List<Message> messageList = messageBuilder.getMessages(userId);
            log.info("messagesList: {}", messageList);
            return GetMessagesForUserResponse.builder()
                    .userId(userId)
                    .messageList(messageList).build();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to get user details for userId: {}", userId);
            throw new EncuestaException("Unable to get user details for userId: " + userId);
        }
    }

    private void validateSaveMessageRequest(final SaveMessageRequest saveMessageRequest) {
        Validate.notNull(saveMessageRequest, "saveMessageRequest cannot be null");
        Validate.notBlank(saveMessageRequest.getUserMessage(), "message cannot be null");
        Validate.notNull(saveMessageRequest.getUserId(), "userId cannot be blank");
    }
}
