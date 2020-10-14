package com.branch.www.screencapture;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.hardware.display.VirtualDisplay;
import android.hardware.display.VirtualDisplay.Callback;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.androlua.LuaAccessibilityService;
import java.nio.Buffer;

@TargetApi(21)
public class b
{
  public static String a = "";
  private static LuaAccessibilityService b;
  private static a c;
  private static b g;
  private static Intent j;
  private final Context d;
  private final VirtualDisplay.Callback e;
  private Image f;
  private MediaProjection h;
  private VirtualDisplay i;
  private ImageReader k;
  private int l;
  private int m;
  private int n;
  
  public b(Context paramContext, VirtualDisplay.Callback paramCallback)
  {
    this.d = paramContext;
    this.e = paramCallback;
    h();
    if (j == null)
    {
      paramContext = new Intent(this.d, ScreenCaptureActivity.class);
      paramContext.setFlags(268435456);
      this.d.startActivity(paramContext);
      return;
    }
    c();
  }
  
  public static void a(Intent paramIntent)
  {
    if (paramIntent == null)
    {
      if (b != null) {
        Toast.makeText(b, "未获得权限", 0).show();
      }
      if (c != null) {
        c.onScreenCaptureError("未获得权限");
      }
      return;
    }
    j = paramIntent;
    if (b != null) {
      b.getHandler().postDelayed(new Runnable()
      {
        public void run()
        {
          b.a(b.f(), b.g());
        }
      }, 500L);
    }
    j = paramIntent;
    if (g != null) {
      g.c();
    }
  }
  
  /* Error */
  public static void a(LuaAccessibilityService paramLuaAccessibilityService, a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_0
    //   6: putstatic 74	com/branch/www/screencapture/b:b	Lcom/androlua/LuaAccessibilityService;
    //   9: aload_1
    //   10: putstatic 87	com/branch/www/screencapture/b:c	Lcom/branch/www/screencapture/a;
    //   13: aconst_null
    //   14: astore 7
    //   16: getstatic 52	com/branch/www/screencapture/b:j	Landroid/content/Intent;
    //   19: ifnonnull +39 -> 58
    //   22: new 54	android/content/Intent
    //   25: dup
    //   26: aload_0
    //   27: ldc 56
    //   29: invokespecial 59	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   32: astore_1
    //   33: aload_1
    //   34: ldc 60
    //   36: invokevirtual 64	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   39: pop
    //   40: aload_0
    //   41: aload_1
    //   42: invokevirtual 116	com/androlua/LuaAccessibilityService:startActivity	(Landroid/content/Intent;)V
    //   45: aconst_null
    //   46: astore 6
    //   48: aload 6
    //   50: astore 5
    //   52: aload 7
    //   54: astore_0
    //   55: goto +486 -> 541
    //   58: aload_0
    //   59: ldc 118
    //   61: invokevirtual 122	com/androlua/LuaAccessibilityService:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   64: checkcast 124	android/view/WindowManager
    //   67: astore 5
    //   69: new 126	android/util/DisplayMetrics
    //   72: dup
    //   73: invokespecial 127	android/util/DisplayMetrics:<init>	()V
    //   76: astore 6
    //   78: aload 5
    //   80: ifnull +37 -> 117
    //   83: aload 5
    //   85: invokeinterface 131 1 0
    //   90: aload 6
    //   92: invokevirtual 137	android/view/Display:getRealMetrics	(Landroid/util/DisplayMetrics;)V
    //   95: aload 6
    //   97: getfield 140	android/util/DisplayMetrics:densityDpi	I
    //   100: istore_2
    //   101: aload 6
    //   103: getfield 143	android/util/DisplayMetrics:widthPixels	I
    //   106: istore 4
    //   108: aload 6
    //   110: getfield 146	android/util/DisplayMetrics:heightPixels	I
    //   113: istore_3
    //   114: goto +19 -> 133
    //   117: aload_0
    //   118: invokevirtual 150	com/androlua/LuaAccessibilityService:getHeight	()I
    //   121: istore_3
    //   122: aload_0
    //   123: invokevirtual 153	com/androlua/LuaAccessibilityService:getWidth	()I
    //   126: istore 4
    //   128: aload_0
    //   129: invokevirtual 156	com/androlua/LuaAccessibilityService:getDensity	()I
    //   132: istore_2
    //   133: iload 4
    //   135: iload_3
    //   136: iconst_1
    //   137: iconst_1
    //   138: invokestatic 162	android/media/ImageReader:newInstance	(IIII)Landroid/media/ImageReader;
    //   141: astore 5
    //   143: aload_0
    //   144: ldc -92
    //   146: invokevirtual 122	com/androlua/LuaAccessibilityService:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   149: checkcast 166	android/media/projection/MediaProjectionManager
    //   152: iconst_m1
    //   153: getstatic 52	com/branch/www/screencapture/b:j	Landroid/content/Intent;
    //   156: invokevirtual 170	android/media/projection/MediaProjectionManager:getMediaProjection	(ILandroid/content/Intent;)Landroid/media/projection/MediaProjection;
    //   159: astore 6
    //   161: aload 6
    //   163: ldc -84
    //   165: iload 4
    //   167: iload_3
    //   168: iload_2
    //   169: bipush 16
    //   171: aload 5
    //   173: invokevirtual 176	android/media/ImageReader:getSurface	()Landroid/view/Surface;
    //   176: aconst_null
    //   177: aconst_null
    //   178: invokevirtual 182	android/media/projection/MediaProjection:createVirtualDisplay	(Ljava/lang/String;IIIILandroid/view/Surface;Landroid/hardware/display/VirtualDisplay$Callback;Landroid/os/Handler;)Landroid/hardware/display/VirtualDisplay;
    //   181: astore_0
    //   182: aload 5
    //   184: astore 7
    //   186: aload_0
    //   187: astore 8
    //   189: aload 6
    //   191: astore 9
    //   193: aload 5
    //   195: invokevirtual 186	android/media/ImageReader:acquireLatestImage	()Landroid/media/Image;
    //   198: astore 10
    //   200: aload 10
    //   202: astore 7
    //   204: iconst_0
    //   205: istore_2
    //   206: aload 7
    //   208: astore 10
    //   210: iload_2
    //   211: bipush 40
    //   213: if_icmpge +67 -> 280
    //   216: aload 5
    //   218: astore 7
    //   220: aload_0
    //   221: astore 8
    //   223: aload 6
    //   225: astore 9
    //   227: ldc2_w 187
    //   230: invokestatic 194	java/lang/Thread:sleep	(J)V
    //   233: goto +21 -> 254
    //   236: astore 10
    //   238: aload 5
    //   240: astore 7
    //   242: aload_0
    //   243: astore 8
    //   245: aload 6
    //   247: astore 9
    //   249: aload 10
    //   251: invokevirtual 197	java/lang/InterruptedException:printStackTrace	()V
    //   254: aload 5
    //   256: astore 7
    //   258: aload_0
    //   259: astore 8
    //   261: aload 6
    //   263: astore 9
    //   265: aload 5
    //   267: invokevirtual 186	android/media/ImageReader:acquireLatestImage	()Landroid/media/Image;
    //   270: astore 10
    //   272: aload 10
    //   274: ifnull +519 -> 793
    //   277: goto +3 -> 280
    //   280: aload 10
    //   282: ifnonnull +25 -> 307
    //   285: aload 5
    //   287: astore 7
    //   289: aload_0
    //   290: astore 8
    //   292: aload 6
    //   294: astore 9
    //   296: aload_1
    //   297: ldc -57
    //   299: invokeinterface 93 2 0
    //   304: goto +207 -> 511
    //   307: aload 5
    //   309: astore 7
    //   311: aload_0
    //   312: astore 8
    //   314: aload 6
    //   316: astore 9
    //   318: aload 10
    //   320: invokevirtual 202	android/media/Image:getWidth	()I
    //   323: istore_2
    //   324: aload 5
    //   326: astore 7
    //   328: aload_0
    //   329: astore 8
    //   331: aload 6
    //   333: astore 9
    //   335: aload 10
    //   337: invokevirtual 203	android/media/Image:getHeight	()I
    //   340: istore_3
    //   341: aload 5
    //   343: astore 7
    //   345: aload_0
    //   346: astore 8
    //   348: aload 6
    //   350: astore 9
    //   352: aload 10
    //   354: invokevirtual 207	android/media/Image:getPlanes	()[Landroid/media/Image$Plane;
    //   357: astore 12
    //   359: aload 5
    //   361: astore 7
    //   363: aload_0
    //   364: astore 8
    //   366: aload 6
    //   368: astore 9
    //   370: aload 12
    //   372: iconst_0
    //   373: aaload
    //   374: invokevirtual 213	android/media/Image$Plane:getBuffer	()Ljava/nio/ByteBuffer;
    //   377: astore 11
    //   379: aload 5
    //   381: astore 7
    //   383: aload_0
    //   384: astore 8
    //   386: aload 6
    //   388: astore 9
    //   390: aload 12
    //   392: iconst_0
    //   393: aaload
    //   394: invokevirtual 216	android/media/Image$Plane:getPixelStride	()I
    //   397: istore 4
    //   399: aload 5
    //   401: astore 7
    //   403: aload_0
    //   404: astore 8
    //   406: aload 6
    //   408: astore 9
    //   410: aload 12
    //   412: iconst_0
    //   413: aaload
    //   414: invokevirtual 219	android/media/Image$Plane:getRowStride	()I
    //   417: iload 4
    //   419: iload_2
    //   420: imul
    //   421: isub
    //   422: iload 4
    //   424: idiv
    //   425: iload_2
    //   426: iadd
    //   427: iload_3
    //   428: getstatic 225	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   431: invokestatic 231	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   434: astore 12
    //   436: aload 5
    //   438: astore 7
    //   440: aload_0
    //   441: astore 8
    //   443: aload 6
    //   445: astore 9
    //   447: aload 12
    //   449: aload 11
    //   451: invokevirtual 235	android/graphics/Bitmap:copyPixelsFromBuffer	(Ljava/nio/Buffer;)V
    //   454: aload 5
    //   456: astore 7
    //   458: aload_0
    //   459: astore 8
    //   461: aload 6
    //   463: astore 9
    //   465: aload 12
    //   467: iconst_0
    //   468: iconst_0
    //   469: iload_2
    //   470: iload_3
    //   471: invokestatic 238	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIII)Landroid/graphics/Bitmap;
    //   474: astore 11
    //   476: aload 5
    //   478: astore 7
    //   480: aload_0
    //   481: astore 8
    //   483: aload 6
    //   485: astore 9
    //   487: aload 10
    //   489: invokevirtual 241	android/media/Image:close	()V
    //   492: aload 5
    //   494: astore 7
    //   496: aload_0
    //   497: astore 8
    //   499: aload 6
    //   501: astore 9
    //   503: aload_1
    //   504: aload 11
    //   506: invokeinterface 245 2 0
    //   511: aload 5
    //   513: astore 7
    //   515: aload_0
    //   516: astore 8
    //   518: aload 6
    //   520: astore 9
    //   522: aconst_null
    //   523: putstatic 74	com/branch/www/screencapture/b:b	Lcom/androlua/LuaAccessibilityService;
    //   526: aload 5
    //   528: astore 7
    //   530: aload_0
    //   531: astore 8
    //   533: aload 6
    //   535: astore 9
    //   537: aconst_null
    //   538: putstatic 87	com/branch/www/screencapture/b:c	Lcom/branch/www/screencapture/a;
    //   541: aload_0
    //   542: ifnull +7 -> 549
    //   545: aload_0
    //   546: invokevirtual 250	android/hardware/display/VirtualDisplay:release	()V
    //   549: aload 5
    //   551: ifnull +8 -> 559
    //   554: aload 5
    //   556: invokevirtual 251	android/media/ImageReader:close	()V
    //   559: aload 6
    //   561: ifnull +189 -> 750
    //   564: aload 6
    //   566: astore_1
    //   567: goto +179 -> 746
    //   570: astore 7
    //   572: aload 5
    //   574: astore 10
    //   576: aload 6
    //   578: astore_1
    //   579: aload 7
    //   581: astore 5
    //   583: goto +78 -> 661
    //   586: astore_1
    //   587: aconst_null
    //   588: astore_0
    //   589: goto +174 -> 763
    //   592: astore 7
    //   594: aconst_null
    //   595: astore_0
    //   596: aload 5
    //   598: astore 10
    //   600: aload 6
    //   602: astore_1
    //   603: aload 7
    //   605: astore 5
    //   607: goto +54 -> 661
    //   610: astore_1
    //   611: aconst_null
    //   612: astore 6
    //   614: aload 6
    //   616: astore_0
    //   617: goto +146 -> 763
    //   620: astore 6
    //   622: aconst_null
    //   623: astore_1
    //   624: aload_1
    //   625: astore_0
    //   626: aload 5
    //   628: astore 10
    //   630: aload 6
    //   632: astore 5
    //   634: goto +27 -> 661
    //   637: astore_1
    //   638: aconst_null
    //   639: astore 6
    //   641: aload 6
    //   643: astore 5
    //   645: aload 5
    //   647: astore_0
    //   648: goto +115 -> 763
    //   651: astore 5
    //   653: aconst_null
    //   654: astore_1
    //   655: aload_1
    //   656: astore 10
    //   658: aload 10
    //   660: astore_0
    //   661: aload 10
    //   663: astore 7
    //   665: aload_0
    //   666: astore 8
    //   668: aload_1
    //   669: astore 9
    //   671: aload 5
    //   673: invokevirtual 252	java/lang/Exception:printStackTrace	()V
    //   676: aload 10
    //   678: astore 7
    //   680: aload_0
    //   681: astore 8
    //   683: aload_1
    //   684: astore 9
    //   686: getstatic 87	com/branch/www/screencapture/b:c	Lcom/branch/www/screencapture/a;
    //   689: ldc -57
    //   691: invokeinterface 93 2 0
    //   696: aload 10
    //   698: astore 7
    //   700: aload_0
    //   701: astore 8
    //   703: aload_1
    //   704: astore 9
    //   706: aconst_null
    //   707: putstatic 74	com/branch/www/screencapture/b:b	Lcom/androlua/LuaAccessibilityService;
    //   710: aload 10
    //   712: astore 7
    //   714: aload_0
    //   715: astore 8
    //   717: aload_1
    //   718: astore 9
    //   720: aconst_null
    //   721: putstatic 87	com/branch/www/screencapture/b:c	Lcom/branch/www/screencapture/a;
    //   724: aload_0
    //   725: ifnull +7 -> 732
    //   728: aload_0
    //   729: invokevirtual 250	android/hardware/display/VirtualDisplay:release	()V
    //   732: aload 10
    //   734: ifnull +8 -> 742
    //   737: aload 10
    //   739: invokevirtual 251	android/media/ImageReader:close	()V
    //   742: aload_1
    //   743: ifnull +7 -> 750
    //   746: aload_1
    //   747: invokevirtual 255	android/media/projection/MediaProjection:stop	()V
    //   750: return
    //   751: astore_1
    //   752: aload 9
    //   754: astore 6
    //   756: aload 8
    //   758: astore_0
    //   759: aload 7
    //   761: astore 5
    //   763: aload_0
    //   764: ifnull +7 -> 771
    //   767: aload_0
    //   768: invokevirtual 250	android/hardware/display/VirtualDisplay:release	()V
    //   771: aload 5
    //   773: ifnull +8 -> 781
    //   776: aload 5
    //   778: invokevirtual 251	android/media/ImageReader:close	()V
    //   781: aload 6
    //   783: ifnull +8 -> 791
    //   786: aload 6
    //   788: invokevirtual 255	android/media/projection/MediaProjection:stop	()V
    //   791: aload_1
    //   792: athrow
    //   793: iload_2
    //   794: iconst_1
    //   795: iadd
    //   796: istore_2
    //   797: aload 10
    //   799: astore 7
    //   801: goto -595 -> 206
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	804	0	paramLuaAccessibilityService	LuaAccessibilityService
    //   0	804	1	parama	a
    //   100	697	2	i1	int
    //   113	358	3	i2	int
    //   106	319	4	i3	int
    //   50	596	5	localObject1	Object
    //   651	21	5	localException1	Exception
    //   761	16	5	localObject2	Object
    //   46	569	6	localObject3	Object
    //   620	11	6	localException2	Exception
    //   639	148	6	localObject4	Object
    //   14	515	7	localObject5	Object
    //   570	10	7	localException3	Exception
    //   592	12	7	localException4	Exception
    //   663	137	7	localObject6	Object
    //   187	570	8	localLuaAccessibilityService	LuaAccessibilityService
    //   191	562	9	localObject7	Object
    //   198	11	10	localObject8	Object
    //   236	14	10	localInterruptedException	InterruptedException
    //   270	528	10	localObject9	Object
    //   377	128	11	localObject10	Object
    //   357	109	12	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   227	233	236	java/lang/InterruptedException
    //   193	200	570	java/lang/Exception
    //   227	233	570	java/lang/Exception
    //   249	254	570	java/lang/Exception
    //   265	272	570	java/lang/Exception
    //   296	304	570	java/lang/Exception
    //   318	324	570	java/lang/Exception
    //   335	341	570	java/lang/Exception
    //   352	359	570	java/lang/Exception
    //   370	379	570	java/lang/Exception
    //   390	399	570	java/lang/Exception
    //   410	436	570	java/lang/Exception
    //   447	454	570	java/lang/Exception
    //   465	476	570	java/lang/Exception
    //   487	492	570	java/lang/Exception
    //   503	511	570	java/lang/Exception
    //   522	526	570	java/lang/Exception
    //   537	541	570	java/lang/Exception
    //   161	182	586	finally
    //   161	182	592	java/lang/Exception
    //   143	161	610	finally
    //   143	161	620	java/lang/Exception
    //   16	45	637	finally
    //   58	78	637	finally
    //   83	114	637	finally
    //   117	133	637	finally
    //   133	143	637	finally
    //   16	45	651	java/lang/Exception
    //   58	78	651	java/lang/Exception
    //   83	114	651	java/lang/Exception
    //   117	133	651	java/lang/Exception
    //   133	143	651	java/lang/Exception
    //   193	200	751	finally
    //   227	233	751	finally
    //   249	254	751	finally
    //   265	272	751	finally
    //   296	304	751	finally
    //   318	324	751	finally
    //   335	341	751	finally
    //   352	359	751	finally
    //   370	379	751	finally
    //   390	399	751	finally
    //   410	436	751	finally
    //   447	454	751	finally
    //   465	476	751	finally
    //   487	492	751	finally
    //   503	511	751	finally
    //   522	526	751	finally
    //   537	541	751	finally
    //   671	676	751	finally
    //   686	696	751	finally
    //   706	710	751	finally
    //   720	724	751	finally
  }
  
  private void h()
  {
    WindowManager localWindowManager = (WindowManager)this.d.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    this.n = localDisplayMetrics.densityDpi;
    this.l = localDisplayMetrics.widthPixels;
    this.m = localDisplayMetrics.heightPixels;
    i();
  }
  
  private void i()
  {
    this.k = ImageReader.newInstance(this.l, this.m, 1, 1);
  }
  
  private MediaProjectionManager j()
  {
    return (MediaProjectionManager)this.d.getSystemService("media_projection");
  }
  
  private void k()
  {
    if (this.h == null) {
      d();
    }
    if (this.h == null) {
      return;
    }
    if (this.i != null) {
      return;
    }
    try
    {
      this.i = this.h.createVirtualDisplay("screen-mirror", this.l, this.m, this.n, 16, this.k.getSurface(), this.e, null);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private Bitmap l()
  {
    if (this.k == null) {
      return null;
    }
    this.f = this.k.acquireLatestImage();
    if (this.f == null) {
      return null;
    }
    int i1 = this.f.getWidth();
    int i2 = this.f.getHeight();
    Object localObject2 = this.f.getPlanes();
    Object localObject1 = localObject2[0].getBuffer();
    int i3 = localObject2[0].getPixelStride();
    localObject2 = Bitmap.createBitmap((localObject2[0].getRowStride() - i3 * i1) / i3 + i1, i2, Bitmap.Config.ARGB_8888);
    ((Bitmap)localObject2).copyPixelsFromBuffer((Buffer)localObject1);
    localObject1 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, i1, i2);
    this.f.close();
    this.f = null;
    return (Bitmap)localObject1;
  }
  
  private void m()
  {
    if (this.h != null)
    {
      this.h.stop();
      this.h = null;
    }
  }
  
  private void n()
  {
    if (this.i == null) {
      return;
    }
    this.i.release();
    this.i = null;
  }
  
  private void o()
  {
    if (this.k != null) {
      this.k.close();
    }
    this.k = null;
  }
  
  public Bitmap a()
  {
    return l();
  }
  
  public void b()
  {
    n();
    o();
    h();
    c();
  }
  
  public void c()
  {
    if (this.h != null) {}
    for (;;)
    {
      k();
      return;
      d();
    }
  }
  
  public void d()
  {
    if (this.h != null) {
      return;
    }
    if (j == null)
    {
      Intent localIntent = new Intent(this.d, ScreenCaptureActivity.class);
      localIntent.setFlags(268435456);
      this.d.startActivity(localIntent);
      return;
    }
    this.h = j().getMediaProjection(-1, j);
  }
  
  public void e()
  {
    n();
    m();
    o();
    g = null;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\branch\www\screencapture\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */