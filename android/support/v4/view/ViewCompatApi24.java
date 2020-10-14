package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.PointerIcon;
import android.view.View;

@TargetApi(24)
@RequiresApi(24)
class ViewCompatApi24
{
  public static void setPointerIcon(View paramView, Object paramObject)
  {
    paramView.setPointerIcon((PointerIcon)paramObject);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\view\ViewCompatApi24.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */