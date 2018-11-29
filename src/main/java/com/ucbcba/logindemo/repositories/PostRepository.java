package com.ucbcba.logindemo.repositories;

import com.ucbcba.logindemo.entities.Post;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PostRepository extends CrudRepository<Post, Integer> {
}
