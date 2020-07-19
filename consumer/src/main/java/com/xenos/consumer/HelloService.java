package com.xenos.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        log.info("consumer HelloService hiService /hi param");
        return restTemplate.getForObject("http://producer/hi?name="+name,String.class);
    }

    public String hiError(String name) {
        log.info("consumer HelloService hiError /hi param");
        return "hi,"+name+",sorry,error!";
    }
}
