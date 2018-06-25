package pl.justsend.api.client.model;

/**

 * User: lciesluk
 * Date: 08.10.14
 * Time: 15:48

 */
public class UserPurseResponse extends BaseModel {

    private Long balanceInPoints;
    private Long freePoints;

    public Long getBalanceInPoints() {
        return balanceInPoints;
    }

    public void setBalanceInPoints(Long balanceInPoints) {
        this.balanceInPoints = balanceInPoints;
    }

    public Long getFreePoints() {
        return freePoints;
    }

    public void setFreePoints(Long freePoints) {
        this.freePoints = freePoints;
    }
}
