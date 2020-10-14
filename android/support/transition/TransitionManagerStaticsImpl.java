package android.support.transition;

import android.view.ViewGroup;

abstract class TransitionManagerStaticsImpl
{
  public abstract void beginDelayedTransition(ViewGroup paramViewGroup);
  
  public abstract void beginDelayedTransition(ViewGroup paramViewGroup, TransitionImpl paramTransitionImpl);
  
  public abstract void go(SceneImpl paramSceneImpl);
  
  public abstract void go(SceneImpl paramSceneImpl, TransitionImpl paramTransitionImpl);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerStaticsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */