package report;

import knowledge.AnswerKnowledge;
import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;

import java.util.*;

public class TimeReportGenerator implements KnowledgeReportGenerator {
    private int[] wikis;
    private int[] questions;
    private Map<Integer, Integer> wikiCount;
    private Map<Integer, Integer> questionCount;
    public TimeReportGenerator() {
        this.wikis = new int[7];
        this.questions = new int[7];
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

    public Map<Integer, Integer> getWikis() {
        return wikiCount;
    }

    public Map<Integer, Integer> getQuestions() {
        return questionCount;
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
//    public List<ReportItem> getWikis(){
//        List<ReportItem> items = new ArrayList<>();
//        for (Integer day : wikiCount.keySet()) {
//            Calendar date = Calendar.getInstance();
//            date.set(Calendar.DAY_OF_YEAR, day);
//
//            ReportItem item = new ReportItem();
//            item.date = date.getTime();
//            item.count = wikiCount.get(day);
//
//            items.add(item);
//        }
//        return items;
//    }
//
//    public List<ReportItem> getQuestions(){
//        List<ReportItem> items = new ArrayList<>();
//        for (Integer day : questionCount.keySet()) {
//            Calendar date = Calendar.getInstance();
//            date.set(Calendar.DAY_OF_YEAR, day);
//
//            ReportItem item = new ReportItem();
//            item.date = date.getTime();
//            item.count = questionCount.get(day);
//
//            items.add(item);
//        }
//        return items;
//    }

    public static class ReportItem {
        public Date date;
        public int count;
    }
}
