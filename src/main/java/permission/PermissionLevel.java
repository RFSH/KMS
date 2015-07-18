package permission;

import util.IdGenerator;

public class PermissionLevel {
    private String id;
    private String name;
    private Integer order;

    public PermissionLevel(String name, int order) {
        this.name = name;
        this.order = order;
        this.id = IdGenerator.generateID();
    }

    public PermissionLevel(String name, int order, String id){
        this.name = name;
        this.order = order;
        this.id = id;
    }

    public PermissionLevel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer getOrder) {
        this.order = order;
    }
}
