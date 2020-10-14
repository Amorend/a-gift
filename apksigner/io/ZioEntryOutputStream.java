package apksigner.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class ZioEntryOutputStream
  extends OutputStream
{
  CRC32 crc = new CRC32();
  int crcValue = 0;
  OutputStream downstream;
  int size = 0;
  OutputStream wrapped;
  
  public ZioEntryOutputStream(int paramInt, OutputStream paramOutputStream)
  {
    this.wrapped = paramOutputStream;
    if (paramInt != 0)
    {
      this.downstream = new DeflaterOutputStream(paramOutputStream, new Deflater(9, true));
      return;
    }
    this.downstream = paramOutputStream;
  }
  
  public void close()
    throws IOException
  {
    this.downstream.flush();
    this.downstream.close();
    this.crcValue = ((int)this.crc.getValue());
  }
  
  public void flush()
    throws IOException
  {
    this.downstream.flush();
  }
  
  public int getCRC()
  {
    return this.crcValue;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public OutputStream getWrappedStream()
  {
    return this.wrapped;
  }
  
  public void write(int paramInt)
    throws IOException
  {
    this.downstream.write(paramInt);
    this.crc.update(paramInt);
    this.size += 1;
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    this.downstream.write(paramArrayOfByte);
    this.crc.update(paramArrayOfByte);
    this.size += paramArrayOfByte.length;
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.downstream.write(paramArrayOfByte, paramInt1, paramInt2);
    this.crc.update(paramArrayOfByte, paramInt1, paramInt2);
    this.size += paramInt2;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\io\ZioEntryOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */