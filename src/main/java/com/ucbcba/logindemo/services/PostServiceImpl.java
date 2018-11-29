package com.ucbcba.logindemo.services;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.ucbcba.logindemo.entities.Post;
import com.ucbcba.logindemo.entities.User;
import com.ucbcba.logindemo.entities.UserReport;
import com.ucbcba.logindemo.repositories.PostRepository;
import com.ucbcba.logindemo.repositories.UserReportRepository;
import com.ucbcba.logindemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserReportRepository userReportRepository;

    @Autowired
    @Qualifier(value = "postRepository")
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository =  postRepository;
    }


    @Override
    public Iterable<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }


    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPost(Integer id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public String getPostUserName(Integer id) {
        List<User> list = userRepository.findAll();
        for (User e:
             list) {
           if(e.getId() == id){
               return e.getUsername();
           }
        }

        return "ANONYMUS";
    }

    @Override
    public int getPostReport(Integer id){
        List<UserReport> reportList = userReportRepository.findAll();
        for (UserReport ur:
            reportList){
            if (ur.getId() == id)
                return ur.getReported();
        }
        return id;
    }


    @Override
    public Post findPost(Integer id){
        Optional<Post> opt;
        opt = postRepository.findById(id);
        return opt.get();
    }
}
