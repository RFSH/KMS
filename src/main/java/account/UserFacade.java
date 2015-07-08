package account;

import util.FormError;

public class UserFacade {
    private static UserFacade instance;

    private UserFacade() {

    }

    public void login(String username, String password) throws FormError {
        User user = UserCatalog.getInstance().findUser(username);
        if (user == null) {
            throw new FormError("Invalid username");
        }
        if (! user.authenticate(password)) {
            throw new FormError("Invalid password");
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
