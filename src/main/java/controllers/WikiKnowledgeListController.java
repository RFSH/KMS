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
import java.util.Date;
import java.util.List;

public class WikiKnowledgeListController extends JavaNGController {
    @Scope
    public List<WikiKnowledge> searchWikiKnowledge(String query, String fromDate, String toDate) {
        WikiKnowledgeQuery wikiQuery = new WikiKnowledgeQuery();
        wikiQuery.setIsApproved(true);
        wikiQuery.setQuery(query);

        if (fromDate != null && !fromDate.isEmpty()) {
            wikiQuery.setFromDate(new Date(Long.parseLong(fromDate)));
        }
        if (toDate != null && !toDate.isEmpty()) {
            wikiQuery.setToDate(new Date(Long.parseLong(toDate)));
        }

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
