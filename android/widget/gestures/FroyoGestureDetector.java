package android.widget.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

@TargetApi(8)
public class FroyoGestureDetector
  extends EclairGestureDetector
{
  protected final ScaleGestureDetector mDetector = new ScaleGestureDetector(paramContext, new ScaleGestureDetector.OnScaleGestureListener()
  {
    @Override
    public boolean onScale(ScaleGestureDetector paramAnonymousScaleGestureDetector)
    {
      float f = paramAnonymousScaleGestureDetector.getScaleFactor();
      if ((Float.isNaN(f)) || (Float.isInfinite(f))) {
        return false;
      }
      FroyoGestureDetector.this.mListener.onScale(f, paramAnonymousScaleGestureDetector.getFocusX(), paramAnonymousScaleGestureDetector.getFocusY());
      return true;
    }
    
    @Override
    public boolean onScaleBegin(ScaleGestureDetector paramAnonymousScaleGestureDetector)
    {
      return true;
    }
    
    @Override
    public void onScaleEnd(ScaleGestureDetector paramAnonymousScaleGestureDetector) {}
  });
  
  public FroyoGestureDetector(Context paramContext)
  {
    super(paramContext);
  }
  
  @Override
  public boolean isScaling()
  {
    return this.mDetector.isInProgress();
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    try
    {
      this.mDetector.onTouchEvent(paramMotionEvent);
      boolean bool = super.onTouchEvent(paramMotionEvent);
      return bool;
    }
    catch (IllegalArgumentException paramMotionEvent) {}
    return true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\gestures\FroyoGestureDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */