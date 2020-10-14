package com.tencent.connect.common;

import android.content.Intent;
import com.tencent.open.a.f;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UIListenerManager
{
  private static UIListenerManager a = null;
  private Map<String, ApiTask> b = Collections.synchronizedMap(new HashMap());
  
  private UIListenerManager()
  {
    if (this.b == null) {
      this.b = Collections.synchronizedMap(new HashMap());
    }
  }
  
  private IUiListener a(int paramInt, IUiListener paramIUiListener)
  {
    if (paramInt == 11101) {
      f.e("openSDK_LOG.UIListenerManager", "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
    }
    for (;;)
    {
      return paramIUiListener;
      if (paramInt == 11105) {
        f.e("openSDK_LOG.UIListenerManager", "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
      } else if (paramInt == 11106) {
        f.e("openSDK_LOG.UIListenerManager", "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
      }
    }
  }
  
  public static UIListenerManager getInstance()
  {
    if (a == null) {
      a = new UIListenerManager();
    }
    return a;
  }
  
  public IUiListener getListnerWithAction(String paramString)
  {
    if (paramString == null)
    {
      f.e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
      return null;
    }
    ApiTask localApiTask;
    synchronized (this.b)
    {
      localApiTask = (ApiTask)this.b.get(paramString);
      this.b.remove(paramString);
      if (localApiTask == null) {
        return null;
      }
    }
    return localApiTask.mListener;
  }
  
  public IUiListener getListnerWithRequestCode(int paramInt)
  {
    String str = h.a(paramInt);
    if (str == null)
    {
      f.e("openSDK_LOG.UIListenerManager", "getListner action is null! rquestCode=" + paramInt);
      return null;
    }
    return getListnerWithAction(str);
  }
  
  public void handleDataToListener(Intent paramIntent, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.UIListenerManager", "handleDataToListener");
    if (paramIntent == null)
    {
      paramIUiListener.onCancel();
      return;
    }
    String str1 = paramIntent.getStringExtra("key_action");
    int i;
    if ("action_login".equals(str1))
    {
      i = paramIntent.getIntExtra("key_error_code", 0);
      if (i == 0)
      {
        paramIntent = paramIntent.getStringExtra("key_response");
        if (paramIntent == null) {}
      }
    }
    String str3;
    String str2;
    do
    {
      do
      {
        try
        {
          paramIUiListener.onComplete(k.d(paramIntent));
          return;
        }
        catch (JSONException localJSONException)
        {
          paramIUiListener.onError(new UiError(-4, "服务器返回数据格式有误!", paramIntent));
          f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", localJSONException);
          return;
        }
        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
        paramIUiListener.onComplete(new JSONObject());
        return;
        f.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + i + "");
        paramIUiListener.onError(new UiError(i, paramIntent.getStringExtra("key_error_msg"), paramIntent.getStringExtra("key_error_detail")));
        return;
      } while (!"action_share".equals(localJSONException));
      str3 = paramIntent.getStringExtra("result");
      str2 = paramIntent.getStringExtra("response");
      if ("cancel".equals(str3))
      {
        paramIUiListener.onCancel();
        return;
      }
      if ("error".equals(str3))
      {
        paramIUiListener.onError(new UiError(-6, "unknown error", str2 + ""));
        return;
      }
    } while (!"complete".equals(str3));
    if (str2 == null) {}
    for (paramIntent = "{\"ret\": 0}";; paramIntent = str2) {
      try
      {
        paramIUiListener.onComplete(new JSONObject(paramIntent));
        return;
      }
      catch (JSONException paramIntent)
      {
        paramIntent.printStackTrace();
        paramIUiListener.onError(new UiError(-4, "json error", str2 + ""));
      }
    }
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent, IUiListener paramIUiListener)
  {
    f.c("openSDK_LOG.UIListenerManager", "onActivityResult req=" + paramInt1 + " res=" + paramInt2);
    Object localObject2 = getListnerWithRequestCode(paramInt1);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      if (paramIUiListener != null) {
        localObject1 = a(paramInt1, paramIUiListener);
      }
    }
    else
    {
      if (paramInt2 != -1) {
        break label566;
      }
      if (paramIntent != null) {
        break label103;
      }
      ((IUiListener)localObject1).onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
      return true;
    }
    f.e("openSDK_LOG.UIListenerManager", "onActivityResult can't find the listener");
    return false;
    label103:
    paramIUiListener = paramIntent.getStringExtra("key_action");
    if ("action_login".equals(paramIUiListener))
    {
      paramInt1 = paramIntent.getIntExtra("key_error_code", 0);
      if (paramInt1 == 0)
      {
        paramIntent = paramIntent.getStringExtra("key_response");
        if (paramIntent == null) {}
      }
    }
    for (;;)
    {
      try
      {
        ((IUiListener)localObject1).onComplete(k.d(paramIntent));
        return true;
      }
      catch (JSONException paramIUiListener)
      {
        ((IUiListener)localObject1).onError(new UiError(-4, "服务器返回数据格式有误!", paramIntent));
        f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", paramIUiListener);
        continue;
      }
      f.b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
      ((IUiListener)localObject1).onComplete(new JSONObject());
      continue;
      f.e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + paramInt1 + "");
      ((IUiListener)localObject1).onError(new UiError(paramInt1, paramIntent.getStringExtra("key_error_msg"), paramIntent.getStringExtra("key_error_detail")));
      continue;
      if ("action_share".equals(paramIUiListener))
      {
        localObject2 = paramIntent.getStringExtra("result");
        paramIUiListener = paramIntent.getStringExtra("response");
        if ("cancel".equals(localObject2)) {
          ((IUiListener)localObject1).onCancel();
        }
        for (;;)
        {
          break;
          if ("error".equals(localObject2))
          {
            ((IUiListener)localObject1).onError(new UiError(-6, "unknown error", paramIUiListener + ""));
          }
          else if ("complete".equals(localObject2))
          {
            if (paramIUiListener == null) {}
            for (paramIntent = "{\"ret\": 0}";; paramIntent = paramIUiListener) {
              try
              {
                ((IUiListener)localObject1).onComplete(new JSONObject(paramIntent));
              }
              catch (JSONException paramIntent)
              {
                paramIntent.printStackTrace();
                ((IUiListener)localObject1).onError(new UiError(-4, "json error", paramIUiListener + ""));
              }
            }
          }
        }
      }
      paramInt1 = paramIntent.getIntExtra("key_error_code", 0);
      if (paramInt1 == 0)
      {
        paramIntent = paramIntent.getStringExtra("key_response");
        if (paramIntent != null) {}
        for (;;)
        {
          try
          {
            ((IUiListener)localObject1).onComplete(k.d(paramIntent));
          }
          catch (JSONException paramIUiListener)
          {
            ((IUiListener)localObject1).onError(new UiError(-4, "服务器返回数据格式有误!", paramIntent));
            continue;
          }
          ((IUiListener)localObject1).onComplete(new JSONObject());
        }
      }
      ((IUiListener)localObject1).onError(new UiError(paramInt1, paramIntent.getStringExtra("key_error_msg"), paramIntent.getStringExtra("key_error_detail")));
      continue;
      label566:
      ((IUiListener)localObject1).onCancel();
    }
  }
  
  public Object setListenerWithRequestcode(int paramInt, IUiListener paramIUiListener)
  {
    String str = h.a(paramInt);
    if (str == null)
    {
      f.e("openSDK_LOG.UIListenerManager", "setListener action is null! rquestCode=" + paramInt);
      return null;
    }
    synchronized (this.b)
    {
      paramIUiListener = (ApiTask)this.b.put(str, new ApiTask(paramInt, paramIUiListener));
      if (paramIUiListener == null) {
        return null;
      }
    }
    return paramIUiListener.mListener;
  }
  
  public Object setListnerWithAction(String paramString, IUiListener paramIUiListener)
  {
    int i = h.a(paramString);
    if (i == -1)
    {
      f.e("openSDK_LOG.UIListenerManager", "setListnerWithAction fail, action = " + paramString);
      return null;
    }
    synchronized (this.b)
    {
      paramString = (ApiTask)this.b.put(paramString, new ApiTask(i, paramIUiListener));
      if (paramString == null) {
        return null;
      }
    }
    return paramString.mListener;
  }
  
  public class ApiTask
  {
    public IUiListener mListener;
    public int mRequestCode;
    
    public ApiTask(int paramInt, IUiListener paramIUiListener)
    {
      this.mRequestCode = paramInt;
      this.mListener = paramIUiListener;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\common\UIListenerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */