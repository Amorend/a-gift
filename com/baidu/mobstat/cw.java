package com.baidu.mobstat;

import java.security.MessageDigest;

public final class cw
{
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] >> 4 & 0xF;
      int k = paramArrayOfByte[i] & 0xF;
      if (j >= 10) {
        j = j + 97 - 10;
      } else {
        j += 48;
      }
      localStringBuilder.append((char)j);
      if (k >= 10) {
        j = k + 97 - 10;
      } else {
        j = k + 48;
      }
      localStringBuilder.append((char)j);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  private static String b(MessageDigest paramMessageDigest, java.io.File paramFile)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 38	java/io/File:isFile	()Z
    //   4: ifeq +124 -> 128
    //   7: aconst_null
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_3
    //   12: new 40	java/io/FileInputStream
    //   15: dup
    //   16: aload_1
    //   17: invokespecial 43	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   20: astore_1
    //   21: sipush 4048
    //   24: newarray <illegal type>
    //   26: astore_3
    //   27: aload_1
    //   28: aload_3
    //   29: invokevirtual 47	java/io/FileInputStream:read	([B)I
    //   32: istore_2
    //   33: iload_2
    //   34: iconst_m1
    //   35: if_icmpne +14 -> 49
    //   38: aload_1
    //   39: ifnull +63 -> 102
    //   42: aload_1
    //   43: invokevirtual 50	java/io/FileInputStream:close	()V
    //   46: goto +56 -> 102
    //   49: aload_0
    //   50: aload_3
    //   51: iconst_0
    //   52: iload_2
    //   53: invokevirtual 56	java/security/MessageDigest:update	([BII)V
    //   56: goto -29 -> 27
    //   59: astore_0
    //   60: aload_1
    //   61: astore_3
    //   62: goto +48 -> 110
    //   65: astore 4
    //   67: goto +12 -> 79
    //   70: astore_0
    //   71: goto +39 -> 110
    //   74: astore 4
    //   76: aload 5
    //   78: astore_1
    //   79: aload_1
    //   80: astore_3
    //   81: aload 4
    //   83: invokestatic 61	com/baidu/mobstat/cz:b	(Ljava/lang/Throwable;)V
    //   86: aload_1
    //   87: ifnull +15 -> 102
    //   90: aload_1
    //   91: invokevirtual 50	java/io/FileInputStream:close	()V
    //   94: goto +8 -> 102
    //   97: astore_1
    //   98: aload_1
    //   99: invokestatic 61	com/baidu/mobstat/cz:b	(Ljava/lang/Throwable;)V
    //   102: aload_0
    //   103: invokevirtual 65	java/security/MessageDigest:digest	()[B
    //   106: invokestatic 67	com/baidu/mobstat/cw:a	([B)Ljava/lang/String;
    //   109: areturn
    //   110: aload_3
    //   111: ifnull +15 -> 126
    //   114: aload_3
    //   115: invokevirtual 50	java/io/FileInputStream:close	()V
    //   118: goto +8 -> 126
    //   121: astore_1
    //   122: aload_1
    //   123: invokestatic 61	com/baidu/mobstat/cz:b	(Ljava/lang/Throwable;)V
    //   126: aload_0
    //   127: athrow
    //   128: ldc 69
    //   130: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramMessageDigest	MessageDigest
    //   0	131	1	paramFile	java.io.File
    //   32	21	2	i	int
    //   11	104	3	localObject1	Object
    //   65	1	4	localException1	Exception
    //   74	8	4	localException2	Exception
    //   8	69	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   21	27	59	finally
    //   27	33	59	finally
    //   49	56	59	finally
    //   21	27	65	java/lang/Exception
    //   27	33	65	java/lang/Exception
    //   49	56	65	java/lang/Exception
    //   12	21	70	finally
    //   81	86	70	finally
    //   12	21	74	java/lang/Exception
    //   42	46	97	java/io/IOException
    //   90	94	97	java/io/IOException
    //   114	118	121	java/io/IOException
  }
  
  private static String b(MessageDigest paramMessageDigest, byte[] paramArrayOfByte)
  {
    paramMessageDigest.update(paramArrayOfByte);
    return a(paramMessageDigest.digest());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */