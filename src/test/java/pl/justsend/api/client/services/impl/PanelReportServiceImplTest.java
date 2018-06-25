package pl.justsend.api.client.services.impl;

import org.testng.annotations.BeforeMethod;

import static pl.justsend.api.client.services.impl.BaseTest.APP_KEY;

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