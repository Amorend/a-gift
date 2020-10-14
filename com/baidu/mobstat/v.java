package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import org.json.JSONException;
import org.json.JSONObject;

public class v
{
  public static JSONObject a(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("s", Build.VERSION.SDK_INT);
      localJSONObject.put("sv", Build.VERSION.RELEASE);
      localJSONObject.put("ii", dc.a(2, paramContext));
      localJSONObject.put("w", dc.b(paramContext));
      localJSONObject.put("h", dc.c(paramContext));
      localJSONObject.put("ly", bc.c);
      localJSONObject.put("pv", "13");
      Object localObject = paramContext.getPackageName();
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        localObject = localPackageManager.getPackageInfo((String)localObject, 0);
        localJSONObject.put("pn", dc.h(2, paramContext));
        localJSONObject.put("a", ((PackageInfo)localObject).versionCode);
        localJSONObject.put("n", ((PackageInfo)localObject).versionName);
      }
      catch (Exception localException)
      {
        bd.a(localException);
      }
      localJSONObject.put("mc", dc.b(2, paramContext));
      localJSONObject.put("bm", dc.f(2, paramContext));
      localJSONObject.put("m", Build.MODEL);
      localJSONObject.put("dn", dc.a(paramContext, 2));
      return localJSONObject;
    }
    catch (JSONException paramContext)
    {
      bd.b(paramContext);
    }
    return localJSONObject;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */