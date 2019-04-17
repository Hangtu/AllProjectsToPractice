package com.example.jpa.demo.courses;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CourseService
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(Integer id) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(id).forEach(course -> {
            courses.add(course);
        });
        return courses;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourse(Integer id) {
        System.out.println(courseRepository.findById(id).isPresent());
       return courseRepository.findById(id).get();
    }

    
}