package controllers;

import javang.Scope;
import knowledgebase.Letter;
import knowledgebase.LetterCatalog;
import knowledgebase.LetterPathNode;

import java.util.List;

public class LetterListController{

    @Scope
    public List<Letter> searchLetters(String title){
        return LetterCatalog.getInstance().findLetters(title);
    }

}
