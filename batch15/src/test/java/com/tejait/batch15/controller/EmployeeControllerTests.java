package com.tejait.batch15.controller;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EmployeeController.class)

public class EmployeeControllerTests {

    @MockitoBean
    EmployeeService service;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp() throws Exception {


        Employee givenEmp = Employee.builder()
                .id(1)
                .age(21)
                .fname("Manjith")
                .lname("v")
                .salary(1500000)
                .dept("java")
                .empCode("MAN457es")
                .fullname("Manjith v")
                .build();

        BDDMockito.given(service.saveEmployee(ArgumentMatchers.any(Employee.class)))
                .willReturn(givenEmp);
        ResultActions result =mockMvc.perform(post("http://localhost:8080/employee/saveEmp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(givenEmp)));
        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("Manjith"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.dept").value("java"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("v"));






    }
}