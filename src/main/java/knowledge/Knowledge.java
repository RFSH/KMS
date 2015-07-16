package knowledge;

import account.Employee;
import tag.Tag;
import util.ValidationError;

import java.util.List;

public class Knowledge {
    private String id;
    private List<Tag> tags;
    private Employee owner;

    public void addVoteUpOrDown(int upOrDown){
        //TODO
    }

    public int getVoteSum(){
        //TODO
        return 0;
    }

    public void validate() throws ValidationError{
        //TODO
    }

    /* Getters and Setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }



}
