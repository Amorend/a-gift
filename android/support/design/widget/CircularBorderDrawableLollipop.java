package android.support.design.widget;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.support.annotation.RequiresApi;

@TargetApi(21)
@RequiresApi(21)
class CircularBorderDrawableLollipop
  extends CircularBorderDrawable
{
  public void getOutline(Outline paramOutline)
  {
    copyBounds(this.mRect);
    paramOutline.setOval(this.mRect);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\CircularBorderDrawableLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */