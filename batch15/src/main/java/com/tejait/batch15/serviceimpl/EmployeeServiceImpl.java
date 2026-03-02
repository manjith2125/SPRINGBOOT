package com.tejait.batch15.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejait.batch15.constants.SearchFilter;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.repository.EmployeeRepository;
import com.tejait.batch15.service.EmployeeService;


@Service
public class EmployeeServiceImpl  implements EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	
	
	@Override
	public Employee saveEmployee(Employee emp) {
		
		
		
		String fname=emp.getFname();			// Sai
		String lname=emp.getLname();			// k
		emp.setFullname(fname.concat(" "+lname));  // Sai L
		Employee saveEmp=repository.save(emp);
		
		
		
		return saveEmp;
	}



	
	@Override
	public Optional<Employee> getById(Integer id) {
		
		return repository.findById(id);
	}



	@Override
	public List<Employee> getAllEmps() {
	
		return repository.findAll();
	}



	@Override
	public void deleteEmployee(Integer id) {
		
		repository.deleteById(id);
	}




	@Override
	public boolean existsById(Integer id) {
		
		return repository.existsById(id);
		
	}




	@Override
	public Page<Employee> getPagination(int pageNum, int pageSize) {
		Pageable pageable=PageRequest.of(pageNum, pageSize);
		
		
		return repository.findAll(pageable);
			
	
	}




	@Override
	public List<Employee> getAllSortingOrder( String property,String orderType) {
		
		if(orderType.equalsIgnoreCase("desc")) {
		return 	repository.findAll(Sort.by(Direction.DESC, property));
		}
		
		return 	repository.findAll(Sort.by(Direction.ASC, property));
		
	}




	@Override
	public List<Employee> findByDepartment(String dept) {
		
		return repository.findByDept(dept);
	}




	@Override
	public List<Employee> findByFname(String fname) {
		
		return repository.findByFname(fname);
		
	}




	@Override
	public List<Employee> findByLname(String lname) {
		
		return repository.findByLname(lname);
	}




	@Override
	public List<Employee> findBySalary(Long salary) {
		
		return repository.findBySalary(salary);
	}




	@Override
	public List<Employee> FindByAgeLessThan(int age) {
		
		return repository.findByAgeLessThan(age);
		
	}




	@Override
	public List<Employee> FindByFnameAndLname(String fname, String lname) {

		return repository.findByFnameAndLname(fname, lname);
		
	}




	@Override
	public List<Employee> FindByLnameOrFname(String lname, String fname) {
		
		return repository.findByLnameOrFname(lname, fname);
	}




	@Override
	public List<Employee> findSalaryBetween(long salary, long salary1) {
		
		return repository.findBySalaryBetween(salary, salary1);
		
	}




	@Override
	public List<Employee> FindByAgeLessThanEqual(int age) {
		
		return repository.findByAgeLessThanEqual(age);
	}




	@Override
	public List<Employee> findByFnameLike(String fname) {
	
		return repository.findByFnameLike("%"+fname+"%");
		
	}




	@Override
	public List<Employee> findByAgeOrderByLastnameDesc(int age) {
		
		return repository.findByAgeOrderByLnameDesc(age);
		
	}




	@Override
	public List<Employee> findByLnameIsStartingWith(String lname) {
		
		return repository.findByLnameIsStartingWith(lname);
		
	}




	@Override
	public List<Employee> findByLnameIsEndingWith(String fname) {
		
		return repository.findByFnameIsEndingWith(fname);
		
	}




	@Override
	public List<Employee> FindByFnameContaning(String fname) {
		
		return repository.findByFnameContaining(fname);
		
	}




	@Override
	public List<Employee> FindByAgeIsNull() {
		
		return repository.findByAgeIsNull();
	}




	@Override
	public List<Employee> findDistinctByDept(String dept) {
		
		return repository.findDistinctByDept(dept);
		
	}




	@Override
	public List<Employee> FindByDeptIN(List<String> dept) {
		
		return repository.findByDeptIn(dept);
		
	}




	@Override
	public List<Employee> findByFirstnameIgnoreCase(String fname) {
		
		return repository.findByFnameIgnoreCase(fname);
	}


// SEARCH FILTERS OPERATIONS .. 

	@Override
	public List<Employee> searchFilters(String filterType, String empCode) {
	//List<Employee> list=ArrayList<>
		List<Employee> list=null;
		
		//Empty array to re-asign the value to list to return the required list...
		switch(filterType) {
		case SearchFilter.START_WITH:
		     list=repository.findByEmpCodeStartingWith(empCode);
			break;
		case SearchFilter.END_WITH:
			list=repository.findByEmpCodeEndingWith(empCode);
			break;
		case SearchFilter.CONTAINS:
			list=repository.findByEmpCodeContaining(empCode);
			break;
			
		case SearchFilter.NOT_CONTAINS:
			list=repository.findByEmpCodeNotContaining(empCode);
			break;
			
		case SearchFilter.EQUALS:
			list=repository.findByEmpCode(empCode);
			break;
		case SearchFilter.NOT_EQUALS:
			list=repository.findByEmpCodeNot(empCode);
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value ::  "+filterType);
			
		
		}
		
		
		return list;
	}



	}



