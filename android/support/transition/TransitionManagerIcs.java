package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;

@TargetApi(14)
@RequiresApi(14)
class TransitionManagerIcs
  extends TransitionManagerImpl
{
  private final TransitionManagerPort mTransitionManager = new TransitionManagerPort();
  
  public void setTransition(SceneImpl paramSceneImpl1, SceneImpl paramSceneImpl2, TransitionImpl paramTransitionImpl)
  {
    TransitionManagerPort localTransitionManagerPort = this.mTransitionManager;
    ScenePort localScenePort = ((SceneIcs)paramSceneImpl1).mScene;
    paramSceneImpl2 = ((SceneIcs)paramSceneImpl2).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl1 = null;; paramSceneImpl1 = ((TransitionIcs)paramTransitionImpl).mTransition)
    {
      localTransitionManagerPort.setTransition(localScenePort, paramSceneImpl2, paramSceneImpl1);
      return;
    }
  }
  
  public void setTransition(SceneImpl paramSceneImpl, TransitionImpl paramTransitionImpl)
  {
    TransitionManagerPort localTransitionManagerPort = this.mTransitionManager;
    ScenePort localScenePort = ((SceneIcs)paramSceneImpl).mScene;
    if (paramTransitionImpl == null) {}
    for (paramSceneImpl = null;; paramSceneImpl = ((TransitionIcs)paramTransitionImpl).mTransition)
    {
      localTransitionManagerPort.setTransition(localScenePort, paramSceneImpl);
      return;
    }
  }
  
  public void transitionTo(SceneImpl paramSceneImpl)
  {
    this.mTransitionManager.transitionTo(((SceneIcs)paramSceneImpl).mScene);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */