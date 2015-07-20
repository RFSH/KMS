package report;

import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;

import java.util.*;

public class TimeReportGenerator implements KnowledgeReportGenerator {
    private Map<Integer, Integer> wikiCount;
    private Map<Integer, Integer> questionCount;
    public TimeReportGenerator() {
        wikiCount = new HashMap<>();
        questionCount = new HashMap<>();
    }

    public void visitWikiKnowldege(WikiKnowledge wikiKnowledge){
        Calendar then = Calendar.getInstance();
        then.setTime(wikiKnowledge.getCreationDate());
        int day = then.get(Calendar.DAY_OF_YEAR);
        Integer count = wikiCount.get(day);
        if (count == null) {
            count = 0;
        }
        count ++;
        wikiCount.put(day, count);
    }

    public void visitQuestionKnowldege(QuestionKnowledge questionKnowledge){
        Calendar then = Calendar.getInstance();
        then.setTime(questionKnowledge.getCreationDate());
        int day = then.get(Calendar.DAY_OF_YEAR);
        Integer count = questionCount.get(day);
        if (count == null) {
            count = 0;
        }
        count ++;
        questionCount.put(day, count);
    }

    public List<Integer> getAllDays() {
        Set<Integer> days = new HashSet<>();
        days.addAll(wikiCount.keySet());
        days.addAll(questionCount.keySet());
        List<Integer> dayList = new ArrayList<>(days) ;
        Collections.sort(dayList);
        return dayList;
    }

    public int getWikiCount(int day) {
        Integer count = wikiCount.get(day);
        if (count == null) {
            count = 0;
        }
        return count;
    }

    public int getQuestionCount(int day) {
        Integer count = questionCount.get(day);
        if (count == null) {
            count = 0;
        }
        return count;
    }

}
