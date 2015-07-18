package tag;

import util.ValidationError;

public class Tag {
    private String id;
    private String name;

    public Tag(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void validate() throws ValidationError {

    }
}
