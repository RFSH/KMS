package controllers;

import javang.Scope;
import knowledge.KnowledgeCatalog;
import knowledge.QuestionKnowledge;
import knowledge.WikiKnowledge;
import report.TagReportGenerator;

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

        System.out.println(tagReportGenerator.getTags());

        return tagReportGenerator;
    }
}
