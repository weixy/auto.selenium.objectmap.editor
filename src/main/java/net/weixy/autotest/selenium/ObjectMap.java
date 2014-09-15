package net.weixy.autotest.selenium;

import com.intellij.lang.Language;

/**
 * Created by Jim Wei on 15/09/14.
 */
public class ObjectMap extends Language {

    public static final ObjectMap INSTANCE = new ObjectMap();

    private ObjectMap() {
        super("ObjectMap");
    }

}
