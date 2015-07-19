package knowledge;

import report.KnowledgeReportGenerator;

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


    @Override
    public void acceptReporter(KnowledgeReportGenerator knowledgeReportGenerator) {
        // Doesn't accept report.
    }

    @Override
    public KnowledgeType getKnowledgeType() {
        return KnowledgeType.ANSWER;
    }
}
