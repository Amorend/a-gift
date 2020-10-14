package com.tencent.open.utils;

import android.util.Base64;
import com.tencent.open.a.f;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class d
{
  private static byte[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramString2 = new SecretKeySpec(paramString2.getBytes(), "DES");
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      localCipher.init(1, paramString2, localIvParameterSpec);
      paramString1 = Base64.encodeToString(localCipher.doFinal(paramString1.getBytes()), 0);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      f.c("DESUtils", "encode " + paramString1.toString());
    }
    return null;
  }
  
  public static String b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Base64.decode(paramString1, 0);
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
      paramString2 = new SecretKeySpec(paramString2.getBytes(), "DES");
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      localCipher.init(2, paramString2, localIvParameterSpec);
      paramString1 = new String(localCipher.doFinal(paramString1));
      return paramString1;
    }
    catch (Exception paramString1)
    {
      f.c("DESUtils", "decode " + paramString1.toString());
    }
    return null;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */