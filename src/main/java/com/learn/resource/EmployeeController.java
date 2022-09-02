package com.learn.resource;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.EmployeeDto;
import com.learn.model.Employee;
import com.learn.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/list")
	public List<EmployeeDto> getAllEmployees() {
		return employeeRepository.findAll().stream().map(emp -> modelMapper.map(emp, EmployeeDto.class)).toList();
	}

	@PostMapping("/addEmp")
	public EmployeeDto save1(@RequestBody EmployeeDto employeeDto) {
		return modelMapper.map(employeeRepository.save(modelMapper.map(employeeDto, Employee.class)),
				EmployeeDto.class);
	}

	@DeleteMapping("/{id}")
	public EmployeeDto delete(@PathVariable("id") Integer id) throws Exception {
		Optional<Employee> emplOptional = employeeRepository.findById(id);
		if (emplOptional.isPresent()) {
			Employee employee = emplOptional.get();
			employeeRepository.delete(employee);
			return modelMapper.map(employee, EmployeeDto.class);
		}
		throw new Exception("No employee found with EmpNo : " + id);
	}

	@PutMapping("/{id}")
	public EmployeeDto update(@PathVariable("id") Integer id, @RequestBody EmployeeDto employeeDto) throws Exception {
		Optional<Employee> employeeObj = employeeRepository.findById(id);
		if (employeeObj.isPresent()) {
			Employee employee = employeeObj.get();
			if (!employeeDto.getFirstName().isBlank())
				employee.setFirstName(employeeDto.getFirstName());

			if (!employeeDto.getLastName().isBlank())
				employee.setLastName(employeeDto.getLastName());

			if (!employeeDto.getLastName().isBlank())
				employee.setLastName(employeeDto.getLastName());
			return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
		}
		throw new Exception("No employee found with EmpNo : " + id);

	}

}
