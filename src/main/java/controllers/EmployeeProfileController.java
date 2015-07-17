package controllers;

import account.Employee;
import account.UserCatalog;
import javang.Scope;

public class EmployeeProfileController {
    @Scope
    public Employee getEmployee(String employeeId) {
        return UserCatalog.getInstance().findEmployeeById(employeeId);
    }

    @Scope
    public EmployeeStats getEmployeeStats(String employeeId) {
        EmployeeStats stats = new EmployeeStats();

        // TODO fill here
        stats.wikiKnowledgeCount = 5;
        stats.questionKnowledgeCount = 10;
        stats.voteNum = 20;
        stats.voteSum = 30;
        return stats;
    }

    public static class EmployeeStats {
        public int wikiKnowledgeCount;
        public int questionKnowledgeCount;
        public int voteSum;
        public int voteNum;
    }

}
