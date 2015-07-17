package knowledge;

import java.util.Date;

public class QuestionKnowledgeQuery{
    String query;
    Date fromDate;
    Date toDate;

    public QuestionKnowledgeQuery() {

    }

    public QuestionKnowledgeQuery(String query, Date fromDate, Date toDate) {
        this.query = query;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /* Getter and Setters */

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
