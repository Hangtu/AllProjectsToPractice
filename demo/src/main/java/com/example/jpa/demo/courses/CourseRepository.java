package com.example.jpa.demo.courses;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * TopicRepository
 */
public interface CourseRepository extends CrudRepository <Course, Integer> { 

    public List<Course> findByTopicId(Integer id);
    
}