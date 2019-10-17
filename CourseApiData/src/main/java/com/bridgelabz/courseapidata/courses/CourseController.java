package com.bridgelabz.courseapidata.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.courseapidata.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping("/topics/{topicId}/courses")
	public List<Course> getAllCourse(@PathVariable String topicId) {
		return courseService.getCourses(topicId);

	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")//{foo}
	public Course getCourse(@PathVariable String id)//@PathVariable("foo") String id
	{
		return courseService.getCourse(id);
	}
	
	@RequestMapping( method = RequestMethod.POST,value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course,@PathVariable String topicId)//will get an instance of a Topic which is already available and add topic into it
	{
		course.setTopic(new Topic(topicId,"",""));
		courseService.addCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/topics/{topicId}/courses/{id}")//{foo}
	public void deleteCourse(@PathVariable String id)//@PathVariable("foo") String id
	{
		courseService.deleteCourse(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Course course,@PathVariable String topicId,@PathVariable String id)//will get an instance of a Topic which is already available and update topic into it
	{
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course);
	}

}
