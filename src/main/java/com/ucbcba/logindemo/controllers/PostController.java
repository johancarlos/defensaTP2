package com.ucbcba.logindemo.controllers;

import com.ucbcba.logindemo.entities.Category;
import com.ucbcba.logindemo.entities.Post;
import com.ucbcba.logindemo.entities.User;
import com.ucbcba.logindemo.services.CategoryService;
import com.ucbcba.logindemo.services.PostService;
import com.ucbcba.logindemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class PostController {
    private PostService postService;
    CategoryService categoryService;
    UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) { // si es que no existe un objeto categoryService te lo creo uno
        this.categoryService = categoryService;
    }

    @Autowired
    public void setUserService(UserService userService){this.userService = userService;}

    public UserService getUserService(){return userService;}


    @RequestMapping(value = "/user/posts", method = RequestMethod.GET)
    public String list(Model model) {
        Iterable<Category> category = categoryService.listAllCategories();
        Iterable<Post> postList = postService.listAllPosts();
        Iterable<User> user = userService.listAllUsers();
        model.addAttribute("variableTexto","Hello");
        model.addAttribute("Categories",category);
        model.addAttribute("postList",postList);
        model.addAttribute("users",user);
        return "/user/posts";
    }

    @RequestMapping(value = "/newPost/{id}",method = RequestMethod.GET)
    public String newPost(@PathVariable Integer id, Model model ) {
        User user = userService.findUserById(id);
        Iterable<Category> category = categoryService.listAllCategories();
        Iterable<Post> post = postService.listAllPosts();
        model.addAttribute("categories",category);
        model.addAttribute("posts",post);
        model.addAttribute("user",user);
        return "newPost";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String save(@ModelAttribute("post")Post post)
    {
        postService.savePost(post);
        return "redirect:/user/posts";
    }



    @RequestMapping(value = "/user/profile/{id}")
    public String profile(@PathVariable Integer id, Model model)
    {
        User user = userService.findUserById(id);
        model.addAttribute("user",user.getUsername());
        model.addAttribute("karma", user.getKarma());
        return "user/profile";
    }



    @RequestMapping("/post/{id}/{uid}")
    public String show(@PathVariable Integer id, @PathVariable Integer uid, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("uid", uid);
        return "showPost";
    }

    @RequestMapping(value = "/editPost/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Iterable<Category> iteCat = categoryService.listAllCategories();
        Post post = postService.findPost(id);
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("post", post);
        return "editPost";
    }


    @RequestMapping("/deletePost/{id}")
    public String delete(@PathVariable Integer id) {
        postService.deletePost(id);
        return "redirect:/user/posts";
    }

    @RequestMapping(value = "/postlike/{id}", method = RequestMethod.GET)
    public String like(@PathVariable Integer id, Model model) {
        Post post = postService.getPost(id);
        userService.findUserById(post.getUser().getId()).setKarma(userService.findUserById(post.getUser().getId()).getKarma() + 1);
        post.setLikes(post.getLikes()+1);
        postService.savePost(post);
        return "redirect:/welcome";
    }


    @RequestMapping(value ="/postunlike/{id}",method = RequestMethod.GET)
    public String dislike(@PathVariable Integer id, Model model)
    {
        Post post = postService.getPost(id);
        if (0<post.getLikes())
        {
            userService.findUserById(post.getUser().getId()).setKarma(userService.findUserById(post.getUser().getId()).getKarma() - 1);
            post.setLikes(post.getLikes()-1);
            postService.savePost(post);
        }
        return "redirect:/welcome";
    }



    @RequestMapping("/")
    public String main(Model model) {
        Iterable<Post> postList = postService.listAllPosts();
        model.addAttribute("postList",postList);
        return "root";
    }

}