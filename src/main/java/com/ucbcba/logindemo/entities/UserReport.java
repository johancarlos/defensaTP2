package com.ucbcba.logindemo.entities;


import javax.persistence.*;


@Entity
@Table(name = "userReport")
public class UserReport {


    @Id
    @GeneratedValue
    public Integer id;

    private int reported;




    @ManyToMany
    private Post post;


    @ManyToMany
    private User user;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost(){return post;}
    public void setPost(Post post){this.post = post;}




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getReported() {
        return reported;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }
}
