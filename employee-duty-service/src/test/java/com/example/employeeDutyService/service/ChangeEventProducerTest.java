package com.example.employeeDutyService.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class ChangeEventProducerTest {

    @Autowired
    private OutputDestination outputDestination;

    @Autowired
    private ChangeEventProducer changeEventProducer;

    @Test
    public void publishedMessage_WillBe_Received(){
        changeEventProducer.sendEvent(1);
        Message<byte[]> result = outputDestination.receive(100, "change-event");
        assertThat(result).isNotNull();
        assertThat(new String(result.getPayload())).isEqualTo("1");
    }

}