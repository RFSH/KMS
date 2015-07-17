package knowledgebase;

import tag.Tag;

import java.util.List;

public class Project {
    private String title;
    private String description;
    private String id;


    public List<ProjectActivity> getProjectActivites(){
        return new ProjectActivityDAO().getProjectActivities("id", getId());
    }

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
}
