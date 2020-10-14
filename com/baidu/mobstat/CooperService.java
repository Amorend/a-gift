package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CooperService
  extends bk
  implements ICooperService
{
  private static CooperService a;
  private bu b = new bu();
  
  static CooperService a()
  {
    try
    {
      if (a == null) {
        a = new CooperService();
      }
      CooperService localCooperService = a;
      return localCooperService;
    }
    finally {}
  }
  
  private static String a(Context paramContext)
  {
    String str = dc.j(paramContext);
    paramContext = str;
    if (!TextUtils.isEmpty(str)) {
      paramContext = str.replaceAll(":", "");
    }
    return paramContext;
  }
  
  private String a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramContext = b(paramContext);
    } else {
      paramContext = a(paramContext);
    }
    Object localObject = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject = "";
    }
    return (String)localObject;
  }
  
  private static String b(Context paramContext)
  {
    String str = dc.i(paramContext);
    paramContext = str;
    if (!TextUtils.isEmpty(str)) {
      paramContext = str.replaceAll(":", "");
    }
    return paramContext;
  }
  
  private static String c(Context paramContext)
  {
    String str = dc.k(paramContext);
    paramContext = str;
    if (!TextUtils.isEmpty(str)) {
      paramContext = str.replaceAll(":", "");
    }
    return paramContext;
  }
  
  private String d(Context paramContext)
  {
    try
    {
      if ((this.b.m == null) || (this.b.m.equals("")))
      {
        boolean bool = bj.a().g(paramContext);
        if (bool) {
          this.b.m = bj.a().f(paramContext);
        }
        if ((!bool) || (this.b.m == null) || (this.b.m.equals(""))) {
          this.b.m = dc.a(paramContext, "BaiduMobAd_CHANNEL");
        }
      }
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
    }
    return this.b.m;
  }
  
  public boolean checkCellLocationSetting(Context paramContext)
  {
    return "true".equalsIgnoreCase(dc.a(paramContext, "BaiduMobAd_CELL_LOCATION"));
  }
  
  public boolean checkGPSLocationSetting(Context paramContext)
  {
    return "true".equals(dc.a(paramContext, "BaiduMobAd_GPS_LOCATION"));
  }
  
  public boolean checkWifiLocationSetting(Context paramContext)
  {
    return "true".equalsIgnoreCase(dc.a(paramContext, "BaiduMobAd_WIFI_LOCATION"));
  }
  
  public void enableDeviceMac(Context paramContext, boolean paramBoolean)
  {
    bj.a().d(paramContext, paramBoolean);
  }
  
  public String getAppChannel(Context paramContext)
  {
    return d(paramContext);
  }
  
  public String getAppKey(Context paramContext)
  {
    if (this.b.f == null) {
      this.b.f = dc.a(paramContext, "BaiduMobAd_STAT_ID");
    }
    return this.b.f;
  }
  
  public int getAppVersionCode(Context paramContext)
  {
    if (this.b.h == -1) {
      this.b.h = dc.e(paramContext);
    }
    return this.b.h;
  }
  
  public String getAppVersionName(Context paramContext)
  {
    if (TextUtils.isEmpty(this.b.i)) {
      this.b.i = dc.f(paramContext);
    }
    return this.b.i;
  }
  
  public String getCUID(Context paramContext, boolean paramBoolean)
  {
    if (this.b.g == null)
    {
      this.b.g = bj.a().e(paramContext);
      if ((this.b.g == null) || ("".equalsIgnoreCase(this.b.g))) {
        try
        {
          this.b.g = de.a(paramContext);
          Matcher localMatcher = Pattern.compile("\\s*|\t|\r|\n").matcher(this.b.g);
          this.b.g = localMatcher.replaceAll("");
          this.b.g = getSecretValue(this.b.g);
          bj.a().b(paramContext, this.b.g);
        }
        catch (Exception paramContext)
        {
          cz.c(paramContext.getMessage());
        }
      }
    }
    if (paramBoolean) {
      return this.b.g;
    }
    try
    {
      paramContext = this.b.g;
      if (!TextUtils.isEmpty(paramContext))
      {
        paramContext = new String(cr.b(1, ct.a(paramContext.getBytes())));
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
    }
    return null;
  }
  
  public String getDeviceId(TelephonyManager paramTelephonyManager, Context paramContext)
  {
    Object localObject = this.b.j;
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      return this.b.j;
    }
    if (bj.a().i(paramContext)) {
      paramTelephonyManager = this.b;
    }
    for (paramContext = getMacIdForTv(paramContext);; paramContext = getSecretValue(this.b.j))
    {
      paramTelephonyManager.j = paramContext;
      break;
      if (paramTelephonyManager == null) {
        break;
      }
      Pattern localPattern = Pattern.compile("\\s*|\t|\r|\n");
      try
      {
        String str = paramTelephonyManager.getDeviceId();
        paramTelephonyManager = (TelephonyManager)localObject;
        if (str != null) {
          paramTelephonyManager = localPattern.matcher(str).replaceAll("");
        }
      }
      catch (Exception paramTelephonyManager)
      {
        cz.a(paramTelephonyManager);
        paramTelephonyManager = (TelephonyManager)localObject;
      }
      if (paramTelephonyManager != null)
      {
        localObject = paramTelephonyManager;
        if (!paramTelephonyManager.equals("000000000000000")) {}
      }
      else
      {
        localObject = a(paramContext);
      }
      if ((dc.s(paramContext)) && ((TextUtils.isEmpty((CharSequence)localObject)) || (((String)localObject).equals("000000000000000")))) {
        try
        {
          paramTelephonyManager = c(paramContext);
          localObject = paramTelephonyManager;
        }
        catch (Exception paramTelephonyManager)
        {
          cz.a(paramTelephonyManager);
        }
      }
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        paramTelephonyManager = (TelephonyManager)localObject;
        if (!((String)localObject).equals("000000000000000")) {}
      }
      else
      {
        paramTelephonyManager = bj.a().d(paramContext);
      }
      if (!TextUtils.isEmpty(paramTelephonyManager))
      {
        localObject = paramTelephonyManager;
        if (!paramTelephonyManager.equals("000000000000000")) {}
      }
      else
      {
        paramTelephonyManager = new StringBuilder();
        paramTelephonyManager.append(new Date().getTime());
        paramTelephonyManager.append("");
        paramTelephonyManager = paramTelephonyManager.toString();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("hol");
        ((StringBuilder)localObject).append(paramTelephonyManager.hashCode());
        ((StringBuilder)localObject).append("mes");
        localObject = ((StringBuilder)localObject).toString();
        bj.a().a(paramContext, (String)localObject);
      }
      this.b.j = ((String)localObject);
      paramTelephonyManager = this.b;
    }
  }
  
  public bu getHeadObject()
  {
    return this.b;
  }
  
  public JSONObject getHeaderExt(Context paramContext)
  {
    String str = bj.a().k(paramContext);
    JSONObject localJSONObject = new JSONObject();
    paramContext = localJSONObject;
    if (!TextUtils.isEmpty(str)) {}
    try
    {
      paramContext = new JSONObject(str);
      return paramContext;
    }
    catch (JSONException paramContext) {}
    return localJSONObject;
  }
  
  public String getHost()
  {
    return Config.LOG_SEND_URL;
  }
  
  public String getLinkedWay(Context paramContext)
  {
    if (TextUtils.isEmpty(this.b.s)) {
      this.b.s = dc.o(paramContext);
    }
    return this.b.s;
  }
  
  public String getMTJSDKVersion()
  {
    return "3.7.5.5";
  }
  
  public String getMacAddress(Context paramContext, boolean paramBoolean)
  {
    String str1 = "02:00:00:00:00:00".replace(":", "");
    if ((!paramBoolean) && (Build.VERSION.SDK_INT >= 23)) {
      return getSecretValue(str1);
    }
    if (!TextUtils.isEmpty(this.b.t)) {}
    for (;;)
    {
      return this.b.t;
      String str2 = bj.a().h(paramContext);
      if (!TextUtils.isEmpty(str2))
      {
        this.b.t = str2;
      }
      else
      {
        str2 = a(paramContext, paramBoolean);
        if ((!TextUtils.isEmpty(str2)) && (!str1.equals(str2)))
        {
          str1 = getSecretValue(str2);
          this.b.t = str1;
          bj.a().d(paramContext, this.b.t);
        }
        else
        {
          this.b.t = "";
        }
      }
    }
  }
  
  public String getMacIdForTv(Context paramContext)
  {
    if (!TextUtils.isEmpty(this.b.u)) {
      return this.b.u;
    }
    String str = bj.a().j(paramContext);
    Object localObject;
    if (!TextUtils.isEmpty(str)) {
      localObject = this.b;
    }
    for (paramContext = str;; paramContext = "")
    {
      ((bu)localObject).u = paramContext;
      break;
      localObject = dc.c(1, paramContext);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        this.b.u = ((String)localObject);
        bj.a().e(paramContext, (String)localObject);
        break;
      }
      localObject = this.b;
    }
  }
  
  public String getManufacturer()
  {
    if (TextUtils.isEmpty(this.b.p)) {
      this.b.p = Build.MANUFACTURER;
    }
    return this.b.p;
  }
  
  public String getOSSysVersion()
  {
    if (TextUtils.isEmpty(this.b.d)) {
      this.b.d = Build.VERSION.RELEASE;
    }
    return this.b.d;
  }
  
  public String getOSVersion()
  {
    if (TextUtils.isEmpty(this.b.c)) {
      this.b.c = Integer.toString(Build.VERSION.SDK_INT);
    }
    return this.b.c;
  }
  
  public String getOperator(TelephonyManager paramTelephonyManager)
  {
    if (TextUtils.isEmpty(this.b.n)) {
      this.b.n = paramTelephonyManager.getNetworkOperator();
    }
    return this.b.n;
  }
  
  public String getPhoneModel()
  {
    if (TextUtils.isEmpty(this.b.o)) {
      this.b.o = Build.MODEL;
    }
    return this.b.o;
  }
  
  public String getSecretValue(String paramString)
  {
    return cr.c(1, paramString.getBytes());
  }
  
  public int getTagValue()
  {
    return 1;
  }
  
  public String getUUID()
  {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  public void installHeader(Context paramContext, JSONObject paramJSONObject)
  {
    this.b.a(paramContext, paramJSONObject);
  }
  
  public boolean isDeviceMacEnabled(Context paramContext)
  {
    return bj.a().l(paramContext);
  }
  
  public void resetHeadSign()
  {
    this.b.x = a().getUUID();
  }
  
  public void setHeadSqCounted(boolean paramBoolean)
  {
    bu localbu;
    if (paramBoolean) {
      localbu = this.b;
    }
    for (int i = 0;; i = localbu.z + 1)
    {
      localbu.z = i;
      return;
      localbu = this.b;
    }
  }
  
  public void setHeaderExt(Context paramContext, ExtraInfo paramExtraInfo)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramExtraInfo != null) {
      localJSONObject = paramExtraInfo.dumpToJson();
    }
    this.b.a(localJSONObject);
    bj.a().f(paramContext, localJSONObject.toString());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\CooperService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */