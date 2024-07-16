package com.beyond.basic.servletjsp;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

// Controller가 아닌 WebServlet을 통해 라우팅
// 메서드 단위가 아닌 클래스 단위
@WebServlet("/hello/servlet/rest/get")
public class HelloServletRestGet extends HttpServlet {
    @Override
    // HttpServletRequest에는 사용자의 요청 정보가 담겨 있음
    // HttpServletResponse에는 사용자에게 응답 정보를 담아줘야 함
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("honghong");
        hello.setEmail("honhon@naver.com");
        hello.setPassword("12345678");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hello);

        // 응답 바디 생성
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        // 기본적으로 버퍼를 통해 데이터가 조립되므로, 버퍼를 비우는 과정
        printWriter.flush();
    }
}
