package com.tejait.batch15.repository;

import com.tejait.batch15.model.Employee;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@EntityScan(basePackageClasses = Employee.class)
public class EmployeeRepositoryTests {


    @Autowired
    EmployeeRepository repository;


    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp(){



      Employee givenEmp=Employee.builder()
               //.id(1)
               .age(21)
               .fname("Manjith")
               .lname("v")
               .salary(1500000)
               .dept("java")
               .empCode("MAN457es")
               .fullname("Manjith v")
               .build();


     Employee savedEmp= repository.save(givenEmp);
       assertThat(savedEmp).isNotNull();
       assertThat(savedEmp.getId()).isPositive();
       assertThat(savedEmp.getAge()).isGreaterThan(18);
       assertThat(savedEmp.getDept()).isEqualTo("java");
       assertThat(savedEmp.getFullname()).isEqualTo("Manjith v");
       assertThat(savedEmp).hasFieldOrPropertyWithValue("fullname" , "Manjith v");
       assertThat(savedEmp).hasFieldOrProperty("empCode");
       assertThat(savedEmp).hasNoNullFieldsOrProperties();


   }

@Test
    void givenId_whenFindById_getIdObject(){


    Employee givenEmp=Employee.builder()
            //.id(1)
            .age(21)
            .fname("Manjith")
            .lname("v")
            .salary(1500000)
            .dept("java")
            .empCode("MAN457es")
            .fullname("Manjith v")
            .build();


    Employee savedEmp= repository.save(givenEmp);

    Optional<Employee> employee =repository.findById(savedEmp.getId());

    assertThat(employee.get()).isNotNull();
    assertThat(employee.get().getEmpCode()).isEqualTo("MAN457es");



}


    @Test
    void givenEmployees_whenFindAll_thenReturnEmployeesList() {

        // GIVEN (prepare data)

        Employee emp1 = Employee.builder()
                .age(21)
                .fname("Manjith")
                .lname("V")
                .salary(1500000)
                .dept("Java")
                .empCode("MAN457es")
                .fullname("Manjith V")
                .build();

        Employee emp2 = Employee.builder()
                .age(25)
                .fname("Rahul")
                .lname("K")
                .salary(1200000)
                .dept("Spring")
                .empCode("RAH123xy")
                .fullname("Rahul K")
                .build();

        // save data into DB
        repository.save(emp1);
        repository.save(emp2);

        // WHEN (calling method)
        List<Employee> employeeList = repository.findAll();

        // THEN (assertions)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
        assertThat(employeeList);
    }




}
