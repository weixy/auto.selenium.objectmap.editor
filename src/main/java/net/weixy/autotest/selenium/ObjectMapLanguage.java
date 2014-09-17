package net.weixy.autotest.selenium;

import com.intellij.lang.Language;

/**
 * Created by Jim Wei on 15/09/14.
 */
public class ObjectMapLanguage extends Language {

    public static final ObjectMapLanguage INSTANCE = new ObjectMapLanguage();

    private ObjectMapLanguage() {
        super("ObjectMap");
    }

}
