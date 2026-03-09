package com.tejait.batch15.controller;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.exceptions.AccountAlreadyExists;
import com.tejait.batch15.exceptions.DataNotFoundException;
import com.tejait.batch15.exceptions.IdNotFoundExceptions;
import com.tejait.batch15.exceptions.InSufficientFundsException;
import com.tejait.batch15.exceptions.MobileNumberAlreadyExists;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//@CrossOrigin
@RestController
@RequestMapping("/employee")
public class  EmployeeController {
	
	//@Autowired
	EmployeeService service;

//	public EmployeeController(EmployeeService service) {
//		super();
//		this.service = service;
//	}
	//-----------------------SAVE EMPLOYEE------------------------------------------------------------------------------
	
	@RequestMapping (value="saveEmp", method=RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp){
		
		Employee saveEmp=service.saveEmployee(emp);
		
	ResponseEntity<Employee> response=new ResponseEntity<Employee>(saveEmp, HttpStatus.CREATED);
	 
		return response;
	}
	//-----------------------UPDATE EMPLOYEE  ------------------------------------------------------------------------------
	
	// Save operation will work for UPDATE operation there is no special UPDATE operation ... 
	// Here we PUT method...
	
	@RequestMapping(value = "updateEmp" , method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp){
		
	Employee updatedEmp=service.saveEmployee(emp);
	
		return new ResponseEntity<Employee>(updatedEmp, HttpStatus.OK);
		
	}
	
	//-----------------------DELETE EMPLOYEE BY ID  ------------------------------------------------------------------------------
	
	
	
	@RequestMapping(value = "deleteEmp/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeId(@PathVariable Integer id){
						service.deleteEmployee(id);								// delete operations uses void, so no return statement needed but here we are using 
																				// just to tell which is being removed...
		
		return new ResponseEntity<>("Deleted Employee ID", HttpStatus.OK); 
	}
	
	//-----------------------GET EMPLOYEE BY ID  ------------------------------------------------------------------------------
	
	
	@RequestMapping(value = "getByEmpId/{id}",method = RequestMethod.GET)
	public ResponseEntity<Employee> getByEmpId(@PathVariable Integer id){
		
		Optional<Employee> empObj=service .getById(id);
		
		return new ResponseEntity<Employee>(empObj.get(), HttpStatus.OK);
		
	}
	//-----------------------GET  ALL -EMPLOYEE   ------------------------------------------------------------------------------
	
	
	@RequestMapping (value = "getAll",method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmps(){
		
		List<Employee> list=service.getAllEmps();
		
		for(Employee emp: list) {
			System.out.println(emp);
		}
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("existsById/{id}")
	public ResponseEntity<Boolean> existsById(@PathVariable Integer id){
		
		boolean result=service.existsById(id);
		
		if(!result) {
			//throw new IdNotFoundExceptions("Given Id Not Available");
			//throw new AccountAlreadyExists("Account in use Check Again");
			//throw new DataNotFoundException("Data Not Found From Resource");
			//throw new MobileNumberAlreadyExists("Mobile Number Already in Use");
			throw new InSufficientFundsException("InSufficient Funds Add More");
			
	
		}
		
		return new ResponseEntity<Boolean>(result , HttpStatus.OK);
		
	}
	@GetMapping("pagination")
	public ResponseEntity<Page<Employee>> paginationData(@RequestParam int pageNum, @RequestParam int pageSize){
		
		Page<Employee> page=service.getPagination(pageNum, pageSize);
		return new ResponseEntity<Page<Employee>>(page, HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("dataSorting")
	public ResponseEntity<List<Employee>> sorting(  @RequestParam String property, @RequestParam String orderType){													
	    
	    List<Employee> sortedEmployees = service.getAllSortingOrder(property, orderType);
	    
	    return new ResponseEntity<>(sortedEmployees, HttpStatus.OK);
	}
	
	@GetMapping("findByDept/{dept}")
	public ResponseEntity<List<Employee>> findByDept(@PathVariable String dept){
		
		List<Employee> list=service.findByDepartment(dept);
		return new ResponseEntity<List<Employee>> (list , HttpStatus.OK);
		
	}
	@GetMapping("findByFirstName/{fname}")
	public ResponseEntity<List<Employee>> findByFirstName(@PathVariable String fname){
		
		List<Employee> list=service.findByFname(fname);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
	}
	@GetMapping("findByLastName/{lname}")
	public ResponseEntity<List<Employee>> findByLastName(@PathVariable String lname){
		
		
		List<Employee> list=service.findByLname(lname);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("FindBySalary/{salary}")
	public ResponseEntity<List<Employee>> findBySalary(@PathVariable Long salary){
		
		List<Employee> list=service.findBySalary(salary);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
		
	}
	@GetMapping("FindByAge/{age}")
	public ResponseEntity<List<Employee>> findByAgeLess(@PathVariable int age){
		List<Employee> ageLess=service.FindByAgeLessThan(age);
		return new ResponseEntity<List<Employee>>(ageLess , HttpStatus.OK);
		
	}
	
	@GetMapping("FindByFullName")
	public ResponseEntity<List<Employee>> findByFullName(@RequestParam String fname , @RequestParam String lname){
		
		List<Employee> list=service.FindByFnameAndLname(fname , lname);
		
		return new ResponseEntity<List<Employee>>(list , HttpStatus.OK);
		
	}
	@GetMapping("FindByOR")
	public ResponseEntity<List<Employee>> findByOR(@RequestParam String lname , @RequestParam String fname){
		
		List<Employee> list=service.FindByLnameOrFname(lname, fname );
		
	return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
	}
	@GetMapping("FindBySalaryBetween")
	public ResponseEntity<List<Employee>> findBySalaryBetween(@RequestParam long salary ,@RequestParam long salary1){
	 List<Employee > list=service.findSalaryBetween( salary, salary1);
	 
	 return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
		
		
	}
	
	@GetMapping("FindByAgeLessThanEqual/{age}")
	public ResponseEntity<List<Employee>> findByAgeLessThanEqual(@PathVariable int age){
		List<Employee> ageLessThanEqual=service.FindByAgeLessThanEqual(age);
		return new ResponseEntity<List<Employee>>(ageLessThanEqual , HttpStatus.OK);
		
	}
	@GetMapping("FindByFnameLike/{fname}")
	public ResponseEntity<List<Employee>> findByFirstNameLike(@PathVariable String fname){
		List<Employee> list=service.findByFnameLike(fname);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
	}
	@GetMapping("findByAgeOrderByLastnameDesc/{age}")
	public ResponseEntity<List<Employee>> findByAgeOrderLname(@PathVariable int age){
		List<Employee> list=service.findByAgeOrderByLastnameDesc(age);
		return new ResponseEntity<List<Employee>>(list ,HttpStatus.OK);
		
	}
	@GetMapping("findByLnameIsStartingWith")
	public ResponseEntity<List<Employee>> FindPrefix(@RequestParam String lname){
		
		List<Employee>list =service.findByLnameIsStartingWith(lname);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
		
	}
	
	@GetMapping("findByLnameIsEndingWith")
	public ResponseEntity<List<Employee>> FindSuffix(@RequestParam String fname){
		
		List<Employee>list =service.findByLnameIsEndingWith(fname);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
		
	}
	
	@GetMapping("findByFirstnameContaining/{fname}")
	public ResponseEntity<List<Employee>> findByFirstnameContaining(@PathVariable String fname){
		List<Employee> list=service.FindByFnameContaning(fname);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
		
		
	}
	@GetMapping("findByAgeIsNull")
	public ResponseEntity<List<Employee>> findByAgeIsNull(){
		
		List<Employee> list=service.FindByAgeIsNull();
		
		return new ResponseEntity<List<Employee>>(list ,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("findDistinctByDept/{dept}")
	public ResponseEntity<List<Employee>> findDistinctByDept(@PathVariable String dept){
		
		List<Employee> list=service.findDistinctByDept(dept);
		
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		
	}
	@GetMapping("findByDeptIn")
	public ResponseEntity<List<Employee>> findByDeptIn(@RequestParam List<String> dept){
		
		List<Employee> deptIn=service.FindByDeptIN(dept);
		
		return new ResponseEntity<List<Employee>>(deptIn , HttpStatus.OK);
		
		
	}
	
	@GetMapping("findByFnameIgnoreCase")
	public ResponseEntity<List<Employee>> findByFirstnameIgnoreCase(@RequestParam String fname){
		
		List<Employee> IgnoreCaseFname=service.findByFirstnameIgnoreCase(fname);
		
		return new ResponseEntity<List<Employee>>(IgnoreCaseFname, HttpStatus.OK);
	}

	@GetMapping("searchFilters")
	public ResponseEntity<List<Employee>> searchFilter(@RequestParam String filterType , @RequestParam String empCode ){
		List<Employee> list=service.searchFilters(filterType ,empCode);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
}

