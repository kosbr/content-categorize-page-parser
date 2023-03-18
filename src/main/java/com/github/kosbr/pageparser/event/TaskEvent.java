package com.github.kosbr.pageparser.event;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskEvent {

    private UUID taskId;
    private String url;

}
