package android.widget.gestures;

import android.view.MotionEvent;

public interface GestureDetector
{
  public abstract boolean isDragging();
  
  public abstract boolean isScaling();
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);
  
  public abstract void setOnGestureListener(OnGestureListener paramOnGestureListener);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\gestures\GestureDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */