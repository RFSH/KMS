package controllers;

import account.UserFacade;
import javang.JavaNGController;
import javang.Scope;
import util.ValidationError;

public class LoginController extends JavaNGController {

    @Scope
    public String login(String username, String password) {
        try {
            UserFacade.getInstance().login(username, password);
            changePage("/knowledge/list");
            return "";
        } catch (ValidationError formError) {
            return formError.getError();
        }
    }

}
