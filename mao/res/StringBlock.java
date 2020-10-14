package mao.res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import mao.util.LEDataInputStream;
import mao.util.LEDataOutputStream;
import mao.util.Utf8Utils;

public class StringBlock
{
  public static final int CHUNK_STRINGBLOCK = 1835009;
  public static final int IS_UTF8 = 256;
  private static final CharsetDecoder UTF16LE_DECODER = Charset.forName("UTF-16LE").newDecoder();
  private static final CharsetEncoder UTF16LE_ENCODER = Charset.forName("UTF-16LE").newEncoder();
  private static final CharsetDecoder UTF8_DECODER = Charset.forName("UTF-8").newDecoder();
  private static final CharsetEncoder UTF8_ENCODER = Charset.forName("UTF-8").newEncoder();
  private int chunkSize;
  private int flags;
  private boolean m_isUTF8;
  private int[] m_stringOffsets;
  byte[] m_strings;
  private int[] m_styleOffsets;
  private int[] m_styles;
  private int stringsOffset;
  private int styleOffsetCount;
  private int stylesOffset;
  
  private String decodeString(int paramInt1, int paramInt2)
  {
    try
    {
      if (this.m_isUTF8) {}
      for (CharsetDecoder localCharsetDecoder = UTF8_DECODER;; localCharsetDecoder = UTF16LE_DECODER) {
        return localCharsetDecoder.decode(ByteBuffer.wrap(this.m_strings, paramInt1, paramInt2)).toString();
      }
      return (String)null;
    }
    catch (CharacterCodingException localCharacterCodingException) {}
  }
  
  private static final int getShort(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | paramArrayOfByte[paramInt] & 0xFF;
  }
  
  private int[] getStyle(int paramInt)
  {
    int k = 0;
    if ((this.m_styleOffsets == null) || (this.m_styles == null) || (paramInt >= this.m_styleOffsets.length)) {
      return (int[])null;
    }
    paramInt = this.m_styleOffsets[paramInt] / 4;
    int i = paramInt;
    int j = 0;
    for (;;)
    {
      if ((i >= this.m_styles.length) || (this.m_styles[i] == -1))
      {
        if ((j != 0) && (j % 3 == 0)) {
          break;
        }
        return (int[])null;
      }
      j += 1;
      i += 1;
    }
    int[] arrayOfInt = new int[j];
    i = k;
    for (;;)
    {
      if ((paramInt >= this.m_styles.length) || (this.m_styles[paramInt] == -1)) {
        return arrayOfInt;
      }
      arrayOfInt[i] = this.m_styles[paramInt];
      i += 1;
      paramInt += 1;
    }
  }
  
  private static final int[] getVarint(byte[] paramArrayOfByte, int paramInt)
  {
    int j = paramArrayOfByte[paramInt];
    if ((j & 0x80) == 0) {}
    for (int i = 0;; i = 1)
    {
      j &= 0x7F;
      if (i != 0) {
        break;
      }
      return new int[] { j, 1 };
    }
    return new int[] { j << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF, 2 };
  }
  
  private void outputStyleTag(String paramString, StringBuilder paramStringBuilder, boolean paramBoolean)
  {
    paramStringBuilder.append('<');
    if (paramBoolean) {
      paramStringBuilder.append('/');
    }
    int j = paramString.indexOf(';');
    if (j == -1) {
      paramStringBuilder.append(paramString);
    }
    do
    {
      paramStringBuilder.append('>');
      return;
      paramStringBuilder.append(paramString.substring(0, j));
    } while (paramBoolean);
    int i = 1;
    label65:
    int k;
    String str;
    if (i != 0)
    {
      k = paramString.indexOf('=', j + 1);
      paramStringBuilder.append(' ').append(paramString.substring(j + 1, k)).append("=\"");
      j = paramString.indexOf(';', k + 1);
      if (j == -1) {
        break label152;
      }
      str = paramString.substring(k + 1, j);
    }
    for (;;)
    {
      paramStringBuilder.append(str).append('"');
      break label65;
      break;
      label152:
      str = paramString.substring(k + 1);
      i = 0;
    }
  }
  
  public static StringBlock read(LEDataInputStream paramLEDataInputStream)
    throws IOException
  {
    paramLEDataInputStream.skipCheckInt(1835009);
    StringBlock localStringBlock = new StringBlock();
    int j = paramLEDataInputStream.readInt();
    localStringBlock.chunkSize = j;
    System.out.println("chunkSize " + j);
    int i = paramLEDataInputStream.readInt();
    System.out.println("stringCount " + i);
    int n = paramLEDataInputStream.readInt();
    localStringBlock.styleOffsetCount = n;
    System.out.println("styleOffsetCount " + n);
    int i1 = paramLEDataInputStream.readInt();
    localStringBlock.flags = i1;
    int m = paramLEDataInputStream.readInt();
    localStringBlock.stringsOffset = m;
    System.out.println("stringsOffset " + m);
    int k = paramLEDataInputStream.readInt();
    localStringBlock.stylesOffset = k;
    System.out.println("stylesOffset " + k);
    boolean bool;
    if ((i1 & 0x100) == 0)
    {
      bool = false;
      localStringBlock.m_isUTF8 = bool;
      localStringBlock.m_stringOffsets = paramLEDataInputStream.readIntArray(i);
      if (n != 0) {
        localStringBlock.m_styleOffsets = paramLEDataInputStream.readIntArray(n);
      }
      if (k != 0) {
        break label321;
      }
    }
    label321:
    for (i = j;; i = k)
    {
      i -= m;
      if (i % 4 == 0) {
        break label326;
      }
      throw new IOException(new StringBuffer().append("String data size is not multiple of 4 (").append(i).toString() + ").");
      bool = true;
      break;
    }
    label326:
    localStringBlock.m_strings = new byte[i];
    paramLEDataInputStream.readFully(localStringBlock.m_strings);
    if (k != 0)
    {
      i = j - k;
      if (i % 4 != 0) {
        throw new IOException(new StringBuffer().append("Style data size is not multiple of 4 (").append(i).toString() + ").");
      }
      localStringBlock.m_styles = paramLEDataInputStream.readIntArray(i / 4);
      System.out.println("m_styles_size " + i);
    }
    System.out.println();
    return localStringBlock;
  }
  
  public int getChunkSize()
  {
    return this.chunkSize;
  }
  
  public String getHTML(int paramInt)
  {
    String str = getString(paramInt);
    if (str == null) {
      return str;
    }
    int[] arrayOfInt1 = getStyle(paramInt);
    if (arrayOfInt1 == null) {
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder(str.length() + 32);
    int[] arrayOfInt2 = new int[arrayOfInt1.length / 3];
    int k = 0;
    int i = 0;
    paramInt = 0;
    int j = -1;
    label84:
    label90:
    label95:
    int m;
    if (paramInt == arrayOfInt1.length)
    {
      if (j == -1) {
        break label184;
      }
      paramInt = arrayOfInt1[(j + 1)];
      k -= 1;
      if (k >= 0) {
        break label193;
      }
      m = k + 1;
      if (i >= paramInt) {
        break label311;
      }
      localStringBuilder.append(str.substring(i, paramInt));
      i = paramInt;
    }
    label184:
    label193:
    label311:
    for (;;)
    {
      if (j == -1)
      {
        return localStringBuilder.toString();
        if (arrayOfInt1[(paramInt + 1)] == -1) {
          m = j;
        }
        for (;;)
        {
          paramInt += 3;
          j = m;
          break;
          if (j != -1)
          {
            m = j;
            if (arrayOfInt1[(j + 1)] <= arrayOfInt1[(paramInt + 1)]) {}
          }
          else
          {
            m = paramInt;
          }
        }
        paramInt = str.length();
        break label84;
        int n = arrayOfInt2[k];
        int i1 = arrayOfInt1[(n + 2)];
        if (i1 >= paramInt) {
          break label95;
        }
        m = i;
        if (i <= i1)
        {
          localStringBuilder.append(str.substring(i, i1 + 1));
          m = i1 + 1;
        }
        outputStyleTag(getString(arrayOfInt1[n]), localStringBuilder, true);
        k -= 1;
        i = m;
        break label90;
      }
      outputStyleTag(getString(arrayOfInt1[j]), localStringBuilder, false);
      arrayOfInt1[(j + 1)] = -1;
      k = m + 1;
      arrayOfInt2[m] = j;
      break;
    }
  }
  
  public int getSize()
  {
    if (this.m_stringOffsets != null) {
      return this.m_stringOffsets.length;
    }
    return 0;
  }
  
  public String getString(int paramInt)
  {
    if ((paramInt < 0) || (this.m_stringOffsets == null) || (paramInt >= this.m_stringOffsets.length)) {
      return (String)null;
    }
    int i = this.m_stringOffsets[paramInt];
    if (!this.m_isUTF8)
    {
      paramInt = getShort(this.m_strings, i) * 2;
      i += 2;
    }
    for (;;)
    {
      return decodeString(i, paramInt);
      paramInt = getVarint(this.m_strings, i)[1] + i;
      int[] arrayOfInt = getVarint(this.m_strings, paramInt);
      i = arrayOfInt[1] + paramInt;
      paramInt = arrayOfInt[0];
    }
  }
  
  public void getStrings(List<String> paramList)
  {
    int j = getSize();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      paramList.add(Utf8Utils.escapeString(getString(i)));
      i += 1;
    }
  }
  
  public void write(List<String> paramList, LEDataOutputStream paramLEDataOutputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
    LEDataOutputStream localLEDataOutputStream1 = new LEDataOutputStream(localByteArrayOutputStream1);
    int k = paramList.size();
    int[] arrayOfInt = new int[k];
    ByteArrayOutputStream localByteArrayOutputStream2 = new ByteArrayOutputStream();
    LEDataOutputStream localLEDataOutputStream2 = new LEDataOutputStream(localByteArrayOutputStream2);
    int i = 0;
    int j = 0;
    if (i >= k)
    {
      i = localByteArrayOutputStream2.size();
      j = i % 4;
      if (j != 0) {
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= 4 - j)
      {
        paramList = localByteArrayOutputStream2.toByteArray();
        System.out.println("string chunk size: " + this.chunkSize);
        localLEDataOutputStream1.writeInt(k);
        localLEDataOutputStream1.writeInt(this.styleOffsetCount);
        localLEDataOutputStream1.writeInt(this.flags);
        localLEDataOutputStream1.writeInt(this.stringsOffset);
        localLEDataOutputStream1.writeInt(this.stylesOffset);
        localLEDataOutputStream1.writeIntArray(arrayOfInt);
        if (this.styleOffsetCount != 0)
        {
          System.out.println("write stylesOffset");
          localLEDataOutputStream1.writeIntArray(this.m_styleOffsets);
        }
        localLEDataOutputStream1.writeFully(paramList);
        if (this.m_styles != null)
        {
          System.out.println("write m_styles");
          localLEDataOutputStream1.writeIntArray(this.m_styles);
        }
        paramLEDataOutputStream.writeInt(1835009);
        paramList = localByteArrayOutputStream1.toByteArray();
        paramLEDataOutputStream.writeInt(paramList.length + 8);
        paramLEDataOutputStream.writeFully(paramList);
        return;
        arrayOfInt[i] = j;
        char[] arrayOfChar = Utf8Utils.escapeSequence((String)paramList.get(i)).toCharArray();
        localLEDataOutputStream2.writeShort((short)arrayOfChar.length);
        localLEDataOutputStream2.writeCharArray(arrayOfChar);
        localLEDataOutputStream2.writeShort((short)0);
        j += arrayOfChar.length * 2 + 4;
        i += 1;
        break;
      }
      localByteArrayOutputStream2.write(0);
      i += 1;
    }
  }
  
  public void write(LEDataOutputStream paramLEDataOutputStream)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList(getSize());
    getStrings(localArrayList);
    write(localArrayList, paramLEDataOutputStream);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\res\StringBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */