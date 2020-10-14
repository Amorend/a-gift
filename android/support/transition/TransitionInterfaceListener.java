package android.support.transition;

abstract interface TransitionInterfaceListener<TransitionT extends TransitionInterface>
{
  public abstract void onTransitionCancel(TransitionT paramTransitionT);
  
  public abstract void onTransitionEnd(TransitionT paramTransitionT);
  
  public abstract void onTransitionPause(TransitionT paramTransitionT);
  
  public abstract void onTransitionResume(TransitionT paramTransitionT);
  
  public abstract void onTransitionStart(TransitionT paramTransitionT);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionInterfaceListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */