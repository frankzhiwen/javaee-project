package org.example.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult {
    private boolean success;
    private String message;
    private Long total;
    private Object data;
}
