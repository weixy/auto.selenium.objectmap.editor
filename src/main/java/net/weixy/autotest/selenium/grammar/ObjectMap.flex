package net.weixy.autotest.selenium.grammar;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import net.weixy.autotest.selenium.psi.ObjectMapTypes;

%%
%public
%class ObjectMapLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\n|\r|\r\n
WHITE_SPACE=[\ \t\f]
COMMENT="#".*(\n|\r|\r\n)
PROPERTY=[a-z0-9A-Z]+("."[a-z0-9A-Z]+)*
SEPARATOR="="
BY= "id" | "xpath" | "cssselector" | "textequal" | "containtxt" | "label"
EXPRESSION=":".*

%state PARSE_OBJECT

%%

    <YYINITIAL> {COMMENT}           { System.out.println("COMMENT " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.COMMENT; }
    <YYINITIAL>    {PROPERTY}      { System.out.println("PROPERTY " + yytext()); yybegin(PARSE_OBJECT); return ObjectMapTypes.PROPERTY; }
    <PARSE_OBJECT> {
        {SEPARATOR}         { System.out.println("SEPARATOR " + yytext()); yybegin(PARSE_OBJECT); return ObjectMapTypes.SEPARATOR; }
        {BY}                { System.out.println("BY " + yytext()); yybegin(PARSE_OBJECT); return ObjectMapTypes.BY; }
        {EXPRESSION}        { System.out.println("EXPRESSION " + yytext()); yybegin(PARSE_OBJECT); return ObjectMapTypes.EXPRESSION; }
        {CRLF}          { System.out.println("CRLF"); yybegin(YYINITIAL); return ObjectMapTypes.CRLF; }
        {WHITE_SPACE}+  { System.out.println("WHITE_SPACE"); yybegin(PARSE_OBJECT); return TokenType.WHITE_SPACE; }
    }
    <YYINITIAL> {CRLF}          { System.out.println("CRLF"); yybegin(YYINITIAL); return ObjectMapTypes.CRLF; }
    {WHITE_SPACE}+  { System.out.println("WHITE_SPACE"); yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    .               { return TokenType.BAD_CHARACTER; }


    //<YYINITIAL> {PROPERTY}      { System.out.println("PROPERTY " + yytext());  return ObjectMapTypes.PROPERTY; }
    //{SEPARATOR}     { System.out.println("SEPARATOR " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.SEPARATOR; }
    //{BY}            { System.out.println("BY " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.BY; }
    //{EXPRESSION}    { System.out.println("EXPRESSION " + yytext()); yybegin(YYINITIAL); return ObjectMapTypes.EXPRESSION; }

