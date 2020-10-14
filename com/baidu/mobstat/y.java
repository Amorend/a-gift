package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum y
{
  private int f;
  
  private y(int paramInt)
  {
    this.f = paramInt;
  }
  
  private int a(List<String> paramList, ArrayList<Long> paramArrayList, ArrayList<w> paramArrayList1, int paramInt1, int paramInt2)
  {
    int j = c();
    int k = 0;
    int i = 0;
    int m = paramInt2;
    paramInt2 = j;
    while (paramInt2 > 0)
    {
      j = m;
      if (paramInt2 < m) {
        j = paramInt2;
      }
      Object localObject1 = a(j, i);
      if ((i == 0) && (((ArrayList)localObject1).size() != 0)) {
        paramArrayList1.add((w)((ArrayList)localObject1).get(0));
      }
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (w)((Iterator)localObject1).next();
        long l = ((w)localObject2).a();
        localObject2 = ((w)localObject2).b();
        m = ((String)localObject2).length() + k;
        if (m > paramInt1) {
          break;
        }
        paramArrayList.add(Long.valueOf(l));
        paramList.add(localObject2);
        k = m;
      }
      paramInt2 -= j;
      i += j;
      m = j;
    }
    return k;
  }
  
  /* Error */
  private int c()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 129	com/baidu/mobstat/y:a	()Lcom/baidu/mobstat/x;
    //   4: astore_2
    //   5: aload_2
    //   6: astore_3
    //   7: aload_2
    //   8: invokevirtual 133	com/baidu/mobstat/x:a	()Z
    //   11: ifeq +20 -> 31
    //   14: aload_2
    //   15: astore_3
    //   16: aload_2
    //   17: invokevirtual 135	com/baidu/mobstat/x:b	()I
    //   20: istore_1
    //   21: aload_2
    //   22: ifnull +7 -> 29
    //   25: aload_2
    //   26: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   29: iload_1
    //   30: ireturn
    //   31: aload_2
    //   32: ifnull +40 -> 72
    //   35: goto +33 -> 68
    //   38: astore_2
    //   39: goto +35 -> 74
    //   42: astore 4
    //   44: goto +13 -> 57
    //   47: astore_2
    //   48: aconst_null
    //   49: astore_3
    //   50: goto +24 -> 74
    //   53: astore 4
    //   55: aconst_null
    //   56: astore_2
    //   57: aload_2
    //   58: astore_3
    //   59: aload 4
    //   61: invokestatic 143	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   64: aload_2
    //   65: ifnull +7 -> 72
    //   68: aload_2
    //   69: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   72: iconst_0
    //   73: ireturn
    //   74: aload_3
    //   75: ifnull +7 -> 82
    //   78: aload_3
    //   79: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   82: aload_2
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	y
    //   20	10	1	i	int
    //   4	28	2	localx	x
    //   38	1	2	localObject1	Object
    //   47	1	2	localObject2	Object
    //   56	27	2	localObject3	Object
    //   6	73	3	localObject4	Object
    //   42	1	4	localException1	Exception
    //   53	7	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   7	14	38	finally
    //   16	21	38	finally
    //   59	64	38	finally
    //   7	14	42	java/lang/Exception
    //   16	21	42	java/lang/Exception
    //   0	5	47	finally
    //   0	5	53	java/lang/Exception
  }
  
  /* Error */
  public int a(ArrayList<Long> paramArrayList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_3
    //   4: aload_1
    //   5: ifnull +216 -> 221
    //   8: aload_1
    //   9: invokevirtual 79	java/util/ArrayList:size	()I
    //   12: istore_2
    //   13: iload_2
    //   14: ifne +6 -> 20
    //   17: goto +204 -> 221
    //   20: aconst_null
    //   21: astore 8
    //   23: aconst_null
    //   24: astore 7
    //   26: aload_0
    //   27: invokevirtual 129	com/baidu/mobstat/y:a	()Lcom/baidu/mobstat/x;
    //   30: astore 6
    //   32: aload 6
    //   34: invokevirtual 133	com/baidu/mobstat/x:a	()Z
    //   37: istore 5
    //   39: iload 5
    //   41: ifne +17 -> 58
    //   44: aload 6
    //   46: ifnull +8 -> 54
    //   49: aload 6
    //   51: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   54: aload_0
    //   55: monitorexit
    //   56: iconst_0
    //   57: ireturn
    //   58: aload_1
    //   59: invokevirtual 79	java/util/ArrayList:size	()I
    //   62: istore 4
    //   64: iconst_0
    //   65: istore_2
    //   66: iload_3
    //   67: iload 4
    //   69: if_icmpge +63 -> 132
    //   72: aload 6
    //   74: aload_1
    //   75: iload_3
    //   76: invokevirtual 83	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   79: checkcast 116	java/lang/Long
    //   82: invokevirtual 157	java/lang/Long:longValue	()J
    //   85: invokevirtual 160	com/baidu/mobstat/x:b	(J)Z
    //   88: istore 5
    //   90: iload 5
    //   92: ifne +17 -> 109
    //   95: aload 6
    //   97: ifnull +8 -> 105
    //   100: aload 6
    //   102: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   105: aload_0
    //   106: monitorexit
    //   107: iload_2
    //   108: ireturn
    //   109: iload_2
    //   110: iconst_1
    //   111: iadd
    //   112: istore_2
    //   113: iload_3
    //   114: iconst_1
    //   115: iadd
    //   116: istore_3
    //   117: goto -51 -> 66
    //   120: astore 7
    //   122: aload 6
    //   124: astore_1
    //   125: aload 7
    //   127: astore 6
    //   129: goto +51 -> 180
    //   132: iload_2
    //   133: istore_3
    //   134: aload 6
    //   136: ifnull +64 -> 200
    //   139: aload 6
    //   141: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   144: iload_2
    //   145: istore_3
    //   146: goto +54 -> 200
    //   149: astore_1
    //   150: goto +54 -> 204
    //   153: astore 7
    //   155: aload 6
    //   157: astore_1
    //   158: aload 7
    //   160: astore 6
    //   162: goto +16 -> 178
    //   165: astore_1
    //   166: aload 7
    //   168: astore 6
    //   170: goto +34 -> 204
    //   173: astore 6
    //   175: aload 8
    //   177: astore_1
    //   178: iconst_0
    //   179: istore_2
    //   180: aload_1
    //   181: astore 7
    //   183: aload 6
    //   185: invokestatic 143	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   188: iload_2
    //   189: istore_3
    //   190: aload_1
    //   191: ifnull +9 -> 200
    //   194: aload_1
    //   195: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   198: iload_2
    //   199: istore_3
    //   200: aload_0
    //   201: monitorexit
    //   202: iload_3
    //   203: ireturn
    //   204: aload 6
    //   206: ifnull +8 -> 214
    //   209: aload 6
    //   211: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   214: aload_1
    //   215: athrow
    //   216: astore_1
    //   217: aload_0
    //   218: monitorexit
    //   219: aload_1
    //   220: athrow
    //   221: aload_0
    //   222: monitorexit
    //   223: iconst_0
    //   224: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	this	y
    //   0	225	1	paramArrayList	ArrayList<Long>
    //   12	187	2	i	int
    //   3	200	3	j	int
    //   62	8	4	k	int
    //   37	54	5	bool	boolean
    //   30	139	6	localObject1	Object
    //   173	37	6	localException1	Exception
    //   24	1	7	localObject2	Object
    //   120	6	7	localException2	Exception
    //   153	14	7	localException3	Exception
    //   181	1	7	localArrayList	ArrayList<Long>
    //   21	155	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   72	90	120	java/lang/Exception
    //   32	39	149	finally
    //   58	64	149	finally
    //   72	90	149	finally
    //   32	39	153	java/lang/Exception
    //   58	64	153	java/lang/Exception
    //   26	32	165	finally
    //   183	188	165	finally
    //   26	32	173	java/lang/Exception
    //   8	13	216	finally
    //   49	54	216	finally
    //   100	105	216	finally
    //   139	144	216	finally
    //   194	198	216	finally
    //   209	214	216	finally
    //   214	216	216	finally
  }
  
  /* Error */
  public long a(long paramLong, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore 9
    //   5: aconst_null
    //   6: astore 7
    //   8: aload_0
    //   9: invokevirtual 129	com/baidu/mobstat/y:a	()Lcom/baidu/mobstat/x;
    //   12: astore 8
    //   14: aload 8
    //   16: invokevirtual 133	com/baidu/mobstat/x:a	()Z
    //   19: istore 6
    //   21: iload 6
    //   23: ifne +19 -> 42
    //   26: aload 8
    //   28: ifnull +8 -> 36
    //   31: aload 8
    //   33: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   36: aload_0
    //   37: monitorexit
    //   38: ldc2_w 163
    //   41: lreturn
    //   42: aload 8
    //   44: lload_1
    //   45: invokestatic 167	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   48: aload_3
    //   49: invokevirtual 170	com/baidu/mobstat/x:a	(Ljava/lang/String;Ljava/lang/String;)J
    //   52: lstore 4
    //   54: lload 4
    //   56: lstore_1
    //   57: aload 8
    //   59: ifnull +63 -> 122
    //   62: aload 8
    //   64: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   67: lload 4
    //   69: lstore_1
    //   70: goto +52 -> 122
    //   73: astore_3
    //   74: goto +52 -> 126
    //   77: astore 7
    //   79: aload 8
    //   81: astore_3
    //   82: aload 7
    //   84: astore 8
    //   86: goto +16 -> 102
    //   89: astore_3
    //   90: aload 7
    //   92: astore 8
    //   94: goto +32 -> 126
    //   97: astore 8
    //   99: aload 9
    //   101: astore_3
    //   102: aload_3
    //   103: astore 7
    //   105: aload 8
    //   107: invokestatic 143	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   110: aload_3
    //   111: ifnull +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   118: ldc2_w 163
    //   121: lstore_1
    //   122: aload_0
    //   123: monitorexit
    //   124: lload_1
    //   125: lreturn
    //   126: aload 8
    //   128: ifnull +11 -> 139
    //   131: aload 8
    //   133: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   136: goto +3 -> 139
    //   139: aload_3
    //   140: athrow
    //   141: aload_0
    //   142: monitorexit
    //   143: aload_3
    //   144: athrow
    //   145: astore_3
    //   146: goto -5 -> 141
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	this	y
    //   0	149	1	paramLong	long
    //   0	149	3	paramString	String
    //   52	16	4	l	long
    //   19	3	6	bool	boolean
    //   6	1	7	localObject1	Object
    //   77	14	7	localException1	Exception
    //   103	1	7	str	String
    //   12	81	8	localObject2	Object
    //   97	35	8	localException2	Exception
    //   3	97	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   14	21	73	finally
    //   42	54	73	finally
    //   14	21	77	java/lang/Exception
    //   42	54	77	java/lang/Exception
    //   8	14	89	finally
    //   105	110	89	finally
    //   8	14	97	java/lang/Exception
    //   31	36	145	finally
    //   62	67	145	finally
    //   114	118	145	finally
    //   131	136	145	finally
    //   139	141	145	finally
  }
  
  public abstract x a();
  
  /* Error */
  public ArrayList<w> a(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 76	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 172	java/util/ArrayList:<init>	()V
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 4
    //   17: aload_0
    //   18: invokevirtual 129	com/baidu/mobstat/y:a	()Lcom/baidu/mobstat/x;
    //   21: astore 5
    //   23: aload 5
    //   25: invokevirtual 133	com/baidu/mobstat/x:a	()Z
    //   28: istore_3
    //   29: iload_3
    //   30: ifne +18 -> 48
    //   33: aload 5
    //   35: ifnull +8 -> 43
    //   38: aload 5
    //   40: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   43: aload_0
    //   44: monitorexit
    //   45: aload 7
    //   47: areturn
    //   48: aload 5
    //   50: iload_1
    //   51: iload_2
    //   52: invokevirtual 173	com/baidu/mobstat/x:a	(II)Ljava/util/ArrayList;
    //   55: astore 6
    //   57: aload 6
    //   59: astore 4
    //   61: aload 5
    //   63: ifnull +67 -> 130
    //   66: aload 5
    //   68: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   71: aload 6
    //   73: astore 4
    //   75: goto +55 -> 130
    //   78: astore 6
    //   80: aload 5
    //   82: astore 4
    //   84: aload 6
    //   86: astore 5
    //   88: goto +47 -> 135
    //   91: astore 6
    //   93: goto +14 -> 107
    //   96: astore 5
    //   98: goto +37 -> 135
    //   101: astore 6
    //   103: aload 8
    //   105: astore 5
    //   107: aload 5
    //   109: astore 4
    //   111: aload 6
    //   113: invokestatic 143	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   116: aload 5
    //   118: ifnull +8 -> 126
    //   121: aload 5
    //   123: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   126: aload 7
    //   128: astore 4
    //   130: aload_0
    //   131: monitorexit
    //   132: aload 4
    //   134: areturn
    //   135: aload 4
    //   137: ifnull +8 -> 145
    //   140: aload 4
    //   142: invokevirtual 138	com/baidu/mobstat/x:close	()V
    //   145: aload 5
    //   147: athrow
    //   148: astore 4
    //   150: aload_0
    //   151: monitorexit
    //   152: aload 4
    //   154: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	155	0	this	y
    //   0	155	1	paramInt1	int
    //   0	155	2	paramInt2	int
    //   28	2	3	bool	boolean
    //   15	126	4	localObject1	Object
    //   148	5	4	localObject2	Object
    //   21	66	5	localObject3	Object
    //   96	1	5	localObject4	Object
    //   105	41	5	localObject5	Object
    //   55	17	6	localArrayList1	ArrayList
    //   78	7	6	localObject6	Object
    //   91	1	6	localException1	Exception
    //   101	11	6	localException2	Exception
    //   9	118	7	localArrayList2	ArrayList
    //   12	92	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   23	29	78	finally
    //   48	57	78	finally
    //   23	29	91	java/lang/Exception
    //   48	57	91	java/lang/Exception
    //   17	23	96	finally
    //   111	116	96	finally
    //   17	23	101	java/lang/Exception
    //   2	11	148	finally
    //   38	43	148	finally
    //   66	71	148	finally
    //   121	126	148	finally
    //   140	145	148	finally
    //   145	148	148	finally
  }
  
  public List<String> a(int paramInt)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject1 = new ArrayList();
      Object localObject3 = new ArrayList();
      a(localArrayList, (ArrayList)localObject1, (ArrayList)localObject3, paramInt, 500);
      if ((((ArrayList)localObject3).size() != 0) && (localArrayList.size() == 0) && (((ArrayList)localObject1).size() == 0))
      {
        localObject3 = (w)((ArrayList)localObject3).get(0);
        long l = ((w)localObject3).a();
        localObject3 = ((w)localObject3).b();
        ((ArrayList)localObject1).add(Long.valueOf(l));
        localArrayList.add(localObject3);
      }
      paramInt = a((ArrayList)localObject1);
      localObject1 = localArrayList;
      if (paramInt != localArrayList.size()) {
        localObject1 = localArrayList.subList(0, paramInt);
      }
      return (List<String>)localObject1;
    }
    finally {}
  }
  
  public boolean b()
  {
    try
    {
      int i = c();
      boolean bool;
      if (i == 0) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean b(int paramInt)
  {
    try
    {
      int i = c();
      boolean bool;
      if (i >= paramInt) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String toString()
  {
    return String.valueOf(this.f);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */