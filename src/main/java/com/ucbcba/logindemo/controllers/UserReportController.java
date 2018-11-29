package com.ucbcba.logindemo.controllers;


import com.ucbcba.logindemo.entities.Post;
import com.ucbcba.logindemo.entities.User;
import com.ucbcba.logindemo.entities.UserReport;
import com.ucbcba.logindemo.services.PostService;
import com.ucbcba.logindemo.services.UserReportService;
import com.ucbcba.logindemo.entities.UserReport;

import com.ucbcba.logindemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserReportController {
    @Autowired
    private UserReportService userReportService;

    private UserService userService;
    private PostService postService;


    public UserReportService getUserReportService() {
        return userReportService;
    }

    public void setUserReportService(UserReportService userReportService) {
        this.userReportService = userReportService;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value ="/reportPost/{id}",method = RequestMethod.GET)
    public String reportPost(@PathVariable Integer id, Model model)
    {
        Post post = postService.getPost(id);
        UserReport userReport = userReportService.findUserReportbyId(id);

        //userReport.setPost(post.getId());
        userReportService.saveReport(userReport);


        return "redirect:/welcome";

        /*
        Post post = postService.getPost(id);
        if (0<post.getLikes())
        {
            userService.findUserById(post.getUser().getId()).setKarma(userService.findUserById(post.getUser().getId()).getKarma() - 1);
            post.setLikes(post.getLikes()-1);
            postService.savePost(post);
        }
        return "redirect:/welcome";
        */
    }
}
