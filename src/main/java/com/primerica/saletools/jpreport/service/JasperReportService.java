package com.primerica.saletools.jpreport.service;

import com.primerica.saletools.jpreport.exception.ApplicationException;
import com.primerica.saletools.jpreport.util.JasperReportUtil;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JasperReportService implements ReportService{

    @Override
    public byte[] generatePDF(String jasperTemplate, Map<String, Object> params) throws ApplicationException {
        byte[] pdf;
        try {
            JasperReport jasperReport = JasperReportUtil.loadJASPERFile(jasperTemplate);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ApplicationException("123", e.getMessage());
        }
        return pdf;
    }
}
