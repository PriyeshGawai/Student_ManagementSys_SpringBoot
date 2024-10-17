package com.StudentManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("students/new")
	public String createStudentForm(Model model) {
		Student student=new Student(); //Hold Student object
		model.addAttribute("student", student);
		return "create-students";//html page
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student ) {
		 System.out.println("Student First Name: " + student.getFirstName());
		    System.out.println("Student Last Name: " + student.getLastName());
		    System.out.println("Student Email: " + student.getEmail());
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable int id,Model model) {
		model.addAttribute("student",service.getStudentById(id));
		return "edit_return";
	}
}