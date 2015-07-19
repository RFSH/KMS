package permission;

import account.Employee;
import account.Manager;
import account.User;
import knowledge.Knowledge;
import knowledge.WikiKnowledge;

public class Authorizer {

    public ItemPermissions getPermissions(User user, Knowledge item) {
        ItemPermissions permissions = new ItemPermissions();

        if (user instanceof Manager) {
            permissions.setView(true);
            permissions.setAdd(false);
            permissions.setChange(false);
            permissions.setClone(false);
            permissions.setDelete(false);
            permissions.setDeprecate(true);
            permissions.setVote(false);
        } else if (user instanceof Employee) {
            permissions.setView(true);
            permissions.setAdd(true);
            permissions.setClone(true);
            permissions.setChange(true);
            permissions.setDeprecate(true);
            permissions.setReport(true);
            permissions.setVote(true);

            if (item instanceof WikiKnowledge) {
                WikiKnowledge wikiKnowledge = (WikiKnowledge)item;
                Employee employee = (Employee)user;
                if (wikiKnowledge.isDeprecated()) {
                    permissions.setChange(false);
                }

                if (wikiKnowledge.getViewPermissionLevel() != null &&
                        wikiKnowledge.getViewPermissionLevel().getOrder() < employee.getPermissionLevel().getOrder()) {
                    permissions.setView(false);
                }

                if (wikiKnowledge.getChangePermissionLevel() != null &&
                        wikiKnowledge.getChangePermissionLevel().getOrder() < employee.getPermissionLevel().getOrder()) {
                    permissions.setChange(false);
                    permissions.setDeprecate(false);
                }

                if (!wikiKnowledge.isApproved() && !wikiKnowledge.getOwner().equals(employee)) {
                    permissions.setView(false);
                    permissions.setChange(false);
                }
            }
        }

        return permissions;
    }

    public ItemPermissions getPermissions(User user, Employee item) {
        return null;
    }
}
