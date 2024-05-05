package com.example.testweb;

import com.example.testweb.req.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @PostMapping("/")
    public ResponseEntity test(@RequestBody TestRequest req) {
        return ResponseEntity.ok(req);
    }
}
