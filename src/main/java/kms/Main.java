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
        new JavaNGWindow(indexUrl, 800, 600, new JavaNGWindow.StateListener() {
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


}
