package com.github.kosbr.pageparser;

import com.github.kosbr.pageparser.event.TaskEvent;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskHandler {

    private final static Logger LOG = LoggerFactory.getLogger(TaskHandler.class);

    @Value("${configuration.body.size.max:1067008}")
    private Integer maxBodySizeBytes;

    @Value("${configuration.timeout:10000}")
    private Integer timeoutMs;

    @Autowired
    private final RedisTemplate<String, String> redisTemplate;

    public TaskHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // todo metrics
    public void handle(TaskEvent payload) {
        LOG.info("Going to hanlde " + payload);
        validateTask(payload);
        try {
            Document doc = Jsoup.connect(payload.getUrl())
                    .maxBodySize(maxBodySizeBytes)
                    .timeout(timeoutMs)
                    .get();
            String text = doc.body().text();
            redisTemplate.opsForValue().set(payload.getTaskId().toString(), text);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        LOG.info("Handled" + payload);
    }

    private void validateTask(TaskEvent payload) {
        if (payload.getTaskId() == null) {
            throw new IllegalArgumentException("wrong event, task id is null");
        }
        if (payload.getUrl() == null || payload.getUrl().isBlank()) {
            throw new IllegalArgumentException("wrong event, url is blank");
        }
    }
}
