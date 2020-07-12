package com.diprotiv.encuesta.model.response;

import com.diprotiv.encuesta.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMessagesForUserResponse {
    private String userId;
    private List<Message> messageList;
}
