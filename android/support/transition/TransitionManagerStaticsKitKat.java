package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi(19)
class TransitionManagerStaticsKitKat
  extends TransitionManagerStaticsImpl
{
  public void beginDelayedTransition(ViewGroup paramViewGroup)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup);
  }
  
  public void beginDelayedTransition(ViewGroup paramViewGroup, TransitionImpl paramTransitionImpl)
  {
    if (paramTransitionImpl == null) {}
    for (paramTransitionImpl = null;; paramTransitionImpl = ((TransitionKitKat)paramTransitionImpl).mTransition)
    {
      TransitionManager.beginDelayedTransition(paramViewGroup, paramTransitionImpl);
      return;
    }
  }
  
  public void go(SceneImpl paramSceneImpl)
  {
    TransitionManager.go(((SceneWrapper)paramSceneImpl).mScene);
  }
  
  public void go(SceneImpl paramSceneImpl, TransitionImpl paramTransitionImpl)
  {
    Scene localScene = ((SceneWrapper)paramSceneImpl).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl = null;; paramSceneImpl = ((TransitionKitKat)paramTransitionImpl).mTransition)
    {
      TransitionManager.go(localScene, paramSceneImpl);
      return;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerStaticsKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */