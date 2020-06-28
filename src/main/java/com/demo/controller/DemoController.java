package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.DemoService;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
	
	@Autowired
	DemoService demoService;
	
	@GetMapping("/check")
	public ResponseEntity<?> checkCircuitBreakerCalls(@RequestParam(name="id",required = false) String id) {
		if(!StringUtils.isEmpty(id)) {
			String data = demoService.callEmployeeService(id);
			return new ResponseEntity<String> (data, new HttpHeaders(), HttpStatus.OK);
		}	
		return new ResponseEntity<>("Mandatory Parameters Missing", HttpStatus.BAD_REQUEST);
	}

}
