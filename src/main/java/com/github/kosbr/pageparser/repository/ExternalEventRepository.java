package com.github.kosbr.pageparser.repository;

import com.github.kosbr.pageparser.event.CategorizeEvent;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExternalEventRepository {

    @Value("${configuration.matcher-exchange}")
    private String matcherExchange;

    private final StreamBridge streamBridge;

    public ExternalEventRepository(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendEventToCategorize(UUID taskId) {
        CategorizeEvent event = new CategorizeEvent(taskId);
        streamBridge.send(matcherExchange, MessageBuilder.withPayload(event).build());
    }
}
