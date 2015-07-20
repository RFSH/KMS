package controllers;

import javang.Scope;
import knowledge.*;
import report.TagReportGenerator;
import report.TimeReportGenerator;

import java.util.Date;
import java.util.List;

public class ReportController {

    @Scope
    public TagReportGenerator getTagsReport() {
        TagReportGenerator tagReportGenerator = new TagReportGenerator();
        List<WikiKnowledge> wikiKnowledges = KnowledgeCatalog.getInstance().findWikiKnowledges(null);
        List<QuestionKnowledge> questionKnowledges = KnowledgeCatalog.getInstance().findQuestionKnowledges(null);

        for (WikiKnowledge wikiKnowledge : wikiKnowledges) {
            wikiKnowledge.acceptReporter(tagReportGenerator);
        }

        for (QuestionKnowledge questionKnowledge : questionKnowledges) {
            questionKnowledge.acceptReporter(tagReportGenerator);
        }

        return tagReportGenerator;
    }

    @Scope
    public TimeReportGenerator getTimeReport() {
        TimeReportGenerator timeReportGenerator = new TimeReportGenerator();

        int DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date sevenDaysAgo = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        sevenDaysAgo.setHours(0);
        sevenDaysAgo.setMinutes(0);
        sevenDaysAgo.setSeconds(0);

        WikiKnowledgeQuery wikiQuery = new WikiKnowledgeQuery();
        wikiQuery.setFromDate(sevenDaysAgo);
        List<WikiKnowledge> wikiKnowledges = KnowledgeCatalog.getInstance().findWikiKnowledges(wikiQuery);

        QuestionKnowledgeQuery questionQuery = new QuestionKnowledgeQuery();
        questionQuery.setFromDate(sevenDaysAgo);
        List<QuestionKnowledge> questionKnowledges = KnowledgeCatalog.getInstance().findQuestionKnowledges(questionQuery);

        for (WikiKnowledge wikiKnowledge : wikiKnowledges) {
            wikiKnowledge.acceptReporter(timeReportGenerator);
        }

        for (QuestionKnowledge questionKnowledge : questionKnowledges) {
            questionKnowledge.acceptReporter(timeReportGenerator);
        }

        return timeReportGenerator;
    }

}
