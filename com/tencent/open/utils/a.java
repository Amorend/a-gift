package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

public final class a
{
  private static final l a = new l(101010256L);
  private static final m b = new m(38651);
  
  public static String a(File paramFile)
    throws IOException
  {
    return a(paramFile, "channelNo");
  }
  
  public static String a(File paramFile, String paramString)
    throws IOException
  {
    File localFile = null;
    try
    {
      paramFile = new RandomAccessFile(paramFile, "r");
      localFile = paramFile;
      byte[] arrayOfByte = a(paramFile);
      if (arrayOfByte == null) {
        return null;
      }
      localFile = paramFile;
      a locala = new a(null);
      localFile = paramFile;
      locala.a(arrayOfByte);
      localFile = paramFile;
      paramString = locala.a.getProperty(paramString);
      return paramString;
    }
    finally
    {
      if (localFile != null) {
        localFile.close();
      }
    }
  }
  
  private static byte[] a(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    long l1 = paramRandomAccessFile.length() - 22L;
    paramRandomAccessFile.seek(l1);
    byte[] arrayOfByte = a.a();
    int i = paramRandomAccessFile.read();
    int k = 0;
    for (;;)
    {
      int j = k;
      if (i != -1)
      {
        if ((i == arrayOfByte[0]) && (paramRandomAccessFile.read() == arrayOfByte[1]) && (paramRandomAccessFile.read() == arrayOfByte[2]) && (paramRandomAccessFile.read() == arrayOfByte[3])) {
          j = 1;
        }
      }
      else
      {
        if (j != 0) {
          break;
        }
        throw new ZipException("archive is not a ZIP archive");
      }
      long l2 = l1 - 1L;
      l1 = l2;
      paramRandomAccessFile.seek(l2);
      i = paramRandomAccessFile.read();
    }
    paramRandomAccessFile.seek(l1 + 16L + 4L);
    arrayOfByte = new byte[2];
    paramRandomAccessFile.readFully(arrayOfByte);
    i = new m(arrayOfByte).b();
    if (i == 0) {
      return null;
    }
    arrayOfByte = new byte[i];
    paramRandomAccessFile.read(arrayOfByte);
    return arrayOfByte;
  }
  
  private static class a
  {
    Properties a = new Properties();
    byte[] b;
    
    void a(byte[] paramArrayOfByte)
      throws IOException
    {
      if (paramArrayOfByte == null) {
        return;
      }
      ByteBuffer localByteBuffer = ByteBuffer.wrap(paramArrayOfByte);
      int i = a.a().a().length;
      byte[] arrayOfByte = new byte[i];
      localByteBuffer.get(arrayOfByte);
      if (!a.a().equals(new m(arrayOfByte))) {
        throw new ProtocolException("unknow protocl [" + Arrays.toString(paramArrayOfByte) + "]");
      }
      if (paramArrayOfByte.length - i <= 2) {
        return;
      }
      arrayOfByte = new byte[2];
      localByteBuffer.get(arrayOfByte);
      int j = new m(arrayOfByte).b();
      if (paramArrayOfByte.length - i - 2 < j) {
        return;
      }
      arrayOfByte = new byte[j];
      localByteBuffer.get(arrayOfByte);
      this.a.load(new ByteArrayInputStream(arrayOfByte));
      i = paramArrayOfByte.length - i - j - 2;
      if (i > 0)
      {
        this.b = new byte[i];
        localByteBuffer.get(this.b);
      }
    }
    
    public String toString()
    {
      return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */