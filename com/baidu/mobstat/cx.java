package com.baidu.mobstat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cx
{
  public static String a(File paramFile)
  {
    try
    {
      paramFile = cw.a(MessageDigest.getInstance("SHA-256"), paramFile);
      return paramFile;
    }
    catch (NoSuchAlgorithmException paramFile)
    {
      cz.b(paramFile);
    }
    return "";
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = cw.a(MessageDigest.getInstance("SHA-256"), paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      cz.b(paramArrayOfByte);
    }
    return "";
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */