package controllers;

import abusereport.AbuseReportCatalog;
import account.Context;
import account.Manager;
import account.User;
import javang.JavaNGController;
import javang.Scope;
import knowledge.Knowledge;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;
import permission.Authorizer;
import permission.ItemPermissions;
import permission.PermissionLevel;
import permission.PermissionLevelCatalog;
import util.ValidationError;

import java.util.List;

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
            KnowledgeCatalog.getInstance().deleteKnowledge(knowledgeId);
        }
    }

    @Scope
    public void addAbuseReport(String knowledgeId, String content) {
        Knowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        AbuseReportCatalog.getInstance().addAbuseReport(content, knowledge);
    }

    @Scope
    public boolean isUserManager() {
        return Context.getInstance().getLoggedInUser() instanceof Manager;
    }

    @Scope
    public List<PermissionLevel> getPermissionLevels() {
        return PermissionLevelCatalog.getInstance().getOrderedPermissionLevels();
    }

    @Scope
    public ItemPermissions getUserPermissions(String knowledgeId) {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
        User user = Context.getInstance().getLoggedInUser();
        return new Authorizer().getPermissions(user, knowledge);
    }
}
