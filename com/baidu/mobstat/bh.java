package com.baidu.mobstat;

import android.app.Activity;
import android.view.View;
import android.view.View.AccessibilityDelegate;

class bh
  extends View.AccessibilityDelegate
{
  private View.AccessibilityDelegate b;
  private Activity c;
  private View d;
  private String e;
  
  public bh(bf parambf, Activity paramActivity, View paramView, String paramString, View.AccessibilityDelegate paramAccessibilityDelegate)
  {
    this.b = paramAccessibilityDelegate;
    this.c = paramActivity;
    this.d = paramView;
    this.e = paramString;
  }
  
  public void sendAccessibilityEvent(View paramView, int paramInt)
  {
    if ((paramView == this.d) && (paramInt == 1)) {
      bf.a(this.a, this.c, this.d, this.e);
    }
    if ((this.b != null) && (!(this.b instanceof bh))) {
      this.b.sendAccessibilityEvent(paramView, paramInt);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\bh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */