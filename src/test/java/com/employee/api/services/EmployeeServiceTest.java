package com.employee.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService service;
	
	@Mock
	EmployeeRepository repositry;
	
	@Test
	public void getEmpByIdTest() {
		when(repositry.findById(1L)).thenReturn(mockedEmployee());
		
		Employee empl = service.getEmpById(1L);
		
		assertEquals(empl.getFirstName(), "ksh");
	}
	
	public Optional<Employee> mockedEmployee() {
		return Optional.of(new Employee("ksh", "test", "test@test.com"));
	}
	
	
}
