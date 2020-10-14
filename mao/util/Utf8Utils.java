package mao.util;

import java.io.IOException;
import java.io.Writer;

public final class Utf8Utils
{
  private static char[] tempBuffer = (char[])null;
  
  private static int digit(char paramChar)
  {
    int i = Character.digit(paramChar, 16);
    if ((i < 0) || (i > 15)) {
      throw new IllegalArgumentException("illegal not hex character: " + paramChar);
    }
    return i;
  }
  
  public static String escapeSequence(String paramString)
  {
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    if (i >= j) {
      return localStringBuilder.toString();
    }
    char c = paramString.charAt(i);
    if (c == '\\')
    {
      if (i >= j - 1) {
        throw new IllegalArgumentException("escape Sequence error: " + c);
      }
      i += 1;
      switch (paramString.charAt(i))
      {
      default: 
        throw new IllegalArgumentException("escape Sequence error: " + paramString.substring(i - 1));
      case 'b': 
        localStringBuilder.append('\b');
      }
    }
    for (;;)
    {
      i += 1;
      break;
      localStringBuilder.append('\t');
      continue;
      localStringBuilder.append('\n');
      continue;
      localStringBuilder.append('\r');
      continue;
      localStringBuilder.append('\'');
      continue;
      localStringBuilder.append('"');
      continue;
      localStringBuilder.append('\\');
      continue;
      int k = i + 1;
      if (k > j - 4) {
        throw new IllegalArgumentException("unicode error: " + paramString.substring(k - 2));
      }
      i = k + 1;
      k = digit(paramString.charAt(k));
      int m = i + 1;
      int n = digit(paramString.charAt(i));
      i = m + 1;
      localStringBuilder.append((char)(n << 8 | k << 12 | digit(paramString.charAt(m)) << 4 | digit(paramString.charAt(i))));
      continue;
      localStringBuilder.append(c);
    }
  }
  
  public static String escapeString(String paramString)
  {
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j * 3 / 2);
    int i = 0;
    if (i >= j) {
      return localStringBuilder.toString();
    }
    char c = paramString.charAt(i);
    if ((c >= ' ') && (c < ''))
    {
      if ((c == '\'') || (c == '"') || (c == '\\')) {
        localStringBuilder.append('\\');
      }
      localStringBuilder.append(c);
    }
    for (;;)
    {
      i += 1;
      break;
      if (c <= '') {}
      switch (c)
      {
      case '\013': 
      case '\f': 
      default: 
        localStringBuilder.append(c);
        break;
      case '\n': 
        localStringBuilder.append("\\n");
        break;
      case '\r': 
        localStringBuilder.append("\\r");
        break;
      case '\t': 
        localStringBuilder.append("\\t");
      }
    }
  }
  
  public static byte[] stringToUtf8Bytes(String paramString)
  {
    int k = paramString.length();
    byte[] arrayOfByte = new byte[k * 3];
    int j = 0;
    int i = 0;
    if (j >= k)
    {
      paramString = new byte[i];
      System.arraycopy(arrayOfByte, 0, paramString, 0, i);
      return paramString;
    }
    int m = paramString.charAt(j);
    if ((m != 0) && (m < 128))
    {
      arrayOfByte[i] = ((byte)m);
      i += 1;
    }
    for (;;)
    {
      j += 1;
      break;
      if (m < 2048)
      {
        arrayOfByte[i] = ((byte)(m >> 6 & 0x1F | 0xC0));
        arrayOfByte[(i + 1)] = ((byte)(m & 0x3F | 0x80));
        i += 2;
      }
      else
      {
        arrayOfByte[i] = ((byte)(m >> 12 & 0xF | 0xE0));
        arrayOfByte[(i + 1)] = ((byte)(m >> 6 & 0x3F | 0x80));
        arrayOfByte[(i + 2)] = ((byte)(m & 0x3F | 0x80));
        i += 3;
      }
    }
  }
  
  private static String throwBadUtf8(int paramInt1, int paramInt2)
  {
    throw new IllegalArgumentException("bad utf-8 byte ");
  }
  
  public static String utf8BytesToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((tempBuffer == null) || (tempBuffer.length < paramInt2)) {
      tempBuffer = new char[paramInt2];
    }
    char[] arrayOfChar = tempBuffer;
    int j = 0;
    if (paramInt2 <= 0) {
      return new String(arrayOfChar, 0, j);
    }
    int m = paramArrayOfByte[paramInt1] & 0xFF;
    int i;
    int k;
    switch (m >> 4)
    {
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    default: 
      return throwBadUtf8(m, paramInt1);
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
      paramInt2 -= 1;
      if (m == 0) {
        return throwBadUtf8(m, paramInt1);
      }
      i = (char)m;
      k = paramInt1 + 1;
      paramInt1 = paramInt2;
      paramInt2 = k;
    }
    for (;;)
    {
      arrayOfChar[j] = i;
      j += 1;
      k = paramInt1;
      paramInt1 = paramInt2;
      paramInt2 = k;
      break;
      k = paramInt2 - 2;
      if (k < 0) {
        return throwBadUtf8(m, paramInt1);
      }
      paramInt2 = paramArrayOfByte[(paramInt1 + 1)] & 0xFF;
      if ((paramInt2 & 0xC0) != 128) {
        return throwBadUtf8(paramInt2, paramInt1 + 1);
      }
      m = (m & 0x1F) << 6 | paramInt2 & 0x3F;
      if ((m != 0) && (m < 128)) {
        return throwBadUtf8(paramInt2, paramInt1 + 1);
      }
      i = (char)m;
      paramInt2 = paramInt1 + 2;
      paramInt1 = k;
      continue;
      k = paramInt2 - 3;
      if (k < 0) {
        return throwBadUtf8(m, paramInt1);
      }
      int n = paramArrayOfByte[(paramInt1 + 1)] & 0xFF;
      if ((n & 0xC0) != 128) {
        return throwBadUtf8(n, paramInt1 + 1);
      }
      paramInt2 = paramArrayOfByte[(paramInt1 + 2)] & 0xFF;
      if ((n & 0xC0) != 128) {
        return throwBadUtf8(paramInt2, paramInt1 + 2);
      }
      m = (m & 0xF) << 12 | (n & 0x3F) << 6 | paramInt2 & 0x3F;
      if (m < 2048) {
        return throwBadUtf8(paramInt2, paramInt1 + 2);
      }
      i = (char)m;
      paramInt2 = paramInt1 + 3;
      paramInt1 = k;
    }
  }
  
  public static void writeEscapedChar(Writer paramWriter, char paramChar)
    throws IOException
  {
    if ((paramChar >= ' ') && (paramChar < ''))
    {
      if ((paramChar == '\'') || (paramChar == '"') || (paramChar == '\\')) {
        paramWriter.write(92);
      }
      paramWriter.write(paramChar);
      return;
    }
    if (paramChar <= '') {}
    switch (paramChar)
    {
    case '\013': 
    case '\f': 
    default: 
      paramWriter.write("\\u");
      paramWriter.write(Character.forDigit(paramChar >> '\f', 16));
      paramWriter.write(Character.forDigit(paramChar >> '\b' & 0xF, 16));
      paramWriter.write(Character.forDigit(paramChar >> '\004' & 0xF, 16));
      paramWriter.write(Character.forDigit(paramChar & 0xF, 16));
      return;
    case '\n': 
      paramWriter.write("\\n");
      return;
    case '\r': 
      paramWriter.write("\\r");
      return;
    }
    paramWriter.write("\\t");
  }
  
  public static void writeEscapedString(Writer paramWriter, String paramString)
    throws IOException
  {
    int i = 0;
    if (i >= paramString.length()) {
      return;
    }
    int j = paramString.charAt(i);
    if ((j >= 32) && (j < 127))
    {
      if ((j == 39) || (j == 34) || (j == 92)) {
        paramWriter.write(92);
      }
      paramWriter.write(j);
    }
    for (;;)
    {
      i += 1;
      break;
      if (j <= 127) {}
      switch (j)
      {
      case 11: 
      case 12: 
      default: 
        paramWriter.write(j);
        break;
      case 10: 
        paramWriter.write("\\n");
        break;
      case 13: 
        paramWriter.write("\\r");
        break;
      case 9: 
        paramWriter.write("\\t");
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\util\Utf8Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */