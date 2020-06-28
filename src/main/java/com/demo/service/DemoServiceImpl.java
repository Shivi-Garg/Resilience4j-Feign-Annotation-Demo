package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.feign.EmployeeServiceFeign;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	EmployeeServiceFeign employeeServiceFeign;

	@Override
	public String callEmployeeService(String employeeId) {
		boolean isEmployeeExists = employeeServiceFeign.employeeCheck(employeeId);
		if(isEmployeeExists) {
			//logic goes here..
			return "valid employeeId";
		}
		return "invalid Id";
	}

}
