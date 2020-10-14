package android.support.transition;

import android.animation.Animator;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class Fade
  extends Visibility
{
  public static final int IN = 1;
  public static final int OUT = 2;
  
  public Fade()
  {
    this(-1);
  }
  
  public Fade(int paramInt)
  {
    super(true);
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (paramInt > 0)
      {
        this.mImpl = new FadeKitKat(this, paramInt);
        return;
      }
      this.mImpl = new FadeKitKat(this);
      return;
    }
    if (paramInt > 0)
    {
      this.mImpl = new FadeIcs(this, paramInt);
      return;
    }
    this.mImpl = new FadeIcs(this);
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
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\Fade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */