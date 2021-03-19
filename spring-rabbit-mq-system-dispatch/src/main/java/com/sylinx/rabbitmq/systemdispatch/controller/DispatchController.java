package com.sylinx.rabbitmq.systemdispatch.controller;

import com.sylinx.rabbitmq.systemdispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @GetMapping("/order")
    public String lock(String orderId) throws Exception {
        if (orderId.equals("1001")) {
            Thread.sleep(3000L);
        }
        dispatchService.dispatch(orderId);
        return "success";
    }
}
