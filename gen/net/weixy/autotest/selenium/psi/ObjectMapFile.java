package net.weixy.autotest.selenium.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import net.weixy.autotest.selenium.ObjectMapLanguage;
import net.weixy.autotest.selenium.ObjectMapFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by Jim Wei on 17/09/14.
 */
public class ObjectMapFile extends PsiFileBase {
    public ObjectMapFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, ObjectMapLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ObjectMapFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "ObjectMap File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
