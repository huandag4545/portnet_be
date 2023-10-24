package com.project.portnet_be.application.join.controller;

import com.project.portnet_be.application.join.model.JoinModel;
import com.project.portnet_be.application.join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/join")
public class JoinRestController {

    private @Autowired JoinService joinService;

    @GetMapping()
    public JoinModel get(){
        System.out.print("hi");
        return joinService.get();
    }

    @PostMapping()
    public void insert(@RequestBody JoinModel joinModel){
        joinService.insert(joinModel);
    }

}
