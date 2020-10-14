package com.tencent.open;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.f;
import com.tencent.open.c.a.a;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class c
  extends b
  implements a.a
{
  static Toast c = null;
  private String d;
  private IUiListener e;
  private c f;
  private Handler g;
  private com.tencent.open.c.a h;
  private com.tencent.open.c.b i;
  private WeakReference<Context> j;
  private int k;
  
  public c(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    this.j = new WeakReference(paramContext);
    this.d = paramString2;
    this.f = new c(paramContext, paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.g = new d(this.f, paramContext.getMainLooper());
    this.e = paramIUiListener;
    this.k = Math.round(185.0F * paramContext.getResources().getDisplayMetrics().density);
    f.e("openSDK_LOG.PKDialog", "density=" + paramContext.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.k);
  }
  
  private void b()
  {
    this.h = new com.tencent.open.c.a((Context)this.j.get());
    this.h.setBackgroundColor(1711276032);
    this.h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.i = new com.tencent.open.c.b((Context)this.j.get());
    this.i.setBackgroundColor(0);
    this.i.setBackgroundDrawable(null);
    if (Build.VERSION.SDK_INT >= 11) {}
    try
    {
      View.class.getMethod("setLayerType", new Class[] { Integer.TYPE, Paint.class }).invoke(this.i, new Object[] { Integer.valueOf(1), new Paint() });
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, this.k);
      localLayoutParams.addRule(13, -1);
      this.i.setLayoutParams(localLayoutParams);
      this.h.addView(this.i);
      this.h.a(this);
      setContentView(this.h);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void c()
  {
    this.i.setVerticalScrollBarEnabled(false);
    this.i.setHorizontalScrollBarEnabled(false);
    this.i.setWebViewClient(new a(null));
    this.i.setWebChromeClient(this.b);
    this.i.clearFormData();
    WebSettings localWebSettings = this.i.getSettings();
    if (localWebSettings == null) {
      return;
    }
    localWebSettings.setSavePassword(false);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setNeedInitialFocus(false);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setJavaScriptEnabled(true);
    if ((this.j != null) && (this.j.get() != null))
    {
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath(((Context)this.j.get()).getApplicationContext().getDir("databases", 0).getPath());
    }
    localWebSettings.setDomStorageEnabled(true);
    this.a.a(new b(null), "sdk_js_if");
    this.i.clearView();
    this.i.loadUrl(this.d);
    this.i.getSettings().setSavePassword(false);
  }
  
  private static void c(Context paramContext, String paramString)
  {
    try
    {
      paramString = k.d(paramString);
      int m = paramString.getInt("type");
      paramString = paramString.getString("msg");
      if (m == 0)
      {
        if (c == null) {
          c = Toast.makeText(paramContext, paramString, 0);
        }
        for (;;)
        {
          c.show();
          return;
          c.setView(c.getView());
          c.setText(paramString);
          c.setDuration(0);
        }
      }
      if (m != 1) {
        return;
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if (c == null) {
      c = Toast.makeText(paramContext, paramString, 1);
    }
    for (;;)
    {
      c.show();
      return;
      c.setView(c.getView());
      c.setText(paramString);
      c.setDuration(1);
    }
  }
  
  private static void d(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return;
    }
    try
    {
      paramContext = k.d(paramString);
      int m = paramContext.getInt("action");
      paramContext.getString("msg");
      if (m == 1) {}
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void a()
  {
    this.i.getLayoutParams().height = this.k;
    f.e("openSDK_LOG.PKDialog", "onKeyboardHidden keyboard hide");
  }
  
  public void a(int paramInt)
  {
    if ((this.j != null) && (this.j.get() != null)) {
      if ((paramInt >= this.k) || (2 != ((Context)this.j.get()).getResources().getConfiguration().orientation)) {
        break label68;
      }
    }
    label68:
    for (this.i.getLayoutParams().height = paramInt;; this.i.getLayoutParams().height = this.k)
    {
      f.e("openSDK_LOG.PKDialog", "onKeyboardShown keyboard show");
      return;
    }
  }
  
  protected void a(String paramString)
  {
    f.b("openSDK_LOG.PKDialog", "--onConsoleMessage--");
    try
    {
      this.a.a(this.i, paramString);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    getWindow().setSoftInputMode(16);
    getWindow().setSoftInputMode(1);
    b();
    c();
  }
  
  private class a
    extends WebViewClient
  {
    private a() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      c.c(c.this).setVisibility(0);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      f.a("openSDK_LOG.PKDialog", "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      c.b(c.this).onError(new UiError(paramInt, paramString1, paramString2));
      if ((c.a(c.this) != null) && (c.a(c.this).get() != null)) {
        Toast.makeText((Context)c.a(c.this).get(), "网络连接异常或系统错误", 0).show();
      }
      c.this.dismiss();
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      f.a("openSDK_LOG.PKDialog", "Redirect URL: " + paramString);
      if (paramString.startsWith(com.tencent.open.utils.g.a().a((Context)c.a(c.this).get(), "auth://tauth.qq.com/")))
      {
        c.b(c.this).onComplete(k.c(paramString));
        c.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        c.b(c.this).onCancel();
        c.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        c.this.dismiss();
        return true;
      }
      return false;
    }
  }
  
  private class b
    extends a.b
  {
    private b() {}
  }
  
  private static class c
    implements IUiListener
  {
    String a;
    String b;
    private WeakReference<Context> c;
    private String d;
    private IUiListener e;
    
    public c(Context paramContext, String paramString1, String paramString2, String paramString3, IUiListener paramIUiListener)
    {
      this.c = new WeakReference(paramContext);
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
      com.tencent.open.b.g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, ((JSONObject)paramObject).optInt("ret", -6), this.a, false);
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
        com.tencent.open.b.g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramUiError.errorCode, str, false);
        if (this.e != null)
        {
          this.e.onError(paramUiError);
          this.e = null;
        }
        return;
      }
    }
  }
  
  private class d
    extends Handler
  {
    private c.c b;
    
    public d(c.c paramc, Looper paramLooper)
    {
      super();
      this.b = paramc;
    }
    
    public void handleMessage(Message paramMessage)
    {
      f.b("openSDK_LOG.PKDialog", "msg = " + paramMessage.what);
      switch (paramMessage.what)
      {
      }
      do
      {
        do
        {
          return;
          c.c.a(this.b, (String)paramMessage.obj);
          return;
          this.b.onCancel();
          return;
        } while ((c.a(c.this) == null) || (c.a(c.this).get() == null));
        c.a((Context)c.a(c.this).get(), (String)paramMessage.obj);
        return;
        return;
      } while ((c.a(c.this) == null) || (c.a(c.this).get() == null));
      c.b((Context)c.a(c.this).get(), (String)paramMessage.obj);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */