package knowledge;

import java.util.Date;

public class WikiKnowledgeQuery {
    private String query;
    private Boolean deprecated;
    private Boolean approved;
    private Date fromDate;
    private Date toDate;

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setIsApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean isDeprecated() {
        return deprecated;
    }

    public void setIsDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
