package android.support.transition;

import android.animation.Animator;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

public abstract class Visibility
  extends Transition
  implements VisibilityInterface
{
  public Visibility()
  {
    this(false);
  }
  
  Visibility(boolean paramBoolean)
  {
    super(true);
    if (!paramBoolean) {
      if (Build.VERSION.SDK_INT < 19) {
        break label37;
      }
    }
    label37:
    for (this.mImpl = new VisibilityKitKat();; this.mImpl = new VisibilityIcs())
    {
      this.mImpl.init(this);
      return;
    }
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues)
  {
    this.mImpl.captureEndValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues)
  {
    this.mImpl.captureStartValues(paramTransitionValues);
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    return ((VisibilityImpl)this.mImpl).isVisible(paramTransitionValues);
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((VisibilityImpl)this.mImpl).onAppear(paramViewGroup, paramTransitionValues1, paramInt1, paramTransitionValues2, paramInt2);
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((VisibilityImpl)this.mImpl).onDisappear(paramViewGroup, paramTransitionValues1, paramInt1, paramTransitionValues2, paramInt2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\Visibility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */