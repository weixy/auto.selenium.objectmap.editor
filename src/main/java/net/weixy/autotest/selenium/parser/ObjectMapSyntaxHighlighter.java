package net.weixy.autotest.selenium.parser;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import net.weixy.autotest.selenium.grammar.ObjectMapLexer;
import net.weixy.autotest.selenium.psi.ObjectMapTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Reader;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * Created by Jim Wei on 18/09/14.
 */
public class ObjectMapSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey COMMENT = createTextAttributesKey("OBJMAP_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey PROPERTY = createTextAttributesKey("OBJMAP_PROPERTY", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("OBJMAP_SEPARATOR", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    public static final TextAttributesKey BY = createTextAttributesKey("OBJMAP_BY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey EXPRESSION = createTextAttributesKey("OBJMAP_EXPRESSION", DefaultLanguageHighlighterColors.STRING);
    static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("OBJMAP_BAD_CHARACTER", new TextAttributes(Color.RED, null, null, null, Font.BOLD));

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[] {BAD_CHARACTER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[] {COMMENT};
    private static final TextAttributesKey[] PROPERTY_KEYS = new TextAttributesKey[] {PROPERTY};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[] {SEPARATOR};
    private static final TextAttributesKey[] BY_KEYS = new TextAttributesKey[] {BY};
    private static final TextAttributesKey[] EXPRESSION_KEYS = new TextAttributesKey[] {EXPRESSION};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new ObjectMapLexer((Reader)null));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        if (iElementType.equals(ObjectMapTypes.PROPERTY)) {
            return PROPERTY_KEYS;
        } else if (iElementType.equals(ObjectMapTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (iElementType.equals(ObjectMapTypes.BY)) {
            return BY_KEYS;
        } else if (iElementType.equals(ObjectMapTypes.EXPRESSION)) {
            return EXPRESSION_KEYS;
        } else if (iElementType.equals(ObjectMapTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (iElementType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
