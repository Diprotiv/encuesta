package com.diprotiv.encuesta.model.request;

import com.diprotiv.encuesta.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {
    private User user;
}
