package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.c.b;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.g;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialApiIml
  extends BaseApi
{
  private Activity c;
  
  public SocialApiIml(QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  public SocialApiIml(com.tencent.connect.auth.c paramc, QQToken paramQQToken)
  {
    super(paramc, paramQQToken);
  }
  
  private void a(Activity paramActivity, Intent paramIntent, String paramString, Bundle paramBundle, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithAgent action = " + paramString);
    paramIntent.putExtra("key_action", paramString);
    paramIntent.putExtra("key_params", paramBundle);
    UIListenerManager.getInstance().setListenerWithRequestcode(11105, paramIUiListener);
    a(paramActivity, paramIntent, 11105);
  }
  
  private void a(Activity paramActivity, Intent paramIntent, String paramString1, Bundle paramBundle, String paramString2, IUiListener paramIUiListener, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("-->handleIntent action = ").append(paramString1).append(", activityIntent = null ? ");
    if (paramIntent == null) {}
    for (boolean bool = true;; bool = false)
    {
      com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", bool);
      if (paramIntent == null) {
        break;
      }
      a(paramActivity, paramIntent, paramString1, paramBundle, paramIUiListener);
      return;
    }
    paramIntent = com.tencent.open.utils.f.a(e.a(), this.b.getAppId());
    if ((paramBoolean) || (paramIntent.b("C_LoginH5"))) {}
    for (int i = 1; i != 0; i = 0)
    {
      a(paramActivity, paramString1, paramBundle, paramString2, paramIUiListener);
      return;
    }
    a(paramActivity, paramBundle, paramIUiListener);
  }
  
  private void a(Activity paramActivity, String paramString, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.c = paramActivity;
    Intent localIntent2 = c("com.tencent.open.agent.SocialFriendChooser");
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null)
    {
      com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
      localIntent1 = c("com.tencent.open.agent.RequestFreegiftActivity");
    }
    paramBundle.putAll(b());
    if ("action_ask".equals(paramString)) {
      paramBundle.putString("type", "request");
    }
    for (;;)
    {
      a(paramActivity, localIntent1, paramString, paramBundle, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/request/sdk_request.html?"), paramIUiListener, false);
      return;
      if ("action_gift".equals(paramString)) {
        paramBundle.putString("type", "freegift");
      }
    }
  }
  
  private void a(Activity paramActivity, String paramString1, Bundle paramBundle, String paramString2, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5 action = " + paramString1);
    Intent localIntent = b("com.tencent.open.agent.AgentActivity");
    paramString1 = new a(paramActivity, paramIUiListener, paramString1, paramString2, paramBundle);
    paramBundle = b("com.tencent.open.agent.EncryTokenActivity");
    if ((paramBundle != null) && (localIntent != null) && (localIntent.getComponent() != null) && (paramBundle.getComponent() != null) && (localIntent.getComponent().getPackageName().equals(paramBundle.getComponent().getPackageName())))
    {
      paramBundle.putExtra("oauth_consumer_key", this.b.getAppId());
      paramBundle.putExtra("openid", this.b.getOpenId());
      paramBundle.putExtra("access_token", this.b.getAccessToken());
      paramBundle.putExtra("key_action", "action_check_token");
      if (a(paramBundle))
      {
        com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
        UIListenerManager.getInstance().setListenerWithRequestcode(11106, paramString1);
        a(paramActivity, paramBundle, 11106);
      }
      return;
    }
    com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
    paramBundle = k.f("tencent&sdk&qazxc***14969%%" + this.b.getAccessToken() + this.b.getAppId() + this.b.getOpenId() + "qzone3.4");
    paramActivity = new JSONObject();
    try
    {
      paramActivity.put("encry_token", paramBundle);
      paramString1.onComplete(paramActivity);
      return;
    }
    catch (JSONException paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
      }
    }
  }
  
  private void a(Context paramContext, String paramString1, Bundle paramBundle, String paramString2, IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.a("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
    CookieSyncManager.createInstance(paramContext);
    paramBundle.putString("oauth_consumer_key", this.b.getAppId());
    if (this.b.isSessionValid()) {
      paramBundle.putString("access_token", this.b.getAccessToken());
    }
    paramContext = this.b.getOpenId();
    if (paramContext != null) {
      paramBundle.putString("openid", paramContext);
    }
    try
    {
      paramBundle.putString("pf", e.a().getSharedPreferences("pfStore", 0).getString("pf", "openmobile_android"));
      paramContext = new StringBuilder();
      paramContext.append(paramString2);
      paramContext.append(HttpUtils.encodeUrl(paramBundle));
      paramContext = paramContext.toString();
      com.tencent.open.a.f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
      if (("action_challenge".equals(paramString1)) || ("action_brag".equals(paramString1)))
      {
        com.tencent.open.a.f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
        new c(this.c, paramString1, paramContext, paramIUiListener, this.b).show();
        return;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramBundle.putString("pf", "openmobile_android");
      }
      new TDialog(this.c, paramString1, paramContext, paramIUiListener, this.b).show();
    }
  }
  
  public void ask(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    a(paramActivity, "action_ask", paramBundle, paramIUiListener);
  }
  
  protected Intent b(String paramString)
  {
    Intent localIntent1 = new Intent();
    localIntent1.setClassName("com.qzone", paramString);
    Intent localIntent2 = new Intent();
    localIntent2.setClassName("com.tencent.mobileqq", paramString);
    Intent localIntent3 = new Intent();
    localIntent3.setClassName("com.tencent.minihd.qq", paramString);
    if ((k.d(e.a())) && (h.a(e.a(), localIntent3))) {
      return localIntent3;
    }
    if ((h.a(e.a(), localIntent2)) && (h.c(e.a(), "4.7") >= 0)) {
      return localIntent2;
    }
    if ((h.a(e.a(), localIntent1)) && (h.a(h.a(e.a(), "com.qzone"), "4.2") >= 0))
    {
      if (h.a(e.a(), localIntent1.getComponent().getPackageName(), "ec96e9ac1149251acbb1b0c5777cae95")) {}
      for (paramString = localIntent1;; paramString = null) {
        return paramString;
      }
    }
    return null;
  }
  
  public void gift(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    a(paramActivity, "action_gift", paramBundle, paramIUiListener);
  }
  
  public void invite(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.c = paramActivity;
    Intent localIntent2 = c("com.tencent.open.agent.SocialFriendChooser");
    Intent localIntent1 = localIntent2;
    if (localIntent2 == null)
    {
      com.tencent.open.a.f.c("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
      localIntent1 = c("com.tencent.open.agent.AppInvitationActivity");
    }
    paramBundle.putAll(b());
    a(paramActivity, localIntent1, "action_invite", paramBundle, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?"), paramIUiListener, false);
  }
  
  public void story(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.c = paramActivity;
    Intent localIntent = c("com.tencent.open.agent.SendStoryActivity");
    paramBundle.putAll(b());
    a(paramActivity, localIntent, "action_story", paramBundle, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?"), paramIUiListener, false);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public void writeEncryToken(Context paramContext)
  {
    Object localObject2 = this.b.getAccessToken();
    String str1 = this.b.getAppId();
    String str2 = this.b.getOpenId();
    b localb = null;
    Object localObject1 = localb;
    if (localObject2 != null)
    {
      localObject1 = localb;
      if (((String)localObject2).length() > 0)
      {
        localObject1 = localb;
        if (str1 != null)
        {
          localObject1 = localb;
          if (str1.length() > 0)
          {
            localObject1 = localb;
            if (str2 != null)
            {
              localObject1 = localb;
              if (str2.length() > 0) {
                localObject1 = k.f("tencent&sdk&qazxc***14969%%" + (String)localObject2 + str1 + str2 + "qzone3.4");
              }
            }
          }
        }
      }
    }
    localb = new b(paramContext);
    localObject2 = localb.getSettings();
    ((WebSettings)localObject2).setDomStorageEnabled(true);
    ((WebSettings)localObject2).setJavaScriptEnabled(true);
    ((WebSettings)localObject2).setDatabaseEnabled(true);
    localObject1 = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.b.getOpenId() + "_" + this.b.getAppId() + "\"]=\"" + (String)localObject1 + "\";</script></head><body></body></html>";
    paramContext = g.a().a(paramContext, "http://qzs.qq.com");
    localb.loadDataWithBaseURL(paramContext, (String)localObject1, "text/html", "utf-8", paramContext);
  }
  
  private class a
    implements IUiListener
  {
    private IUiListener b;
    private String c;
    private String d;
    private Bundle e;
    private Activity f;
    
    a(Activity paramActivity, IUiListener paramIUiListener, String paramString1, String paramString2, Bundle paramBundle)
    {
      this.b = paramIUiListener;
      this.c = paramString1;
      this.d = paramString2;
      this.e = paramBundle;
    }
    
    public void onCancel()
    {
      this.b.onCancel();
    }
    
    public void onComplete(Object paramObject)
    {
      Object localObject = (JSONObject)paramObject;
      paramObject = null;
      try
      {
        localObject = ((JSONObject)localObject).getString("encry_token");
        paramObject = localObject;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localJSONException.printStackTrace();
          com.tencent.open.a.f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", localJSONException);
        }
      }
      this.e.putString("encrytoken", (String)paramObject);
      SocialApiIml.a(SocialApiIml.this, SocialApiIml.a(SocialApiIml.this), this.c, this.e, this.d, this.b);
      if (TextUtils.isEmpty((CharSequence)paramObject))
      {
        com.tencent.open.a.f.b("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
        SocialApiIml.this.writeEncryToken(this.f);
      }
    }
    
    public void onError(UiError paramUiError)
    {
      com.tencent.open.a.f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncryptTokenListener() onError" + paramUiError.errorMessage);
      this.b.onError(paramUiError);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\SocialApiIml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */