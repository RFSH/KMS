package knowledge;

import java.util.Date;

public class WikiKnowledgeQuery {
    String query;
    boolean isDeprecated;
    boolean isApproved;
    Date from_date;
    Date to_date;

    public WikiKnowledgeQuery(String query, boolean isDeprecated, boolean isApproved, Date from_date, Date to_date) {
        this.query = query;
        this.isDeprecated = isDeprecated;
        this.isApproved = isApproved;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    /* Getter and Setters */

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(boolean isDeprecated) {
        this.isDeprecated = isDeprecated;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }
}
