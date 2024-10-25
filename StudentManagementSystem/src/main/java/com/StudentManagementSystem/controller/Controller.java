package com.StudentManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.StudentManagementSystem.Entity.Student;
import com.StudentManagementSystem.Service.StudentService;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private StudentService service;
	
	@GetMapping("/home")
	public String home() {
		return "home";//view page html file - > home.html
	}
	
	@GetMapping("/students")
	public String getAllStudents(Model model) {
		model.addAttribute("students",service.getAllStudents());
		return "Students";
	}
	
	/*
	 * for testing using postman
	 */
	@GetMapping("/allStudents")
	@ResponseBody
	public List<Student> getAllStudents() {
	    return service.getAllStudents();  // Return the list directly as JSON
	}

	
	@GetMapping("students/new")
	public String createStudentForm(Model model) {
		Student student=new Student(); //Hold Student object
		model.addAttribute("student", student);
		return "create-students";//html page
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student ) {
//		 System.out.println("Student First Name: " + student.getFirstName());
//		    System.out.println("Student Last Name: " + student.getLastName());
//		    System.out.println("Student Email: " + student.getEmail());
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable int id,Model model) {
		model.addAttribute("student",service.getStudentById(id));
		return "edit_return";
	}
	
	@PostMapping("/students/edit/{id}")
	public String EditStudent(@PathVariable int id, @ModelAttribute("Student") Student student) {
		
		Student editStudent= service.getStudentById(id);
		editStudent.setFirstName(student.getFirstName());
		editStudent.setLastName(student.getLastName());
		editStudent.setEmail(student.getEmail());
		
		service.saveStudent(editStudent);
		
		return "redirect:/students";
	}
	
	@GetMapping("students/{id}")
	public String deleteById(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/students";
	}
	
	/*
	 * for testing using postman
	 */
	
	@GetMapping("getStudentsById/{id}")
	@ResponseBody
	public Student getById(@PathVariable Integer id) {
		return service.getStudentById(id);
	}
	
	
	@DeleteMapping("deleteStudentsById/{id}")
	@ResponseBody
	public void deleteById(@PathVariable Integer id) {
		 service.deleteById(id);
	}
	
	@PostMapping("addStudents")
	@ResponseBody
	public void addStudents(@RequestBody Student student) {
		 service.saveStudent(student);
	}
}
