package knowledgebase;

import tag.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    private String title;
    private String description;
    private String id;
    private List<ProjectActivity> projectActivities;
    private Date creationDate;


    /* Getter and Setters */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProjectActivity> getProjectActivities() {
        if(this.projectActivities == null){
            this.projectActivities = new ProjectActivityDAO().getProjectActivities(getId());
        }
        return this.projectActivities;
    }

    public void setProjectActivities(List<ProjectActivity> projectActivities) {
        this.projectActivities = projectActivities;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }
}
