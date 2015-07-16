package knowledgebase;

import tag.Tag;

import java.util.List;

public class Letter {
    private String title;
    private String content;
    private String id;
    private List<Tag> tags;


    public List<LetterPathNode> getLetterPathNodes(){
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
