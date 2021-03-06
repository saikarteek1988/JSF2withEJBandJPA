/**
 * 
 */
package com.gui.controllers;

import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ejb.services.EmployeeService;
import com.jpa.entities.Employee;

/**
 * @author MounikaAmrutha
 *
 */
@ViewScoped
@ManagedBean
public class EmployeeController {
	
	private static final String NAME_PATTERN = "[a-zA-Z]*";
	
	private static final String DOUBLE_PATTERN = "^[0-9]{1,13}(\\.[0-9]{0,2})?$";

	private Employee employee = new Employee();
	
	@EJB
	private EmployeeService service;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	 
	public String saveEmployee(Employee emp) {
		FacesContext context = FacesContext.getCurrentInstance();
		if(employee.getFirstName() != null && employee.getFirstName().equals("")){
			context.addMessage("First Name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "First Name is required.", ""));
		} else if(!Pattern.matches(NAME_PATTERN, employee.getFirstName())){
			context.addMessage("First Name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "First Name allows only Alphabets.", ""));
		} else if(employee.getFirstName().length() > 7){
			context.addMessage("First Name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "First Name allows only 7 characters.", ""));
		}

		if(employee.getLastName() != null && employee.getLastName().equals("")){
			context.addMessage("First Name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Last Name is required.", ""));
		} else if(!Pattern.matches(NAME_PATTERN, employee.getLastName())){
			context.addMessage("First Name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Last Name allows only characters.", ""));
		}

		String sal = String.valueOf(employee.getSalary());
		if(sal == null || sal.equals("0.0")){
			context.addMessage("Salary", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary is required.", ""));
		} else if(!Pattern.matches(DOUBLE_PATTERN, sal)){
			context.addMessage("Salary", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary accepts only XXX.XX format", ""));
			
		}
		
		if(context.getMessageList().size() > 0){
			return null;
		} else {
			service.addEmployee(emp);
			return "success";
		}
	}

}
