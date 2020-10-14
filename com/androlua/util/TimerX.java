package com.androlua.util;

import java.util.Date;

public class TimerX
{
  private static long a;
  private final TimerImpl b;
  private final FinalizerHelper c;
  
  public TimerX()
  {
    this(false);
  }
  
  public TimerX(String paramString)
  {
    this(paramString, false);
  }
  
  public TimerX(String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      throw new NullPointerException("name is null");
    }
    this.b = new TimerImpl(paramString, paramBoolean);
    this.c = new FinalizerHelper(this.b);
  }
  
  public TimerX(boolean paramBoolean)
  {
    this(localStringBuilder.toString(), paramBoolean);
  }
  
  private static long a()
  {
    try
    {
      long l = a;
      a = l + 1L;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void a(TimerTaskX paramTimerTaskX, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    synchronized (this.b)
    {
      if (TimerImpl.a(this.b)) {
        throw new IllegalStateException("Timer was canceled");
      }
      paramLong1 += System.currentTimeMillis();
      if (paramLong1 < 0L)
      {
        paramTimerTaskX = new StringBuilder();
        paramTimerTaskX.append("Illegal delay to start the TimerTask: ");
        paramTimerTaskX.append(paramLong1);
        throw new IllegalArgumentException(paramTimerTaskX.toString());
      }
      synchronized (paramTimerTaskX.b)
      {
        if (paramTimerTaskX.a()) {
          throw new IllegalStateException("TimerTask is scheduled already");
        }
        if (paramTimerTaskX.c) {
          throw new IllegalStateException("TimerTask is canceled");
        }
        paramTimerTaskX.d = paramLong1;
        paramTimerTaskX.e = paramLong2;
        paramTimerTaskX.f = paramBoolean;
        TimerImpl.a(this.b, paramTimerTaskX);
        return;
      }
    }
  }
  
  public void cancel()
  {
    this.b.cancel();
  }
  
  public int purge()
  {
    synchronized (this.b)
    {
      int i = this.b.purge();
      return i;
    }
  }
  
  public void schedule(TimerTaskX paramTimerTaskX, long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException();
    }
    a(paramTimerTaskX, paramLong, -1L, false);
  }
  
  public void schedule(TimerTaskX paramTimerTaskX, long paramLong1, long paramLong2)
  {
    if ((paramLong1 >= 0L) && (paramLong2 > 0L))
    {
      a(paramTimerTaskX, paramLong1, paramLong2, false);
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public void schedule(TimerTaskX paramTimerTaskX, Date paramDate)
  {
    if (paramDate.getTime() < 0L) {
      throw new IllegalArgumentException();
    }
    long l = paramDate.getTime() - System.currentTimeMillis();
    if (l < 0L) {
      l = 0L;
    }
    a(paramTimerTaskX, l, -1L, false);
  }
  
  public void schedule(TimerTaskX paramTimerTaskX, Date paramDate, long paramLong)
  {
    if ((paramLong > 0L) && (paramDate.getTime() >= 0L))
    {
      long l = paramDate.getTime() - System.currentTimeMillis();
      if (l < 0L) {
        l = 0L;
      }
      a(paramTimerTaskX, l, paramLong, false);
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public void scheduleAtFixedRate(TimerTaskX paramTimerTaskX, long paramLong1, long paramLong2)
  {
    if ((paramLong1 >= 0L) && (paramLong2 > 0L))
    {
      a(paramTimerTaskX, paramLong1, paramLong2, true);
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public void scheduleAtFixedRate(TimerTaskX paramTimerTaskX, Date paramDate, long paramLong)
  {
    if ((paramLong > 0L) && (paramDate.getTime() >= 0L))
    {
      a(paramTimerTaskX, paramDate.getTime() - System.currentTimeMillis(), paramLong, true);
      return;
    }
    throw new IllegalArgumentException();
  }
  
  private static final class FinalizerHelper
  {
    private final TimerX.TimerImpl a;
    
    FinalizerHelper(TimerX.TimerImpl paramTimerImpl)
    {
      this.a = paramTimerImpl;
    }
    
    /* Error */
    protected void finalize()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 16	com/androlua/util/TimerX$FinalizerHelper:a	Lcom/androlua/util/TimerX$TimerImpl;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 16	com/androlua/util/TimerX$FinalizerHelper:a	Lcom/androlua/util/TimerX$TimerImpl;
      //   11: iconst_1
      //   12: invokestatic 23	com/androlua/util/TimerX$TimerImpl:a	(Lcom/androlua/util/TimerX$TimerImpl;Z)Z
      //   15: pop
      //   16: aload_0
      //   17: getfield 16	com/androlua/util/TimerX$FinalizerHelper:a	Lcom/androlua/util/TimerX$TimerImpl;
      //   20: invokevirtual 26	java/lang/Object:notify	()V
      //   23: aload_1
      //   24: monitorexit
      //   25: aload_0
      //   26: invokespecial 28	java/lang/Object:finalize	()V
      //   29: return
      //   30: astore_2
      //   31: aload_1
      //   32: monitorexit
      //   33: aload_2
      //   34: athrow
      //   35: astore_1
      //   36: aload_0
      //   37: invokespecial 28	java/lang/Object:finalize	()V
      //   40: aload_1
      //   41: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	42	0	this	FinalizerHelper
      //   35	6	1	localObject1	Object
      //   30	4	2	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   7	25	30	finally
      //   31	33	30	finally
      //   0	7	35	finally
      //   33	35	35	finally
    }
  }
  
  private static final class TimerImpl
    extends Thread
  {
    private boolean a;
    private boolean b;
    private TimerHeap c = new TimerHeap(null);
    
    TimerImpl(String paramString, boolean paramBoolean)
    {
      setName(paramString);
      setDaemon(paramBoolean);
      start();
    }
    
    private void a(TimerTaskX paramTimerTaskX)
    {
      this.c.insert(paramTimerTaskX);
      notify();
    }
    
    public void cancel()
    {
      try
      {
        this.a = true;
        this.c.reset();
        notify();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    public int purge()
    {
      if (this.c.isEmpty()) {
        return 0;
      }
      TimerHeap.a(this.c, 0);
      this.c.deleteIfCancelled();
      return TimerHeap.a(this.c);
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 52	com/androlua/util/TimerX$TimerImpl:a	Z
      //   6: ifeq +6 -> 12
      //   9: aload_0
      //   10: monitorexit
      //   11: return
      //   12: aload_0
      //   13: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   16: invokevirtual 65	com/androlua/util/TimerX$TimerImpl$TimerHeap:isEmpty	()Z
      //   19: ifeq +22 -> 41
      //   22: aload_0
      //   23: getfield 55	com/androlua/util/TimerX$TimerImpl:b	Z
      //   26: ifeq +6 -> 32
      //   29: aload_0
      //   30: monitorexit
      //   31: return
      //   32: aload_0
      //   33: invokevirtual 80	java/lang/Object:wait	()V
      //   36: aload_0
      //   37: monitorexit
      //   38: goto -38 -> 0
      //   41: invokestatic 86	java/lang/System:currentTimeMillis	()J
      //   44: lstore_2
      //   45: aload_0
      //   46: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   49: invokevirtual 90	com/androlua/util/TimerX$TimerImpl$TimerHeap:minimum	()Lcom/androlua/util/TimerTaskX;
      //   52: astore 5
      //   54: aload 5
      //   56: getfield 95	com/androlua/util/TimerTaskX:b	Ljava/lang/Object;
      //   59: astore 6
      //   61: aload 6
      //   63: monitorenter
      //   64: aload 5
      //   66: getfield 97	com/androlua/util/TimerTaskX:c	Z
      //   69: istore 4
      //   71: iconst_0
      //   72: istore_1
      //   73: iload 4
      //   75: ifeq +17 -> 92
      //   78: aload_0
      //   79: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   82: iconst_0
      //   83: invokevirtual 101	com/androlua/util/TimerX$TimerImpl$TimerHeap:delete	(I)V
      //   86: aload 6
      //   88: monitorexit
      //   89: goto -53 -> 36
      //   92: aload 5
      //   94: getfield 105	com/androlua/util/TimerTaskX:d	J
      //   97: lload_2
      //   98: lsub
      //   99: lstore_2
      //   100: aload 6
      //   102: monitorexit
      //   103: lload_2
      //   104: lconst_0
      //   105: lcmp
      //   106: ifle +11 -> 117
      //   109: aload_0
      //   110: lload_2
      //   111: invokevirtual 108	java/lang/Object:wait	(J)V
      //   114: goto -78 -> 36
      //   117: aload 5
      //   119: getfield 95	com/androlua/util/TimerTaskX:b	Ljava/lang/Object;
      //   122: astore 6
      //   124: aload 6
      //   126: monitorenter
      //   127: aload_0
      //   128: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   131: invokevirtual 90	com/androlua/util/TimerX$TimerImpl$TimerHeap:minimum	()Lcom/androlua/util/TimerTaskX;
      //   134: getfield 105	com/androlua/util/TimerTaskX:d	J
      //   137: aload 5
      //   139: getfield 105	com/androlua/util/TimerTaskX:d	J
      //   142: lcmp
      //   143: ifeq +13 -> 156
      //   146: aload_0
      //   147: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   150: aload 5
      //   152: invokestatic 111	com/androlua/util/TimerX$TimerImpl$TimerHeap:a	(Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;Lcom/androlua/util/TimerTaskX;)I
      //   155: istore_1
      //   156: aload 5
      //   158: getfield 97	com/androlua/util/TimerTaskX:c	Z
      //   161: ifeq +25 -> 186
      //   164: aload_0
      //   165: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   168: aload_0
      //   169: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   172: aload 5
      //   174: invokestatic 111	com/androlua/util/TimerX$TimerImpl$TimerHeap:a	(Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;Lcom/androlua/util/TimerTaskX;)I
      //   177: invokevirtual 101	com/androlua/util/TimerX$TimerImpl$TimerHeap:delete	(I)V
      //   180: aload 6
      //   182: monitorexit
      //   183: goto -147 -> 36
      //   186: aload 5
      //   188: aload 5
      //   190: getfield 105	com/androlua/util/TimerTaskX:d	J
      //   193: invokevirtual 114	com/androlua/util/TimerTaskX:setScheduledTime	(J)V
      //   196: aload_0
      //   197: getfield 25	com/androlua/util/TimerX$TimerImpl:c	Lcom/androlua/util/TimerX$TimerImpl$TimerHeap;
      //   200: iload_1
      //   201: invokevirtual 101	com/androlua/util/TimerX$TimerImpl$TimerHeap:delete	(I)V
      //   204: aload 5
      //   206: getfield 117	com/androlua/util/TimerTaskX:e	J
      //   209: lconst_0
      //   210: lcmp
      //   211: iflt +53 -> 264
      //   214: aload 5
      //   216: getfield 120	com/androlua/util/TimerTaskX:f	Z
      //   219: ifeq +22 -> 241
      //   222: aload 5
      //   224: aload 5
      //   226: getfield 105	com/androlua/util/TimerTaskX:d	J
      //   229: aload 5
      //   231: getfield 117	com/androlua/util/TimerTaskX:e	J
      //   234: ladd
      //   235: putfield 105	com/androlua/util/TimerTaskX:d	J
      //   238: goto +17 -> 255
      //   241: aload 5
      //   243: invokestatic 86	java/lang/System:currentTimeMillis	()J
      //   246: aload 5
      //   248: getfield 117	com/androlua/util/TimerTaskX:e	J
      //   251: ladd
      //   252: putfield 105	com/androlua/util/TimerTaskX:d	J
      //   255: aload_0
      //   256: aload 5
      //   258: invokespecial 49	com/androlua/util/TimerX$TimerImpl:a	(Lcom/androlua/util/TimerTaskX;)V
      //   261: goto +9 -> 270
      //   264: aload 5
      //   266: lconst_0
      //   267: putfield 105	com/androlua/util/TimerTaskX:d	J
      //   270: aload 6
      //   272: monitorexit
      //   273: aload_0
      //   274: monitorexit
      //   275: aload 5
      //   277: invokevirtual 123	com/androlua/util/TimerTaskX:isEnabled	()Z
      //   280: ifeq -280 -> 0
      //   283: aload 5
      //   285: invokevirtual 125	com/androlua/util/TimerTaskX:run	()V
      //   288: goto -288 -> 0
      //   291: astore 5
      //   293: aload_0
      //   294: monitorenter
      //   295: aload_0
      //   296: iconst_1
      //   297: putfield 52	com/androlua/util/TimerX$TimerImpl:a	Z
      //   300: aload_0
      //   301: monitorexit
      //   302: goto +10 -> 312
      //   305: astore 5
      //   307: aload_0
      //   308: monitorexit
      //   309: aload 5
      //   311: athrow
      //   312: aload 5
      //   314: athrow
      //   315: astore 5
      //   317: aload 6
      //   319: monitorexit
      //   320: aload 5
      //   322: athrow
      //   323: astore 5
      //   325: aload 6
      //   327: monitorexit
      //   328: aload 5
      //   330: athrow
      //   331: astore 5
      //   333: aload_0
      //   334: monitorexit
      //   335: aload 5
      //   337: athrow
      //   338: astore 5
      //   340: goto -304 -> 36
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	343	0	this	TimerImpl
      //   72	129	1	i	int
      //   44	67	2	l	long
      //   69	5	4	bool	boolean
      //   52	232	5	localTimerTaskX	TimerTaskX
      //   291	1	5	localObject1	Object
      //   305	8	5	localObject2	Object
      //   315	6	5	localObject3	Object
      //   323	6	5	localObject4	Object
      //   331	5	5	localObject5	Object
      //   338	1	5	localInterruptedException	InterruptedException
      //   59	267	6	localObject6	Object
      // Exception table:
      //   from	to	target	type
      //   275	288	291	finally
      //   295	302	305	finally
      //   307	309	305	finally
      //   127	156	315	finally
      //   156	183	315	finally
      //   186	238	315	finally
      //   241	255	315	finally
      //   255	261	315	finally
      //   264	270	315	finally
      //   270	273	315	finally
      //   317	320	315	finally
      //   64	71	323	finally
      //   78	89	323	finally
      //   92	103	323	finally
      //   325	328	323	finally
      //   2	11	331	finally
      //   12	31	331	finally
      //   32	36	331	finally
      //   36	38	331	finally
      //   41	64	331	finally
      //   109	114	331	finally
      //   117	127	331	finally
      //   273	275	331	finally
      //   320	323	331	finally
      //   328	331	331	finally
      //   333	335	331	finally
      //   32	36	338	java/lang/InterruptedException
      //   109	114	338	java/lang/InterruptedException
    }
    
    private static final class TimerHeap
    {
      private int a = 256;
      private TimerTaskX[] b = new TimerTaskX[this.a];
      private int c = 0;
      private int d = 0;
      
      private int a(TimerTaskX paramTimerTaskX)
      {
        int i = 0;
        while (i < this.b.length)
        {
          if (this.b[i] == paramTimerTaskX) {
            return i;
          }
          i += 1;
        }
        return -1;
      }
      
      private void a()
      {
        int j = this.c - 1;
        int k;
        for (int i = (j - 1) / 2; this.b[j].d < this.b[i].d; i = k)
        {
          TimerTaskX localTimerTaskX = this.b[j];
          this.b[j] = this.b[i];
          this.b[i] = localTimerTaskX;
          k = (i - 1) / 2;
          j = i;
        }
      }
      
      private void a(int paramInt)
      {
        int i = paramInt * 2 + 1;
        int j = paramInt;
        paramInt = i;
        while ((paramInt < this.c) && (this.c > 0))
        {
          int k = paramInt + 1;
          i = paramInt;
          if (k < this.c)
          {
            i = paramInt;
            if (this.b[k].d < this.b[paramInt].d) {
              i = k;
            }
          }
          if (this.b[j].d < this.b[i].d) {
            return;
          }
          TimerTaskX localTimerTaskX = this.b[j];
          this.b[j] = this.b[i];
          this.b[i] = localTimerTaskX;
          paramInt = i * 2 + 1;
          j = i;
        }
      }
      
      public void adjustMinimum()
      {
        a(0);
      }
      
      public void delete(int paramInt)
      {
        if ((paramInt >= 0) && (paramInt < this.c))
        {
          TimerTaskX[] arrayOfTimerTaskX1 = this.b;
          TimerTaskX[] arrayOfTimerTaskX2 = this.b;
          int i = this.c - 1;
          this.c = i;
          arrayOfTimerTaskX1[paramInt] = arrayOfTimerTaskX2[i];
          this.b[this.c] = null;
          a(paramInt);
        }
      }
      
      public void deleteIfCancelled()
      {
        int j;
        for (int i = 0; i < this.c; i = j + 1)
        {
          j = i;
          if (this.b[i].c)
          {
            this.d += 1;
            delete(i);
            j = i - 1;
          }
        }
      }
      
      public void insert(TimerTaskX paramTimerTaskX)
      {
        if (this.b.length == this.c)
        {
          arrayOfTimerTaskX = new TimerTaskX[this.c * 2];
          System.arraycopy(this.b, 0, arrayOfTimerTaskX, 0, this.c);
          this.b = arrayOfTimerTaskX;
        }
        TimerTaskX[] arrayOfTimerTaskX = this.b;
        int i = this.c;
        this.c = (i + 1);
        arrayOfTimerTaskX[i] = paramTimerTaskX;
        a();
      }
      
      public boolean isEmpty()
      {
        return this.c == 0;
      }
      
      public TimerTaskX minimum()
      {
        return this.b[0];
      }
      
      public void reset()
      {
        this.b = new TimerTaskX[this.a];
        this.c = 0;
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\TimerX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */