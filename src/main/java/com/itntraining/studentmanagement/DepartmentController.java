package com.itntraining.studentmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentRepository DepartmentRepository;
	
	@PostMapping("/departments")
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		Department savedDepartment=DepartmentRepository.save(department);
		return ResponseEntity.ok(savedDepartment);
	}
	
	@GetMapping("/departments")
	public ResponseEntity<?> getAllDepartments(){
		List<Department> departmentList=DepartmentRepository.findAll();
		return ResponseEntity.ok(departmentList);
	}
	
	@PutMapping("/departments")
	@Transactional //save transaction automatically to the database
	public ResponseEntity<?> updateDepartment(@RequestBody Department department,@RequestParam Long departmentId){
//		Optional<Department> oldDepartmentOptional=DepartmentRepository.findById(departmentId);
//		oldDepartmentOptional.ifPresent(s->{
//			s.setDepartmentName(department.getDepartmentName());
//		});
		DepartmentRepository.findById(departmentId).ifPresent(d->{
			d.setDepartmentName(department.getDepartmentName());
		});
		return ResponseEntity.ok("Department Updated Successfully");
	}
	
	@DeleteMapping("/departments")
	public ResponseEntity<?> deleteDepartment(@RequestParam Long departmentId){
//		DepartmentRepository.findById(departmentId).ifPresent(s->{
//			DepartmentRepository.delete(s);
//		});
		DepartmentRepository.deleteById(departmentId);
		return ResponseEntity.ok("Deleted");
	}
	
}
