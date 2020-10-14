package com.androlua.util;

import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import com.androlua.LuaAccessibilityService;
import com.luajava.LuaTable;

public class ClickRunnable
  implements Runnable
{
  private final LuaAccessibilityService a;
  private LuaTable b;
  private int c = 1;
  private int d = -1;
  private int e = -1;
  private ClickCallback f;
  private boolean g = false;
  private ClickRunnable h;
  
  public ClickRunnable(LuaAccessibilityService paramLuaAccessibilityService, LuaTable paramLuaTable)
  {
    this.a = paramLuaAccessibilityService;
    this.b = paramLuaTable;
  }
  
  private boolean a(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    int i = paramString.lastIndexOf("$");
    long l1 = 1000L;
    long l2 = l1;
    String str = paramString;
    if (i > 0) {}
    try
    {
      l2 = Long.valueOf(paramString.substring(i + 1)).longValue();
      l1 = l2;
    }
    catch (Exception localException1)
    {
      for (;;) {}
    }
    str = paramString.substring(0, i);
    l2 = l1;
    i = str.lastIndexOf(">");
    paramString = str;
    if ((i <= 0) || (this.d < 0)) {}
    try
    {
      this.d = Integer.valueOf(str.substring(i + 1)).intValue();
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    this.d = -1;
    paramString = str.substring(0, i);
    i = paramString.lastIndexOf("<");
    str = paramString;
    if ((i <= 0) || (this.e < 0)) {}
    try
    {
      this.e = Integer.valueOf(paramString.substring(i + 1)).intValue();
    }
    catch (Exception localException2)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    this.e = -1;
    str = paramString.substring(0, i);
    this.e -= 1;
    this.d -= 1;
    paramString = this.a.findAccessibilityNodeInfo(str);
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("findAccessibilityNodeInfo ");
    localStringBuilder.append(str);
    localStringBuilder.append(",");
    localStringBuilder.append(this.d);
    localStringBuilder.append(",");
    localStringBuilder.append(this.e);
    localStringBuilder.append(",");
    localStringBuilder.append(paramString);
    Log.i("lua", localStringBuilder.toString());
    if (paramString != null)
    {
      this.d = -1;
      this.a.toClick2(paramString);
    }
    while ((this.d > 0) || (this.e > 0))
    {
      this.a.getHandler().postDelayed(this, l2);
      return true;
    }
    if (this.f != null) {
      this.f.onDone(true, this.b, str, this.c);
    }
    return false;
  }
  
  public boolean canClick()
  {
    if (this.b.length() == 0) {
      return false;
    }
    int k = this.b.length();
    int j;
    for (int i = 0; i < k; i = j)
    {
      if (this.g)
      {
        if (this.f != null) {
          this.f.onDone(false, this.b, null, -1);
        }
        return false;
      }
      Object localObject1 = this.b;
      j = i + 1;
      localObject1 = ((LuaTable)localObject1).get(Integer.valueOf(j));
      Object localObject2;
      if ((localObject1 instanceof LuaTable))
      {
        localObject1 = (LuaTable)localObject1;
        if (((LuaTable)localObject1).length() != 0)
        {
          localObject2 = (String)((LuaTable)localObject1).get(Integer.valueOf(1));
          if ((localObject2 != null) && (a((String)localObject2)))
          {
            this.b = ((LuaTable)localObject1);
            return true;
          }
        }
      }
      else if ((localObject1 instanceof String))
      {
        localObject1 = (String)localObject1;
        localObject2 = this.a.findAccessibilityNodeInfo((String)localObject1);
        if (localObject2 != null)
        {
          this.a.toClick2((AccessibilityNodeInfo)localObject2);
          if (this.f != null) {
            this.f.onDone(true, this.b, (String)localObject1, i);
          }
          return true;
        }
      }
    }
    if (this.f != null) {
      this.f.onDone(false, this.b, null, -1);
    }
    return false;
  }
  
  public boolean canClick(ClickCallback paramClickCallback)
  {
    this.f = paramClickCallback;
    return canClick();
  }
  
  public void cancel()
  {
    this.g = true;
    if (this.h != null) {
      this.h.cancel();
    }
  }
  
  public void run()
  {
    boolean bool2 = this.g;
    boolean bool1 = false;
    if (bool2)
    {
      if (this.f != null) {
        this.f.onDone(false, this.b, null, -1);
      }
      return;
    }
    if ((this.d < 0) && (this.e < 0)) {
      this.c += 1;
    }
    Object localObject = this.b.get(Integer.valueOf(this.c));
    if (localObject == null)
    {
      if (this.f != null)
      {
        localObject = this.f;
        if (this.c == this.b.length()) {
          bool1 = true;
        }
        ((ClickCallback)localObject).onDone(bool1, this.b, null, this.c);
      }
      return;
    }
    if ((localObject instanceof LuaTable))
    {
      localObject = (LuaTable)localObject;
      if (((LuaTable)localObject).length() == 0) {
        return;
      }
      this.h = new ClickRunnable(this.a, (LuaTable)localObject);
      this.h.canClick(new ClickCallback()
      {
        public void onDone(boolean paramAnonymousBoolean, LuaTable paramAnonymousLuaTable, String paramAnonymousString, int paramAnonymousInt)
        {
          ClickRunnable.a(ClickRunnable.this, null);
          ClickRunnable.this.run();
        }
      });
      return;
    }
    if ((localObject instanceof String)) {
      a((String)localObject);
    }
  }
  
  public static abstract interface ClickCallback
  {
    public abstract void onDone(boolean paramBoolean, LuaTable paramLuaTable, String paramString, int paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\ClickRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */