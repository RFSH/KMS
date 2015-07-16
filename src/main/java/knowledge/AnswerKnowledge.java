package knowledge;

public class AnswerKnowledge extends Knowledge{
    private String content;
    private QuestionKnowledge questionKnowledge;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionKnowledge getQuestionKnowledge() {
        return questionKnowledge;
    }

    public void setQuestionKnowledge(QuestionKnowledge questionKnowledge) {
        this.questionKnowledge = questionKnowledge;
    }

}
