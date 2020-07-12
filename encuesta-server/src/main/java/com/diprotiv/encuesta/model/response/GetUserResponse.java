package com.diprotiv.encuesta.model.response;

import com.diprotiv.encuesta.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private User user;
    private Boolean isPresent;
}
