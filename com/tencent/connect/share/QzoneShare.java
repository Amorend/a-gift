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
import com.tencent.open.utils.c;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

public class QzoneShare
  extends BaseApi
{
  public static final String SHARE_TO_QQ_APP_NAME = "appName";
  public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
  public static final String SHARE_TO_QQ_EXT_INT = "cflag";
  public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
  public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
  public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
  public static final String SHARE_TO_QQ_SITE = "site";
  public static final String SHARE_TO_QQ_SUMMARY = "summary";
  public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
  public static final String SHARE_TO_QQ_TITLE = "title";
  public static final String SHARE_TO_QZONE_EXTMAP = "extMap";
  public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
  public static final int SHARE_TO_QZONE_TYPE_APP = 6;
  public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
  public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
  public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
  private boolean c = true;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  public String mViaShareQzoneType = "";
  
  public QzoneShare(Context paramContext, QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  private void b(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QzoneShare", "doshareToQzone() --start");
    StringBuffer localStringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str2 = paramBundle.getString("title");
    String str3 = paramBundle.getString("summary");
    String str4 = paramBundle.getString("targetUrl");
    String str5 = paramBundle.getString("audio_url");
    int k = paramBundle.getInt("req_type", 1);
    String str6 = paramBundle.getString("appName");
    int m = paramBundle.getInt("cflag", 0);
    String str7 = paramBundle.getString("share_qq_ext_str");
    String str1 = "";
    try
    {
      localObject1 = paramBundle.getBundle("extMap");
      paramBundle = str1;
      if (localObject1 != null)
      {
        localObject2 = ((Bundle)localObject1).keySet();
        JSONObject localJSONObject = new JSONObject();
        paramBundle = ((Set)localObject2).iterator();
        while (paramBundle.hasNext())
        {
          String str8 = (String)paramBundle.next();
          localJSONObject.put(str8, ((Bundle)localObject1).get(str8));
        }
        paramBundle = str1;
        if (((Set)localObject2).size() > 0) {
          paramBundle = localJSONObject.toString();
        }
      }
    }
    catch (Exception paramBundle)
    {
      Object localObject1;
      Object localObject2;
      for (;;)
      {
        int j;
        f.b("openSDK_LOG.QzoneShare", "ShareToQzone()  --error parse extmap", paramBundle);
        paramBundle = str1;
        continue;
        int i = localArrayList.size();
      }
      localStringBuffer.append("&image_url=" + Base64.encodeToString(k.i(((StringBuffer)localObject2).toString()), 2));
      if (TextUtils.isEmpty(str2)) {
        break label421;
      }
      localStringBuffer.append("&title=" + Base64.encodeToString(k.i(str2), 2));
      if (TextUtils.isEmpty(str3)) {
        break label462;
      }
      localStringBuffer.append("&description=" + Base64.encodeToString(k.i(str3), 2));
      if (TextUtils.isEmpty(str1)) {
        break label496;
      }
      localStringBuffer.append("&share_id=" + str1);
      if (TextUtils.isEmpty(str4)) {
        break label537;
      }
      localStringBuffer.append("&url=" + Base64.encodeToString(k.i(str4), 2));
      if (TextUtils.isEmpty(str6)) {
        break label578;
      }
      localStringBuffer.append("&app_name=" + Base64.encodeToString(k.i(str6), 2));
      if (k.e((String)localObject1)) {
        break label619;
      }
      localStringBuffer.append("&open_id=" + Base64.encodeToString(k.i((String)localObject1), 2));
      if (k.e(str5)) {
        break label660;
      }
      localStringBuffer.append("&audioUrl=" + Base64.encodeToString(k.i(str5), 2));
      localStringBuffer.append("&req_type=" + Base64.encodeToString(k.i(String.valueOf(k)), 2));
      if (k.e(str7)) {
        break label737;
      }
      localStringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(k.i(str7), 2));
      if (TextUtils.isEmpty(paramBundle)) {
        break label776;
      }
      localStringBuffer.append("&share_qzone_ext_str=" + Base64.encodeToString(k.i(paramBundle), 2));
      localStringBuffer.append("&cflag=" + Base64.encodeToString(k.i(String.valueOf(m)), 2));
      f.a("openSDK_LOG.QzoneShare", "doshareToQzone, url: " + localStringBuffer.toString());
      com.tencent.connect.a.a.a(e.a(), this.b, "requireApi", new String[] { "shareToNativeQQ" });
      paramBundle = new Intent("android.intent.action.VIEW");
      paramBundle.setData(Uri.parse(localStringBuffer.toString()));
      paramBundle.putExtra("pkg_name", paramActivity.getPackageName());
      if (!k.g(paramActivity, "4.6.0")) {
        break label1044;
      }
    }
    str1 = this.b.getAppId();
    localObject1 = this.b.getOpenId();
    f.a("openSDK_LOG.QzoneShare", "openId:" + (String)localObject1);
    if (localArrayList != null)
    {
      localObject2 = new StringBuffer();
      if (localArrayList.size() > 9)
      {
        i = 9;
        j = 0;
        while (j < i)
        {
          ((StringBuffer)localObject2).append(URLEncoder.encode((String)localArrayList.get(j)));
          if (j != i - 1) {
            ((StringBuffer)localObject2).append(";");
          }
          j += 1;
        }
      }
    }
    label421:
    label462:
    label496:
    label537:
    label578:
    label619:
    label660:
    label737:
    label776:
    if (a(paramBundle))
    {
      UIListenerManager.getInstance().setListenerWithRequestcode(11104, paramIUiListener);
      a(paramActivity, paramBundle, 11104);
    }
    f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is < 4.6.0");
    if (a(paramBundle))
    {
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "0", this.mViaShareQzoneType, "0", "1", "0");
      d.a().a(0, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
    }
    for (;;)
    {
      f.c("openSDK_LOG", "doShareToQzone() --end");
      return;
      label1044:
      f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is > 4.6.0");
      if (UIListenerManager.getInstance().setListnerWithAction("shareToQzone", paramIUiListener) != null) {
        f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- do listener onCancel()");
      }
      if (!a(paramBundle)) {
        break;
      }
      a(paramActivity, 10104, paramBundle, false);
      break;
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", this.mViaShareQzoneType, "0", "1", "0");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
    }
  }
  
  public void releaseResource() {}
  
  public void shareToQzone(final Activity paramActivity, final Bundle paramBundle, final IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QzoneShare", "shareToQzone() -- start");
    if (paramBundle == null)
    {
      paramIUiListener.onError(new UiError(-6, "传入参数不可以为空", null));
      f.e("openSDK_LOG.QzoneShare", "shareToQzone() params is null");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "传入参数不可以为空");
      return;
    }
    String str1 = paramBundle.getString("title");
    String str4 = paramBundle.getString("summary");
    String str2 = paramBundle.getString("targetUrl");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    String str3 = k.a(paramActivity);
    Object localObject;
    int k;
    if (str3 == null)
    {
      localObject = paramBundle.getString("appName");
      k = paramBundle.getInt("req_type");
      switch (k)
      {
      case 2: 
      case 3: 
      case 4: 
      default: 
        this.mViaShareQzoneType = "1";
        switch (k)
        {
        case 2: 
        case 3: 
        case 4: 
        default: 
          label183:
          if ((k.e(str1)) && (k.e(str4))) {
            if ((localArrayList != null) && (localArrayList.size() != 0)) {
              this.c = false;
            }
          }
          break;
        }
        break;
      }
    }
    for (;;)
    {
      this.d = false;
      this.e = true;
      this.f = false;
      for (;;)
      {
        if ((k.b()) || (!k.g(paramActivity, "4.5.0"))) {
          break label671;
        }
        paramIUiListener.onError(new UiError(-6, "分享图片失败，检测不到SD卡!", null));
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() sdcard is null--end");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "分享图片失败，检测不到SD卡!");
        return;
        localObject = str3;
        if (str3.length() <= 20) {
          break;
        }
        localObject = str3.substring(0, 20) + "...";
        break;
        this.mViaShareQzoneType = "4";
        break label183;
        this.mViaShareQzoneType = "1";
        break label183;
        this.mViaShareQzoneType = "2";
        break label183;
        if (k.g(paramActivity, "5.0.0"))
        {
          paramIUiListener.onError(new UiError(-15, "手Q版本过低，应用分享只支持手Q5.0及其以上版本", null));
          f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, app share is not support below qq5.0.");
          d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone, app share is not support below qq5.0.");
          return;
        }
        str2 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", new Object[] { this.b.getAppId(), "mqq" });
        paramBundle.putString("targetUrl", str2);
        continue;
        this.c = true;
        this.d = false;
        this.e = true;
        this.f = false;
      }
      paramIUiListener.onError(new UiError(-5, "请选择支持的分享类型", null));
      f.e("openSDK_LOG.QzoneShare", "shareToQzone() error--end请选择支持的分享类型");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 请选择支持的分享类型");
      return;
      str1 = "来自" + (String)localObject + "的分享";
      this.c = true;
      continue;
      this.c = true;
    }
    label671:
    if (this.c)
    {
      if (TextUtils.isEmpty(str2))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl为必填项，请补充后分享", null));
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl为必填项，请补充后分享");
        return;
      }
      if (!k.g(str2))
      {
        paramIUiListener.onError(new UiError(-5, "targetUrl有误", null));
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "targetUrl有误");
        return;
      }
    }
    if (this.d)
    {
      paramBundle.putString("title", "");
      paramBundle.putString("summary", "");
    }
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        paramBundle.putString("appName", (String)localObject);
      }
      if ((localArrayList != null) && ((localArrayList == null) || (localArrayList.size() != 0))) {
        break;
      }
      if (!this.f) {
        break label1249;
      }
      paramIUiListener.onError(new UiError(-6, "纯图分享，imageUrl 不能为空", null));
      f.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() imageUrl is null");
      return;
      if ((this.e) && (k.e(str1)))
      {
        paramIUiListener.onError(new UiError(-6, "title不能为空!", null));
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() title is null--end");
        d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() title is null");
        return;
      }
      if ((!k.e(str1)) && (str1.length() > 200)) {
        paramBundle.putString("title", k.a(str1, 200, null, null));
      }
      if ((!k.e(str4)) && (str4.length() > 600)) {
        paramBundle.putString("summary", k.a(str4, 600, null, null));
      }
    }
    int j;
    for (int i = 0; i < localArrayList.size(); i = j + 1)
    {
      localObject = (String)localArrayList.get(i);
      j = i;
      if (!k.g((String)localObject))
      {
        j = i;
        if (!k.h((String)localObject))
        {
          localArrayList.remove(i);
          j = i - 1;
        }
      }
    }
    if (localArrayList.size() == 0)
    {
      paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
      f.e("openSDK_LOG.QzoneShare", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 非法的图片地址!");
      return;
    }
    paramBundle.putStringArrayList("imageUrl", localArrayList);
    label1249:
    if (!k.g(paramActivity, "4.6.0"))
    {
      f.c("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
      a.a(paramActivity, localArrayList, new c()
      {
        public void a(int paramAnonymousInt, String paramAnonymousString)
        {
          paramIUiListener.onError(new UiError(-6, "非法的图片地址!", null));
        }
        
        public void a(int paramAnonymousInt, ArrayList<String> paramAnonymousArrayList)
        {
          if (paramAnonymousInt == 0) {
            paramBundle.putStringArrayList("imageUrl", paramAnonymousArrayList);
          }
          QzoneShare.a(QzoneShare.this, paramActivity, paramBundle, paramIUiListener);
        }
      });
    }
    for (;;)
    {
      f.c("openSDK_LOG.QzoneShare", "shareToQzone() --end");
      return;
      if ((h.c(paramActivity, "4.2.0") >= 0) && (h.c(paramActivity, "4.6.0") < 0))
      {
        f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver between 4.2.0 and 4.6.0, will use qqshare");
        localObject = new QQShare(paramActivity, this.b);
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          str1 = (String)localArrayList.get(0);
          if ((k == 5) && (!k.h(str1)))
          {
            paramIUiListener.onError(new UiError(-6, "手Q版本过低，纯图分享不支持网路图片", null));
            f.e("openSDK_LOG.QzoneShare", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
            d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
            return;
          }
          paramBundle.putString("imageLocalUrl", str1);
        }
        if (!k.g(paramActivity, "4.5.0")) {
          paramBundle.putInt("cflag", 1);
        }
        ((QQShare)localObject).shareToQQ(paramActivity, paramBundle, paramIUiListener);
      }
      else
      {
        f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
        new TDialog(paramActivity, "", a(""), null, this.b).show();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\share\QzoneShare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */