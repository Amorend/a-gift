package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@TargetApi(19)
@RequiresApi(19)
class TransitionKitKat
  extends TransitionImpl
{
  private CompatListener mCompatListener;
  TransitionInterface mExternalTransition;
  Transition mTransition;
  
  static android.transition.TransitionValues convertToPlatform(TransitionValues paramTransitionValues)
  {
    if (paramTransitionValues == null) {
      return null;
    }
    android.transition.TransitionValues localTransitionValues = new android.transition.TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    return localTransitionValues;
  }
  
  static TransitionValues convertToSupport(android.transition.TransitionValues paramTransitionValues)
  {
    if (paramTransitionValues == null) {
      return null;
    }
    TransitionValues localTransitionValues = new TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    return localTransitionValues;
  }
  
  static void copyValues(TransitionValues paramTransitionValues, android.transition.TransitionValues paramTransitionValues1)
  {
    if (paramTransitionValues == null) {}
    do
    {
      return;
      paramTransitionValues1.view = paramTransitionValues.view;
    } while (paramTransitionValues.values.size() <= 0);
    paramTransitionValues1.values.putAll(paramTransitionValues.values);
  }
  
  static void copyValues(android.transition.TransitionValues paramTransitionValues, TransitionValues paramTransitionValues1)
  {
    if (paramTransitionValues == null) {}
    do
    {
      return;
      paramTransitionValues1.view = paramTransitionValues.view;
    } while (paramTransitionValues.values.size() <= 0);
    paramTransitionValues1.values.putAll(paramTransitionValues.values);
  }
  
  static void wrapCaptureEndValues(TransitionInterface paramTransitionInterface, android.transition.TransitionValues paramTransitionValues)
  {
    TransitionValues localTransitionValues = new TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    paramTransitionInterface.captureEndValues(localTransitionValues);
    copyValues(localTransitionValues, paramTransitionValues);
  }
  
  static void wrapCaptureStartValues(TransitionInterface paramTransitionInterface, android.transition.TransitionValues paramTransitionValues)
  {
    TransitionValues localTransitionValues = new TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    paramTransitionInterface.captureStartValues(localTransitionValues);
    copyValues(localTransitionValues, paramTransitionValues);
  }
  
  public TransitionImpl addListener(TransitionInterfaceListener paramTransitionInterfaceListener)
  {
    if (this.mCompatListener == null)
    {
      this.mCompatListener = new CompatListener();
      this.mTransition.addListener(this.mCompatListener);
    }
    this.mCompatListener.addListener(paramTransitionInterfaceListener);
    return this;
  }
  
  public TransitionImpl addTarget(int paramInt)
  {
    this.mTransition.addTarget(paramInt);
    return this;
  }
  
  public TransitionImpl addTarget(View paramView)
  {
    this.mTransition.addTarget(paramView);
    return this;
  }
  
  public void captureEndValues(TransitionValues paramTransitionValues)
  {
    android.transition.TransitionValues localTransitionValues = new android.transition.TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    this.mTransition.captureEndValues(localTransitionValues);
    copyValues(localTransitionValues, paramTransitionValues);
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    android.transition.TransitionValues localTransitionValues = new android.transition.TransitionValues();
    copyValues(paramTransitionValues, localTransitionValues);
    this.mTransition.captureStartValues(localTransitionValues);
    copyValues(localTransitionValues, paramTransitionValues);
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    android.transition.TransitionValues localTransitionValues1 = null;
    android.transition.TransitionValues localTransitionValues2;
    if (paramTransitionValues1 != null)
    {
      localTransitionValues2 = new android.transition.TransitionValues();
      copyValues(paramTransitionValues1, localTransitionValues2);
    }
    for (paramTransitionValues1 = localTransitionValues2;; paramTransitionValues1 = null)
    {
      if (paramTransitionValues2 != null)
      {
        localTransitionValues1 = new android.transition.TransitionValues();
        copyValues(paramTransitionValues2, localTransitionValues1);
      }
      return this.mTransition.createAnimator(paramViewGroup, paramTransitionValues1, localTransitionValues1);
    }
  }
  
  public TransitionImpl excludeChildren(int paramInt, boolean paramBoolean)
  {
    this.mTransition.excludeChildren(paramInt, paramBoolean);
    return this;
  }
  
  public TransitionImpl excludeChildren(View paramView, boolean paramBoolean)
  {
    this.mTransition.excludeChildren(paramView, paramBoolean);
    return this;
  }
  
  public TransitionImpl excludeChildren(Class paramClass, boolean paramBoolean)
  {
    this.mTransition.excludeChildren(paramClass, paramBoolean);
    return this;
  }
  
  public TransitionImpl excludeTarget(int paramInt, boolean paramBoolean)
  {
    this.mTransition.excludeTarget(paramInt, paramBoolean);
    return this;
  }
  
  public TransitionImpl excludeTarget(View paramView, boolean paramBoolean)
  {
    this.mTransition.excludeTarget(paramView, paramBoolean);
    return this;
  }
  
  public TransitionImpl excludeTarget(Class paramClass, boolean paramBoolean)
  {
    this.mTransition.excludeTarget(paramClass, paramBoolean);
    return this;
  }
  
  public long getDuration()
  {
    return this.mTransition.getDuration();
  }
  
  public TimeInterpolator getInterpolator()
  {
    return this.mTransition.getInterpolator();
  }
  
  public String getName()
  {
    return this.mTransition.getName();
  }
  
  public long getStartDelay()
  {
    return this.mTransition.getStartDelay();
  }
  
  public List<Integer> getTargetIds()
  {
    return this.mTransition.getTargetIds();
  }
  
  public List<View> getTargets()
  {
    return this.mTransition.getTargets();
  }
  
  public String[] getTransitionProperties()
  {
    return this.mTransition.getTransitionProperties();
  }
  
  public TransitionValues getTransitionValues(View paramView, boolean paramBoolean)
  {
    TransitionValues localTransitionValues = new TransitionValues();
    copyValues(this.mTransition.getTransitionValues(paramView, paramBoolean), localTransitionValues);
    return localTransitionValues;
  }
  
  public void init(TransitionInterface paramTransitionInterface, Object paramObject)
  {
    this.mExternalTransition = paramTransitionInterface;
    if (paramObject == null)
    {
      this.mTransition = new TransitionWrapper(paramTransitionInterface);
      return;
    }
    this.mTransition = ((Transition)paramObject);
  }
  
  public TransitionImpl removeListener(TransitionInterfaceListener paramTransitionInterfaceListener)
  {
    if (this.mCompatListener == null) {}
    do
    {
      return this;
      this.mCompatListener.removeListener(paramTransitionInterfaceListener);
    } while (!this.mCompatListener.isEmpty());
    this.mTransition.removeListener(this.mCompatListener);
    this.mCompatListener = null;
    return this;
  }
  
  public TransitionImpl removeTarget(int paramInt)
  {
    if (paramInt > 0) {
      getTargetIds().remove(Integer.valueOf(paramInt));
    }
    return this;
  }
  
  public TransitionImpl removeTarget(View paramView)
  {
    this.mTransition.removeTarget(paramView);
    return this;
  }
  
  public TransitionImpl setDuration(long paramLong)
  {
    this.mTransition.setDuration(paramLong);
    return this;
  }
  
  public TransitionImpl setInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    this.mTransition.setInterpolator(paramTimeInterpolator);
    return this;
  }
  
  public TransitionImpl setStartDelay(long paramLong)
  {
    this.mTransition.setStartDelay(paramLong);
    return this;
  }
  
  public String toString()
  {
    return this.mTransition.toString();
  }
  
  private class CompatListener
    implements Transition.TransitionListener
  {
    private final ArrayList<TransitionInterfaceListener> mListeners = new ArrayList();
    
    CompatListener() {}
    
    void addListener(TransitionInterfaceListener paramTransitionInterfaceListener)
    {
      this.mListeners.add(paramTransitionInterfaceListener);
    }
    
    boolean isEmpty()
    {
      return this.mListeners.isEmpty();
    }
    
    public void onTransitionCancel(Transition paramTransition)
    {
      paramTransition = this.mListeners.iterator();
      while (paramTransition.hasNext()) {
        ((TransitionInterfaceListener)paramTransition.next()).onTransitionCancel(TransitionKitKat.this.mExternalTransition);
      }
    }
    
    public void onTransitionEnd(Transition paramTransition)
    {
      paramTransition = this.mListeners.iterator();
      while (paramTransition.hasNext()) {
        ((TransitionInterfaceListener)paramTransition.next()).onTransitionEnd(TransitionKitKat.this.mExternalTransition);
      }
    }
    
    public void onTransitionPause(Transition paramTransition)
    {
      paramTransition = this.mListeners.iterator();
      while (paramTransition.hasNext()) {
        ((TransitionInterfaceListener)paramTransition.next()).onTransitionPause(TransitionKitKat.this.mExternalTransition);
      }
    }
    
    public void onTransitionResume(Transition paramTransition)
    {
      paramTransition = this.mListeners.iterator();
      while (paramTransition.hasNext()) {
        ((TransitionInterfaceListener)paramTransition.next()).onTransitionResume(TransitionKitKat.this.mExternalTransition);
      }
    }
    
    public void onTransitionStart(Transition paramTransition)
    {
      paramTransition = this.mListeners.iterator();
      while (paramTransition.hasNext()) {
        ((TransitionInterfaceListener)paramTransition.next()).onTransitionStart(TransitionKitKat.this.mExternalTransition);
      }
    }
    
    void removeListener(TransitionInterfaceListener paramTransitionInterfaceListener)
    {
      this.mListeners.remove(paramTransitionInterfaceListener);
    }
  }
  
  private static class TransitionWrapper
    extends Transition
  {
    private TransitionInterface mTransition;
    
    public TransitionWrapper(TransitionInterface paramTransitionInterface)
    {
      this.mTransition = paramTransitionInterface;
    }
    
    public void captureEndValues(android.transition.TransitionValues paramTransitionValues)
    {
      TransitionKitKat.wrapCaptureEndValues(this.mTransition, paramTransitionValues);
    }
    
    public void captureStartValues(android.transition.TransitionValues paramTransitionValues)
    {
      TransitionKitKat.wrapCaptureStartValues(this.mTransition, paramTransitionValues);
    }
    
    public Animator createAnimator(ViewGroup paramViewGroup, android.transition.TransitionValues paramTransitionValues1, android.transition.TransitionValues paramTransitionValues2)
    {
      return this.mTransition.createAnimator(paramViewGroup, TransitionKitKat.convertToSupport(paramTransitionValues1), TransitionKitKat.convertToSupport(paramTransitionValues2));
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */