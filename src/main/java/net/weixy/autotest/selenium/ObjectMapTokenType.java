package net.weixy.autotest.selenium;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Jim Wei on 16/09/14.
 */
public class ObjectMapTokenType extends IElementType {
    public ObjectMapTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ObjectMap.INSTANCE);
    }

    @Override
    public String toString() {
        return "ObjectMapTokenType." + super.toString();
    }
}
