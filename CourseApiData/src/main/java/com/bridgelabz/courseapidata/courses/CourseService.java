package com.bridgelabz.courseapidata.courses;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getCourses(String topicId) {
		ArrayList<Course> courses = new ArrayList<Course>();
		// ???
		courseRepository.findByTopicId(topicId).forEach(courses::add);
		return courses;
	}

	public Course getCourse(String id) {
		return courseRepository.findById(id).orElse(null);
	}

	public void addCourse(Course course) {
		courseRepository.save(course);

	}

	public void updateCourse(Course course) {
		courseRepository.save(course);

	}

	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}

}
