package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.c.c;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.k;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class a
  extends Dialog
{
  private String a;
  private b b;
  private IUiListener c;
  private Handler d;
  private FrameLayout e;
  private LinearLayout f;
  private FrameLayout g;
  private ProgressBar h;
  private String i;
  private c j;
  private Context k;
  private com.tencent.open.web.security.b l;
  private boolean m = false;
  private int n;
  private String o;
  private String p;
  private long q = 0L;
  private long r = 30000L;
  private HashMap<String, Runnable> s;
  
  public a(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    this.k = paramContext;
    this.a = paramString2;
    this.b = new b(paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.d = new c(this.b, paramContext.getMainLooper());
    this.c = paramIUiListener;
    this.i = paramString1;
    this.l = new com.tencent.open.web.security.b();
    getWindow().setSoftInputMode(32);
  }
  
  private String a()
  {
    String str = this.a.substring(this.a.indexOf("?") + 1);
    str = "http://qzs.qq.com/open/mobile/login/qzsjump.html?" + str;
    f.c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
    return str;
  }
  
  private String a(String paramString)
  {
    paramString = new StringBuilder(paramString);
    if ((!TextUtils.isEmpty(this.p)) && (this.p.length() >= 4))
    {
      String str = this.p.substring(this.p.length() - 4);
      paramString.append("_u_").append(str);
    }
    return paramString.toString();
  }
  
  private void b()
  {
    c();
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    this.j = new c(this.k);
    if (Build.VERSION.SDK_INT >= 11) {
      this.j.setLayerType(1, null);
    }
    this.j.setLayoutParams(localLayoutParams);
    this.e = new FrameLayout(this.k);
    localLayoutParams.gravity = 17;
    this.e.setLayoutParams(localLayoutParams);
    this.e.addView(this.j);
    this.e.addView(this.g);
    setContentView(this.e);
  }
  
  private static void b(Context paramContext, String paramString)
  {
    try
    {
      paramString = k.d(paramString);
      int i1 = paramString.getInt("type");
      paramString = paramString.getString("msg");
      Toast.makeText(paramContext.getApplicationContext(), paramString, i1).show();
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void c()
  {
    this.h = new ProgressBar(this.k);
    Object localObject1 = new LinearLayout.LayoutParams(-2, -2);
    this.h.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.f = new LinearLayout(this.k);
    localObject1 = null;
    Object localObject2;
    if (this.i.equals("action_login"))
    {
      localObject2 = new LinearLayout.LayoutParams(-2, -2);
      ((LinearLayout.LayoutParams)localObject2).gravity = 16;
      ((LinearLayout.LayoutParams)localObject2).leftMargin = 5;
      localObject1 = new TextView(this.k);
      if (!Locale.getDefault().getLanguage().equals("zh")) {
        break label285;
      }
      ((TextView)localObject1).setText("登录中...");
    }
    for (;;)
    {
      ((TextView)localObject1).setTextColor(Color.rgb(255, 255, 255));
      ((TextView)localObject1).setTextSize(18.0F);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject2 = new FrameLayout.LayoutParams(-2, -2);
      ((FrameLayout.LayoutParams)localObject2).gravity = 17;
      this.f.setLayoutParams((ViewGroup.LayoutParams)localObject2);
      this.f.addView(this.h);
      if (localObject1 != null) {
        this.f.addView((View)localObject1);
      }
      this.g = new FrameLayout(this.k);
      localObject1 = new FrameLayout.LayoutParams(-1, -2);
      ((FrameLayout.LayoutParams)localObject1).leftMargin = 80;
      ((FrameLayout.LayoutParams)localObject1).rightMargin = 80;
      ((FrameLayout.LayoutParams)localObject1).topMargin = 40;
      ((FrameLayout.LayoutParams)localObject1).bottomMargin = 40;
      ((FrameLayout.LayoutParams)localObject1).gravity = 17;
      this.g.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.g.setBackgroundResource(17301504);
      this.g.addView(this.f);
      return;
      label285:
      ((TextView)localObject1).setText("Logging in...");
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void d()
  {
    this.j.setVerticalScrollBarEnabled(false);
    this.j.setHorizontalScrollBarEnabled(false);
    this.j.setWebViewClient(new a(null));
    this.j.setWebChromeClient(new WebChromeClient());
    this.j.clearFormData();
    this.j.clearSslPreferences();
    this.j.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        return true;
      }
    });
    this.j.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        }
        for (;;)
        {
          return false;
          if (!paramAnonymousView.hasFocus()) {
            paramAnonymousView.requestFocus();
          }
        }
      }
    });
    WebSettings localWebSettings = this.j.getSettings();
    localWebSettings.setSavePassword(false);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setNeedInitialFocus(false);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setDatabasePath(this.k.getDir("databases", 0).getPath());
    localWebSettings.setDomStorageEnabled(true);
    f.a("openSDK_LOG.AuthDialog", "-->mUrl : " + this.a);
    this.o = this.a;
    this.j.loadUrl(this.a);
    this.j.setVisibility(4);
    this.j.getSettings().setSavePassword(false);
    this.l.a(new SecureJsInterface(), "SecureJsInterface");
    SecureJsInterface.isPWDEdit = false;
    super.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        try
        {
          if (JniInterface.isJniOk) {
            JniInterface.clearAllPWD();
          }
          return;
        }
        catch (Exception paramAnonymousDialogInterface) {}
      }
    });
  }
  
  private boolean e()
  {
    Object localObject1 = b.a();
    String str = ((b)localObject1).c();
    Object localObject2 = new b.a();
    ((b.a)localObject2).a = this.c;
    ((b.a)localObject2).b = this;
    ((b.a)localObject2).c = str;
    localObject1 = ((b)localObject1).a((b.a)localObject2);
    localObject2 = this.a.substring(0, this.a.indexOf("?"));
    Bundle localBundle = k.b(this.a);
    localBundle.putString("token_key", str);
    localBundle.putString("serial", (String)localObject1);
    localBundle.putString("browser", "1");
    this.a = ((String)localObject2 + "?" + HttpUtils.encodeUrl(localBundle));
    return k.a(this.k, this.a);
  }
  
  public void a(String paramString1, String paramString2)
  {
    paramString1 = "javascript:" + paramString1 + "(" + paramString2 + ");void(" + System.currentTimeMillis() + ");";
    this.j.loadUrl(paramString1);
  }
  
  public void dismiss()
  {
    this.s.clear();
    this.d.removeCallbacksAndMessages(null);
    if (isShowing()) {
      super.dismiss();
    }
    if (this.j != null)
    {
      this.j.destroy();
      this.j = null;
    }
  }
  
  public void onBackPressed()
  {
    if (!this.m) {
      this.b.onCancel();
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    b();
    d();
    this.s = new HashMap();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  private class a
    extends WebViewClient
  {
    private a() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      f.a("openSDK_LOG.AuthDialog", "-->onPageFinished, url: " + paramString);
      a.g(a.this).setVisibility(8);
      if (a.e(a.this) != null) {
        a.e(a.this).setVisibility(0);
      }
      if (!TextUtils.isEmpty(paramString)) {
        a.n(a.this).removeCallbacks((Runnable)a.p(a.this).remove(paramString));
      }
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      f.a("openSDK_LOG.AuthDialog", "-->onPageStarted, url: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      a.g(a.this).setVisibility(0);
      a.a(a.this, SystemClock.elapsedRealtime());
      if (!TextUtils.isEmpty(a.i(a.this))) {
        a.n(a.this).removeCallbacks((Runnable)a.p(a.this).remove(a.i(a.this)));
      }
      a.c(a.this, paramString);
      paramWebView = new a.d(a.this, a.i(a.this));
      a.p(a.this).put(paramString, paramWebView);
      a.n(a.this).postDelayed(paramWebView, 120000L);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      f.c("openSDK_LOG.AuthDialog", "-->onReceivedError, errorCode: " + paramInt + " | description: " + paramString1);
      if (!k.b(a.a(a.this)))
      {
        a.f(a.this).onError(new UiError(9001, "当前网络不可用，请稍后重试！", paramString2));
        a.this.dismiss();
        return;
      }
      if (!a.i(a.this).startsWith("http://qzs.qq.com/open/mobile/login/qzsjump.html?"))
      {
        long l1 = SystemClock.elapsedRealtime();
        long l2 = a.j(a.this);
        if ((a.k(a.this) < 1) && (l1 - l2 < a.l(a.this)))
        {
          a.m(a.this);
          a.n(a.this).postDelayed(new Runnable()
          {
            public void run()
            {
              a.e(a.this).loadUrl(a.i(a.this));
            }
          }, 500L);
          return;
        }
        a.e(a.this).loadUrl(a.o(a.this));
        return;
      }
      a.f(a.this).onError(new UiError(paramInt, paramString1, paramString2));
      a.this.dismiss();
    }
    
    @TargetApi(8)
    public void onReceivedSslError(WebView paramWebView, final SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      f.e("openSDK_LOG.AuthDialog", "-->onReceivedSslError " + paramSslError.getPrimaryError() + "请求不合法，请检查手机安全设置，如系统时间、代理等");
      Object localObject = Locale.getDefault().getLanguage();
      paramWebView = "The SSL certificate is invalid,do you countinue?";
      paramSslError = "yes";
      String str = "no";
      if (((String)localObject).equals("zh"))
      {
        paramWebView = "ssl证书无效，是否继续访问？";
        paramSslError = "是";
        str = "否";
      }
      localObject = new AlertDialog.Builder(a.a(a.this));
      ((AlertDialog.Builder)localObject).setMessage(paramWebView);
      ((AlertDialog.Builder)localObject).setPositiveButton(paramSslError, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramSslErrorHandler.proceed();
        }
      });
      ((AlertDialog.Builder)localObject).setNegativeButton(str, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramSslErrorHandler.cancel();
          a.this.dismiss();
        }
      });
      ((AlertDialog.Builder)localObject).create().show();
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      f.a("openSDK_LOG.AuthDialog", "-->Redirect URL: " + paramString);
      if (paramString.startsWith("auth://browser"))
      {
        paramWebView = k.c(paramString);
        a.a(a.this, a.b(a.this));
        if (a.c(a.this)) {}
        for (;;)
        {
          return true;
          if (paramWebView.optString("fail_cb", null) != null)
          {
            a.this.a(paramWebView.optString("fail_cb"), "");
          }
          else
          {
            if (paramWebView.optInt("fall_to_wv") == 1)
            {
              paramString = a.this;
              if (a.d(a.this).indexOf("?") > -1) {}
              for (paramWebView = "&";; paramWebView = "?")
              {
                a.a(paramString, paramWebView);
                a.a(a.this, "browser_error=1");
                a.e(a.this).loadUrl(a.d(a.this));
                break;
              }
            }
            paramWebView = paramWebView.optString("redir", null);
            if (paramWebView != null) {
              a.e(a.this).loadUrl(paramWebView);
            }
          }
        }
      }
      if (paramString.startsWith("auth://tauth.qq.com/"))
      {
        a.f(a.this).onComplete(k.c(paramString));
        a.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        a.f(a.this).onCancel();
        a.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        a.this.dismiss();
        return true;
      }
      if ((paramString.startsWith("download://")) || (paramString.endsWith(".apk"))) {
        try
        {
          if (paramString.startsWith("download://")) {}
          for (paramWebView = Uri.parse(Uri.decode(paramString.substring("download://".length())));; paramWebView = Uri.parse(Uri.decode(paramString)))
          {
            paramWebView = new Intent("android.intent.action.VIEW", paramWebView);
            paramWebView.addFlags(268435456);
            a.a(a.this).startActivity(paramWebView);
            break;
          }
          if (!paramString.startsWith("auth://progress")) {
            break label490;
          }
        }
        catch (Exception paramWebView)
        {
          f.b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", paramWebView);
        }
      }
      try
      {
        paramWebView = Uri.parse(paramString).getPathSegments();
        if (paramWebView.isEmpty()) {
          return true;
        }
        int i = Integer.valueOf((String)paramWebView.get(0)).intValue();
        if (i == 0)
        {
          a.g(a.this).setVisibility(8);
          a.e(a.this).setVisibility(0);
        }
        else if (i == 1)
        {
          a.g(a.this).setVisibility(0);
        }
      }
      catch (Exception paramWebView)
      {
        return true;
      }
      label490:
      if (paramString.startsWith("auth://onLoginSubmit")) {
        try
        {
          paramWebView = Uri.parse(paramString).getPathSegments();
          if (!paramWebView.isEmpty()) {
            a.b(a.this, (String)paramWebView.get(0));
          }
          return true;
        }
        catch (Exception paramWebView)
        {
          for (;;) {}
        }
      }
      if (a.h(a.this).a(a.e(a.this), paramString)) {
        return true;
      }
      f.c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
      return false;
      return true;
      return true;
    }
  }
  
  private class b
    implements IUiListener
  {
    String a;
    String b;
    private String d;
    private IUiListener e;
    
    public b(String paramString1, String paramString2, String paramString3, IUiListener paramIUiListener)
    {
      this.d = paramString1;
      this.a = paramString2;
      this.b = paramString3;
      this.e = paramIUiListener;
    }
    
    private void a(String paramString)
    {
      try
      {
        onComplete(k.d(paramString));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        onError(new UiError(-4, "服务器返回数据格式有误!", paramString));
      }
    }
    
    public void onCancel()
    {
      if (this.e != null)
      {
        this.e.onCancel();
        this.e = null;
      }
    }
    
    public void onComplete(Object paramObject)
    {
      paramObject = (JSONObject)paramObject;
      g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, ((JSONObject)paramObject).optInt("ret", -6), this.a, false);
      if (this.e != null)
      {
        this.e.onComplete(paramObject);
        this.e = null;
      }
    }
    
    public void onError(UiError paramUiError)
    {
      if (paramUiError.errorMessage != null) {}
      for (String str = paramUiError.errorMessage + this.a;; str = this.a)
      {
        g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramUiError.errorCode, str, false);
        a.a(a.this, str);
        if (this.e != null)
        {
          this.e.onError(paramUiError);
          this.e = null;
        }
        return;
      }
    }
  }
  
  private class c
    extends Handler
  {
    private a.b b;
    
    public c(a.b paramb, Looper paramLooper)
    {
      super();
      this.b = paramb;
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        return;
      case 1: 
        a.b.a(this.b, (String)paramMessage.obj);
        return;
      case 2: 
        this.b.onCancel();
        return;
      }
      a.a(a.a(a.this), (String)paramMessage.obj);
    }
  }
  
  class d
    implements Runnable
  {
    String a = "";
    
    public d(String paramString)
    {
      this.a = paramString;
    }
    
    public void run()
    {
      f.a("openSDK_LOG.AuthDialog", "-->timeoutUrl: " + this.a + " | mRetryUrl: " + a.i(a.this));
      if (this.a.equals(a.i(a.this)))
      {
        a.f(a.this).onError(new UiError(9002, "请求页面超时，请稍后重试！", a.i(a.this)));
        a.this.dismiss();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\auth\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */