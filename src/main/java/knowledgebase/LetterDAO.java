package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetterDAO extends BaseDAO<Letter>{
    @Override
    public String getTableName() {
        return "letters";
    }

    @Override
    public Object[] getColumnValues(Letter letter) {
        return new String[]{
                "id", letter.getId(),
                "title", letter.getTitle(),
                "content", letter.getContent()
        };
    }

    @Override
    public String getWhereClause(Letter letter) {
        return "id= '"+ letter.getId()+ "'";
    }

    @Override
    public Letter getObjectFromResult(ResultSet result) {
        Letter letter = new Letter();
        try{
            letter.setId(result.getString("id"));
            letter.setTitle(result.getString("title"));
            letter.setContent(result.getString("content"));
        }catch( SQLException e){
            e.printStackTrace();
        }
        return letter;
    }
}
