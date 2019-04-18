package com.coffee.coffeedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.coffeedemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
