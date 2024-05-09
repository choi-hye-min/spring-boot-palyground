package com.example.testweb;

import com.example.testweb.req.TestRequest;
import com.example.testweb.threadlocal.ThreadLocalDataInfoContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {
    private final ThreadLocalDataInfoContext threadLocalDataInfoContext;
    private final RedisTemplate redisTemplate;

    @PostMapping("/")
    public ResponseEntity test(@RequestBody TestRequest req) {
        return ResponseEntity.ok(req);
    }

    @GetMapping("/redis")
    public ResponseEntity redis(@RequestBody TestRequest req) {
        redisTemplate.opsForValue().set("test", req);
        TestRequest test = (TestRequest) redisTemplate.opsForValue().get("test");
        return ResponseEntity.ok(test);
    }
}
