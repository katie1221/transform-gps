package com.example.demo.controller;

import com.example.demo.enity.PositionRequestJson;
import com.example.demo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qzz
 * @date 2024/10/18
 */
@RestController
public class TestController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/api/{version}/position/test/")
    public Object getTransferGps(@RequestBody PositionRequestJson requestJson){
        return positionService.getTransferGps(requestJson);
    }
}
