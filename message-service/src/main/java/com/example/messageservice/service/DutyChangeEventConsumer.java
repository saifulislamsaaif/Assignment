package com.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class DutyChangeEventConsumer {

    private final MessageService messageService;

    @Bean
    public Consumer<Integer> dutyEventSink(){
        return messageService::sendMessage;
    }
}
