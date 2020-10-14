package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R.attr;
import android.view.MotionEvent;
import android.view.View;

class DropDownListView
  extends ListViewCompat
{
  private ViewPropertyAnimatorCompat mClickAnimation;
  private boolean mDrawsInPressedState;
  private boolean mHijackFocus;
  private boolean mListSelectionHidden;
  private ListViewAutoScrollHelper mScrollHelper;
  
  public DropDownListView(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    this.mHijackFocus = paramBoolean;
    setCacheColorHint(0);
  }
  
  private void clearPressedItem()
  {
    this.mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    View localView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
    if (localView != null) {
      localView.setPressed(false);
    }
    if (this.mClickAnimation != null)
    {
      this.mClickAnimation.cancel();
      this.mClickAnimation = null;
    }
  }
  
  private void clickPressedItem(View paramView, int paramInt)
  {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
  {
    this.mDrawsInPressedState = true;
    if (Build.VERSION.SDK_INT >= 21) {
      drawableHotspotChanged(paramFloat1, paramFloat2);
    }
    if (!isPressed()) {
      setPressed(true);
    }
    layoutChildren();
    if (this.mMotionPosition != -1)
    {
      View localView = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
      if ((localView != null) && (localView != paramView) && (localView.isPressed())) {
        localView.setPressed(false);
      }
    }
    this.mMotionPosition = paramInt;
    float f1 = paramView.getLeft();
    float f2 = paramView.getTop();
    if (Build.VERSION.SDK_INT >= 21) {
      paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2);
    }
    if (!paramView.isPressed()) {
      paramView.setPressed(true);
    }
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  public boolean hasFocus()
  {
    return (this.mHijackFocus) || (super.hasFocus());
  }
  
  public boolean hasWindowFocus()
  {
    return (this.mHijackFocus) || (super.hasWindowFocus());
  }
  
  public boolean isFocused()
  {
    return (this.mHijackFocus) || (super.isFocused());
  }
  
  public boolean isInTouchMode()
  {
    return ((this.mHijackFocus) && (this.mListSelectionHidden)) || (super.isInTouchMode());
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    switch (i)
    {
    default: 
      paramInt = 0;
    case 3: 
      for (bool = true;; bool = false)
      {
        label37:
        if ((!bool) || (paramInt != 0)) {
          clearPressedItem();
        }
        if (!bool) {
          break;
        }
        if (this.mScrollHelper == null) {
          this.mScrollHelper = new ListViewAutoScrollHelper(this);
        }
        this.mScrollHelper.setEnabled(true);
        this.mScrollHelper.onTouch(this, paramMotionEvent);
        label93:
        return bool;
        paramInt = 0;
      }
    }
    for (boolean bool = false;; bool = true)
    {
      int j = paramMotionEvent.findPointerIndex(paramInt);
      if (j < 0)
      {
        paramInt = 0;
        bool = false;
        break label37;
      }
      paramInt = (int)paramMotionEvent.getX(j);
      j = (int)paramMotionEvent.getY(j);
      int k = pointToPosition(paramInt, j);
      if (k == -1)
      {
        paramInt = 1;
        break label37;
      }
      View localView = getChildAt(k - getFirstVisiblePosition());
      setPressedItem(localView, k, paramInt, j);
      if (i != 1) {
        break;
      }
      clickPressedItem(localView, k);
      break;
      if (this.mScrollHelper == null) {
        break label93;
      }
      this.mScrollHelper.setEnabled(false);
      return bool;
    }
  }
  
  void setListSelectionHidden(boolean paramBoolean)
  {
    this.mListSelectionHidden = paramBoolean;
  }
  
  protected boolean touchModeDrawsInPressedStateCompat()
  {
    return (this.mDrawsInPressedState) || (super.touchModeDrawsInPressedStateCompat());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\DropDownListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */