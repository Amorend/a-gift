package android.support.transition;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Map;

@TargetApi(14)
@RequiresApi(14)
abstract class VisibilityPort
  extends TransitionPort
{
  private static final String PROPNAME_PARENT = "android:visibility:parent";
  private static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
  private static final String[] sTransitionProperties = { "android:visibility:visibility", "android:visibility:parent" };
  
  private void captureValues(TransitionValues paramTransitionValues)
  {
    int i = paramTransitionValues.view.getVisibility();
    paramTransitionValues.values.put("android:visibility:visibility", Integer.valueOf(i));
    paramTransitionValues.values.put("android:visibility:parent", paramTransitionValues.view.getParent());
  }
  
  private VisibilityInfo getVisibilityChangeInfo(TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    VisibilityInfo localVisibilityInfo = new VisibilityInfo();
    localVisibilityInfo.visibilityChange = false;
    localVisibilityInfo.fadeIn = false;
    if (paramTransitionValues1 != null)
    {
      localVisibilityInfo.startVisibility = ((Integer)paramTransitionValues1.values.get("android:visibility:visibility")).intValue();
      localVisibilityInfo.startParent = ((ViewGroup)paramTransitionValues1.values.get("android:visibility:parent"));
      if (paramTransitionValues2 == null) {
        break label149;
      }
      localVisibilityInfo.endVisibility = ((Integer)paramTransitionValues2.values.get("android:visibility:visibility")).intValue();
      localVisibilityInfo.endParent = ((ViewGroup)paramTransitionValues2.values.get("android:visibility:parent"));
    }
    for (;;)
    {
      if ((paramTransitionValues1 != null) && (paramTransitionValues2 != null))
      {
        if ((localVisibilityInfo.startVisibility == localVisibilityInfo.endVisibility) && (localVisibilityInfo.startParent == localVisibilityInfo.endParent))
        {
          return localVisibilityInfo;
          localVisibilityInfo.startVisibility = -1;
          localVisibilityInfo.startParent = null;
          break;
          label149:
          localVisibilityInfo.endVisibility = -1;
          localVisibilityInfo.endParent = null;
          continue;
        }
        if (localVisibilityInfo.startVisibility == localVisibilityInfo.endVisibility) {
          break label226;
        }
        if (localVisibilityInfo.startVisibility != 0) {
          break label206;
        }
        localVisibilityInfo.fadeIn = false;
        localVisibilityInfo.visibilityChange = true;
      }
    }
    if (paramTransitionValues1 == null)
    {
      localVisibilityInfo.fadeIn = true;
      localVisibilityInfo.visibilityChange = true;
    }
    for (;;)
    {
      return localVisibilityInfo;
      label206:
      if (localVisibilityInfo.endVisibility != 0) {
        break;
      }
      localVisibilityInfo.fadeIn = true;
      localVisibilityInfo.visibilityChange = true;
      break;
      label226:
      if (localVisibilityInfo.startParent == localVisibilityInfo.endParent) {
        break;
      }
      if (localVisibilityInfo.endParent == null)
      {
        localVisibilityInfo.fadeIn = false;
        localVisibilityInfo.visibilityChange = true;
        break;
      }
      if (localVisibilityInfo.startParent != null) {
        break;
      }
      localVisibilityInfo.fadeIn = true;
      localVisibilityInfo.visibilityChange = true;
      break;
      if (paramTransitionValues2 == null)
      {
        localVisibilityInfo.fadeIn = false;
        localVisibilityInfo.visibilityChange = true;
      }
    }
  }
  
  public void captureEndValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    int i = 0;
    int j = -1;
    VisibilityInfo localVisibilityInfo = getVisibilityChangeInfo(paramTransitionValues1, paramTransitionValues2);
    if (localVisibilityInfo.visibilityChange)
    {
      View localView1;
      View localView2;
      if ((this.mTargets.size() > 0) || (this.mTargetIds.size() > 0))
      {
        if (paramTransitionValues1 == null) {
          break label160;
        }
        localView1 = paramTransitionValues1.view;
        if (paramTransitionValues2 == null) {
          break label166;
        }
        localView2 = paramTransitionValues2.view;
        label62:
        if (localView1 == null) {
          break label172;
        }
        i = localView1.getId();
        label74:
        if (localView2 != null) {
          j = localView2.getId();
        }
        if ((!isValidTarget(localView1, i)) && (!isValidTarget(localView2, j))) {
          break label178;
        }
        i = 1;
      }
      for (;;)
      {
        if ((i != 0) || (localVisibilityInfo.startParent != null) || (localVisibilityInfo.endParent != null))
        {
          if (localVisibilityInfo.fadeIn)
          {
            return onAppear(paramViewGroup, paramTransitionValues1, localVisibilityInfo.startVisibility, paramTransitionValues2, localVisibilityInfo.endVisibility);
            label160:
            localView1 = null;
            break;
            label166:
            localView2 = null;
            break label62;
            label172:
            i = -1;
            break label74;
            label178:
            i = 0;
            continue;
          }
          return onDisappear(paramViewGroup, paramTransitionValues1, localVisibilityInfo.startVisibility, paramTransitionValues2, localVisibilityInfo.endVisibility);
        }
      }
    }
    return null;
  }
  
  public String[] getTransitionProperties()
  {
    return sTransitionProperties;
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    if (paramTransitionValues == null) {
      return false;
    }
    int i = ((Integer)paramTransitionValues.values.get("android:visibility:visibility")).intValue();
    paramTransitionValues = (View)paramTransitionValues.values.get("android:visibility:parent");
    if ((i == 0) && (paramTransitionValues != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return null;
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return null;
  }
  
  private static class VisibilityInfo
  {
    ViewGroup endParent;
    int endVisibility;
    boolean fadeIn;
    ViewGroup startParent;
    int startVisibility;
    boolean visibilityChange;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\VisibilityPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */