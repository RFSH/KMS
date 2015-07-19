package controllers;

import account.Context;
import account.Employee;
import account.Manager;
import account.UserFacade;
import javang.JavaNGController;
import javang.Scope;
import kms.MenuCreator;
import util.ValidationError;

public class LoginController extends JavaNGController {

    @Scope
    public void init() {
        hideMenu();
    }

    @Scope
    public String login(String username, String password) {
        try {
            UserFacade.getInstance().login(username, password);
            changePage("/knowledge/list");

            if (Context.getInstance().getLoggedInUser() instanceof Employee) {
                setMenu(MenuCreator.createEmployeeMenu());
            } else if (Context.getInstance().getLoggedInUser() instanceof Manager) {
                setMenu(MenuCreator.createManagerMenu());
            }
            return "";
        } catch (ValidationError formError) {
            return formError.getError();
        }
    }

}
