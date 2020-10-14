package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;

@TargetApi(14)
@RequiresApi(14)
class TransitionSetIcs
  extends TransitionIcs
  implements TransitionSetImpl
{
  private TransitionSetPort mTransitionSet = new TransitionSetPort();
  
  public TransitionSetIcs(TransitionInterface paramTransitionInterface)
  {
    init(paramTransitionInterface, this.mTransitionSet);
  }
  
  public TransitionSetIcs addTransition(TransitionImpl paramTransitionImpl)
  {
    this.mTransitionSet.addTransition(((TransitionIcs)paramTransitionImpl).mTransition);
    return this;
  }
  
  public int getOrdering()
  {
    return this.mTransitionSet.getOrdering();
  }
  
  public TransitionSetIcs removeTransition(TransitionImpl paramTransitionImpl)
  {
    this.mTransitionSet.removeTransition(((TransitionIcs)paramTransitionImpl).mTransition);
    return this;
  }
  
  public TransitionSetIcs setOrdering(int paramInt)
  {
    this.mTransitionSet.setOrdering(paramInt);
    return this;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionSetIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */