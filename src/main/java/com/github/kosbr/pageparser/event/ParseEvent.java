package com.github.kosbr.pageparser.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This event is consumed by this service.
 * We must go to this URL and read the contents.
 */
@Data
@AllArgsConstructor
public class ParseEvent {

    private UUID taskId;
    private String url;

}
