package com.primerica.saletools.jpreport.service;

import com.primerica.saletools.jpreport.exception.ApplicationException;

import java.util.Map;

public interface ReportService {
    byte[] generatePDF(String jasperTemplate, Map<String, Object> params) throws ApplicationException;
}
