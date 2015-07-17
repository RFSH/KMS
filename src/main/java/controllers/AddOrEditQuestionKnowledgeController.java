package controllers;

import javang.JavaNGController;
import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;
import netscape.javascript.JSObject;
import tag.Tag;
import tag.TagCatalog;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class AddOrEditQuestionKnowledgeController extends JavaNGController {
    private String id;
    private String title;
    private String content;
    private List<String> tags;

    @Scope
    public QuestionKnowledge getQuestionKnowledge(String knowledgeId) {
        return KnowledgeCatalog.getInstance().getQuestionKnowledge(knowledgeId);
    }

    @Scope
    public String addOrUpdateQuestionKnowledge(boolean update, JSObject details) {
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

        changePage("/question/list");
        return "";
    }

    private void updateKnowledge() throws ValidationError {
        QuestionKnowledge knowledge = KnowledgeCatalog.getInstance().getQuestionKnowledge(id);
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
        KnowledgeCatalog.getInstance().addQuestionKnowledge(title, content, tags);
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
