package com.springboot.apiv2.controller;

import com.springboot.apiv2.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//웹 애플리케이션에서 값을 가져 올 때 사용하는 api
@RestController
@RequestMapping("/apiV2/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // 1 리퀘스트 맵핑 안쓴단다 이제 .
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        LOGGER.info("getHello 메서드가 호출되었습니다.");
        return "Hello Fuck World";
    }
    // 2
    @GetMapping(value = "/name")
    public String name(){
        LOGGER.info("getName 메서드가 호출되었습니다.");
        return "hi jiyong";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable(@PathVariable String variable){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization){
        return name + " " + email + " " + organization;
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberdto){
        // return memeberDto.getName() + " " + memberDto.getEmail() + " " + memberDto.getOrganization();
        return memberdto.toString();
    }
}
