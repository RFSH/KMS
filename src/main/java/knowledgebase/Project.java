package knowledgebase;

import tag.Tag;

import java.util.List;

public class Project {
    private String title;
    private String description;
    private String id;
    private List<Tag> tags;


    public List<ProjectActivity> getProjectActivites(){
        //TODO
        return null;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
