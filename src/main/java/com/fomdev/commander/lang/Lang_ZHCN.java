package com.fomdev.commander.lang;

import com.fomdev.translation.api.LangUtil;
import com.fomdev.translation.api.LanguageProvider;
import com.fomdev.translation.api.Translatable;

import java.util.HashMap;
import java.util.Map;

@Translatable(lang = "zh_cn")
public class Lang_ZHCN implements LanguageProvider {
    @Override
    public Map<String, String> getTranslation() {
        return LangUtil.buildDictionaryFromLines(new HashMap<>(),
                "'tile.commander.cmdnf.err' : '未知的命令'"
        );
    }
}