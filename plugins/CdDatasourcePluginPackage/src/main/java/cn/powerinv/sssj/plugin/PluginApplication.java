package cn.powerinv.sssj.plugin;

import cn.powerinv.sssj.sdk.client.BasicConfigureInfo;

public class PluginApplication implements BasicConfigureInfo {
    private static final String PLUGIN_NAME = "工程";

    @Override
    public String geiPluginName() {
        return PLUGIN_NAME;
    }
}
