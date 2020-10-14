package android.support.transition;

import android.animation.Animator;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class ChangeBounds
  extends Transition
{
  public ChangeBounds()
  {
    super(true);
    if (Build.VERSION.SDK_INT < 19)
    {
      this.mImpl = new ChangeBoundsIcs(this);
      return;
    }
    this.mImpl = new ChangeBoundsKitKat(this);
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
  
  public void setResizeClip(boolean paramBoolean)
  {
    ((ChangeBoundsInterface)this.mImpl).setResizeClip(paramBoolean);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\ChangeBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */