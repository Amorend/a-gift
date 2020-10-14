package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class c
{
  private AuthAgent a;
  private QQToken b;
  
  private c(String paramString, Context paramContext)
  {
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
    this.b = new QQToken(paramString);
    this.a = new AuthAgent(this.b);
    com.tencent.connect.a.a.c(paramContext, this.b);
    f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
  }
  
  private int a(Activity paramActivity, Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    return a(paramActivity, paramFragment, paramString1, paramIUiListener, paramString2, false);
  }
  
  private int a(Activity paramActivity, Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2, boolean paramBoolean)
  {
    String str = paramActivity.getApplicationContext().getPackageName();
    paramString2 = paramActivity.getPackageManager();
    Object localObject = null;
    try
    {
      Iterator localIterator = paramString2.getInstalledApplications(128).iterator();
      for (;;)
      {
        paramString2 = (String)localObject;
        if (localIterator.hasNext())
        {
          paramString2 = (ApplicationInfo)localIterator.next();
          if (str.equals(paramString2.packageName)) {
            paramString2 = paramString2.sourceDir;
          }
        }
        else
        {
          if (paramString2 == null) {
            break;
          }
          paramString2 = com.tencent.open.utils.a.a(new File(paramString2));
          if (TextUtils.isEmpty(paramString2)) {
            break;
          }
          f.a("openSDK_LOG.QQAuth", "-->login channelId: " + paramString2);
          int i = a(paramActivity, paramString1, paramIUiListener, paramString2, paramString2, "");
          return i;
        }
      }
    }
    catch (Throwable paramString2)
    {
      for (;;)
      {
        f.b("openSDK_LOG.QQAuth", "-->login get channel id exception.", paramString2);
        paramString2.printStackTrace();
      }
    }
    f.b("openSDK_LOG.QQAuth", "-->login channelId is null ");
    com.tencent.connect.common.BaseApi.isOEM = false;
    return this.a.doLogin(paramActivity, paramString1, paramIUiListener, false, paramFragment, paramBoolean);
  }
  
  public static c a(String paramString, Context paramContext)
  {
    e.a(paramContext.getApplicationContext());
    f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
      localPackageManager.getActivityInfo(new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
      paramString = new c(paramString, paramContext);
      f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      f.b("openSDK_LOG.QQAuth", "createInstance() error --end", paramString);
      Toast.makeText(paramContext.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
    }
    return null;
  }
  
  public int a(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "login()");
    return a(paramActivity, paramString, paramIUiListener, "");
  }
  
  public int a(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + paramActivity);
    return a(paramActivity, null, paramString1, paramIUiListener, paramString2);
  }
  
  @Deprecated
  public int a(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2, String paramString3, String paramString4)
  {
    String str = paramString2;
    f.c("openSDK_LOG.QQAuth", "loginWithOEM");
    com.tencent.connect.common.BaseApi.isOEM = true;
    paramString2 = str;
    if (str.equals("")) {
      paramString2 = "null";
    }
    str = paramString3;
    if (paramString3.equals("")) {
      str = "null";
    }
    paramString3 = paramString4;
    if (paramString4.equals("")) {
      paramString3 = "null";
    }
    com.tencent.connect.common.BaseApi.installChannel = str;
    com.tencent.connect.common.BaseApi.registerChannel = paramString2;
    com.tencent.connect.common.BaseApi.businessId = paramString3;
    return this.a.doLogin(paramActivity, paramString1, paramIUiListener);
  }
  
  public int a(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean)
  {
    f.c("openSDK_LOG.QQAuth", "login()");
    return a(paramActivity, null, paramString, paramIUiListener, "", paramBoolean);
  }
  
  public int a(Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return a(localFragmentActivity, paramFragment, paramString1, paramIUiListener, paramString2);
  }
  
  public int a(Fragment paramFragment, String paramString1, IUiListener paramIUiListener, String paramString2, boolean paramBoolean)
  {
    FragmentActivity localFragmentActivity = paramFragment.getActivity();
    f.c("openSDK_LOG.QQAuth", "-->login activity: " + localFragmentActivity);
    return a(localFragmentActivity, paramFragment, paramString1, paramIUiListener, paramString2, paramBoolean);
  }
  
  public void a()
  {
    this.a.a(null);
  }
  
  public void a(Context paramContext, String paramString)
  {
    f.a("openSDK_LOG.QQAuth", "setOpenId() --start");
    this.b.setOpenId(paramString);
    com.tencent.connect.a.a.d(paramContext, this.b);
    f.a("openSDK_LOG.QQAuth", "setOpenId() --end");
  }
  
  public void a(IUiListener paramIUiListener)
  {
    this.a.b(paramIUiListener);
  }
  
  public void a(String paramString1, String paramString2)
  {
    f.a("openSDK_LOG.QQAuth", "setAccessToken(), validTimeInSecond = " + paramString2 + "");
    this.b.setAccessToken(paramString1, paramString2);
  }
  
  public int b(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQAuth", "reAuth()");
    return this.a.doLogin(paramActivity, paramString, paramIUiListener, true, null);
  }
  
  public QQToken b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("isSessionValid(), result = ");
    if (this.b.isSessionValid()) {}
    for (String str = "true";; str = "false")
    {
      f.a("openSDK_LOG.QQAuth", str + "");
      return this.b.isSessionValid();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\auth\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */