/**
 * 
 */
package com.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.EmployeeService;
import com.jpa.entities.Employee;

/**
 * @author MounikaAmrutha
 *
 */
@Stateless
public class EmployeeServiceImpl implements EmployeeService {

	/* (non-Javadoc)
	 * @see com.ejb.services.EmployeeService#addEmployee(com.jpa.entities.Employee)
	 */
	
	@PersistenceContext(name = "SampleApplication")
	private EntityManager em;
	
	@Override
	public void addEmployee(Employee emp) {
		em.persist(emp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees() {
		return em.createNativeQuery("SELECT * FROM employee", Employee.class).getResultList();
	}

}
