package controllers;

import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;
import knowledge.WikiKnowledgeQuery;

import java.util.List;

public class WikiKnowledgeListController extends JavaNGController {
    @Scope
    public List<WikiKnowledge> searchWikiKnowledge(String query, String fromDate, String toDate) {
        WikiKnowledgeQuery wikiQuery = new WikiKnowledgeQuery();
        wikiQuery.setIsApproved(null);
        wikiQuery.setIsDeprecated(null);
        wikiQuery.setQuery(query);
        return KnowledgeCatalog.getInstance().findWikiKnowledges(wikiQuery);
    }
}
