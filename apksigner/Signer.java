package apksigner;

import apksigner.io.ZioEntry;
import apksigner.io.ZipInput;
import apksigner.io.ZipOutput;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.DigestOutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signer
{
  private static final String CERT_RSA_NAME = "META-INF/CERT.RSA";
  private static final String CERT_SF_NAME = "META-INF/CERT.SF";
  public static final String[] DEFAULT_KEYS = { "platform", "testkey" };
  static PrivateKey privateKey;
  static X509Certificate publicKey;
  private static Signer res = new Signer();
  static byte[] sigBlockTemp;
  private static Pattern stripPattern = Pattern.compile("^META-INF/(.*)[.](SF|RSA|DSA)$");
  
  public Signer() {}
  
  public Signer(String paramString)
    throws IOException, GeneralSecurityException
  {
    loadKeys(paramString);
  }
  
  private static Manifest addDigestsToManifest(Map<String, ZioEntry> paramMap)
    throws IOException, GeneralSecurityException
  {
    Manifest localManifest1 = (Manifest)null;
    Object localObject = (ZioEntry)paramMap.get("META-INF/MANIFEST.MF");
    if (localObject != null)
    {
      localManifest1 = new Manifest();
      localManifest1.read(((ZioEntry)localObject).getInputStream());
    }
    for (;;)
    {
      Manifest localManifest2 = new Manifest();
      localObject = localManifest2.getMainAttributes();
      MessageDigest localMessageDigest;
      byte[] arrayOfByte;
      Iterator localIterator;
      if (localManifest1 != null)
      {
        ((Attributes)localObject).putAll(localManifest1.getMainAttributes());
        localMessageDigest = MessageDigest.getInstance("SHA1");
        arrayOfByte = new byte['က'];
        localObject = new TreeMap();
        ((TreeMap)localObject).putAll(paramMap);
        localIterator = ((Collection)((TreeMap)localObject).values()).iterator();
      }
      String str;
      do
      {
        if (!localIterator.hasNext())
        {
          return localManifest2;
          ((Attributes)localObject).putValue("Manifest-Version", "1.0");
          ((Attributes)localObject).putValue("Created-By", "1.0 (Android SignApk)");
          break;
        }
        paramMap = (ZioEntry)localIterator.next();
        str = paramMap.getName();
      } while ((paramMap.isDirectory()) || (str.equals("META-INF/MANIFEST.MF")) || (str.equals("META-INF/CERT.SF")) || (str.equals("META-INF/CERT.RSA")) || ((stripPattern != null) && (stripPattern.matcher(str).matches())));
      paramMap = paramMap.getInputStream();
      for (;;)
      {
        int i = paramMap.read(arrayOfByte);
        if (i <= 0)
        {
          localObject = (Attributes)null;
          paramMap = (Map<String, ZioEntry>)localObject;
          if (localManifest1 != null)
          {
            Attributes localAttributes = localManifest1.getAttributes(str);
            paramMap = (Map<String, ZioEntry>)localObject;
            if (localAttributes != null) {
              paramMap = new Attributes(localAttributes);
            }
          }
          localObject = paramMap;
          if (paramMap == null) {
            localObject = new Attributes();
          }
          ((Attributes)localObject).putValue("SHA1-Digest", Base64.encode(localMessageDigest.digest()));
          localManifest2.getEntries().put(str, localObject);
          break;
        }
        localMessageDigest.update(arrayOfByte, 0, i);
      }
    }
  }
  
  private static void copyFiles(Manifest paramManifest, Map<String, ZioEntry> paramMap, ZipOutput paramZipOutput, long paramLong)
    throws IOException
  {
    paramManifest = new ArrayList(paramManifest.getEntries().keySet());
    Collections.sort(paramManifest);
    paramManifest = ((Collection)paramManifest).iterator();
    for (;;)
    {
      if (!paramManifest.hasNext()) {
        return;
      }
      ZioEntry localZioEntry = (ZioEntry)paramMap.get((String)paramManifest.next());
      localZioEntry.setTime(paramLong);
      paramZipOutput.write(localZioEntry);
    }
  }
  
  /* Error */
  private static KeySpec decryptPrivateKey(byte[] paramArrayOfByte, String paramString)
    throws GeneralSecurityException
  {
    // Byte code:
    //   0: aconst_null
    //   1: checkcast 229	javax/crypto/EncryptedPrivateKeyInfo
    //   4: astore_2
    //   5: new 229	javax/crypto/EncryptedPrivateKeyInfo
    //   8: dup
    //   9: aload_0
    //   10: invokespecial 232	javax/crypto/EncryptedPrivateKeyInfo:<init>	([B)V
    //   13: astore_0
    //   14: aload_1
    //   15: invokevirtual 236	java/lang/String:toCharArray	()[C
    //   18: astore_1
    //   19: aload_0
    //   20: invokevirtual 239	javax/crypto/EncryptedPrivateKeyInfo:getAlgName	()Ljava/lang/String;
    //   23: invokestatic 244	javax/crypto/SecretKeyFactory:getInstance	(Ljava/lang/String;)Ljavax/crypto/SecretKeyFactory;
    //   26: new 246	javax/crypto/spec/PBEKeySpec
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 249	javax/crypto/spec/PBEKeySpec:<init>	([C)V
    //   34: invokevirtual 253	javax/crypto/SecretKeyFactory:generateSecret	(Ljava/security/spec/KeySpec;)Ljavax/crypto/SecretKey;
    //   37: astore_1
    //   38: aload_0
    //   39: invokevirtual 239	javax/crypto/EncryptedPrivateKeyInfo:getAlgName	()Ljava/lang/String;
    //   42: invokestatic 258	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   45: astore_2
    //   46: aload_2
    //   47: iconst_2
    //   48: aload_1
    //   49: aload_0
    //   50: invokevirtual 262	javax/crypto/EncryptedPrivateKeyInfo:getAlgParameters	()Ljava/security/AlgorithmParameters;
    //   53: invokevirtual 266	javax/crypto/Cipher:init	(ILjava/security/Key;Ljava/security/AlgorithmParameters;)V
    //   56: aload_0
    //   57: aload_2
    //   58: invokevirtual 270	javax/crypto/EncryptedPrivateKeyInfo:getKeySpec	(Ljavax/crypto/Cipher;)Ljava/security/spec/PKCS8EncodedKeySpec;
    //   61: astore_0
    //   62: aload_0
    //   63: areturn
    //   64: astore_0
    //   65: aconst_null
    //   66: checkcast 272	java/security/spec/KeySpec
    //   69: areturn
    //   70: astore_0
    //   71: getstatic 278	java/lang/System:err	Ljava/io/PrintStream;
    //   74: ldc_w 280
    //   77: invokevirtual 285	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   80: aload_0
    //   81: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramArrayOfByte	byte[]
    //   0	82	1	paramString	String
    //   4	54	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	14	64	java/io/IOException
    //   56	62	70	java/security/spec/InvalidKeySpecException
  }
  
  private static void generateSignatureFile(Manifest paramManifest, OutputStream paramOutputStream)
    throws IOException, GeneralSecurityException
  {
    paramOutputStream.write("Signature-Version: 1.0\r\n".getBytes());
    paramOutputStream.write("Created-By: 1.0 (Android SignApk)\r\n".getBytes());
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA1");
    PrintStream localPrintStream = new PrintStream(new DigestOutputStream(new ByteArrayOutputStream(), localMessageDigest), true, "UTF-8");
    paramManifest.write(localPrintStream);
    localPrintStream.flush();
    paramOutputStream.write((new StringBuffer().append("SHA1-Digest-Manifest: ").append(Base64.encode(localMessageDigest.digest())).toString() + "\r\n\r\n").getBytes());
    paramManifest = ((Collection)paramManifest.getEntries().entrySet()).iterator();
    if (!paramManifest.hasNext()) {
      return;
    }
    Object localObject = (Map.Entry)paramManifest.next();
    String str = new StringBuffer().append("Name: ").append((String)((Map.Entry)localObject).getKey()).toString() + "\r\n";
    localPrintStream.print(str);
    localObject = ((Collection)((Attributes)((Map.Entry)localObject).getValue()).entrySet()).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        localPrintStream.print("\r\n");
        localPrintStream.flush();
        paramOutputStream.write(str.getBytes());
        paramOutputStream.write((new StringBuffer().append("SHA1-Digest: ").append(Base64.encode(localMessageDigest.digest())).toString() + "\r\n\r\n").getBytes());
        break;
      }
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      localPrintStream.print(new StringBuffer().append(new StringBuffer().append(localEntry.getKey()).append(": ").toString()).append(localEntry.getValue()).toString() + "\r\n");
    }
  }
  
  private static byte[] readBytes(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
      if (i == -1) {
        return localByteArrayOutputStream.toByteArray();
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static PrivateKey readPrivateKey(InputStream paramInputStream)
    throws IOException, GeneralSecurityException
  {
    try
    {
      Object localObject3 = readBytes(paramInputStream);
      Object localObject1 = decryptPrivateKey((byte[])localObject3, "");
      if (localObject1 == null) {
        localObject1 = new PKCS8EncodedKeySpec((byte[])localObject3);
      }
      for (;;)
      {
        try
        {
          localObject3 = KeyFactory.getInstance("RSA").generatePrivate((KeySpec)localObject1);
          return (PrivateKey)localObject3;
        }
        catch (InvalidKeySpecException localInvalidKeySpecException)
        {
          localObject1 = KeyFactory.getInstance("DSA").generatePrivate((KeySpec)localObject1);
          return (PrivateKey)localObject1;
        }
      }
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  private static X509Certificate readPublicKey(InputStream paramInputStream)
    throws IOException, GeneralSecurityException
  {
    try
    {
      X509Certificate localX509Certificate = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(paramInputStream);
      return localX509Certificate;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  /* Error */
  public static void sign(String paramString1, String paramString2)
    throws IOException, GeneralSecurityException
  {
    // Byte code:
    //   0: getstatic 416	apksigner/Signer:privateKey	Ljava/security/PrivateKey;
    //   3: ifnonnull +11 -> 14
    //   6: getstatic 48	apksigner/Signer:res	Lapksigner/Signer;
    //   9: ldc 41
    //   11: invokevirtual 58	apksigner/Signer:loadKeys	(Ljava/lang/String;)V
    //   14: aconst_null
    //   15: checkcast 418	apksigner/io/ZipInput
    //   18: astore 5
    //   20: aconst_null
    //   21: checkcast 218	apksigner/io/ZipOutput
    //   24: astore 4
    //   26: aload_0
    //   27: invokestatic 421	apksigner/io/ZipInput:read	(Ljava/lang/String;)Lapksigner/io/ZipInput;
    //   30: astore_0
    //   31: aload_0
    //   32: invokevirtual 422	apksigner/io/ZipInput:getEntries	()Ljava/util/Map;
    //   35: astore 6
    //   37: new 218	apksigner/io/ZipOutput
    //   40: dup
    //   41: new 424	java/io/FileOutputStream
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 426	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   49: invokespecial 428	apksigner/io/ZipOutput:<init>	(Ljava/io/OutputStream;)V
    //   52: astore 5
    //   54: getstatic 430	apksigner/Signer:publicKey	Ljava/security/cert/X509Certificate;
    //   57: invokevirtual 434	java/security/cert/X509Certificate:getNotBefore	()Ljava/util/Date;
    //   60: invokevirtual 440	java/util/Date:getTime	()J
    //   63: ldc2_w 441
    //   66: ladd
    //   67: lstore_2
    //   68: aload 6
    //   70: invokestatic 444	apksigner/Signer:addDigestsToManifest	(Ljava/util/Map;)Ljava/util/jar/Manifest;
    //   73: astore_1
    //   74: new 73	apksigner/io/ZioEntry
    //   77: dup
    //   78: ldc 65
    //   80: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   83: astore 4
    //   85: aload 4
    //   87: lload_2
    //   88: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   91: aload_1
    //   92: aload 4
    //   94: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   97: invokevirtual 314	java/util/jar/Manifest:write	(Ljava/io/OutputStream;)V
    //   100: aload 5
    //   102: aload 4
    //   104: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   107: new 73	apksigner/io/ZioEntry
    //   110: dup
    //   111: ldc 11
    //   113: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   116: astore 7
    //   118: aload 7
    //   120: lload_2
    //   121: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   124: invokestatic 454	apksigner/Signature:getInstance	()Lapksigner/Signature;
    //   127: astore 4
    //   129: aload 4
    //   131: getstatic 416	apksigner/Signer:privateKey	Ljava/security/PrivateKey;
    //   134: invokevirtual 458	apksigner/Signature:initSign	(Ljava/security/PrivateKey;)V
    //   137: new 302	java/io/ByteArrayOutputStream
    //   140: dup
    //   141: invokespecial 303	java/io/ByteArrayOutputStream:<init>	()V
    //   144: astore 8
    //   146: aload_1
    //   147: aload 8
    //   149: invokestatic 460	apksigner/Signer:generateSignatureFile	(Ljava/util/jar/Manifest;Ljava/io/OutputStream;)V
    //   152: aload 8
    //   154: invokevirtual 366	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   157: astore 8
    //   159: aload 7
    //   161: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   164: aload 8
    //   166: invokevirtual 296	java/io/OutputStream:write	([B)V
    //   169: aload 5
    //   171: aload 7
    //   173: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   176: aload 4
    //   178: aload 8
    //   180: invokevirtual 462	apksigner/Signature:update	([B)V
    //   183: new 73	apksigner/io/ZioEntry
    //   186: dup
    //   187: ldc 8
    //   189: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   192: astore 7
    //   194: aload 7
    //   196: lload_2
    //   197: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   200: aload 4
    //   202: getstatic 430	apksigner/Signer:publicKey	Ljava/security/cert/X509Certificate;
    //   205: aload 7
    //   207: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   210: invokestatic 466	apksigner/Signer:writeSignatureBlock	(Lapksigner/Signature;Ljava/security/cert/X509Certificate;Ljava/io/OutputStream;)V
    //   213: aload 5
    //   215: aload 7
    //   217: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   220: aload_1
    //   221: aload 6
    //   223: aload 5
    //   225: lload_2
    //   226: invokestatic 468	apksigner/Signer:copyFiles	(Ljava/util/jar/Manifest;Ljava/util/Map;Lapksigner/io/ZipOutput;J)V
    //   229: aload_0
    //   230: ifnull +7 -> 237
    //   233: aload_0
    //   234: invokevirtual 469	apksigner/io/ZipInput:close	()V
    //   237: aload 5
    //   239: ifnull +8 -> 247
    //   242: aload 5
    //   244: invokevirtual 470	apksigner/io/ZipOutput:close	()V
    //   247: return
    //   248: astore_1
    //   249: aload 5
    //   251: astore_0
    //   252: aload_1
    //   253: astore 5
    //   255: aload 4
    //   257: astore_1
    //   258: aload 5
    //   260: invokevirtual 473	java/lang/Exception:printStackTrace	()V
    //   263: aload_1
    //   264: astore 5
    //   266: goto -37 -> 229
    //   269: astore_1
    //   270: aload 5
    //   272: astore_0
    //   273: aload_0
    //   274: ifnull +7 -> 281
    //   277: aload_0
    //   278: invokevirtual 469	apksigner/io/ZipInput:close	()V
    //   281: aload 4
    //   283: ifnull +8 -> 291
    //   286: aload 4
    //   288: invokevirtual 470	apksigner/io/ZipOutput:close	()V
    //   291: aload_1
    //   292: athrow
    //   293: astore_0
    //   294: return
    //   295: astore_0
    //   296: goto -5 -> 291
    //   299: astore_1
    //   300: goto -27 -> 273
    //   303: astore_1
    //   304: aload 5
    //   306: astore 4
    //   308: goto -35 -> 273
    //   311: astore 5
    //   313: aload_1
    //   314: astore 4
    //   316: aload 5
    //   318: astore_1
    //   319: goto -46 -> 273
    //   322: astore 5
    //   324: aload 4
    //   326: astore_1
    //   327: goto -69 -> 258
    //   330: astore 4
    //   332: aload 5
    //   334: astore_1
    //   335: aload 4
    //   337: astore 5
    //   339: goto -81 -> 258
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	342	0	paramString1	String
    //   0	342	1	paramString2	String
    //   67	159	2	l	long
    //   24	301	4	localObject1	Object
    //   330	6	4	localException1	Exception
    //   18	287	5	localObject2	Object
    //   311	6	5	localObject3	Object
    //   322	11	5	localException2	Exception
    //   337	1	5	localObject4	Object
    //   35	187	6	localMap	Map
    //   116	100	7	localZioEntry	ZioEntry
    //   144	35	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   26	31	248	java/lang/Exception
    //   26	31	269	finally
    //   233	237	293	java/io/IOException
    //   242	247	293	java/io/IOException
    //   277	281	295	java/io/IOException
    //   286	291	295	java/io/IOException
    //   31	54	299	finally
    //   54	229	303	finally
    //   258	263	311	finally
    //   31	54	322	java/lang/Exception
    //   54	229	330	java/lang/Exception
  }
  
  /* Error */
  public static void sign(String paramString1, String paramString2, String paramString3)
    throws IOException, GeneralSecurityException
  {
    // Byte code:
    //   0: getstatic 48	apksigner/Signer:res	Lapksigner/Signer;
    //   3: aload_2
    //   4: invokevirtual 58	apksigner/Signer:loadKeys	(Ljava/lang/String;)V
    //   7: aconst_null
    //   8: checkcast 418	apksigner/io/ZipInput
    //   11: astore 5
    //   13: aconst_null
    //   14: checkcast 218	apksigner/io/ZipOutput
    //   17: astore_2
    //   18: aload_0
    //   19: invokestatic 421	apksigner/io/ZipInput:read	(Ljava/lang/String;)Lapksigner/io/ZipInput;
    //   22: astore_0
    //   23: aload_0
    //   24: invokevirtual 422	apksigner/io/ZipInput:getEntries	()Ljava/util/Map;
    //   27: astore 6
    //   29: new 218	apksigner/io/ZipOutput
    //   32: dup
    //   33: new 424	java/io/FileOutputStream
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 426	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   41: invokespecial 428	apksigner/io/ZipOutput:<init>	(Ljava/io/OutputStream;)V
    //   44: astore 5
    //   46: getstatic 430	apksigner/Signer:publicKey	Ljava/security/cert/X509Certificate;
    //   49: invokevirtual 434	java/security/cert/X509Certificate:getNotBefore	()Ljava/util/Date;
    //   52: invokevirtual 440	java/util/Date:getTime	()J
    //   55: ldc2_w 441
    //   58: ladd
    //   59: lstore_3
    //   60: aload 6
    //   62: invokestatic 444	apksigner/Signer:addDigestsToManifest	(Ljava/util/Map;)Ljava/util/jar/Manifest;
    //   65: astore_1
    //   66: new 73	apksigner/io/ZioEntry
    //   69: dup
    //   70: ldc 65
    //   72: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   75: astore_2
    //   76: aload_2
    //   77: lload_3
    //   78: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   81: aload_1
    //   82: aload_2
    //   83: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   86: invokevirtual 314	java/util/jar/Manifest:write	(Ljava/io/OutputStream;)V
    //   89: aload 5
    //   91: aload_2
    //   92: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   95: new 73	apksigner/io/ZioEntry
    //   98: dup
    //   99: ldc 11
    //   101: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   104: astore 7
    //   106: aload 7
    //   108: lload_3
    //   109: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   112: invokestatic 454	apksigner/Signature:getInstance	()Lapksigner/Signature;
    //   115: astore_2
    //   116: aload_2
    //   117: getstatic 416	apksigner/Signer:privateKey	Ljava/security/PrivateKey;
    //   120: invokevirtual 458	apksigner/Signature:initSign	(Ljava/security/PrivateKey;)V
    //   123: new 302	java/io/ByteArrayOutputStream
    //   126: dup
    //   127: invokespecial 303	java/io/ByteArrayOutputStream:<init>	()V
    //   130: astore 8
    //   132: aload_1
    //   133: aload 8
    //   135: invokestatic 460	apksigner/Signer:generateSignatureFile	(Ljava/util/jar/Manifest;Ljava/io/OutputStream;)V
    //   138: aload 8
    //   140: invokevirtual 366	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   143: astore 8
    //   145: aload 7
    //   147: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   150: aload 8
    //   152: invokevirtual 296	java/io/OutputStream:write	([B)V
    //   155: aload 5
    //   157: aload 7
    //   159: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   162: aload_2
    //   163: aload 8
    //   165: invokevirtual 462	apksigner/Signature:update	([B)V
    //   168: new 73	apksigner/io/ZioEntry
    //   171: dup
    //   172: ldc 8
    //   174: invokespecial 445	apksigner/io/ZioEntry:<init>	(Ljava/lang/String;)V
    //   177: astore 7
    //   179: aload 7
    //   181: lload_3
    //   182: invokevirtual 216	apksigner/io/ZioEntry:setTime	(J)V
    //   185: aload_2
    //   186: getstatic 430	apksigner/Signer:publicKey	Ljava/security/cert/X509Certificate;
    //   189: aload 7
    //   191: invokevirtual 449	apksigner/io/ZioEntry:getOutputStream	()Ljava/io/OutputStream;
    //   194: invokestatic 466	apksigner/Signer:writeSignatureBlock	(Lapksigner/Signature;Ljava/security/cert/X509Certificate;Ljava/io/OutputStream;)V
    //   197: aload 5
    //   199: aload 7
    //   201: invokevirtual 222	apksigner/io/ZipOutput:write	(Lapksigner/io/ZioEntry;)V
    //   204: aload_1
    //   205: aload 6
    //   207: aload 5
    //   209: lload_3
    //   210: invokestatic 468	apksigner/Signer:copyFiles	(Ljava/util/jar/Manifest;Ljava/util/Map;Lapksigner/io/ZipOutput;J)V
    //   213: aload_0
    //   214: ifnull +7 -> 221
    //   217: aload_0
    //   218: invokevirtual 469	apksigner/io/ZipInput:close	()V
    //   221: aload 5
    //   223: ifnull +8 -> 231
    //   226: aload 5
    //   228: invokevirtual 470	apksigner/io/ZipOutput:close	()V
    //   231: return
    //   232: astore_1
    //   233: aload 5
    //   235: astore_0
    //   236: aload_1
    //   237: astore 5
    //   239: aload_2
    //   240: astore_1
    //   241: aload 5
    //   243: invokevirtual 473	java/lang/Exception:printStackTrace	()V
    //   246: aload_1
    //   247: astore 5
    //   249: goto -36 -> 213
    //   252: astore_1
    //   253: aload 5
    //   255: astore_0
    //   256: aload_0
    //   257: ifnull +7 -> 264
    //   260: aload_0
    //   261: invokevirtual 469	apksigner/io/ZipInput:close	()V
    //   264: aload_2
    //   265: ifnull +7 -> 272
    //   268: aload_2
    //   269: invokevirtual 470	apksigner/io/ZipOutput:close	()V
    //   272: aload_1
    //   273: athrow
    //   274: astore_0
    //   275: return
    //   276: astore_0
    //   277: goto -5 -> 272
    //   280: astore_1
    //   281: goto -25 -> 256
    //   284: astore_1
    //   285: aload 5
    //   287: astore_2
    //   288: goto -32 -> 256
    //   291: astore 5
    //   293: aload_1
    //   294: astore_2
    //   295: aload 5
    //   297: astore_1
    //   298: goto -42 -> 256
    //   301: astore 5
    //   303: aload_2
    //   304: astore_1
    //   305: goto -64 -> 241
    //   308: astore_2
    //   309: aload 5
    //   311: astore_1
    //   312: aload_2
    //   313: astore 5
    //   315: goto -74 -> 241
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	paramString1	String
    //   0	318	1	paramString2	String
    //   0	318	2	paramString3	String
    //   59	151	3	l	long
    //   11	275	5	localObject1	Object
    //   291	5	5	localObject2	Object
    //   301	9	5	localException	Exception
    //   313	1	5	str	String
    //   27	179	6	localMap	Map
    //   104	96	7	localZioEntry	ZioEntry
    //   130	34	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   18	23	232	java/lang/Exception
    //   18	23	252	finally
    //   217	221	274	java/io/IOException
    //   226	231	274	java/io/IOException
    //   260	264	276	java/io/IOException
    //   268	272	276	java/io/IOException
    //   23	46	280	finally
    //   46	213	284	finally
    //   241	246	291	finally
    //   23	46	301	java/lang/Exception
    //   46	213	308	java/lang/Exception
  }
  
  public static void sign(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    res.loadKeys("testkey");
    paramString3 = (ZipInput)null;
    paramString3 = (ZipOutput)null;
    paramString1 = ZipInput.read(paramString1);
    paramString3 = paramString1.getEntries();
    paramString2 = new ZipOutput(new FileOutputStream(paramString2));
    long l = publicKey.getNotBefore().getTime() + 3600000L;
    paramString4 = addDigestsToManifest(paramString3);
    Object localObject1 = new ZioEntry("META-INF/MANIFEST.MF");
    ((ZioEntry)localObject1).setTime(l);
    paramString4.write(((ZioEntry)localObject1).getOutputStream());
    paramString2.write((ZioEntry)localObject1);
    ZioEntry localZioEntry = new ZioEntry("META-INF/CERT.SF");
    localZioEntry.setTime(l);
    localObject1 = Signature.getInstance();
    ((Signature)localObject1).initSign(privateKey);
    Object localObject2 = new ByteArrayOutputStream();
    generateSignatureFile(paramString4, (OutputStream)localObject2);
    localObject2 = ((ByteArrayOutputStream)localObject2).toByteArray();
    localZioEntry.getOutputStream().write((byte[])localObject2);
    paramString2.write(localZioEntry);
    ((Signature)localObject1).update((byte[])localObject2);
    localZioEntry = new ZioEntry("META-INF/CERT.RSA");
    localZioEntry.setTime(l);
    writeSignatureBlock((Signature)localObject1, publicKey, localZioEntry.getOutputStream());
    paramString2.write(localZioEntry);
    copyFiles(paramString4, paramString3, paramString2, l);
    paramString1.close();
    paramString2.close();
  }
  
  private static void writeSignatureBlock(Signature paramSignature, X509Certificate paramX509Certificate, OutputStream paramOutputStream)
    throws IOException, GeneralSecurityException, Exception
  {
    paramOutputStream.write(sigBlockTemp);
    paramOutputStream.write(paramSignature.sign());
  }
  
  public void loadKeys(String paramString)
    throws IOException, GeneralSecurityException
  {
    privateKey = readPrivateKey(getClass().getResource(new StringBuffer().append("/assets/keys/").append(paramString).toString() + ".pk8").openStream());
    publicKey = readPublicKey(getClass().getResource(new StringBuffer().append("/assets/keys/").append(paramString).toString() + ".x509.pem").openStream());
    sigBlockTemp = readBytes(getClass().getResource(new StringBuffer().append("/assets/keys/").append(paramString).toString() + ".sbt").openStream());
  }
  
  public void loadKeys(String paramString1, String paramString2)
    throws IOException, GeneralSecurityException
  {
    privateKey = readPrivateKey(new FileInputStream(new StringBuffer().append(paramString1).append(paramString2).toString() + ".pk8"));
    publicKey = readPublicKey(new FileInputStream(new StringBuffer().append(paramString1).append(paramString2).toString() + ".x509.pem"));
    sigBlockTemp = readBytes(new FileInputStream(new StringBuffer().append(paramString1).append(paramString2).toString() + ".sbt"));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\Signer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */