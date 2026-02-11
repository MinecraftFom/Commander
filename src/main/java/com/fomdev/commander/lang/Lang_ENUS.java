package com.fomdev.commander.lang;

import com.fomdev.translation.api.LangUtil;
import com.fomdev.translation.api.LanguageProvider;
import com.fomdev.translation.api.Translatable;

import java.util.HashMap;
import java.util.Map;

@Translatable
public class Lang_ENUS implements LanguageProvider {
    @Override
    public Map<String, String> getTranslation() {
        return LangUtil.buildDictionaryFromLines(new HashMap<>(),
                "'tile.commander.cmdnf.err' : 'Command Not Found'"
        );
    }
}