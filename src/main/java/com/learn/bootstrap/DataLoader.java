package com.learn.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.learn.model.Employee;
import com.learn.repository.EmployeeRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private EmployeeRepository employeeRepository;

	public DataLoader(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Employee emp = new Employee();
		emp.setDepartment("PES");
		emp.setFirstName("D");
		emp.setLastName("Mehta");
		employeeRepository.save(emp);
	}

}
