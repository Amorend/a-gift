package com.androlua.util;

import com.androlua.LuaApplication;

public class RSASecurity
{
  private static String a = LuaApplication.getInstance().getLuaExtDir("PublicKey");
  private static String b = LuaApplication.getInstance().getLuaExtDir("PrivateKey");
  
  /* Error */
  public static byte[] decrypt(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: new 37	java/io/ObjectInputStream
    //   7: dup
    //   8: new 39	java/io/FileInputStream
    //   11: dup
    //   12: getstatic 27	com/androlua/util/RSASecurity:b	Ljava/lang/String;
    //   15: invokespecial 42	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   18: invokespecial 45	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   21: astore_2
    //   22: aload_2
    //   23: invokevirtual 49	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   26: checkcast 51	java/security/Key
    //   29: astore_1
    //   30: aload_2
    //   31: invokevirtual 54	java/io/ObjectInputStream:close	()V
    //   34: ldc 56
    //   36: invokestatic 61	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   39: astore_2
    //   40: aload_2
    //   41: iconst_2
    //   42: aload_1
    //   43: invokevirtual 65	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   46: aload_2
    //   47: aload_0
    //   48: invokevirtual 68	javax/crypto/Cipher:doFinal	([B)[B
    //   51: areturn
    //   52: astore_0
    //   53: aload_2
    //   54: astore_1
    //   55: goto +18 -> 73
    //   58: astore_0
    //   59: aload_2
    //   60: astore_1
    //   61: goto +10 -> 71
    //   64: astore_0
    //   65: goto +8 -> 73
    //   68: astore_0
    //   69: aload_3
    //   70: astore_1
    //   71: aload_0
    //   72: athrow
    //   73: aload_1
    //   74: invokevirtual 54	java/io/ObjectInputStream:close	()V
    //   77: aload_0
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	paramArrayOfByte	byte[]
    //   3	71	1	localObject1	Object
    //   21	39	2	localObject2	Object
    //   1	69	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   22	30	52	finally
    //   22	30	58	java/lang/Exception
    //   4	22	64	finally
    //   71	73	64	finally
    //   4	22	68	java/lang/Exception
  }
  
  /* Error */
  public static byte[] encrypt(String paramString)
  {
    // Byte code:
    //   0: invokestatic 73	com/androlua/util/RSASecurity:generateKeyPair	()V
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_1
    //   7: new 37	java/io/ObjectInputStream
    //   10: dup
    //   11: new 39	java/io/FileInputStream
    //   14: dup
    //   15: getstatic 23	com/androlua/util/RSASecurity:a	Ljava/lang/String;
    //   18: invokespecial 42	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   21: invokespecial 45	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   24: astore_2
    //   25: aload_2
    //   26: invokevirtual 49	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   29: checkcast 51	java/security/Key
    //   32: astore_1
    //   33: aload_2
    //   34: invokevirtual 54	java/io/ObjectInputStream:close	()V
    //   37: ldc 56
    //   39: invokestatic 61	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   42: astore_2
    //   43: aload_2
    //   44: iconst_1
    //   45: aload_1
    //   46: invokevirtual 65	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   49: aload_2
    //   50: aload_0
    //   51: invokevirtual 79	java/lang/String:getBytes	()[B
    //   54: invokevirtual 68	javax/crypto/Cipher:doFinal	([B)[B
    //   57: areturn
    //   58: astore_0
    //   59: aload_2
    //   60: astore_1
    //   61: goto +18 -> 79
    //   64: astore_0
    //   65: aload_2
    //   66: astore_1
    //   67: goto +10 -> 77
    //   70: astore_0
    //   71: goto +8 -> 79
    //   74: astore_0
    //   75: aload_3
    //   76: astore_1
    //   77: aload_0
    //   78: athrow
    //   79: aload_1
    //   80: invokevirtual 54	java/io/ObjectInputStream:close	()V
    //   83: aload_0
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramString	String
    //   6	74	1	localObject1	Object
    //   24	42	2	localObject2	Object
    //   4	72	3	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   25	33	58	finally
    //   25	33	64	java/lang/Exception
    //   7	25	70	finally
    //   77	79	70	finally
    //   7	25	74	java/lang/Exception
  }
  
  /* Error */
  public static void generateKeyPair()
  {
    // Byte code:
    //   0: new 81	java/security/SecureRandom
    //   3: dup
    //   4: invokespecial 82	java/security/SecureRandom:<init>	()V
    //   7: astore_0
    //   8: ldc 56
    //   10: invokestatic 87	java/security/KeyPairGenerator:getInstance	(Ljava/lang/String;)Ljava/security/KeyPairGenerator;
    //   13: astore_1
    //   14: aload_1
    //   15: sipush 1024
    //   18: aload_0
    //   19: invokevirtual 91	java/security/KeyPairGenerator:initialize	(ILjava/security/SecureRandom;)V
    //   22: aload_1
    //   23: sipush 1024
    //   26: invokevirtual 94	java/security/KeyPairGenerator:initialize	(I)V
    //   29: aload_1
    //   30: invokevirtual 97	java/security/KeyPairGenerator:generateKeyPair	()Ljava/security/KeyPair;
    //   33: astore_0
    //   34: aload_0
    //   35: invokevirtual 103	java/security/KeyPair:getPublic	()Ljava/security/PublicKey;
    //   38: astore_1
    //   39: aload_0
    //   40: invokevirtual 107	java/security/KeyPair:getPrivate	()Ljava/security/PrivateKey;
    //   43: astore 4
    //   45: aconst_null
    //   46: astore_2
    //   47: aconst_null
    //   48: astore_3
    //   49: new 109	java/io/ObjectOutputStream
    //   52: dup
    //   53: new 111	java/io/FileOutputStream
    //   56: dup
    //   57: getstatic 23	com/androlua/util/RSASecurity:a	Ljava/lang/String;
    //   60: invokespecial 112	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   63: invokespecial 115	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   66: astore_0
    //   67: new 109	java/io/ObjectOutputStream
    //   70: dup
    //   71: new 111	java/io/FileOutputStream
    //   74: dup
    //   75: getstatic 27	com/androlua/util/RSASecurity:b	Ljava/lang/String;
    //   78: invokespecial 112	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   81: invokespecial 115	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   84: astore_3
    //   85: aload_0
    //   86: aload_1
    //   87: invokevirtual 119	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   90: aload_3
    //   91: aload 4
    //   93: invokevirtual 119	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   96: aload_0
    //   97: invokevirtual 120	java/io/ObjectOutputStream:close	()V
    //   100: aload_3
    //   101: invokevirtual 120	java/io/ObjectOutputStream:close	()V
    //   104: return
    //   105: astore_1
    //   106: aload_3
    //   107: astore_2
    //   108: goto +37 -> 145
    //   111: astore_2
    //   112: aload_3
    //   113: astore_1
    //   114: goto +10 -> 124
    //   117: astore_1
    //   118: goto +27 -> 145
    //   121: astore_2
    //   122: aconst_null
    //   123: astore_1
    //   124: goto +14 -> 138
    //   127: astore_1
    //   128: aconst_null
    //   129: astore_0
    //   130: goto +15 -> 145
    //   133: astore_2
    //   134: aconst_null
    //   135: astore_1
    //   136: aload_3
    //   137: astore_0
    //   138: aload_2
    //   139: athrow
    //   140: astore_3
    //   141: aload_1
    //   142: astore_2
    //   143: aload_3
    //   144: astore_1
    //   145: aload_0
    //   146: invokevirtual 120	java/io/ObjectOutputStream:close	()V
    //   149: aload_2
    //   150: invokevirtual 120	java/io/ObjectOutputStream:close	()V
    //   153: aload_1
    //   154: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   7	139	0	localObject1	Object
    //   13	74	1	localObject2	Object
    //   105	1	1	localObject3	Object
    //   113	1	1	localObject4	Object
    //   117	1	1	localObject5	Object
    //   123	1	1	localObject6	Object
    //   127	1	1	localObject7	Object
    //   135	19	1	localObject8	Object
    //   46	62	2	localObject9	Object
    //   111	1	2	localException1	Exception
    //   121	1	2	localException2	Exception
    //   133	6	2	localException3	Exception
    //   142	8	2	localObject10	Object
    //   48	89	3	localObjectOutputStream	java.io.ObjectOutputStream
    //   140	4	3	localObject11	Object
    //   43	49	4	localPrivateKey	java.security.PrivateKey
    // Exception table:
    //   from	to	target	type
    //   85	96	105	finally
    //   85	96	111	java/lang/Exception
    //   67	85	117	finally
    //   67	85	121	java/lang/Exception
    //   49	67	127	finally
    //   49	67	133	java/lang/Exception
    //   138	140	140	finally
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\RSASecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */