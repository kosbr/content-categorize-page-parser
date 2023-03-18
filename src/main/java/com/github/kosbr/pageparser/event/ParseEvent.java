package com.github.kosbr.pageparser.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParseEvent {

    private UUID taskId;
    private String url;

}
