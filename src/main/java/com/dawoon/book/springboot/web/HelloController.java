package com.dawoon.book.springboot.web;
import com.dawoon.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello") // HTTP Method인 Get의 Request를 받을 수 있는 API 생성
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto") // ResponseDto를 사용하도록 하는 코드
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
