package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.e;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

public class QzonePublish
  extends BaseApi
{
  public static final String HULIAN_CALL_BACK = "hulian_call_back";
  public static final String HULIAN_EXTRA_SCENE = "hulian_extra_scene";
  public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
  public static final String PUBLISH_TO_QZONE_EXTMAP = "extMap";
  public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
  public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
  public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
  public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
  public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
  public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
  public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
  public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";
  
  public QzonePublish(Context paramContext, QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  private void b(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
    localStringBuffer = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
    ArrayList localArrayList = paramBundle.getStringArrayList("imageUrl");
    str1 = paramBundle.getString("summary");
    j = paramBundle.getInt("req_type", 3);
    str2 = paramBundle.getString("appName");
    str3 = paramBundle.getString("videoPath");
    k = paramBundle.getInt("videoDuration");
    l = paramBundle.getLong("videoSize");
    localObject1 = "";
    try
    {
      paramBundle = paramBundle.getBundle("extMap");
      paramIUiListener = (IUiListener)localObject1;
      if (paramBundle == null) {
        break label180;
      }
      paramIUiListener = paramBundle.keySet();
      localObject2 = new JSONObject();
      paramIUiListener = paramIUiListener.iterator();
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        Object localObject2;
        String str4;
        label180:
        int m;
        int i;
        f.b("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", paramBundle);
        paramIUiListener = (IUiListener)localObject1;
        continue;
        localStringBuffer.append("&image_url=" + Base64.encodeToString(k.i(((StringBuffer)localObject1).toString()), 2));
        if (4 == j)
        {
          paramBundle = "8";
          localStringBuffer.append("&videoPath=" + Base64.encodeToString(k.i(str3), 2));
          localStringBuffer.append("&videoDuration=" + Base64.encodeToString(k.i(String.valueOf(k)), 2));
          localStringBuffer.append("&videoSize=" + Base64.encodeToString(k.i(String.valueOf(l)), 2));
        }
        if (!TextUtils.isEmpty(str1)) {
          localStringBuffer.append("&description=" + Base64.encodeToString(k.i(str1), 2));
        }
        if (!TextUtils.isEmpty((CharSequence)localObject2)) {
          localStringBuffer.append("&share_id=" + (String)localObject2);
        }
        if (!TextUtils.isEmpty(str2)) {
          localStringBuffer.append("&app_name=" + Base64.encodeToString(k.i(str2), 2));
        }
        if (!k.e(str4)) {
          localStringBuffer.append("&open_id=" + Base64.encodeToString(k.i(str4), 2));
        }
        if (!TextUtils.isEmpty(paramIUiListener)) {
          localStringBuffer.append("&share_qzone_ext_str=" + Base64.encodeToString(k.i(paramIUiListener), 2));
        }
        localStringBuffer.append("&req_type=" + Base64.encodeToString(k.i(String.valueOf(j)), 2));
        f.a("openSDK_LOG.QzonePublish", "doPublishToQzone, url: " + localStringBuffer.toString());
        a.a(e.a(), this.b, "requireApi", new String[] { "shareToNativeQQ" });
        paramIUiListener = new Intent("android.intent.action.VIEW");
        paramIUiListener.setData(Uri.parse(localStringBuffer.toString()));
        paramIUiListener.putExtra("pkg_name", paramActivity.getPackageName());
        if (a(paramIUiListener))
        {
          a(paramActivity, 10104, paramIUiListener, false);
          d.a().a(0, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
          d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", paramBundle, "0", "1", "0");
        }
        for (;;)
        {
          f.c("openSDK_LOG", "doPublishToQzone() --end");
          return;
          f.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
          d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
          d.a().a(this.b.getOpenId(), this.b.getAppId(), "ANDROIDQQ.SHARETOQZ.XX", "11", "3", "1", paramBundle, "0", "1", "0");
        }
      }
    }
    if (paramIUiListener.hasNext())
    {
      str4 = (String)paramIUiListener.next();
      if (!TextUtils.isEmpty(paramBundle.getString(str4))) {
        ((JSONObject)localObject2).put(str4, paramBundle.getString(str4));
      }
    }
    else
    {
      paramIUiListener = (IUiListener)localObject1;
      if (((JSONObject)localObject2).length() > 0) {
        paramIUiListener = ((JSONObject)localObject2).toString();
      }
      localObject2 = this.b.getAppId();
      str4 = this.b.getOpenId();
      f.a("openSDK_LOG.QzonePublish", "openId:" + str4);
      localObject1 = "";
      paramBundle = (Bundle)localObject1;
      if (3 == j)
      {
        paramBundle = (Bundle)localObject1;
        if (localArrayList != null)
        {
          paramBundle = "7";
          localObject1 = new StringBuffer();
          m = localArrayList.size();
          i = 0;
          while (i < m)
          {
            ((StringBuffer)localObject1).append(URLEncoder.encode((String)localArrayList.get(i)));
            if (i != m - 1) {
              ((StringBuffer)localObject1).append(";");
            }
            i += 1;
          }
        }
      }
    }
  }
  
  public void publishToQzone(final Activity paramActivity, final Bundle paramBundle, final IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
    if (paramBundle == null)
    {
      paramIUiListener.onError(new UiError(-6, "传入参数不可以为空", null));
      f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "传入参数不可以为空");
      return;
    }
    if (!k.e(paramActivity))
    {
      paramIUiListener.onError(new UiError(-15, "手Q版本过低，请下载安装最新版手Q", null));
      f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
      d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
      new TDialog(paramActivity, "", a(""), null, this.b).show();
      return;
    }
    Object localObject2 = k.a(paramActivity);
    final Object localObject1;
    if (localObject2 == null) {
      localObject1 = paramBundle.getString("appName");
    }
    int i;
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        paramBundle.putString("appName", (String)localObject1);
      }
      i = paramBundle.getInt("req_type");
      if (i != 3) {
        break label365;
      }
      localObject1 = paramBundle.getStringArrayList("imageUrl");
      if ((localObject1 == null) || (((ArrayList)localObject1).size() <= 0)) {
        break label349;
      }
      int j;
      for (i = 0; i < ((ArrayList)localObject1).size(); i = j + 1)
      {
        j = i;
        if (!k.h((String)((ArrayList)localObject1).get(i)))
        {
          ((ArrayList)localObject1).remove(i);
          j = i - 1;
        }
      }
      localObject1 = localObject2;
      if (((String)localObject2).length() > 20) {
        localObject1 = ((String)localObject2).substring(0, 20) + "...";
      }
    }
    paramBundle.putStringArrayList("imageUrl", (ArrayList)localObject1);
    label349:
    b(paramActivity, paramBundle, paramIUiListener);
    f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
    return;
    label365:
    if (i == 4)
    {
      localObject1 = paramBundle.getString("videoPath");
      if (!k.h((String)localObject1))
      {
        f.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
        paramIUiListener.onError(new UiError(-5, "请选择有效的视频文件", null));
        return;
      }
      localObject2 = new MediaPlayer();
      ((MediaPlayer)localObject2).setOnPreparedListener(new MediaPlayer.OnPreparedListener()
      {
        public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
        {
          long l = new File(localObject1).length();
          int i = paramAnonymousMediaPlayer.getDuration();
          paramBundle.putString("videoPath", localObject1);
          paramBundle.putInt("videoDuration", i);
          paramBundle.putLong("videoSize", l);
          QzonePublish.a(QzonePublish.this, paramActivity, paramBundle, paramIUiListener);
          f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
        }
      });
      ((MediaPlayer)localObject2).setOnErrorListener(new MediaPlayer.OnErrorListener()
      {
        public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          f.e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
          paramIUiListener.onError(new UiError(-5, "请选择有效的视频文件", null));
          return false;
        }
      });
      try
      {
        ((MediaPlayer)localObject2).setDataSource((String)localObject1);
        ((MediaPlayer)localObject2).prepareAsync();
        return;
      }
      catch (Exception paramActivity)
      {
        f.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
        paramIUiListener.onError(new UiError(-5, "请选择有效的视频文件", null));
        return;
      }
    }
    paramIUiListener.onError(new UiError(-5, "请选择支持的分享类型", null));
    f.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end请选择支持的分享类型");
    d.a().a(1, "SHARE_CHECK_SDK", "1000", this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() 请选择支持的分享类型");
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\share\QzonePublish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */