package knowledgebase;

import java.util.Date;
import java.util.List;

public class Letter {
    private String title;
    private String content;
    private String id;
    private List<LetterPathNode> letterPathNodes;
    private Date creationDate;


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

    public List<LetterPathNode> getLetterPathNodes() {
        if (this.letterPathNodes == null) {
            this.letterPathNodes = new LetterPathNodeDAO().getLetterPathNodes(getId());
        }
        return letterPathNodes;
    }

    public void setLetterPathNodes(List<LetterPathNode> letterPathNodes) {
        this.letterPathNodes = letterPathNodes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }
}
