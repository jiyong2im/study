package com.springboot.apiv2.controller;

import com.springboot.apiv2.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
// 웹 애플리케이션을 통해 데이터베이스 등의 저장소에 리소스를 저장할 떄 사용하는 api
@RestController
@RequestMapping("/api/v2/post-api")
public class PostController {

    //@RequestMapping < 이거 안 씀
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello world";
    }

    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();

    }
}
