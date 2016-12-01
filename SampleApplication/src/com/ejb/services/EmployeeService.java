/**
 * 
 */
package com.ejb.services;

import java.util.List;

import com.jpa.entities.Employee;

/**
 * @author MounikaAmrutha
 *
 */
public interface EmployeeService {
	 
	public void addEmployee(Employee emp);

	public List<Employee> getEmployees();
	
}
