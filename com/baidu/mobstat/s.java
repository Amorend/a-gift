package com.baidu.mobstat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

class s
{
  static s a = new s();
  private String b = "";
  
  private String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).versionName;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      bd.b(paramContext);
      paramContext = "";
    }
    paramString = paramContext;
    if (paramContext == null) {
      paramString = "";
    }
    return paramString;
  }
  
  private ArrayList<t> a(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return c(paramContext, paramInt);
    }
    return b(paramContext, paramInt);
  }
  
  private void a(Context paramContext, ArrayList<t> paramArrayList, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  private void a(Context paramContext, boolean paramBoolean, int paramInt)
  {
    ArrayList localArrayList = a(paramContext, paramInt);
    if (localArrayList != null)
    {
      if (localArrayList.size() == 0) {
        return;
      }
      if (paramBoolean)
      {
        String str = ((t)localArrayList.get(0)).b();
        if (a(str, this.b)) {
          this.b = str;
        }
      }
      a(paramContext, localArrayList, paramBoolean);
    }
  }
  
  private boolean a(int paramInt)
  {
    return (paramInt == 100) || (paramInt == 200) || (paramInt == 130);
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    return (!TextUtils.isEmpty(paramString1)) && (!paramString1.equals(this.b));
  }
  
  private ArrayList<t> b(Context paramContext, int paramInt)
  {
    Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
    try
    {
      localObject1 = ((ActivityManager)localObject1).getRunningTasks(50);
    }
    catch (Exception localException)
    {
      bd.b(localException);
      localIterator = null;
    }
    if (localIterator == null) {
      return new ArrayList();
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = localIterator.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (ActivityManager.RunningTaskInfo)localIterator.next();
      if (localLinkedHashMap.size() > paramInt) {
        break;
      }
      localObject2 = ((ActivityManager.RunningTaskInfo)localObject2).topActivity;
      if (localObject2 != null)
      {
        localObject2 = ((ComponentName)localObject2).getPackageName();
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!b(paramContext, (String)localObject2)) && (!localLinkedHashMap.containsKey(localObject2))) {
          localLinkedHashMap.put(localObject2, new t((String)localObject2, a(paramContext, (String)localObject2), ""));
        }
      }
    }
    return new ArrayList(localLinkedHashMap.values());
  }
  
  private boolean b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).applicationInfo;
      if (paramContext == null) {
        return false;
      }
      int i = paramContext.flags;
      if ((i & 0x1) != 0) {
        return true;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      bd.b(paramContext);
    }
    return false;
  }
  
  private ArrayList<t> c(Context paramContext, int paramInt)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localList == null) {
      return new ArrayList();
    }
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    int i = 0;
    while ((i < localList.size()) && (localLinkedHashMap.size() <= paramInt))
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(i);
      if (a(localRunningAppProcessInfo.importance))
      {
        Object localObject = localRunningAppProcessInfo.pkgList;
        if ((localObject != null) && (localObject.length != 0))
        {
          localObject = localRunningAppProcessInfo.pkgList[0];
          if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!b(paramContext, (String)localObject)) && (!localLinkedHashMap.containsKey(localObject))) {
            localLinkedHashMap.put(localObject, new t((String)localObject, a(paramContext, (String)localObject), String.valueOf(localRunningAppProcessInfo.importance)));
          }
        }
      }
      i += 1;
    }
    return new ArrayList(localLinkedHashMap.values());
  }
  
  public void a(Context paramContext, boolean paramBoolean)
  {
    int i = 1;
    if (!paramBoolean) {
      i = 20;
    }
    try
    {
      a(paramContext, paramBoolean, i);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */