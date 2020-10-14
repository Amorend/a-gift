package com.baidu.mobstat;

import android.text.TextUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class cr
{
  public static byte[] a(int paramInt, byte[] paramArrayOfByte)
  {
    paramInt -= 1;
    if ((paramInt >= 0) && (cu.a.length > paramInt))
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(cu.a[paramInt].getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(1, localSecretKeySpec);
      return localCipher.doFinal(paramArrayOfByte);
    }
    return new byte[0];
  }
  
  public static byte[] b(int paramInt, byte[] paramArrayOfByte)
  {
    paramInt -= 1;
    if ((paramInt >= 0) && (cu.a.length > paramInt))
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(cu.a[paramInt].getBytes(), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(2, localSecretKeySpec);
      return localCipher.doFinal(paramArrayOfByte);
    }
    return new byte[0];
  }
  
  public static String c(int paramInt, byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = ct.b(a(paramInt, paramArrayOfByte));
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      cz.a(paramArrayOfByte);
    }
    return "";
  }
  
  public static String d(int paramInt, byte[] paramArrayOfByte)
  {
    paramArrayOfByte = c(paramInt, paramArrayOfByte);
    if (TextUtils.isEmpty(paramArrayOfByte)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramArrayOfByte);
    localStringBuilder.append("|");
    localStringBuilder.append(paramInt);
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */