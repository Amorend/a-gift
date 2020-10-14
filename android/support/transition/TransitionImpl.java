package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

abstract class TransitionImpl
{
  public abstract TransitionImpl addListener(TransitionInterfaceListener paramTransitionInterfaceListener);
  
  public abstract TransitionImpl addTarget(int paramInt);
  
  public abstract TransitionImpl addTarget(View paramView);
  
  public abstract void captureEndValues(TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(TransitionValues paramTransitionValues);
  
  public abstract Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2);
  
  public abstract TransitionImpl excludeChildren(int paramInt, boolean paramBoolean);
  
  public abstract TransitionImpl excludeChildren(View paramView, boolean paramBoolean);
  
  public abstract TransitionImpl excludeChildren(Class paramClass, boolean paramBoolean);
  
  public abstract TransitionImpl excludeTarget(int paramInt, boolean paramBoolean);
  
  public abstract TransitionImpl excludeTarget(View paramView, boolean paramBoolean);
  
  public abstract TransitionImpl excludeTarget(Class paramClass, boolean paramBoolean);
  
  public abstract long getDuration();
  
  public abstract TimeInterpolator getInterpolator();
  
  public abstract String getName();
  
  public abstract long getStartDelay();
  
  public abstract List<Integer> getTargetIds();
  
  public abstract List<View> getTargets();
  
  public abstract String[] getTransitionProperties();
  
  public abstract TransitionValues getTransitionValues(View paramView, boolean paramBoolean);
  
  public void init(TransitionInterface paramTransitionInterface)
  {
    init(paramTransitionInterface, null);
  }
  
  public abstract void init(TransitionInterface paramTransitionInterface, Object paramObject);
  
  public abstract TransitionImpl removeListener(TransitionInterfaceListener paramTransitionInterfaceListener);
  
  public abstract TransitionImpl removeTarget(int paramInt);
  
  public abstract TransitionImpl removeTarget(View paramView);
  
  public abstract TransitionImpl setDuration(long paramLong);
  
  public abstract TransitionImpl setInterpolator(TimeInterpolator paramTimeInterpolator);
  
  public abstract TransitionImpl setStartDelay(long paramLong);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */