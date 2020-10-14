package android.support.transition;

import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi(14)
class TransitionSetPort
  extends TransitionPort
{
  public static final int ORDERING_SEQUENTIAL = 1;
  public static final int ORDERING_TOGETHER = 0;
  int mCurrentListeners;
  private boolean mPlayTogether = true;
  boolean mStarted = false;
  ArrayList<TransitionPort> mTransitions = new ArrayList();
  
  private void setupStartEndListeners()
  {
    TransitionSetListener localTransitionSetListener = new TransitionSetListener(this);
    Iterator localIterator = this.mTransitions.iterator();
    while (localIterator.hasNext()) {
      ((TransitionPort)localIterator.next()).addListener(localTransitionSetListener);
    }
    this.mCurrentListeners = this.mTransitions.size();
  }
  
  public TransitionSetPort addListener(TransitionPort.TransitionListener paramTransitionListener)
  {
    return (TransitionSetPort)super.addListener(paramTransitionListener);
  }
  
  public TransitionSetPort addTarget(int paramInt)
  {
    return (TransitionSetPort)super.addTarget(paramInt);
  }
  
  public TransitionSetPort addTarget(View paramView)
  {
    return (TransitionSetPort)super.addTarget(paramView);
  }
  
  public TransitionSetPort addTransition(TransitionPort paramTransitionPort)
  {
    if (paramTransitionPort != null)
    {
      this.mTransitions.add(paramTransitionPort);
      paramTransitionPort.mParent = this;
      if (this.mDuration >= 0L) {
        paramTransitionPort.setDuration(this.mDuration);
      }
    }
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void cancel()
  {
    super.cancel();
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((TransitionPort)this.mTransitions.get(i)).cancel();
      i += 1;
    }
  }
  
  public void captureEndValues(TransitionValues paramTransitionValues)
  {
    int i = paramTransitionValues.view.getId();
    if (isValidTarget(paramTransitionValues.view, i))
    {
      Iterator localIterator = this.mTransitions.iterator();
      while (localIterator.hasNext())
      {
        TransitionPort localTransitionPort = (TransitionPort)localIterator.next();
        if (localTransitionPort.isValidTarget(paramTransitionValues.view, i)) {
          localTransitionPort.captureEndValues(paramTransitionValues);
        }
      }
    }
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    int i = paramTransitionValues.view.getId();
    if (isValidTarget(paramTransitionValues.view, i))
    {
      Iterator localIterator = this.mTransitions.iterator();
      while (localIterator.hasNext())
      {
        TransitionPort localTransitionPort = (TransitionPort)localIterator.next();
        if (localTransitionPort.isValidTarget(paramTransitionValues.view, i)) {
          localTransitionPort.captureStartValues(paramTransitionValues);
        }
      }
    }
  }
  
  public TransitionSetPort clone()
  {
    TransitionSetPort localTransitionSetPort = (TransitionSetPort)super.clone();
    localTransitionSetPort.mTransitions = new ArrayList();
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      localTransitionSetPort.addTransition(((TransitionPort)this.mTransitions.get(i)).clone());
      i += 1;
    }
    return localTransitionSetPort;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void createAnimators(ViewGroup paramViewGroup, TransitionValuesMaps paramTransitionValuesMaps1, TransitionValuesMaps paramTransitionValuesMaps2)
  {
    Iterator localIterator = this.mTransitions.iterator();
    while (localIterator.hasNext()) {
      ((TransitionPort)localIterator.next()).createAnimators(paramViewGroup, paramTransitionValuesMaps1, paramTransitionValuesMaps2);
    }
  }
  
  public int getOrdering()
  {
    if (this.mPlayTogether) {
      return 0;
    }
    return 1;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void pause(View paramView)
  {
    super.pause(paramView);
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((TransitionPort)this.mTransitions.get(i)).pause(paramView);
      i += 1;
    }
  }
  
  public TransitionSetPort removeListener(TransitionPort.TransitionListener paramTransitionListener)
  {
    return (TransitionSetPort)super.removeListener(paramTransitionListener);
  }
  
  public TransitionSetPort removeTarget(int paramInt)
  {
    return (TransitionSetPort)super.removeTarget(paramInt);
  }
  
  public TransitionSetPort removeTarget(View paramView)
  {
    return (TransitionSetPort)super.removeTarget(paramView);
  }
  
  public TransitionSetPort removeTransition(TransitionPort paramTransitionPort)
  {
    this.mTransitions.remove(paramTransitionPort);
    paramTransitionPort.mParent = null;
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void resume(View paramView)
  {
    super.resume(paramView);
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((TransitionPort)this.mTransitions.get(i)).resume(paramView);
      i += 1;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void runAnimators()
  {
    if (this.mTransitions.isEmpty())
    {
      start();
      end();
    }
    for (;;)
    {
      return;
      setupStartEndListeners();
      Object localObject;
      if (!this.mPlayTogether)
      {
        int i = 1;
        while (i < this.mTransitions.size())
        {
          ((TransitionPort)this.mTransitions.get(i - 1)).addListener(new TransitionPort.TransitionListenerAdapter()
          {
            public void onTransitionEnd(TransitionPort paramAnonymousTransitionPort)
            {
              this.val$nextTransition.runAnimators();
              paramAnonymousTransitionPort.removeListener(this);
            }
          });
          i += 1;
        }
        localObject = (TransitionPort)this.mTransitions.get(0);
        if (localObject != null) {
          ((TransitionPort)localObject).runAnimators();
        }
      }
      else
      {
        localObject = this.mTransitions.iterator();
        while (((Iterator)localObject).hasNext()) {
          ((TransitionPort)((Iterator)localObject).next()).runAnimators();
        }
      }
    }
  }
  
  void setCanRemoveViews(boolean paramBoolean)
  {
    super.setCanRemoveViews(paramBoolean);
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((TransitionPort)this.mTransitions.get(i)).setCanRemoveViews(paramBoolean);
      i += 1;
    }
  }
  
  public TransitionSetPort setDuration(long paramLong)
  {
    super.setDuration(paramLong);
    if (this.mDuration >= 0L)
    {
      int j = this.mTransitions.size();
      int i = 0;
      while (i < j)
      {
        ((TransitionPort)this.mTransitions.get(i)).setDuration(paramLong);
        i += 1;
      }
    }
    return this;
  }
  
  public TransitionSetPort setInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    return (TransitionSetPort)super.setInterpolator(paramTimeInterpolator);
  }
  
  public TransitionSetPort setOrdering(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + paramInt);
    case 1: 
      this.mPlayTogether = false;
      return this;
    }
    this.mPlayTogether = true;
    return this;
  }
  
  TransitionSetPort setSceneRoot(ViewGroup paramViewGroup)
  {
    super.setSceneRoot(paramViewGroup);
    int j = this.mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((TransitionPort)this.mTransitions.get(i)).setSceneRoot(paramViewGroup);
      i += 1;
    }
    return this;
  }
  
  public TransitionSetPort setStartDelay(long paramLong)
  {
    return (TransitionSetPort)super.setStartDelay(paramLong);
  }
  
  String toString(String paramString)
  {
    String str = super.toString(paramString);
    int i = 0;
    while (i < this.mTransitions.size())
    {
      str = str + "\n" + ((TransitionPort)this.mTransitions.get(i)).toString(new StringBuilder().append(paramString).append("  ").toString());
      i += 1;
    }
    return str;
  }
  
  static class TransitionSetListener
    extends TransitionPort.TransitionListenerAdapter
  {
    TransitionSetPort mTransitionSet;
    
    TransitionSetListener(TransitionSetPort paramTransitionSetPort)
    {
      this.mTransitionSet = paramTransitionSetPort;
    }
    
    public void onTransitionEnd(TransitionPort paramTransitionPort)
    {
      TransitionSetPort localTransitionSetPort = this.mTransitionSet;
      localTransitionSetPort.mCurrentListeners -= 1;
      if (this.mTransitionSet.mCurrentListeners == 0)
      {
        this.mTransitionSet.mStarted = false;
        this.mTransitionSet.end();
      }
      paramTransitionPort.removeListener(this);
    }
    
    public void onTransitionStart(TransitionPort paramTransitionPort)
    {
      if (!this.mTransitionSet.mStarted)
      {
        this.mTransitionSet.start();
        this.mTransitionSet.mStarted = true;
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionSetPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */