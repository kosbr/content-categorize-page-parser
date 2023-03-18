package com.github.kosbr.pageparser.repository;

import com.github.kosbr.pageparser.event.CategorizeEvent;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExternalEventRepository {

    private final static Logger LOG = LoggerFactory.getLogger(ExternalEventRepository.class);


    @Value("${configuration.matcher-exchange}")
    private String matcherExchange;

    private final StreamBridge streamBridge;

    public ExternalEventRepository(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendEventToCategorize(UUID taskId) {
        CategorizeEvent event = new CategorizeEvent(taskId);
        streamBridge.send(matcherExchange, MessageBuilder.withPayload(event).build());
        LOG.info("Event sent to matcher " + taskId);
    }
}
