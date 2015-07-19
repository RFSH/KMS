package account;

import util.IdGenerator;

import java.util.List;

public class RoleCatalog {
    private static RoleCatalog instance;

    private RoleCatalog() {

    }

    public void removeRole(Role role) {
        new RoleDAO().delete(role);
    }

    public void setRoles(List<Role> roles) {
        RoleDAO dao = new RoleDAO();
        for(Role role: roles){
            if(role.getId() == null){
                role.setId(IdGenerator.generateID());
                dao.insert(role);
            }else{
                dao.update(role);
            }
        }
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
