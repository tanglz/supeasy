package com.uoit.network.supeasy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    private static final String template="Hello, %s!";
    private final AtomicLong counter= new AtomicLong();
    @GetMapping("/hello")
    public Hello hello(@RequestParam(value = "name",defaultValue = "world") String name){
        return new Hello(counter.incrementAndGet(),String.format(template,name));
    }
}
