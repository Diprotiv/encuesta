package com.diprotiv.encuesta.builder.impl;

import com.diprotiv.encuesta.builder.UserBuilder;
import com.diprotiv.encuesta.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserBuilderImpl implements UserBuilder {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean saveUser(final User user) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("user").document(user.getUserId());
        Map<String, Object> data = objectMapper.convertValue(user, Map.class);
        ApiFuture<WriteResult> result = docRef.set(data);
        log.info("Update time: {} ", result.get().getUpdateTime());
        return true;
    }

    @Override
    public Optional<User> getUser(final String userId) throws InterruptedException, ExecutionException {
        if (StringUtils.isEmpty(userId)) {
            return Optional.empty();
        }
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve all users
        DocumentReference docRef = db.collection("user").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        // future.get() blocks on response
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            return Optional.ofNullable(objectMapper.convertValue(document.getData(), User.class));
        } else {
            System.out.println("No such document!");
            return Optional.empty();
        }
    }

    @Override
    public boolean updateMessage(final String userId, final String messageId) throws InterruptedException, ExecutionException {
        Validate.notBlank(userId, "userId cannot be null/blank");
        Validate.notBlank(messageId, "messageId cannot be null/blank");
        Optional<User> userOptional = getUser(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<String> messageIdList = Optional.ofNullable(user.getMessageIdList())
                    .orElse(new ArrayList<>());
            messageIdList.add(messageId);
            user.setMessageIdList(messageIdList);
            log.info("Adding the messageId {} to the user: {}", messageId, userId);
            log.info("Updated User: {}", user);
            saveUser(user);
            return true;
        }
        return false;
    }


}
