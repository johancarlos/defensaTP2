package com.ucbcba.logindemo.services;

import com.ucbcba.logindemo.entities.Category;

public interface CategoryService {

    Iterable<Category> listAllCategories();
    Iterable<Category> findAll();
    Category getById(Integer id);
    void create(Category Category);
    void deleteById(Integer id);
    void save(Category autor);

}
