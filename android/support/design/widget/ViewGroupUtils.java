package android.support.design.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

class ViewGroupUtils
{
  private static final ViewGroupUtilsImpl IMPL = new ViewGroupUtilsImplBase();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      IMPL = new ViewGroupUtilsImplHoneycomb();
      return;
    }
  }
  
  static void getDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
  {
    paramRect.set(0, 0, paramView.getWidth(), paramView.getHeight());
    offsetDescendantRect(paramViewGroup, paramView, paramRect);
  }
  
  static void offsetDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
  {
    IMPL.offsetDescendantRect(paramViewGroup, paramView, paramRect);
  }
  
  private static abstract interface ViewGroupUtilsImpl
  {
    public abstract void offsetDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect);
  }
  
  private static class ViewGroupUtilsImplBase
    implements ViewGroupUtils.ViewGroupUtilsImpl
  {
    public void offsetDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
    {
      paramViewGroup.offsetDescendantRectToMyCoords(paramView, paramRect);
      paramRect.offset(paramView.getScrollX(), paramView.getScrollY());
    }
  }
  
  private static class ViewGroupUtilsImplHoneycomb
    implements ViewGroupUtils.ViewGroupUtilsImpl
  {
    public void offsetDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
    {
      ViewGroupUtilsHoneycomb.offsetDescendantRect(paramViewGroup, paramView, paramRect);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ViewGroupUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */