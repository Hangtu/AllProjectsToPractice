package com.example.jpa.demo.topics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TopicController {

@Autowired
private TopicService topicService;
    
@RequestMapping(value="topics", method=RequestMethod.GET)
public List<Topic> getAllTopics() {
    return topicService.getAllTopics();
}

@RequestMapping(value="topics", method=RequestMethod.POST)
public void addTopic(@RequestBody Topic topic) {
    topicService.addTopic(topic);
}

@RequestMapping(value="topics/{id}", method=RequestMethod.GET)
public Topic findOneTopic(@PathVariable Integer id) {
   return topicService.getTopic(id);
}

@RequestMapping(value="topics", method=RequestMethod.PUT)
public void updateTopic(@RequestBody Topic topic) {
    topicService.updateTopic(topic);
}

@RequestMapping(value="topics/{id}", method=RequestMethod.DELETE)
public void deleteTopic(@PathVariable Integer id) {
    topicService.deleteTopic(id);
}
  
  
}