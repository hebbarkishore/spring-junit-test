package com.employee.api.repository;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.employee.api.model.Employee;
import org.junit.jupiter.api.MethodOrderer;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class EmployeeRepositoryTest {
	
	@Autowired
	EmployeeRepository repository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void insertEmployee() {
		System.out.println("insert method");
		Employee employee = new Employee("Ksh", "Master", "test@testing.com");
		repository.save(employee);
		
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void getEmployee( ) {
		System.out.println("select method");
		Employee employee = repository.findById(1L).get();
		System.out.println("employee::"+employee);
		Assertions.assertThat(employee.getFirstName()).isEqualTo("Ksh");
	}
	
	@Test
    @Order(3)
    public void getListOfEmployeesTest(){
        List<Employee> employees = repository.findAll();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		Employee employee = repository.findById(1L).get();
		employee.setEmail("test2@gmail.com");
		Employee employeeUpdated = repository.save(employee);
		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("test2@gmail.com");
	}
	
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        Employee employee = repository.findById(1L).get();
        repository.delete(employee);
        Employee employee1 = null;
        Optional<Employee> optionalEmployee = repository.findByEmail("test2@gmail.com");
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }
        Assertions.assertThat(employee1).isNull();
    }

}
