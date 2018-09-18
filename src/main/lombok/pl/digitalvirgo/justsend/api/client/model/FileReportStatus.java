package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.FileReportStatuses;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.ReportTypes;

import java.util.Date;

@Data
public class FileReportStatus {

    private FileReportStatuses fileReportStatus;
    private ReportTypes reportTypes;
    private String fileName;
    private String fileId;
    private Date fromDate;
    private Date toDate;
    private Date createDate;
    private String additionalParameter;

}