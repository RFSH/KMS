package account;

import util.Strings;
import util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String nationalId;
    private String password;

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void sendEmail() {

    }

    public void validate() throws ValidationError {
        List<String> errors = new ArrayList<String>();
        if (id == null) {
            errors.add(Strings.USER_NO_ID);
        }
        if (firstName == null || firstName.isEmpty()) {
            errors.add(Strings.USER_NO_FIRSTNAME);
        }
        if (lastName == null || lastName.isEmpty()) {
            errors.add(Strings.USER_NO_LASTNAME);
        }
        if (email == null || email.isEmpty()) {
            errors.add(Strings.USER_NO_EMAIL);
        }
        if (nationalId == null || nationalId.isEmpty()) {
            errors.add(Strings.USER_NO_NATIONAL_ID);
        }
        if (password == null || password.isEmpty()) {
            errors.add(Strings.USER_NO_PASSWORD);
        }
        if (!errors.isEmpty()) {
            throw new ValidationError(errors);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            try {
                return ((User)obj).id.equals(id);
            } catch (NullPointerException e) {
                return false;
            }
        }
        return super.equals(obj);
    }

    /* Getters and Setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
