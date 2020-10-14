package mao.res;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import mao.util.LEDataInputStream;
import mao.util.LEDataOutputStream;

public class ARSCDecoder
{
  public static final int ARSC_CHUNK_TYPE = 786434;
  public static final int CHECK_PACKAGE = 512;
  byte[] buf;
  ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
  int id;
  private final LEDataInputStream mIn;
  private int mResId;
  private StringBlock mSpecNames;
  public StringBlock mTableStrings;
  private StringBlock mTypeNames;
  String name;
  int packageCount;
  
  ARSCDecoder(InputStream paramInputStream)
  {
    this.mIn = new LEDataInputStream(paramInputStream);
  }
  
  private void checkChunk(int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt1 != paramInt2) {
      throw new IOException(String.format("Invalid chunk type: expected=0x%08x, got=0x%08x", new Object[] { Integer.valueOf(paramInt2), Short.valueOf((short)paramInt1) }));
    }
  }
  
  public static ARSCDecoder read(List<String> paramList, InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new ARSCDecoder(paramInputStream);
    paramInputStream.readTable(paramList);
    return paramInputStream;
  }
  
  private void readPackage()
    throws IOException
  {
    byte[] arrayOfByte = new byte['ࠀ'];
    for (;;)
    {
      int i = this.mIn.read(arrayOfByte, 0, 2048);
      if (i == -1) {
        return;
      }
      this.byteOut.write(arrayOfByte, 0, i);
    }
  }
  
  private void readTable(List<String> paramList)
    throws IOException
  {
    checkChunk(this.mIn.readInt(), 786434);
    this.mIn.readInt();
    this.packageCount = this.mIn.readInt();
    this.mTableStrings = StringBlock.read(this.mIn);
    readPackage();
    this.mTableStrings.getStrings(paramList);
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
    localLEDataOutputStream.writeInt(this.packageCount);
    this.mTableStrings.write(paramList, localLEDataOutputStream);
    writePackage(localLEDataOutputStream);
    paramLEDataOutputStream.writeInt(786434);
    paramLEDataOutputStream.writeInt(localByteArrayOutputStream.size() + 8);
    paramLEDataOutputStream.writeFully(localByteArrayOutputStream.toByteArray());
  }
  
  public void writePackage(LEDataOutputStream paramLEDataOutputStream)
    throws IOException
  {
    paramLEDataOutputStream.writeFully(this.byteOut.toByteArray());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\mao\res\ARSCDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */