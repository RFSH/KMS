package knowledge;

import account.Context;
import account.Employee;
import report.KnowledgeReportGenerator;
import util.IdGenerator;
import util.ValidationError;

import java.util.List;

public class QuestionKnowledge extends Knowledge {
    private String title;
    private String content;

    public AnswerKnowledge addAnswerKnowledge(String content) throws ValidationError {
        KnowledgeDAO dao = new KnowledgeDAO();
        AnswerKnowledge answer = new AnswerKnowledge();
        answer.setContent(content);
        answer.setId(IdGenerator.generateID());
        answer.setQuestion(this);
        answer.setOwner((Employee) Context.getInstance().getLoggedInUser());
        answer.validate();
        dao.insert(answer);
        return answer;
    }

    public List<AnswerKnowledge> getAnswers() {
        KnowledgeDAO dao = new KnowledgeDAO();
        return dao.getAnswerKnowledges("question_id", getId());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void acceptReporter(KnowledgeReportGenerator knowledgeReportGenerator) {
        knowledgeReportGenerator.visitQuestionKnowldege(this);
    }

    @Override
    public KnowledgeType getKnowledgeType() {
        return KnowledgeType.QUESTION;
    }
}
