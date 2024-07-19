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
import java.io.PrintWriter;

// form 데이터 형식으로 사용자의 Post 요청을 받아 처리
@WebServlet("/hello/servlet/jsp/post")
public class HelloServletJspPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println(name);
        System.out.println(email);
        System.out.println(password);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("ok");
        printWriter.flush();
    }

}
