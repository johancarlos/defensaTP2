package com.ucbcba.logindemo.controllers;


import com.ucbcba.logindemo.entities.Post;
import com.ucbcba.logindemo.services.PostService;
import com.ucbcba.logindemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class HomeController {


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registrationInit(Model model) {
        model.addAttribute("postList", postService.listAllPosts());
        return "home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model)
    {
        model.addAttribute("postList", postService.listAllPosts());
        return "admin";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        com.ucbcba.logindemo.entities.User userLooged=userService.findByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("userLooged", userLooged.getId());
        System.out.println(userLooged.getId());

        if (user.getAuthorities().toArray()[0].toString().equals("ADMIN")){
            return "redirect:/admin";
        }
        Iterable<Post> postList = postService.listAllPosts();
        model.addAttribute("postList",postList);
        model.addAttribute("postService", postService);
        return "welcome";
    }
}


