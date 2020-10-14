package com.baidu.mobstat;

import android.annotation.SuppressLint;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class cq
{
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = b(a(), b(), paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      cz.b(paramArrayOfByte);
    }
    return "";
  }
  
  public static byte[] a()
  {
    KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
    localKeyGenerator.init(128, new SecureRandom());
    return localKeyGenerator.generateKey().getEncoded();
  }
  
  @SuppressLint({"TrulyRandom"})
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    paramArrayOfByte2 = new IvParameterSpec(paramArrayOfByte2);
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, paramArrayOfByte1, paramArrayOfByte2);
    return localCipher.doFinal(paramArrayOfByte3);
  }
  
  public static String b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
  {
    try
    {
      paramArrayOfByte3 = a(paramArrayOfByte1, paramArrayOfByte2, cv.a(paramArrayOfByte3));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(ct.b(paramArrayOfByte3));
      localStringBuilder.append("|");
      localStringBuilder.append(da.a(paramArrayOfByte1));
      localStringBuilder.append("|");
      localStringBuilder.append(da.a(paramArrayOfByte2));
      paramArrayOfByte1 = localStringBuilder.toString();
      return paramArrayOfByte1;
    }
    catch (Exception paramArrayOfByte1)
    {
      cz.b(paramArrayOfByte1);
    }
    return "";
  }
  
  public static byte[] b()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = new byte[16];
    localSecureRandom.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */