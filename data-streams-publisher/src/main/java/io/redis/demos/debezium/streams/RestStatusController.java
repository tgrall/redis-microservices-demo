package io.redis.demos.debezium.streams;


import io.redis.demos.debezium.streams.listener.CDCEventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@org.springframework.context.annotation.Configuration
public class RestStatusController {

    @Inject
    io.debezium.config.Configuration config;

    @Inject
    CDCEventListener cdcEventListener;

    @GetMapping("/status")
    public Map<String,String> status() {

        Map<String,String> result = new HashMap<>();

        result.put("service", "RedisStreamsCDCPublisher");
        result.put("status", "UP");
        result.put("version", "1.0");

        return result;
    }

    @GetMapping("/start")
    public Map<String,String> start() throws IOException {
        Map<String,String> result = new HashMap<>();

        cdcEventListener.startDebezium();
        result.put("service", "RedisStreamsCDCPublisher.restart");
        result.put("action", "OK");

        return result;
    }

}