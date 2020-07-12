package com.diprotiv.encuesta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String authenticationId;
    private String gmailId;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String profilePhotoPath;
    private List<String> messageIdList;
}
