package com.primerica.saletools.jpreport.util;

import com.primerica.saletools.jpreport.exception.ApplicationException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class JasperReportUtil {

    public static JasperReport loadJRXMLFile(String jrxmlFile) throws ApplicationException {
        JasperReport jasperReport = null;
        try {
            InputStream stream = getInputStreamFromFile(jrxmlFile);
            if(stream != null) {
                jasperReport = JasperCompileManager.compileReport(stream);
            }
        } catch (Exception e) {
            throw new ApplicationException("123", e.getMessage());
        }
        return jasperReport;
    }

    public static JasperReport loadJASPERFile(String jasperFile) throws ApplicationException {
        JasperReport jasperReport = null;
        try {
            InputStream stream = getInputStreamFromFile(jasperFile);
            if(stream != null) {
                jasperReport = (JasperReport) JRLoader.loadObject(stream);
            }
        } catch (Exception e) {
            throw new ApplicationException("456", e.getMessage());
        }
        return jasperReport;
    }

    private static InputStream getInputStreamFromFile(String fileName) throws ApplicationException{
        InputStream inputStream;
        try {
            inputStream = new ClassPathResource(fileName).getInputStream();
        } catch (Exception e) {
            throw new ApplicationException("123", e.getMessage());
        }
        return inputStream;
    }
}
