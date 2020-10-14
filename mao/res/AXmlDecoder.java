package mao.res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import mao.util.LEDataInputStream;
import mao.util.LEDataOutputStream;

public class AXmlDecoder
{
  private static final int AXML_CHUNK_TYPE = 524291;
  ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
  private final LEDataInputStream mIn;
  public StringBlock mTableStrings;
  
  AXmlDecoder(LEDataInputStream paramLEDataInputStream)
  {
    this.mIn = paramLEDataInputStream;
  }
  
  private void checkChunk(int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt1 != paramInt2) {
      throw new IOException(String.format("Invalid chunk type: expected=0x%08x, got=0x%08x", new Object[] { Integer.valueOf(paramInt2), Short.valueOf((short)paramInt1) }));
    }
  }
  
  public static AXmlDecoder read(List<String> paramList, InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new AXmlDecoder(new LEDataInputStream(paramInputStream));
    paramInputStream.readStrings(paramList);
    return paramInputStream;
  }
  
  private void readStrings(List<String> paramList)
    throws IOException
  {
    checkChunk(this.mIn.readInt(), 524291);
    this.mIn.readInt();
    this.mTableStrings = StringBlock.read(this.mIn);
    byte[] arrayOfByte = new byte['ࠀ'];
    for (;;)
    {
      int i = this.mIn.read(arrayOfByte, 0, 2048);
      if (i == -1)
      {
        this.mTableStrings.getStrings(paramList);
        return;
      }
      this.byteOut.write(arrayOfByte, 0, i);
    }
  }
  
  public void write(List<String> paramList, OutputStream paramOutputStream)
    throws IOException
  {
    write(paramList, new LEDataOutputStream(paramOutputStream));
  }
  
  public void write(List<String> paramList, LEDataOutputStream paramLEDataOutputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    LEDataOutputStream localLEDataOutputStream = new LEDataOutputStream(localByteArrayOutputStream);
    this.mTableStrings.write(paramList, localLEDataOutputStream);
    localLEDataOutputStream.writeFully(this.byteOut.toByteArray());
    paramLEDataOutputStream.writeInt(524291);
    paramLEDataOutputStream.writeInt(localByteArrayOutputStream.size() + 8);
    paramLEDataOutputStream.writeFully(localByteArrayOutputStream.toByteArray());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\res\AXmlDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */