package com.baidu.mobstat;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.json.JSONException;
import org.json.JSONObject;

class bi
  extends WebViewClient
{
  private WeakReference<Context> a;
  private WebViewClient b;
  
  public bi(Context paramContext, WebViewClient paramWebViewClient)
  {
    this.a = new WeakReference(paramContext);
    this.b = paramWebViewClient;
  }
  
  private void a(String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.getString("action");
    localJSONObject = localJSONObject.getJSONObject("obj");
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return;
    }
    if ("onPageStart".equals(paramString))
    {
      StatService.onPageStart(localContext, localJSONObject.getString("page"));
      return;
    }
    if ("onPageEnd".equals(paramString))
    {
      StatService.onPageEnd(localContext, localJSONObject.getString("page"));
      return;
    }
    if ("onEvent".equals(paramString))
    {
      StatService.onEvent(localContext, localJSONObject.getString("event_id"), localJSONObject.getString("label"), localJSONObject.getInt("acc"));
      return;
    }
    if ("onEventStart".equals(paramString))
    {
      StatService.onEventStart(localContext, localJSONObject.getString("event_id"), localJSONObject.getString("label"));
      return;
    }
    if ("onEventEnd".equals(paramString))
    {
      StatService.onEventEnd(localContext, localJSONObject.getString("event_id"), localJSONObject.getString("label"));
      return;
    }
    if ("onEventDuration".equals(paramString)) {
      StatService.onEventDuration(localContext, localJSONObject.getString("event_id"), localJSONObject.getString("label"), localJSONObject.getLong("duration"));
    }
  }
  
  public void doUpdateVisitedHistory(WebView paramWebView, String paramString, boolean paramBoolean)
  {
    if (this.b != null) {
      this.b.doUpdateVisitedHistory(paramWebView, paramString, paramBoolean);
    }
  }
  
  public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    if (this.b != null) {
      this.b.onFormResubmission(paramWebView, paramMessage1, paramMessage2);
    }
  }
  
  public void onLoadResource(WebView paramWebView, String paramString)
  {
    if (this.b != null) {
      this.b.onLoadResource(paramWebView, paramString);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    if (this.b != null) {
      this.b.onPageFinished(paramWebView, paramString);
    }
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    if (this.b != null) {
      this.b.onPageStarted(paramWebView, paramString, paramBitmap);
    }
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (this.b != null) {
      this.b.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
  }
  
  public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
  {
    if (this.b != null) {
      this.b.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
    }
  }
  
  public void onReceivedLoginRequest(WebView paramWebView, String paramString1, String paramString2, String paramString3)
  {
    if (this.b != null) {
      this.b.onReceivedLoginRequest(paramWebView, paramString1, paramString2, paramString3);
    }
  }
  
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (this.b != null) {
      this.b.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
  }
  
  public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2)
  {
    if (this.b != null) {
      this.b.onScaleChanged(paramWebView, paramFloat1, paramFloat2);
    }
  }
  
  @Deprecated
  public void onTooManyRedirects(WebView paramWebView, Message paramMessage1, Message paramMessage2)
  {
    if (this.b != null) {
      this.b.onTooManyRedirects(paramWebView, paramMessage1, paramMessage2);
    }
  }
  
  public void onUnhandledKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    if (this.b != null) {
      this.b.onUnhandledKeyEvent(paramWebView, paramKeyEvent);
    }
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    if (this.b != null) {
      return this.b.shouldInterceptRequest(paramWebView, paramString);
    }
    return null;
  }
  
  public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    if (this.b != null) {
      return this.b.shouldOverrideKeyEvent(paramWebView, paramKeyEvent);
    }
    return false;
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    Log.d("BaiduStatJSInterface", "shouldOverrideUrlLoading");
    try
    {
      Object localObject = URLDecoder.decode(paramString, "UTF-8");
      paramString = (String)localObject;
      try
      {
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          break label61;
        }
        paramString = (String)localObject;
        if (!((String)localObject).startsWith("bmtj:")) {
          break label61;
        }
        a(((String)localObject).substring(5));
        return true;
      }
      catch (UnsupportedEncodingException|JSONException localUnsupportedEncodingException2)
      {
        paramString = (String)localObject;
        localObject = localUnsupportedEncodingException2;
      }
      cz.b(localUnsupportedEncodingException1);
    }
    catch (UnsupportedEncodingException|JSONException localUnsupportedEncodingException1) {}
    label61:
    if (this.b != null) {
      return this.b.shouldOverrideUrlLoading(paramWebView, paramString);
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\bi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */