package com.example.kafka.service;

import com.example.kafka.model.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorHandlingConsumerService {

    @KafkaListener(topics = { "custom" }, containerFactory = "kafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload CustomMessage message,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                  @Header(KafkaHeaders.OFFSET) int offset) {

        log.info("message received, partition={}, offset={}, message={}", partition, offset, message);
    }

    @KafkaListener(topics = "custom.DLT")
    public void dltListen(final CustomMessage message) {
        log.info("Received from DeadLetterTopic: " + message);
    }
}