package cn.powerinv.sssj.app.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 插件加载类
 *
 * @since 2021-02-26
 */
public final class PluginLoader {
    private static final String PLUGIN_BASE_DIR = "lib";
    private static final String PLUGIN_NAME_ENDING = ".jar";

    private PluginLoader() {
    }

    /**
     * 从指定的目录中加载插件
     */
    public static List<String> loadPlugins() {
        File pluginDir = new File(System.getProperty("user.dir"), PLUGIN_BASE_DIR);
        if (!pluginDir.isDirectory()) {
            return new ArrayList<>();
        }
        File[] fileList = pluginDir.listFiles();
        if (fileList == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(fileList)
                .filter(File::isFile)
                .filter(item -> item.getName().endsWith(PLUGIN_NAME_ENDING))
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
