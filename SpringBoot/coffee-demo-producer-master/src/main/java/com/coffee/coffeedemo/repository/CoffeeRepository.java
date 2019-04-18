package com.coffee.coffeedemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffee.coffeedemo.model.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {

	public Coffee findByName(String name);
	public List<Coffee> findByType(String type);

}
