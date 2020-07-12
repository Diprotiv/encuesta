package com.diprotiv.encuesta.builder.impl;

import com.diprotiv.encuesta.builder.MessageBuilder;
import com.diprotiv.encuesta.builder.UserBuilder;
import com.diprotiv.encuesta.model.Message;
import com.diprotiv.encuesta.model.exception.EncuestaException;
import com.diprotiv.encuesta.util.BuilderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageBuilderImpl implements MessageBuilder {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String MESSAGE_PREFIX = "message";

    @Autowired
    UserBuilder userBuilder;

    @Override
    public boolean saveMessage(final String userId, final String userMessage) throws InterruptedException, ExecutionException {
        Validate.notBlank(userId, "userId cannot be null/blank");
        Validate.notBlank(userMessage, "message cannot be null/blank");
        Message message = Message.builder()
                .userId(userId)
                .messageId(BuilderUtil.getObjectId(MESSAGE_PREFIX))
                .creationTime(Instant.now().toEpochMilli())
                .userMessage(userMessage).build();
        Firestore db = FirestoreClient.getFirestore();
        // add the messageId to the user
        boolean validateUserAndUpdate = userBuilder.updateMessage(userId, message.getMessageId());
        if (!validateUserAndUpdate) {
            log.error("No user exists with userId: {}", userId);
            throw new EncuestaException("No user exists with userId: " + userId);
        }
        DocumentReference docRef = db.collection("user")
                .document(userId)
                .collection("messages")
                .document(message.getMessageId());
        Map<String, Object> data = objectMapper.convertValue(message, Map.class);
        ApiFuture<WriteResult> result = docRef.set(data);
        log.info("Update time: {} ", result.get().getUpdateTime());
        return true;
    }

    @Override
    public List<Message> getMessages(final String userId) throws InterruptedException, ExecutionException {
        if (StringUtils.isEmpty(userId)) {
            return new ArrayList<>();
        }
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> future = db.collection("user")
                .document(userId)
                .collection("messages").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return Optional.ofNullable(documents).orElse(new ArrayList<>()).stream()
                .map(item -> item.toObject(Message.class))
                .collect(Collectors.toList());
    }
}
