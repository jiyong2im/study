package com.springboot.apiv2.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/delete-api")
public class DeleteController {

    //@DeleteMapping value 값과 메소드 파라메타 값이 같아야 삭제가 됌
    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable String variable){
        return variable;
    }

    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email) {
        return "e-mail : " + email;
    }
}
