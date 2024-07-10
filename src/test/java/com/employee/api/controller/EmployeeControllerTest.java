package com.employee.api.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.employee.api.model.Employee;
import com.employee.api.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService service;
	
	private Employee employee;
	
	@BeforeEach
	void setUp() {
		employee = new Employee("ksh", "test", "test@testing.com");
		employee.setId(1L);
	}
	
	@Test
	void getEmployeeTest() throws Exception {
		when(service.getEmpById(1L)).thenReturn(employee);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/employee/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("""
						{
						"id": 1,
						"firstName": "ksh",
						"lastName": "test",
						"email": "test@testing.com"
						}
						"""));
		}
	
	@Test
	void insertEmployeeTest() throws Exception{
		Employee input = new Employee("ksh", "test", "test@testing.com");
		when(service.saveEmployee(input)).thenReturn(employee);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
		.contentType(MediaType.APPLICATION_JSON)
		.content("""
				{
					"firstName": "ksh",
				    "lastName": "Test",
				    "email": "test@testing.com"
				
				}
				"""))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
