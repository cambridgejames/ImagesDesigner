package cn.powerinv.sssj.util;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.text.MessageFormat;

@Slf4j
public class ThemeUtil {
    public enum Theme {
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

    public static URL getStyleResource(String resourcePath) {
        String url = "/style/theme/" + theme.getRoute() + resourcePath;
        log.warn(MessageFormat.format("Load resource file: {0}", url));
        return ThemeUtil.class.getResource(url);
    }
}
