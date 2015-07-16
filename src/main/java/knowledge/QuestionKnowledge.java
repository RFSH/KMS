package knowledge;

import java.util.List;

public class QuestionKnowledge extends Knowledge{
    private String title;
    private String content;

    public AnswerKnowledge addAnswer(String content){
        //TODO
        return null;
    }

    public List<AnswerKnowledge> getAnswers(){
        //TODO
        return null;
    }

    /* Getters and Setters */

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
