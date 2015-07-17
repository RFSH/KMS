package knowledge;

import util.Strings;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class WikiKnowledge extends Knowledge {
    private String title;
    private String content;
    private String attachment;
    private boolean isDeprecated;
    private boolean isApproved;
    List<String> useCaseList;

    public void addUseCase(String useCase) {

    }

    public void deprecate() {

    }

    public void approveOrDisapprove(boolean approve) {


    }

    public WikiKnowledge cloneWikiKnowledge() {
        return null;
    }

    @Override
    public void validate() throws ValidationError {
        super.validate();

        List<String> errors = new ArrayList<String>();
        if (title == null || title.isEmpty()) {
            errors.add(Strings.KNOWLEDGE_NO_TITLE);
        }
        if (title == null || content.isEmpty()) {
            errors.add(Strings.KNOWLEDGE_NO_CONTENT);
        }
        if (!errors.isEmpty()) {
            throw new ValidationError(errors);
        }
    }

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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
        if (useCaseList == null) {
            useCaseList = new ArrayList<>();
        }
        return useCaseList;
    }

    public void setUseCaseList(List<String> useCaseList) {
        this.useCaseList = useCaseList;
    }
}
