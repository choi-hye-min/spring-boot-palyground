package com.example.testweb.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestRequest {
    private String name;
    private Integer age;
}
