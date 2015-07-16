package tag;

import java.util.List;

public class TagCatalog {

    public Tag addDefaultTag(String name){
        Tag tag = new Tag(name);
        new TagDAO().insert(tag);
        return tag;
    }

    public void removeDefaultTag(Tag tag){
        new TagDAO().delete(tag);
    }

    public List<Tag> getDefaultTags(){
        return new TagDAO().getObjects();
    }

}
