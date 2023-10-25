package com.project.portnet_be.application.popol.controller;

import com.project.portnet_be.application.popol.model.PopolModel;
import com.project.portnet_be.application.popol.service.PopolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  포트폴리오
 *
 * */

@Tag(name = "포트폴리오 컨트롤러")
@RestController
@RequestMapping("/api/user")
public class PopolRestController {

    private @Autowired PopolService popolService;

    @GetMapping("/popol")
    public PopolModel get(){
        return popolService.read();
    }

    @GetMapping("/popol/{popolId}")
    public PopolModel get(@PathVariable("popolId") Long popolId){
        return popolService.readDetail(popolId);
    }

    /***
     * 생성
     * Model PopolModel
     * */
    @PostMapping("/popol")
    public Long insert(@RequestBody PopolModel popolModel){
        return popolService.creatPopol(popolModel);
    }

    @PutMapping("/popol/{popolId}")
    public Long update(@RequestBody PopolModel popolModel){
        return popolService.updtPopol(popolModel);
    }
}
