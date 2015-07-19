package abusereport;

import account.Context;
import account.Employee;
import knowledge.Knowledge;
import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class AbuseReportCatalog {
    private static AbuseReportCatalog instance;

    public void addAbuseReport(String content, Knowledge knowledge){
        AbuseReport report = new AbuseReport();
        report.setId(IdGenerator.generateID());
        report.setContent(content);
        report.setKnowledge(knowledge);
        report.setReporter((Employee) Context.getInstance().getLoggedInUser());
        new AbuseReportDAO().insert(report);
    }

    public List<AbuseReport> findAbuseReports(AbuseReportQuery query){
        List<AbuseReport> reports = new AbuseReportDAO().getAbuseReports(query.getContent());
        List<AbuseReport> filteredReports = new ArrayList<>();
        for (AbuseReport report : reports) {
            if (query.getKnowledgeType() == null ||
                    report.getKnowledge().getKnowledgeType().equals(query.getKnowledgeType())) {
                filteredReports.add(report);
            }
        }
        return filteredReports;
    }

    public AbuseReport getReport(String id) {
        return new AbuseReportDAO().getObject("id", id);
    }

    public void removeReport(String id){
        AbuseReportDAO dao = new AbuseReportDAO();
        dao.delete(dao.getTableName(), "id='" + id + "'");
    }

    public static AbuseReportCatalog getInstance() {
        if (instance == null) {
            instance = new AbuseReportCatalog();
        }
        return instance;
    }
}
