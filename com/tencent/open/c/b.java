package com.tencent.open.c;

import android.content.Context;
import android.webkit.WebView;
import java.lang.reflect.Method;

public class b
  extends WebView
{
  public b(Context paramContext)
  {
    super(paramContext);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Object localObject = getSettings();
    if (localObject == null) {
      return;
    }
    localObject = localObject.getClass();
    try
    {
      localObject = ((Class)localObject).getMethod("removeJavascriptInterface", new Class[] { String.class });
      if (localObject != null) {
        ((Method)localObject).invoke(this, new Object[] { "searchBoxJavaBridge_" });
      }
      return;
    }
    catch (Exception localException) {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\c\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */