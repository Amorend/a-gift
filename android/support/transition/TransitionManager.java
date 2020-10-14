package android.support.transition;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class TransitionManager
{
  private static TransitionManagerStaticsImpl sImpl = new TransitionManagerStaticsKitKat();
  private TransitionManagerImpl mImpl;
  
  static
  {
    if (Build.VERSION.SDK_INT < 19)
    {
      sImpl = new TransitionManagerStaticsIcs();
      return;
    }
  }
  
  public TransitionManager()
  {
    if (Build.VERSION.SDK_INT < 19)
    {
      this.mImpl = new TransitionManagerIcs();
      return;
    }
    this.mImpl = new TransitionManagerKitKat();
  }
  
  public static void beginDelayedTransition(@NonNull ViewGroup paramViewGroup)
  {
    sImpl.beginDelayedTransition(paramViewGroup);
  }
  
  public static void beginDelayedTransition(@NonNull ViewGroup paramViewGroup, @Nullable Transition paramTransition)
  {
    TransitionManagerStaticsImpl localTransitionManagerStaticsImpl = sImpl;
    if (paramTransition == null) {}
    for (paramTransition = null;; paramTransition = paramTransition.mImpl)
    {
      localTransitionManagerStaticsImpl.beginDelayedTransition(paramViewGroup, paramTransition);
      return;
    }
  }
  
  public static void go(@NonNull Scene paramScene)
  {
    sImpl.go(paramScene.mImpl);
  }
  
  public static void go(@NonNull Scene paramScene, @Nullable Transition paramTransition)
  {
    TransitionManagerStaticsImpl localTransitionManagerStaticsImpl = sImpl;
    SceneImpl localSceneImpl = paramScene.mImpl;
    if (paramTransition == null) {}
    for (paramScene = null;; paramScene = paramTransition.mImpl)
    {
      localTransitionManagerStaticsImpl.go(localSceneImpl, paramScene);
      return;
    }
  }
  
  public void setTransition(@NonNull Scene paramScene1, @NonNull Scene paramScene2, @Nullable Transition paramTransition)
  {
    TransitionManagerImpl localTransitionManagerImpl = this.mImpl;
    SceneImpl localSceneImpl = paramScene1.mImpl;
    paramScene2 = paramScene2.mImpl;
    if (paramTransition == null) {}
    for (paramScene1 = null;; paramScene1 = paramTransition.mImpl)
    {
      localTransitionManagerImpl.setTransition(localSceneImpl, paramScene2, paramScene1);
      return;
    }
  }
  
  public void setTransition(@NonNull Scene paramScene, @Nullable Transition paramTransition)
  {
    TransitionManagerImpl localTransitionManagerImpl = this.mImpl;
    SceneImpl localSceneImpl = paramScene.mImpl;
    if (paramTransition == null) {}
    for (paramScene = null;; paramScene = paramTransition.mImpl)
    {
      localTransitionManagerImpl.setTransition(localSceneImpl, paramScene);
      return;
    }
  }
  
  public void transitionTo(@NonNull Scene paramScene)
  {
    this.mImpl.transitionTo(paramScene.mImpl);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */