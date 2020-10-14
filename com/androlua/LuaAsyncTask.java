package com.androlua;

import com.androlua.util.AsyncTaskX;
import com.androlua.util.AsyncTaskX.Status;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;

public class LuaAsyncTask
  extends AsyncTaskX
  implements LuaGcable
{
  private Object[] a;
  private LuaState b;
  private LuaContext c;
  private byte[] d;
  private long e = 0L;
  private LuaObject f;
  private LuaObject g;
  
  static
  {
    AsyncTaskX.setDefaultExecutor(AsyncTaskX.THREAD_POOL_EXECUTOR);
  }
  
  public LuaAsyncTask(LuaContext paramLuaContext, long paramLong, LuaObject paramLuaObject)
  {
    paramLuaContext.regGc(this);
    this.c = paramLuaContext;
    this.e = paramLong;
    this.f = paramLuaObject;
  }
  
  public LuaAsyncTask(LuaContext paramLuaContext, LuaObject paramLuaObject1, LuaObject paramLuaObject2)
  {
    paramLuaContext.regGc(this);
    this.c = paramLuaContext;
    this.d = paramLuaObject1.dump();
    this.f = paramLuaObject2;
    paramLuaContext = paramLuaObject1.getLuaState().getLuaObject("luajava").getField("imported");
    if (!paramLuaContext.isNil()) {
      this.a = paramLuaContext.asArray();
    }
  }
  
  public LuaAsyncTask(LuaContext paramLuaContext, LuaObject paramLuaObject1, LuaObject paramLuaObject2, LuaObject paramLuaObject3)
  {
    paramLuaContext.regGc(this);
    this.c = paramLuaContext;
    this.d = paramLuaObject1.dump();
    this.g = paramLuaObject2;
    this.f = paramLuaObject3;
  }
  
  public LuaAsyncTask(LuaContext paramLuaContext, String paramString, LuaObject paramLuaObject)
  {
    paramLuaContext.regGc(this);
    this.c = paramLuaContext;
    this.d = paramString.getBytes();
    this.f = paramLuaObject;
  }
  
  private String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown error ");
      localStringBuilder.append(paramInt);
      return localStringBuilder.toString();
    case 6: 
      return "error error";
    case 5: 
      return "GC error";
    case 4: 
      return "Out of memory";
    case 3: 
      return "Syntax error";
    case 2: 
      return "Runtime error";
    }
    return "Yield error";
  }
  
  /* Error */
  protected Object a(Object[] paramArrayOfObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 38	com/androlua/LuaAsyncTask:e	J
    //   4: lconst_0
    //   5: lcmp
    //   6: ifeq +12 -> 18
    //   9: aload_0
    //   10: getfield 38	com/androlua/LuaAsyncTask:e	J
    //   13: invokestatic 133	java/lang/Thread:sleep	(J)V
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: invokestatic 138	com/luajava/LuaStateFactory:newLuaState	()Lcom/luajava/LuaState;
    //   22: putfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   25: aload_0
    //   26: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   29: invokevirtual 143	com/luajava/LuaState:openLibs	()V
    //   32: aload_0
    //   33: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   36: aload_0
    //   37: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   40: invokevirtual 147	com/luajava/LuaState:pushJavaObject	(Ljava/lang/Object;)V
    //   43: aload_0
    //   44: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   47: instanceof 149
    //   50: ifeq +23 -> 73
    //   53: aload_0
    //   54: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   57: astore 5
    //   59: ldc -105
    //   61: astore 6
    //   63: aload 5
    //   65: aload 6
    //   67: invokevirtual 155	com/luajava/LuaState:setGlobal	(Ljava/lang/String;)V
    //   70: goto +26 -> 96
    //   73: aload_0
    //   74: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   77: instanceof 157
    //   80: ifeq +16 -> 96
    //   83: aload_0
    //   84: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   87: astore 5
    //   89: ldc -97
    //   91: astore 6
    //   93: goto -30 -> 63
    //   96: aload_0
    //   97: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   100: aload_0
    //   101: invokevirtual 147	com/luajava/LuaState:pushJavaObject	(Ljava/lang/Object;)V
    //   104: aload_0
    //   105: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   108: ldc -95
    //   110: invokevirtual 155	com/luajava/LuaState:setGlobal	(Ljava/lang/String;)V
    //   113: aload_0
    //   114: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   117: aload_0
    //   118: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   121: invokevirtual 165	com/luajava/LuaState:pushContext	(Lcom/androlua/LuaContext;)V
    //   124: aload_0
    //   125: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   128: ldc 63
    //   130: invokevirtual 169	com/luajava/LuaState:getGlobal	(Ljava/lang/String;)I
    //   133: pop
    //   134: aload_0
    //   135: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   138: aload_0
    //   139: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   142: invokeinterface 172 1 0
    //   147: invokevirtual 175	com/luajava/LuaState:pushString	(Ljava/lang/String;)V
    //   150: aload_0
    //   151: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   154: bipush -2
    //   156: ldc -79
    //   158: invokevirtual 181	com/luajava/LuaState:setField	(ILjava/lang/String;)V
    //   161: aload_0
    //   162: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   165: iconst_1
    //   166: invokevirtual 185	com/luajava/LuaState:pop	(I)V
    //   169: new 187	com/androlua/LuaPrint
    //   172: dup
    //   173: aload_0
    //   174: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   177: aload_0
    //   178: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   181: invokespecial 190	com/androlua/LuaPrint:<init>	(Lcom/androlua/LuaContext;Lcom/luajava/LuaState;)V
    //   184: ldc -64
    //   186: invokevirtual 197	com/luajava/JavaFunction:register	(Ljava/lang/String;)V
    //   189: new 8	com/androlua/LuaAsyncTask$1
    //   192: dup
    //   193: aload_0
    //   194: aload_0
    //   195: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   198: invokespecial 200	com/androlua/LuaAsyncTask$1:<init>	(Lcom/androlua/LuaAsyncTask;Lcom/luajava/LuaState;)V
    //   201: ldc -54
    //   203: invokevirtual 197	com/luajava/JavaFunction:register	(Ljava/lang/String;)V
    //   206: aload_0
    //   207: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   210: ldc -52
    //   212: invokevirtual 169	com/luajava/LuaState:getGlobal	(Ljava/lang/String;)I
    //   215: pop
    //   216: aload_0
    //   217: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   220: aload_0
    //   221: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   224: invokeinterface 207 1 0
    //   229: invokevirtual 175	com/luajava/LuaState:pushString	(Ljava/lang/String;)V
    //   232: aload_0
    //   233: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   236: bipush -2
    //   238: ldc -47
    //   240: invokevirtual 181	com/luajava/LuaState:setField	(ILjava/lang/String;)V
    //   243: aload_0
    //   244: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   247: aload_0
    //   248: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   251: invokeinterface 212 1 0
    //   256: invokevirtual 175	com/luajava/LuaState:pushString	(Ljava/lang/String;)V
    //   259: aload_0
    //   260: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   263: bipush -2
    //   265: ldc -42
    //   267: invokevirtual 181	com/luajava/LuaState:setField	(ILjava/lang/String;)V
    //   270: aload_0
    //   271: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   274: iconst_1
    //   275: invokevirtual 185	com/luajava/LuaState:pop	(I)V
    //   278: goto +18 -> 296
    //   281: astore 5
    //   283: aload_0
    //   284: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   287: ldc -40
    //   289: aload 5
    //   291: invokeinterface 220 3 0
    //   296: aload_0
    //   297: getfield 84	com/androlua/LuaAsyncTask:a	[Ljava/lang/Object;
    //   300: astore 5
    //   302: iconst_0
    //   303: istore_3
    //   304: aload 5
    //   306: ifnull +86 -> 392
    //   309: aload_0
    //   310: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   313: ldc -34
    //   315: invokevirtual 69	com/luajava/LuaState:getLuaObject	(Ljava/lang/String;)Lcom/luajava/LuaObject;
    //   318: astore 5
    //   320: aload 5
    //   322: iconst_1
    //   323: anewarray 224	java/lang/Object
    //   326: dup
    //   327: iconst_0
    //   328: ldc -30
    //   330: aastore
    //   331: invokevirtual 229	com/luajava/LuaObject:call	([Ljava/lang/Object;)Ljava/lang/Object;
    //   334: pop
    //   335: aload_0
    //   336: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   339: ldc -30
    //   341: invokevirtual 69	com/luajava/LuaState:getLuaObject	(Ljava/lang/String;)Lcom/luajava/LuaObject;
    //   344: astore 5
    //   346: aload_0
    //   347: getfield 84	com/androlua/LuaAsyncTask:a	[Ljava/lang/Object;
    //   350: astore 6
    //   352: aload 6
    //   354: arraylength
    //   355: istore 4
    //   357: iconst_0
    //   358: istore_2
    //   359: iload_2
    //   360: iload 4
    //   362: if_icmpge +30 -> 392
    //   365: aload 5
    //   367: iconst_1
    //   368: anewarray 224	java/lang/Object
    //   371: dup
    //   372: iconst_0
    //   373: aload 6
    //   375: iload_2
    //   376: aaload
    //   377: invokevirtual 230	java/lang/Object:toString	()Ljava/lang/String;
    //   380: aastore
    //   381: invokevirtual 229	com/luajava/LuaObject:call	([Ljava/lang/Object;)Ljava/lang/Object;
    //   384: pop
    //   385: iload_2
    //   386: iconst_1
    //   387: iadd
    //   388: istore_2
    //   389: goto -30 -> 359
    //   392: aload_0
    //   393: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   396: iconst_0
    //   397: invokevirtual 233	com/luajava/LuaState:setTop	(I)V
    //   400: aload_0
    //   401: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   404: aload_0
    //   405: getfield 57	com/androlua/LuaAsyncTask:d	[B
    //   408: ldc -21
    //   410: invokevirtual 239	com/luajava/LuaState:LloadBuffer	([BLjava/lang/String;)I
    //   413: istore 4
    //   415: iload 4
    //   417: istore_2
    //   418: iload 4
    //   420: ifne +141 -> 561
    //   423: aload_0
    //   424: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   427: ldc -15
    //   429: invokevirtual 169	com/luajava/LuaState:getGlobal	(Ljava/lang/String;)I
    //   432: pop
    //   433: aload_0
    //   434: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   437: iconst_m1
    //   438: ldc -13
    //   440: invokevirtual 246	com/luajava/LuaState:getField	(ILjava/lang/String;)I
    //   443: pop
    //   444: aload_0
    //   445: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   448: bipush -2
    //   450: invokevirtual 249	com/luajava/LuaState:remove	(I)V
    //   453: aload_0
    //   454: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   457: bipush -2
    //   459: invokevirtual 252	com/luajava/LuaState:insert	(I)V
    //   462: aload_1
    //   463: arraylength
    //   464: istore 4
    //   466: iconst_0
    //   467: istore_2
    //   468: iload_2
    //   469: iload 4
    //   471: if_icmpge +20 -> 491
    //   474: aload_0
    //   475: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   478: aload_1
    //   479: iload_2
    //   480: aaload
    //   481: invokevirtual 255	com/luajava/LuaState:pushObjectValue	(Ljava/lang/Object;)V
    //   484: iload_2
    //   485: iconst_1
    //   486: iadd
    //   487: istore_2
    //   488: goto -20 -> 468
    //   491: aload_0
    //   492: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   495: iload 4
    //   497: iconst_m1
    //   498: bipush -2
    //   500: iload 4
    //   502: isub
    //   503: invokevirtual 259	com/luajava/LuaState:pcall	(III)I
    //   506: istore 4
    //   508: iload 4
    //   510: istore_2
    //   511: iload 4
    //   513: ifne +48 -> 561
    //   516: aload_0
    //   517: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   520: invokevirtual 263	com/luajava/LuaState:getTop	()I
    //   523: iconst_1
    //   524: isub
    //   525: istore 4
    //   527: iload 4
    //   529: anewarray 224	java/lang/Object
    //   532: astore_1
    //   533: iload_3
    //   534: istore_2
    //   535: iload_2
    //   536: iload 4
    //   538: if_icmpge +99 -> 637
    //   541: aload_1
    //   542: iload_2
    //   543: aload_0
    //   544: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   547: iload_2
    //   548: iconst_2
    //   549: iadd
    //   550: invokevirtual 267	com/luajava/LuaState:toJavaObject	(I)Ljava/lang/Object;
    //   553: aastore
    //   554: iload_2
    //   555: iconst_1
    //   556: iadd
    //   557: istore_2
    //   558: goto -23 -> 535
    //   561: new 96	java/lang/StringBuilder
    //   564: dup
    //   565: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   568: astore_1
    //   569: aload_1
    //   570: aload_0
    //   571: iload_2
    //   572: invokespecial 269	com/androlua/LuaAsyncTask:a	(I)Ljava/lang/String;
    //   575: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: pop
    //   579: aload_1
    //   580: ldc_w 271
    //   583: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   586: pop
    //   587: aload_1
    //   588: aload_0
    //   589: getfield 140	com/androlua/LuaAsyncTask:b	Lcom/luajava/LuaState;
    //   592: iconst_m1
    //   593: invokevirtual 273	com/luajava/LuaState:toString	(I)Ljava/lang/String;
    //   596: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: pop
    //   600: new 127	com/luajava/LuaException
    //   603: dup
    //   604: aload_1
    //   605: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   608: invokespecial 275	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   611: athrow
    //   612: astore_1
    //   613: aload_0
    //   614: getfield 46	com/androlua/LuaAsyncTask:c	Lcom/androlua/LuaContext;
    //   617: ldc_w 277
    //   620: aload_1
    //   621: invokeinterface 220 3 0
    //   626: aconst_null
    //   627: areturn
    //   628: astore 5
    //   630: aload_1
    //   631: areturn
    //   632: astore 5
    //   634: goto -242 -> 392
    //   637: aload_1
    //   638: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	639	0	this	LuaAsyncTask
    //   0	639	1	paramArrayOfObject	Object[]
    //   358	214	2	i	int
    //   303	231	3	j	int
    //   355	184	4	k	int
    //   57	31	5	localLuaState	LuaState
    //   281	9	5	localLuaException1	LuaException
    //   300	66	5	localObject1	Object
    //   628	1	5	localInterruptedException	InterruptedException
    //   632	1	5	localLuaException2	LuaException
    //   61	313	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   169	278	281	com/luajava/LuaException
    //   392	415	612	com/luajava/LuaException
    //   423	466	612	com/luajava/LuaException
    //   474	484	612	com/luajava/LuaException
    //   491	508	612	com/luajava/LuaException
    //   516	533	612	com/luajava/LuaException
    //   541	554	612	com/luajava/LuaException
    //   561	612	612	com/luajava/LuaException
    //   9	16	628	java/lang/InterruptedException
    //   320	357	632	com/luajava/LuaException
    //   365	385	632	com/luajava/LuaException
  }
  
  protected void a(Object paramObject)
  {
    if (isCancelled()) {
      return;
    }
    try
    {
      if (this.f != null) {
        this.f.call((Object[])paramObject);
      }
    }
    catch (LuaException localLuaException)
    {
      this.c.sendError("onPostExecute", localLuaException);
    }
    super.a(paramObject);
    if (this.b != null) {
      this.b.gc(2, 1);
    }
    System.gc();
  }
  
  protected void b(Object[] paramArrayOfObject)
  {
    try
    {
      if (this.g != null) {
        this.g.call(paramArrayOfObject);
      }
    }
    catch (LuaException localLuaException)
    {
      this.c.sendError("onProgressUpdate", localLuaException);
    }
    super.b(paramArrayOfObject);
  }
  
  public void execute()
  {
    super.execute(new Object[0]);
  }
  
  public void gc()
  {
    if (getStatus() == AsyncTaskX.Status.RUNNING) {
      cancel(true);
    }
  }
  
  public void update(int paramInt)
  {
    c(new Object[] { Integer.valueOf(paramInt) });
  }
  
  public void update(Object paramObject)
  {
    c(new Object[] { paramObject });
  }
  
  public void update(String paramString)
  {
    c(new Object[] { paramString });
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */