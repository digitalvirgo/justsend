package pl.justsend.api.client.model.dto;

import pl.justsend.api.client.model.enums.MsisdnValidationStatus;

/**

 * User: tmajewski
 * Date: 21.05.14
 * Time: 17:25
 */
public class GroupMemberDTO {

    private Long id;
    private String msisdn;
    private MsisdnValidationStatus validation;

    public GroupMemberDTO() {
    }

    public GroupMemberDTO(final String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(final String msisdn) {
        this.msisdn = msisdn;
    }

    public MsisdnValidationStatus getValidation() {
        return validation;
    }

    public void setValidation(final MsisdnValidationStatus validation) {
        this.validation = validation;
    }
}