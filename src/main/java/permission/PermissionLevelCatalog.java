package permission;

import java.util.List;

public class PermissionLevelCatalog {
    private static PermissionLevelCatalog instance;

    private PermissionLevelCatalog() {

    }

    public PermissionLevel addPermissionLevel(String name) {
        return null;
    }

    public void removePermissionLevel(PermissionLevel permissionLevel) {

    }

    public List<PermissionLevel> getOrderedPermissionLevels() {
        return new PermissionLevelDAO().getObjects();
    }

    public PermissionLevel findById(String id) {
        return new PermissionLevelDAO().getObject("id", id);
    }

    public void setOrderedPermissionLevels(List<PermissionLevel> permissionLevels) {
    }

    public static PermissionLevelCatalog getInstance() {
        if (instance == null) {
            instance = new PermissionLevelCatalog();
        }
        return instance;
    }
}
