package com.demo.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "employee-service")
public interface EmployeeServiceFeign {
	
	Logger log = LoggerFactory.getLogger(EmployeeServiceFeign.class);
	
	String service = "employee-service";
	
	@CircuitBreaker(name = service, fallbackMethod = "employeeCheckFallback")
	@GetMapping("/api/v1/employee/check")
	boolean employeeCheck(@RequestParam("empId") String empId);
	
	@CircuitBreaker(name = service, fallbackMethod = "getEmployeeIdByEmailFallback")
	@GetMapping("/api/v1/employee/detail")
	String getEmployeeIdByEmail(@RequestParam("email") String email);
	
	default boolean employeeCheckFallback(Exception exception) {
		log.error("in employeeCheck Fallback due to {}", exception.getLocalizedMessage());
		return false;
	}
	
	default String getEmployeeIdByEmailFallback(Exception exception) {
		log.error("in employeeIdByEmail Fallback due to {}", exception.getLocalizedMessage());
		return null;
	}

}
