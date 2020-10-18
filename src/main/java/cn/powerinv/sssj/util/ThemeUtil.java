package cn.powerinv.sssj.util;

import javafx.scene.Scene;

public class ThemeUtil {
    public static enum Theme {
        DARCULAR("Darcular", "darcular");

        private String name;
        private String route;

        Theme(String name, String route) {
            this.name = name;
            this.route = route;
        }

        public String getName() {
            return name;
        }

        public String getRoute() {
            return route;
        }
    }

    public static Theme theme;

    public static String getResourceRoot() {
        return "/themes/" + theme.getRoute();
    }
}
