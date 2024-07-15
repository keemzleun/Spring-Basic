package com.beyond.basic.service;

import com.beyond.basic.domain.Post;
import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostResDto> postList(){
        List<PostResDto> postResDtos = new ArrayList<>();
        List<Post> postList = postRepository.findAll();

        for (Post p : postList){
            postResDtos.add(p.listFromEntity());
            System.out.println("저자의 이름은 " + p.getMember().getEmail());
        }
        return postResDtos;
    }
}
