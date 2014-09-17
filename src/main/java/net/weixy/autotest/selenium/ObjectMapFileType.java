package net.weixy.autotest.selenium;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Jim Wei on 15/09/14.
 */
public class ObjectMapFileType extends LanguageFileType {

    public static final ObjectMapFileType INSTANCE = new ObjectMapFileType();

    private ObjectMapFileType() {
        super(ObjectMapLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Object Map";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Object Map file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "map";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ObjectMapIcons.FILE;
    }
}
