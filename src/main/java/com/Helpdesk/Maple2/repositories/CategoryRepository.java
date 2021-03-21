package com.Helpdesk.Maple2.repositories;

import com.Helpdesk.Maple2.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, Integer> {


}
