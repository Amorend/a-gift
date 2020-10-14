package android.support.v4.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;

@TargetApi(21)
@RequiresApi(21)
class DocumentsContractApi21
{
  private static final String TAG = "DocumentFile";
  
  private static void closeQuietly(AutoCloseable paramAutoCloseable)
  {
    if (paramAutoCloseable != null) {}
    try
    {
      paramAutoCloseable.close();
      return;
    }
    catch (RuntimeException paramAutoCloseable)
    {
      throw paramAutoCloseable;
    }
    catch (Exception paramAutoCloseable) {}
  }
  
  public static Uri createDirectory(Context paramContext, Uri paramUri, String paramString)
  {
    return createFile(paramContext, paramUri, "vnd.android.document/directory", paramString);
  }
  
  public static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    return DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
  }
  
  /* Error */
  public static Uri[] listFiles(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_1
    //   6: aload_1
    //   7: invokestatic 54	android/provider/DocumentsContract:getDocumentId	(Landroid/net/Uri;)Ljava/lang/String;
    //   10: invokestatic 58	android/provider/DocumentsContract:buildChildDocumentsUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   13: astore_2
    //   14: new 60	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 61	java/util/ArrayList:<init>	()V
    //   21: astore_3
    //   22: aload_0
    //   23: aload_2
    //   24: iconst_1
    //   25: anewarray 63	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc 65
    //   32: aastore
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: invokevirtual 71	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   39: astore_2
    //   40: aload_2
    //   41: astore_0
    //   42: aload_2
    //   43: invokeinterface 77 1 0
    //   48: ifeq +71 -> 119
    //   51: aload_2
    //   52: astore_0
    //   53: aload_3
    //   54: aload_1
    //   55: aload_2
    //   56: iconst_0
    //   57: invokeinterface 81 2 0
    //   62: invokestatic 84	android/provider/DocumentsContract:buildDocumentUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   65: invokevirtual 88	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   68: pop
    //   69: goto -29 -> 40
    //   72: astore_1
    //   73: aload_2
    //   74: astore_0
    //   75: ldc 12
    //   77: new 90	java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   84: ldc 93
    //   86: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_1
    //   90: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokestatic 110	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   99: pop
    //   100: aload_2
    //   101: invokestatic 112	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   104: aload_3
    //   105: aload_3
    //   106: invokevirtual 116	java/util/ArrayList:size	()I
    //   109: anewarray 118	android/net/Uri
    //   112: invokevirtual 122	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   115: checkcast 124	[Landroid/net/Uri;
    //   118: areturn
    //   119: aload_2
    //   120: invokestatic 112	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   123: goto -19 -> 104
    //   126: astore_1
    //   127: aconst_null
    //   128: astore_0
    //   129: aload_0
    //   130: invokestatic 112	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   133: aload_1
    //   134: athrow
    //   135: astore_1
    //   136: goto -7 -> 129
    //   139: astore_1
    //   140: aconst_null
    //   141: astore_2
    //   142: goto -69 -> 73
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	paramContext	Context
    //   0	145	1	paramUri	Uri
    //   13	129	2	localObject	Object
    //   21	85	3	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   42	51	72	java/lang/Exception
    //   53	69	72	java/lang/Exception
    //   22	40	126	finally
    //   42	51	135	finally
    //   53	69	135	finally
    //   75	100	135	finally
    //   22	40	139	java/lang/Exception
  }
  
  public static Uri prepareTreeUri(Uri paramUri)
  {
    return DocumentsContract.buildDocumentUriUsingTree(paramUri, DocumentsContract.getTreeDocumentId(paramUri));
  }
  
  public static Uri renameTo(Context paramContext, Uri paramUri, String paramString)
  {
    return DocumentsContract.renameDocument(paramContext.getContentResolver(), paramUri, paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\provider\DocumentsContractApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */