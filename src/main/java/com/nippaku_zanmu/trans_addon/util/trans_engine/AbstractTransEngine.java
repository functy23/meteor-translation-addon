package com.nippaku_zanmu.trans_addon.util.trans_engine;

import com.nippaku_zanmu.trans_addon.util.TransUtil;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Module;

public abstract class AbstractTransEngine implements IKeyGenerate {

    public String transModuleName(Module module){
        return TransUtil.trans(getModuleNameKey(module),module.name);
    }
    public String transModuleDescription(Module module){
        return TransUtil.trans(getModuleDescriptionKey(module),module.description);
    }
    public String transSettingName(Module module, SettingGroup group, Setting s){
        //新版本流星会调整设置分组名，找不到时回退到 general 组
        return TransUtil.transAny(s.name,
            getSettingNameKey(module, group, s),
            getFallbackSettingNameKey(module, group, s));
    }
    public String transSettingDes(Module module, SettingGroup group, Setting s){
        return TransUtil.transAny(s.description,
            getSettingDesKey(module, group, s),
            getFallbackSettingDesKey(module, group, s));
    }

    /**
     * 分组名变化时的兜底 key，默认使用 general 分组
     */
    protected String getFallbackSettingNameKey(Module module, SettingGroup group, Setting<?> s) {
        return getSettingNameKey(module, "general", s.name);
    }

    protected String getFallbackSettingDesKey(Module module, SettingGroup group, Setting<?> s) {
        return getSettingDesKey(module, "general", s.name);
    }
}
