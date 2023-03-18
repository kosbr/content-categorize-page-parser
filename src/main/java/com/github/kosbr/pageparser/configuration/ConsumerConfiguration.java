package com.github.kosbr.pageparser.configuration;

import com.github.kosbr.pageparser.TaskHandler;
import com.github.kosbr.pageparser.event.ParseEvent;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    private TaskHandler taskConsumer;

    /**
     * This bean consumes the events from API Manager to read the contents of the page
     * @return
     */
    @Bean
    public Consumer<Message<ParseEvent>> contentChannelStreamFunction() {
        return taskEventMessage -> taskConsumer.handle(taskEventMessage.getPayload());
    }

}
