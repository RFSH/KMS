package knowledge;

import account.Employee;

public class Vote {
    public static final int UP = 1;
    public static final int DOWN = -1;

    private int upOrDown;
    private Employee employee;
    private Knowledge knowledge;

    /* Getter and Setters */

    public int getUpOrDown() {
        return upOrDown;
    }

    public void setUpOrDown(int upOrDown) {
        this.upOrDown = upOrDown;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }
}
