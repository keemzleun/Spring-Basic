package com.beyond.basic.controller;

import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    @ResponseBody
    public List<PostResDto> postList(){
//        List<PostResDto> postList = postService.postList();
//        return postList;
        return postService.postList();
    }
}
