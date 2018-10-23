package com.primerica.saletools.jpreport.controller;

import com.primerica.saletools.jpreport.exception.ApplicationException;
import com.primerica.saletools.jpreport.service.JasperReportService;
import com.primerica.saletools.jpreport.service.ReportService;
import com.primerica.saletools.jpreport.util.JasperReportUtil;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ReportService jasperReportService;

    @Autowired
    public JasperReportController(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/demo/{username}", method = RequestMethod.GET)
    public String demo(@PathVariable String username) throws ApplicationException {
        try {
            JasperReport jasperReport = JasperReportUtil.loadJASPERFile("Simple_Blue.jasper");
            Map<String,Object> params = new HashMap<>();
            params.put("username", username);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            String path = "/Users/ap789/Desktop/Jasper/demo/output/Demo.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint,path);
        } catch (Exception e) {
            throw new ApplicationException("999", e.getMessage());
        }
        return "successfully generated the pdf report";
    }

    @CrossOrigin(origins = "*")
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
