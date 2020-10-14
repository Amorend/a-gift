package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewParent;

@TargetApi(19)
@RequiresApi(19)
class ViewParentCompatKitKat
{
  public static void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    paramViewParent.notifySubtreeAccessibilityStateChanged(paramView1, paramView2, paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\view\ViewParentCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */