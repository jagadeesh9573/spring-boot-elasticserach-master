package com.springbootjasperreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjasperreport.reportService.EmployeeReportService;

@RestController
@RequestMapping("/emp")
public class EmployeeReportController {

	@Autowired
	private EmployeeReportService employeeReportService;

	@GetMapping("/report")
	public String generateReport() {
		System.out.print(" my first change1 '''24");
		System.out.print(" my first change1 '''24");
		return employeeReportService.generateReport();
	}
}