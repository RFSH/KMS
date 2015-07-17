package controllers;

import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;

public class WikiKnowledgeController extends JavaNGController {
    @Scope
    public WikiKnowledge getWikiKnowledge(String wikiKnowledgeId) {
        return KnowledgeCatalog.getInstance().getWikiKnowledge(wikiKnowledgeId);
    }

    @Scope
    public void addVote(String knowledgeId, int upOrDown) {

    }
}
