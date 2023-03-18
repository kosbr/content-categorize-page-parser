package com.github.kosbr.pageparser.configuration;

import com.github.kosbr.pageparser.TaskHandler;
import com.github.kosbr.pageparser.event.TaskEvent;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    private TaskHandler taskConsumer;

    @Bean
    public Consumer<Message<TaskEvent>> contentChannelStreamFunction() {
        return taskEventMessage -> taskConsumer.handle(taskEventMessage.getPayload());
    }

}
