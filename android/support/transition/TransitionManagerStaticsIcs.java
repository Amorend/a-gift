package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class TransitionManagerStaticsIcs
  extends TransitionManagerStaticsImpl
{
  public void beginDelayedTransition(ViewGroup paramViewGroup)
  {
    TransitionManagerPort.beginDelayedTransition(paramViewGroup);
  }
  
  public void beginDelayedTransition(ViewGroup paramViewGroup, TransitionImpl paramTransitionImpl)
  {
    if (paramTransitionImpl == null) {}
    for (paramTransitionImpl = null;; paramTransitionImpl = ((TransitionIcs)paramTransitionImpl).mTransition)
    {
      TransitionManagerPort.beginDelayedTransition(paramViewGroup, paramTransitionImpl);
      return;
    }
  }
  
  public void go(SceneImpl paramSceneImpl)
  {
    TransitionManagerPort.go(((SceneIcs)paramSceneImpl).mScene);
  }
  
  public void go(SceneImpl paramSceneImpl, TransitionImpl paramTransitionImpl)
  {
    ScenePort localScenePort = ((SceneIcs)paramSceneImpl).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl = null;; paramSceneImpl = ((TransitionIcs)paramTransitionImpl).mTransition)
    {
      TransitionManagerPort.go(localScenePort, paramSceneImpl);
      return;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerStaticsIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */