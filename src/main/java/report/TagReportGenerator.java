package report;

import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;
import tag.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagReportGenerator implements KnowledgeReportGenerator {
    private Map<String, Integer> tags;

    public TagReportGenerator() {
        this.tags = new HashMap<>();
    }

    public void visitWikiKnowldege(WikiKnowledge wikiKnowledge){
        for(Tag tag: wikiKnowledge.getTags()){
            addTag(tag.getName());
        }
    }

    public void visitQuestionKnowldege(QuestionKnowledge questionKnowledge){
        for(Tag tag: questionKnowledge.getTags()){
            addTag(tag.getName());
        }
    }

    private void addTag(String name){
        if(tags.containsKey(name)){
            tags.put(name, tags.get(name)+1);
        }else{
            tags.put(name, 1);
        }
    }

    public Map<String, Integer> getTags() {
        if(tags == null)
            tags = new HashMap<>();
        return tags;
    }

    public List<String> keyList() {
        return new ArrayList<>(tags.keySet());
    }
}
