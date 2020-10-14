package com.tencent.connect.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseApi
{
  public static String businessId = null;
  public static String installChannel;
  public static boolean isOEM = false;
  public static String registerChannel = null;
  protected c a;
  protected QQToken b;
  
  static
  {
    installChannel = null;
  }
  
  public BaseApi(QQToken paramQQToken)
  {
    this(null, paramQQToken);
  }
  
  public BaseApi(c paramc, QQToken paramQQToken)
  {
    this.a = paramc;
    this.b = paramQQToken;
  }
  
  private Intent a(Activity paramActivity, Intent paramIntent)
  {
    paramActivity = new Intent(paramActivity.getApplicationContext(), AssistActivity.class);
    paramActivity.putExtra("is_login", true);
    paramActivity.putExtra("openSDK_LOG.AssistActivity.ExtraIntent", paramIntent);
    return paramActivity;
  }
  
  protected Bundle a()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("format", "json");
    localBundle.putString("status_os", Build.VERSION.RELEASE);
    localBundle.putString("status_machine", Build.MODEL);
    localBundle.putString("status_version", Build.VERSION.SDK);
    localBundle.putString("sdkv", "3.3.0.lite");
    localBundle.putString("sdkp", "a");
    if ((this.b != null) && (this.b.isSessionValid()))
    {
      localBundle.putString("access_token", this.b.getAccessToken());
      localBundle.putString("oauth_consumer_key", this.b.getAppId());
      localBundle.putString("openid", this.b.getOpenId());
      localBundle.putString("appid_for_getting_config", this.b.getAppId());
    }
    SharedPreferences localSharedPreferences = e.a().getSharedPreferences("pfStore", 0);
    if (isOEM) {
      localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
    }
    for (;;)
    {
      return localBundle;
      localBundle.putString("pf", localSharedPreferences.getString("pf", "openmobile_android"));
    }
  }
  
  protected String a(String paramString)
  {
    Bundle localBundle = a();
    StringBuilder localStringBuilder = new StringBuilder();
    if (!TextUtils.isEmpty(paramString)) {
      localBundle.putString("need_version", paramString);
    }
    localStringBuilder.append("http://openmobile.qq.com/oauth2.0/m_jump_by_version?");
    localStringBuilder.append(HttpUtils.encodeUrl(localBundle));
    return localStringBuilder.toString();
  }
  
  protected void a(Activity paramActivity, int paramInt, Intent paramIntent, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramActivity.getApplicationContext(), AssistActivity.class);
    if (paramBoolean) {
      localIntent.putExtra("is_qq_mobile_share", true);
    }
    localIntent.putExtra("openSDK_LOG.AssistActivity.ExtraIntent", paramIntent);
    paramActivity.startActivityForResult(localIntent, paramInt);
  }
  
  protected void a(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    paramIntent.putExtra("key_request_code", paramInt);
    paramActivity.startActivityForResult(a(paramActivity, paramIntent), paramInt);
  }
  
  protected void a(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
    paramIUiListener = new StringBuilder();
    paramIUiListener.append("http://qzs.qq.com/open/mobile/login/qzsjump.html?");
    paramIUiListener.append(HttpUtils.encodeUrl(paramBundle));
    new TDialog(paramActivity, "", paramIUiListener.toString(), null, this.b).show();
  }
  
  protected void a(Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    paramIntent.putExtra("key_request_code", paramInt);
    paramFragment.startActivityForResult(a(paramFragment.getActivity(), paramIntent), paramInt);
  }
  
  protected boolean a(Intent paramIntent)
  {
    if (paramIntent != null) {
      return h.a(e.a(), paramIntent);
    }
    return false;
  }
  
  protected Intent b(String paramString)
  {
    Intent localIntent = new Intent();
    if (k.d(e.a()))
    {
      localIntent.setClassName("com.tencent.minihd.qq", paramString);
      if (h.a(e.a(), localIntent)) {
        return localIntent;
      }
    }
    localIntent.setClassName("com.tencent.mobileqq", paramString);
    if (h.a(e.a(), localIntent)) {
      return localIntent;
    }
    localIntent.setClassName("com.tencent.tim", paramString);
    if (h.a(e.a(), localIntent)) {
      return localIntent;
    }
    return null;
  }
  
  protected Bundle b()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("appid", this.b.getAppId());
    if (this.b.isSessionValid())
    {
      localBundle.putString("keystr", this.b.getAccessToken());
      localBundle.putString("keytype", "0x80");
    }
    Object localObject = this.b.getOpenId();
    if (localObject != null) {
      localBundle.putString("hopenid", (String)localObject);
    }
    localBundle.putString("platform", "androidqz");
    localObject = e.a().getSharedPreferences("pfStore", 0);
    if (isOEM) {
      localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
    }
    for (;;)
    {
      localBundle.putString("sdkv", "3.3.0.lite");
      localBundle.putString("sdkp", "a");
      return localBundle;
      localBundle.putString("pf", ((SharedPreferences)localObject).getString("pf", "openmobile_android"));
      localBundle.putString("pf", "openmobile_android");
    }
  }
  
  protected Intent c(String paramString)
  {
    Intent localIntent = new Intent();
    paramString = b(paramString);
    if (paramString == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      if (paramString.getComponent() != null)
      {
        localIntent.setClassName(paramString.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
        paramString = localIntent;
      }
      else
      {
        paramString = null;
      }
    }
  }
  
  public void releaseResource() {}
  
  public class TempRequestListener
    implements IRequestListener
  {
    private final IUiListener b;
    private final Handler c;
    
    public TempRequestListener(IUiListener paramIUiListener)
    {
      this.b = paramIUiListener;
      this.c = new Handler(e.a().getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          if (paramAnonymousMessage.what == 0)
          {
            BaseApi.TempRequestListener.a(BaseApi.TempRequestListener.this).onComplete(paramAnonymousMessage.obj);
            return;
          }
          BaseApi.TempRequestListener.a(BaseApi.TempRequestListener.this).onError(new UiError(paramAnonymousMessage.what, (String)paramAnonymousMessage.obj, null));
        }
      };
    }
    
    public void onComplete(JSONObject paramJSONObject)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramJSONObject;
      localMessage.what = 0;
      this.c.sendMessage(localMessage);
    }
    
    public void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramConnectTimeoutException.getMessage();
      localMessage.what = -7;
      this.c.sendMessage(localMessage);
    }
    
    public void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramHttpStatusException.getMessage();
      localMessage.what = -9;
      this.c.sendMessage(localMessage);
    }
    
    public void onIOException(IOException paramIOException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramIOException.getMessage();
      localMessage.what = -2;
      this.c.sendMessage(localMessage);
    }
    
    public void onJSONException(JSONException paramJSONException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramJSONException.getMessage();
      localMessage.what = -4;
      this.c.sendMessage(localMessage);
    }
    
    public void onMalformedURLException(MalformedURLException paramMalformedURLException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramMalformedURLException.getMessage();
      localMessage.what = -3;
      this.c.sendMessage(localMessage);
    }
    
    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramNetworkUnavailableException.getMessage();
      localMessage.what = -10;
      this.c.sendMessage(localMessage);
    }
    
    public void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramSocketTimeoutException.getMessage();
      localMessage.what = -8;
      this.c.sendMessage(localMessage);
    }
    
    public void onUnknowException(Exception paramException)
    {
      Message localMessage = this.c.obtainMessage();
      localMessage.obj = paramException.getMessage();
      localMessage.what = -6;
      this.c.sendMessage(localMessage);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\common\BaseApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */