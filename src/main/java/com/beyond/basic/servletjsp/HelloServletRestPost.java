package com.beyond.basic.servletjsp;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/hello/servlet/rest/post")
public class HelloServletRestPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // header 정보 출력
        System.out.println(req.getHeader("Accept"));
        System.out.println(req.getHeader("Host"));
        System.out.println(req.getHeader("Connection"));
        // body 정보 출력
        BufferedReader br = req.getReader();
        String line = null;
        String value = "";
        while((line = br.readLine()) != null){
            value += line;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Hello hello = objectMapper.readValue(value, Hello.class);
        System.out.println(hello);
    }

}
