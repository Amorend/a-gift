package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

public class AssistActivity
  extends Activity
{
  public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
  protected boolean a = false;
  protected Handler b = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        return;
      } while (AssistActivity.this.isFinishing());
      f.d("openSDK_LOG.AssistActivity", "-->finish by timeout");
      AssistActivity.this.finish();
    }
  };
  private boolean c = false;
  private String d;
  
  private void a(Bundle paramBundle)
  {
    String str2 = paramBundle.getString("viaShareType");
    Object localObject = paramBundle.getString("callbackAction");
    String str4 = paramBundle.getString("url");
    String str3 = paramBundle.getString("openId");
    String str5 = paramBundle.getString("appId");
    paramBundle = "";
    String str1 = "";
    if ("shareToQQ".equals(localObject))
    {
      paramBundle = "ANDROIDQQ.SHARETOQQ.XX";
      str1 = "10";
      if (k.a(this, str4)) {
        break label160;
      }
      localObject = UIListenerManager.getInstance().getListnerWithAction((String)localObject);
      if (localObject != null) {
        ((IUiListener)localObject).onError(new UiError(-6, "打开浏览器失败!", null));
      }
      d.a().a(str3, str5, paramBundle, str1, "3", "1", str2, "0", "2", "0");
      finish();
    }
    for (;;)
    {
      getIntent().removeExtra("shareH5");
      return;
      if (!"shareToQzone".equals(localObject)) {
        break;
      }
      paramBundle = "ANDROIDQQ.SHARETOQZ.XX";
      str1 = "11";
      break;
      label160:
      d.a().a(str3, str5, paramBundle, str1, "3", "0", str2, "0", "2", "0");
    }
  }
  
  public static Intent getAssistActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, AssistActivity.class);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("--onActivityResult--requestCode: ").append(paramInt1).append(" | resultCode: ").append(paramInt2).append("data = null ? ");
    if (paramIntent == null) {}
    for (boolean bool = true;; bool = false)
    {
      f.c("openSDK_LOG.AssistActivity", bool);
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      if (paramInt1 != 0) {
        break;
      }
      return;
    }
    if (paramIntent != null) {
      paramIntent.putExtra("key_action", "action_login");
    }
    setResultData(paramInt1, paramIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    setRequestedOrientation(3);
    f.b("openSDK_LOG.AssistActivity", "--onCreate--");
    if (getIntent() == null)
    {
      f.e("openSDK_LOG.AssistActivity", "-->onCreate--getIntent() returns null");
      finish();
    }
    Intent localIntent = (Intent)getIntent().getParcelableExtra("openSDK_LOG.AssistActivity.ExtraIntent");
    int i;
    if (localIntent == null)
    {
      i = 0;
      if (localIntent != null) {
        break label170;
      }
    }
    label170:
    for (Object localObject = "";; localObject = localIntent.getStringExtra("appid"))
    {
      this.d = ((String)localObject);
      localObject = getIntent().getBundleExtra("h5_share_data");
      if (paramBundle != null)
      {
        this.c = paramBundle.getBoolean("RESTART_FLAG");
        this.a = paramBundle.getBoolean("RESUME_FLAG", false);
      }
      if (this.c) {
        break label206;
      }
      if (localObject != null) {
        break label193;
      }
      if (localIntent == null) {
        break label181;
      }
      f.c("openSDK_LOG.AssistActivity", "--onCreate--activityIntent not null, will start activity, reqcode = " + i);
      startActivityForResult(localIntent, i);
      return;
      i = localIntent.getIntExtra("key_request_code", 0);
      break;
    }
    label181:
    f.e("openSDK_LOG.AssistActivity", "--onCreate--activityIntent is null");
    finish();
    return;
    label193:
    f.d("openSDK_LOG.AssistActivity", "--onCreate--h5 bundle not null, will open browser");
    a((Bundle)localObject);
    return;
    label206:
    f.b("openSDK_LOG.AssistActivity", "is restart");
  }
  
  protected void onDestroy()
  {
    f.b("openSDK_LOG.AssistActivity", "-->onDestroy");
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    f.c("openSDK_LOG.AssistActivity", "--onNewIntent");
    super.onNewIntent(paramIntent);
    paramIntent.putExtra("key_action", "action_share");
    setResult(-1, paramIntent);
    if (!isFinishing())
    {
      f.c("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
      finish();
    }
  }
  
  protected void onPause()
  {
    f.b("openSDK_LOG.AssistActivity", "-->onPause");
    this.b.removeMessages(0);
    super.onPause();
  }
  
  protected void onResume()
  {
    f.b("openSDK_LOG.AssistActivity", "-->onResume");
    super.onResume();
    Object localObject = getIntent();
    if (((Intent)localObject).getBooleanExtra("is_login", false)) {
      return;
    }
    if ((!((Intent)localObject).getBooleanExtra("is_qq_mobile_share", false)) && (this.c) && (!isFinishing())) {
      finish();
    }
    if (this.a)
    {
      localObject = this.b.obtainMessage(0);
      this.b.sendMessage((Message)localObject);
      return;
    }
    this.a = true;
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    f.b("openSDK_LOG.AssistActivity", "--onSaveInstanceState--");
    paramBundle.putBoolean("RESTART_FLAG", true);
    paramBundle.putBoolean("RESUME_FLAG", this.a);
    super.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    f.b("openSDK_LOG.AssistActivity", "-->onStart");
    super.onStart();
  }
  
  protected void onStop()
  {
    f.b("openSDK_LOG.AssistActivity", "-->onStop");
    super.onStop();
  }
  
  public void setResultData(int paramInt, Intent paramIntent)
  {
    if (paramIntent == null)
    {
      f.d("openSDK_LOG.AssistActivity", "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
      setResult(0);
      if (paramInt == 11101) {
        d.a().a("", this.d, "2", "1", "7", "2");
      }
      return;
    }
    try
    {
      String str = paramIntent.getStringExtra("key_response");
      f.b("openSDK_LOG.AssistActivity", "--setResultDataForLogin-- " + str);
      if (!TextUtils.isEmpty(str))
      {
        Object localObject = new JSONObject(str);
        str = ((JSONObject)localObject).optString("openid");
        localObject = ((JSONObject)localObject).optString("access_token");
        if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject)))
        {
          f.c("openSDK_LOG.AssistActivity", "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
          setResult(-1, paramIntent);
          d.a().a(str, this.d, "2", "1", "7", "0");
        }
        else
        {
          f.d("openSDK_LOG.AssistActivity", "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
          setResult(0, paramIntent);
          d.a().a("", this.d, "2", "1", "7", "1");
        }
      }
    }
    catch (Exception paramIntent)
    {
      f.e("openSDK_LOG.AssistActivity", "--setResultData--parse response failed");
      paramIntent.printStackTrace();
    }
    f.d("openSDK_LOG.AssistActivity", "--setResultData--response is empty, setResult ACTIVITY_OK");
    setResult(-1, paramIntent);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\common\AssistActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */