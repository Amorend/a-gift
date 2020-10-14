package android.widget.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.log.LogManager;
import android.widget.log.Logger;

public class CupcakeGestureDetector
  implements GestureDetector
{
  private static final String LOG_TAG = "CupcakeGestureDetector";
  private boolean mIsDragging;
  float mLastTouchX;
  float mLastTouchY;
  protected OnGestureListener mListener;
  final float mMinimumVelocity;
  final float mTouchSlop;
  private VelocityTracker mVelocityTracker;
  
  public CupcakeGestureDetector(Context paramContext)
  {
    paramContext = ViewConfiguration.get(paramContext);
    this.mMinimumVelocity = paramContext.getScaledMinimumFlingVelocity();
    this.mTouchSlop = paramContext.getScaledTouchSlop();
  }
  
  float getActiveX(MotionEvent paramMotionEvent)
  {
    return paramMotionEvent.getX();
  }
  
  float getActiveY(MotionEvent paramMotionEvent)
  {
    return paramMotionEvent.getY();
  }
  
  @Override
  public boolean isDragging()
  {
    return this.mIsDragging;
  }
  
  @Override
  public boolean isScaling()
  {
    return false;
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      this.mVelocityTracker = VelocityTracker.obtain();
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.addMovement(paramMotionEvent);
      }
      for (;;)
      {
        this.mLastTouchX = getActiveX(paramMotionEvent);
        this.mLastTouchY = getActiveY(paramMotionEvent);
        this.mIsDragging = false;
        break;
        LogManager.getLogger().i("CupcakeGestureDetector", "Velocity tracker is null");
      }
      float f1 = getActiveX(paramMotionEvent);
      float f2 = getActiveY(paramMotionEvent);
      float f3 = f1 - this.mLastTouchX;
      float f4 = f2 - this.mLastTouchY;
      if (!this.mIsDragging) {
        if (Math.sqrt(f3 * f3 + f4 * f4) >= this.mTouchSlop) {
          break label218;
        }
      }
      label218:
      for (boolean bool = false;; bool = true)
      {
        this.mIsDragging = bool;
        if (this.mIsDragging)
        {
          this.mListener.onDrag(f3, f4);
          this.mLastTouchX = f1;
          this.mLastTouchY = f2;
          if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(paramMotionEvent);
          }
        }
        break;
      }
      if (this.mVelocityTracker != null)
      {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = ((VelocityTracker)null);
      }
      continue;
      if ((this.mIsDragging) && (this.mVelocityTracker != null))
      {
        this.mLastTouchX = getActiveX(paramMotionEvent);
        this.mLastTouchY = getActiveY(paramMotionEvent);
        this.mVelocityTracker.addMovement(paramMotionEvent);
        this.mVelocityTracker.computeCurrentVelocity(1000);
        f1 = this.mVelocityTracker.getXVelocity();
        f2 = this.mVelocityTracker.getYVelocity();
        if (Math.max(Math.abs(f1), Math.abs(f2)) >= this.mMinimumVelocity) {
          this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -f1, -f2);
        }
      }
      if (this.mVelocityTracker != null)
      {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = ((VelocityTracker)null);
      }
    }
  }
  
  @Override
  public void setOnGestureListener(OnGestureListener paramOnGestureListener)
  {
    this.mListener = paramOnGestureListener;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\gestures\CupcakeGestureDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */