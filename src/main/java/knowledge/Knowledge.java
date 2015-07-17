package knowledge;

import account.Employee;
import tag.Tag;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class Knowledge {
    private String id;
    private Employee employee;
    private List<Tag> tags;

    public Vote addVote(int upOrDown) {
        return null;
    }

    public void validate() throws ValidationError {
        if (employee == null) {
            throw new ValidationError("Knowledge must have employee");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
