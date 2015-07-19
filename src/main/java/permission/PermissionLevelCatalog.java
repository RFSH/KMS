package permission;

import util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class PermissionLevelCatalog {
    private static PermissionLevelCatalog instance;

    private PermissionLevelCatalog() {

    }

    public PermissionLevel addPermissionLevel(String name, int order) {
        PermissionLevel permissionLevel = new PermissionLevel(name, order);
        new PermissionLevelDAO().insert(permissionLevel);
        return permissionLevel;
    }

    public void removePermissionLevel(PermissionLevel permission) {
        new PermissionLevelDAO().delete(permission);
    }

    public List<PermissionLevel> getOrderedPermissionLevels() {
        return new PermissionLevelDAO().getObjects();
    }

    public PermissionLevel findById(String id) {
        return new PermissionLevelDAO().getObject("id", id);
    }

    public void setOrderedPermissionLevels(List<PermissionLevel> permissionLevels) {
        PermissionLevelDAO dao = new PermissionLevelDAO();
        for(PermissionLevel permission: permissionLevels){
            if(permission.getId() == null){
                permission.setId(IdGenerator.generateID());
                dao.insert(permission);
            }else{
                dao.update(permission);
            }
        }
    }

    public static PermissionLevelCatalog getInstance() {
        if (instance == null) {
            instance = new PermissionLevelCatalog();
        }
        return instance;
    }
}
