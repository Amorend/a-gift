package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.ArrayList;

class ValueAnimatorCompatImplGingerbread
  extends ValueAnimatorCompat.Impl
{
  private static final int DEFAULT_DURATION = 200;
  private static final int HANDLER_DELAY = 10;
  private static final Handler sHandler = new Handler(Looper.getMainLooper());
  private float mAnimatedFraction;
  private long mDuration = 200L;
  private final float[] mFloatValues = new float[2];
  private final int[] mIntValues = new int[2];
  private Interpolator mInterpolator;
  private boolean mIsRunning;
  private ArrayList<ValueAnimatorCompat.Impl.AnimatorListenerProxy> mListeners;
  private final Runnable mRunnable = new Runnable()
  {
    public void run()
    {
      ValueAnimatorCompatImplGingerbread.this.update();
    }
  };
  private long mStartTime;
  private ArrayList<ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy> mUpdateListeners;
  
  private void dispatchAnimationCancel()
  {
    if (this.mListeners != null)
    {
      int j = this.mListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)this.mListeners.get(i)).onAnimationCancel();
        i += 1;
      }
    }
  }
  
  private void dispatchAnimationEnd()
  {
    if (this.mListeners != null)
    {
      int j = this.mListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)this.mListeners.get(i)).onAnimationEnd();
        i += 1;
      }
    }
  }
  
  private void dispatchAnimationStart()
  {
    if (this.mListeners != null)
    {
      int j = this.mListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ValueAnimatorCompat.Impl.AnimatorListenerProxy)this.mListeners.get(i)).onAnimationStart();
        i += 1;
      }
    }
  }
  
  private void dispatchAnimationUpdate()
  {
    if (this.mUpdateListeners != null)
    {
      int j = this.mUpdateListeners.size();
      int i = 0;
      while (i < j)
      {
        ((ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy)this.mUpdateListeners.get(i)).onAnimationUpdate();
        i += 1;
      }
    }
  }
  
  public void addListener(ValueAnimatorCompat.Impl.AnimatorListenerProxy paramAnimatorListenerProxy)
  {
    if (this.mListeners == null) {
      this.mListeners = new ArrayList();
    }
    this.mListeners.add(paramAnimatorListenerProxy);
  }
  
  public void addUpdateListener(ValueAnimatorCompat.Impl.AnimatorUpdateListenerProxy paramAnimatorUpdateListenerProxy)
  {
    if (this.mUpdateListeners == null) {
      this.mUpdateListeners = new ArrayList();
    }
    this.mUpdateListeners.add(paramAnimatorUpdateListenerProxy);
  }
  
  public void cancel()
  {
    this.mIsRunning = false;
    sHandler.removeCallbacks(this.mRunnable);
    dispatchAnimationCancel();
    dispatchAnimationEnd();
  }
  
  public void end()
  {
    if (this.mIsRunning)
    {
      this.mIsRunning = false;
      sHandler.removeCallbacks(this.mRunnable);
      this.mAnimatedFraction = 1.0F;
      dispatchAnimationUpdate();
      dispatchAnimationEnd();
    }
  }
  
  public float getAnimatedFloatValue()
  {
    return AnimationUtils.lerp(this.mFloatValues[0], this.mFloatValues[1], getAnimatedFraction());
  }
  
  public float getAnimatedFraction()
  {
    return this.mAnimatedFraction;
  }
  
  public int getAnimatedIntValue()
  {
    return AnimationUtils.lerp(this.mIntValues[0], this.mIntValues[1], getAnimatedFraction());
  }
  
  public long getDuration()
  {
    return this.mDuration;
  }
  
  public boolean isRunning()
  {
    return this.mIsRunning;
  }
  
  public void setDuration(long paramLong)
  {
    this.mDuration = paramLong;
  }
  
  public void setFloatValues(float paramFloat1, float paramFloat2)
  {
    this.mFloatValues[0] = paramFloat1;
    this.mFloatValues[1] = paramFloat2;
  }
  
  public void setIntValues(int paramInt1, int paramInt2)
  {
    this.mIntValues[0] = paramInt1;
    this.mIntValues[1] = paramInt2;
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.mInterpolator = paramInterpolator;
  }
  
  public void start()
  {
    if (this.mIsRunning) {
      return;
    }
    if (this.mInterpolator == null) {
      this.mInterpolator = new AccelerateDecelerateInterpolator();
    }
    this.mIsRunning = true;
    this.mAnimatedFraction = 0.0F;
    startInternal();
  }
  
  final void startInternal()
  {
    this.mStartTime = SystemClock.uptimeMillis();
    dispatchAnimationUpdate();
    dispatchAnimationStart();
    sHandler.postDelayed(this.mRunnable, 10L);
  }
  
  final void update()
  {
    if (this.mIsRunning)
    {
      float f2 = MathUtils.constrain((float)(SystemClock.uptimeMillis() - this.mStartTime) / (float)this.mDuration, 0.0F, 1.0F);
      float f1 = f2;
      if (this.mInterpolator != null) {
        f1 = this.mInterpolator.getInterpolation(f2);
      }
      this.mAnimatedFraction = f1;
      dispatchAnimationUpdate();
      if (SystemClock.uptimeMillis() >= this.mStartTime + this.mDuration)
      {
        this.mIsRunning = false;
        dispatchAnimationEnd();
      }
    }
    if (this.mIsRunning) {
      sHandler.postDelayed(this.mRunnable, 10L);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ValueAnimatorCompatImplGingerbread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */