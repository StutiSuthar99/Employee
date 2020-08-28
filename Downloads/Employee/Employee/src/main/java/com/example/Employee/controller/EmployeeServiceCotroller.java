package com.example.Employee.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.model.Employee;
import com.example.Exceptions.EmployeeNotFoundException;

@RestController
@ControllerAdvice
public class EmployeeServiceCotroller {
	
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<String> exception(EmployeeNotFoundException exception){
		return new ResponseEntity<String>("Employee Not Found", HttpStatus.NOT_FOUND);
	}
	
	private static Map<Integer, Employee> employeeRepo = new HashMap<>();
	
	static {
		employeeRepo.put(1, new Employee(1, "XYZ", 25, "CEO", 100000));
		employeeRepo.put(2, new Employee(2, "ABC", 25, "CTO", 100000));
		employeeRepo.put(3, new Employee(3, "PQR", 25, "CFO", 100000));
			      
	}
	
	
	@RequestMapping(value = "/employees")
	public ResponseEntity<Collection<Employee>> getProduct() {
		return new ResponseEntity<>(employeeRepo.values(), HttpStatus.OK);
	}
	   
   @RequestMapping(value = "/employee", method = RequestMethod.POST)
   public ResponseEntity<String> createProduct(@RequestBody Employee product) {
	   employeeRepo.put(product.getId(), product);
	   return new ResponseEntity<>("Employee is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
   public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @RequestBody Employee employee) { 
	   if(!employeeRepo.containsKey(Integer.parseInt(id))) throw new EmployeeNotFoundException();
	   employeeRepo.remove(Integer.parseInt(id));
	   employee.setId(Integer.parseInt(id));
	   employeeRepo.put(Integer.parseInt(id), employee);
	   return new ResponseEntity<>("Employee is updated successsfully", HttpStatus.OK);
   }   
   
   @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<String> delete(@PathVariable("id") String id) { 
	   employeeRepo.remove(Integer.parseInt(id));
	   return new ResponseEntity<>("Employee is deleted successsfully", HttpStatus.OK);
   }

}
