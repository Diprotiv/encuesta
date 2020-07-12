package com.diprotiv.encuesta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String messageId;
    private String userId;
    private String userMessage;
    private Long creationTime;
}
