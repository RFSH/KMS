package knowledge;

import account.UserCatalog;
import db.BaseDAO;
import permission.PermissionLevelCatalog;
import tag.TagCatalog;
import tag.TagDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeDAO extends BaseDAO<Knowledge> {
    @Override
    public String getTableName() {
        return "knowledges";
    }

    @Override
    public Object[] getColumnValues(Knowledge knowledge) {
        return new String[]{
                "id", knowledge.getId(),
                "employee_id", knowledge.getOwner().getId()
        };
    }

    @Override
    public String getWhereClause(Knowledge knowledge) {
        return "id='" + knowledge.getId() + "'";
    }

    @Override
    public Knowledge getObjectFromResult(ResultSet result) {
//        Knowledge knowledge = new Knowledge();
//
//        try {
//            knowledge.setId(result.getString("id"));
//        } catch (SQLException e) {
//        }
        return null;
    }

    public void populateKnowledge(Knowledge knowledge, ResultSet resultSet) {
        try {
            knowledge.setId(resultSet.getString("id"));

            String employeeId = resultSet.getString("employee_id");
            knowledge.setOwner(UserCatalog.getInstance().findEmployeeById(employeeId));

            knowledge.setTags(TagCatalog.getInstance().findTagsByKnowledge(knowledge));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public WikiKnowledge getWikiKnowledgeFromResult(ResultSet resultSet) {
        WikiKnowledge wikiKnowledge = new WikiKnowledge();
        try {
            populateKnowledge(wikiKnowledge, resultSet);
            wikiKnowledge.setTitle(resultSet.getString("title"));
            wikiKnowledge.setContent(resultSet.getString("content"));
            wikiKnowledge.setApproved(resultSet.getBoolean("is_approved"));
            wikiKnowledge.setDeprecated(resultSet.getBoolean("is_deprecated"));
            wikiKnowledge.setAttachment(resultSet.getString("attachment"));

            String usecases = resultSet.getString("usecases");
            List<String> usecaseList = new ArrayList<>();
            if (usecases != null && !usecases.isEmpty()) {
                for (String usecase : usecases.split(",")) {
                    usecaseList.add(usecase);
                }
            }
            wikiKnowledge.setUseCaseList(usecaseList);

            wikiKnowledge.setViewPermissionLevel(PermissionLevelCatalog.getInstance().findById(resultSet.getString("view_permission_level_id")));
            wikiKnowledge.setChangePermissionLevel(PermissionLevelCatalog.getInstance().findById(resultSet.getString("change_permission_level_id")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wikiKnowledge;
    }

    public QuestionKnowledge getQuestionKnowledgeFromResult(ResultSet resultSet) {
        QuestionKnowledge knowledge = new QuestionKnowledge();
        try {
            populateKnowledge(knowledge, resultSet);
            knowledge.setTitle(resultSet.getString("title"));
            knowledge.setContent(resultSet.getString("content"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledge;
    }

    public AnswerKnowledge getAnswerKnowledgeFromResult(ResultSet resultSet) {
        AnswerKnowledge knowledge = new AnswerKnowledge();
        try {
            populateKnowledge(knowledge, resultSet);
            knowledge.setContent(resultSet.getString("content"));

            String questionId = resultSet.getString("question_id");
            QuestionKnowledge question  =KnowledgeCatalog.getInstance().getQuestionKnowledge(questionId);
            knowledge.setQuestion(question);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledge;
    }

    public WikiKnowledge getWikiKnowledge(String column, String value) {
        ResultSet result = query("knowledges JOIN wikiknowledges ON knowledges.id=wikiknowledges.id", column, value);
        try {
            if (result.next()) {
                return getWikiKnowledgeFromResult(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<WikiKnowledge> getWikiKnowledges(String column, String value) {
        ResultSet result = query("knowledges JOIN wikiknowledges ON knowledges.id=wikiknowledges.id", column, value);
        List<WikiKnowledge> knowledges = new ArrayList<>();
        try {
            while (result.next()) {
                knowledges.add(getWikiKnowledgeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledges;
    }

    public List<WikiKnowledge> getWikiKnowledges() {
        ResultSet result = query("knowledges JOIN wikiknowledges ON knowledges.id=wikiknowledges.id", null);
        List<WikiKnowledge> knowledges = new ArrayList<>();
        try {
            while (result.next()) {
                knowledges.add(getWikiKnowledgeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledges;
    }

    public QuestionKnowledge getQuestionKnowledge(String column, String value) {
        ResultSet result = query("knowledges JOIN questions ON knowledges.id=questions.id", column, value);
        try {
            if (result.next()) {
                return getQuestionKnowledgeFromResult(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<QuestionKnowledge> getQuestionKnowledges(String column, String value) {
        ResultSet result = query("knowledges JOIN questions ON knowledges.id=questions.id", column, value);
        List<QuestionKnowledge> knowledges = new ArrayList<>();
        try {
            while (result.next()) {
                knowledges.add(getQuestionKnowledgeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledges;
    }

    public List<QuestionKnowledge> getQuestionKnowledges() {
        ResultSet result = query("knowledges JOIN questions ON knowledges.id=questions.id", null);
        List<QuestionKnowledge> knowledges = new ArrayList<>();
        try {
            while (result.next()) {
                knowledges.add(getQuestionKnowledgeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledges;
    }

    public AnswerKnowledge getAnswerKnowledge(String column, String value) {
        ResultSet result = query("knowledges JOIN answers ON knowledges.id=answers.id", column, value);
        try {
            if (result.next()) {
                return getAnswerKnowledgeFromResult(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AnswerKnowledge> getAnswerKnowledges(String column, String value) {
        ResultSet result = query("knowledges JOIN answers ON knowledges.id=answers.id", column, value);
        List<AnswerKnowledge> knowledges = new ArrayList<>();
        try {
            while (result.next()) {
                knowledges.add(getAnswerKnowledgeFromResult(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knowledges;
    }

    public void insert(WikiKnowledge knowledge) {
        super.insert(knowledge);

        StringBuilder usecases = new StringBuilder();
        for (int i = 0; i < knowledge.getUseCaseList().size(); i++) {
            if (i != 0) {
                usecases.append(",");
            }
            usecases.append(knowledge.getUseCaseList().get(i));
        }

        if (knowledge.getViewPermissionLevel() == null) {
            super.insert("wikiknowledges", new Object[]{
                    "id", knowledge.getId(),
                    "title", knowledge.getTitle(),
                    "content", knowledge.getContent(),
                    "attachment", knowledge.getAttachment(),
                    "is_deprecated", knowledge.isDeprecated(),
                    "is_approved", knowledge.isApproved(),
                    "usecases", usecases.toString()
            });
        } else {
            super.insert("wikiknowledges", new Object[]{
                    "id", knowledge.getId(),
                    "title", knowledge.getTitle(),
                    "content", knowledge.getContent(),
                    "attachment", knowledge.getAttachment(),
                    "is_deprecated", knowledge.isDeprecated(),
                    "is_approved", knowledge.isApproved(),
                    "view_permission_level_id", knowledge.getViewPermissionLevel().getId(),
                    "change_permission_level_id", knowledge.getChangePermissionLevel().getId(),
                    "usecases", usecases.toString()
            });
        }


        new TagDAO().insertKnowledgeTags(knowledge.getId(), knowledge.getTags());
    }

    public void insert(QuestionKnowledge knowledge) {
        super.insert(knowledge);

        super.insert("questions", new Object[]{
                "id", knowledge.getId(),
                "title", knowledge.getTitle(),
                "content", knowledge.getContent()
        });
        new TagDAO().insertKnowledgeTags(knowledge.getId(), knowledge.getTags());
    }

    public void insert(AnswerKnowledge knowledge) {
        super.insert(knowledge);

        super.insert("answers", new Object[]{
                "id", knowledge.getId(),
                "question_id", knowledge.getQuestion().getId(),
                "content", knowledge.getContent()
        });
    }

    public void update(WikiKnowledge knowledge) {
        super.update(knowledge);

        StringBuilder usecases = new StringBuilder();
        for (int i = 0; i < knowledge.getUseCaseList().size(); i++) {
            if (i != 0) {
                usecases.append(",");
            }
            usecases.append(knowledge.getUseCaseList().get(i));
        }

        if (knowledge.getViewPermissionLevel() != null) {
            super.update("wikiknowledges", getWhereClause(knowledge), new Object[]{
                    "id", knowledge.getId(),
                    "title", knowledge.getTitle(),
                    "content", knowledge.getContent(),
                    "attachment", knowledge.getAttachment(),
                    "is_deprecated", knowledge.isDeprecated(),
                    "is_approved", knowledge.isApproved(),
                    "view_permission_level_id", knowledge.getViewPermissionLevel().getId(),
                    "change_permission_level_id", knowledge.getChangePermissionLevel().getId(),
                    "usecases", usecases.toString()
            });
        } else {
            super.update("wikiknowledges", getWhereClause(knowledge), new Object[]{
                    "id", knowledge.getId(),
                    "title", knowledge.getTitle(),
                    "content", knowledge.getContent(),
                    "attachment", knowledge.getAttachment(),
                    "is_deprecated", knowledge.isDeprecated(),
                    "is_approved", knowledge.isApproved(),
                    "usecases", usecases.toString()
            });
        }
        new TagDAO().insertKnowledgeTags(knowledge.getId(), knowledge.getTags());
    }

    public void update(QuestionKnowledge knowledge) {
        super.update(knowledge);

        super.update("questions", getWhereClause(knowledge), new Object[]{
                "id", knowledge.getId(),
                "title", knowledge.getTitle(),
                "content", knowledge.getContent()
        });
        new TagDAO().insertKnowledgeTags(knowledge.getId(), knowledge.getTags());
    }

    public void update(AnswerKnowledge knowledge) {
        super.update(knowledge);

        super.update("answers", getWhereClause(knowledge), new Object[]{
                "id", knowledge.getId(),
                "question_id", knowledge.getQuestion().getId(),
                "content", knowledge.getContent()
        });
        new TagDAO().insertKnowledgeTags(knowledge.getId(), knowledge.getTags());
    }
}
