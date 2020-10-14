package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.f;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class TDialog
  extends b
{
  static final FrameLayout.LayoutParams c = new FrameLayout.LayoutParams(-1, -1);
  static Toast d = null;
  private static WeakReference<ProgressDialog> f;
  private WeakReference<Context> e;
  private String g;
  private OnTimeListener h;
  private IUiListener i;
  private FrameLayout j;
  private com.tencent.open.c.b k;
  private Handler l;
  private boolean m = false;
  private QQToken n = null;
  
  public TDialog(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    this.e = new WeakReference(paramContext);
    this.g = paramString2;
    this.h = new OnTimeListener(paramContext, paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.l = new THandler(this.h, paramContext.getMainLooper());
    this.i = paramIUiListener;
    this.n = paramQQToken;
  }
  
  private void a()
  {
    new TextView((Context)this.e.get()).setText("test");
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    this.k = new com.tencent.open.c.b((Context)this.e.get());
    this.k.setLayoutParams(localLayoutParams);
    this.j = new FrameLayout((Context)this.e.get());
    localLayoutParams.gravity = 17;
    this.j.setLayoutParams(localLayoutParams);
    this.j.addView(this.k);
    setContentView(this.j);
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  private void b()
  {
    this.k.setVerticalScrollBarEnabled(false);
    this.k.setHorizontalScrollBarEnabled(false);
    this.k.setWebViewClient(new FbWebViewClient(null));
    this.k.setWebChromeClient(this.b);
    this.k.clearFormData();
    WebSettings localWebSettings = this.k.getSettings();
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
    if ((this.e != null) && (this.e.get() != null))
    {
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath(((Context)this.e.get()).getApplicationContext().getDir("databases", 0).getPath());
    }
    localWebSettings.setDomStorageEnabled(true);
    this.a.a(new JsListener(null), "sdk_js_if");
    this.k.loadUrl(this.g);
    this.k.setLayoutParams(c);
    this.k.setVisibility(4);
    this.k.getSettings().setSavePassword(false);
  }
  
  private static void c(Context paramContext, String paramString)
  {
    try
    {
      paramString = k.d(paramString);
      int i1 = paramString.getInt("type");
      paramString = paramString.getString("msg");
      if (i1 == 0)
      {
        if (d == null) {
          d = Toast.makeText(paramContext, paramString, 0);
        }
        for (;;)
        {
          d.show();
          return;
          d.setView(d.getView());
          d.setText(paramString);
          d.setDuration(0);
        }
      }
      if (i1 != 1) {
        return;
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if (d == null) {
      d = Toast.makeText(paramContext, paramString, 1);
    }
    for (;;)
    {
      d.show();
      return;
      d.setView(d.getView());
      d.setText(paramString);
      d.setDuration(1);
    }
  }
  
  private static void d(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return;
    }
    try
    {
      paramString = k.d(paramString);
      int i1 = paramString.getInt("action");
      paramString = paramString.getString("msg");
      if (i1 == 1)
      {
        if ((f == null) || (f.get() == null))
        {
          paramContext = new ProgressDialog(paramContext);
          paramContext.setMessage(paramString);
          f = new WeakReference(paramContext);
          paramContext.show();
        }
        else
        {
          ((ProgressDialog)f.get()).setMessage(paramString);
          if (!((ProgressDialog)f.get()).isShowing()) {
            ((ProgressDialog)f.get()).show();
          }
        }
      }
      else if (i1 == 0)
      {
        if (f == null) {
          return;
        }
        if ((f.get() != null) && (((ProgressDialog)f.get()).isShowing()))
        {
          ((ProgressDialog)f.get()).dismiss();
          f = null;
        }
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  protected void a(String paramString)
  {
    f.b("openSDK_LOG.TDialog", "--onConsoleMessage--");
    try
    {
      this.a.a(this.k, paramString);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public void onBackPressed()
  {
    if (this.h != null) {
      this.h.onCancel();
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    a();
    b();
  }
  
  private class FbWebViewClient
    extends WebViewClient
  {
    private FbWebViewClient() {}
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      TDialog.d(TDialog.this).setVisibility(0);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      f.a("openSDK_LOG.TDialog", "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      TDialog.c(TDialog.this).onError(new UiError(paramInt, paramString1, paramString2));
      if ((TDialog.a(TDialog.this) != null) && (TDialog.a(TDialog.this).get() != null)) {
        Toast.makeText((Context)TDialog.a(TDialog.this).get(), "网络连接异常或系统错误", 0).show();
      }
      TDialog.this.dismiss();
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      f.a("openSDK_LOG.TDialog", "Redirect URL: " + paramString);
      if (paramString.startsWith(com.tencent.open.utils.g.a().a((Context)TDialog.a(TDialog.this).get(), "auth://tauth.qq.com/")))
      {
        TDialog.c(TDialog.this).onComplete(k.c(paramString));
        if (TDialog.this.isShowing()) {
          TDialog.this.dismiss();
        }
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        TDialog.c(TDialog.this).onCancel();
        if (TDialog.this.isShowing()) {
          TDialog.this.dismiss();
        }
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        if (TDialog.this.isShowing()) {
          TDialog.this.dismiss();
        }
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
            if ((TDialog.a(TDialog.this) == null) || (TDialog.a(TDialog.this).get() == null)) {
              break;
            }
            ((Context)TDialog.a(TDialog.this).get()).startActivity(paramWebView);
            break;
          }
          if (!paramString.startsWith("auth://progress")) {
            break label285;
          }
        }
        catch (Exception paramWebView)
        {
          paramWebView.printStackTrace();
        }
      }
      return true;
      label285:
      return false;
      return true;
    }
  }
  
  private class JsListener
    extends a.b
  {
    private JsListener() {}
    
    public void onAddShare(String paramString)
    {
      f.b("openSDK_LOG.TDialog", "JsListener onAddShare");
      onComplete(paramString);
    }
    
    public void onCancel(String paramString)
    {
      f.e("openSDK_LOG.TDialog", "JsListener onCancel --msg = " + paramString);
      TDialog.b(TDialog.this).obtainMessage(2, paramString).sendToTarget();
      TDialog.this.dismiss();
    }
    
    public void onCancelAddShare(String paramString)
    {
      f.e("openSDK_LOG.TDialog", "JsListener onCancelAddShare" + paramString);
      onCancel("cancel");
    }
    
    public void onCancelInvite()
    {
      f.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
      onCancel("");
    }
    
    public void onCancelLogin()
    {
      onCancel("");
    }
    
    public void onComplete(String paramString)
    {
      TDialog.b(TDialog.this).obtainMessage(1, paramString).sendToTarget();
      f.e("openSDK_LOG.TDialog", "JsListener onComplete" + paramString);
      TDialog.this.dismiss();
    }
    
    public void onInvite(String paramString)
    {
      onComplete(paramString);
    }
    
    public void onLoad(String paramString)
    {
      TDialog.b(TDialog.this).obtainMessage(4, paramString).sendToTarget();
    }
    
    public void showMsg(String paramString)
    {
      TDialog.b(TDialog.this).obtainMessage(3, paramString).sendToTarget();
    }
  }
  
  private static class OnTimeListener
    implements IUiListener
  {
    String a;
    String b;
    private WeakReference<Context> c;
    private String d;
    private IUiListener e;
    
    public OnTimeListener(Context paramContext, String paramString1, String paramString2, String paramString3, IUiListener paramIUiListener)
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
  
  private class THandler
    extends Handler
  {
    private TDialog.OnTimeListener b;
    
    public THandler(TDialog.OnTimeListener paramOnTimeListener, Looper paramLooper)
    {
      super();
      this.b = paramOnTimeListener;
    }
    
    public void handleMessage(Message paramMessage)
    {
      f.b("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + paramMessage.what);
      switch (paramMessage.what)
      {
      }
      do
      {
        do
        {
          return;
          TDialog.OnTimeListener.a(this.b, (String)paramMessage.obj);
          return;
          this.b.onCancel();
          return;
        } while ((TDialog.a(TDialog.this) == null) || (TDialog.a(TDialog.this).get() == null));
        TDialog.a((Context)TDialog.a(TDialog.this).get(), (String)paramMessage.obj);
        return;
        return;
      } while ((TDialog.a(TDialog.this) == null) || (TDialog.a(TDialog.this).get() == null));
      TDialog.b((Context)TDialog.a(TDialog.this).get(), (String)paramMessage.obj);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\TDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */