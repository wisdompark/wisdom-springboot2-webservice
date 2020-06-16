package com.wisdom.book.springboot.web;

import com.wisdom.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON 반환하도록 만들어 준다.
public class HelloController {
    @GetMapping("/hello")   // Get 요청 받을수 있는 API를 만들어 준다.
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        // @RequstParam은 외부 API에서 넘긴 파라미터를 가져오는 어노테이션이다.
        return new HelloResponseDto(name, amount);
    }

}
