package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

public class PrefOperate
{
  public static String getAppKey(Context paramContext)
  {
    return CooperService.a().getAppKey(paramContext);
  }
  
  public static void loadMetaDataConfig(Context paramContext)
  {
    Object localObject1 = SendStrategyEnum.APP_START;
    try
    {
      String str2 = dc.a(paramContext, "BaiduMobAd_EXCEPTION_LOG");
      if ((!TextUtils.isEmpty(str2)) && ("true".equals(str2))) {
        bt.a().a(paramContext, false);
      }
    }
    catch (Exception localException2)
    {
      cz.a(localException2);
    }
    int i;
    try
    {
      Object localObject2 = dc.a(paramContext, "BaiduMobAd_SEND_STRATEGY");
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        if (((String)localObject2).equals(SendStrategyEnum.APP_START.name()))
        {
          localObject2 = SendStrategyEnum.APP_START;
          localObject1 = localObject2;
        }
        for (;;)
        {
          try
          {
            localbj = bj.a();
            localObject1 = localObject2;
            i = ((SendStrategyEnum)localObject2).ordinal();
            localObject1 = localObject2;
            localbj.a(paramContext, i);
          }
          catch (Exception localException3)
          {
            break label190;
          }
          if (localException3.equals(SendStrategyEnum.ONCE_A_DAY.name()))
          {
            localObject3 = SendStrategyEnum.ONCE_A_DAY;
            localObject1 = localObject3;
            bj.a().a(paramContext, ((SendStrategyEnum)localObject3).ordinal());
            localObject1 = localObject3;
            bj.a().b(paramContext, 24);
            break label184;
          }
          if (!((String)localObject3).equals(SendStrategyEnum.SET_TIME_INTERVAL.name())) {
            break;
          }
          localObject3 = SendStrategyEnum.SET_TIME_INTERVAL;
          localObject1 = localObject3;
          bj localbj = bj.a();
          localObject1 = localObject3;
          i = ((SendStrategyEnum)localObject3).ordinal();
        }
      }
      Object localObject3 = localObject1;
      label184:
      localObject1 = localObject3;
    }
    catch (Exception localException4)
    {
      label190:
      cz.a(localException4);
    }
    try
    {
      String str3 = dc.a(paramContext, "BaiduMobAd_TIME_INTERVAL");
      if (!TextUtils.isEmpty(str3))
      {
        i = Integer.parseInt(str3);
        if ((((SendStrategyEnum)localObject1).ordinal() == SendStrategyEnum.SET_TIME_INTERVAL.ordinal()) && (i > 0) && (i <= 24)) {
          bj.a().b(paramContext, i);
        }
      }
    }
    catch (Exception localException1)
    {
      cz.a(localException1);
    }
    try
    {
      String str1 = dc.a(paramContext, "BaiduMobAd_ONLY_WIFI");
      if (!TextUtils.isEmpty(str1))
      {
        if ("true".equals(str1))
        {
          bj.a().a(paramContext, true);
          return;
        }
        if ("false".equals(str1))
        {
          bj.a().a(paramContext, false);
          return;
        }
      }
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
    }
  }
  
  public static void setAppChannel(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      cz.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
    }
    CooperService.a().getHeadObject().m = paramString;
    if ((paramBoolean) && (paramString != null) && (!paramString.equals("")))
    {
      bj.a().c(paramContext, paramString);
      bj.a().b(paramContext, true);
    }
    if (!paramBoolean)
    {
      bj.a().c(paramContext, "");
      bj.a().b(paramContext, false);
    }
  }
  
  public static void setAppChannel(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      cz.c("设置的渠道不能为空或者为null || The channel that you have been set is null or empty, please check it.");
    }
    CooperService.a().getHeadObject().m = paramString;
  }
  
  public static void setAppKey(String paramString)
  {
    CooperService.a().getHeadObject().f = paramString;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\PrefOperate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */