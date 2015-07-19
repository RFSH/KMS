package controllers;

import abusereport.AbuseReportCatalog;
import javang.JavaNGController;
import javang.Scope;
import knowledge.*;
import util.ValidationError;

import java.util.List;

public class QuestionKnowledgeController extends JavaNGController {
    @Scope
    public QuestionKnowledge getQuestionKnowledge(String questionKnowledgeId) {
        return KnowledgeCatalog.getInstance().getQuestionKnowledge(questionKnowledgeId);
    }

    @Scope
    public int addVote(String knowledgeId, int upOrDown) {
        Knowledge knowledge = KnowledgeCatalog.getInstance().getKnowledge(knowledgeId);
        knowledge.addVoteUpOrDown(upOrDown);
        return knowledge.getVoteSum();
    }

    @Scope
    public List<AnswerKnowledge> getAnswers(String questionId) {
        QuestionKnowledge question = KnowledgeCatalog.getInstance().getQuestionKnowledge(questionId);
        return question.getAnswers();
    }

    @Scope
    public AnswerKnowledge addAnswer(String questionId, String content) {
        QuestionKnowledge question = KnowledgeCatalog.getInstance().getQuestionKnowledge(questionId);
        try {
            return question.addAnswerKnowledge(content);
        } catch (ValidationError validationError) {
        }
        return null;
    }

    @Scope
    public void addAbuseReport(String knowledgeId, String content) {
        Knowledge knowledge = KnowledgeCatalog.getInstance().getKnowledge(knowledgeId);
        AbuseReportCatalog.getInstance().addAbuseReport(content, knowledge);
    }
}
