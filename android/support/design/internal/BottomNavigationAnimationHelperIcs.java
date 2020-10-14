package android.support.design.internal;

import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.ViewGroup;

class BottomNavigationAnimationHelperIcs
  extends BottomNavigationAnimationHelperBase
{
  private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
  private final TransitionSet mSet = new AutoTransition();
  
  BottomNavigationAnimationHelperIcs()
  {
    this.mSet.setOrdering(0);
    this.mSet.setDuration(115L);
    this.mSet.setInterpolator(new FastOutSlowInInterpolator());
    TextScale localTextScale = new TextScale();
    this.mSet.addTransition(localTextScale);
  }
  
  void beginDelayedTransition(ViewGroup paramViewGroup)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, this.mSet);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\internal\BottomNavigationAnimationHelperIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */