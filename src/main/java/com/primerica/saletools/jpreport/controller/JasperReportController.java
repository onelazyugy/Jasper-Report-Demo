package com.primerica.saletools.jpreport.controller;

import com.primerica.saletools.jpreport.exception.ApplicationException;
import com.primerica.saletools.jpreport.service.JasperReportService;
import com.primerica.saletools.jpreport.service.ReportService;
import com.primerica.saletools.jpreport.util.JasperReportUtil;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JasperReportController {
    // tell where to put the generated pdf file on your local machine, can be change is src/main/resource/application.properties file
    @Value("${pdfoutput}")
    private String pdfOutput;
    private ReportService jasperReportService;

    @Autowired
    public JasperReportController(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    @RequestMapping(value = "/jasper", method = RequestMethod.GET)
    public String demo2() {
        return "Hello Jasper Report";
    }

    @CrossOrigin(origins = "*")// add your UI domain
    @RequestMapping(value = "/demo/{username}", method = RequestMethod.GET)
    public String demo(@PathVariable String username) throws ApplicationException {
        try {
            JasperReport jasperReport = JasperReportUtil.loadJASPERFile("Simple_Blue.jasper");
            Map<String,Object> params = new HashMap<>();
            params.put("username", username);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, this.pdfOutput.concat("Demo.pdf"));
        } catch (Exception e) {
            throw new ApplicationException("999", e.getMessage());
        }
        return "successfully generated the pdf report";
    }

    @CrossOrigin(origins = "*")// add your UI domain
    @RequestMapping(value = "/report/{username}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> report(@PathVariable String username) throws ApplicationException {
        String jasperTemplate = "Simple_Blue.jasper";
        Map<String,Object> params = new HashMap<>();
        params.put("username", username);

        byte[] reportPdf = this.jasperReportService.generatePDF(jasperTemplate, params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        ResponseEntity<byte[]> response = new ResponseEntity<>(reportPdf, headers, HttpStatus.OK);
        return response;
    }
}
