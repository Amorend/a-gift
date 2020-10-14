package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class o
{
  static o a = new o();
  
  private void a(Context paramContext, ArrayList<p> paramArrayList)
  {
    paramContext = new StringBuilder();
    paramContext.append(System.currentTimeMillis());
    try
    {
      JSONArray localJSONArray = new JSONArray();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        JSONObject localJSONObject = ((p)paramArrayList.next()).a();
        if (localJSONObject != null) {
          localJSONArray.put(localJSONObject);
        }
      }
      paramArrayList = new JSONObject();
      paramArrayList.put("app_apk", localJSONArray);
      paramArrayList.put("meta-data", paramContext.toString());
      paramContext = cq.a(paramArrayList.toString().getBytes());
    }
    catch (Exception paramContext)
    {
      bd.b(paramContext);
      paramContext = "";
    }
    if (!TextUtils.isEmpty(paramContext)) {
      y.e.a(System.currentTimeMillis(), paramContext);
    }
  }
  
  private void b(Context paramContext)
  {
    a(paramContext, c(paramContext));
  }
  
  private ArrayList<p> c(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = d(paramContext).iterator();
    while (localIterator.hasNext())
    {
      paramContext = (PackageInfo)localIterator.next();
      Object localObject = paramContext.applicationInfo;
      if (localObject != null)
      {
        String str2 = paramContext.packageName;
        String str3 = paramContext.versionName;
        String str1 = "";
        Signature[] arrayOfSignature = paramContext.signatures;
        paramContext = str1;
        if (arrayOfSignature != null)
        {
          paramContext = str1;
          if (arrayOfSignature.length != 0) {
            paramContext = arrayOfSignature[0].toChars().toString();
          }
        }
        str1 = cx.a(paramContext.getBytes());
        paramContext = "";
        localObject = ((ApplicationInfo)localObject).sourceDir;
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          paramContext = cx.a(new File((String)localObject));
        }
        localArrayList.add(new p(str2, str3, str1, paramContext));
      }
    }
    return localArrayList;
  }
  
  private ArrayList<PackageInfo> d(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramContext.getPackageManager();
    if (localObject == null) {
      return localArrayList;
    }
    paramContext = new ArrayList(1);
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(64);
      paramContext = (Context)localObject;
    }
    catch (Exception localException)
    {
      bd.b(localException);
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if ((localApplicationInfo != null) && ((localApplicationInfo.flags & 0x1) == 0)) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public void a(Context paramContext)
  {
    try
    {
      b(paramContext);
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */