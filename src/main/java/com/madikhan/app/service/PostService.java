package com.madikhan.app.service;

import com.madikhan.app.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void save(Post post);
    void remove(Long id);
    void update(Post post);
    List<Post> listAll();
    Post listById(Long id);

}
