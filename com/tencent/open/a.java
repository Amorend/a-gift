package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.tencent.open.a.f;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class a
{
  protected HashMap<String, b> a = new HashMap();
  
  public void a(b paramb, String paramString)
  {
    this.a.put(paramString, paramb);
  }
  
  public void a(String paramString1, String paramString2, List<String> paramList, a parama)
  {
    f.a("openSDK_LOG.JsBridge", "getResult---objName = " + paramString1 + " methodName = " + paramString2);
    int i = 0;
    int j = paramList.size();
    for (;;)
    {
      if (i < j) {
        try
        {
          paramList.set(i, URLDecoder.decode((String)paramList.get(i), "UTF-8"));
          i += 1;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          for (;;)
          {
            localUnsupportedEncodingException.printStackTrace();
          }
        }
      }
    }
    paramString1 = (b)this.a.get(paramString1);
    if (paramString1 != null)
    {
      f.b("openSDK_LOG.JsBridge", "call----");
      paramString1.call(paramString2, paramList, parama);
    }
    do
    {
      return;
      f.b("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
    } while (parama == null);
    parama.a();
  }
  
  public boolean a(WebView paramWebView, String paramString)
  {
    f.a("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + paramString);
    if (paramString == null) {
      return false;
    }
    if (!Uri.parse(paramString).getScheme().equals("jsbridge")) {
      return false;
    }
    Object localObject = new ArrayList(Arrays.asList((paramString + "/#").split("/")));
    if (((ArrayList)localObject).size() < 6) {
      return false;
    }
    String str1 = (String)((ArrayList)localObject).get(2);
    String str2 = (String)((ArrayList)localObject).get(3);
    localObject = ((ArrayList)localObject).subList(4, ((ArrayList)localObject).size() - 1);
    paramString = new a(paramWebView, 4L, paramString);
    paramWebView.getUrl();
    a(str1, str2, (List)localObject, paramString);
    return true;
  }
  
  public static class a
  {
    protected WeakReference<WebView> a;
    protected long b;
    protected String c;
    
    public a(WebView paramWebView, long paramLong, String paramString)
    {
      this.a = new WeakReference(paramWebView);
      this.b = paramLong;
      this.c = paramString;
    }
    
    public void a()
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView == null) {
        return;
      }
      localWebView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
    }
    
    public void a(Object paramObject)
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView == null) {
        return;
      }
      String str = "'undefined'";
      if ((paramObject instanceof String))
      {
        paramObject = ((String)paramObject).replace("\\", "\\\\").replace("'", "\\'");
        str = "'" + paramObject + "'";
      }
      for (;;)
      {
        localWebView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + str + "});");
        return;
        if (((paramObject instanceof Number)) || ((paramObject instanceof Long)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Double)) || ((paramObject instanceof Float))) {
          str = paramObject.toString();
        } else if ((paramObject instanceof Boolean)) {
          str = paramObject.toString();
        }
      }
    }
    
    public void a(String paramString)
    {
      WebView localWebView = (WebView)this.a.get();
      if (localWebView != null) {
        localWebView.loadUrl("javascript:" + paramString);
      }
    }
  }
  
  public static class b
  {
    public void call(String paramString, List<String> paramList, a.a parama)
    {
      Method[] arrayOfMethod = getClass().getDeclaredMethods();
      Object localObject2 = null;
      int j = arrayOfMethod.length;
      int i = 0;
      for (;;)
      {
        Object localObject1 = localObject2;
        if (i < j)
        {
          localObject1 = arrayOfMethod[i];
          if ((!((Method)localObject1).getName().equals(paramString)) || (((Method)localObject1).getParameterTypes().length != paramList.size())) {}
        }
        else
        {
          if (localObject1 != null) {}
          for (;;)
          {
            try
            {
              switch (paramList.size())
              {
              case 0: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3), paramList.get(4), paramList.get(5) });
                paramList = ((Method)localObject1).getReturnType();
                f.b("openSDK_LOG.JsBridge", "-->call, result: " + paramString + " | ReturnType: " + paramList.getName());
                if (("void".equals(paramList.getName())) || (paramList == Void.class))
                {
                  if (parama == null) {
                    break label571;
                  }
                  parama.a(null);
                  return;
                  paramString = ((Method)localObject1).invoke(this, new Object[0]);
                }
                break;
              case 1: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0) });
                break;
              case 2: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1) });
                break;
              case 3: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2) });
                break;
              case 4: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3) });
                break;
              case 5: 
                paramString = ((Method)localObject1).invoke(this, new Object[] { paramList.get(0), paramList.get(1), paramList.get(2), paramList.get(3), paramList.get(4) });
                continue;
                if ((parama == null) || (!customCallback())) {
                  break label571;
                }
                if (paramString != null)
                {
                  paramString = paramString.toString();
                  parama.a(paramString);
                  return;
                }
                break;
              }
            }
            catch (Exception paramString)
            {
              f.b("openSDK_LOG.JsBridge", "-->handler call mehtod ex. targetMethod: " + localObject1, paramString);
              if (parama != null) {
                parama.a();
              }
              return;
            }
            paramString = null;
            continue;
            if (parama != null) {
              parama.a();
            }
            return;
          }
          label571:
          return;
        }
        i += 1;
      }
    }
    
    public boolean customCallback()
    {
      return false;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */