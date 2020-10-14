package com.baidu.mobstat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class cv
{
  public static byte[] a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
    }
    catch (IOException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return localByteArrayOutputStream.toByteArray();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */