package com.great.learning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.great.learning.model.Student;

@Service
public interface StudentService {
	
	List<Student> findAll();
	Student findById(int id);
	void save(Student myBook);
	void deleteById(int id);
	
}
