package report;

import knowledge.AnswerKnowledge;
import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;

public interface KnowledgeReportGenerator {
    public void visitWikiKnowldege(WikiKnowledge wikiKnowledge);

    public void visitQuestionKnowldege(QuestionKnowledge questionKnowledge);

    public void visitAnswerKnowldege(AnswerKnowledge answerKnowledge);
}
