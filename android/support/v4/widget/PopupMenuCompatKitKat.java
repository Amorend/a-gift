package android.support.v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View.OnTouchListener;
import android.widget.PopupMenu;

@TargetApi(19)
@RequiresApi(19)
class PopupMenuCompatKitKat
{
  public static View.OnTouchListener getDragToOpenListener(Object paramObject)
  {
    return ((PopupMenu)paramObject).getDragToOpenListener();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\widget\PopupMenuCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */