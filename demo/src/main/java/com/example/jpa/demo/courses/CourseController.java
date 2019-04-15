package com.example.jpa.demo.courses;

import java.util.List;

import com.example.jpa.demo.topics.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/topic/{id}/course", method = RequestMethod.GET)
    public List<Course> getAllCourses(@PathVariable Integer id) {
        return courseService.getAllCourses(id);
    }

    @RequestMapping(value = "/topic/{topicID}/course", method = RequestMethod.POST)
    public String addCourse(@RequestBody Course course, @PathVariable Integer topicID) {
        course.setTopic(new Topic(topicID,""));
        courseService.addCourse(course);
        return "course added";
    }

    @RequestMapping(value = "course/{id}", method = RequestMethod.GET)
    public Course findOneCourse(@PathVariable Integer id) {
        return courseService.getCourse(id);
    }

}