package com.shaoqiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: renshaoqiang
 * @date: 2023/6/23
 * @description: 测试Kafka的使用
 */
@RestController
public class TestKafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/sendKafka")
    public String kafka(@RequestParam("message") String message) {
        kafkaTemplate.send("testKafka", message);
        return "send success";
    }
}
