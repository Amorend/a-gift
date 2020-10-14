package com.tencent.open.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.tencent.open.utils.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class f
  extends SQLiteOpenHelper
{
  protected static final String[] a = { "key" };
  protected static f b;
  
  public f(Context paramContext)
  {
    super(paramContext, "sdk_report.db", null, 2);
  }
  
  public static f a()
  {
    try
    {
      if (b == null) {
        b = new f(e.a());
      }
      f localf = b;
      return localf;
    }
    finally {}
  }
  
  /* Error */
  public List<Serializable> a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 41	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 43	java/util/ArrayList:<init>	()V
    //   9: invokestatic 49	java/util/Collections:synchronizedList	(Ljava/util/List;)Ljava/util/List;
    //   12: astore 10
    //   14: aload_1
    //   15: invokestatic 55	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   18: istore_2
    //   19: iload_2
    //   20: ifeq +8 -> 28
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 10
    //   27: areturn
    //   28: aload_0
    //   29: invokevirtual 59	com/tencent/open/b/f:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore 9
    //   34: aload 9
    //   36: ifnonnull +6 -> 42
    //   39: goto -16 -> 23
    //   42: aconst_null
    //   43: astore 4
    //   45: aconst_null
    //   46: astore_3
    //   47: aload 9
    //   49: ldc 61
    //   51: aconst_null
    //   52: ldc 63
    //   54: iconst_1
    //   55: anewarray 12	java/lang/String
    //   58: dup
    //   59: iconst_0
    //   60: aload_1
    //   61: aastore
    //   62: aconst_null
    //   63: aconst_null
    //   64: aconst_null
    //   65: invokevirtual 69	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   68: astore_1
    //   69: aload_1
    //   70: ifnull +158 -> 228
    //   73: aload_1
    //   74: astore_3
    //   75: aload_1
    //   76: astore 4
    //   78: aload_1
    //   79: invokeinterface 75 1 0
    //   84: ifle +144 -> 228
    //   87: aload_1
    //   88: astore_3
    //   89: aload_1
    //   90: astore 4
    //   92: aload_1
    //   93: invokeinterface 79 1 0
    //   98: pop
    //   99: aload_1
    //   100: astore_3
    //   101: aload_1
    //   102: astore 4
    //   104: new 81	java/io/ByteArrayInputStream
    //   107: dup
    //   108: aload_1
    //   109: aload_1
    //   110: ldc 83
    //   112: invokeinterface 87 2 0
    //   117: invokeinterface 91 2 0
    //   122: invokespecial 94	java/io/ByteArrayInputStream:<init>	([B)V
    //   125: astore 11
    //   127: aconst_null
    //   128: astore 6
    //   130: aconst_null
    //   131: astore 5
    //   133: aconst_null
    //   134: astore 8
    //   136: new 96	java/io/ObjectInputStream
    //   139: dup
    //   140: aload 11
    //   142: invokespecial 99	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   145: astore 7
    //   147: aload 7
    //   149: astore 5
    //   151: aload 7
    //   153: astore 6
    //   155: aload 7
    //   157: invokevirtual 103	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   160: checkcast 105	java/io/Serializable
    //   163: astore_3
    //   164: aload_3
    //   165: astore 5
    //   167: aload 7
    //   169: ifnull +13 -> 182
    //   172: aload_1
    //   173: astore_3
    //   174: aload_1
    //   175: astore 4
    //   177: aload 7
    //   179: invokevirtual 108	java/io/ObjectInputStream:close	()V
    //   182: aload_1
    //   183: astore_3
    //   184: aload_1
    //   185: astore 4
    //   187: aload 11
    //   189: invokevirtual 109	java/io/ByteArrayInputStream:close	()V
    //   192: aload 5
    //   194: ifnull +18 -> 212
    //   197: aload_1
    //   198: astore_3
    //   199: aload_1
    //   200: astore 4
    //   202: aload 10
    //   204: aload 5
    //   206: invokeinterface 115 2 0
    //   211: pop
    //   212: aload_1
    //   213: astore_3
    //   214: aload_1
    //   215: astore 4
    //   217: aload_1
    //   218: invokeinterface 118 1 0
    //   223: istore_2
    //   224: iload_2
    //   225: ifne -126 -> 99
    //   228: aload_1
    //   229: ifnull +9 -> 238
    //   232: aload_1
    //   233: invokeinterface 119 1 0
    //   238: iconst_0
    //   239: ifeq +11 -> 250
    //   242: new 121	java/lang/NullPointerException
    //   245: dup
    //   246: invokespecial 122	java/lang/NullPointerException:<init>	()V
    //   249: athrow
    //   250: aload 9
    //   252: ifnull +8 -> 260
    //   255: aload 9
    //   257: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   260: goto -237 -> 23
    //   263: astore_3
    //   264: goto -82 -> 182
    //   267: astore_3
    //   268: goto -76 -> 192
    //   271: astore_3
    //   272: aload 5
    //   274: ifnull +13 -> 287
    //   277: aload_1
    //   278: astore_3
    //   279: aload_1
    //   280: astore 4
    //   282: aload 5
    //   284: invokevirtual 108	java/io/ObjectInputStream:close	()V
    //   287: aload_1
    //   288: astore_3
    //   289: aload_1
    //   290: astore 4
    //   292: aload 11
    //   294: invokevirtual 109	java/io/ByteArrayInputStream:close	()V
    //   297: aload 8
    //   299: astore 5
    //   301: goto -109 -> 192
    //   304: astore_3
    //   305: goto -18 -> 287
    //   308: astore_3
    //   309: aload 8
    //   311: astore 5
    //   313: goto -121 -> 192
    //   316: astore 5
    //   318: aload 6
    //   320: ifnull +13 -> 333
    //   323: aload_1
    //   324: astore_3
    //   325: aload_1
    //   326: astore 4
    //   328: aload 6
    //   330: invokevirtual 108	java/io/ObjectInputStream:close	()V
    //   333: aload_1
    //   334: astore_3
    //   335: aload_1
    //   336: astore 4
    //   338: aload 11
    //   340: invokevirtual 109	java/io/ByteArrayInputStream:close	()V
    //   343: aload_1
    //   344: astore_3
    //   345: aload_1
    //   346: astore 4
    //   348: aload 5
    //   350: athrow
    //   351: astore_1
    //   352: aload_3
    //   353: astore 4
    //   355: ldc 125
    //   357: ldc 127
    //   359: aload_1
    //   360: invokestatic 132	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   363: aload_3
    //   364: ifnull +9 -> 373
    //   367: aload_3
    //   368: invokeinterface 119 1 0
    //   373: iconst_0
    //   374: ifeq +11 -> 385
    //   377: new 121	java/lang/NullPointerException
    //   380: dup
    //   381: invokespecial 122	java/lang/NullPointerException:<init>	()V
    //   384: athrow
    //   385: aload 9
    //   387: ifnull -127 -> 260
    //   390: aload 9
    //   392: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   395: goto -135 -> 260
    //   398: astore_1
    //   399: aload_1
    //   400: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   403: goto -153 -> 250
    //   406: astore_1
    //   407: aload_0
    //   408: monitorexit
    //   409: aload_1
    //   410: athrow
    //   411: astore_1
    //   412: aload_1
    //   413: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   416: goto -31 -> 385
    //   419: astore_1
    //   420: aload 4
    //   422: ifnull +10 -> 432
    //   425: aload 4
    //   427: invokeinterface 119 1 0
    //   432: iconst_0
    //   433: ifeq +11 -> 444
    //   436: new 121	java/lang/NullPointerException
    //   439: dup
    //   440: invokespecial 122	java/lang/NullPointerException:<init>	()V
    //   443: athrow
    //   444: aload 9
    //   446: ifnull +8 -> 454
    //   449: aload 9
    //   451: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   454: aload_1
    //   455: athrow
    //   456: astore_3
    //   457: aload_3
    //   458: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   461: goto -17 -> 444
    //   464: astore_3
    //   465: goto -132 -> 333
    //   468: astore_3
    //   469: goto -126 -> 343
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	472	0	this	f
    //   0	472	1	paramString	String
    //   18	207	2	bool	boolean
    //   46	168	3	localObject1	Object
    //   263	1	3	localIOException1	IOException
    //   267	1	3	localIOException2	IOException
    //   271	1	3	localException	Exception
    //   278	11	3	str1	String
    //   304	1	3	localIOException3	IOException
    //   308	1	3	localIOException4	IOException
    //   324	44	3	str2	String
    //   456	2	3	localIOException5	IOException
    //   464	1	3	localIOException6	IOException
    //   468	1	3	localIOException7	IOException
    //   43	383	4	localObject2	Object
    //   131	181	5	localObject3	Object
    //   316	33	5	localObject4	Object
    //   128	201	6	localObject5	Object
    //   145	33	7	localObjectInputStream	java.io.ObjectInputStream
    //   134	176	8	localObject6	Object
    //   32	418	9	localSQLiteDatabase	SQLiteDatabase
    //   12	191	10	localList	List
    //   125	214	11	localByteArrayInputStream	java.io.ByteArrayInputStream
    // Exception table:
    //   from	to	target	type
    //   177	182	263	java/io/IOException
    //   187	192	267	java/io/IOException
    //   136	147	271	java/lang/Exception
    //   155	164	271	java/lang/Exception
    //   282	287	304	java/io/IOException
    //   292	297	308	java/io/IOException
    //   136	147	316	finally
    //   155	164	316	finally
    //   47	69	351	java/lang/Exception
    //   78	87	351	java/lang/Exception
    //   92	99	351	java/lang/Exception
    //   104	127	351	java/lang/Exception
    //   177	182	351	java/lang/Exception
    //   187	192	351	java/lang/Exception
    //   202	212	351	java/lang/Exception
    //   217	224	351	java/lang/Exception
    //   282	287	351	java/lang/Exception
    //   292	297	351	java/lang/Exception
    //   328	333	351	java/lang/Exception
    //   338	343	351	java/lang/Exception
    //   348	351	351	java/lang/Exception
    //   242	250	398	java/io/IOException
    //   2	19	406	finally
    //   28	34	406	finally
    //   232	238	406	finally
    //   242	250	406	finally
    //   255	260	406	finally
    //   367	373	406	finally
    //   377	385	406	finally
    //   390	395	406	finally
    //   399	403	406	finally
    //   412	416	406	finally
    //   425	432	406	finally
    //   436	444	406	finally
    //   449	454	406	finally
    //   454	456	406	finally
    //   457	461	406	finally
    //   377	385	411	java/io/IOException
    //   47	69	419	finally
    //   78	87	419	finally
    //   92	99	419	finally
    //   104	127	419	finally
    //   177	182	419	finally
    //   187	192	419	finally
    //   202	212	419	finally
    //   217	224	419	finally
    //   282	287	419	finally
    //   292	297	419	finally
    //   328	333	419	finally
    //   338	343	419	finally
    //   348	351	419	finally
    //   355	363	419	finally
    //   436	444	456	java/io/IOException
    //   328	333	464	java/io/IOException
    //   338	343	468	java/io/IOException
  }
  
  public void a(String paramString, List<Serializable> paramList)
  {
    for (;;)
    {
      try
      {
        int i = paramList.size();
        if (i == 0) {
          return;
        }
        if (i <= 20)
        {
          if (!TextUtils.isEmpty(paramString))
          {
            b(paramString);
            SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
            if (localSQLiteDatabase != null)
            {
              localSQLiteDatabase.beginTransaction();
              try
              {
                ContentValues localContentValues = new ContentValues();
                int j = 0;
                if (j < i)
                {
                  Serializable localSerializable = (Serializable)paramList.get(j);
                  Object localObject2;
                  Object localObject1;
                  if (localSerializable != null)
                  {
                    localContentValues.put("type", paramString);
                    localByteArrayOutputStream = new ByteArrayOutputStream(512);
                    localObject2 = null;
                    localObject1 = null;
                  }
                  try
                  {
                    localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
                    localObject1 = localObjectOutputStream;
                    localObject2 = localObjectOutputStream;
                    localObjectOutputStream.writeObject(localSerializable);
                  }
                  catch (IOException localIOException5)
                  {
                    ObjectOutputStream localObjectOutputStream;
                    if (localIOException2 == null) {
                      continue;
                    }
                    try
                    {
                      localIOException2.close();
                    }
                    catch (IOException localIOException3)
                    {
                      try
                      {
                        localByteArrayOutputStream.close();
                      }
                      catch (IOException localIOException4) {}
                      localIOException3 = localIOException3;
                      continue;
                    }
                    continue;
                  }
                  finally
                  {
                    if (localIOException5 == null) {
                      continue;
                    }
                  }
                  try
                  {
                    localObjectOutputStream.close();
                  }
                  catch (IOException localIOException1)
                  {
                    try
                    {
                      localByteArrayOutputStream.close();
                      localContentValues.put("blob", localByteArrayOutputStream.toByteArray());
                      localSQLiteDatabase.insert("via_cgi_report", null, localContentValues);
                      localContentValues.clear();
                      j += 1;
                      continue;
                      localIOException1 = localIOException1;
                      continue;
                    }
                    catch (IOException localIOException2)
                    {
                      continue;
                    }
                  }
                }
              }
              catch (Exception paramList)
              {
                ByteArrayOutputStream localByteArrayOutputStream;
                localSQLiteDatabase.setTransactionSuccessful();
                localSQLiteDatabase.endTransaction();
                if (localSQLiteDatabase == null) {
                  continue;
                }
                localSQLiteDatabase.close();
                continue;
              }
              finally
              {
                localSQLiteDatabase.endTransaction();
                if (localSQLiteDatabase != null) {
                  localSQLiteDatabase.close();
                }
              }
            }
          }
        }
        else {
          i = 20;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic 55	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokevirtual 147	com/tencent/open/b/f:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   18: astore_3
    //   19: aload_3
    //   20: ifnonnull +6 -> 26
    //   23: goto -12 -> 11
    //   26: aload_3
    //   27: ldc 61
    //   29: ldc 63
    //   31: iconst_1
    //   32: anewarray 12	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: aload_1
    //   38: aastore
    //   39: invokevirtual 209	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   42: pop
    //   43: aload_3
    //   44: ifnull +7 -> 51
    //   47: aload_3
    //   48: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   51: goto -40 -> 11
    //   54: astore_1
    //   55: ldc 125
    //   57: ldc -45
    //   59: aload_1
    //   60: invokestatic 132	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   63: aload_3
    //   64: ifnull -13 -> 51
    //   67: aload_3
    //   68: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   71: goto -20 -> 51
    //   74: astore_1
    //   75: aload_3
    //   76: ifnull +7 -> 83
    //   79: aload_3
    //   80: invokevirtual 123	android/database/sqlite/SQLiteDatabase:close	()V
    //   83: aload_1
    //   84: athrow
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	f
    //   0	90	1	paramString	String
    //   6	2	2	bool	boolean
    //   18	62	3	localSQLiteDatabase	SQLiteDatabase
    // Exception table:
    //   from	to	target	type
    //   26	43	54	java/lang/Exception
    //   26	43	74	finally
    //   55	63	74	finally
    //   2	7	85	finally
    //   14	19	85	finally
    //   47	51	85	finally
    //   67	71	85	finally
    //   79	83	85	finally
    //   83	85	85	finally
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS via_cgi_report");
    onCreate(paramSQLiteDatabase);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\b\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */