package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(21)
@RequiresApi(21)
class SceneApi21
  extends SceneWrapper
{
  public void enter()
  {
    this.mScene.enter();
  }
  
  public void init(ViewGroup paramViewGroup)
  {
    this.mScene = new Scene(paramViewGroup);
  }
  
  public void init(ViewGroup paramViewGroup, View paramView)
  {
    this.mScene = new Scene(paramViewGroup, paramView);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\SceneApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */