package com.example.jpa.demo.topics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TopicService
 */
@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topic -> {
            topics.add(topic);
        });
        return topics;
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public Topic getTopic(Integer id) {
        System.out.println(topicRepository.findById(id).isPresent());
       return topicRepository.findById(id).get();
    }

    public void updateTopic(Topic topic) {
        topicRepository.save(topic);
    }
    
}