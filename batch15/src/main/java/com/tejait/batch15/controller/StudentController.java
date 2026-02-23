package com.tejait.batch15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tejait.batch15.model.Student;
import com.tejait.batch15.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService service;
	
	//-----------------------SAVE STUDENT------------------------------------------------------------------------------
	@RequestMapping (value = "saveStudent" , method = RequestMethod.POST)
	public ResponseEntity<Student> saveStudent(@RequestBody Student stud){
		Student saveStud=service.saveStudent(stud);
		ResponseEntity<Student> response =new ResponseEntity<Student>(saveStud, HttpStatus.CREATED);
		
		return response;
		
	}
	
	//-----------------------      UPDATE STUDENT------------------------------------------------------------------------------
	
   @RequestMapping(value = "StudentUpdatedDetails", method = RequestMethod.PUT)
	public ResponseEntity<Student> updateStudent(@RequestBody Student StudentUpdate ){
		
	  Student UPD=service.StudentUpdatedDetails(StudentUpdate);
	  return new ResponseEntity<Student>(UPD ,HttpStatus.OK);
	   
	   
	}
 //----------------------------          DELETE STUDENT------------------------------------------------------------------------------
   
   @RequestMapping(value = "studentDelById/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<String> deleteStudentById(@PathVariable Integer id){
	   
	   				service.deleteStudent(id);
	   return new ResponseEntity<String>("Deleted Student ID ::"+id , HttpStatus.OK);
   }
   
 //----------------------------          FIND STUDENT by ID..... ------------------------------------------------------------------------------
   @RequestMapping(value = "getByStudentId/{id}", method = RequestMethod.GET)
   public ResponseEntity<Student> getByStuId(@PathVariable Integer id){
	   Optional<Student> StudObj=service.getById(id);
	   return new ResponseEntity<Student>(StudObj.get(), HttpStatus.OK);
	   
   }
 //----------------------------          FIND STUDENT (Get ALL).......... ---------------------------------------------------------------------------
  
@RequestMapping (value= "getAll", method = RequestMethod.GET)
   public ResponseEntity<List<Student>> getAllStudents(){
	
	List<Student> list =service.getAllStudents();
	
	return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	   
   }
   



 
}
