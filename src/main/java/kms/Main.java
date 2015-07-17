package kms;

import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEvent;
import javang.JavaNGMenu;
import javang.JavaNGMenuHandler;
import javang.JavaNGMenuItem;
import javang.JavaNGWindow;

public class Main {
    public static void main(String[] args) {

        String indexUrl = Main.class.getClassLoader().getResource("index.html").toExternalForm();
//		indexUrl = "http://localhost:9000/";
        new JavaNGWindow(indexUrl, 800, 600, createMenu(), new JavaNGWindow.StateListener() {
            @Override
            public void onStageInit(JavaNGWindow window) {
                window.getWebEngine().onAlertProperty().set(new EventHandler<WebEvent<String>>() {
                    @Override
                    public void handle(WebEvent<String> event) {
                        System.out.println("ALEERT " + event.getData());
                    }
                });
            }

            @Override
            public void onReady(JavaNGWindow window) {
                System.out.println("READY");
            }
        });
    }

    private static JavaNGMenu createMenu() {
        JavaNGMenu root = new JavaNGMenu("");

        JavaNGMenu menuProfile = new JavaNGMenu("حساب کاربری");
        menuProfile.addItem(new JavaNGMenuItem("ورود", new MenuActionHandler("/")));

        JavaNGMenu menuAdmin = new JavaNGMenu("مدیریت");
        JavaNGMenu adminEmployees = new JavaNGMenu("کارمندان");
        adminEmployees.addItem(new JavaNGMenuItem("فهرست کارمندان", new MenuActionHandler("/employee/list")));
        adminEmployees.addItem(new JavaNGMenuItem("ایجاد کارمند جدید", new MenuActionHandler("/employee/create")));
        menuAdmin.addItem(adminEmployees);
        JavaNGMenu adminReport = new JavaNGMenu("گزارشات");
        adminReport.addItem(new JavaNGMenuItem("گزارش تخلفات", new MenuActionHandler("/admin/abuse-list")));
        adminReport.addItem(new JavaNGMenuItem("گزارش رخداد‌های اخیر", new MenuActionHandler("/report/activities")));
        adminReport.addItem(new JavaNGMenuItem("گزارش فرآورده‌های دانشی", new MenuActionHandler("/report/tag")));
        menuAdmin.addItem(adminReport);
        menuAdmin.getItems().add(new JavaNGMenuItem("تنظیمات", new MenuActionHandler("/admin/settings")));

        JavaNGMenu menuKnowledge = new JavaNGMenu("دانش‌");

        menuKnowledge.addItem(new JavaNGMenuItem("فهرست دانش ها", new MenuActionHandler("/knowledge/list")));
        menuKnowledge.addItem(new JavaNGMenuItem("ایجاد دانش جدید", new MenuActionHandler("/knowledge/create")));

        JavaNGMenu menuQA = new JavaNGMenu("سوال");
        menuQA.addItem(new JavaNGMenuItem("فهرست سوال ها", new MenuActionHandler("/question/list")));
        menuQA.addItem(new JavaNGMenuItem("ایجاد سوال جدید", new MenuActionHandler("/question/create")));

        JavaNGMenu menuKDBM = new JavaNGMenu("مدیریت پایگاه دانش");
        menuKDBM.addItem(new JavaNGMenuItem("پروژه‌ها", new MenuActionHandler("/kdbm/projects")));
        menuKDBM.addItem(new JavaNGMenuItem("پیغام‌ها", new MenuActionHandler("/kdbm/mails")));


        root.addItem(menuProfile);
        root.addItem(menuAdmin);
        root.addItem(menuKnowledge);
        root.addItem(menuQA);
        root.addItem(menuKDBM);
        return root;
    }

    private static class MenuActionHandler extends JavaNGMenuHandler {
        private String mPage;

        public MenuActionHandler(String page) {
            mPage = page;
        }

        @Override
        public void onMenuClick() {
            changePage(mPage);
        }
    }
}
