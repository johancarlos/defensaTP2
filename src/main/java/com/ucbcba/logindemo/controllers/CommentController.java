package com.ucbcba.logindemo.controllers;


import com.ucbcba.logindemo.entities.Comment;
import com.ucbcba.logindemo.entities.Post;
import com.ucbcba.logindemo.services.CommentService;
import com.ucbcba.logindemo.services.PostService;
import com.ucbcba.logindemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CommentController
{
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @Autowired
    public void setCommentService(CommentService commentService)
    {
        this.commentService = commentService;
    }

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService)
    {
        this.postService = postService;
    }

    @RequestMapping (value = "/comment", method = RequestMethod.POST)
    public String save (Comment comment, @RequestParam("value1") Integer valueOne)
    {
        comment.setUser(userService.findUserById(valueOne));
        commentService.saveComment(comment);
        return "redirect:/post/" + comment.getPost().getId() + "/" + valueOne;
    }

    @RequestMapping(value="/commentlike/{id}/{idpost}/{uid}",method = RequestMethod.GET)
    public String like(@PathVariable Integer id, @PathVariable Integer idpost,@PathVariable Integer uid, Model model)
    {
        Post post = postService.getPost(idpost);
        Comment comment = commentService.getComment(id);
        userService.findUserById(comment.getUser().getId()).setKarma(userService.findUserById(comment.getUser().getId()).getKarma() + 1);
        comment.setLikes(comment.getLikes()+1);
        commentService.saveComment(comment);
        return "redirect:/post/"+ post.getId() + "/" + uid;
    }



    @RequestMapping(value ="/commentdislike/{id}/{idpost}/{uid}",method = RequestMethod.GET)
    public String dislike(@PathVariable Integer id, @PathVariable Integer idpost,@PathVariable Integer uid, Model model)
    {
        Post post = postService.getPost(idpost);
        Comment comment = commentService.getComment(id);
        userService.findUserById(comment.getUser().getId()).setKarma(userService.findUserById(comment.getUser().getId()).getKarma() - 1);
        if (0<comment.getLikes())
        {
            comment.setLikes(comment.getLikes()-1);
            commentService.saveComment(comment);
        }
        return "redirect:/post/"+ post.getId() + "/" + uid;
    }
}
