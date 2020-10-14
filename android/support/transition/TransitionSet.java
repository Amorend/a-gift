package android.support.transition;

import android.animation.Animator;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class TransitionSet
  extends Transition
{
  public static final int ORDERING_SEQUENTIAL = 1;
  public static final int ORDERING_TOGETHER = 0;
  
  public TransitionSet()
  {
    super(true);
    if (Build.VERSION.SDK_INT < 19)
    {
      this.mImpl = new TransitionSetIcs(this);
      return;
    }
    this.mImpl = new TransitionSetKitKat(this);
  }
  
  @NonNull
  public TransitionSet addTransition(@NonNull Transition paramTransition)
  {
    ((TransitionSetImpl)this.mImpl).addTransition(paramTransition.mImpl);
    return this;
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues)
  {
    this.mImpl.captureEndValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues)
  {
    this.mImpl.captureStartValues(paramTransitionValues);
  }
  
  @Nullable
  public Animator createAnimator(@NonNull ViewGroup paramViewGroup, @NonNull TransitionValues paramTransitionValues1, @NonNull TransitionValues paramTransitionValues2)
  {
    return this.mImpl.createAnimator(paramViewGroup, paramTransitionValues1, paramTransitionValues2);
  }
  
  public int getOrdering()
  {
    return ((TransitionSetImpl)this.mImpl).getOrdering();
  }
  
  @NonNull
  public TransitionSet removeTransition(@NonNull Transition paramTransition)
  {
    ((TransitionSetImpl)this.mImpl).removeTransition(paramTransition.mImpl);
    return this;
  }
  
  @NonNull
  public TransitionSet setOrdering(int paramInt)
  {
    ((TransitionSetImpl)this.mImpl).setOrdering(paramInt);
    return this;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */