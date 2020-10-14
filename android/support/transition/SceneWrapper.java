package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi(19)
abstract class SceneWrapper
  extends SceneImpl
{
  Scene mScene;
  
  public void exit()
  {
    this.mScene.exit();
  }
  
  public ViewGroup getSceneRoot()
  {
    return this.mScene.getSceneRoot();
  }
  
  public void setEnterAction(Runnable paramRunnable)
  {
    this.mScene.setEnterAction(paramRunnable);
  }
  
  public void setExitAction(Runnable paramRunnable)
  {
    this.mScene.setExitAction(paramRunnable);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\SceneWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */