package controllers;

import abusereport.AbuseReport;
import abusereport.AbuseReportCatalog;
import abusereport.AbuseReportQuery;
import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.KnowledgeType;

import java.util.List;

public class AbuseReportListController extends JavaNGController {
    @Scope
    public List<AbuseReport> searchAbuseReport(String query, String knowledgeTypeStr) {
        int knowledgeType = Integer.parseInt(knowledgeTypeStr);
        AbuseReportQuery abuseQuery = new AbuseReportQuery();
        abuseQuery.setContnet(query);
        if (knowledgeType > 0) {
            abuseQuery.setKnowledgeType(KnowledgeType.values()[knowledgeType]);
        }
        return AbuseReportCatalog.getInstance().findAbuseReports(abuseQuery);
    }

    @Scope
    public void responseToReport(String reportId, boolean isApproved) {
        AbuseReport report = AbuseReportCatalog.getInstance().getReport(reportId);
        if (isApproved) {
            KnowledgeCatalog.getInstance().deleteKnowledge(report.getKnowledge());
        }
        AbuseReportCatalog.getInstance().removeReport(report.getId());
    }
}
