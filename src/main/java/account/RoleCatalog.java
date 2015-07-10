package account;

import java.util.List;

public class RoleCatalog {
    private static RoleCatalog instance;

    private RoleCatalog() {

    }

    public Role addRole(String name) {
        return null;
    }

    public void removeRole(Role role) {

    }

    public List<Role> getRoles() {
        return new RoleDAO().getObjects();
    }

    public Role findById(String id) {
        return new RoleDAO().getObject("id", id);
    }

    public Role findByName(String name) {
        return new RoleDAO().getObject("name", name);
    }

    public static RoleCatalog getInstance() {
        if (instance == null) {
            instance = new RoleCatalog();
        }
        return instance;
    }
}
