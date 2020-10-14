package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.design.R.dimen;
import android.support.design.R.styleable;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class BottomSheetBehavior<V extends View>
  extends CoordinatorLayout.Behavior<V>
{
  private static final float HIDE_FRICTION = 0.1F;
  private static final float HIDE_THRESHOLD = 0.5F;
  public static final int PEEK_HEIGHT_AUTO = -1;
  public static final int STATE_COLLAPSED = 4;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_EXPANDED = 3;
  public static final int STATE_HIDDEN = 5;
  public static final int STATE_SETTLING = 2;
  int mActivePointerId;
  private BottomSheetCallback mCallback;
  private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback()
  {
    public int clampViewPositionHorizontal(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return paramAnonymousView.getLeft();
    }
    
    public int clampViewPositionVertical(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      int i = BottomSheetBehavior.this.mMinOffset;
      if (BottomSheetBehavior.this.mHideable) {}
      for (paramAnonymousInt2 = BottomSheetBehavior.this.mParentHeight;; paramAnonymousInt2 = BottomSheetBehavior.this.mMaxOffset) {
        return MathUtils.constrain(paramAnonymousInt1, i, paramAnonymousInt2);
      }
    }
    
    public int getViewVerticalDragRange(View paramAnonymousView)
    {
      if (BottomSheetBehavior.this.mHideable) {
        return BottomSheetBehavior.this.mParentHeight - BottomSheetBehavior.this.mMinOffset;
      }
      return BottomSheetBehavior.this.mMaxOffset - BottomSheetBehavior.this.mMinOffset;
    }
    
    public void onViewDragStateChanged(int paramAnonymousInt)
    {
      if (paramAnonymousInt == 1) {
        BottomSheetBehavior.this.setStateInternal(1);
      }
    }
    
    public void onViewPositionChanged(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      BottomSheetBehavior.this.dispatchOnSlide(paramAnonymousInt2);
    }
    
    public void onViewReleased(View paramAnonymousView, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      int j = 3;
      int i;
      if (paramAnonymousFloat2 < 0.0F) {
        i = BottomSheetBehavior.this.mMinOffset;
      }
      while (BottomSheetBehavior.this.mViewDragHelper.settleCapturedViewAt(paramAnonymousView.getLeft(), i))
      {
        BottomSheetBehavior.this.setStateInternal(2);
        ViewCompat.postOnAnimation(paramAnonymousView, new BottomSheetBehavior.SettleRunnable(BottomSheetBehavior.this, paramAnonymousView, j));
        return;
        if ((BottomSheetBehavior.this.mHideable) && (BottomSheetBehavior.this.shouldHide(paramAnonymousView, paramAnonymousFloat2)))
        {
          i = BottomSheetBehavior.this.mParentHeight;
          j = 5;
        }
        else if (paramAnonymousFloat2 == 0.0F)
        {
          i = paramAnonymousView.getTop();
          if (Math.abs(i - BottomSheetBehavior.this.mMinOffset) < Math.abs(i - BottomSheetBehavior.this.mMaxOffset))
          {
            i = BottomSheetBehavior.this.mMinOffset;
          }
          else
          {
            i = BottomSheetBehavior.this.mMaxOffset;
            j = 4;
          }
        }
        else
        {
          i = BottomSheetBehavior.this.mMaxOffset;
          j = 4;
        }
      }
      BottomSheetBehavior.this.setStateInternal(j);
    }
    
    public boolean tryCaptureView(View paramAnonymousView, int paramAnonymousInt)
    {
      if (BottomSheetBehavior.this.mState == 1) {}
      View localView;
      do
      {
        do
        {
          return false;
        } while (BottomSheetBehavior.this.mTouchingScrollingChild);
        if ((BottomSheetBehavior.this.mState != 3) || (BottomSheetBehavior.this.mActivePointerId != paramAnonymousInt)) {
          break;
        }
        localView = (View)BottomSheetBehavior.this.mNestedScrollingChildRef.get();
      } while ((localView != null) && (ViewCompat.canScrollVertically(localView, -1)));
      if ((BottomSheetBehavior.this.mViewRef != null) && (BottomSheetBehavior.this.mViewRef.get() == paramAnonymousView)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  };
  boolean mHideable;
  private boolean mIgnoreEvents;
  private int mInitialY;
  private int mLastNestedScrollDy;
  int mMaxOffset;
  private float mMaximumVelocity;
  int mMinOffset;
  private boolean mNestedScrolled;
  WeakReference<View> mNestedScrollingChildRef;
  int mParentHeight;
  private int mPeekHeight;
  private boolean mPeekHeightAuto;
  private int mPeekHeightMin;
  private boolean mSkipCollapsed;
  int mState = 4;
  boolean mTouchingScrollingChild;
  private VelocityTracker mVelocityTracker;
  ViewDragHelper mViewDragHelper;
  WeakReference<V> mViewRef;
  
  public BottomSheetBehavior() {}
  
  public BottomSheetBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.BottomSheetBehavior_Layout);
    TypedValue localTypedValue = paramAttributeSet.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
    if ((localTypedValue != null) && (localTypedValue.data == -1)) {
      setPeekHeight(localTypedValue.data);
    }
    for (;;)
    {
      setHideable(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
      setSkipCollapsed(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
      paramAttributeSet.recycle();
      this.mMaximumVelocity = ViewConfiguration.get(paramContext).getScaledMaximumFlingVelocity();
      return;
      setPeekHeight(paramAttributeSet.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
    }
  }
  
  private View findScrollingChild(View paramView)
  {
    if ((paramView instanceof NestedScrollingChild)) {
      return paramView;
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = paramView.getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = findScrollingChild(paramView.getChildAt(i));
        if (localView != null) {
          return localView;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static <V extends View> BottomSheetBehavior<V> from(V paramV)
  {
    paramV = paramV.getLayoutParams();
    if (!(paramV instanceof CoordinatorLayout.LayoutParams)) {
      throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
    paramV = ((CoordinatorLayout.LayoutParams)paramV).getBehavior();
    if (!(paramV instanceof BottomSheetBehavior)) {
      throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }
    return (BottomSheetBehavior)paramV;
  }
  
  private float getYVelocity()
  {
    this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
    return VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
  }
  
  private void reset()
  {
    this.mActivePointerId = -1;
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }
  
  void dispatchOnSlide(int paramInt)
  {
    View localView = (View)this.mViewRef.get();
    if ((localView != null) && (this.mCallback != null))
    {
      if (paramInt > this.mMaxOffset) {
        this.mCallback.onSlide(localView, (this.mMaxOffset - paramInt) / (this.mParentHeight - this.mMaxOffset));
      }
    }
    else {
      return;
    }
    this.mCallback.onSlide(localView, (this.mMaxOffset - paramInt) / (this.mMaxOffset - this.mMinOffset));
  }
  
  public final int getPeekHeight()
  {
    if (this.mPeekHeightAuto) {
      return -1;
    }
    return this.mPeekHeight;
  }
  
  @VisibleForTesting
  int getPeekHeightMin()
  {
    return this.mPeekHeightMin;
  }
  
  public boolean getSkipCollapsed()
  {
    return this.mSkipCollapsed;
  }
  
  public final int getState()
  {
    return this.mState;
  }
  
  public boolean isHideable()
  {
    return this.mHideable;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    if (!paramV.isShown())
    {
      this.mIgnoreEvents = true;
      return false;
    }
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      reset();
    }
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i)
    {
    }
    while ((!this.mIgnoreEvents) && (this.mViewDragHelper.shouldInterceptTouchEvent(paramMotionEvent)))
    {
      return true;
      this.mTouchingScrollingChild = false;
      this.mActivePointerId = -1;
      if (this.mIgnoreEvents)
      {
        this.mIgnoreEvents = false;
        return false;
        int j = (int)paramMotionEvent.getX();
        this.mInitialY = ((int)paramMotionEvent.getY());
        View localView = (View)this.mNestedScrollingChildRef.get();
        if ((localView != null) && (paramCoordinatorLayout.isPointInChildBounds(localView, j, this.mInitialY)))
        {
          this.mActivePointerId = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
          this.mTouchingScrollingChild = true;
        }
        if ((this.mActivePointerId == -1) && (!paramCoordinatorLayout.isPointInChildBounds(paramV, j, this.mInitialY))) {}
        for (bool1 = true;; bool1 = false)
        {
          this.mIgnoreEvents = bool1;
          break;
        }
      }
    }
    paramV = (View)this.mNestedScrollingChildRef.get();
    if ((i == 2) && (paramV != null) && (!this.mIgnoreEvents) && (this.mState != 1) && (!paramCoordinatorLayout.isPointInChildBounds(paramV, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) && (Math.abs(this.mInitialY - paramMotionEvent.getY()) > this.mViewDragHelper.getTouchSlop())) {}
    for (boolean bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, V paramV, int paramInt)
  {
    if ((ViewCompat.getFitsSystemWindows(paramCoordinatorLayout)) && (!ViewCompat.getFitsSystemWindows(paramV))) {
      ViewCompat.setFitsSystemWindows(paramV, true);
    }
    int i = paramV.getTop();
    paramCoordinatorLayout.onLayoutChild(paramV, paramInt);
    this.mParentHeight = paramCoordinatorLayout.getHeight();
    if (this.mPeekHeightAuto)
    {
      if (this.mPeekHeightMin == 0) {
        this.mPeekHeightMin = paramCoordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
      }
      paramInt = Math.max(this.mPeekHeightMin, this.mParentHeight - paramCoordinatorLayout.getWidth() * 9 / 16);
      this.mMinOffset = Math.max(0, this.mParentHeight - paramV.getHeight());
      this.mMaxOffset = Math.max(this.mParentHeight - paramInt, this.mMinOffset);
      if (this.mState != 3) {
        break label197;
      }
      ViewCompat.offsetTopAndBottom(paramV, this.mMinOffset);
    }
    for (;;)
    {
      if (this.mViewDragHelper == null) {
        this.mViewDragHelper = ViewDragHelper.create(paramCoordinatorLayout, this.mDragCallback);
      }
      this.mViewRef = new WeakReference(paramV);
      this.mNestedScrollingChildRef = new WeakReference(findScrollingChild(paramV));
      return true;
      paramInt = this.mPeekHeight;
      break;
      label197:
      if ((this.mHideable) && (this.mState == 5)) {
        ViewCompat.offsetTopAndBottom(paramV, this.mParentHeight);
      } else if (this.mState == 4) {
        ViewCompat.offsetTopAndBottom(paramV, this.mMaxOffset);
      } else if ((this.mState == 1) || (this.mState == 2)) {
        ViewCompat.offsetTopAndBottom(paramV, i - paramV.getTop());
      }
    }
  }
  
  public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, float paramFloat1, float paramFloat2)
  {
    return (paramView == this.mNestedScrollingChildRef.get()) && ((this.mState != 3) || (super.onNestedPreFling(paramCoordinatorLayout, paramV, paramView, paramFloat1, paramFloat2)));
  }
  
  public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if (paramView != (View)this.mNestedScrollingChildRef.get()) {
      return;
    }
    paramInt1 = paramV.getTop();
    int i = paramInt1 - paramInt2;
    if (paramInt2 > 0) {
      if (i < this.mMinOffset)
      {
        paramArrayOfInt[1] = (paramInt1 - this.mMinOffset);
        ViewCompat.offsetTopAndBottom(paramV, -paramArrayOfInt[1]);
        setStateInternal(3);
      }
    }
    for (;;)
    {
      dispatchOnSlide(paramV.getTop());
      this.mLastNestedScrollDy = paramInt2;
      this.mNestedScrolled = true;
      return;
      paramArrayOfInt[1] = paramInt2;
      ViewCompat.offsetTopAndBottom(paramV, -paramInt2);
      setStateInternal(1);
      continue;
      if ((paramInt2 < 0) && (!ViewCompat.canScrollVertically(paramView, -1))) {
        if ((i <= this.mMaxOffset) || (this.mHideable))
        {
          paramArrayOfInt[1] = paramInt2;
          ViewCompat.offsetTopAndBottom(paramV, -paramInt2);
          setStateInternal(1);
        }
        else
        {
          paramArrayOfInt[1] = (paramInt1 - this.mMaxOffset);
          ViewCompat.offsetTopAndBottom(paramV, -paramArrayOfInt[1]);
          setStateInternal(4);
        }
      }
    }
  }
  
  public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV, Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramCoordinatorLayout, paramV, paramParcelable.getSuperState());
    if ((paramParcelable.state == 1) || (paramParcelable.state == 2))
    {
      this.mState = 4;
      return;
    }
    this.mState = paramParcelable.state;
  }
  
  public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, V paramV)
  {
    return new SavedState(super.onSaveInstanceState(paramCoordinatorLayout, paramV), this.mState);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView1, View paramView2, int paramInt)
  {
    boolean bool = false;
    this.mLastNestedScrollDy = 0;
    this.mNestedScrolled = false;
    if ((paramInt & 0x2) != 0) {
      bool = true;
    }
    return bool;
  }
  
  public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, V paramV, View paramView)
  {
    int j = 3;
    if (paramV.getTop() == this.mMinOffset) {
      setStateInternal(3);
    }
    while ((paramView != this.mNestedScrollingChildRef.get()) || (!this.mNestedScrolled)) {
      return;
    }
    int i;
    if (this.mLastNestedScrollDy > 0)
    {
      i = this.mMinOffset;
      if (!this.mViewDragHelper.smoothSlideViewTo(paramV, paramV.getLeft(), i)) {
        break label194;
      }
      setStateInternal(2);
      ViewCompat.postOnAnimation(paramV, new SettleRunnable(paramV, j));
    }
    for (;;)
    {
      this.mNestedScrolled = false;
      return;
      if ((this.mHideable) && (shouldHide(paramV, getYVelocity())))
      {
        i = this.mParentHeight;
        j = 5;
        break;
      }
      if (this.mLastNestedScrollDy == 0)
      {
        i = paramV.getTop();
        if (Math.abs(i - this.mMinOffset) < Math.abs(i - this.mMaxOffset))
        {
          i = this.mMinOffset;
          break;
        }
        i = this.mMaxOffset;
        j = 4;
        break;
      }
      i = this.mMaxOffset;
      j = 4;
      break;
      label194:
      setStateInternal(j);
    }
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, V paramV, MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!paramV.isShown()) {
      bool1 = false;
    }
    do
    {
      int i;
      do
      {
        return bool1;
        i = MotionEventCompat.getActionMasked(paramMotionEvent);
        if (this.mState != 1) {
          break;
        }
        bool1 = bool2;
      } while (i == 0);
      this.mViewDragHelper.processTouchEvent(paramMotionEvent);
      if (i == 0) {
        reset();
      }
      if (this.mVelocityTracker == null) {
        this.mVelocityTracker = VelocityTracker.obtain();
      }
      this.mVelocityTracker.addMovement(paramMotionEvent);
      if ((i == 2) && (!this.mIgnoreEvents) && (Math.abs(this.mInitialY - paramMotionEvent.getY()) > this.mViewDragHelper.getTouchSlop())) {
        this.mViewDragHelper.captureChildView(paramV, paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex()));
      }
      bool1 = bool2;
    } while (!this.mIgnoreEvents);
    return false;
  }
  
  public void setBottomSheetCallback(BottomSheetCallback paramBottomSheetCallback)
  {
    this.mCallback = paramBottomSheetCallback;
  }
  
  public void setHideable(boolean paramBoolean)
  {
    this.mHideable = paramBoolean;
  }
  
  public final void setPeekHeight(int paramInt)
  {
    int i = 1;
    if (paramInt == -1)
    {
      if (this.mPeekHeightAuto) {
        break label104;
      }
      this.mPeekHeightAuto = true;
      paramInt = i;
    }
    for (;;)
    {
      if ((paramInt != 0) && (this.mState == 4) && (this.mViewRef != null))
      {
        View localView = (View)this.mViewRef.get();
        if (localView != null) {
          localView.requestLayout();
        }
      }
      return;
      if ((this.mPeekHeightAuto) || (this.mPeekHeight != paramInt))
      {
        this.mPeekHeightAuto = false;
        this.mPeekHeight = Math.max(0, paramInt);
        this.mMaxOffset = (this.mParentHeight - paramInt);
        paramInt = i;
      }
      else
      {
        label104:
        paramInt = 0;
      }
    }
  }
  
  public void setSkipCollapsed(boolean paramBoolean)
  {
    this.mSkipCollapsed = paramBoolean;
  }
  
  public final void setState(final int paramInt)
  {
    if (paramInt == this.mState) {}
    final View localView;
    do
    {
      do
      {
        return;
        if (this.mViewRef != null) {
          break;
        }
      } while ((paramInt != 4) && (paramInt != 3) && ((!this.mHideable) || (paramInt != 5)));
      this.mState = paramInt;
      return;
      localView = (View)this.mViewRef.get();
    } while (localView == null);
    ViewParent localViewParent = localView.getParent();
    if ((localViewParent != null) && (localViewParent.isLayoutRequested()) && (ViewCompat.isAttachedToWindow(localView)))
    {
      localView.post(new Runnable()
      {
        public void run()
        {
          BottomSheetBehavior.this.startSettlingAnimation(localView, paramInt);
        }
      });
      return;
    }
    startSettlingAnimation(localView, paramInt);
  }
  
  void setStateInternal(int paramInt)
  {
    if (this.mState == paramInt) {}
    View localView;
    do
    {
      return;
      this.mState = paramInt;
      localView = (View)this.mViewRef.get();
    } while ((localView == null) || (this.mCallback == null));
    this.mCallback.onStateChanged(localView, paramInt);
  }
  
  boolean shouldHide(View paramView, float paramFloat)
  {
    if (this.mSkipCollapsed) {}
    do
    {
      return true;
      if (paramView.getTop() < this.mMaxOffset) {
        return false;
      }
    } while (Math.abs(paramView.getTop() + 0.1F * paramFloat - this.mMaxOffset) / this.mPeekHeight > 0.5F);
    return false;
  }
  
  void startSettlingAnimation(View paramView, int paramInt)
  {
    int i;
    if (paramInt == 4) {
      i = this.mMaxOffset;
    }
    for (;;)
    {
      setStateInternal(2);
      if (this.mViewDragHelper.smoothSlideViewTo(paramView, paramView.getLeft(), i)) {
        ViewCompat.postOnAnimation(paramView, new SettleRunnable(paramView, paramInt));
      }
      return;
      if (paramInt == 3)
      {
        i = this.mMinOffset;
      }
      else
      {
        if ((!this.mHideable) || (paramInt != 5)) {
          break;
        }
        i = this.mParentHeight;
      }
    }
    throw new IllegalArgumentException("Illegal state argument: " + paramInt);
  }
  
  public static abstract class BottomSheetCallback
  {
    public abstract void onSlide(@NonNull View paramView, float paramFloat);
    
    public abstract void onStateChanged(@NonNull View paramView, int paramInt);
  }
  
  protected static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
    {
      public BottomSheetBehavior.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new BottomSheetBehavior.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public BottomSheetBehavior.SavedState[] newArray(int paramAnonymousInt)
      {
        return new BottomSheetBehavior.SavedState[paramAnonymousInt];
      }
    });
    final int state;
    
    public SavedState(Parcel paramParcel)
    {
      this(paramParcel, null);
    }
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      this.state = paramParcel.readInt();
    }
    
    public SavedState(Parcelable paramParcelable, int paramInt)
    {
      super();
      this.state = paramInt;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.state);
    }
  }
  
  private class SettleRunnable
    implements Runnable
  {
    private final int mTargetState;
    private final View mView;
    
    SettleRunnable(View paramView, int paramInt)
    {
      this.mView = paramView;
      this.mTargetState = paramInt;
    }
    
    public void run()
    {
      if ((BottomSheetBehavior.this.mViewDragHelper != null) && (BottomSheetBehavior.this.mViewDragHelper.continueSettling(true)))
      {
        ViewCompat.postOnAnimation(this.mView, this);
        return;
      }
      BottomSheetBehavior.this.setStateInternal(this.mTargetState);
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface State {}
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\BottomSheetBehavior.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */