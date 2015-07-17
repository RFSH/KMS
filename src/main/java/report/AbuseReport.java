package report;

import account.Employee;

public class AbuseReport {
    private String id;
    private String content;
    private Employee reporter;


    public void respondToReport(boolean isAccepted){
        //TODO
    }


    /* Getters and Setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Employee getReporter() {
        return reporter;
    }

    public void setReporter(Employee reporter) {
        this.reporter = reporter;
    }
}
