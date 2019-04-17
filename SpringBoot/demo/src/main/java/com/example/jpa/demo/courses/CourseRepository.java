package com.example.jpa.demo.courses;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * TopicRepository
 */
public interface CourseRepository extends CrudRepository <Course, Integer> { 


    //JPA KNOW AUTOMATICALLY WHAT you are looking foor (topic.id)
    public List<Course> findByTopicId(Integer id);
    
}