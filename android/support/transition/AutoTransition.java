package android.support.transition;

public class AutoTransition
  extends TransitionSet
{
  public AutoTransition()
  {
    setOrdering(1);
    addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\AutoTransition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */