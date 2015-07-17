package controllers;

import account.Context;
import account.Manager;
import account.User;
import javang.JavaNGController;
import javang.Scope;
import knowledge.Knowledge;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;
import util.ValidationError;

public class WikiKnowledgeController extends JavaNGController {
    @Scope
    public WikiKnowledge getWikiKnowledge(String wikiKnowledgeId) {
        return KnowledgeCatalog.getInstance().getWikiKnowledge(wikiKnowledgeId);
    }

    @Scope
    public void addVote(String knowledgeId, int upOrDown) {
        Knowledge knowledge = KnowledgeCatalog.getInstance().getKnowledge(knowledgeId);
        knowledge.addVoteUpOrDown(upOrDown);
    }

    @Scope
    public void deprecateWikiKnowledge(String knowledgeId) {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        knowledge.deprecate();
        knowledge.save();
    }

    @Scope
    public void addUseCase(String knowledgeId, String title) {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        knowledge.getUseCaseList().add(title);
        knowledge.save();
    }

    @Scope
    public void approve(String knowledgeId, int approveOrDisapprove) {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        if (approveOrDisapprove == 1) {
            knowledge.approveOrDisapprove(true);
            knowledge.save();
        } else {
            knowledge.approveOrDisapprove(false);
        }
    }

    @Scope
    public boolean isUserManager() {
        return Context.getInstance().getLoggedInUser() instanceof Manager;
    }

    @Scope
    public boolean hasChangePermission(String knowledgeId) {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        User user = Context.getInstance().getLoggedInUser();
        return user.equals(knowledge.getOwner()) || user instanceof Manager;
    }
}
