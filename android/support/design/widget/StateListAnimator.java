package android.support.design.widget;

import android.util.StateSet;
import java.util.ArrayList;

final class StateListAnimator
{
  private final ValueAnimatorCompat.AnimatorListener mAnimationListener = new ValueAnimatorCompat.AnimatorListenerAdapter()
  {
    public void onAnimationEnd(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
    {
      if (StateListAnimator.this.mRunningAnimator == paramAnonymousValueAnimatorCompat) {
        StateListAnimator.this.mRunningAnimator = null;
      }
    }
  };
  private Tuple mLastMatch = null;
  ValueAnimatorCompat mRunningAnimator = null;
  private final ArrayList<Tuple> mTuples = new ArrayList();
  
  private void cancel()
  {
    if (this.mRunningAnimator != null)
    {
      this.mRunningAnimator.cancel();
      this.mRunningAnimator = null;
    }
  }
  
  private void start(Tuple paramTuple)
  {
    this.mRunningAnimator = paramTuple.mAnimator;
    this.mRunningAnimator.start();
  }
  
  public void addState(int[] paramArrayOfInt, ValueAnimatorCompat paramValueAnimatorCompat)
  {
    paramArrayOfInt = new Tuple(paramArrayOfInt, paramValueAnimatorCompat);
    paramValueAnimatorCompat.addListener(this.mAnimationListener);
    this.mTuples.add(paramArrayOfInt);
  }
  
  public void jumpToCurrentState()
  {
    if (this.mRunningAnimator != null)
    {
      this.mRunningAnimator.end();
      this.mRunningAnimator = null;
    }
  }
  
  void setState(int[] paramArrayOfInt)
  {
    int j = this.mTuples.size();
    int i = 0;
    Tuple localTuple;
    if (i < j)
    {
      localTuple = (Tuple)this.mTuples.get(i);
      if (!StateSet.stateSetMatches(localTuple.mSpecs, paramArrayOfInt)) {}
    }
    for (paramArrayOfInt = localTuple;; paramArrayOfInt = null)
    {
      if (paramArrayOfInt == this.mLastMatch) {}
      do
      {
        return;
        i += 1;
        break;
        if (this.mLastMatch != null) {
          cancel();
        }
        this.mLastMatch = paramArrayOfInt;
      } while (paramArrayOfInt == null);
      start(paramArrayOfInt);
      return;
    }
  }
  
  static class Tuple
  {
    final ValueAnimatorCompat mAnimator;
    final int[] mSpecs;
    
    Tuple(int[] paramArrayOfInt, ValueAnimatorCompat paramValueAnimatorCompat)
    {
      this.mSpecs = paramArrayOfInt;
      this.mAnimator = paramValueAnimatorCompat;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\StateListAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */