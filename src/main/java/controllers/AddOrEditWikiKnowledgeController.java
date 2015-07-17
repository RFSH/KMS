package controllers;

import account.Context;
import account.Employee;
import javang.JavaNGController;
import javang.Scope;
import knowledge.Knowledge;
import knowledge.KnowledgeCatalog;
import knowledge.WikiKnowledge;
import netscape.javascript.JSObject;
import tag.Tag;
import tag.TagCatalog;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class AddOrEditWikiKnowledgeController extends JavaNGController {
    private String id;
    private String title;
    private String content;
    private List<String> tags;

    @Scope
    public WikiKnowledge getWikiKnowledge(String knowledgeId) {
        return KnowledgeCatalog.getInstance().getWikiKnowledge(knowledgeId);
    }

    @Scope
    public String addOrUpdateWikiKnowledge(boolean update, JSObject details) {
        readKnowledgeValues(details);
        try {
            if (update) {
                updateKnowledge();
            } else {
                addKnowledge();
            }
        } catch (ValidationError validationError) {
            return validationError.getError();
        }

        changePage("/knowledge/list");
        return "";
    }

    private void updateKnowledge() throws ValidationError {
        WikiKnowledge knowledge = KnowledgeCatalog.getInstance().getWikiKnowledge(id);
        knowledge.setTitle(title);
        knowledge.setContent(content);

        knowledge.setTags(new ArrayList<>());
        for (String tagName : tags) {
            Tag tag = TagCatalog.getInstance().getTagByName(tagName);
            if (tag == null) {
                tag = TagCatalog.getInstance().addDefaultTag(tagName);
            }
            knowledge.getTags().add(tag);
        }
        knowledge.validate();
        knowledge.save();
    }

    private void addKnowledge() throws ValidationError {
        KnowledgeCatalog.getInstance().addWikiKnowledge(title, content, tags);
    }


    private void readKnowledgeValues(JSObject details) {
        id = getMember(details, "id");
        title = getMember(details, "title");
        content = getMember(details, "content");


        tags = new ArrayList<>();
        JSObject tagObj = (JSObject) details.getMember("tags");
        for (int i = 0; i < (Integer)tagObj.getMember("length"); i++) {
            tags.add((String)tagObj.getMember(String.valueOf(i)));
        }
    }

    private String getMember(JSObject details, String key) {
        String value = (String) details.getMember(key);
        if (value.equals("undefined")) {
            return null;
        }
        return value;
    }
}
