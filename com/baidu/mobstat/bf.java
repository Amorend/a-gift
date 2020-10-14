package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

class bf
{
  private static final bf b = new bf();
  private HashSet<String> a = new HashSet();
  private boolean c;
  private boolean d;
  
  private View.AccessibilityDelegate a(View paramView)
  {
    try
    {
      paramView = (View.AccessibilityDelegate)paramView.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(paramView, new Object[0]);
      return paramView;
    }
    catch (InvocationTargetException paramView)
    {
      cz.b("getAccessibilityDelegate threw an exception when called.", paramView);
      return null;
    }
    catch (NoSuchMethodException|IllegalAccessException paramView)
    {
      for (;;) {}
    }
  }
  
  public static bf a()
  {
    return b;
  }
  
  private String a(View paramView, Activity paramActivity)
  {
    String str = "";
    if (paramView == null) {
      return "";
    }
    try
    {
      paramActivity = (ViewGroup)((ViewGroup)paramActivity.getWindow().getDecorView().findViewById(16908290)).getChildAt(0);
    }
    catch (Exception paramActivity)
    {
      ArrayList localArrayList;
      int i;
      for (;;) {}
    }
    paramActivity = null;
    localArrayList = new ArrayList();
    for (;;)
    {
      localArrayList.add(paramView.getClass().getName());
      if ((paramView == null) || (paramView == paramActivity)) {
        break;
      }
      paramView = (View)paramView.getParent();
    }
    i = localArrayList.size() - 1;
    paramView = str;
    while (i >= 0)
    {
      paramActivity = new StringBuilder();
      paramActivity.append(paramView);
      paramActivity.append((String)localArrayList.get(i));
      paramActivity.append("/");
      paramView = paramActivity.toString();
      i -= 1;
    }
    paramActivity = paramView;
    if (paramView.endsWith("/")) {
      paramActivity = paramView.substring(0, paramView.length() - 1);
    }
    return paramActivity;
  }
  
  private void a(Activity paramActivity)
  {
    Object localObject = paramActivity.getWindow();
    if (localObject == null) {
      return;
    }
    localObject = ((Window)localObject).getDecorView();
    if (localObject == null) {
      return;
    }
    try
    {
      localObject = (ViewGroup)((ViewGroup)((View)localObject).findViewById(16908290)).getChildAt(0);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localObject = null;
    if (localObject != null) {
      a(paramActivity, (ViewGroup)localObject);
    }
  }
  
  private void a(Activity paramActivity, View paramView)
  {
    if ((paramView instanceof Button))
    {
      String str = ((Button)paramView).getText().toString();
      if (TextUtils.isEmpty(str)) {
        return;
      }
      a(paramActivity, paramView, str);
    }
  }
  
  private void a(Activity paramActivity, View paramView, String paramString)
  {
    paramView.setAccessibilityDelegate(new bh(this, paramActivity, paramView, paramString, a(paramView)));
  }
  
  private void a(Activity paramActivity, ViewGroup paramViewGroup)
  {
    int i = paramViewGroup.getChildCount() - 1;
    while (i >= 0)
    {
      View localView = paramViewGroup.getChildAt(i);
      if ((localView instanceof ViewGroup)) {
        a(paramActivity, (ViewGroup)localView);
      }
      a(paramActivity, localView);
      i -= 1;
    }
  }
  
  private void a(Activity paramActivity, boolean paramBoolean)
  {
    if ((paramActivity instanceof IIgnoreAutoTrace)) {
      return;
    }
    if (paramBoolean) {
      bv.a().a(paramActivity);
    }
    if (paramBoolean)
    {
      ch.a().a(paramActivity, System.currentTimeMillis(), true);
      return;
    }
    ch.a().a(paramActivity, System.currentTimeMillis(), true, null);
  }
  
  @TargetApi(14)
  private void a(Context paramContext, boolean paramBoolean)
  {
    if (this.d) {
      return;
    }
    if (Build.VERSION.SDK_INT < 14)
    {
      if (paramBoolean) {
        cz.a("module autotrace only support android os version bigger than 4.0");
      }
      return;
    }
    b(paramContext);
    this.d = true;
  }
  
  private void b(Activity paramActivity, View paramView, String paramString)
  {
    bv.a().a(paramActivity);
    String str1 = paramActivity.getClass().getName();
    ??? = new StringBuilder();
    ((StringBuilder)???).append(str1);
    ((StringBuilder)???).append("_");
    ((StringBuilder)???).append(paramView.hashCode());
    String str2 = ((StringBuilder)???).toString();
    synchronized (this.a)
    {
      if (this.a.contains(str2)) {
        return;
      }
      paramView = a(paramView, paramActivity);
      int i = Config.EventViewType.BUTTON.getValue();
      bm.a().a(paramActivity.getApplicationContext(), paramString, "", 1, System.currentTimeMillis(), paramView, str1, i, true);
      return;
    }
  }
  
  private void b(Context paramContext)
  {
    bg localbg = new bg(this);
    try
    {
      ((Application)paramContext.getApplicationContext()).registerActivityLifecycleCallbacks(localbg);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    cz.a("registerActivityLifecycleCallbacks encounter exception");
  }
  
  public void a(Context paramContext)
  {
    a(paramContext, false);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\bf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */