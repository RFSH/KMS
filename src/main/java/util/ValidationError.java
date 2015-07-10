package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValidationError extends Exception {
    private List<String> errors;

    private ValidationError() {
        errors = new ArrayList<>();
    }

    public ValidationError(String... errors) {
        this();
        for (String message : errors) {
            this.errors.add(message);
        }
    }

    public ValidationError(Collection<String> errors) {
        this();
        this.errors.addAll(errors);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getError() {
        if (errors.size() > 0) {
            return errors.get(0);
        }
        return null;
    }

}
