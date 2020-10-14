package apksigner;

import java.io.IOException;

public class Base64
{
  private static final char[] ALPHABET;
  private static int[] valueDecoding;
  
  static
  {
    int j = 0;
    ALPHABET = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
    valueDecoding = new int[''];
    int i = 0;
    if (i >= valueDecoding.length) {
      i = j;
    }
    for (;;)
    {
      if (i >= ALPHABET.length)
      {
        return;
        valueDecoding[i] = -1;
        i += 1;
        break;
      }
      valueDecoding[ALPHABET[i]] = i;
      i += 1;
    }
  }
  
  public static byte[] decode(String paramString)
    throws IOException
  {
    return decode(paramString, 0, paramString.length());
  }
  
  public static byte[] decode(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int j = 0;
    if (paramInt2 % 4 != 0) {
      throw new IOException("Base64 string length is not multiple of 4");
    }
    int k = paramInt2 / 4 * 3;
    int i = k;
    if (paramString.charAt(paramInt1 + paramInt2 - 1) == '=')
    {
      k -= 1;
      i = k;
      if (paramString.charAt(paramInt1 + paramInt2 - 2) == '=') {
        i = k - 1;
      }
    }
    byte[] arrayOfByte = new byte[i];
    i = 0;
    for (;;)
    {
      if (i >= paramInt2) {
        return arrayOfByte;
      }
      decodeQuantum(paramString.charAt(paramInt1 + i), paramString.charAt(paramInt1 + i + 1), paramString.charAt(paramInt1 + i + 2), paramString.charAt(paramInt1 + i + 3), arrayOfByte, j);
      j += 3;
      i += 4;
    }
  }
  
  private static void decodeQuantum(char paramChar1, char paramChar2, char paramChar3, char paramChar4, byte[] paramArrayOfByte, int paramInt)
    throws IOException
  {
    char c = '\000';
    int i = valueDecoding[(paramChar1 & 0x7F)];
    int j = valueDecoding[(paramChar2 & 0x7F)];
    if (paramChar4 == '=') {
      if (paramChar3 == '=')
      {
        paramChar1 = '\002';
        paramChar2 = '\000';
        paramChar3 = c;
      }
    }
    while ((i < 0) || (j < 0) || (paramChar2 < 0) || (paramChar3 < 0))
    {
      throw new IOException("Invalid character in Base64 string");
      paramChar2 = valueDecoding[(paramChar3 & 0x7F)];
      paramChar1 = '\001';
      paramChar3 = c;
      continue;
      paramChar2 = valueDecoding[(paramChar3 & 0x7F)];
      paramChar3 = valueDecoding[(paramChar4 & 0x7F)];
      paramChar1 = '\000';
    }
    paramArrayOfByte[paramInt] = ((byte)(i << 2 & 0xFC | j >>> 4 & 0x3));
    if (paramChar1 < '\002')
    {
      paramArrayOfByte[(paramInt + 1)] = ((byte)(j << 4 & 0xF0 | paramChar2 >>> '\002' & 0xF));
      if (paramChar1 < '\001') {
        paramArrayOfByte[(paramInt + 2)] = ((byte)(paramChar2 << '\006' & 0xC0 | paramChar3 & 0x3F));
      }
    }
  }
  
  public static String encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static String encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0;
    char[] arrayOfChar = new char[(paramInt2 + 2) / 3 * 4];
    int j = 0;
    for (;;)
    {
      if (i >= arrayOfChar.length) {
        return new String(arrayOfChar);
      }
      encodeQuantum(paramArrayOfByte, paramInt1 + j, paramInt2 - j, arrayOfChar, i);
      j += 3;
      i += 4;
    }
  }
  
  private static void encodeQuantum(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    int i = (byte)0;
    int k = (byte)0;
    int j = (byte)0;
    i = paramArrayOfByte[paramInt1];
    paramArrayOfChar[paramInt3] = ALPHABET[(i >>> 2 & 0x3F)];
    if (paramInt2 > 2)
    {
      paramInt2 = paramArrayOfByte[(paramInt1 + 1)];
      paramInt1 = paramArrayOfByte[(paramInt1 + 2)];
      paramArrayOfChar[(paramInt3 + 1)] = ALPHABET[((i << 4 & 0x30) + (paramInt2 >>> 4 & 0xF))];
      paramArrayOfChar[(paramInt3 + 2)] = ALPHABET[((paramInt2 << 2 & 0x3C) + (paramInt1 >>> 6 & 0x3))];
      paramArrayOfChar[(paramInt3 + 3)] = ALPHABET[(paramInt1 & 0x3F)];
      return;
    }
    if (paramInt2 > 1)
    {
      paramInt1 = paramArrayOfByte[(paramInt1 + 1)];
      paramArrayOfChar[(paramInt3 + 1)] = ALPHABET[((i << 4 & 0x30) + (paramInt1 >>> 4 & 0xF))];
      paramArrayOfChar[(paramInt3 + 2)] = ALPHABET[((paramInt1 << 2 & 0x3C) + (j >>> 6 & 0x3))];
      paramArrayOfChar[(paramInt3 + 3)] = '=';
      return;
    }
    paramArrayOfChar[(paramInt3 + 1)] = ALPHABET[((k >>> 4 & 0xF) + (i << 4 & 0x30))];
    paramArrayOfChar[(paramInt3 + 2)] = '=';
    paramArrayOfChar[(paramInt3 + 3)] = '=';
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\Base64.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */