package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.transition.TransitionManager;

@TargetApi(19)
@RequiresApi(19)
class TransitionManagerKitKat
  extends TransitionManagerImpl
{
  private final TransitionManager mTransitionManager = new TransitionManager();
  
  public void setTransition(SceneImpl paramSceneImpl1, SceneImpl paramSceneImpl2, TransitionImpl paramTransitionImpl)
  {
    TransitionManager localTransitionManager = this.mTransitionManager;
    Scene localScene = ((SceneWrapper)paramSceneImpl1).mScene;
    paramSceneImpl2 = ((SceneWrapper)paramSceneImpl2).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl1 = null;; paramSceneImpl1 = ((TransitionKitKat)paramTransitionImpl).mTransition)
    {
      localTransitionManager.setTransition(localScene, paramSceneImpl2, paramSceneImpl1);
      return;
    }
  }
  
  public void setTransition(SceneImpl paramSceneImpl, TransitionImpl paramTransitionImpl)
  {
    TransitionManager localTransitionManager = this.mTransitionManager;
    Scene localScene = ((SceneWrapper)paramSceneImpl).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl = null;; paramSceneImpl = ((TransitionKitKat)paramTransitionImpl).mTransition)
    {
      localTransitionManager.setTransition(localScene, paramSceneImpl);
      return;
    }
  }
  
  public void transitionTo(SceneImpl paramSceneImpl)
  {
    this.mTransitionManager.transitionTo(((SceneWrapper)paramSceneImpl).mScene);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */