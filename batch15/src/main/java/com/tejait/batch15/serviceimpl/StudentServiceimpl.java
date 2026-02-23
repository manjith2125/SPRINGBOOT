package com.tejait.batch15.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tejait.batch15.model.Student;
import com.tejait.batch15.repository.StudentRepository;
import com.tejait.batch15.service.StudentService;

@Service
public class StudentServiceimpl  implements StudentService{

	@Autowired
	StudentRepository repository;
	
	@Override
	public Student saveStudent(Student stud) {
		
		
		stud.setFullname(stud.getFname().concat("  "+stud.getLname()));
		
	    Student	savestudent=repository.save(stud);
		
		return savestudent;
	}

	@Override
	public Student StudentUpdatedDetails(Student studentUpdate) {
		
		return repository.save(studentUpdate);
	}

	@Override
	public void deleteStudent(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public Optional<Student> getById(Integer id) {
		
		return repository.findById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		
		return repository.findAll();
	}

	

	
	
	
}
