package knowledge;

import java.util.List;

public class WikiKnowledge extends Knowledge{
    private String title;
    private String content;
    private String attachement;
    private boolean isDeprecated;
    private boolean isApproved;
    private List<String> useCaseList;

    public void addUseCase(String name){
        //TODO
    }

    public void deprecate(){
        //TODO
    }

    public void approveOrDisapprove(boolean isApproved){
        //TODO
    }

    public WikiKnowledge cloneWikiKnowledge(){
        //TODO
        return null;
    }


    /* Setter and Getters */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(boolean isDeprecated) {
        this.isDeprecated = isDeprecated;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public List<String> getUseCaseList() {
        return useCaseList;
    }

    public void setUseCaseList(List<String> useCaseList) {
        this.useCaseList = useCaseList;
    }
}
