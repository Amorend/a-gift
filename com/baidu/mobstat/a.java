package com.baidu.mobstat;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class a
{
  public static byte[] a(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, paramString2, new IvParameterSpec(paramString1.getBytes()));
    return localCipher.doFinal(paramArrayOfByte);
  }
  
  public static byte[] b(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, paramString2, new IvParameterSpec(paramString1.getBytes()));
    return localCipher.doFinal(paramArrayOfByte);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */