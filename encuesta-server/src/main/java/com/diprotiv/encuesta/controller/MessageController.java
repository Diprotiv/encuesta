package com.diprotiv.encuesta.controller;

import com.diprotiv.encuesta.model.request.SaveMessageRequest;
import com.diprotiv.encuesta.model.response.GetMessagesForUserResponse;
import com.diprotiv.encuesta.model.response.SaveMessageResponse;
import com.diprotiv.encuesta.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/")
    public SaveMessageResponse saveMessage(@RequestBody final SaveMessageRequest saveMessageRequest) {
        return messageService.saveMessage(saveMessageRequest);
    }

    @GetMapping("/{userId}")
    public GetMessagesForUserResponse getMessagesForUser(@PathVariable("userId") final String userId) {
        return messageService.getMessagesForUser(userId);
    }
}
