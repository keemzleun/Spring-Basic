package com.beyond.basic.controller;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller : 해당 클래스가 Controller(사용자의 요청을 처리하고 응답하는 편의 기능)임을 명시
@Controller
@RequestMapping("/hello") // 클래스 차원에서 url 매핑시 RequestMapping 사용
// @RestController // Controller + 각 메서드마다 @ResponseBody
public class HelloController {

    // CASE 1 ) 사용자가 서버에게 화면 요청
    // CASE 2 ) @ResponseBody가 붙을 경우 단순 String 데이터 return(get요청)

    // @GetMapping : get 요청을 처리하고 url 패턴을 명시
    @GetMapping("/")
    // @ResponseBody: 화면이 아닌 데이터를 return
    // 만약 여기서 responseBody가 없고 return이 String이면 스프링은 templates 폴더 밑에 helloworld.html 화면을 찾아 리턴
    // @ResponseBody
    public String helloWorld() {
        return "helloworld";
    }

    // CASE 3 ) 사용자가 Json 데이터 요청(get 요청)
    // 실습 )
    // data를 리턴하되, json 형식으로 return
    // method명은 helloJson, url 패턴은 /hello/json
    @GetMapping("/json")
    @RequestMapping(value = "/json", method = RequestMethod.GET)// 메서드 차원에서도 RequsetMapping 사용 가능
    @ResponseBody
    // responseBody를 사용하면서 객체를 return시 자동으로 직렬화
    public Hello helloJson() throws JsonProcessingException {
        Hello hello = new Hello();
        hello.setName("keem");
        hello.setEmail("ji@naver.com");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String value = objectMapper.writeValueAsString(hello);
//        return value;
        return hello;
    }

    // CASE 4 ) 사용자가 json 데이터를 요청하되, parameter 형식으로 특정 객체 요청
    // get 요청 중에 특정 데이터를 요청
    @GetMapping("/param1")
    @ResponseBody
    // parameter 형식 : ?name=keem
    public Hello param1(@RequestParam(value = "name") String inputName) {
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail("keem@naver.com");
        System.out.println(hello);
        return hello;
    }

    // 실습
    // url 패턴 : model-param2, 메서드:modelParam2
    // parameter 2개: name, email => hello 객체 생성 후 리턴
    // 요첮 방식: ?name=xxx&email=xxx@naver.com
    @GetMapping("/param2")
    @ResponseBody
    // parameter 형식 : ?name=keem
    public Hello param2(@RequestParam(value = "name") String inputName,
                        @RequestParam(value = "email") String inputEmail) {
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail(inputEmail);
        return hello;
    }


    // CASE 5 ) parameter 형식으로 요청하되, 서버에서 데이터 바인딩 하는 형식
    @GetMapping("/param3")
    @ResponseBody
    // parameter가 많을 경우, 객체로 대체가 가능함
    // 객체에 각 변수가 맞게 알아서 바인딩됨(데이터 바인딩)
    // ?name=xxx&email=xx@naver.com&password=xxx
    // 데이터바인딩의 조건 : 기본생성자, setter
    public Hello param3(Hello hello){
        return hello;
    }


    // CASE 6 ) 서버에서 화면에 데이터를 넣어 사용자에게 return (model 객체 사용)
    // RestController 사용 X. 얘는 데이터를 리턴하기 때문
    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value = "name") String inputName){


        return "helloworld";
    }

}
