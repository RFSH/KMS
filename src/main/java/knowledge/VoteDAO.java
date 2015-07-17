package knowledge;

import account.UserCatalog;
import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDAO extends BaseDAO<Vote> {
    @Override
    public String getTableName() {
        return "votes";
    }

    @Override
    public Object[] getColumnValues(Vote vote) {
        return new Object[] {
                "employee_id", vote.getEmployee().getId(),
                "knowledge_id", vote.getKnowledge().getId(),
                "value", vote.getUpOrDown()
        };
    }

    @Override
    public String getWhereClause(Vote vote) {
        return "knowledge_id='" + vote.getKnowledge().getId() +"' AND employee_id='" + vote.getEmployee().getId() + "'";
    }

    @Override
    public Vote getObjectFromResult(ResultSet result) {
        Vote vote = new Vote();
        try {
            vote.setEmployee(UserCatalog.getInstance().findEmployeeById(result.getString("employee_id")));
            vote.setKnowledge(KnowledgeCatalog.getInstance().getKnowledge(result.getString("knowledge_id")));
            vote.setUpOrDown(result.getInt("value"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vote;
    }

    public Vote getVote(String employeeId, String knowledgeId) {
        ResultSet result = query(getTableName(), "employee_id='" + employeeId + "' AND knowledge_id='" + knowledgeId+"'");
        try {
            if (result.next()) {
                return getObjectFromResult(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
