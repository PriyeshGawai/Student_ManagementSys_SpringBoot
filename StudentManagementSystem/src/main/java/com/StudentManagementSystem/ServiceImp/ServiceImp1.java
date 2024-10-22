package com.StudentManagementSystem.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentManagementSystem.Entity.Student;
import com.StudentManagementSystem.Service.StudentService;
import com.StudentManagementSystem.repository.StudentRepository;

/*
 * service class is use for bussiness logic
 */
@Service
public class ServiceImp1 implements StudentService{

	@Autowired
	StudentRepository repository;
	
	@Override
	public List<Student> getAllStudents() {
		
		return repository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return repository.save(student);
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
		
	}

}
