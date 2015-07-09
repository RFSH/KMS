package account;

import util.FormError;
import util.Strings;

public class UserFacade {
    private static UserFacade instance;

    private UserFacade() {

    }

    public void login(String username, String password) throws FormError {
        User user = UserCatalog.getInstance().findUser(username);
        if (user == null) {
            throw new FormError(Strings.INVALID_USERNAME);
        }
        if (! user.authenticate(password)) {
            throw new FormError(Strings.INVALID_PASSWORD);
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
