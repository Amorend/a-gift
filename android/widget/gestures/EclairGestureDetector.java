package android.widget.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.widget.Compat;

@TargetApi(5)
public class EclairGestureDetector
  extends CupcakeGestureDetector
{
  private static final int INVALID_POINTER_ID = -1;
  private int mActivePointerId = -1;
  private int mActivePointerIndex = 0;
  
  public EclairGestureDetector(Context paramContext)
  {
    super(paramContext);
  }
  
  @Override
  float getActiveX(MotionEvent paramMotionEvent)
  {
    try
    {
      float f = paramMotionEvent.getX(this.mActivePointerIndex);
      return f;
    }
    catch (Exception localException) {}
    return paramMotionEvent.getX();
  }
  
  @Override
  float getActiveY(MotionEvent paramMotionEvent)
  {
    try
    {
      float f = paramMotionEvent.getY(this.mActivePointerIndex);
      return f;
    }
    catch (Exception localException) {}
    return paramMotionEvent.getY();
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    case 2: 
    case 4: 
    case 5: 
    default: 
      if (this.mActivePointerId == -1) {
        break;
      }
    }
    for (int i = this.mActivePointerId;; i = 0)
    {
      this.mActivePointerIndex = paramMotionEvent.findPointerIndex(i);
      try
      {
        boolean bool = super.onTouchEvent(paramMotionEvent);
        return bool;
      }
      catch (IllegalArgumentException paramMotionEvent) {}
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      break;
      this.mActivePointerId = -1;
      break;
      i = Compat.getPointerIndex(paramMotionEvent.getAction());
      if (paramMotionEvent.getPointerId(i) == this.mActivePointerId) {
        if (i != 0) {
          break label158;
        }
      }
      label158:
      for (i = 1;; i = 0)
      {
        this.mActivePointerId = paramMotionEvent.getPointerId(i);
        this.mLastTouchX = paramMotionEvent.getX(i);
        this.mLastTouchY = paramMotionEvent.getY(i);
        break;
      }
    }
    return true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\gestures\EclairGestureDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */