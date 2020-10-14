package mao.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class LEDataOutputStream
{
  protected DataOutputStream dos;
  
  public LEDataOutputStream(OutputStream paramOutputStream)
  {
    this.dos = new DataOutputStream(paramOutputStream);
  }
  
  public int size()
  {
    return this.dos.size();
  }
  
  public final void writeByte(byte paramByte)
    throws IOException
  {
    this.dos.writeByte(paramByte);
  }
  
  public final void writeChar(char paramChar)
    throws IOException
  {
    this.dos.writeByte(paramChar & 0xFF);
    this.dos.writeByte(paramChar >>> '\b' & 0xFF);
  }
  
  public final void writeCharArray(char[] paramArrayOfChar)
    throws IOException
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfChar.length) {
        return;
      }
      writeChar(paramArrayOfChar[i]);
      i += 1;
    }
  }
  
  public final void writeFully(byte[] paramArrayOfByte)
    throws IOException
  {
    this.dos.write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public final void writeFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.dos.write(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final void writeInt(int paramInt)
    throws IOException
  {
    this.dos.writeByte(paramInt & 0xFF);
    this.dos.writeByte(paramInt >>> 8 & 0xFF);
    this.dos.writeByte(paramInt >>> 16 & 0xFF);
    this.dos.writeByte(paramInt >>> 24 & 0xFF);
  }
  
  public final void writeIntArray(int[] paramArrayOfInt)
    throws IOException
  {
    writeIntArray(paramArrayOfInt, 0, paramArrayOfInt.length);
  }
  
  public final void writeIntArray(int[] paramArrayOfInt, int paramInt1, int paramInt2)
    throws IOException
  {
    for (;;)
    {
      if (paramInt1 >= paramInt2) {
        return;
      }
      writeInt(paramArrayOfInt[paramInt1]);
      paramInt1 += 1;
    }
  }
  
  public final void writeNulEndedString(String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    paramString = paramString.toCharArray();
    int i = 0;
    if ((i >= paramString.length) || (paramInt == 0)) {
      if (paramBoolean) {
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= paramInt * 2)
      {
        return;
        writeChar(paramString[i]);
        paramInt -= 1;
        i += 1;
        break;
      }
      this.dos.writeByte(0);
      i += 1;
    }
  }
  
  public final void writeShort(short paramShort)
    throws IOException
  {
    this.dos.writeByte(paramShort & 0xFF);
    this.dos.writeByte(paramShort >>> 8 & 0xFF);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\util\LEDataOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */