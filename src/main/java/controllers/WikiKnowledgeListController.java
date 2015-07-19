package controllers;

import account.Context;
import account.User;
import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;
import knowledge.WikiKnowledgeQuery;
import permission.Authorizer;
import permission.ItemPermissions;

import java.util.ArrayList;
import java.util.List;

public class WikiKnowledgeListController extends JavaNGController {
    @Scope
    public List<WikiKnowledge> searchWikiKnowledge(String query, String fromDate, String toDate) {
        WikiKnowledgeQuery wikiQuery = new WikiKnowledgeQuery();
        wikiQuery.setIsApproved(null);
        wikiQuery.setIsDeprecated(null);
        wikiQuery.setQuery(query);

        User user = Context.getInstance().getLoggedInUser();
        List<WikiKnowledge> knowledges = KnowledgeCatalog.getInstance().findWikiKnowledges(wikiQuery);
        List<WikiKnowledge> filteredKnowledges = new ArrayList<>();
        for (WikiKnowledge knowledge : knowledges) {
            ItemPermissions permission = new Authorizer().getPermissions(user, knowledge);
            if (permission.canView()) {
                filteredKnowledges.add(knowledge);
            }
        }

        return filteredKnowledges;
    }
}
