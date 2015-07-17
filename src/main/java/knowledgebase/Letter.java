package knowledgebase;

import tag.Tag;

import java.util.List;

public class Letter {
    private String title;
    private String content;
    private String id;

    public List<LetterPathNode> getLetterPathNodes(){
        return new LetterPathNodeDAO().getLetterPathNodes("id", getId());
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

}
