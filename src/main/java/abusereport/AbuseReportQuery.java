package abusereport;

import knowledge.KnowledgeType;

public class AbuseReportQuery {
    String contnet;
    KnowledgeType knowledgeType;

    /* Getter and Setters */

    public String getContent() {
        return contnet;
    }

    public void setContnet(String contnet) {
        this.contnet = contnet;
    }

    public KnowledgeType getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(KnowledgeType knowledgeType) {
        this.knowledgeType = knowledgeType;
    }
}
