package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;

public class YLListView
  extends ListView
  implements AbsListView.OnScrollListener
{
  private static final float OFFSET_RADIO = 5.0F;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 400;
  private int finalBottomHeight;
  private int finalTopHeight;
  private View mFooterView;
  private View mHeaderView;
  private float mLastY = -1;
  private int mScrollBack;
  private AbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;
  
  public YLListView(Context paramContext)
  {
    super(paramContext);
    initWithContext(paramContext);
  }
  
  public YLListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initWithContext(paramContext);
  }
  
  public YLListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initWithContext(paramContext);
  }
  
  private void initWithContext(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      @Override
      public void onGlobalLayout()
      {
        View localView;
        if (YLListView.access$L1000008(YLListView.this) == null)
        {
          localView = new View(YLListView.this.getContext());
          YLListView.this.addHeaderView(localView);
        }
        if (YLListView.access$L1000009(YLListView.this) == null)
        {
          localView = new View(YLListView.this.getContext());
          YLListView.this.addFooterView(localView);
        }
        YLListView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }
  
  private void resetFooterHeight()
  {
    int i = getFootHeight();
    if (i > this.finalBottomHeight)
    {
      this.mScrollBack = 1;
      this.mScroller.startScroll(0, i, 0, -i + this.finalBottomHeight, 400);
      invalidate();
    }
  }
  
  private void resetHeaderHeight()
  {
    int i = getHeaderHeight();
    if (i == 0) {
      return;
    }
    this.mScrollBack = 0;
    this.mScroller.startScroll(0, i, 0, this.finalTopHeight - i, 400);
    invalidate();
  }
  
  private void setFooterViewHeight(int paramInt)
  {
    AbsListView.LayoutParams localLayoutParams = (AbsListView.LayoutParams)this.mFooterView.getLayoutParams();
    localLayoutParams.height = paramInt;
    this.mFooterView.setLayoutParams(localLayoutParams);
  }
  
  private void setHeaderHeight(int paramInt)
  {
    AbsListView.LayoutParams localLayoutParams = (AbsListView.LayoutParams)this.mHeaderView.getLayoutParams();
    localLayoutParams.height = paramInt;
    this.mHeaderView.setLayoutParams(localLayoutParams);
  }
  
  private void updateFooterHeight(float paramFloat)
  {
    setFooterViewHeight((int)(getFootHeight() + paramFloat));
  }
  
  private void updateHeaderHeight(float paramFloat)
  {
    setHeaderHeight((int)(getHeaderHeight() + paramFloat));
    setSelection(0);
  }
  
  @Override
  public void addFooterView(View paramView)
  {
    this.mFooterView = paramView;
    super.addFooterView(this.mFooterView);
    this.mFooterView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      @Override
      public void onGlobalLayout()
      {
        if (YLListView.access$L1000011(YLListView.this) == 0) {
          YLListView.access$S1000011(YLListView.this, YLListView.access$L1000009(YLListView.this).getMeasuredHeight());
        }
        YLListView.this.setFooterViewHeight(YLListView.access$L1000011(YLListView.this));
        YLListView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }
  
  @Override
  public void addHeaderView(View paramView)
  {
    this.mHeaderView = paramView;
    super.addHeaderView(this.mHeaderView);
    this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      @Override
      public void onGlobalLayout()
      {
        if (YLListView.access$L1000010(YLListView.this) == 0) {
          YLListView.access$S1000010(YLListView.this, YLListView.access$L1000008(YLListView.this).getMeasuredHeight());
        }
        YLListView.this.setHeaderHeight(YLListView.access$L1000010(YLListView.this));
        YLListView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }
  
  @Override
  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack != 0) {
        break label37;
      }
      setHeaderHeight(this.mScroller.getCurrY());
    }
    for (;;)
    {
      postInvalidate();
      super.computeScroll();
      return;
      label37:
      setFooterViewHeight(this.mScroller.getCurrY());
    }
  }
  
  public int getFootHeight()
  {
    return ((AbsListView.LayoutParams)this.mFooterView.getLayoutParams()).height;
  }
  
  public int getHeaderHeight()
  {
    return ((AbsListView.LayoutParams)this.mHeaderView.getLayoutParams()).height;
  }
  
  @Override
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTotalItemCount = paramInt3;
    if (this.mScrollListener != null) {
      this.mScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    }
  }
  
  @Override
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (this.mScrollListener != null) {
      this.mScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
    }
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLastY == -1) {
      this.mLastY = paramMotionEvent.getRawY();
    }
    switch (paramMotionEvent.getAction())
    {
    case 1: 
    default: 
      this.mLastY = -1;
      if ((getFirstVisiblePosition() == 0) && (getHeaderHeight() > this.finalTopHeight)) {
        resetHeaderHeight();
      }
      if ((getLastVisiblePosition() == this.mTotalItemCount - 1) && (getFootHeight() > this.finalBottomHeight)) {
        resetFooterHeight();
      }
    case 0: 
      for (;;)
      {
        return super.onTouchEvent(paramMotionEvent);
        this.mLastY = paramMotionEvent.getRawY();
      }
    }
    float f = paramMotionEvent.getRawY() - this.mLastY;
    this.mLastY = paramMotionEvent.getRawY();
    if ((getFirstVisiblePosition() == 0) && ((this.mHeaderView.getHeight() > this.finalTopHeight) || (f > 0)) && (this.mHeaderView.getTop() >= 0)) {
      updateHeaderHeight(f / 1.8F);
    }
    for (;;)
    {
      break;
      if ((getLastVisiblePosition() == this.mTotalItemCount - 1) && ((getFootHeight() > this.finalBottomHeight) || (f < 0))) {
        updateFooterHeight(-f / 1.8F);
      }
    }
  }
  
  public void setFinalBottomHeight(int paramInt)
  {
    this.finalBottomHeight = paramInt;
  }
  
  public void setFinalTopHeight(int paramInt)
  {
    this.finalTopHeight = paramInt;
  }
  
  @Override
  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.mScrollListener = paramOnScrollListener;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\YLListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */