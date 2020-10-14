package android.support.v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.widget.PopupWindow;

@TargetApi(23)
@RequiresApi(23)
class PopupWindowCompatApi23
{
  static boolean getOverlapAnchor(PopupWindow paramPopupWindow)
  {
    return paramPopupWindow.getOverlapAnchor();
  }
  
  static int getWindowLayoutType(PopupWindow paramPopupWindow)
  {
    return paramPopupWindow.getWindowLayoutType();
  }
  
  static void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    paramPopupWindow.setOverlapAnchor(paramBoolean);
  }
  
  static void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
  {
    paramPopupWindow.setWindowLayoutType(paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\widget\PopupWindowCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */