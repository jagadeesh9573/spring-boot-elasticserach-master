package com.springbootjasperreport.reportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.springbootjasperreport.entity.Employee;
import com.springbootjasperreport.repository.ReportRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

@Service
public class EmployeeReportService {

	@Autowired
	private ReportRepository reportRepository;

	public String generateReport() {
		try {

			List<Employee> employees = reportRepository.findAll();

			String reportPath = ResourceUtils.getFile("classpath:reports").getPath();
			System.out.println("reportPath>>>>>>>>>>>>>>>>>>>" + reportPath);
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\employee-rpt-database.jrxml");


			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employees);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

	         parameters.put("createdBy", "Jagadeesh");

	         // Fill the report
	         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
	               jrBeanCollectionDataSource);

	         // Export the report to a PDF file
	         //JasperExportManager.exportReportToHtmlFile(jasperPrint, reportPath + "\\Emp-Rpt.csv");
	         JRCsvExporter jRCsvExporter = new JRCsvExporter();
	         jRCsvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	         jRCsvExporter.setExporterOutput(new SimpleWriterExporterOutput(reportPath + "\\Emp-Rpt-Database.csv"));
	         jRCsvExporter.exportReport();

			System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error--> check the console log";
		}
	}
}