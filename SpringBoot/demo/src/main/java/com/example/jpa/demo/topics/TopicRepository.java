package com.example.jpa.demo.topics;

import org.springframework.data.repository.CrudRepository;

/**
 * TopicRepository
 */
public interface TopicRepository extends CrudRepository <Topic, Integer> { 

    
}