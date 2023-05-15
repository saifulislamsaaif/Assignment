package com.example.employeeDutyService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChangeEventProducer {

    private final StreamBridge streamBridge;
    private static final String BINDING_NAME = "binding-out-change-event";

    public void sendEvent(int dutyId) {
        log.debug("Sending duty id: {} to kafka", dutyId);

        Message<Integer> event =  MessageBuilder.withPayload(dutyId).build();

        streamBridge.send(BINDING_NAME, event );
    }
}
