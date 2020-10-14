package android.support.v4.view;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;

@TargetApi(12)
@RequiresApi(12)
class MotionEventCompatHoneycombMr1
{
  static float getAxisValue(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getAxisValue(paramInt);
  }
  
  static float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
  {
    return paramMotionEvent.getAxisValue(paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\view\MotionEventCompatHoneycombMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */