package com.tejait.batch15.serviceimpl;


import com.tejait.batch15.model.Employee;
import com.tejait.batch15.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // this class is now elligible for mock testing becz it has dependency with repository class.
class EmployeeServiceImplTests {
    @Mock // Create repository mock object.
    EmployeeRepository repository;


    @InjectMocks  // Service bean injects into mock bean repository
    EmployeeServiceImpl service;

    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp() {
        // Given
        Employee givenEmp = Employee.builder()
                .id(1)
                .age(21)
                .fname("Manjith")
                .lname("v")
                .salary(1500000)
                .dept("java")
                .empCode("MAN457es")
                .fullname("Manjith v") // even if u comment this test will pass becz serviceimpl has logic for it to work FULLNAME..
                .build();
        BDDMockito.given(repository.save(givenEmp)).willReturn(givenEmp);
       Employee savedEmp = service.saveEmployee(givenEmp);

        assertThat(savedEmp).isNotNull();
        assertThat(savedEmp.getId()).isPositive();
        assertThat(savedEmp.getAge()).isGreaterThan(18);
        assertThat(savedEmp.getDept()).isEqualTo("java");
        assertThat(savedEmp.getFullname()).isEqualTo("Manjith v");
        assertThat(savedEmp).hasFieldOrPropertyWithValue("fullname" , "Manjith v");
        assertThat(savedEmp).hasFieldOrProperty("empCode");
        assertThat(savedEmp).hasNoNullFieldsOrProperties();

    }









}
