package activity;

import java.util.Date;

public class Activity {
    Date creationDate;
    ActivityType type;


    /* Getter and Setters */

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
