package pl.justsend.api.client.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**

 * User: tmajewski
 * Date: 21.05.14
 * Time: 17:15
 */
public class GroupDTO extends AVBaseDTO<Long> {



    private String name;
    private List<GroupMemberDTO> members = new ArrayList<>();
    private Integer userId;
    private Date createDate;
    private Date dateFrom;
    private Date dateTo;
    private String smsOutContent;
    private Boolean fileImporting;
    private String shortName;


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<GroupMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(final List<GroupMemberDTO> members) {
        this.members = members;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getSmsOutContent() {
        return smsOutContent;
    }

    public void setSmsOutContent(String smsOutContent) {
        this.smsOutContent = smsOutContent;
    }

    public Boolean getFileImporting() {
        return fileImporting;
    }

    public void setFileImporting(Boolean fileImporting) {
        this.fileImporting = fileImporting;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", smsOutContent='" + smsOutContent + '\'' +
                ", fileImporting='" + fileImporting + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}