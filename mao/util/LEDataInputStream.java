package mao.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class LEDataInputStream
  implements DataInput
{
  protected final DataInputStream dis;
  int end;
  protected final InputStream is;
  int s;
  protected final byte[] work;
  
  public LEDataInputStream(InputStream paramInputStream)
  {
    this.is = paramInputStream;
    this.dis = new DataInputStream(paramInputStream);
    this.work = new byte[8];
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return this.dis.read(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final boolean readBoolean()
    throws IOException
  {
    return this.dis.readBoolean();
  }
  
  public final byte readByte()
    throws IOException
  {
    return this.dis.readByte();
  }
  
  public final char readChar()
    throws IOException
  {
    this.dis.readFully(this.work, 0, 2);
    return (char)((this.work[1] & 0xFF) << 8 | this.work[0] & 0xFF);
  }
  
  public final double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(readLong());
  }
  
  public final float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(readInt());
  }
  
  public final void readFully(byte[] paramArrayOfByte)
    throws IOException
  {
    this.dis.readFully(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public final void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.dis.readFully(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final int readInt()
    throws IOException
  {
    this.dis.readFully(this.work, 0, 4);
    return this.work[3] << 24 | (this.work[2] & 0xFF) << 16 | (this.work[1] & 0xFF) << 8 | this.work[0] & 0xFF;
  }
  
  public int[] readIntArray(int paramInt)
    throws IOException
  {
    int[] arrayOfInt = new int[paramInt];
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return arrayOfInt;
      }
      arrayOfInt[i] = readInt();
      i += 1;
    }
  }
  
  public final String readLine()
    throws IOException
  {
    return this.dis.readLine();
  }
  
  public final long readLong()
    throws IOException
  {
    this.dis.readFully(this.work, 0, 8);
    return this.work[7] << 56 | (this.work[6] & 0xFF) << 48 | (this.work[5] & 0xFF) << 40 | (this.work[4] & 0xFF) << 32 | (this.work[3] & 0xFF) << 24 | (this.work[2] & 0xFF) << 16 | (this.work[1] & 0xFF) << 8 | this.work[0] & 0xFF;
  }
  
  public String readNulEndedString(int paramInt, boolean paramBoolean)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(16);
    for (;;)
    {
      int i = paramInt - 1;
      if (paramInt == 0) {}
      do
      {
        if (paramBoolean)
        {
          skipBytes(i * 2);
          this.end = (i * 2 + this.end);
        }
        return localStringBuilder.toString();
        paramInt = readShort();
        this.end += 2;
      } while (paramInt == 0);
      localStringBuilder.append((char)paramInt);
      paramInt = i;
    }
  }
  
  public final short readShort()
    throws IOException
  {
    this.dis.readFully(this.work, 0, 2);
    return (short)((this.work[1] & 0xFF) << 8 | this.work[0] & 0xFF);
  }
  
  public final String readUTF()
    throws IOException
  {
    return this.dis.readUTF();
  }
  
  public final int readUnsignedByte()
    throws IOException
  {
    return this.dis.readUnsignedByte();
  }
  
  public final int readUnsignedShort()
    throws IOException
  {
    this.dis.readFully(this.work, 0, 2);
    return (this.work[1] & 0xFF) << 8 | this.work[0] & 0xFF;
  }
  
  public int size()
  {
    return this.end;
  }
  
  public final int skipBytes(int paramInt)
    throws IOException
  {
    return this.dis.skipBytes(paramInt);
  }
  
  public void skipCheckByte(byte paramByte)
    throws IOException
  {
    byte b = readByte();
    if (b != paramByte) {
      throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[] { Byte.valueOf(paramByte), Byte.valueOf(b) }));
    }
  }
  
  public void skipCheckInt(int paramInt)
    throws IOException
  {
    int i = readInt();
    if (i != paramInt) {
      throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i) }));
    }
  }
  
  public void skipCheckShort(short paramShort)
    throws IOException
  {
    short s1 = readShort();
    if (s1 != paramShort) {
      throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", new Object[] { Short.valueOf(paramShort), Short.valueOf(s1) }));
    }
  }
  
  public void skipInt()
    throws IOException
  {
    skipBytes(4);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\util\LEDataInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */