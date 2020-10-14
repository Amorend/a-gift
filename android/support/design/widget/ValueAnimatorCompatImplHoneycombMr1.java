package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.animation.Interpolator;

@TargetApi(12)
@RequiresApi(12)
class ValueAnimatorCompatImplHoneycombMr1
  extends ValueAnimatorCompat.Impl
{
  private final ValueAnimator mValueAnimator = new ValueAnimator();
  
  public void addListener(final ValueAnimatorCompat.Impl.AnimatorListenerProxy paramAnimatorListenerProxy)
  {
    this.mValueAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationCancel();
      }
      
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationEnd();
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        paramAnimatorListenerProxy.onAnimationStart();
      }
    });
  }
  
  public void addUpdateListener(final ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy paramAnimatorUpdateListenerProxy)
  {
    this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        paramAnimatorUpdateListenerProxy.onAnimationUpdate();
      }
    });
  }
  
  public void cancel()
  {
    this.mValueAnimator.cancel();
  }
  
  public void end()
  {
    this.mValueAnimator.end();
  }
  
  public float getAnimatedFloatValue()
  {
    return ((Float)this.mValueAnimator.getAnimatedValue()).floatValue();
  }
  
  public float getAnimatedFraction()
  {
    return this.mValueAnimator.getAnimatedFraction();
  }
  
  public int getAnimatedIntValue()
  {
    return ((Integer)this.mValueAnimator.getAnimatedValue()).intValue();
  }
  
  public long getDuration()
  {
    return this.mValueAnimator.getDuration();
  }
  
  public boolean isRunning()
  {
    return this.mValueAnimator.isRunning();
  }
  
  public void setDuration(long paramLong)
  {
    this.mValueAnimator.setDuration(paramLong);
  }
  
  public void setFloatValues(float paramFloat1, float paramFloat2)
  {
    this.mValueAnimator.setFloatValues(new float[] { paramFloat1, paramFloat2 });
  }
  
  public void setIntValues(int paramInt1, int paramInt2)
  {
    this.mValueAnimator.setIntValues(new int[] { paramInt1, paramInt2 });
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mValueAnimator.setInterpolator(paramInterpolator);
  }
  
  public void start()
  {
    this.mValueAnimator.start();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ValueAnimatorCompatImplHoneycombMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */