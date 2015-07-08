package account;

public class Context {
    private static Context instance;

    private User loggedInUser;

    private Context() {

    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void login(User user) {
        loggedInUser = user;
    }

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }
}
