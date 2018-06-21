package pl.justsend.api.client.services;

import pl.justsend.api.client.model.ReportResponse;
import pl.justsend.api.client.model.dto.ResponseMessageDTO;
import pl.justsend.api.client.model.dto.SingleBulkReportDTO;
import pl.justsend.api.client.services.exception.JustsendApiClientException;

import java.util.List;

public interface PanelReportService {


    List<ReportResponse> retrieveBulksDuringSend(
            Integer rowFrom,
            Integer rowSize) throws JustsendApiClientException;

    List<ReportResponse> retrieveBulksDuringSendPagin(
            Integer rowFrom,
            Integer rowSize,
            String sort,
            Integer order) throws JustsendApiClientException;

    Long retrieveBulksDuringSendCount() throws JustsendApiClientException;

    Long retrieveCountResponseMessages(
            Integer prefixId,
            String from,
            String to,
            Long id,
            String prefix) throws JustsendApiClientException;

    List<ResponseMessageDTO> retrieveResponseMessages(
            Integer prefixId,
            String from,
            String to,
            Integer startRow,
            Integer countRow) throws JustsendApiClientException;

    List<ResponseMessageDTO> retrieveResponseMessagesPagin(Integer pageNumber, Integer countRow, Integer prefixId,
                                                           String from, String to, String sort, Integer order, Long id, String prefix) throws JustsendApiClientException;

    List<ReportResponse> retrieveBulkListByDatePagin(
            String from,
            String to,
            Integer pageNumber,
            Integer rowSize,
            String sort,
            Integer order,
            Long id,
            String name,
            String sender) throws JustsendApiClientException;

    List<SingleBulkReportDTO> retrieveSingleBulksByStartDate(
            String from,
            String to,
            Integer rowFrom,
            Integer rowSize,
            String sort,
            Integer order,
            Long id,
            String sender) throws JustsendApiClientException;
}
