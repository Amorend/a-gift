package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class SceneIcs
  extends SceneImpl
{
  ScenePort mScene;
  
  public void enter()
  {
    this.mScene.enter();
  }
  
  public void exit()
  {
    this.mScene.exit();
  }
  
  public ViewGroup getSceneRoot()
  {
    return this.mScene.getSceneRoot();
  }
  
  public void init(ViewGroup paramViewGroup)
  {
    this.mScene = new ScenePort(paramViewGroup);
  }
  
  public void init(ViewGroup paramViewGroup, View paramView)
  {
    this.mScene = new ScenePort(paramViewGroup, paramView);
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


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\SceneIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */