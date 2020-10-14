package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Build.VERSION;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public abstract class Transition
  implements TransitionInterface
{
  TransitionImpl mImpl;
  
  public Transition()
  {
    this(false);
  }
  
  Transition(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      if (Build.VERSION.SDK_INT < 23) {
        break label36;
      }
      this.mImpl = new TransitionApi23();
    }
    for (;;)
    {
      this.mImpl.init(this);
      return;
      label36:
      if (Build.VERSION.SDK_INT >= 19) {
        this.mImpl = new TransitionKitKat();
      } else {
        this.mImpl = new TransitionIcs();
      }
    }
  }
  
  @NonNull
  public Transition addListener(@NonNull TransitionListener paramTransitionListener)
  {
    this.mImpl.addListener(paramTransitionListener);
    return this;
  }
  
  @NonNull
  public Transition addTarget(@IdRes int paramInt)
  {
    this.mImpl.addTarget(paramInt);
    return this;
  }
  
  @NonNull
  public Transition addTarget(@NonNull View paramView)
  {
    this.mImpl.addTarget(paramView);
    return this;
  }
  
  public abstract void captureEndValues(@NonNull TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(@NonNull TransitionValues paramTransitionValues);
  
  @Nullable
  public Animator createAnimator(@NonNull ViewGroup paramViewGroup, @Nullable TransitionValues paramTransitionValues1, @Nullable TransitionValues paramTransitionValues2)
  {
    return null;
  }
  
  @NonNull
  public Transition excludeChildren(@IdRes int paramInt, boolean paramBoolean)
  {
    this.mImpl.excludeChildren(paramInt, paramBoolean);
    return this;
  }
  
  @NonNull
  public Transition excludeChildren(@NonNull View paramView, boolean paramBoolean)
  {
    this.mImpl.excludeChildren(paramView, paramBoolean);
    return this;
  }
  
  @NonNull
  public Transition excludeChildren(@NonNull Class paramClass, boolean paramBoolean)
  {
    this.mImpl.excludeChildren(paramClass, paramBoolean);
    return this;
  }
  
  @NonNull
  public Transition excludeTarget(@IdRes int paramInt, boolean paramBoolean)
  {
    this.mImpl.excludeTarget(paramInt, paramBoolean);
    return this;
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull View paramView, boolean paramBoolean)
  {
    this.mImpl.excludeTarget(paramView, paramBoolean);
    return this;
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull Class paramClass, boolean paramBoolean)
  {
    this.mImpl.excludeTarget(paramClass, paramBoolean);
    return this;
  }
  
  public long getDuration()
  {
    return this.mImpl.getDuration();
  }
  
  @Nullable
  public TimeInterpolator getInterpolator()
  {
    return this.mImpl.getInterpolator();
  }
  
  @NonNull
  public String getName()
  {
    return this.mImpl.getName();
  }
  
  public long getStartDelay()
  {
    return this.mImpl.getStartDelay();
  }
  
  @NonNull
  public List<Integer> getTargetIds()
  {
    return this.mImpl.getTargetIds();
  }
  
  @NonNull
  public List<View> getTargets()
  {
    return this.mImpl.getTargets();
  }
  
  @Nullable
  public String[] getTransitionProperties()
  {
    return this.mImpl.getTransitionProperties();
  }
  
  @NonNull
  public TransitionValues getTransitionValues(@NonNull View paramView, boolean paramBoolean)
  {
    return this.mImpl.getTransitionValues(paramView, paramBoolean);
  }
  
  @NonNull
  public Transition removeListener(@NonNull TransitionListener paramTransitionListener)
  {
    this.mImpl.removeListener(paramTransitionListener);
    return this;
  }
  
  @NonNull
  public Transition removeTarget(@IdRes int paramInt)
  {
    this.mImpl.removeTarget(paramInt);
    return this;
  }
  
  @NonNull
  public Transition removeTarget(@NonNull View paramView)
  {
    this.mImpl.removeTarget(paramView);
    return this;
  }
  
  @NonNull
  public Transition setDuration(long paramLong)
  {
    this.mImpl.setDuration(paramLong);
    return this;
  }
  
  @NonNull
  public Transition setInterpolator(@Nullable TimeInterpolator paramTimeInterpolator)
  {
    this.mImpl.setInterpolator(paramTimeInterpolator);
    return this;
  }
  
  @NonNull
  public Transition setStartDelay(long paramLong)
  {
    this.mImpl.setStartDelay(paramLong);
    return this;
  }
  
  public String toString()
  {
    return this.mImpl.toString();
  }
  
  public static abstract interface TransitionListener
    extends TransitionInterfaceListener<Transition>
  {
    public abstract void onTransitionCancel(@NonNull Transition paramTransition);
    
    public abstract void onTransitionEnd(@NonNull Transition paramTransition);
    
    public abstract void onTransitionPause(@NonNull Transition paramTransition);
    
    public abstract void onTransitionResume(@NonNull Transition paramTransition);
    
    public abstract void onTransitionStart(@NonNull Transition paramTransition);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\Transition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */