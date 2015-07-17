package knowledgebase;

import db.BaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterPathNodeDAO extends BaseDAO<LetterPathNode> {
    @Override
    public String getTableName() {
        return "letter_path_nodes";
    }

    @Override
    public Object[] getColumnValues(LetterPathNode letterPathNode) {
        return new String[]{
                "id", letterPathNode.getId(),
                "letter_id", letterPathNode.getLetter().getId(),
                "name", letterPathNode.getName()
        };
    }

    @Override
    public String getWhereClause(LetterPathNode letterPathNode) {
        return "id= '"+ letterPathNode.getId()+ "'";
    }

    @Override
    public LetterPathNode getObjectFromResult(ResultSet result) {
        LetterPathNode letterPathNode = new LetterPathNode();
        try{
            letterPathNode.setId(result.getString("id"));
            letterPathNode.setLetter(LetterCatalog.getInstance().findLetterById(result.getString("letter_id")));
            letterPathNode.setName(result.getString("name"));
        }catch( SQLException e){
            e.printStackTrace();
        }
        return letterPathNode;
    }

    public List<LetterPathNode> getLetterPathNodes(String column, String value){
        ResultSet result = query("letters JOIN letter_path_nodes ON letters.id=letter_path_nodes.letter_id", column, value);
        List<LetterPathNode> letterPathNodes = new ArrayList<LetterPathNode>();
        try{
            while(result.next()){
                letterPathNodes.add(getObjectFromResult(result));
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return letterPathNodes;
    }

}
