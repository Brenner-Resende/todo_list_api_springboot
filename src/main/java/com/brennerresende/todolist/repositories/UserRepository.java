package com.brennerresende.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brennerresende.todolist.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
