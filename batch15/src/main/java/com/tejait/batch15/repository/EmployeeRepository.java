package com.tejait.batch15.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejait.batch15.model.Employee;


@Repository

public interface EmployeeRepository extends JpaRepository<Employee,	 Integer> {
	
public List<Employee> findByDept(String dept);
public List<Employee> findByFname(String fname);
public List<Employee> findByLname(String lname);
public List<Employee> findBySalary(long salary);

public List<Employee> findByAgeLessThan(int age);
  

public List<Employee> findByFnameAndLname(String fname , String lname);

public List<Employee> findByLnameOrFname(String lname, String fname);

public List<Employee> findBySalaryBetween(long salary, long salary1);

public List<Employee> findByAgeLessThanEqual(int age);

public List<Employee> findByFnameLike(String fname);

public List<Employee> findByAgeOrderByLnameDesc(int age );
public List<Employee> findByLnameIsStartingWith(String prefix);

public List<Employee> findByFnameIsEndingWith(String suffix);

public List<Employee> findByFnameContaining(String fname);


public List<Employee> findByAgeIsNull(); 
public List<Employee> findDistinctByDept(String dept);

public List<Employee> findByDeptIn(Collection<String> depts);

public List<Employee> findByFnameIgnoreCase(String  fname);



// -------------------------------------Search Filter Start----------------------------------------------------------

public List<Employee> findByEmpCodeStartingWith(String empcode);

public  List<Employee> findByEmpCodeEndingWith(String empcode);

public List<Employee> findByEmpCodeContaining(String empcode);

public List<Employee> findByEmpCodeNotContaining(String empcode);

public List<Employee> findByEmpCode(String empcode);

public List<Employee> findByEmpCodeNot(String empcode);



//-------------------------------------Search Filter Ends-------------------------------------------------------------











}
