package android.support.transition;

import android.animation.Animator;
import android.view.ViewGroup;

abstract interface VisibilityInterface
  extends TransitionInterface
{
  public abstract boolean isVisible(TransitionValues paramTransitionValues);
  
  public abstract Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2);
  
  public abstract Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\VisibilityInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */