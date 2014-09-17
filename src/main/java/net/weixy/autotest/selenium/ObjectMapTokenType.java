package net.weixy.autotest.selenium;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Jim Wei on 16/09/14.
 */
public class ObjectMapTokenType extends IElementType {
    public ObjectMapTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ObjectMapLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ObjectMapTokenType." + super.toString();
    }
}
