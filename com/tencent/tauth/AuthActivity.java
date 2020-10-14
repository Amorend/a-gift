package com.tencent.tauth;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import org.json.JSONObject;

public class AuthActivity
  extends Activity
{
  public static final String ACTION_KEY = "action";
  public static final String ACTION_SHARE_PRIZE = "sharePrize";
  private static int a = 0;
  
  private void a(Uri paramUri)
  {
    f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
    if ((paramUri == null) || (paramUri.toString() == null) || (paramUri.toString().equals("")))
    {
      f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
      finish();
      return;
    }
    paramUri = paramUri.toString();
    paramUri = k.a(paramUri.substring(paramUri.indexOf("#") + 1));
    if (paramUri == null)
    {
      f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
      finish();
      return;
    }
    Object localObject2 = paramUri.getString("action");
    f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, action: " + (String)localObject2);
    if (localObject2 == null)
    {
      finish();
      return;
    }
    Object localObject1;
    if ((((String)localObject2).equals("shareToQQ")) || (((String)localObject2).equals("shareToQzone")) || (((String)localObject2).equals("sendToMyComputer")) || (((String)localObject2).equals("shareToTroopBar")))
    {
      if ((((String)localObject2).equals("shareToQzone")) && (h.a(this, "com.tencent.mobileqq") != null) && (h.c(this, "5.2.0") < 0))
      {
        a += 1;
        if (a == 2)
        {
          a = 0;
          finish();
          return;
        }
      }
      f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
      localObject1 = new Intent(this, AssistActivity.class);
      ((Intent)localObject1).putExtras(paramUri);
      ((Intent)localObject1).setFlags(603979776);
      startActivity((Intent)localObject1);
      finish();
      return;
    }
    if (((String)localObject2).equals("addToQQFavorites"))
    {
      localObject1 = getIntent();
      ((Intent)localObject1).putExtras(paramUri);
      ((Intent)localObject1).putExtra("key_action", "action_share");
      paramUri = UIListenerManager.getInstance().getListnerWithAction((String)localObject2);
      if (paramUri != null) {
        UIListenerManager.getInstance().handleDataToListener((Intent)localObject1, (IUiListener)paramUri);
      }
      finish();
      return;
    }
    if (((String)localObject2).equals("sharePrize"))
    {
      localObject2 = getPackageManager().getLaunchIntentForPackage(getPackageName());
      localObject1 = paramUri.getString("response");
      paramUri = "";
      try
      {
        localObject1 = k.d((String)localObject1).getString("activityid");
        paramUri = (Uri)localObject1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          f.b("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", localException);
        }
      }
      if (!TextUtils.isEmpty(paramUri))
      {
        ((Intent)localObject2).putExtra("sharePrize", true);
        localObject1 = new Bundle();
        ((Bundle)localObject1).putString("activityid", paramUri);
        ((Intent)localObject2).putExtras((Bundle)localObject1);
      }
      startActivity((Intent)localObject2);
      finish();
      return;
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getIntent() == null)
    {
      f.d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
      finish();
      return;
    }
    paramBundle = null;
    try
    {
      Uri localUri = getIntent().getData();
      paramBundle = localUri;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        f.e("openSDK_LOG.AuthActivity", "-->onCreate, getIntent().getData() has exception! " + localException.getMessage());
      }
    }
    f.a("openSDK_LOG.AuthActivity", "-->onCreate, uri: " + paramBundle);
    a(paramBundle);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\tauth\AuthActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */