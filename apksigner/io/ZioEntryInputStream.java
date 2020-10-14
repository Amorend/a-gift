package apksigner.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class ZioEntryInputStream
  extends InputStream
{
  OutputStream monitor = (OutputStream)null;
  int offset = 0;
  RandomAccessFile raf;
  boolean returnDummyByte = false;
  int size;
  
  public ZioEntryInputStream(ZioEntry paramZioEntry)
    throws IOException
  {
    this.size = paramZioEntry.getCompressedSize();
    this.raf = paramZioEntry.getZipInput().in;
    if (paramZioEntry.getDataPosition() >= 0)
    {
      this.raf.seek(paramZioEntry.getDataPosition());
      return;
    }
    paramZioEntry.readLocalHeader();
  }
  
  private int readBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.size - this.offset == 0) {
      if (this.returnDummyByte)
      {
        this.returnDummyByte = false;
        paramArrayOfByte[paramInt1] = ((byte)0);
        paramInt2 = 1;
      }
    }
    int i;
    do
    {
      return paramInt2;
      return -1;
      paramInt2 = Math.min(paramInt2, available());
      i = this.raf.read(paramArrayOfByte, paramInt1, paramInt2);
      paramInt2 = i;
    } while (i <= 0);
    if (this.monitor != null) {
      this.monitor.write(paramArrayOfByte, paramInt1, i);
    }
    this.offset += i;
    return i;
  }
  
  @Override
  public int available()
    throws IOException
  {
    int j = this.size - this.offset;
    int i = j;
    if (j == 0)
    {
      i = j;
      if (this.returnDummyByte) {
        i = 1;
      }
    }
    return i;
  }
  
  @Override
  public void close()
    throws IOException
  {}
  
  @Override
  public boolean markSupported()
  {
    return false;
  }
  
  @Override
  public int read()
    throws IOException
  {
    int i = 0;
    if (this.size - this.offset == 0) {
      if (this.returnDummyByte) {
        this.returnDummyByte = false;
      }
    }
    int j;
    do
    {
      return i;
      return -1;
      j = this.raf.read();
      i = j;
    } while (j < 0);
    if (this.monitor != null) {
      this.monitor.write(j);
    }
    this.offset += 1;
    return j;
  }
  
  @Override
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return readBytes(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  @Override
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return readBytes(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void setMonitorStream(OutputStream paramOutputStream)
  {
    this.monitor = paramOutputStream;
  }
  
  public void setReturnDummyByte(boolean paramBoolean)
  {
    this.returnDummyByte = paramBoolean;
  }
  
  @Override
  public long skip(long paramLong)
    throws IOException
  {
    paramLong = Math.min(paramLong, available());
    this.raf.seek(this.raf.getFilePointer() + paramLong);
    return paramLong;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\io\ZioEntryInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */