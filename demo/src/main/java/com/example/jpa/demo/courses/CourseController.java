package com.example.jpa.demo.courses;

import java.util.List;
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
    
@RequestMapping(value="/topic/{id}/courses", method=RequestMethod.GET)
public List<Course> getAllCourses() {
    return courseService.getAllCourses();
}

@RequestMapping(value="/course", method=RequestMethod.POST)
public void addCourse(@RequestBody Course course) {
    courseService.addCourse(course);
}

@RequestMapping(value="course/{id}", method=RequestMethod.GET)
public Course findOneCourse(@PathVariable Integer id) {
   return courseService.getCourse(id);
}

  
}