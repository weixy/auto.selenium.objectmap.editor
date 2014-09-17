package net.weixy.autotest.selenium;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

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
PROPERTY=[^\w+(\.\w+)*(?=\=)]
EXPRESSION=[(?:):(.*)]
SEPERATOR="="

%state WAITING_VALUE

%%

<YYINITIAL> {
    {COMMENT}       { yybegin(YYINITIAL); return ObjectMaps.COMMENT; }
    {PROPERTY}      { yybegin(YYINITIAL); return ObjectMaps.PROPERTY; }
    {EXPRESSION}    { yybegin(YYINITIAL); return ObjectMaps.EXPRESSION; }
    {SEPERATOR}     { yybegin(YYINITIAL); return ObjectMaps.SEPERATOR;}
}
<WAITING_VALUE> {
    {CRLF} {yybegin(YYINITIAL); return ObjectMaps.NEW_LINE}
    {WHITE_SPACE}+ {yybegin(YYINITIAL); /* do nothing, just skip it */}
}
