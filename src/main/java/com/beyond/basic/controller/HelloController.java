package com.beyond.basic.controller;

import com.beyond.basic.domain.Hello;
import com.beyond.basic.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public String helloWorld(){
//	// 	아래와 같이 Controller에서도 HttpServletRequest를 주입받아 사용 가능
//	// public String helloWorld(HttpServletRequest request) {
//	// 	System.out.println(request.getSession());
//	// 	System.out.println(request.getHeader("Cookie"));
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
    public Hello helloJson(){
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
    public String modelParam(@RequestParam(value = "name") String inputName, Model model){
        // model 객체에 name이라는 키값에 value를 세팅하면 해당 key:value는 화면으로 전달
        model.addAttribute("name", inputName);

        return "helloworld";
    }

    // CASE 7 ) pathvariable방식을 통해 사용자로부터 값을 받아 화면을 리턴
    // localhost:8080/hello/model-path/keem
    // localhost:8080/author/1 (pathvariable 방식)
    // <-> author?id=1 (parameter 방식)
    // pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현함으로, 좀 더 restful한 형식
    @GetMapping("/model-path/{inputName}")
    public String modelPath(@PathVariable String inputName, Model model){
        model.addAttribute("name", inputName);

        return "helloworld";
    }


    // Post 요청 (사용자 입장에서 서버에 데이터를 주는 상황)

    // 실습 )
    // 사용자에게 name, email, password를 입력할 수 있는 화면을 주는 메서드 정의
    @GetMapping("/form-view")
    public  String formView(){
        return "form-view";
    }
    // CASE 1) url 인코딩 방식(text만) 전송
    // 형식 : key1=value&key2=value
    @PostMapping("/form-post1")  // getMapping과 같은 url 패턴 사용도 가능
    @ResponseBody
    public String formPost1(@RequestParam(value = "name") String inName,
                            @RequestParam(value = "email") String inEmail,
                            @RequestParam(value = "password") String inPassword){
        // 받아온 내용 출력
        System.out.println(inName);
        System.out.println(inEmail);
        System.out.println(inPassword);
        return "ok";
    }

    @PostMapping("/form-post2")
    @ResponseBody
    public String formPost2(@ModelAttribute Hello hello){   // @ModelAttribute는 생략 가능. 데이터 바인딩을 하고 있다고 명시적으로 알려주는 것일뿐
        System.out.println(hello);
        return "ok";
    }

    // CASE 2 ) multipart/form-data 방식(text와 파일) 전송
    // url 명 : form-post3, 메서드명: formData3, 화면명: form-file-view
    // form태그: name, email, password, file
    @GetMapping("/form-file-view")
    public String formFilePost(){
        return "form-file-view";
    }

    @PostMapping("/form-file-view")
    @ResponseBody
    public String formFileHandle(Hello hello,
                                 @RequestParam(value="file")MultipartFile file)
    {
        System.out.println(hello);
        System.out.println(file.getOriginalFilename());

        return "ok";
    }

    // CASE 3 ) js를 활용한 form 데이터 전송(text)
    @GetMapping("/axios-form-view")
    public String axiosFormView(){
        // name, email, password 전송
        return "axios-form-view";
    }

    @PostMapping("/axios-form-view")
    @ResponseBody
    // axios를 통해 넘어오는 형식이 key1=value&key2=value 등 url 인코딩 방식
    public String axiosFormPost(Hello hello){
        System.out.println(hello);
        return "ok";
    }
    // CASE 4 ) js를 활용한 form 데이터 전송(+file)
    @GetMapping("axios-form-file-view")
    public String axiosFormFileView(){
        return "axios-form-file-view";
    }

    @PostMapping("axios-form-file-view")
    @ResponseBody
    public String axiosFormFilePost(Hello hello,
                                    @RequestParam(value = "file")MultipartFile file)
    {
        System.out.println(hello);
        System.out.println(file.getOriginalFilename());
        return "ok";

    }

    // CASE 5 ) js를 활용한 json 데이터 전송
    // url 패턴 aixos-json-view 화면명 aixos-json-view, get요청 메서드 동일, post요청 메서드 axiosJsonPost
    @GetMapping("axios-json-view")
    public String axiosJsonView(){

        return "axios-json-view";
    }

    @PostMapping("axios-json-view")
    @ResponseBody
    // json으로 전송한 데이터를 받을 때에는 @RequestBody 어노테이션 사용
    public String axiosJsonPost(@RequestBody Hello hello){
        System.out.println(hello);
        return "ok";
    }


    // CASE 6 ) js를 활용한 json 데이터 전송(+file)
    @GetMapping("/axios-json-file-view")
    public String axiosJsonFileView(){
        return "axios-json-file-view";
    }

    @PostMapping("/axios-json-file-view")
    @ResponseBody
    // RequestPart는 파일과 Json을 처리할 때 주로 사용하는 어노테이션
    public String axiosJsonFilePost(
//                                      @RequestParam("hello") String hello,
//                                      @RequestParam("file") MultipartFile file) throws JsonProcessingException
                                        // formData를 통해 json, file(멀티미디어)을 처리할 때 RequestPart 어노테이션을 많이 사용
                                        @RequestPart("hello") Hello hello,
                                        @RequestPart("file") MultipartFile file)
    {
        System.out.println(hello);
        // String으로 받은 뒤 수동으로 객체로 변환
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = objectMapper.readValue(hello, Hello.class);
//        System.out.println(h1.getName());
        System.out.println(file.getOriginalFilename());
        return "ok";
    }

    // CASE 7 ) js를 활용한 json 데이터  전송(+여러 파일)
    @GetMapping("/axios-json-multi-file-view")
    public String axiosJsonMultiFileView(){
        return "axios-json-multi-file-view";
    }

    @PostMapping("/axios-json-multi-file-view")
    @ResponseBody
    public String axiosJsonMultiFilePost(
            @RequestPart("hello") Hello hello,
            @RequestPart("files") List<MultipartFile> files)
    {
        System.out.println(hello);
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
        }
        return "ok";
    }

    // CASE 8 ) 중첩된 JSON 데이터 처리
    // {name:"hong", email:"hhh@naver.com", scores: [{math: 60}, {music:70}, {english:100}]}
    @GetMapping("/axios-nested-json-view")
    public String axiosNestedJsonView(){
        return "axios-nested-json-view";
    }

    @PostMapping("/axios-nested-json-view")
    @ResponseBody
    public String axiosNestedJsonPost(@RequestBody Student student){
        System.out.println(student);
        return "ok";
    }

    // builder 패턴 실습
    public void helloBuilderTest(){
        Hello hello = Hello.builder().build();
    }





}
