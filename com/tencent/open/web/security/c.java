package com.tencent.open.web.security;

import android.webkit.WebView;
import com.tencent.open.a.a;
import com.tencent.open.a.f;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class c
  extends a.a
{
  private String d;
  
  public c(WebView paramWebView, long paramLong, String paramString1, String paramString2)
  {
    super(paramWebView, paramLong, paramString1);
    this.d = paramString2;
  }
  
  private void b(String paramString)
  {
    WebView localWebView = (WebView)this.a.get();
    if (localWebView != null)
    {
      StringBuffer localStringBuffer = new StringBuffer("javascript:");
      localStringBuffer.append("if(!!").append(this.d).append("){");
      localStringBuffer.append(this.d);
      localStringBuffer.append("(");
      localStringBuffer.append(paramString);
      localStringBuffer.append(")}");
      paramString = localStringBuffer.toString();
      f.a("openSDK_LOG.SecureJsListener", "-->callback, callback: " + paramString);
      localWebView.loadUrl(paramString);
    }
  }
  
  public void a()
  {
    f.b("openSDK_LOG.SecureJsListener", "-->onNoMatchMethod...");
  }
  
  public void a(Object paramObject)
  {
    f.a("openSDK_LOG.SecureJsListener", "-->onComplete, result: " + paramObject);
  }
  
  public void a(String paramString)
  {
    f.a("openSDK_LOG.SecureJsListener", "-->onCustomCallback, js: " + paramString);
    JSONObject localJSONObject = new JSONObject();
    int i = 0;
    if (!com.tencent.open.c.c.a) {
      i = -4;
    }
    try
    {
      localJSONObject.put("result", i);
      localJSONObject.put("sn", this.b);
      localJSONObject.put("data", paramString);
      b(localJSONObject.toString());
      return;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\web\security\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */