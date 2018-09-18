package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.FileReportStatuses;
import pl.digitalvirgo.justsend.api.client.services.impl.enums.ReportTypes;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 09.11.17
 * Time: 11:45
 */
@Data
public class FileReportStatusDTO  {

    private static final long serialVersionUID = 1L;

    private FileReportStatuses fileReportStatus;
    private ReportTypes reportTypes;
    private String fileName;
    private String fileId;
    private Date fromDate;
    private Date toDate;
    private Date createDate;
    private String additionalParameter;

    public FileReportStatusDTO() {
    }

    public FileReportStatusDTO(FileReportStatuses fileReportStatus, String fileId) {
        this.fileReportStatus = fileReportStatus;
        this.fileId = fileId;
    }

    public FileReportStatusDTO(FileReportStatuses fileReportStatus) {
        this.fileReportStatus = fileReportStatus;
    }
}