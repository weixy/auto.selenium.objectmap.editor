package net.weixy.autotest.selenium;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import net.weixy.autotest.selenium.psi.ObjectMapTypes;

%%

%class ObjectMapLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\n|\r|\r\n
WHITE_SPACE=[\ \t\f]
COMMENT="#".*
PROPERTY=[\w+(\.\w+)*(?=\=)]
SEPARATOR="="
BY= "id" | "xpath" | "cssselector" | "textequal" | "containtxt" | "label"
EXPRESSION=[(?:):(.*)]

%state WAITING_VALUE

%%

<YYINITIAL> {
    {COMMENT}       { System.out.println("COMMENT " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.COMMENT; }
    {PROPERTY}      { System.out.println("PROPERTY " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.PROPERTY; }
    {SEPARATOR}     { System.out.println("SEPARATOR " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.SEPARATOR; }
    {BY}            { System.out.println("BY " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.BY; }
    {EXPRESSION}    { System.out.println("EXPRESSION " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.EXPRESSION; }
}
<WAITING_VALUE> {
    {CRLF} { System.out.println("CRLF " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.NEW_LINE; }
    {WHITE_SPACE}+ { System.out.println("white space: " + yytext()); yybegin(WAITING_VALUE); /* do nothing, just skip it */ }
}