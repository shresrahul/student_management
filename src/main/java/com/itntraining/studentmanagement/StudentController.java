package com.itntraining.studentmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired //to create object
	private StudentRepository studentRepository;
	//@GetMapping("/students")
	public Student getStudent() {
		Student student=new Student();
		student.setStudentId(1l);
		student.setFirstName("Rahul");
		student.setLastName("Shrestha");
		student.setAddress("Samakhushi");
		return student;
	}
	
	@GetMapping("/students/{firstName}")
	public String greet(@PathVariable String firstName) {
		return "Hello "+firstName;
	}
	@PostMapping("/students")
	public ResponseEntity<?> saveStudent(@RequestBody Student student) {
		System.out.println("ID: "+student.getStudentId());
		System.out.println("First Name: "+student.getFirstName());
		System.out.println("Last Name: "+student.getLastName());
		System.out.println("Address: "+student.getAddress());
		Student savedStudent = studentRepository.save(student);
		return ResponseEntity.ok(savedStudent);
	}
	
	@GetMapping("/students")
	public ResponseEntity<?> getAllStudents(){
		List<Student> studentList=studentRepository.findAll();
		return ResponseEntity.ok(studentList);
	}
	@PutMapping("/students")
	@Transactional //save transaction automatically to the database
	public ResponseEntity<?> updateStudent(@RequestBody Student student,@RequestParam Long studentId){
		Optional<Student> oldStudentOptional=studentRepository.findById(studentId);
		oldStudentOptional.ifPresent(s->{
			s.setAddress(student.getAddress());
			s.setFirstName(student.getFirstName());
			s.setLastName(student.getLastName());
		});
		return ResponseEntity.ok("Student Updated Successfully");
	}
	@DeleteMapping("/students")
	public ResponseEntity<?> deleteStudent(@RequestParam Long studentId){
		studentRepository.findById(studentId).ifPresent(s->{
			studentRepository.delete(s);
		});
		return ResponseEntity.ok("Deleted");
	}
}
