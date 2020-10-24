package com.soprasteria.service;

import java.util.List;
import java.util.Optional;

import com.soprasteria.model.Employee;

public interface IEmployeeService {
	
	public Integer SaveEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Optional<Employee> getOneEmployee(Integer id);
	public boolean isExist(Integer id);
	public void deleteEmployee(Integer id);

}
