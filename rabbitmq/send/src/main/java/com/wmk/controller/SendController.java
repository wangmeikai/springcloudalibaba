package com.wmk.controller;

import com.wmk.sendutil.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SendController {
    @Autowired
    Send send;

    @GetMapping("/send")
    public String send(){
        send.sendMessage("directExchange","direct.key",UUID.randomUUID().toString());
        return "ok";
    }
}
