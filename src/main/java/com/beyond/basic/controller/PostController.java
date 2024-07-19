package com.beyond.basic.controller;

import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.repository.PostRepository;
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
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/list")
    @ResponseBody
    public List<PostResDto> postList(){
//        List<PostResDto> postList = postService.postList();
//        return postList;
        return postService.postList();
    }

    // lazy(지연 로딩), eager(즉시로딩) 테스트
    @GetMapping("post/member/all")
    @ResponseBody
    public void postMemberAll(){
        System.out.println(postRepository.findAll());
    }
}
