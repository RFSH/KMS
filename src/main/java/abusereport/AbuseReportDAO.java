package abusereport;

import account.Employee;
import account.UserCatalog;
import db.BaseDAO;
import knowledge.Knowledge;
import knowledge.KnowledgeCatalog;
import knowledge.KnowledgeType;
import knowledge.QuestionKnowledge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbuseReportDAO extends BaseDAO<AbuseReport> {
    @Override
    public String getTableName() {
        return "abuse_reports";
    }

    @Override
    public Object[] getColumnValues(AbuseReport abuseReport) {
        return new Object[] {
                "id", abuseReport.getId(),
                "content", abuseReport.getContent(),
                "employee_id", abuseReport.getReporter().getId(),
                "knowledge_id", abuseReport.getKnowledge().getId()
        };
    }

    @Override
    public String getWhereClause(AbuseReport abuseReport) {
        return "id='" + abuseReport.getId() + "'";
    }

    @Override
    public AbuseReport getObjectFromResult(ResultSet result) {
        AbuseReport report = new AbuseReport();
        try {
            report.setId(result.getString("id"));
            report.setContent(result.getString("content"));

            Knowledge knowledge = null;
            String knowledgeId = result.getString("knowledge_id");
            knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
            if (knowledge == null) {
                knowledge = KnowledgeCatalog.getInstance().getQuestionKnowledge(knowledgeId);
            }
            if (knowledge == null) {
                knowledge = KnowledgeCatalog.getInstance().getAnswerKnowledge(knowledgeId);
            }

            if (knowledge != null) {
                report.setKnowledge(knowledge);
            }

            Employee employee = UserCatalog.getInstance().findEmployeeById(result.getString("employee_id"));
            report.setReporter(employee);

            return report;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AbuseReport> getAbuseReports(String content) {
        String query = null;
        if (content != null && !content.isEmpty()) {
            query = String.format("CONTAINS(content, '%s')", content);
        }
        ResultSet result = query(getTableName(), query);
        List<AbuseReport> reports= new ArrayList<>();
        try {
            while (result.next()) {
                reports.add(getObjectFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
