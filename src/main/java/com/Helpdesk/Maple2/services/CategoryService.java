package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Category;

public interface CategoryService {

    public Iterable<Category> findAll();

    public Category find(int id);

    public Category save(Category category);

    public void delete(int id);
}
