package com.ucbcba.logindemo.services;

import com.ucbcba.logindemo.entities.Post;

public interface PostService {
    Iterable<Post> listAllPosts();

    void createPost(Post post);

    int getPostReport(Integer id);

    Post findPost(Integer id);

    void savePost(Post post);

    Post getPost(Integer id);

    void deletePost(Integer id);

    String getPostUserName(Integer id);

}
