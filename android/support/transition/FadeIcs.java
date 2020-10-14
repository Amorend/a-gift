package android.support.transition;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class FadeIcs
  extends TransitionIcs
  implements VisibilityImpl
{
  public FadeIcs(TransitionInterface paramTransitionInterface)
  {
    init(paramTransitionInterface, new FadePort());
  }
  
  public FadeIcs(TransitionInterface paramTransitionInterface, int paramInt)
  {
    init(paramTransitionInterface, new FadePort(paramInt));
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    return ((FadePort)this.mTransition).isVisible(paramTransitionValues);
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((FadePort)this.mTransition).onAppear(paramViewGroup, paramTransitionValues1, paramInt1, paramTransitionValues2, paramInt2);
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((FadePort)this.mTransition).onDisappear(paramViewGroup, paramTransitionValues1, paramInt1, paramTransitionValues1, paramInt1);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\FadeIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */