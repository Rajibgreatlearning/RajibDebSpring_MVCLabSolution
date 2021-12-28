package com.great.learning.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.great.learning.model.Student;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) { // Constructor dependency
														// injection
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Student> findAll() {
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		transaction.commit();
		return students;
	}

	@Transactional
	public Student findById(int id) {
		Student myStudent;
		//Transaction transaction = session.beginTransaction();
		myStudent = session.get(Student.class, id);
		//transaction.commit();
		return myStudent;

	}

	@Transactional
	public void save(Student myStudent) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(myStudent);
		transaction.commit();

	}

	@Transactional
	public void deleteById(int id) {
		Student myStudent;
		Transaction transaction = session.beginTransaction();
		myStudent = session.get(Student.class, id);
		session.delete(myStudent);
		transaction.commit();
		

	}

	
	
	@Transactional
	public void print(List<Student> students){
		for(Student student: students){
			System.out.println(student);
		}
	}
	

}
