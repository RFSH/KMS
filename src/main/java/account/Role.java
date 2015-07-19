package account;

public class Role {
    private String id;
    private String name;

    public Role(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Role() {

    }

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
}
