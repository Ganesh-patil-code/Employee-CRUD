package com.soprasteria.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soprasteria.service.IEmployeeService;

public class Emp {
	
	@Autowired
	private IEmployeeService service;
	
	public List<Employee> listEmp(){
	return service.getAllEmployee();
	}
}
