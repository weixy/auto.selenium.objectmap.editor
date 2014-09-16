package net.weixy.autotest.selenium;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Jim Wei on 16/09/14.
 */
public class ObjectMapElementType extends IElementType {
    public ObjectMapElementType(@NotNull @NonNls String debugName) {
        super(debugName, ObjectMap.INSTANCE);
    }
}
