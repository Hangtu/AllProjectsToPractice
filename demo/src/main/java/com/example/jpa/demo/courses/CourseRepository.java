package com.example.jpa.demo.courses;

import org.springframework.data.repository.CrudRepository;

/**
 * TopicRepository
 */
public interface CourseRepository extends CrudRepository <Course, Integer> { 

    
}