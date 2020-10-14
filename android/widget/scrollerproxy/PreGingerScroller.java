package android.widget.scrollerproxy;

import android.content.Context;
import android.widget.Scroller;

public class PreGingerScroller
  extends ScrollerProxy
{
  private final Scroller mScroller;
  
  public PreGingerScroller(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext);
  }
  
  @Override
  public boolean computeScrollOffset()
  {
    return this.mScroller.computeScrollOffset();
  }
  
  @Override
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10)
  {
    this.mScroller.fling(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  @Override
  public void forceFinished(boolean paramBoolean)
  {
    this.mScroller.forceFinished(paramBoolean);
  }
  
  @Override
  public int getCurrX()
  {
    return this.mScroller.getCurrX();
  }
  
  @Override
  public int getCurrY()
  {
    return this.mScroller.getCurrY();
  }
  
  public boolean isFinished()
  {
    return this.mScroller.isFinished();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\scrollerproxy\PreGingerScroller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */