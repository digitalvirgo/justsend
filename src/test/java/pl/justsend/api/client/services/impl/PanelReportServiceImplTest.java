package pl.justsend.api.client.services.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

import static org.testng.Assert.*;
import static pl.justsend.api.client.test.services.BaseTest.APP_KEY;

public class PanelReportServiceImplTest {

    private PanelReportServiceImpl panelReportService;

    @BeforeMethod
    public void setUp() {
        panelReportService = new PanelReportServiceImpl(APP_KEY);
    }

//    @Test
//    public void testRetrieveBulksDuringSend() throws JustsendApiClientException {
//        List<ReportResponse> reportResponses = panelReportService.retrieveBulksDuringSend(1, 10);
//
//    }
//
//    @Test
//    public void testRetrieveBulksDuringSendPagin() {
//        panelReportService.retrieveBulkListByDatePagin()
//    }
//
//    @Test
//    public void testRetrieveBulksDuringSendCount() throws JustsendApiClientException {
//        Long retrieveBulksDuringSendCount = panelReportService.retrieveBulksDuringSendCount();
//    }
//
//    @Test
//    public void testRetrieveCountResponseMessages() {
//        panelReportService.retrieveCountResponseMessages()
//    }
//
//    @Test
//    public void testRetrieveResponseMessages() {
//        panelReportService.retrieveResponseMessages()
//    }
//
//    @Test
//    public void testRetrieveResponseMessagesPagin() {
//    }
//
//    @Test
//    public void testRetrieveBulkListByDatePagin() {
//    }
//
//    @Test
//    public void testRetrieveSingleBulksByStartDate() {
//    }
}