package com.diprotiv.encuesta.service;

import com.diprotiv.encuesta.builder.UserBuilder;
import com.diprotiv.encuesta.model.request.SaveUserRequest;
import com.diprotiv.encuesta.model.response.GetUserResponse;
import com.diprotiv.encuesta.model.response.SaveUserResponse;
import com.diprotiv.encuesta.model.User;
import com.diprotiv.encuesta.model.exception.EncuestaException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserBuilder userBuilder;

    public SaveUserResponse saveUser(final SaveUserRequest saveUserRequest) {
        validateUserRequest(saveUserRequest);
        User user = saveUserRequest.getUser();
        try {
            log.info("Saving the user details for userId: {}", user.getUserId());
            boolean status = userBuilder.saveUser(user);
            return SaveUserResponse.builder()
                    .status(true).build();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to save user details for userId: {}", user.getUserId());
            throw new EncuestaException("Unable to save user details for userId: " + user.getUserId());
        }
    }

    public GetUserResponse getUser(final String userId) {
        try {
            log.info("Getting the user details for userId: {}", userId);
            Optional<User> userOptional = userBuilder.getUser(userId);
            if (userOptional.isPresent()) {
                return GetUserResponse.builder()
                        .user(userOptional.get())
                        .isPresent(Boolean.TRUE).build();
            }
            return GetUserResponse.builder()
                    .isPresent(Boolean.FALSE).build();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to get user details for userId: {}", userId);
            throw new EncuestaException("Unable to get user details for userId: " + userId);
        }
    }

    private void validateUserRequest(final SaveUserRequest saveUserRequest) {
        Validate.notNull(saveUserRequest, "Save user request cannot be null");
        Validate.notNull(saveUserRequest.getUser(), "User cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getUserId(), "userId cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getAuthenticationId(), "authenticationId cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getGmailId(), "gmailId cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getPassword(), "password cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getFirstName(), "firstName cannot be null");
        Validate.notBlank(saveUserRequest.getUser().getLastName(), "lastName cannot be null");
    }
}
