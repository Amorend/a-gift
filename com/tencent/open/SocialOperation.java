package com.tencent.open;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.k;
import java.util.Iterator;
import java.util.Set;

public class SocialOperation
  extends BaseApi
{
  public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
  public static final String GAME_FRIEND_LABEL = "friend_label";
  public static final String GAME_FRIEND_OPENID = "fopen_id";
  public static final String GAME_SIGNATURE = "signature";
  public static final String GAME_UNION_ID = "unionid";
  public static final String GAME_UNION_NAME = "union_name";
  public static final String GAME_ZONE_ID = "zoneid";
  
  public SocialOperation(QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  private void a(Activity paramActivity)
  {
    a(paramActivity, "");
  }
  
  private void a(Activity paramActivity, String paramString)
  {
    new TDialog(paramActivity, "", a(paramString), null, this.b).show();
  }
  
  public void bindQQGroup(Activity paramActivity, Bundle paramBundle)
  {
    f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
    if (paramActivity == null)
    {
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    if (paramBundle == null)
    {
      Toast.makeText(paramActivity, "Bundle参数为空", 0).show();
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, params is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    Object localObject = k.a(paramActivity);
    StringBuffer localStringBuffer = new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localStringBuffer.append("&app_name=" + Base64.encodeToString(k.i((String)localObject), 2));
    }
    localObject = paramBundle.getString("unionid");
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      Toast.makeText(paramActivity, "游戏公会ID为空", 0).show();
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union id is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    localStringBuffer.append("&unionid=" + Base64.encodeToString(k.i((String)localObject), 2));
    localObject = paramBundle.getString("union_name");
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      Toast.makeText(paramActivity, "游戏公会名称为空", 0).show();
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union name is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    localStringBuffer.append("&union_name=" + Base64.encodeToString(k.i((String)localObject), 2));
    localObject = paramBundle.getString("zoneid");
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      Toast.makeText(paramActivity, "游戏区域ID为空", 0).show();
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game zone id  is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    localStringBuffer.append("&zoneid=" + Base64.encodeToString(k.i((String)localObject), 2));
    paramBundle = paramBundle.getString("signature");
    if (TextUtils.isEmpty(paramBundle))
    {
      Toast.makeText(paramActivity, "游戏签名为空", 0).show();
      f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game signature is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      return;
    }
    localStringBuffer.append("&signature=" + Base64.encodeToString(k.i(paramBundle), 2));
    paramBundle = this.b.getOpenId();
    if (!TextUtils.isEmpty(paramBundle))
    {
      localStringBuffer.append("&openid=" + Base64.encodeToString(k.i(paramBundle), 2));
      paramBundle = b();
      localObject = paramBundle.keySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        paramBundle.putString(str, Base64.encodeToString(k.i(paramBundle.getString(str)), 2));
      }
    }
    Toast.makeText(paramActivity, "Openid为空", 0).show();
    f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
    d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
    return;
    localStringBuffer.append("&" + HttpUtils.encodeUrl(paramBundle));
    f.a("openSDK_LOG.GameAppOperation", "-->bindQQGroup, url: " + localStringBuffer.toString());
    paramBundle = new Intent("android.intent.action.VIEW");
    paramBundle.setData(Uri.parse(localStringBuffer.toString()));
    if ((a(paramBundle)) && (!k.f(paramActivity, "5.1.0"))) {
      f.c("openSDK_LOG.GameAppOperation", "-->bingQQGroup target activity found, qqver > 5.1.0");
    }
    for (;;)
    {
      try
      {
        paramActivity.startActivity(paramBundle);
        d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "0");
        f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- end");
        return;
      }
      catch (Exception paramBundle)
      {
        f.b("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", paramBundle);
        d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
        a(paramActivity);
        continue;
      }
      f.d("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDSDK.BINDGROUP.XX", "18", "18", "1");
      a(paramActivity);
    }
  }
  
  public void makeFriend(Activity paramActivity, Bundle paramBundle)
  {
    f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
    if (paramBundle == null)
    {
      f.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
      return;
    }
    Object localObject = paramBundle.getString("fopen_id");
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      f.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
      return;
    }
    String str1 = paramBundle.getString("friend_label");
    String str2 = paramBundle.getString("add_msg");
    String str3 = k.a(paramActivity);
    String str4 = this.b.getOpenId();
    String str5 = this.b.getAppId();
    f.a("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid: " + (String)localObject + " | label: " + str1 + " | message: " + str2 + " | openid: " + str4 + " | appid:" + str5);
    paramBundle = new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
    paramBundle.append("&fopen_id=" + Base64.encodeToString(k.i((String)localObject), 2));
    if (!TextUtils.isEmpty(str4)) {
      paramBundle.append("&open_id=" + Base64.encodeToString(k.i(str4), 2));
    }
    if (!TextUtils.isEmpty(str5)) {
      paramBundle.append("&app_id=" + str5);
    }
    if (!TextUtils.isEmpty(str1)) {
      paramBundle.append("&friend_label=" + Base64.encodeToString(k.i(str1), 2));
    }
    if (!TextUtils.isEmpty(str2)) {
      paramBundle.append("&add_msg=" + Base64.encodeToString(k.i(str2), 2));
    }
    if (!TextUtils.isEmpty(str3)) {
      paramBundle.append("&app_name=" + Base64.encodeToString(k.i(str3), 2));
    }
    f.a("openSDK_LOG.GameAppOperation", "-->make friend, url: " + paramBundle.toString());
    localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setData(Uri.parse(paramBundle.toString()));
    if ((a((Intent)localObject)) && (!k.f(paramActivity, "5.1.0"))) {
      f.c("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
    }
    for (;;)
    {
      try
      {
        paramActivity.startActivity((Intent)localObject);
        d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "0");
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
        return;
      }
      catch (Exception paramBundle)
      {
        f.b("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", paramBundle);
        a(paramActivity);
        d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
        continue;
      }
      f.d("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
      a(paramActivity);
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.MAKEAFRIEND.XX", "14", "18", "1");
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\SocialOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */