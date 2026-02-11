package com.fomdev.commander.lang;

import com.fomdev.translation.api.LangUtil;
import com.fomdev.translation.api.LanguageProvider;
import com.fomdev.translation.api.Translatable;

import java.util.HashMap;
import java.util.Map;

@Translatable(lang = "ne_ko")
public class Lang_NEKO implements LanguageProvider {
    @Override
    public Map<String, String> getTranslation() {
        return LangUtil.buildDictionaryFromLines(new HashMap<>(),
                "'tile.commander.cmdnf.err' : 'X~~~X，小猫没有找到命令呢，主人～'"
        );
    }
}