package com.soprasteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.model.Employee;
import com.soprasteria.service.IEmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployees()
	{
		ResponseEntity<?> resp=null;
		try {
			List<Employee> list=service.getAllEmployee();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No data found",HttpStatus.OK);			
		}catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fech data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			}
		return resp;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneEmp(@PathVariable("id") Integer id){
		ResponseEntity<?> resp=null;
		try {
			Optional<Employee> opt=service.getOneEmployee(id);
			if(opt.isPresent())
				resp=new ResponseEntity<Employee>(opt.get(),HttpStatus.OK); 
			else
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to fetch data",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee)
	{
		ResponseEntity<String> resp=null;
		try {
			Boolean exist=service.isExist(employee.getId());
			if(exist) {
				service.SaveEmployee(employee);
				resp=new ResponseEntity<String>(employee.getId()+"Updated",HttpStatus.OK);
			}else {
			resp=new ResponseEntity<String>(employee.getId()+"Not Exist",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Update..",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id") Integer id){
		ResponseEntity<String> resp=null;
		try {
			Boolean exist=service.isExist(id);
			if(exist) {
				service.deleteEmployee(id);
				resp=new ResponseEntity<String>("Deleted..",HttpStatus.OK);
			}
			else
			{
				resp=new ResponseEntity<String>("Not Exist..",HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			resp=new ResponseEntity<String>("Fail Unable to delete..",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> SaveEmp(@RequestBody Employee employee){
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.SaveEmployee(employee);
			resp=new ResponseEntity<String>("Saved successfully..",HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to save",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
}
