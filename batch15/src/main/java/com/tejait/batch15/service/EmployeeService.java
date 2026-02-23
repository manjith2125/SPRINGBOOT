package com.tejait.batch15.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.tejait.batch15.model.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee emp);

	public void deleteEmployee(Integer id);

	public Optional<Employee> getById(Integer id);

	public List<Employee> getAllEmps();

	public boolean existsById(Integer id);

	public Page<Employee> getPagination(int pageNum, int pageSize);



	public List<Employee> getAllSortingOrder(String sortBy, String direction);

	public List<Employee> findByDepartment(String dept);

	public List<Employee> findByFname(String fname);

	

	public List<Employee> findByLname(String lname);

	public List<Employee> findBySalary(Long salary);

	public List<Employee> FindByAgeLessThan(int age);

	public List<Employee> FindByFnameAndLname(String fname, String lname);

	public List<Employee> FindByLnameOrFname(String lname, String fname);

	public List<Employee> findSalaryBetween(long salary, long salary1);

	public List<Employee> FindByAgeLessThanEqual(int age);

	public List<Employee> findByFnameLike(String fname);

	public List<Employee> findByAgeOrderByLastnameDesc(int age);

	public List<Employee> findByLnameIsStartingWith(String lname);

	public List<Employee> findByLnameIsEndingWith(String fname);

	public List<Employee> FindByFnameContaning(String fname);

	public List<Employee> FindByAgeIsNull();

	public List<Employee> findDistinctByDept(String dept);

	public List<Employee> FindByDeptIN(List<String> dept);

	public List<Employee> findByFirstnameIgnoreCase(String fname);

	public List<Employee> searchFilters(String filterType, String empCode);



	

	







	



}
