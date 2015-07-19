package permission;

public class ItemPermissions {
    private boolean view;
    private boolean change;
    private boolean add;
    private boolean delete;
    private boolean deprecate;
    private boolean clone;
    private boolean report;
    private boolean vote;

    public boolean canView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean canChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public boolean canAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean canDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean canDeprecate() {
        return deprecate;
    }

    public void setDeprecate(boolean deprecate) {
        this.deprecate = deprecate;
    }

    public boolean canClone() {
        return clone;
    }

    public void setClone(boolean clone) {
        this.clone = clone;
    }

    public boolean canReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean canVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
