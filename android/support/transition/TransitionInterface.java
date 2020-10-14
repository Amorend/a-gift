package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

abstract interface TransitionInterface
{
  public abstract void captureEndValues(TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(TransitionValues paramTransitionValues);
  
  public abstract Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */