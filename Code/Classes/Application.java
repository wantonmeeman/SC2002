package Classes;

import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable {
// 私有属性，对应类图中的 attributes

    private String applicationID;
    private String status;
    private Date applicationDate;
    private String typeFlat;

    public Application(String applicationID, String status, Date applicationDate, String typeFlat) {
        this.applicationID = applicationID;
        this.status = status;
        this.applicationDate = applicationDate;
        this.typeFlat = typeFlat;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getTypeFlat() {
        return typeFlat;
    }

    public void setTypeFlat(String typeFlat) {
        this.typeFlat = typeFlat;
    }

    @Override
    public String toString() {
        return "Application{"
                + "applicationID='" + applicationID + '\''
                + ", status='" + status + '\''
                + ", applicationDate=" + applicationDate
                + ", typeFlat='" + typeFlat + '\''
                + '}';
    }
}
