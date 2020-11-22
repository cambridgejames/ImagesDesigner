package cn.powerinv.sssj.app.util;

import lombok.extern.slf4j.Slf4j;

import javafx.collections.ObservableList;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 场景主题管理器
 *
 * @author Powerinv
 * @date 2020-07-20
 */
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

    private static Map<Scene, List<String>> sceneMap = new ConcurrentHashMap<>();
    private static Theme theme;

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(Theme targetTheme) {
        theme = targetTheme;
        for (Map.Entry<Scene, List<String>> entry : sceneMap.entrySet()) {
            Scene currentScene = entry.getKey();
            ObservableList<String> stylesheets = currentScene.getStylesheets();
            stylesheets.clear();
            entry.getValue().forEach(style -> stylesheets.add(
                    ThemeUtil.class.getResource("/style/theme/" + theme.getRoute() + style).toExternalForm()
            ));
        }
    }

    public static List<String> getResourceList(Scene scene) {
        return sceneMap.get(scene);
    }

    /**
     * 向场景主题管理器注册场景
     * @param scene 场景
     * @param styleList 所需的资源列表
     */
    public static void registerScene(Scene scene, List<String> styleList) {
        List<String> styleListNew = new ArrayList<>();
        styleList.stream().filter(style -> style.endsWith(".css")).forEach(styleListNew::add);
        sceneMap.put(scene, styleListNew);
    }
}
