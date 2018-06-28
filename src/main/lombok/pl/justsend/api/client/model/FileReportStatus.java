package pl.justsend.api.client.model;

import lombok.Data;
import pl.justsend.api.client.model.enums.FileReportStatuses;
import pl.justsend.api.client.model.enums.ReportTypes;

import java.io.Serializable;
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