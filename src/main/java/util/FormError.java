package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormError extends Exception {
    private List<String> errors;

    private FormError() {
        errors = new ArrayList<>();
    }

    public FormError(String... errors) {
        this();
        for (String message : errors) {
            this.errors.add(message);
        }
    }

    public FormError(Collection<String> errors) {
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
