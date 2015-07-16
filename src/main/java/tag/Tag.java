package tag;

public class Tag {
    private String id;
    private String name;

    public Tag(){

    }

    public Tag(String name){
        this.name = name;
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
