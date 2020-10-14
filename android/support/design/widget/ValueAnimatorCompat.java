package android.support.design.widget;

import android.support.annotation.NonNull;
import android.view.animation.Interpolator;

class ValueAnimatorCompat
{
  private final Impl mImpl;
  
  ValueAnimatorCompat(Impl paramImpl)
  {
    this.mImpl = paramImpl;
  }
  
  public void addListener(final AnimatorListener paramAnimatorListener)
  {
    if (paramAnimatorListener != null)
    {
      this.mImpl.addListener(new ValueAnimatorCompat.Impl.AnimatorListenerProxy()
      {
        public void onAnimationCancel()
        {
          paramAnimatorListener.onAnimationCancel(ValueAnimatorCompat.this);
        }
        
        public void onAnimationEnd()
        {
          paramAnimatorListener.onAnimationEnd(ValueAnimatorCompat.this);
        }
        
        public void onAnimationStart()
        {
          paramAnimatorListener.onAnimationStart(ValueAnimatorCompat.this);
        }
      });
      return;
    }
    this.mImpl.addListener(null);
  }
  
  public void addUpdateListener(final AnimatorUpdateListener paramAnimatorUpdateListener)
  {
    if (paramAnimatorUpdateListener != null)
    {
      this.mImpl.addUpdateListener(new ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy()
      {
        public void onAnimationUpdate()
        {
          paramAnimatorUpdateListener.onAnimationUpdate(ValueAnimatorCompat.this);
        }
      });
      return;
    }
    this.mImpl.addUpdateListener(null);
  }
  
  public void cancel()
  {
    this.mImpl.cancel();
  }
  
  public void end()
  {
    this.mImpl.end();
  }
  
  public float getAnimatedFloatValue()
  {
    return this.mImpl.getAnimatedFloatValue();
  }
  
  public float getAnimatedFraction()
  {
    return this.mImpl.getAnimatedFraction();
  }
  
  public int getAnimatedIntValue()
  {
    return this.mImpl.getAnimatedIntValue();
  }
  
  public long getDuration()
  {
    return this.mImpl.getDuration();
  }
  
  public boolean isRunning()
  {
    return this.mImpl.isRunning();
  }
  
  public void setDuration(long paramLong)
  {
    this.mImpl.setDuration(paramLong);
  }
  
  public void setFloatValues(float paramFloat1, float paramFloat2)
  {
    this.mImpl.setFloatValues(paramFloat1, paramFloat2);
  }
  
  public void setIntValues(int paramInt1, int paramInt2)
  {
    this.mImpl.setIntValues(paramInt1, paramInt2);
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mImpl.setInterpolator(paramInterpolator);
  }
  
  public void start()
  {
    this.mImpl.start();
  }
  
  static abstract interface AnimatorListener
  {
    public abstract void onAnimationCancel(ValueAnimatorCompat paramValueAnimatorCompat);
    
    public abstract void onAnimationEnd(ValueAnimatorCompat paramValueAnimatorCompat);
    
    public abstract void onAnimationStart(ValueAnimatorCompat paramValueAnimatorCompat);
  }
  
  static class AnimatorListenerAdapter
    implements ValueAnimatorCompat.AnimatorListener
  {
    public void onAnimationCancel(ValueAnimatorCompat paramValueAnimatorCompat) {}
    
    public void onAnimationEnd(ValueAnimatorCompat paramValueAnimatorCompat) {}
    
    public void onAnimationStart(ValueAnimatorCompat paramValueAnimatorCompat) {}
  }
  
  static abstract interface AnimatorUpdateListener
  {
    public abstract void onAnimationUpdate(ValueAnimatorCompat paramValueAnimatorCompat);
  }
  
  static abstract interface Creator
  {
    @NonNull
    public abstract ValueAnimatorCompat createAnimator();
  }
  
  static abstract class Impl
  {
    abstract void addListener(AnimatorListenerProxy paramAnimatorListenerProxy);
    
    abstract void addUpdateListener(AnimatorUpdateListenerProxy paramAnimatorUpdateListenerProxy);
    
    abstract void cancel();
    
    abstract void end();
    
    abstract float getAnimatedFloatValue();
    
    abstract float getAnimatedFraction();
    
    abstract int getAnimatedIntValue();
    
    abstract long getDuration();
    
    abstract boolean isRunning();
    
    abstract void setDuration(long paramLong);
    
    abstract void setFloatValues(float paramFloat1, float paramFloat2);
    
    abstract void setIntValues(int paramInt1, int paramInt2);
    
    abstract void setInterpolator(Interpolator paramInterpolator);
    
    abstract void start();
    
    static abstract interface AnimatorListenerProxy
    {
      public abstract void onAnimationCancel();
      
      public abstract void onAnimationEnd();
      
      public abstract void onAnimationStart();
    }
    
    static abstract interface AnimatorUpdateListenerProxy
    {
      public abstract void onAnimationUpdate();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ValueAnimatorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */