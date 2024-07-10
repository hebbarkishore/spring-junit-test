package com.employee.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.model.Employee;
import com.employee.api.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping("/save")
	public Employee insertEmployee(@RequestBody Employee employee)  {
		return service.saveEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return service.getEmpById(id);
	}

}
