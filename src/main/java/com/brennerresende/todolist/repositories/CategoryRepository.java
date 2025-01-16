package com.brennerresende.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brennerresende.todolist.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
