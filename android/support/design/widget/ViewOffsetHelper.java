package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class ViewOffsetHelper
{
  private int mLayoutLeft;
  private int mLayoutTop;
  private int mOffsetLeft;
  private int mOffsetTop;
  private final View mView;
  
  public ViewOffsetHelper(View paramView)
  {
    this.mView = paramView;
  }
  
  private void updateOffsets()
  {
    ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
    ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
  }
  
  public int getLayoutLeft()
  {
    return this.mLayoutLeft;
  }
  
  public int getLayoutTop()
  {
    return this.mLayoutTop;
  }
  
  public int getLeftAndRightOffset()
  {
    return this.mOffsetLeft;
  }
  
  public int getTopAndBottomOffset()
  {
    return this.mOffsetTop;
  }
  
  public void onViewLayout()
  {
    this.mLayoutTop = this.mView.getTop();
    this.mLayoutLeft = this.mView.getLeft();
    updateOffsets();
  }
  
  public boolean setLeftAndRightOffset(int paramInt)
  {
    if (this.mOffsetLeft != paramInt)
    {
      this.mOffsetLeft = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }
  
  public boolean setTopAndBottomOffset(int paramInt)
  {
    if (this.mOffsetTop != paramInt)
    {
      this.mOffsetTop = paramInt;
      updateOffsets();
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ViewOffsetHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */