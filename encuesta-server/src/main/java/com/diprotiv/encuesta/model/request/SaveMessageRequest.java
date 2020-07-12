package com.diprotiv.encuesta.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveMessageRequest {
    private String userId;
    private String userMessage;
}
