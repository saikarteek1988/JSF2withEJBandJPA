/**
 * 
 */
package com.gui.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ejb.services.EmployeeService;
import com.jpa.entities.Employee;

/**
 * @author MounikaAmrutha
 *
 */
@ViewScoped
@ManagedBean
public class EmployeeDetailsController {

	@EJB
	private EmployeeService service;
	 
	public List<Employee> getEmployees() {
		List<Employee> employees = service.getEmployees();
		return employees;
	}
}
