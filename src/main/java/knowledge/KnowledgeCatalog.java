package knowledge;

import account.Context;
import account.Employee;
import tag.Tag;
import tag.TagCatalog;
import util.IdGenerator;
import util.ValidationError;

import java.util.Date;
import java.util.List;

public class KnowledgeCatalog {
    private static KnowledgeCatalog instance;

    public Knowledge getKnowledge(String id) {
        KnowledgeDAO dao = new KnowledgeDAO();
        Knowledge knowledge = dao.getWikiKnowledge("knowledges.id", id);
        if (knowledge == null) {
            knowledge = dao.getQuestionKnowledge("knowledges.id", id);
        }
        if (knowledge == null) {
            knowledge = dao.getAnswerKnowledge("knowledges.id", id);
        }
        return knowledge;
    }

    public WikiKnowledge getWikiKnowledge(String id) {
        return new KnowledgeDAO().getWikiKnowledge("knowledges.id", id);
    }

    public QuestionKnowledge getQuestionKnowledge(String id) {
        return new KnowledgeDAO().getQuestionKnowledge("knowledges.id", id);
    }

    public AnswerKnowledge getAnswerKnowledge(String id) {
        return new KnowledgeDAO().getAnswerKnowledge("knowledges.id", id);
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
        knowledge.setCreationDate(new Date());
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
        knowledge.setCreationDate(new Date());
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
        StringBuilder queryBuilder = new StringBuilder();
        if (query != null && query.getQuery() != null && !query.getQuery().isEmpty()) {
            queryBuilder.append("(title LIKE '%").append(query.getQuery()).append("%' OR ")
            .append("content LIKE '%").append(query.getQuery()).append("%')");
        }
        if (query != null && query.getFromDate() != null) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("creation_date >= ").append(query.getFromDate().getTime());
        }
        if (query != null && query.getToDate() != null) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("creation_date <= ").append(query.getToDate().getTime());
        }
        return new KnowledgeDAO().getWikiKnowledges(queryBuilder.toString());
    }

    public List<QuestionKnowledge> findQuestionKnowledges(QuestionKnowledgeQuery query) {
        StringBuilder queryBuilder = new StringBuilder();
        if (query != null && query.getQuery() != null && !query.getQuery().isEmpty()) {
            queryBuilder.append("(title LIKE '%").append(query.getQuery()).append("%' OR ")
                    .append("content LIKE '%").append(query.getQuery()).append("%')");
        }
        if (query != null && query.getFromDate() != null) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("creation_date >= ").append(query.getFromDate().getTime());
        }
        if (query != null && query.getToDate() != null) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }
            queryBuilder.append("creation_date <= ").append(query.getToDate().getTime());
        }
        return new KnowledgeDAO().getQuestionKnowledges(queryBuilder.toString());
    }

    public void deleteKnowledge(String knowledgeId) {
        deleteKnowledge(getKnowledge(knowledgeId));
    }

    public void deleteKnowledge(Knowledge knowledge) {
        new KnowledgeDAO().delete(knowledge);
    }

    public static KnowledgeCatalog getInstance() {
        if (instance == null) {
            instance = new KnowledgeCatalog();
        }
        return instance;
    }


}
