package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R.attr;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@CoordinatorLayout.DefaultBehavior(Behavior.class)
public class AppBarLayout
  extends LinearLayout
{
  private static final int INVALID_SCROLL_RANGE = -1;
  static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
  static final int PENDING_ACTION_COLLAPSED = 2;
  static final int PENDING_ACTION_EXPANDED = 1;
  static final int PENDING_ACTION_FORCE = 8;
  static final int PENDING_ACTION_NONE = 0;
  private boolean mCollapsed;
  private boolean mCollapsible;
  private int mDownPreScrollRange = -1;
  private int mDownScrollRange = -1;
  private boolean mHaveChildWithInterpolator;
  private WindowInsetsCompat mLastInsets;
  private List<OnOffsetChangedListener> mListeners;
  private int mPendingAction = 0;
  private final int[] mTmpStatesArray = new int[2];
  private int mTotalScrollRange = -1;
  
  public AppBarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOrientation(1);
    ThemeUtils.checkAppCompatTheme(paramContext);
    if (Build.VERSION.SDK_INT >= 21)
    {
      ViewUtilsLollipop.setBoundsViewOutlineProvider(this);
      ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, paramAttributeSet, 0, R.style.Widget_Design_AppBarLayout);
    }
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout);
    ViewCompat.setBackground(this, paramContext.getDrawable(R.styleable.AppBarLayout_android_background));
    if (paramContext.hasValue(R.styleable.AppBarLayout_expanded)) {
      setExpanded(paramContext.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false);
    }
    if ((Build.VERSION.SDK_INT >= 21) && (paramContext.hasValue(R.styleable.AppBarLayout_elevation))) {
      ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, paramContext.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0));
    }
    paramContext.recycle();
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        return AppBarLayout.this.onWindowInsetChanged(paramAnonymousWindowInsetsCompat);
      }
    });
  }
  
  private void invalidateScrollRanges()
  {
    this.mTotalScrollRange = -1;
    this.mDownPreScrollRange = -1;
    this.mDownScrollRange = -1;
  }
  
  private boolean setCollapsibleState(boolean paramBoolean)
  {
    if (this.mCollapsible != paramBoolean)
    {
      this.mCollapsible = paramBoolean;
      refreshDrawableState();
      return true;
    }
    return false;
  }
  
  private void setExpanded(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    int k = 0;
    int i;
    if (paramBoolean1)
    {
      i = 1;
      if (!paramBoolean2) {
        break label48;
      }
    }
    label48:
    for (int j = 4;; j = 0)
    {
      if (paramBoolean3) {
        k = 8;
      }
      this.mPendingAction = (k | j | i);
      requestLayout();
      return;
      i = 2;
      break;
    }
  }
  
  private void updateCollapsible()
  {
    int j = getChildCount();
    int i = 0;
    if (i < j) {
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isCollapsible()) {}
    }
    for (boolean bool = true;; bool = false)
    {
      setCollapsibleState(bool);
      return;
      i += 1;
      break;
    }
  }
  
  public void addOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener)
  {
    if (this.mListeners == null) {
      this.mListeners = new ArrayList();
    }
    if ((paramOnOffsetChangedListener != null) && (!this.mListeners.contains(paramOnOffsetChangedListener))) {
      this.mListeners.add(paramOnOffsetChangedListener);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void dispatchOffsetUpdates(int paramInt)
  {
    if (this.mListeners != null)
    {
      int j = this.mListeners.size();
      int i = 0;
      while (i < j)
      {
        OnOffsetChangedListener localOnOffsetChangedListener = (OnOffsetChangedListener)this.mListeners.get(i);
        if (localOnOffsetChangedListener != null) {
          localOnOffsetChangedListener.onOffsetChanged(this, paramInt);
        }
        i += 1;
      }
    }
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((Build.VERSION.SDK_INT >= 19) && ((paramLayoutParams instanceof LinearLayout.LayoutParams))) {
      return new LayoutParams((LinearLayout.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  int getDownNestedPreScrollRange()
  {
    if (this.mDownPreScrollRange != -1) {
      return this.mDownPreScrollRange;
    }
    int j = getChildCount() - 1;
    int i = 0;
    View localView;
    int k;
    int m;
    if (j >= 0)
    {
      localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      k = localView.getMeasuredHeight();
      m = localLayoutParams.mScrollFlags;
      if ((m & 0x5) == 5)
      {
        int n = localLayoutParams.topMargin;
        i = localLayoutParams.bottomMargin + n + i;
        if ((m & 0x8) != 0) {
          i += ViewCompat.getMinimumHeight(localView);
        }
      }
    }
    for (;;)
    {
      j -= 1;
      break;
      if ((m & 0x2) != 0)
      {
        i += k - ViewCompat.getMinimumHeight(localView);
      }
      else
      {
        i += k - getTopInset();
        continue;
        if (i > 0)
        {
          i = Math.max(0, i);
          this.mDownPreScrollRange = i;
          return i;
        }
      }
    }
  }
  
  int getDownNestedScrollRange()
  {
    if (this.mDownScrollRange != -1) {
      return this.mDownScrollRange;
    }
    int k = getChildCount();
    int j = 0;
    int i = 0;
    if (j < k)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      int n = localView.getMeasuredHeight();
      int i1 = localLayoutParams.topMargin;
      int i2 = localLayoutParams.bottomMargin;
      int m = localLayoutParams.mScrollFlags;
      if ((m & 0x1) != 0)
      {
        i += n + (i1 + i2);
        if ((m & 0x2) != 0) {
          i -= ViewCompat.getMinimumHeight(localView) + getTopInset();
        }
      }
    }
    for (;;)
    {
      i = Math.max(0, i);
      this.mDownScrollRange = i;
      return i;
      j += 1;
      break;
    }
  }
  
  final int getMinimumHeightForVisibleOverlappingContent()
  {
    int j = getTopInset();
    int i = ViewCompat.getMinimumHeight(this);
    if (i != 0) {
      return i * 2 + j;
    }
    i = getChildCount();
    if (i >= 1) {}
    for (i = ViewCompat.getMinimumHeight(getChildAt(i - 1)); i != 0; i = 0) {
      return i * 2 + j;
    }
    return getHeight() / 3;
  }
  
  int getPendingAction()
  {
    return this.mPendingAction;
  }
  
  @Deprecated
  public float getTargetElevation()
  {
    return 0.0F;
  }
  
  @VisibleForTesting
  final int getTopInset()
  {
    if (this.mLastInsets != null) {
      return this.mLastInsets.getSystemWindowInsetTop();
    }
    return 0;
  }
  
  public final int getTotalScrollRange()
  {
    if (this.mTotalScrollRange != -1) {
      return this.mTotalScrollRange;
    }
    int k = getChildCount();
    int j = 0;
    int i = 0;
    if (j < k)
    {
      View localView = getChildAt(j);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      int n = localView.getMeasuredHeight();
      int m = localLayoutParams.mScrollFlags;
      if ((m & 0x1) != 0)
      {
        int i1 = localLayoutParams.topMargin;
        i += localLayoutParams.bottomMargin + (n + i1);
        if ((m & 0x2) != 0) {
          i -= ViewCompat.getMinimumHeight(localView);
        }
      }
    }
    for (;;)
    {
      i = Math.max(0, i - getTopInset());
      this.mTotalScrollRange = i;
      return i;
      j += 1;
      break;
    }
  }
  
  int getUpNestedPreScrollRange()
  {
    return getTotalScrollRange();
  }
  
  boolean hasChildWithInterpolator()
  {
    return this.mHaveChildWithInterpolator;
  }
  
  boolean hasScrollableChildren()
  {
    return getTotalScrollRange() != 0;
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt1 = this.mTmpStatesArray;
    int[] arrayOfInt2 = super.onCreateDrawableState(arrayOfInt1.length + paramInt);
    if (this.mCollapsible)
    {
      paramInt = R.attr.state_collapsible;
      arrayOfInt1[0] = paramInt;
      if ((!this.mCollapsible) || (!this.mCollapsed)) {
        break label65;
      }
    }
    label65:
    for (paramInt = R.attr.state_collapsed;; paramInt = -R.attr.state_collapsed)
    {
      arrayOfInt1[1] = paramInt;
      return mergeDrawableStates(arrayOfInt2, arrayOfInt1);
      paramInt = -R.attr.state_collapsible;
      break;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    invalidateScrollRanges();
    this.mHaveChildWithInterpolator = false;
    paramInt2 = getChildCount();
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 < paramInt2)
      {
        if (((LayoutParams)getChildAt(paramInt1).getLayoutParams()).getScrollInterpolator() != null) {
          this.mHaveChildWithInterpolator = true;
        }
      }
      else
      {
        updateCollapsible();
        return;
      }
      paramInt1 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    invalidateScrollRanges();
  }
  
  WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat paramWindowInsetsCompat)
  {
    WindowInsetsCompat localWindowInsetsCompat = null;
    if (ViewCompat.getFitsSystemWindows(this)) {
      localWindowInsetsCompat = paramWindowInsetsCompat;
    }
    if (!ViewUtils.objectEquals(this.mLastInsets, localWindowInsetsCompat))
    {
      this.mLastInsets = localWindowInsetsCompat;
      invalidateScrollRanges();
    }
    return paramWindowInsetsCompat;
  }
  
  public void removeOnOffsetChangedListener(OnOffsetChangedListener paramOnOffsetChangedListener)
  {
    if ((this.mListeners != null) && (paramOnOffsetChangedListener != null)) {
      this.mListeners.remove(paramOnOffsetChangedListener);
    }
  }
  
  void resetPendingAction()
  {
    this.mPendingAction = 0;
  }
  
  boolean setCollapsedState(boolean paramBoolean)
  {
    if (this.mCollapsed != paramBoolean)
    {
      this.mCollapsed = paramBoolean;
      refreshDrawableState();
      return true;
    }
    return false;
  }
  
  public void setExpanded(boolean paramBoolean)
  {
    setExpanded(paramBoolean, ViewCompat.isLaidOut(this));
  }
  
  public void setExpanded(boolean paramBoolean1, boolean paramBoolean2)
  {
    setExpanded(paramBoolean1, paramBoolean2, true);
  }
  
  public void setOrientation(int paramInt)
  {
    if (paramInt != 1) {
      throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }
    super.setOrientation(paramInt);
  }
  
  @Deprecated
  public void setTargetElevation(float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, paramFloat);
    }
  }
  
  public static class Behavior
    extends HeaderBehavior<AppBarLayout>
  {
    private static final int INVALID_POSITION = -1;
    private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
    private WeakReference<View> mLastNestedScrollingChildRef;
    private ValueAnimatorCompat mOffsetAnimator;
    private int mOffsetDelta;
    private int mOffsetToChildIndexOnLayout = -1;
    private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
    private float mOffsetToChildIndexOnLayoutPerc;
    private DragCallback mOnDragCallback;
    private boolean mSkipNestedPreScroll;
    private boolean mWasNestedFlung;
    
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    private void animateOffsetTo(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt, float paramFloat)
    {
      int i = Math.abs(getTopBottomOffsetForScrollingSibling() - paramInt);
      paramFloat = Math.abs(paramFloat);
      if (paramFloat > 0.0F) {}
      for (i = Math.round(i / paramFloat * 1000.0F) * 3;; i = (int)((i / paramAppBarLayout.getHeight() + 1.0F) * 150.0F))
      {
        animateOffsetWithDuration(paramCoordinatorLayout, paramAppBarLayout, paramInt, i);
        return;
      }
    }
    
    private void animateOffsetWithDuration(final CoordinatorLayout paramCoordinatorLayout, final AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2)
    {
      int i = getTopBottomOffsetForScrollingSibling();
      if (i == paramInt1)
      {
        if ((this.mOffsetAnimator != null) && (this.mOffsetAnimator.isRunning())) {
          this.mOffsetAnimator.cancel();
        }
        return;
      }
      if (this.mOffsetAnimator == null)
      {
        this.mOffsetAnimator = ViewUtils.createAnimator();
        this.mOffsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        this.mOffsetAnimator.addUpdateListener(new ValueAnimatorCompat.AnimatorUpdateListener()
        {
          public void onAnimationUpdate(ValueAnimatorCompat paramAnonymousValueAnimatorCompat)
          {
            AppBarLayout.Behavior.this.setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, paramAnonymousValueAnimatorCompat.getAnimatedIntValue());
          }
        });
      }
      for (;;)
      {
        this.mOffsetAnimator.setDuration(Math.min(paramInt2, 600));
        this.mOffsetAnimator.setIntValues(i, paramInt1);
        this.mOffsetAnimator.start();
        return;
        this.mOffsetAnimator.cancel();
      }
    }
    
    private static boolean checkFlag(int paramInt1, int paramInt2)
    {
      return (paramInt1 & paramInt2) == paramInt2;
    }
    
    private static View getAppBarChildOnOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = Math.abs(paramInt);
      int j = paramAppBarLayout.getChildCount();
      paramInt = 0;
      while (paramInt < j)
      {
        View localView = paramAppBarLayout.getChildAt(paramInt);
        if ((i >= localView.getTop()) && (i <= localView.getBottom())) {
          return localView;
        }
        paramInt += 1;
      }
      return null;
    }
    
    private int getChildIndexOnOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = 0;
      int j = paramAppBarLayout.getChildCount();
      while (i < j)
      {
        View localView = paramAppBarLayout.getChildAt(i);
        if ((localView.getTop() <= -paramInt) && (localView.getBottom() >= -paramInt)) {
          return i;
        }
        i += 1;
      }
      return -1;
    }
    
    private int interpolateOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int k = Math.abs(paramInt);
      int m = paramAppBarLayout.getChildCount();
      int j = 0;
      int i = paramInt;
      View localView;
      Interpolator localInterpolator;
      if (j < m)
      {
        localView = paramAppBarLayout.getChildAt(j);
        AppBarLayout.LayoutParams localLayoutParams = (AppBarLayout.LayoutParams)localView.getLayoutParams();
        localInterpolator = localLayoutParams.getScrollInterpolator();
        if ((k < localView.getTop()) || (k > localView.getBottom())) {
          break label224;
        }
        i = paramInt;
        if (localInterpolator != null)
        {
          m = localLayoutParams.getScrollFlags();
          if ((m & 0x1) == 0) {
            break label233;
          }
          i = localView.getHeight();
          j = localLayoutParams.topMargin;
          j = localLayoutParams.bottomMargin + (i + j) + 0;
          i = j;
          if ((m & 0x2) == 0) {}
        }
      }
      label224:
      label233:
      for (i = j - ViewCompat.getMinimumHeight(localView);; i = 0)
      {
        j = i;
        if (ViewCompat.getFitsSystemWindows(localView)) {
          j = i - paramAppBarLayout.getTopInset();
        }
        i = paramInt;
        if (j > 0)
        {
          i = localView.getTop();
          float f = j;
          i = Math.round(localInterpolator.getInterpolation((k - i) / j) * f);
          i = Integer.signum(paramInt) * (i + localView.getTop());
        }
        return i;
        j += 1;
        break;
      }
    }
    
    private boolean shouldJumpElevationState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      paramCoordinatorLayout = paramCoordinatorLayout.getDependents(paramAppBarLayout);
      int j = paramCoordinatorLayout.size();
      int i = 0;
      while (i < j)
      {
        paramAppBarLayout = ((CoordinatorLayout.LayoutParams)((View)paramCoordinatorLayout.get(i)).getLayoutParams()).getBehavior();
        if ((paramAppBarLayout instanceof AppBarLayout.ScrollingViewBehavior)) {
          return ((AppBarLayout.ScrollingViewBehavior)paramAppBarLayout).getOverlayTop() != 0;
        }
        i += 1;
      }
      return false;
    }
    
    private void snapToChildIfNeeded(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      int n = getTopBottomOffsetForScrollingSibling();
      int i1 = getChildIndexOnOffset(paramAppBarLayout, n);
      View localView;
      int m;
      int k;
      int i;
      int j;
      if (i1 >= 0)
      {
        localView = paramAppBarLayout.getChildAt(i1);
        m = ((AppBarLayout.LayoutParams)localView.getLayoutParams()).getScrollFlags();
        if ((m & 0x11) == 17)
        {
          k = -localView.getTop();
          i = -localView.getBottom();
          j = i;
          if (i1 == paramAppBarLayout.getChildCount() - 1) {
            j = i + paramAppBarLayout.getTopInset();
          }
          if (!checkFlag(m, 2)) {
            break label140;
          }
          j += ViewCompat.getMinimumHeight(localView);
          i = k;
        }
      }
      for (;;)
      {
        if (n < (j + i) / 2) {}
        for (;;)
        {
          animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, MathUtils.constrain(j, -paramAppBarLayout.getTotalScrollRange(), 0), 0.0F);
          return;
          label140:
          if (!checkFlag(m, 5)) {
            break label185;
          }
          m = ViewCompat.getMinimumHeight(localView) + j;
          i = m;
          if (n < m) {
            break;
          }
          j = m;
          i = k;
          break;
          j = i;
        }
        label185:
        i = k;
      }
    }
    
    private void updateAppBarLayoutDrawableState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, boolean paramBoolean)
    {
      boolean bool2 = true;
      boolean bool3 = false;
      View localView = getAppBarChildOnOffset(paramAppBarLayout, paramInt1);
      int i;
      int j;
      if (localView != null)
      {
        i = ((AppBarLayout.LayoutParams)localView.getLayoutParams()).getScrollFlags();
        bool1 = bool3;
        if ((i & 0x1) != 0)
        {
          j = ViewCompat.getMinimumHeight(localView);
          if ((paramInt2 <= 0) || ((i & 0xC) == 0)) {
            break label129;
          }
          if (-paramInt1 < localView.getBottom() - j - paramAppBarLayout.getTopInset()) {
            break label123;
          }
          bool1 = true;
        }
      }
      label123:
      label129:
      do
      {
        for (;;)
        {
          bool1 = paramAppBarLayout.setCollapsedState(bool1);
          if ((Build.VERSION.SDK_INT >= 11) && ((paramBoolean) || ((bool1) && (shouldJumpElevationState(paramCoordinatorLayout, paramAppBarLayout))))) {
            paramAppBarLayout.jumpDrawablesToCurrentState();
          }
          return;
          bool1 = false;
        }
        bool1 = bool3;
      } while ((i & 0x2) == 0);
      if (-paramInt1 >= localView.getBottom() - j - paramAppBarLayout.getTopInset()) {}
      for (boolean bool1 = bool2;; bool1 = false) {
        break;
      }
    }
    
    boolean canDragView(AppBarLayout paramAppBarLayout)
    {
      if (this.mOnDragCallback != null) {
        return this.mOnDragCallback.canDrag(paramAppBarLayout);
      }
      if (this.mLastNestedScrollingChildRef != null)
      {
        paramAppBarLayout = (View)this.mLastNestedScrollingChildRef.get();
        return (paramAppBarLayout != null) && (paramAppBarLayout.isShown()) && (!ViewCompat.canScrollVertically(paramAppBarLayout, -1));
      }
      return true;
    }
    
    int getMaxDragOffset(AppBarLayout paramAppBarLayout)
    {
      return -paramAppBarLayout.getDownNestedScrollRange();
    }
    
    int getScrollRangeForDragFling(AppBarLayout paramAppBarLayout)
    {
      return paramAppBarLayout.getTotalScrollRange();
    }
    
    int getTopBottomOffsetForScrollingSibling()
    {
      return getTopAndBottomOffset() + this.mOffsetDelta;
    }
    
    @VisibleForTesting
    boolean isOffsetAnimatorRunning()
    {
      return (this.mOffsetAnimator != null) && (this.mOffsetAnimator.isRunning());
    }
    
    void onFlingFinished(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      snapToChildIfNeeded(paramCoordinatorLayout, paramAppBarLayout);
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt)
    {
      boolean bool = super.onLayoutChild(paramCoordinatorLayout, paramAppBarLayout, paramInt);
      int i = paramAppBarLayout.getPendingAction();
      View localView;
      if ((this.mOffsetToChildIndexOnLayout >= 0) && ((i & 0x8) == 0))
      {
        localView = paramAppBarLayout.getChildAt(this.mOffsetToChildIndexOnLayout);
        paramInt = -localView.getBottom();
        if (this.mOffsetToChildIndexOnLayoutIsMinHeight)
        {
          paramInt = ViewCompat.getMinimumHeight(localView) + paramAppBarLayout.getTopInset() + paramInt;
          setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, paramInt);
        }
      }
      for (;;)
      {
        paramAppBarLayout.resetPendingAction();
        this.mOffsetToChildIndexOnLayout = -1;
        setTopAndBottomOffset(MathUtils.constrain(getTopAndBottomOffset(), -paramAppBarLayout.getTotalScrollRange(), 0));
        updateAppBarLayoutDrawableState(paramCoordinatorLayout, paramAppBarLayout, getTopAndBottomOffset(), 0, true);
        paramAppBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
        return bool;
        paramInt = Math.round(localView.getHeight() * this.mOffsetToChildIndexOnLayoutPerc) + paramInt;
        break;
        if (i != 0)
        {
          if ((i & 0x4) != 0) {
            paramInt = 1;
          }
          for (;;)
          {
            if ((i & 0x2) != 0)
            {
              i = -paramAppBarLayout.getUpNestedPreScrollRange();
              if (paramInt != 0)
              {
                animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i, 0.0F);
                break;
                paramInt = 0;
                continue;
              }
              setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, i);
              break;
            }
          }
          if ((i & 0x1) != 0) {
            if (paramInt != 0) {
              animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, 0, 0.0F);
            } else {
              setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, 0);
            }
          }
        }
      }
    }
    
    public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (((CoordinatorLayout.LayoutParams)paramAppBarLayout.getLayoutParams()).height == -2)
      {
        paramCoordinatorLayout.onMeasureChild(paramAppBarLayout, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(0, 0), paramInt4);
        return true;
      }
      return super.onMeasureChild(paramCoordinatorLayout, paramAppBarLayout, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    public boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      boolean bool = false;
      if (!paramBoolean) {
        paramBoolean = fling(paramCoordinatorLayout, paramAppBarLayout, -paramAppBarLayout.getTotalScrollRange(), 0, -paramFloat2);
      }
      for (;;)
      {
        this.mWasNestedFlung = paramBoolean;
        return paramBoolean;
        int i;
        if (paramFloat2 < 0.0F)
        {
          i = -paramAppBarLayout.getTotalScrollRange() + paramAppBarLayout.getDownNestedPreScrollRange();
          paramBoolean = bool;
          if (getTopBottomOffsetForScrollingSibling() < i)
          {
            animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i, paramFloat2);
            paramBoolean = true;
          }
        }
        else
        {
          i = -paramAppBarLayout.getUpNestedPreScrollRange();
          paramBoolean = bool;
          if (getTopBottomOffsetForScrollingSibling() > i)
          {
            animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, i, paramFloat2);
            paramBoolean = true;
          }
        }
      }
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
    {
      if ((paramInt2 != 0) && (!this.mSkipNestedPreScroll))
      {
        if (paramInt2 >= 0) {
          break label50;
        }
        paramInt1 = -paramAppBarLayout.getTotalScrollRange();
      }
      for (int i = paramInt1 + paramAppBarLayout.getDownNestedPreScrollRange();; i = 0)
      {
        paramArrayOfInt[1] = scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt2, paramInt1, i);
        return;
        label50:
        paramInt1 = -paramAppBarLayout.getUpNestedPreScrollRange();
      }
    }
    
    public void onNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (paramInt4 < 0)
      {
        scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt4, -paramAppBarLayout.getDownNestedScrollRange(), 0);
        this.mSkipNestedPreScroll = true;
        return;
      }
      this.mSkipNestedPreScroll = false;
    }
    
    public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, Parcelable paramParcelable)
    {
      if ((paramParcelable instanceof SavedState))
      {
        paramParcelable = (SavedState)paramParcelable;
        super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable.getSuperState());
        this.mOffsetToChildIndexOnLayout = paramParcelable.firstVisibleChildIndex;
        this.mOffsetToChildIndexOnLayoutPerc = paramParcelable.firstVisibleChildPercentageShown;
        this.mOffsetToChildIndexOnLayoutIsMinHeight = paramParcelable.firstVisibleChildAtMinimumHeight;
        return;
      }
      super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable);
      this.mOffsetToChildIndexOnLayout = -1;
    }
    
    public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      boolean bool = false;
      Object localObject = super.onSaveInstanceState(paramCoordinatorLayout, paramAppBarLayout);
      int j = getTopAndBottomOffset();
      int k = paramAppBarLayout.getChildCount();
      int i = 0;
      while (i < k)
      {
        paramCoordinatorLayout = paramAppBarLayout.getChildAt(i);
        int m = paramCoordinatorLayout.getBottom() + j;
        if ((paramCoordinatorLayout.getTop() + j <= 0) && (m >= 0))
        {
          localObject = new SavedState((Parcelable)localObject);
          ((SavedState)localObject).firstVisibleChildIndex = i;
          if (m == ViewCompat.getMinimumHeight(paramCoordinatorLayout) + paramAppBarLayout.getTopInset()) {
            bool = true;
          }
          ((SavedState)localObject).firstVisibleChildAtMinimumHeight = bool;
          ((SavedState)localObject).firstVisibleChildPercentageShown = (m / paramCoordinatorLayout.getHeight());
          return (Parcelable)localObject;
        }
        i += 1;
      }
      return (Parcelable)localObject;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView1, View paramView2, int paramInt)
    {
      if (((paramInt & 0x2) != 0) && (paramAppBarLayout.hasScrollableChildren()) && (paramCoordinatorLayout.getHeight() - paramView1.getHeight() <= paramAppBarLayout.getHeight())) {}
      for (boolean bool = true;; bool = false)
      {
        if ((bool) && (this.mOffsetAnimator != null)) {
          this.mOffsetAnimator.cancel();
        }
        this.mLastNestedScrollingChildRef = null;
        return bool;
      }
    }
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView)
    {
      if (!this.mWasNestedFlung) {
        snapToChildIfNeeded(paramCoordinatorLayout, paramAppBarLayout);
      }
      this.mSkipNestedPreScroll = false;
      this.mWasNestedFlung = false;
      this.mLastNestedScrollingChildRef = new WeakReference(paramView);
    }
    
    public void setDragCallback(@Nullable DragCallback paramDragCallback)
    {
      this.mOnDragCallback = paramDragCallback;
    }
    
    int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, int paramInt3)
    {
      int i = 0;
      int j = getTopBottomOffsetForScrollingSibling();
      if ((paramInt2 != 0) && (j >= paramInt2) && (j <= paramInt3))
      {
        paramInt2 = MathUtils.constrain(paramInt1, paramInt2, paramInt3);
        paramInt1 = i;
        if (j != paramInt2)
        {
          if (!paramAppBarLayout.hasChildWithInterpolator()) {
            break label130;
          }
          paramInt1 = interpolateOffset(paramAppBarLayout, paramInt2);
          boolean bool = setTopAndBottomOffset(paramInt1);
          this.mOffsetDelta = (paramInt2 - paramInt1);
          if ((!bool) && (paramAppBarLayout.hasChildWithInterpolator())) {
            paramCoordinatorLayout.dispatchDependentViewsChanged(paramAppBarLayout);
          }
          paramAppBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
          if (paramInt2 >= j) {
            break label136;
          }
        }
        label130:
        label136:
        for (paramInt1 = -1;; paramInt1 = 1)
        {
          updateAppBarLayoutDrawableState(paramCoordinatorLayout, paramAppBarLayout, paramInt2, paramInt1, false);
          paramInt1 = j - paramInt2;
          return paramInt1;
          paramInt1 = paramInt2;
          break;
        }
      }
      this.mOffsetDelta = 0;
      return 0;
    }
    
    public static abstract class DragCallback
    {
      public abstract boolean canDrag(@NonNull AppBarLayout paramAppBarLayout);
    }
    
    protected static class SavedState
      extends AbsSavedState
    {
      public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
      {
        public AppBarLayout.Behavior.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
        {
          return new AppBarLayout.Behavior.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
        }
        
        public AppBarLayout.Behavior.SavedState[] newArray(int paramAnonymousInt)
        {
          return new AppBarLayout.Behavior.SavedState[paramAnonymousInt];
        }
      });
      boolean firstVisibleChildAtMinimumHeight;
      int firstVisibleChildIndex;
      float firstVisibleChildPercentageShown;
      
      public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        super(paramClassLoader);
        this.firstVisibleChildIndex = paramParcel.readInt();
        this.firstVisibleChildPercentageShown = paramParcel.readFloat();
        if (paramParcel.readByte() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          this.firstVisibleChildAtMinimumHeight = bool;
          return;
        }
      }
      
      public SavedState(Parcelable paramParcelable)
      {
        super();
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        super.writeToParcel(paramParcel, paramInt);
        paramParcel.writeInt(this.firstVisibleChildIndex);
        paramParcel.writeFloat(this.firstVisibleChildPercentageShown);
        if (this.firstVisibleChildAtMinimumHeight) {}
        for (paramInt = 1;; paramInt = 0)
        {
          paramParcel.writeByte((byte)paramInt);
          return;
        }
      }
    }
  }
  
  public static class LayoutParams
    extends LinearLayout.LayoutParams
  {
    static final int COLLAPSIBLE_FLAGS = 10;
    static final int FLAG_QUICK_RETURN = 5;
    static final int FLAG_SNAP = 17;
    public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
    public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
    public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
    public static final int SCROLL_FLAG_SCROLL = 1;
    public static final int SCROLL_FLAG_SNAP = 16;
    int mScrollFlags = 1;
    Interpolator mScrollInterpolator;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(int paramInt1, int paramInt2, float paramFloat)
    {
      super(paramInt2, paramFloat);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppBarLayout_Layout);
      this.mScrollFlags = paramAttributeSet.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
      if (paramAttributeSet.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
        this.mScrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(paramContext, paramAttributeSet.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
      }
      paramAttributeSet.recycle();
    }
    
    @TargetApi(19)
    @RequiresApi(19)
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.mScrollFlags = paramLayoutParams.mScrollFlags;
      this.mScrollInterpolator = paramLayoutParams.mScrollInterpolator;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    @TargetApi(19)
    @RequiresApi(19)
    public LayoutParams(LinearLayout.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public int getScrollFlags()
    {
      return this.mScrollFlags;
    }
    
    public Interpolator getScrollInterpolator()
    {
      return this.mScrollInterpolator;
    }
    
    boolean isCollapsible()
    {
      return ((this.mScrollFlags & 0x1) == 1) && ((this.mScrollFlags & 0xA) != 0);
    }
    
    public void setScrollFlags(int paramInt)
    {
      this.mScrollFlags = paramInt;
    }
    
    public void setScrollInterpolator(Interpolator paramInterpolator)
    {
      this.mScrollInterpolator = paramInterpolator;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface ScrollFlags {}
  }
  
  public static abstract interface OnOffsetChangedListener
  {
    public abstract void onOffsetChanged(AppBarLayout paramAppBarLayout, int paramInt);
  }
  
  public static class ScrollingViewBehavior
    extends HeaderScrollingViewBehavior
  {
    public ScrollingViewBehavior() {}
    
    public ScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ScrollingViewBehavior_Layout);
      setOverlayTop(paramContext.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
      paramContext.recycle();
    }
    
    private static int getAppBarLayoutOffset(AppBarLayout paramAppBarLayout)
    {
      paramAppBarLayout = ((CoordinatorLayout.LayoutParams)paramAppBarLayout.getLayoutParams()).getBehavior();
      if ((paramAppBarLayout instanceof AppBarLayout.Behavior)) {
        return ((AppBarLayout.Behavior)paramAppBarLayout).getTopBottomOffsetForScrollingSibling();
      }
      return 0;
    }
    
    private void offsetChildAsNeeded(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      paramCoordinatorLayout = ((CoordinatorLayout.LayoutParams)paramView2.getLayoutParams()).getBehavior();
      if ((paramCoordinatorLayout instanceof AppBarLayout.Behavior))
      {
        paramCoordinatorLayout = (AppBarLayout.Behavior)paramCoordinatorLayout;
        int i = paramView2.getBottom();
        int j = paramView1.getTop();
        ViewCompat.offsetTopAndBottom(paramView1, paramCoordinatorLayout.mOffsetDelta + (i - j) + getVerticalLayoutGap() - getOverlapPixelsForOffset(paramView2));
      }
    }
    
    AppBarLayout findFirstDependency(List<View> paramList)
    {
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        View localView = (View)paramList.get(i);
        if ((localView instanceof AppBarLayout)) {
          return (AppBarLayout)localView;
        }
        i += 1;
      }
      return null;
    }
    
    float getOverlapRatioForOffset(View paramView)
    {
      int j;
      int k;
      int i;
      if ((paramView instanceof AppBarLayout))
      {
        paramView = (AppBarLayout)paramView;
        j = paramView.getTotalScrollRange();
        k = paramView.getDownNestedPreScrollRange();
        i = getAppBarLayoutOffset(paramView);
        if ((k == 0) || (j + i > k)) {
          break label43;
        }
      }
      label43:
      do
      {
        return 0.0F;
        j -= k;
      } while (j == 0);
      return 1.0F + i / j;
    }
    
    int getScrollRange(View paramView)
    {
      if ((paramView instanceof AppBarLayout)) {
        return ((AppBarLayout)paramView).getTotalScrollRange();
      }
      return super.getScrollRange(paramView);
    }
    
    public boolean layoutDependsOn(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return paramView2 instanceof AppBarLayout;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      offsetChildAsNeeded(paramCoordinatorLayout, paramView1, paramView2);
      return false;
    }
    
    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout paramCoordinatorLayout, View paramView, Rect paramRect, boolean paramBoolean)
    {
      AppBarLayout localAppBarLayout = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
      if (localAppBarLayout != null)
      {
        paramRect.offset(paramView.getLeft(), paramView.getTop());
        paramView = this.mTempRect1;
        paramView.set(0, 0, paramCoordinatorLayout.getWidth(), paramCoordinatorLayout.getHeight());
        if (!paramView.contains(paramRect))
        {
          if (!paramBoolean) {}
          for (paramBoolean = true;; paramBoolean = false)
          {
            localAppBarLayout.setExpanded(false, paramBoolean);
            return true;
          }
        }
      }
      return false;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\AppBarLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */