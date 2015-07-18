package controllers;

import account.Role;
import account.RoleCatalog;
import javang.Scope;
import netscape.javascript.JSObject;
import permission.PermissionLevel;
import permission.PermissionLevelCatalog;
import tag.Tag;
import tag.TagCatalog;

import java.util.ArrayList;
import java.util.List;

public class SettingsController {

    @Scope
    public void removePermissionLevel(String permissionId) {
        if (!permissionId.equals("undefined")) {
            PermissionLevel permissionLevel = PermissionLevelCatalog.getInstance().findById(permissionId);
            PermissionLevelCatalog.getInstance().removePermissionLevel(permissionLevel);
        }
    }

    @Scope
    public List<PermissionLevel> getPermissions() {
        return PermissionLevelCatalog.getInstance().getOrderedPermissionLevels();
    }

    @Scope
    public boolean setPermissions(JSObject data, int size) {
        List<PermissionLevel> permissionLevels = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            JSObject object = (JSObject) data.getSlot(i);
            String id = getMember(object, "id");
            String name = getMember(object, "name");
            if (name == null || name.equals("")) {
                return false;
            }
            permissionLevels.add(new PermissionLevel(name, i + 1, id));
            System.out.println("name= " + name + ", id= " + id);
        }

        PermissionLevelCatalog.getInstance().setOrderedPermissionLevels(permissionLevels);
        return true;
    }

    @Scope
    public void removeDefaultTag(String tagId) {
        if (!tagId.equals("undefined")) {
            Tag tag = TagCatalog.getInstance().getTagById(tagId);
            TagCatalog.getInstance().removeDefaultTag(tag);
        }
    }

    @Scope
    public List<Tag> getDefaultTags() {
        return TagCatalog.getInstance().getDefaultTags();
    }

    @Scope
    public boolean setDefaultTags(JSObject data, int size) {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            JSObject object = (JSObject) data.getSlot(i);
            String id = getMember(object, "id");
            String name = getMember(object, "name");
            if (name == null || name.equals("")) {
                return false;
            }
            tags.add(new Tag(name, id));
            System.out.println("name= " + name + ", id= " + id);
        }

        TagCatalog.getInstance().setTags(tags);
        return true;
    }

    @Scope
    public void removeRole(String roleId) {
        if (!roleId.equals("undefined")) {
            Role role = RoleCatalog.getInstance().findById(roleId);
            RoleCatalog.getInstance().removeRole(role);
        }
    }

    @Scope
    public List<Role> getRoles() {
        return RoleCatalog.getInstance().getRoles();
    }

    @Scope
    public boolean setRoles(JSObject data, int size) {
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            JSObject object = (JSObject) data.getSlot(i);
            String id = getMember(object, "id");
            String name = getMember(object, "name");
            if (name == null || name.equals("")) {
                return false;
            }
            roles.add(new Role(name, id));
            System.out.println("name= " + name + ", id= " + id);
        }

        RoleCatalog.getInstance().setRoles(roles);
        return true;
    }


    private String getMember(JSObject details, String key) {
        String value = (String) details.getMember(key);
        if (value.equals("undefined")) {
            return null;
        }
        return value;
    }
}
