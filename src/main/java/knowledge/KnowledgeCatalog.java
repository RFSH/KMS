package knowledge;

import account.Context;
import account.Employee;
import tag.Tag;
import tag.TagCatalog;
import util.IdGenerator;
import util.ValidationError;

import java.util.List;

public class KnowledgeCatalog {
    private static KnowledgeCatalog instance;

    public Knowledge getKnowledge(String id) {
        return new KnowledgeDAO().getObject("id", id);
    }

    public WikiKnowledge getWikiKnowledge(String id) {
        return new KnowledgeDAO().getWikiKnowledge("knowledges.id", id);
    }

    public QuestionKnowledge getQuestionKnowledge(String id) {
        return new KnowledgeDAO().getQuestionKnowledge("knowledges.id", id);
    }

    public void addWikiKnowledge(String title, String content, List<String> tags) throws ValidationError {
        WikiKnowledge knowledge = new WikiKnowledge();
        knowledge.setTitle(title);
        knowledge.setContent(content);
        for (String tagName : tags) {
            Tag tag = TagCatalog.getInstance().getTagByName(tagName);
            if (tag == null) {
                tag = TagCatalog.getInstance().addDefaultTag(tagName);
            }
            knowledge.getTags().add(tag);
        }
        knowledge.setId(IdGenerator.generateID());
        knowledge.setApproved(false);
        knowledge.setAttachment(null);
        knowledge.setDeprecated(false);

        knowledge.setOwner((Employee) Context.getInstance().getLoggedInUser());
        knowledge.validate();
        new KnowledgeDAO().insert(knowledge);
    }

    public void addQuestionKnowledge(String title, String content, List<String> tags) throws ValidationError {
        QuestionKnowledge knowledge = new QuestionKnowledge();
        knowledge.setTitle(title);
        knowledge.setContent(content);
        for (String tagName : tags) {
            Tag tag = TagCatalog.getInstance().getTagByName(tagName);
            if (tag == null) {
                tag = TagCatalog.getInstance().addDefaultTag(tagName);
            }
            knowledge.getTags().add(tag);
        }
        knowledge.setId(IdGenerator.generateID());

        knowledge.setOwner((Employee) Context.getInstance().getLoggedInUser());
        knowledge.validate();
        new KnowledgeDAO().insert(knowledge);
    }

    public void updateKnowledge(Knowledge knowledge) throws ValidationError {
        knowledge.validate();
        new KnowledgeDAO().update(knowledge);
    }

    public void updateKnowledge(WikiKnowledge knowledge) throws ValidationError {
        knowledge.validate();
        new KnowledgeDAO().update(knowledge);
    }

    public List<WikiKnowledge> findWikiKnowledges(WikiKnowledgeQuery query) {
        // TODO fix wiki knowledge search
        if (query.getQuery() == null || query.getQuery().isEmpty()) {
            return new KnowledgeDAO().getWikiKnowledges();
        }
        return new KnowledgeDAO().getWikiKnowledges("title", query.getQuery());
    }

    public List<QuestionKnowledge> findQuestionKnowledges(QuestionKnowledgeQuery query) {
        // TODO fix question knowledge search
        if (query.getQuery() == null || query.getQuery().isEmpty()) {
            return new KnowledgeDAO().getQuestionKnowledges();
        }
        return new KnowledgeDAO().getQuestionKnowledges("title", query.getQuery());
    }

    public static KnowledgeCatalog getInstance() {
        if (instance == null) {
            instance = new KnowledgeCatalog();
        }
        return instance;
    }

}
