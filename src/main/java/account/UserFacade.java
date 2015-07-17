package account;

import util.ValidationError;
import util.Strings;

public class UserFacade {
    private static UserFacade instance;

    private UserFacade() {

    }

    public void login(String username, String password) throws ValidationError {
        User user = UserCatalog.getInstance().findUser(username);
        if (user == null) {
            throw new ValidationError(Strings.INVALID_USERNAME);
        }
        if (! user.authenticate(password)) {
            throw new ValidationError(Strings.INVALID_PASSWORD);
        }

        if (user.getUsername().equals("admin")) {
            user = UserCatalog.getInstance().getManager();
        } else {
            user = UserCatalog.getInstance().findEmployeeById(user.getId());
        }

        Context.getInstance().login(user);
    }

    public static UserFacade getInstance() {
        if (instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

}
