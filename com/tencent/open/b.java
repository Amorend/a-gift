package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.a.f;

public abstract class b
  extends Dialog
{
  protected a a;
  @SuppressLint({"NewApi"})
  protected final WebChromeClient b = new WebChromeClient()
  {
    public void onConsoleMessage(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2)
    {
      f.c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + paramAnonymousString1 + " -- From 222 line " + paramAnonymousInt + " of " + paramAnonymousString2);
      if (Build.VERSION.SDK_INT == 7) {
        b.this.a(paramAnonymousString1);
      }
    }
    
    public boolean onConsoleMessage(ConsoleMessage paramAnonymousConsoleMessage)
    {
      if (paramAnonymousConsoleMessage == null) {
        return false;
      }
      f.c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + paramAnonymousConsoleMessage.message() + " -- From  111 line " + paramAnonymousConsoleMessage.lineNumber() + " of " + paramAnonymousConsoleMessage.sourceId());
      b localb;
      if (Build.VERSION.SDK_INT > 7)
      {
        localb = b.this;
        if (paramAnonymousConsoleMessage != null) {
          break label84;
        }
      }
      label84:
      for (paramAnonymousConsoleMessage = "";; paramAnonymousConsoleMessage = paramAnonymousConsoleMessage.message())
      {
        localb.a(paramAnonymousConsoleMessage);
        return true;
      }
    }
  };
  
  public b(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }
  
  protected abstract void a(String paramString);
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.a = new a();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */