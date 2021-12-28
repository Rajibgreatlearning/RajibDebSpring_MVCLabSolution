package com.great.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.great.learning.model.Student;
import com.great.learning.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		System.out.println("Inside student table");
		// get the book from DB
		List<Student> studentList = studentService.findAll();
		theModel.addAttribute("Students", studentList);
		return "list-students";    //list-books
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student newStudent = new Student();
		theModel.addAttribute("Student", newStudent);

		return "Student-form";  //Book-form

	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		// get the Book from the service
		Student theStudent = studentService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);

		} else {
			student = new Student(name,department, country);
		}

		studentService.save(student);

		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {
		// delete the customer
		studentService.deleteById(id);
		return "redirect:/students/list";
	}

	

}
