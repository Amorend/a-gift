package android.support.transition;

abstract interface TransitionSetImpl
{
  public abstract TransitionSetImpl addTransition(TransitionImpl paramTransitionImpl);
  
  public abstract int getOrdering();
  
  public abstract TransitionSetImpl removeTransition(TransitionImpl paramTransitionImpl);
  
  public abstract TransitionSetImpl setOrdering(int paramInt);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionSetImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */