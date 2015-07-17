package controllers;

import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.QuestionKnowledge;
import knowledge.QuestionKnowledgeQuery;
import knowledge.WikiKnowledge;

import java.util.List;

public class QuestionKnowledgeListController extends JavaNGController {
    @Scope
    public List<QuestionKnowledge> searchQuestionKnowledge(String query, String fromDate, String toDate) {
        QuestionKnowledgeQuery questionQuery = new QuestionKnowledgeQuery();
        questionQuery.setQuery(query);
        return KnowledgeCatalog.getInstance().findQuestionKnowledges(questionQuery);
    }
}
