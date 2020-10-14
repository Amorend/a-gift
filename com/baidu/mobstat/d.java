package com.baidu.mobstat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class d
{
  public static byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = MessageDigest.getInstance("SHA-1").digest(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      throw new RuntimeException(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */