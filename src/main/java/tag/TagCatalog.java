package tag;

import knowledge.Knowledge;
import util.IdGenerator;
import util.ValidationError;

import java.util.List;

public class TagCatalog {
    private static TagCatalog instance;

    public Tag addDefaultTag(String name) throws ValidationError {
        Tag tag = new Tag();
        tag.setId(IdGenerator.generateID());
        tag.setName(name);
        tag.validate();
        new TagDAO().insert(tag);
        return tag;
    }

    public void setTags(List<Tag> tags) {
        TagDAO dao = new TagDAO();
        for(Tag tag: tags){
            if(tag.getId() == null){
                tag.setId(IdGenerator.generateID());
                dao.insert(tag);
            }else{
                dao.update(tag);
            }
        }
    }

    public void removeDefaultTag(Tag tag) {
        new TagDAO().delete(tag);
    }

    public List<Tag> getDefaultTags() {
        return new TagDAO().getObjects();
    }

    public Tag getTagByName(String tag) {
        return new TagDAO().getObject("name", tag);
    }

    public Tag getTagById(String id) {
        return new TagDAO().getObject("id", id);
    }

    public List<Tag> findTagsByKnowledge(Knowledge knowledge) {
        return new TagDAO().getKnowledgeTags(knowledge.getId());
    }

    public static TagCatalog getInstance() {
        if (instance == null) {
            instance = new TagCatalog();
        }
        return instance;
    }
}
