package knowledgebase;

import java.util.List;

public class LetterCatalog {
    private static LetterCatalog instance;

    public List<Letter> findLetters(String title){
        if(title.isEmpty()){
           return new LetterDAO().getObjects();
        }
        return new LetterDAO().getObjects("title", title);
    }

    public Letter findLetterById(String id){
        return new LetterDAO().getObject("id", id);
    }

    public static LetterCatalog getInstance() {
        if (instance == null) {
            instance = new LetterCatalog();
        }
        return instance;
    }

}
