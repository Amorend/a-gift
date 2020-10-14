package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.b;
import com.tencent.open.utils.c;
import com.tencent.open.utils.e;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.ArrayList;

public class QQShare
  extends BaseApi
{
  public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 512;
  public static final int QQ_SHARE_TITLE_MAX_LENGTH = 128;
  public static final String SHARE_TO_QQ_APP_NAME = "appName";
  public static final String SHARE_TO_QQ_ARK_INFO = "share_to_qq_ark_info";
  public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
  public static final String SHARE_TO_QQ_EXT_INT = "cflag";
  public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
  public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
  public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
  public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
  public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
  public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
  public static final String SHARE_TO_QQ_SITE = "site";
  public static final String SHARE_TO_QQ_SUMMARY = "summary";
  public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
  public static final String SHARE_TO_QQ_TITLE = "title";
  public static final int SHARE_TO_QQ_TYPE_APP = 6;
  public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
  public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
  public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
  public String mViaShareQQType = "";
  
  public QQShare(Context paramContext, QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  private void b(final Activity paramActivity, final Bundle paramBundle, final IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
    String str1 = paramBundle.getString("imageUrl");
    final String str2 = paramBundle.getString("title");
    final String str3 = paramBundle.getString("summary");
    f.a("openSDK_LOG.QQShare", "shareToMobileQQ -- imageUrl: " + str1);
    if (!TextUtils.isEmpty(str1)) {
      if (k.g(str1))
      {
        if ((TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str3)))
        {
          if (paramIUiListener != null)
          {
            paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
            f.e("openSDK_LOG.QQShare", "分享图片失败，检测不到SD卡!");
          }
          d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "分享图片失败，检测不到SD卡!");
          return;
        }
        if (!k.f(paramActivity, "4.3.0")) {
          c(paramActivity, paramBundle, paramIUiListener);
        }
      }
    }
    for (;;)
    {
      f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
      return;
      new b(paramActivity).a(str1, new c()
      {
        public void a(int paramAnonymousInt, String paramAnonymousString)
        {
          if (paramAnonymousInt == 0) {
            paramBundle.putString("imageLocalUrl", paramAnonymousString);
          }
          while ((!TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3)))
          {
            QQShare.a(QQShare.this, paramActivity, paramBundle, paramIUiListener);
            return;
          }
          if (paramIUiListener != null)
          {
            paramIUiListener.onError(new UiError(-6, "获取分享图片失败!", null));
            f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
          }
          d.a().a(1, "SHARE_CHECK_SDK", "1000", QQShare.a(QQShare.this).getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "获取分享图片失败!");
        }
        
        public void a(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList) {}
      });
      continue;
      paramBundle.putString("imageUrl", null);
      if (k.f(paramActivity, "4.3.0"))
      {
        f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
        c(paramActivity, paramBundle, paramIUiListener);
      }
      else
      {
        f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0 ");
        a.a(paramActivity, str1, new c()
        {
          public void a(int paramAnonymousInt, String paramAnonymousString)
          {
            if (paramAnonymousInt == 0) {
              paramBundle.putString("imageLocalUrl", paramAnonymousString);
            }
            while ((!TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3)))
            {
              QQShare.a(QQShare.this, paramActivity, paramBundle, paramIUiListener);
              return;
            }
            if (paramIUiListener != null)
            {
              paramIUiListener.onError(new UiError(-6, "获取分享图片失败!", null));
              f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
            }
            d.a().a(1, "SHARE_CHECK_SDK", "1000", QQShare.b(QQShare.this).getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "获取分享图片失败!");
          }
          
          public void a(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList) {}
        });
        continue;
        c(paramActivity, paramBundle, paramIUiListener);
      }
    }
  }
  
  private void c(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQShare", "doShareToQQ() -- start");
    StringBuffer localStringBuffer = new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
    String str6 = paramBundle.getString("imageUrl");
    String str7 = paramBundle.getString("title");
    String str8 = paramBundle.getString("summary");
    String str9 = paramBundle.getString("targetUrl");
    String str3 = paramBundle.getString("audio_url");
    int i = paramBundle.getInt("req_type", 1);
    String str4 = paramBundle.getString("share_to_qq_ark_info");
    int j = paramBundle.getInt("cflag", 0);
    String str5 = paramBundle.getString("share_qq_ext_str");
    String str2 = k.a(paramActivity);
    String str1 = str2;
    if (str2 == null) {
      str1 = paramBundle.getString("appName");
    }
    paramBundle = paramBundle.getString("imageLocalUrl");
    String str10 = this.b.getAppId();
    str2 = this.b.getOpenId();
    f.a("openSDK_LOG.QQShare", "doShareToQQ -- openid: " + str2);
    if (!TextUtils.isEmpty(str6)) {
      localStringBuffer.append("&image_url=" + Base64.encodeToString(k.i(str6), 2));
    }
    if (!TextUtils.isEmpty(paramBundle)) {
      localStringBuffer.append("&file_data=" + Base64.encodeToString(k.i(paramBundle), 2));
    }
    if (!TextUtils.isEmpty(str7)) {
      localStringBuffer.append("&title=" + Base64.encodeToString(k.i(str7), 2));
    }
    if (!TextUtils.isEmpty(str8)) {
      localStringBuffer.append("&description=" + Base64.encodeToString(k.i(str8), 2));
    }
    if (!TextUtils.isEmpty(str10)) {
      localStringBuffer.append("&share_id=" + str10);
    }
    if (!TextUtils.isEmpty(str9)) {
      localStringBuffer.append("&url=" + Base64.encodeToString(k.i(str9), 2));
    }
    if (!TextUtils.isEmpty(str1))
    {
      paramBundle = str1;
      if (str1.length() > 20) {
        paramBundle = str1.substring(0, 20) + "...";
      }
      localStringBuffer.append("&app_name=" + Base64.encodeToString(k.i(paramBundle), 2));
    }
    if (!TextUtils.isEmpty(str2)) {
      localStringBuffer.append("&open_id=" + Base64.encodeToString(k.i(str2), 2));
    }
    if (!TextUtils.isEmpty(str3)) {
      localStringBuffer.append("&audioUrl=" + Base64.encodeToString(k.i(str3), 2));
    }
    localStringBuffer.append("&req_type=" + Base64.encodeToString(k.i(String.valueOf(i)), 2));
    if (!TextUtils.isEmpty(str4)) {
      localStringBuffer.append("&share_to_qq_ark_info=" + Base64.encodeToString(k.i(str4), 2));
    }
    if (!TextUtils.isEmpty(str5)) {
      localStringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(k.i(str5), 2));
    }
    localStringBuffer.append("&cflag=" + Base64.encodeToString(k.i(String.valueOf(j)), 2));
    f.a("openSDK_LOG.QQShare", "doShareToQQ -- url: " + localStringBuffer.toString());
    com.tencent.connect.a.a.a(e.a(), this.b, "requireApi", new String[] { "shareToNativeQQ" });
    paramBundle = new Intent("android.intent.action.VIEW");
    paramBundle.setData(Uri.parse(localStringBuffer.toString()));
    paramBundle.putExtra("pkg_name", paramActivity.getPackageName());
    if (k.f(paramActivity, "4.6.0"))
    {
      f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
      if (a(paramBundle))
      {
        UIListenerManager.getInstance().setListenerWithRequestcode(11103, paramIUiListener);
        a(paramActivity, paramBundle, 11103);
      }
      paramActivity = "10";
      if (j == 1) {
        paramActivity = "11";
      }
      if (!a(paramBundle)) {
        break label1017;
      }
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQQ.XX", paramActivity, "3", "0", this.mViaShareQQType, "0", "1", "0");
      d.a().a(0, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
    }
    for (;;)
    {
      f.c("openSDK_LOG.QQShare", "doShareToQQ() --end");
      return;
      f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
      if (UIListenerManager.getInstance().setListnerWithAction("shareToQQ", paramIUiListener) != null) {
        f.c("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
      }
      if (!a(paramBundle)) {
        break;
      }
      a(paramActivity, 10103, paramBundle, true);
      break;
      label1017:
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQQ.XX", paramActivity, "3", "1", this.mViaShareQQType, "0", "1", "0");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
    }
  }
  
  public void releaseResource() {}
  
  public void shareToQQ(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QQShare", "shareToQQ() -- start.");
    String str2 = paramBundle.getString("imageUrl");
    String str3 = paramBundle.getString("title");
    String str4 = paramBundle.getString("summary");
    String str1 = paramBundle.getString("targetUrl");
    String str5 = paramBundle.getString("imageLocalUrl");
    int i = paramBundle.getInt("req_type", 1);
    f.c("openSDK_LOG.QQShare", "shareToQQ -- type: " + i);
    switch (i)
    {
    }
    while (i == 6) {
      if (k.f(paramActivity, "5.0.0"))
      {
        paramIUiListener.onError(new UiError(-15, "手Q版本过低，应用分享只支持手Q5.0及其以上版本", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ, app share is not support below qq5.0.");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, app share is not support below qq5.0.");
        return;
        this.mViaShareQQType = "1";
        continue;
        this.mViaShareQQType = "3";
        continue;
        this.mViaShareQQType = "2";
        continue;
        this.mViaShareQQType = "4";
      }
      else
      {
        str1 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", new Object[] { this.b.getAppId(), "mqq" });
        paramBundle.putString("targetUrl", str1);
      }
    }
    if ((!k.b()) && (k.f(paramActivity, "4.5.0")))
    {
      paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
      f.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
      return;
    }
    if (i == 5)
    {
      if (k.f(paramActivity, "4.3.0"))
      {
        paramIUiListener.onError(new UiError(-6, "低版本手Q不支持该项功能!", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
        return;
      }
      if (!k.h(str5))
      {
        paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ -- error: 非法的图片地址!");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "非法的图片地址!");
        return;
      }
    }
    if (i != 5)
    {
      if ((TextUtils.isEmpty(str1)) || ((!str1.startsWith("http://")) && (!str1.startsWith("https://"))))
      {
        paramIUiListener.onError(new UiError(-6, "传入参数有误!", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
        return;
      }
      if (TextUtils.isEmpty(str3))
      {
        paramIUiListener.onError(new UiError(-6, "title不能为空!", null));
        f.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
        return;
      }
    }
    if ((!TextUtils.isEmpty(str2)) && (!str2.startsWith("http://")) && (!str2.startsWith("https://")) && (!new File(str2).exists()))
    {
      paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
      f.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
      return;
    }
    if ((!TextUtils.isEmpty(str3)) && (str3.length() > 128)) {
      paramBundle.putString("title", k.a(str3, 128, null, null));
    }
    if ((!TextUtils.isEmpty(str4)) && (str4.length() > 512)) {
      paramBundle.putString("summary", k.a(str4, 512, null, null));
    }
    boolean bool;
    if (paramBundle.getInt("cflag", 0) == 1)
    {
      bool = true;
      if (!k.a(paramActivity, bool)) {
        break label894;
      }
      f.c("openSDK_LOG.QQShare", "shareToQQ, support share");
      b(paramActivity, paramBundle, paramIUiListener);
    }
    for (;;)
    {
      f.c("openSDK_LOG.QQShare", "shareToQQ() -- end.");
      return;
      bool = false;
      break;
      try
      {
        label894:
        f.d("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
        new TDialog(paramActivity, "", a(""), null, this.b).show();
      }
      catch (RuntimeException paramActivity)
      {
        f.b("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", paramActivity);
        paramActivity.printStackTrace();
        paramIUiListener.onError(new UiError(-6, "没有在主线程调用！", null));
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\share\QQShare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */