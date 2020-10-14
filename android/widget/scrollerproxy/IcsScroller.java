package android.widget.scrollerproxy;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

@TargetApi(14)
public class IcsScroller
  extends GingerScroller
{
  public IcsScroller(Context paramContext)
  {
    super(paramContext);
  }
  
  @Override
  public boolean computeScrollOffset()
  {
    return this.mScroller.computeScrollOffset();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\scrollerproxy\IcsScroller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */