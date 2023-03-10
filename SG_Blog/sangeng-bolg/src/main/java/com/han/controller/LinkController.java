package com.han.controller;

import com.han.domain.ResponseResult;
import com.han.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkedService;

    @GetMapping("/getAllLink")
    public ResponseResult getAllLinked(){
        return linkedService.getAllLink();
    }
}
