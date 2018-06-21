package pl.justsend.api.client.model.dto;

import lombok.Data;
import pl.justsend.api.client.model.enums.FileReportStatuses;
import pl.justsend.api.client.model.enums.ReportTypes;

import java.io.Serializable;
import java.util.Date;

/**

 * User: posiadacz
 * Date: 09.11.17
 * Time: 11:45
 */
@Data
public class FileReportStatusDTO implements Serializable {

    private FileReportStatuses fileReportStatus;
    private ReportTypes reportTypes;
    private String fileName;
    private String fileId;
    private Date fromDate;
    private Date toDate;
    private Date createDate;
    private String additionalParameter;

}