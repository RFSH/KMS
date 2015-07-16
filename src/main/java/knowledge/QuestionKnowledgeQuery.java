package knowledge;

import java.util.Date;

public class QuestionKnowledgeQuery{
    String query;
    Date from_date; //TODO Date oke?
    Date to_date;

    public QuestionKnowledgeQuery(String query, Date from_date, Date to_date) {
        this.query = query;
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
