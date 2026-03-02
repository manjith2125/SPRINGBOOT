package com.tejait.batch15.it;

import com.tejait.batch15.model.Employee;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
 class EmployeeControllerTestsITTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp() throws Exception {


        Employee givenEmp = Employee.builder()
              //  .id(1)
                .age(21)
                .fname("Manjith")
                .lname("v")
                .salary(1500000)
                .dept("java")
                .empCode("MAN457es")
                .fullname("Manjith v")
                .build();


        ResultActions result = mockMvc.perform(post("/employee/saveEmp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(givenEmp)));
        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("Manjith"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.dept").value("java"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.lname").value("v"));


    }
}
