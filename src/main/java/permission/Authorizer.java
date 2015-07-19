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
            permissions.setAdd(false);
            permissions.setChange(false);
            permissions.setClone(false);
            permissions.setDelete(false);
            permissions.setDeprecate(true);
            permissions.setVote(false);
        } else if (user instanceof Employee) {
            permissions.setAdd(true);
            permissions.setChange(true);
            permissions.setClone(true);
            permissions.setDeprecate(true);
            permissions.setReport(true);
            permissions.setDelete(false);
            permissions.setVote(true);

            if (item instanceof WikiKnowledge && ((WikiKnowledge)item).isDeprecated()) {
                permissions.setChange(false);
            }
        }

        return permissions;
    }

    public ItemPermissions getPermissions(User user, Employee item) {
        return null;
    }
}
