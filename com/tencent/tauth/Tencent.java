package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.SocialApi;
import com.tencent.open.SocialOperation;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Tencent
{
  public static final int REQUEST_LOGIN = 10001;
  private static Tencent b;
  private final c a;
  
  private Tencent(String paramString, Context paramContext)
  {
    this.a = c.a(paramString, paramContext);
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    try
    {
      ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), "com.tencent.tauth.AuthActivity");
      paramContext.getPackageManager().getActivityInfo(localComponentName, 0);
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        paramString = new ComponentName(paramContext.getPackageName(), "com.tencent.connect.common.AssistActivity");
        paramContext.getPackageManager().getActivityInfo(paramString, 0);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext = "没有在AndroidManifest.xml中检测到com.tencent.connect.common.AssistActivity,请加上com.tencent.connect.common.AssistActivity,详细信息请查看官网文档." + "\n配置示例如下: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>";
        com.tencent.open.a.f.e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.connect.common.AssistActivity\n" + paramContext);
      }
      paramContext = paramContext;
      paramContext = "没有在AndroidManifest.xml中检测到com.tencent.tauth.AuthActivity,请加上com.tencent.tauth.AuthActivity,并配置<data android:scheme=\"tencent" + paramString + "\" />,详细信息请查看官网文档.";
      paramContext = paramContext + "\n配置示例如下: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + paramString + "\" />\n" + "</intent-filter>\n" + "</activity>";
      com.tencent.open.a.f.e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.tauth.AuthActivity" + paramContext);
      return false;
    }
  }
  
  public static Tencent createInstance(String paramString, Context paramContext)
  {
    for (;;)
    {
      try
      {
        e.a(paramContext.getApplicationContext());
        com.tencent.open.a.f.c("openSDK_LOG.Tencent", "createInstance()  -- start, appId = " + paramString);
        if (b == null)
        {
          b = new Tencent(paramString, paramContext);
          boolean bool = a(paramContext, paramString);
          if (!bool)
          {
            paramString = null;
            return paramString;
          }
        }
        else
        {
          if (paramString.equals(b.getAppId())) {
            continue;
          }
          b.logout(paramContext);
          b = new Tencent(paramString, paramContext);
          continue;
        }
        com.tencent.open.utils.f.a(paramContext, paramString);
      }
      finally {}
      com.tencent.open.a.f.c("openSDK_LOG.Tencent", "createInstance()  -- end");
      paramString = b;
    }
  }
  
  public static void handleResultData(Intent paramIntent, IUiListener paramIUiListener)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("handleResultData() data = null ? ");
    if (paramIntent == null)
    {
      bool = true;
      localStringBuilder = localStringBuilder.append(bool).append(", listener = null ? ");
      if (paramIUiListener != null) {
        break label63;
      }
    }
    label63:
    for (boolean bool = true;; bool = false)
    {
      com.tencent.open.a.f.c("openSDK_LOG.Tencent", bool);
      UIListenerManager.getInstance().handleDataToListener(paramIntent, paramIUiListener);
      return;
      bool = false;
      break;
    }
  }
  
  public static boolean onActivityResultData(int paramInt1, int paramInt2, Intent paramIntent, IUiListener paramIUiListener)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("onActivityResultData() reqcode = ").append(paramInt1).append(", resultcode = ").append(paramInt2).append(", data = null ? ");
    if (paramIntent == null)
    {
      bool = true;
      localStringBuilder = localStringBuilder.append(bool).append(", listener = null ? ");
      if (paramIUiListener != null) {
        break label92;
      }
    }
    label92:
    for (boolean bool = true;; bool = false)
    {
      com.tencent.open.a.f.c("openSDK_LOG.Tencent", bool);
      return UIListenerManager.getInstance().onActivityResult(paramInt1, paramInt2, paramIntent, paramIUiListener);
      bool = false;
      break;
    }
  }
  
  public int ask(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "ask()");
    new SocialApi(this.a.b()).ask(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }
  
  public void bindQQGroup(Activity paramActivity, Bundle paramBundle)
  {
    new SocialOperation(getQQToken()).bindQQGroup(paramActivity, paramBundle);
  }
  
  public void checkLogin(IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "checkLogin()");
    this.a.a(paramIUiListener);
  }
  
  public boolean checkSessionValid(String paramString)
  {
    Object localObject = this.a.b().loadSession(paramString);
    if ((localObject != null) && (((JSONObject)localObject).length() != 0)) {}
    try
    {
      paramString = ((JSONObject)localObject).getString("access_token");
      String str1 = ((JSONObject)localObject).getString("expires_in");
      String str2 = ((JSONObject)localObject).getString("openid");
      localObject = ((JSONObject)localObject).getString("expires_time");
      if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty((CharSequence)localObject)))
      {
        long l1 = Long.parseLong((String)localObject);
        long l2 = System.currentTimeMillis();
        if (l2 < l1) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
      com.tencent.open.a.f.c("QQToken", "checkSessionValid " + paramString.toString());
    }
    return false;
  }
  
  public String getAccessToken()
  {
    return this.a.b().getAccessToken();
  }
  
  public String getAppId()
  {
    return this.a.b().getAppId();
  }
  
  public long getExpiresIn()
  {
    return this.a.b().getExpireTimeInSecond();
  }
  
  public String getOpenId()
  {
    return this.a.b().getOpenId();
  }
  
  public QQToken getQQToken()
  {
    return this.a.b();
  }
  
  public int gift(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "gift()");
    new SocialApi(this.a.b()).gift(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }
  
  @Deprecated
  public void handleLoginData(Intent paramIntent, IUiListener paramIUiListener)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("handleLoginData() data = null ? ");
    if (paramIntent == null)
    {
      bool = true;
      localStringBuilder = localStringBuilder.append(bool).append(", listener = null ? ");
      if (paramIUiListener != null) {
        break label68;
      }
    }
    label68:
    for (boolean bool = true;; bool = false)
    {
      com.tencent.open.a.f.c("openSDK_LOG.Tencent", bool);
      UIListenerManager.getInstance().handleDataToListener(paramIntent, paramIUiListener);
      return;
      bool = false;
      break;
    }
  }
  
  public void initSessionCache(JSONObject paramJSONObject)
  {
    try
    {
      String str1 = paramJSONObject.getString("access_token");
      String str2 = paramJSONObject.getString("expires_in");
      paramJSONObject = paramJSONObject.getString("openid");
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(paramJSONObject)))
      {
        setAccessToken(str1, str2);
        setOpenId(paramJSONObject);
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      com.tencent.open.a.f.c("QQToken", "initSessionCache " + paramJSONObject.toString());
    }
  }
  
  public int invite(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "invite()");
    new SocialApi(this.a.b()).invite(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }
  
  public boolean isQQInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mobileqq")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public boolean isReady()
  {
    if ((isSessionValid()) && (getOpenId() != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isSessionValid()
  {
    return this.a.c();
  }
  
  public boolean isSupportSSOLogin(Activity paramActivity)
  {
    if ((k.d(paramActivity)) && (h.a(paramActivity, "com.tencent.minihd.qq") != null)) {
      return true;
    }
    if ((h.c(paramActivity, "4.1") >= 0) || (h.d(paramActivity, "1.1") >= 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean joinQQGroup(Activity paramActivity, String paramString)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "joinQQGroup()");
    Intent localIntent = new Intent();
    String str1 = this.a.b().getOpenId();
    String str2 = this.a.b().getAppId();
    paramString = new StringBuffer("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + paramString);
    if (!TextUtils.isEmpty(str1)) {
      paramString.append("&openid=" + Base64.encodeToString(k.i(str1), 2));
    }
    if (!TextUtils.isEmpty(str2)) {
      paramString.append("&appid=" + str2);
    }
    localIntent.setData(Uri.parse(paramString.toString()));
    try
    {
      paramActivity.startActivity(localIntent);
      d.a().a(this.a.b().getOpenId(), this.a.b().getAppId(), "ANDROIDQQ.JOININGROUP.XX", "13", "18", "0");
      return true;
    }
    catch (Exception paramActivity)
    {
      d.a().a(this.a.b().getOpenId(), this.a.b().getAppId(), "ANDROIDQQ.JOININGROUP.XX", "13", "18", "1");
    }
    return false;
  }
  
  public JSONObject loadSession(String paramString)
  {
    return this.a.b().loadSession(paramString);
  }
  
  public int login(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "login() with activity, scope is " + paramString);
    return this.a.a(paramActivity, paramString, paramIUiListener);
  }
  
  public int login(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "login() with activity, scope is " + paramString);
    return this.a.a(paramActivity, paramString, paramIUiListener, paramBoolean);
  }
  
  public int login(Fragment paramFragment, String paramString, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "login() with fragment, scope is " + paramString);
    return this.a.a(paramFragment, paramString, paramIUiListener, "");
  }
  
  public int login(Fragment paramFragment, String paramString, IUiListener paramIUiListener, boolean paramBoolean)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "login() with fragment, scope is " + paramString);
    return this.a.a(paramFragment, paramString, paramIUiListener, "", paramBoolean);
  }
  
  public int loginServerSide(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "loginServerSide() with activity, scope = " + paramString + ",server_side");
    return this.a.a(paramActivity, paramString + ",server_side", paramIUiListener);
  }
  
  public int loginServerSide(Fragment paramFragment, String paramString, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "loginServerSide() with fragment, scope = " + paramString + ",server_side");
    return this.a.a(paramFragment, paramString + ",server_side", paramIUiListener, "");
  }
  
  public int loginWithOEM(Activity paramActivity, String paramString1, IUiListener paramIUiListener, String paramString2, String paramString3, String paramString4)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "loginWithOEM() with activity, scope = " + paramString1);
    return this.a.a(paramActivity, paramString1, paramIUiListener, paramString2, paramString3, paramString4);
  }
  
  public void logout(Context paramContext)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "logout()");
    this.a.b().setAccessToken(null, "0");
    this.a.b().setOpenId(null);
  }
  
  public void makeFriend(Activity paramActivity, Bundle paramBundle)
  {
    new SocialOperation(getQQToken()).makeFriend(paramActivity, paramBundle);
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "onActivityResult() deprecated, will do nothing");
    return false;
  }
  
  public void publishToQzone(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "publishToQzone()");
    new QzonePublish(paramActivity, this.a.b()).publishToQzone(paramActivity, paramBundle, paramIUiListener);
  }
  
  public int reAuth(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "reAuth() with activity, scope = " + paramString);
    return this.a.b(paramActivity, paramString, paramIUiListener);
  }
  
  public void releaseResource() {}
  
  public void reportDAU()
  {
    this.a.a();
  }
  
  public JSONObject request(String paramString1, Bundle paramBundle, String paramString2)
    throws IOException, JSONException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "request()");
    return HttpUtils.request(this.a.b(), e.a(), paramString1, paramBundle, paramString2);
  }
  
  public void requestAsync(String paramString1, Bundle paramBundle, String paramString2, IRequestListener paramIRequestListener, Object paramObject)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "requestAsync()");
    HttpUtils.requestAsync(this.a.b(), e.a(), paramString1, paramBundle, paramString2, paramIRequestListener);
  }
  
  public void saveSession(JSONObject paramJSONObject)
  {
    this.a.b().saveSession(paramJSONObject);
  }
  
  public void setAccessToken(String paramString1, String paramString2)
  {
    com.tencent.open.a.f.a("openSDK_LOG.Tencent", "setAccessToken(), expiresIn = " + paramString2 + "");
    this.a.a(paramString1, paramString2);
  }
  
  public void setOpenId(String paramString)
  {
    com.tencent.open.a.f.a("openSDK_LOG.Tencent", "setOpenId() --start");
    this.a.a(e.a(), paramString);
    com.tencent.open.a.f.a("openSDK_LOG.Tencent", "setOpenId() --end");
  }
  
  public void shareToQQ(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "shareToQQ()");
    new QQShare(paramActivity, this.a.b()).shareToQQ(paramActivity, paramBundle, paramIUiListener);
  }
  
  public void shareToQzone(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "shareToQzone()");
    new QzoneShare(paramActivity, this.a.b()).shareToQzone(paramActivity, paramBundle, paramIUiListener);
  }
  
  public int story(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.Tencent", "story()");
    new SocialApi(this.a.b()).story(paramActivity, paramBundle, paramIUiListener);
    return 0;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\tauth\Tencent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */