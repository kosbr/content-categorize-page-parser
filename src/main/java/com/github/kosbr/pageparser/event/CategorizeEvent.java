package com.github.kosbr.pageparser.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This event is sent by this service. It means we already read the document and saved it contents to redis.
 * So another service is able to read it and categorize.
 */
@Data
@AllArgsConstructor
public class CategorizeEvent {

    private UUID id;

}
