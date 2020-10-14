package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

class q
{
  static q a = new q();
  
  public void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getPackageManager();
    if (!"android.intent.action.PACKAGE_REMOVED".equals(paramString1)) {
      try
      {
        paramContext = paramContext.getPackageInfo(paramString2, 8192).versionName;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        bd.a(paramContext);
      }
    } else {
      paramContext = "unkown";
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("n", paramString2);
      localJSONObject.put("a", paramString1);
      localJSONObject.put("v", paramContext);
      paramContext = new JSONArray();
      paramContext.put(localJSONObject);
      paramString1 = new StringBuilder();
      paramString1.append(System.currentTimeMillis());
      paramString2 = new JSONObject();
      paramString2.put("app_change", paramContext);
      paramString2.put("meta-data", paramString1.toString());
      paramContext = cq.a(paramString2.toString().getBytes());
    }
    catch (Exception paramContext)
    {
      bd.b(paramContext.getMessage());
      paramContext = "";
    }
    if (!TextUtils.isEmpty(paramContext))
    {
      long l = System.currentTimeMillis();
      y.d.a(l, paramContext);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */