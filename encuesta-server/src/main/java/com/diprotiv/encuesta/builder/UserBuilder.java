package com.diprotiv.encuesta.builder;

import com.diprotiv.encuesta.model.User;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Interface for the user-builder.
 */
public interface UserBuilder {

    /**
     * Method to save the user in the database.
     * @param user user
     */
    boolean saveUser(final User user) throws InterruptedException, ExecutionException;

    /**
     * Method to get the user from the database.
     * @param userId userId
     * @return question
     */
    Optional<User> getUser(final String userId) throws InterruptedException, ExecutionException;

    /**
     * Method to update the user with a new message.
     * @param userId userId
     * @param messageId messageId
     * @return statusofUpdate
     */
    boolean updateMessage(final String userId, final String messageId) throws InterruptedException, ExecutionException;
}
