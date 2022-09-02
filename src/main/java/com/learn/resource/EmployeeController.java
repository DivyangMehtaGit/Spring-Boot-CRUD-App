package com.learn.resource;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dto.EmployeeDto;
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

}
