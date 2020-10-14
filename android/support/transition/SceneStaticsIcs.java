package android.support.transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@TargetApi(14)
@RequiresApi(14)
class SceneStaticsIcs
  extends SceneStaticsImpl
{
  public SceneImpl getSceneForLayout(ViewGroup paramViewGroup, int paramInt, Context paramContext)
  {
    SceneIcs localSceneIcs = new SceneIcs();
    localSceneIcs.mScene = ScenePort.getSceneForLayout(paramViewGroup, paramInt, paramContext);
    return localSceneIcs;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\SceneStaticsIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */