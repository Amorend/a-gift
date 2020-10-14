package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.g;
import com.tencent.open.utils.h;
import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import com.tencent.open.web.security.JniInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthAgent
  extends BaseApi
{
  public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
  public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
  public static String SECURE_LIB_FILE_NAME = "libwbsafeedit";
  public static String SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
  public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
  public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
  private IUiListener c;
  private String d;
  private WeakReference<Activity> e;
  
  static
  {
    String str = Build.CPU_ABI;
    if ((str != null) && (!str.equals("")))
    {
      if (str.equalsIgnoreCase("arm64-v8a"))
      {
        SECURE_LIB_FILE_NAME = "libwbsafeedit_64";
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
        return;
      }
      if (str.equalsIgnoreCase("x86"))
      {
        SECURE_LIB_FILE_NAME = "libwbsafeedit_x86";
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "is x86 architecture");
        return;
      }
      if (str.equalsIgnoreCase("x86_64"))
      {
        SECURE_LIB_FILE_NAME = "libwbsafeedit_x86_64";
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
        return;
      }
      SECURE_LIB_FILE_NAME = "libwbsafeedit";
      SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
      com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
      return;
    }
    SECURE_LIB_FILE_NAME = "libwbsafeedit";
    SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
  }
  
  public AuthAgent(QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  private int a(boolean paramBoolean1, final IUiListener paramIUiListener, boolean paramBoolean2)
  {
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start");
    CookieSyncManager.createInstance(e.a());
    final Object localObject1 = a();
    if (paramBoolean1) {
      ((Bundle)localObject1).putString("isadd", "1");
    }
    ((Bundle)localObject1).putString("scope", this.d);
    ((Bundle)localObject1).putString("client_id", this.b.getAppId());
    if (isOEM) {
      ((Bundle)localObject1).putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
    }
    for (;;)
    {
      Object localObject2 = System.currentTimeMillis() / 1000L + "";
      ((Bundle)localObject1).putString("sign", h.b(e.a(), (String)localObject2));
      ((Bundle)localObject1).putString("time", (String)localObject2);
      ((Bundle)localObject1).putString("display", "mobile");
      ((Bundle)localObject1).putString("response_type", "token");
      ((Bundle)localObject1).putString("redirect_uri", "auth://tauth.qq.com/");
      ((Bundle)localObject1).putString("cancel_display", "1");
      ((Bundle)localObject1).putString("switch", "1");
      ((Bundle)localObject1).putString("status_userip", k.a());
      if (paramBoolean2) {
        ((Bundle)localObject1).putString("style", "qr");
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(g.a().a(e.a(), "https://openmobile.qq.com/oauth2.0/m_authorize?"));
      ((StringBuilder)localObject2).append(HttpUtils.encodeUrl((Bundle)localObject1));
      localObject1 = ((StringBuilder)localObject2).toString();
      paramIUiListener = new c(e.a(), paramIUiListener, true, false);
      com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
      i.a(new Runnable()
      {
        public void run()
        {
          h.a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 3);
          JniInterface.loadSo();
          if (AuthAgent.e(AuthAgent.this) == null) {
            return;
          }
          final Activity localActivity = (Activity)AuthAgent.e(AuthAgent.this).get();
          if (localActivity != null) {
            localActivity.runOnUiThread(new Runnable()
            {
              public void run()
              {
                Object localObject;
                if (JniInterface.isJniOk)
                {
                  localObject = new a(localActivity, "action_login", AuthAgent.1.this.a, AuthAgent.1.this.b, AuthAgent.f(AuthAgent.this));
                  if (!localActivity.isFinishing()) {
                    ((a)localObject).show();
                  }
                }
                do
                {
                  return;
                  com.tencent.open.a.f.d("openSDK_LOG.AuthAgent", "OpenUi, secure so load failed, goto download QQ.");
                  localObject = new TDialog(localActivity, "", AuthAgent.a(AuthAgent.this, ""), null, AuthAgent.g(AuthAgent.this));
                } while (localActivity.isFinishing());
                ((TDialog)localObject).show();
              }
            });
          }
        }
      });
      com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
      return 2;
      ((Bundle)localObject1).putString("pf", "openmobile_android");
    }
  }
  
  private boolean a(Activity paramActivity, Fragment paramFragment, boolean paramBoolean)
  {
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
    Intent localIntent = b("com.tencent.open.agent.AgentActivity");
    if (localIntent != null)
    {
      Bundle localBundle = a();
      if (paramBoolean) {
        localBundle.putString("isadd", "1");
      }
      localBundle.putString("scope", this.d);
      localBundle.putString("client_id", this.b.getAppId());
      if (isOEM)
      {
        localBundle.putString("pf", "desktop_m_qq-" + installChannel + "-" + "android" + "-" + registerChannel + "-" + businessId);
        localBundle.putString("need_pay", "1");
        localBundle.putString("oauth_app_name", h.a(e.a()));
        localIntent.putExtra("key_action", "action_login");
        localIntent.putExtra("key_params", localBundle);
        localIntent.putExtra("appid", this.b.getAppId());
        if (!a(localIntent)) {
          break label331;
        }
        this.c = new b(this.c);
        UIListenerManager.getInstance().setListenerWithRequestcode(11101, this.c);
        if (paramFragment == null) {
          break label310;
        }
        com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
        a(paramFragment, localIntent, 11101);
      }
      for (;;)
      {
        com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
        d.a().a(0, "LOGIN_CHECK_SDK", "1000", this.b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        return true;
        localBundle.putString("pf", "openmobile_android");
        break;
        label310:
        com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "startAssitActivity activity");
        a(paramActivity, localIntent, 11101);
      }
    }
    label331:
    d.a().a(1, "LOGIN_CHECK_SDK", "1000", this.b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
    return false;
  }
  
  protected void a(IUiListener paramIUiListener)
  {
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "reportDAU() -- start");
    String str1 = this.b.getAccessToken();
    String str2 = this.b.getOpenId();
    String str3 = this.b.getAppId();
    Object localObject = "";
    paramIUiListener = (IUiListener)localObject;
    if (!TextUtils.isEmpty(str1))
    {
      paramIUiListener = (IUiListener)localObject;
      if (!TextUtils.isEmpty(str2))
      {
        paramIUiListener = (IUiListener)localObject;
        if (!TextUtils.isEmpty(str3)) {
          paramIUiListener = k.f("tencent&sdk&qazxc***14969%%" + str1 + str3 + str2 + "qzone3.4");
        }
      }
    }
    if (TextUtils.isEmpty(paramIUiListener))
    {
      com.tencent.open.a.f.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
      return;
    }
    localObject = a();
    ((Bundle)localObject).putString("encrytoken", paramIUiListener);
    HttpUtils.requestAsync(this.b, e.a(), "https://openmobile.qq.com/user/user_login_statis", (Bundle)localObject, "POST", null);
    com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "reportDAU() -- end");
  }
  
  protected void b(IUiListener paramIUiListener)
  {
    Bundle localBundle = a();
    localBundle.putString("reqType", "checkLogin");
    paramIUiListener = new BaseApi.TempRequestListener(this, new a(paramIUiListener));
    HttpUtils.requestAsync(this.b, e.a(), "https://openmobile.qq.com/v3/user/get_info", localBundle, "GET", paramIUiListener);
  }
  
  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener)
  {
    return doLogin(paramActivity, paramString, paramIUiListener, false, null);
  }
  
  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean, Fragment paramFragment)
  {
    return doLogin(paramActivity, paramString, paramIUiListener, paramBoolean, paramFragment, false);
  }
  
  public int doLogin(Activity paramActivity, String paramString, IUiListener paramIUiListener, boolean paramBoolean1, Fragment paramFragment, boolean paramBoolean2)
  {
    this.d = paramString;
    this.e = new WeakReference(paramActivity);
    this.c = paramIUiListener;
    if ((!com.tencent.open.utils.f.a(paramActivity, this.b.getAppId()).b("C_LoginWeb")) && (a(paramActivity, paramFragment, paramBoolean1)))
    {
      com.tencent.open.a.f.c("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
      d.a().a(this.b.getOpenId(), this.b.getAppId(), "2", "1", "5", "0", "0", "0");
      return 1;
    }
    d.a().a(this.b.getOpenId(), this.b.getAppId(), "2", "1", "5", "1", "0", "0");
    com.tencent.open.a.f.d("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
    this.c = new b(this.c);
    return a(paramBoolean1, this.c, paramBoolean2);
  }
  
  public void releaseResource()
  {
    this.c = null;
  }
  
  private class a
    implements IUiListener
  {
    IUiListener a;
    
    public a(IUiListener paramIUiListener)
    {
      this.a = paramIUiListener;
    }
    
    public void onCancel()
    {
      if (this.a != null) {
        this.a.onCancel();
      }
    }
    
    public void onComplete(Object paramObject)
    {
      if (paramObject == null)
      {
        com.tencent.open.a.f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
        return;
      }
      paramObject = (JSONObject)paramObject;
      try
      {
        int i = ((JSONObject)paramObject).getInt("ret");
        if (i == 0) {}
        for (paramObject = "success"; this.a != null; paramObject = ((JSONObject)paramObject).getString("msg"))
        {
          this.a.onComplete(new JSONObject().put("ret", i).put("msg", paramObject));
          break;
        }
        return;
      }
      catch (JSONException paramObject)
      {
        ((JSONException)paramObject).printStackTrace();
        com.tencent.open.a.f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
      }
    }
    
    public void onError(UiError paramUiError)
    {
      if (this.a != null) {
        this.a.onError(paramUiError);
      }
    }
  }
  
  private class b
    implements IUiListener
  {
    IUiListener a;
    private final String c = "sendinstall";
    private final String d = "installwording";
    private final String e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";
    
    public b(IUiListener paramIUiListener)
    {
      this.a = paramIUiListener;
    }
    
    private Drawable a(String paramString, Context paramContext)
    {
      Object localObject2 = paramContext.getApplicationContext().getAssets();
      Object localObject1 = null;
      paramContext = (Context)localObject1;
      try
      {
        localObject2 = ((AssetManager)localObject2).open(paramString);
        if (localObject2 == null) {
          return null;
        }
        paramContext = (Context)localObject1;
        boolean bool = paramString.endsWith(".9.png");
        if (bool)
        {
          paramString = null;
          paramContext = (Context)localObject1;
          try
          {
            localObject2 = BitmapFactory.decodeStream((InputStream)localObject2);
            paramString = (String)localObject2;
          }
          catch (OutOfMemoryError localOutOfMemoryError)
          {
            for (;;)
            {
              paramContext = (Context)localObject1;
              localOutOfMemoryError.printStackTrace();
            }
          }
          if (paramString != null)
          {
            paramContext = (Context)localObject1;
            localObject2 = paramString.getNinePatchChunk();
            paramContext = (Context)localObject1;
            NinePatch.isNinePatchChunk((byte[])localObject2);
            paramContext = (Context)localObject1;
            paramString = new NinePatchDrawable(paramString, (byte[])localObject2, new Rect(), null);
            paramContext = paramString;
          }
        }
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
        return paramContext;
        return null;
        paramContext = (Context)localObject1;
        paramString = Drawable.createFromStream(localOutOfMemoryError, paramString);
        paramContext = paramString;
        localOutOfMemoryError.close();
        paramContext = paramString;
      }
      return paramContext;
    }
    
    private View a(Context paramContext, Drawable paramDrawable, String paramString, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
    {
      Object localObject1 = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      float f = ((DisplayMetrics)localObject1).density;
      localObject1 = new RelativeLayout(paramContext);
      Object localObject2 = new ImageView(paramContext);
      ((ImageView)localObject2).setImageDrawable(paramDrawable);
      ((ImageView)localObject2).setScaleType(ImageView.ScaleType.FIT_XY);
      ((ImageView)localObject2).setId(1);
      int i = (int)(60.0F * f);
      int j = (int)(60.0F * f);
      int k = (int)(14.0F * f);
      k = (int)(18.0F * f);
      int m = (int)(6.0F * f);
      int n = (int)(18.0F * f);
      paramDrawable = new RelativeLayout.LayoutParams(i, j);
      paramDrawable.addRule(9);
      paramDrawable.setMargins(0, k, m, n);
      ((RelativeLayout)localObject1).addView((View)localObject2, paramDrawable);
      paramDrawable = new TextView(paramContext);
      paramDrawable.setText(paramString);
      paramDrawable.setTextSize(14.0F);
      paramDrawable.setGravity(3);
      paramDrawable.setIncludeFontPadding(false);
      paramDrawable.setPadding(0, 0, 0, 0);
      paramDrawable.setLines(2);
      paramDrawable.setId(5);
      paramDrawable.setMinWidth((int)(185.0F * f));
      paramString = new RelativeLayout.LayoutParams(-2, -2);
      paramString.addRule(1, 1);
      paramString.addRule(6, 1);
      i = (int)(10.0F * f);
      paramString.setMargins(0, 0, (int)(5.0F * f), 0);
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramDrawable = new View(paramContext);
      paramDrawable.setBackgroundColor(Color.rgb(214, 214, 214));
      paramDrawable.setId(3);
      paramString = new RelativeLayout.LayoutParams(-2, 2);
      paramString.addRule(3, 1);
      paramString.addRule(5, 1);
      paramString.addRule(7, 5);
      paramString.setMargins(0, 0, 0, (int)(12.0F * f));
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramDrawable = new LinearLayout(paramContext);
      paramString = new RelativeLayout.LayoutParams(-2, -2);
      paramString.addRule(5, 1);
      paramString.addRule(7, 5);
      paramString.addRule(3, 3);
      localObject2 = new Button(paramContext);
      ((Button)localObject2).setText("跳过");
      ((Button)localObject2).setBackgroundDrawable(a("buttonNegt.png", paramContext));
      ((Button)localObject2).setTextColor(Color.rgb(36, 97, 131));
      ((Button)localObject2).setTextSize(20.0F);
      ((Button)localObject2).setOnClickListener(paramOnClickListener2);
      ((Button)localObject2).setId(4);
      paramOnClickListener2 = new LinearLayout.LayoutParams(0, (int)(45.0F * f));
      paramOnClickListener2.rightMargin = ((int)(14.0F * f));
      paramOnClickListener2.leftMargin = ((int)(4.0F * f));
      paramOnClickListener2.weight = 1.0F;
      paramDrawable.addView((View)localObject2, paramOnClickListener2);
      paramOnClickListener2 = new Button(paramContext);
      paramOnClickListener2.setText("确定");
      paramOnClickListener2.setTextSize(20.0F);
      paramOnClickListener2.setTextColor(Color.rgb(255, 255, 255));
      paramOnClickListener2.setBackgroundDrawable(a("buttonPost.png", paramContext));
      paramOnClickListener2.setOnClickListener(paramOnClickListener1);
      paramContext = new LinearLayout.LayoutParams(0, (int)(45.0F * f));
      paramContext.weight = 1.0F;
      paramContext.rightMargin = ((int)(4.0F * f));
      paramDrawable.addView(paramOnClickListener2, paramContext);
      ((RelativeLayout)localObject1).addView(paramDrawable, paramString);
      paramContext = new FrameLayout.LayoutParams((int)(279.0F * f), (int)(163.0F * f));
      ((RelativeLayout)localObject1).setPadding((int)(14.0F * f), 0, (int)(12.0F * f), (int)(12.0F * f));
      ((RelativeLayout)localObject1).setLayoutParams(paramContext);
      ((RelativeLayout)localObject1).setBackgroundColor(Color.rgb(247, 251, 247));
      paramContext = new PaintDrawable(Color.rgb(247, 251, 247));
      paramContext.setCornerRadius(5.0F * f);
      ((RelativeLayout)localObject1).setBackgroundDrawable(paramContext);
      return (View)localObject1;
    }
    
    private void a(String paramString, final IUiListener paramIUiListener, final Object paramObject)
    {
      if (AuthAgent.e(AuthAgent.this) == null) {
        return;
      }
      Activity localActivity = (Activity)AuthAgent.e(AuthAgent.this).get();
      if (localActivity == null) {
        return;
      }
      Dialog localDialog = new Dialog(localActivity);
      localDialog.requestWindowFeature(1);
      Object localObject3 = localActivity.getPackageManager();
      Object localObject1 = null;
      Drawable localDrawable = null;
      try
      {
        localObject2 = ((PackageManager)localObject3).getPackageInfo(localActivity.getPackageName(), 0);
        localObject1 = localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          Object localObject2;
          localNameNotFoundException.printStackTrace();
        }
      }
      if (localObject1 != null) {
        localDrawable = ((PackageInfo)localObject1).applicationInfo.loadIcon((PackageManager)localObject3);
      }
      localObject1 = new a(localDialog, paramIUiListener)
      {
        public void onClick(View paramAnonymousView)
        {
          AuthAgent.b.this.a();
          if ((this.d != null) && (this.d.isShowing())) {
            this.d.dismiss();
          }
          if (paramIUiListener != null) {
            paramIUiListener.onComplete(paramObject);
          }
        }
      };
      localObject2 = new a(localDialog, paramIUiListener)
      {
        public void onClick(View paramAnonymousView)
        {
          if ((this.d != null) && (this.d.isShowing())) {
            this.d.dismiss();
          }
          if (paramIUiListener != null) {
            paramIUiListener.onComplete(paramObject);
          }
        }
      };
      localObject3 = new ColorDrawable();
      ((ColorDrawable)localObject3).setAlpha(0);
      localDialog.getWindow().setBackgroundDrawable((Drawable)localObject3);
      localDialog.setContentView(a(localActivity, localDrawable, paramString, (View.OnClickListener)localObject1, (View.OnClickListener)localObject2));
      localDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          if (paramIUiListener != null) {
            paramIUiListener.onComplete(paramObject);
          }
        }
      });
      if ((localActivity != null) && (!localActivity.isFinishing())) {
        localDialog.show();
      }
    }
    
    protected void a()
    {
      Bundle localBundle = AuthAgent.j(AuthAgent.this);
      if (AuthAgent.e(AuthAgent.this) == null) {
        return;
      }
      Activity localActivity = (Activity)AuthAgent.e(AuthAgent.this).get();
      if (localActivity != null) {
        HttpUtils.requestAsync(AuthAgent.k(AuthAgent.this), localActivity, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", localBundle, "POST", null);
      }
    }
    
    public void onCancel()
    {
      if (this.a != null) {
        this.a.onCancel();
      }
    }
    
    public void onComplete(Object paramObject)
    {
      JSONObject localJSONObject;
      int j;
      if (paramObject != null)
      {
        localJSONObject = (JSONObject)paramObject;
        if (localJSONObject != null)
        {
          j = 0;
          Object localObject = "";
          for (;;)
          {
            try
            {
              if (localJSONObject.getInt("sendinstall") != 1) {
                continue;
              }
              i = 1;
              j = i;
              String str = localJSONObject.getString("installwording");
              localObject = str;
            }
            catch (JSONException localJSONException)
            {
              com.tencent.open.a.f.d("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
              int i = j;
              continue;
              if (this.a == null) {
                return;
              }
              if (AuthAgent.h(AuthAgent.this) == null) {
                continue;
              }
              AuthAgent.i(AuthAgent.this).saveSession(localJSONObject);
              this.a.onComplete(paramObject);
            }
            localObject = URLDecoder.decode((String)localObject);
            com.tencent.open.a.f.a("openSDK_LOG.AuthAgent", " WORDING = " + (String)localObject + "xx");
            if ((i == 0) || (TextUtils.isEmpty((CharSequence)localObject))) {
              continue;
            }
            a((String)localObject, this.a, paramObject);
            return;
            i = 0;
          }
        }
      }
    }
    
    public void onError(UiError paramUiError)
    {
      if (this.a != null) {
        this.a.onError(paramUiError);
      }
    }
    
    private abstract class a
      implements View.OnClickListener
    {
      Dialog d;
      
      a(Dialog paramDialog)
      {
        this.d = paramDialog;
      }
    }
  }
  
  private class c
    implements IUiListener
  {
    private final IUiListener b;
    private final boolean c;
    private final Context d;
    
    public c(Context paramContext, IUiListener paramIUiListener, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.d = paramContext;
      this.b = paramIUiListener;
      this.c = paramBoolean1;
      com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
    }
    
    public void onCancel()
    {
      com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
      this.b.onCancel();
      com.tencent.open.a.f.b();
    }
    
    public void onComplete(Object paramObject)
    {
      com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
      paramObject = (JSONObject)paramObject;
      for (;;)
      {
        try
        {
          str1 = ((JSONObject)paramObject).getString("access_token");
          String str2 = ((JSONObject)paramObject).getString("expires_in");
          String str3 = ((JSONObject)paramObject).getString("openid");
          if ((str1 != null) && (AuthAgent.a(AuthAgent.this) != null) && (str3 != null))
          {
            AuthAgent.b(AuthAgent.this).setAccessToken(str1, str2);
            AuthAgent.c(AuthAgent.this).setOpenId(str3);
            com.tencent.connect.a.a.d(this.d, AuthAgent.d(AuthAgent.this));
          }
          str1 = ((JSONObject)paramObject).getString("pf");
          if (str1 == null) {}
        }
        catch (JSONException localJSONException)
        {
          String str1;
          localJSONException.printStackTrace();
          com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", localJSONException);
          continue;
        }
        try
        {
          this.d.getSharedPreferences("pfStore", 0).edit().putString("pf", str1).commit();
          if (this.c) {
            CookieSyncManager.getInstance().sync();
          }
          this.b.onComplete(paramObject);
          AuthAgent.this.releaseResource();
          com.tencent.open.a.f.b();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", localException);
        }
      }
    }
    
    public void onError(UiError paramUiError)
    {
      com.tencent.open.a.f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
      this.b.onError(paramUiError);
      com.tencent.open.a.f.b();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\auth\AuthAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */