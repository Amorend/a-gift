package android.support.transition;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi(19)
class FadeKitKat
  extends TransitionKitKat
  implements VisibilityImpl
{
  public FadeKitKat(TransitionInterface paramTransitionInterface)
  {
    init(paramTransitionInterface, new Fade());
  }
  
  public FadeKitKat(TransitionInterface paramTransitionInterface, int paramInt)
  {
    init(paramTransitionInterface, new Fade(paramInt));
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    return ((Fade)this.mTransition).isVisible(convertToPlatform(paramTransitionValues));
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((Fade)this.mTransition).onAppear(paramViewGroup, convertToPlatform(paramTransitionValues1), paramInt1, convertToPlatform(paramTransitionValues2), paramInt2);
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((Fade)this.mTransition).onDisappear(paramViewGroup, convertToPlatform(paramTransitionValues1), paramInt1, convertToPlatform(paramTransitionValues2), paramInt2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\FadeKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */