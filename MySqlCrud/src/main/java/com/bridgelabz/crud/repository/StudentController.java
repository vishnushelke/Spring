package com.bridgelabz.crud.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.crud.model.Student;
import com.bridgelabz.crud.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/studs")
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("/studs/{id}")
	public Student getStudent(@PathVariable(value = "id") int id) {
		return studentRepository.findById(id).orElse(null);
	}

	@PostMapping("/studs")
	public Student addStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@PutMapping("/studs/{id}")
	public Student updateStudent(@PathVariable(value = "id") int id, @RequestBody Student student) {
		return studentRepository.save(student);
	}

	@DeleteMapping("/studs/{id}")
	public String deleteStudent(@PathVariable(value = "id") int id) {
		return "student deleted";
	}

}
