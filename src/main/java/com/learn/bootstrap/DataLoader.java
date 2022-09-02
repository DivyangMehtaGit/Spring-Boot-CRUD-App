package com.learn.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.learn.model.Employee;
import com.learn.repository.EmployeeRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Employee emp = new Employee();
		emp.setDepartment("PES");
		emp.setFirstName("D");
		emp.setLastName("Mehta");
		employeeRepository.save(emp);
	}

}
