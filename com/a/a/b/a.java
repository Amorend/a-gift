package com.a.a.b;

import com.androlua.LuaLexer;
import com.androlua.LuaTokenTypes;
import java.io.IOException;

public class a
{
  private static int a(LuaTokenTypes paramLuaTokenTypes)
  {
    switch (1.a[paramLuaTokenTypes.ordinal()])
    {
    default: 
      return 0;
    case 8: 
    case 9: 
    case 10: 
      return -1;
    }
    return 1;
  }
  
  public static int a(CharSequence paramCharSequence)
  {
    paramCharSequence = new LuaLexer(paramCharSequence);
    i = 0;
    try
    {
      for (;;)
      {
        LuaTokenTypes localLuaTokenTypes = paramCharSequence.advance();
        if (localLuaTokenTypes == null) {
          return i;
        }
        if (paramCharSequence.yytext().equals("switch"))
        {
          i += 1;
        }
        else
        {
          int j = a(localLuaTokenTypes);
          i += j;
        }
      }
      return i;
    }
    catch (IOException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
    }
  }
  
  public static CharSequence a(CharSequence paramCharSequence, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    LuaLexer localLuaLexer = new LuaLexer(paramCharSequence);
    int j = 1;
    int i = 0;
    for (;;)
    {
      try
      {
        paramCharSequence = localLuaLexer.advance();
        if (paramCharSequence == null) {
          return localStringBuilder;
        }
        if (paramCharSequence == LuaTokenTypes.NEW_LINE)
        {
          if ((localStringBuilder.length() > 0) && (localStringBuilder.charAt(localStringBuilder.length() - 1) == ' ')) {
            localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
          }
          localStringBuilder.append('\n');
          i = Math.max(0, i);
          j = 1;
          continue;
        }
        if (j != 0) {}
        char[] arrayOfChar;
        switch (1.a[paramCharSequence.ordinal()])
        {
        case 11: 
          arrayOfChar = a(i * paramInt);
          break;
        case 16: 
        case 17: 
          paramCharSequence = localLuaLexer.yytext();
          localStringBuilder.append(paramCharSequence);
          break;
        case 12: 
        case 13: 
        case 14: 
        case 15: 
          localStringBuilder.append(a(i * paramInt - paramInt / 2));
          paramCharSequence = localLuaLexer.yytext();
          break;
        case 8: 
        case 9: 
        case 10: 
          i -= 1;
          localStringBuilder.append(a(i * paramInt));
          paramCharSequence = localLuaLexer.yytext();
          continue;
          localStringBuilder.append(arrayOfChar);
          localStringBuilder.append(localLuaLexer.yytext());
          i += a(paramCharSequence);
          break label328;
          if (paramCharSequence == LuaTokenTypes.WHITE_SPACE)
          {
            localStringBuilder.append(' ');
            continue;
          }
          localStringBuilder.append(localLuaLexer.yytext());
          int k = a(paramCharSequence);
          i += k;
          break;
        default: 
          continue;
        }
      }
      catch (IOException paramCharSequence)
      {
        paramCharSequence.printStackTrace();
        return localStringBuilder;
      }
      label328:
      j = 0;
    }
  }
  
  private static char[] a(int paramInt)
  {
    int i = 0;
    if (paramInt < 0) {
      return new char[0];
    }
    char[] arrayOfChar = new char[paramInt];
    while (i < paramInt)
    {
      arrayOfChar[i] = ' ';
      i += 1;
    }
    return arrayOfChar;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */