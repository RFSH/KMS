package knowledge;

public class AnswerKnowledge extends Knowledge {
    private QuestionKnowledge question;
    private String content;

    public QuestionKnowledge getQuestion() {
        return question;
    }

    public String getContent() {
        return content;
    }

    public void setQuestion(QuestionKnowledge question) {
        this.question = question;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
