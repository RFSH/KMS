package knowledge;

import account.Context;
import account.Employee;
import report.KnowledgeReportGenerator;
import tag.Tag;
import util.ValidationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Knowledge {
    private String id;
    private Employee owner;
    private List<Tag> tags;
    private Date creationDate;

    public abstract void acceptReporter(KnowledgeReportGenerator knowledgeReportGenerator);

    public Vote addVoteUpOrDown(int upOrDown) {
        VoteDAO dao = new VoteDAO();
        Vote vote = dao.getVote(Context.getInstance().getLoggedInUser().getId(), id);
        if (vote == null) {
            vote = new Vote();
            vote.setEmployee((Employee)Context.getInstance().getLoggedInUser());
            vote.setKnowledge(this);
            vote.setUpOrDown(upOrDown);
            dao.insert(vote);
        } else if (vote.getUpOrDown() != upOrDown) {
            if (vote.getUpOrDown() == 0) {
                vote.setUpOrDown(upOrDown);
            } else {
                vote.setUpOrDown(0);
            }
            dao.update(vote);
        }
        return vote;
    }

    public int getVoteSum() {
        VoteDAO dao = new VoteDAO();
        List<Vote> votes = dao.getObjects("knowledge_id", id);

        int sum = 0;
        for (Vote vote : votes) {
            sum += vote.getUpOrDown();
        }
        return sum;
    }

    public void save() throws ValidationError {
        try {
            KnowledgeCatalog.getInstance().updateKnowledge(this);
        } catch (ValidationError validationError) {
            validationError.printStackTrace();
        }
    }

    public void validate() throws ValidationError {
        if (owner == null) {
            throw new ValidationError("Knowledge must have employee");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public abstract KnowledgeType getKnowledgeType();

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }
}
