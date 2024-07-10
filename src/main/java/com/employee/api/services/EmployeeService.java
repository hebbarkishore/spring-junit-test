package com.employee.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.api.model.Employee;
import com.employee.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	
	public Employee getEmpById(Long empId) {
		
		try {
			Employee e = repository.findById(empId).get();
			return e;
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	

}
