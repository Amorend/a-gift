package android.support.transition;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Visibility;
import android.view.ViewGroup;

@TargetApi(19)
@RequiresApi(19)
class VisibilityKitKat
  extends TransitionKitKat
  implements VisibilityImpl
{
  public void init(TransitionInterface paramTransitionInterface, Object paramObject)
  {
    this.mExternalTransition = paramTransitionInterface;
    if (paramObject == null)
    {
      this.mTransition = new VisibilityWrapper((VisibilityInterface)paramTransitionInterface);
      return;
    }
    this.mTransition = ((Visibility)paramObject);
  }
  
  public boolean isVisible(TransitionValues paramTransitionValues)
  {
    return ((Visibility)this.mTransition).isVisible(convertToPlatform(paramTransitionValues));
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((Visibility)this.mTransition).onAppear(paramViewGroup, convertToPlatform(paramTransitionValues1), paramInt1, convertToPlatform(paramTransitionValues2), paramInt2);
  }
  
  public Animator onDisappear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, TransitionValues paramTransitionValues2, int paramInt2)
  {
    return ((Visibility)this.mTransition).onDisappear(paramViewGroup, convertToPlatform(paramTransitionValues1), paramInt1, convertToPlatform(paramTransitionValues2), paramInt2);
  }
  
  private static class VisibilityWrapper
    extends Visibility
  {
    private final VisibilityInterface mVisibility;
    
    VisibilityWrapper(VisibilityInterface paramVisibilityInterface)
    {
      this.mVisibility = paramVisibilityInterface;
    }
    
    public void captureEndValues(android.transition.TransitionValues paramTransitionValues)
    {
      TransitionKitKat.wrapCaptureEndValues(this.mVisibility, paramTransitionValues);
    }
    
    public void captureStartValues(android.transition.TransitionValues paramTransitionValues)
    {
      TransitionKitKat.wrapCaptureStartValues(this.mVisibility, paramTransitionValues);
    }
    
    public Animator createAnimator(ViewGroup paramViewGroup, android.transition.TransitionValues paramTransitionValues1, android.transition.TransitionValues paramTransitionValues2)
    {
      return this.mVisibility.createAnimator(paramViewGroup, TransitionKitKat.convertToSupport(paramTransitionValues1), TransitionKitKat.convertToSupport(paramTransitionValues2));
    }
    
    public boolean isVisible(android.transition.TransitionValues paramTransitionValues)
    {
      if (paramTransitionValues == null) {
        return false;
      }
      TransitionValues localTransitionValues = new TransitionValues();
      TransitionKitKat.copyValues(paramTransitionValues, localTransitionValues);
      return this.mVisibility.isVisible(localTransitionValues);
    }
    
    public Animator onAppear(ViewGroup paramViewGroup, android.transition.TransitionValues paramTransitionValues1, int paramInt1, android.transition.TransitionValues paramTransitionValues2, int paramInt2)
    {
      return this.mVisibility.onAppear(paramViewGroup, TransitionKitKat.convertToSupport(paramTransitionValues1), paramInt1, TransitionKitKat.convertToSupport(paramTransitionValues2), paramInt2);
    }
    
    public Animator onDisappear(ViewGroup paramViewGroup, android.transition.TransitionValues paramTransitionValues1, int paramInt1, android.transition.TransitionValues paramTransitionValues2, int paramInt2)
    {
      return this.mVisibility.onDisappear(paramViewGroup, TransitionKitKat.convertToSupport(paramTransitionValues1), paramInt1, TransitionKitKat.convertToSupport(paramTransitionValues2), paramInt2);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\VisibilityKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */