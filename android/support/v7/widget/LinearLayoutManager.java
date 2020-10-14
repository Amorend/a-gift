package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v7.widget.helper.ItemTouchHelper.ViewDropHandler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager
  extends RecyclerView.LayoutManager
  implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider
{
  static final boolean DEBUG = false;
  public static final int HORIZONTAL = 0;
  public static final int INVALID_OFFSET = Integer.MIN_VALUE;
  private static final float MAX_SCROLL_FACTOR = 0.33333334F;
  private static final String TAG = "LinearLayoutManager";
  public static final int VERTICAL = 1;
  final AnchorInfo mAnchorInfo = new AnchorInfo();
  private int mInitialPrefetchItemCount = 2;
  private boolean mLastStackFromEnd;
  private final LayoutChunkResult mLayoutChunkResult = new LayoutChunkResult();
  private LayoutState mLayoutState;
  int mOrientation;
  OrientationHelper mOrientationHelper;
  SavedState mPendingSavedState = null;
  int mPendingScrollPosition = -1;
  int mPendingScrollPositionOffset = Integer.MIN_VALUE;
  private boolean mRecycleChildrenOnDetach;
  private boolean mReverseLayout = false;
  boolean mShouldReverseLayout = false;
  private boolean mSmoothScrollbarEnabled = true;
  private boolean mStackFromEnd = false;
  
  public LinearLayoutManager(Context paramContext)
  {
    this(paramContext, 1, false);
  }
  
  public LinearLayoutManager(Context paramContext, int paramInt, boolean paramBoolean)
  {
    setOrientation(paramInt);
    setReverseLayout(paramBoolean);
    setAutoMeasureEnabled(true);
  }
  
  public LinearLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setOrientation(paramContext.orientation);
    setReverseLayout(paramContext.reverseLayout);
    setStackFromEnd(paramContext.stackFromEnd);
    setAutoMeasureEnabled(true);
  }
  
  private int computeScrollExtent(RecyclerView.State paramState)
  {
    boolean bool2 = false;
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    OrientationHelper localOrientationHelper = this.mOrientationHelper;
    if (!this.mSmoothScrollbarEnabled) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = findFirstVisibleChildClosestToStart(bool1, true);
      bool1 = bool2;
      if (!this.mSmoothScrollbarEnabled) {
        bool1 = true;
      }
      return ScrollbarHelper.computeScrollExtent(paramState, localOrientationHelper, localView, findFirstVisibleChildClosestToEnd(bool1, true), this, this.mSmoothScrollbarEnabled);
    }
  }
  
  private int computeScrollOffset(RecyclerView.State paramState)
  {
    boolean bool2 = false;
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    OrientationHelper localOrientationHelper = this.mOrientationHelper;
    if (!this.mSmoothScrollbarEnabled) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = findFirstVisibleChildClosestToStart(bool1, true);
      bool1 = bool2;
      if (!this.mSmoothScrollbarEnabled) {
        bool1 = true;
      }
      return ScrollbarHelper.computeScrollOffset(paramState, localOrientationHelper, localView, findFirstVisibleChildClosestToEnd(bool1, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }
  }
  
  private int computeScrollRange(RecyclerView.State paramState)
  {
    boolean bool2 = false;
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    OrientationHelper localOrientationHelper = this.mOrientationHelper;
    if (!this.mSmoothScrollbarEnabled) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = findFirstVisibleChildClosestToStart(bool1, true);
      bool1 = bool2;
      if (!this.mSmoothScrollbarEnabled) {
        bool1 = true;
      }
      return ScrollbarHelper.computeScrollRange(paramState, localOrientationHelper, localView, findFirstVisibleChildClosestToEnd(bool1, true), this, this.mSmoothScrollbarEnabled);
    }
  }
  
  private View findFirstPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
  }
  
  private View findFirstReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findReferenceChild(paramRecycler, paramState, 0, getChildCount(), paramState.getItemCount());
  }
  
  private View findFirstVisibleChildClosestToEnd(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mShouldReverseLayout) {
      return findOneVisibleChild(0, getChildCount(), paramBoolean1, paramBoolean2);
    }
    return findOneVisibleChild(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
  }
  
  private View findFirstVisibleChildClosestToStart(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mShouldReverseLayout) {
      return findOneVisibleChild(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
    }
    return findOneVisibleChild(0, getChildCount(), paramBoolean1, paramBoolean2);
  }
  
  private View findLastPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
  }
  
  private View findLastReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findReferenceChild(paramRecycler, paramState, getChildCount() - 1, -1, paramState.getItemCount());
  }
  
  private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mShouldReverseLayout) {
      return findFirstPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
    }
    return findLastPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
  }
  
  private View findPartiallyOrCompletelyInvisibleChildClosestToStart(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mShouldReverseLayout) {
      return findLastPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
    }
    return findFirstPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
  }
  
  private View findReferenceChildClosestToEnd(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mShouldReverseLayout) {
      return findFirstReferenceChild(paramRecycler, paramState);
    }
    return findLastReferenceChild(paramRecycler, paramState);
  }
  
  private View findReferenceChildClosestToStart(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mShouldReverseLayout) {
      return findLastReferenceChild(paramRecycler, paramState);
    }
    return findFirstReferenceChild(paramRecycler, paramState);
  }
  
  private int fixLayoutEndGap(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, boolean paramBoolean)
  {
    int i = this.mOrientationHelper.getEndAfterPadding() - paramInt;
    if (i > 0)
    {
      int j = -scrollBy(-i, paramRecycler, paramState);
      i = j;
      if (paramBoolean)
      {
        paramInt = this.mOrientationHelper.getEndAfterPadding() - (paramInt + j);
        i = j;
        if (paramInt > 0)
        {
          this.mOrientationHelper.offsetChildren(paramInt);
          i = j + paramInt;
        }
      }
      return i;
    }
    return 0;
  }
  
  private int fixLayoutStartGap(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, boolean paramBoolean)
  {
    int i = paramInt - this.mOrientationHelper.getStartAfterPadding();
    if (i > 0)
    {
      int j = -scrollBy(i, paramRecycler, paramState);
      i = j;
      if (paramBoolean)
      {
        paramInt = paramInt + j - this.mOrientationHelper.getStartAfterPadding();
        i = j;
        if (paramInt > 0)
        {
          this.mOrientationHelper.offsetChildren(-paramInt);
          i = j - paramInt;
        }
      }
      return i;
    }
    return 0;
  }
  
  private View getChildClosestToEnd()
  {
    if (this.mShouldReverseLayout) {}
    for (int i = 0;; i = getChildCount() - 1) {
      return getChildAt(i);
    }
  }
  
  private View getChildClosestToStart()
  {
    if (this.mShouldReverseLayout) {}
    for (int i = getChildCount() - 1;; i = 0) {
      return getChildAt(i);
    }
  }
  
  private void layoutForPredictiveAnimations(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2)
  {
    if ((!paramState.willRunPredictiveAnimations()) || (getChildCount() == 0) || (paramState.isPreLayout()) || (!supportsPredictiveItemAnimations())) {
      return;
    }
    int i = 0;
    int j = 0;
    List localList = paramRecycler.getScrapList();
    int n = localList.size();
    int i1 = getPosition(getChildAt(0));
    int k = 0;
    if (k < n)
    {
      RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)localList.get(k);
      int m;
      if (localViewHolder.isRemoved())
      {
        m = j;
        j = i;
        i = m;
      }
      for (;;)
      {
        m = k + 1;
        k = j;
        j = i;
        i = k;
        k = m;
        break;
        int i2;
        if (localViewHolder.getLayoutPosition() < i1)
        {
          i2 = 1;
          label143:
          if (i2 == this.mShouldReverseLayout) {
            break label195;
          }
        }
        label195:
        for (m = -1;; m = 1)
        {
          if (m != -1) {
            break label201;
          }
          m = this.mOrientationHelper.getDecoratedMeasurement(localViewHolder.itemView) + i;
          i = j;
          j = m;
          break;
          i2 = 0;
          break label143;
        }
        label201:
        m = this.mOrientationHelper.getDecoratedMeasurement(localViewHolder.itemView) + j;
        j = i;
        i = m;
      }
    }
    this.mLayoutState.mScrapList = localList;
    if (i > 0)
    {
      updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), paramInt1);
      this.mLayoutState.mExtra = i;
      this.mLayoutState.mAvailable = 0;
      this.mLayoutState.assignPositionFromScrapList();
      fill(paramRecycler, this.mLayoutState, paramState, false);
    }
    if (j > 0)
    {
      updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), paramInt2);
      this.mLayoutState.mExtra = j;
      this.mLayoutState.mAvailable = 0;
      this.mLayoutState.assignPositionFromScrapList();
      fill(paramRecycler, this.mLayoutState, paramState, false);
    }
    this.mLayoutState.mScrapList = null;
  }
  
  private void logChildren()
  {
    Log.d("LinearLayoutManager", "internal representation of views on the screen");
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      Log.d("LinearLayoutManager", "item " + getPosition(localView) + ", coord:" + this.mOrientationHelper.getDecoratedStart(localView));
      i += 1;
    }
    Log.d("LinearLayoutManager", "==============");
  }
  
  private void recycleByLayoutState(RecyclerView.Recycler paramRecycler, LayoutState paramLayoutState)
  {
    if ((!paramLayoutState.mRecycle) || (paramLayoutState.mInfinite)) {
      return;
    }
    if (paramLayoutState.mLayoutDirection == -1)
    {
      recycleViewsFromEnd(paramRecycler, paramLayoutState.mScrollingOffset);
      return;
    }
    recycleViewsFromStart(paramRecycler, paramLayoutState.mScrollingOffset);
  }
  
  private void recycleChildren(RecyclerView.Recycler paramRecycler, int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {}
    for (;;)
    {
      return;
      int i = paramInt1;
      if (paramInt2 > paramInt1)
      {
        paramInt2 -= 1;
        while (paramInt2 >= paramInt1)
        {
          removeAndRecycleViewAt(paramInt2, paramRecycler);
          paramInt2 -= 1;
        }
      }
      else
      {
        while (i > paramInt2)
        {
          removeAndRecycleViewAt(i, paramRecycler);
          i -= 1;
        }
      }
    }
  }
  
  private void recycleViewsFromEnd(RecyclerView.Recycler paramRecycler, int paramInt)
  {
    int i = getChildCount();
    if (paramInt < 0) {}
    for (;;)
    {
      return;
      int j = this.mOrientationHelper.getEnd() - paramInt;
      View localView;
      if (this.mShouldReverseLayout)
      {
        paramInt = 0;
        while (paramInt < i)
        {
          localView = getChildAt(paramInt);
          if ((this.mOrientationHelper.getDecoratedStart(localView) < j) || (this.mOrientationHelper.getTransformedStartWithDecoration(localView) < j))
          {
            recycleChildren(paramRecycler, 0, paramInt);
            return;
          }
          paramInt += 1;
        }
      }
      else
      {
        paramInt = i - 1;
        while (paramInt >= 0)
        {
          localView = getChildAt(paramInt);
          if ((this.mOrientationHelper.getDecoratedStart(localView) < j) || (this.mOrientationHelper.getTransformedStartWithDecoration(localView) < j))
          {
            recycleChildren(paramRecycler, i - 1, paramInt);
            return;
          }
          paramInt -= 1;
        }
      }
    }
  }
  
  private void recycleViewsFromStart(RecyclerView.Recycler paramRecycler, int paramInt)
  {
    if (paramInt < 0) {}
    for (;;)
    {
      return;
      int j = getChildCount();
      int i;
      View localView;
      if (this.mShouldReverseLayout)
      {
        i = j - 1;
        while (i >= 0)
        {
          localView = getChildAt(i);
          if ((this.mOrientationHelper.getDecoratedEnd(localView) > paramInt) || (this.mOrientationHelper.getTransformedEndWithDecoration(localView) > paramInt))
          {
            recycleChildren(paramRecycler, j - 1, i);
            return;
          }
          i -= 1;
        }
      }
      else
      {
        i = 0;
        while (i < j)
        {
          localView = getChildAt(i);
          if ((this.mOrientationHelper.getDecoratedEnd(localView) > paramInt) || (this.mOrientationHelper.getTransformedEndWithDecoration(localView) > paramInt))
          {
            recycleChildren(paramRecycler, 0, i);
            return;
          }
          i += 1;
        }
      }
    }
  }
  
  private void resolveShouldLayoutReverse()
  {
    boolean bool = true;
    if ((this.mOrientation == 1) || (!isLayoutRTL()))
    {
      this.mShouldReverseLayout = this.mReverseLayout;
      return;
    }
    if (!this.mReverseLayout) {}
    for (;;)
    {
      this.mShouldReverseLayout = bool;
      return;
      bool = false;
    }
  }
  
  private boolean updateAnchorFromChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    int i = 0;
    if (getChildCount() == 0) {}
    do
    {
      return false;
      View localView = getFocusedChild();
      if ((localView != null) && (paramAnchorInfo.isViewValidAsAnchor(localView, paramState)))
      {
        paramAnchorInfo.assignFromViewAndKeepVisibleRect(localView);
        return true;
      }
    } while (this.mLastStackFromEnd != this.mStackFromEnd);
    if (paramAnchorInfo.mLayoutFromEnd)
    {
      paramRecycler = findReferenceChildClosestToEnd(paramRecycler, paramState);
      label66:
      if (paramRecycler == null) {
        break label165;
      }
      paramAnchorInfo.assignFromView(paramRecycler);
      if ((!paramState.isPreLayout()) && (supportsPredictiveItemAnimations()))
      {
        if ((this.mOrientationHelper.getDecoratedStart(paramRecycler) >= this.mOrientationHelper.getEndAfterPadding()) || (this.mOrientationHelper.getDecoratedEnd(paramRecycler) < this.mOrientationHelper.getStartAfterPadding())) {
          i = 1;
        }
        if (i != 0) {
          if (!paramAnchorInfo.mLayoutFromEnd) {
            break label167;
          }
        }
      }
    }
    label165:
    label167:
    for (i = this.mOrientationHelper.getEndAfterPadding();; i = this.mOrientationHelper.getStartAfterPadding())
    {
      paramAnchorInfo.mCoordinate = i;
      return true;
      paramRecycler = findReferenceChildClosestToStart(paramRecycler, paramState);
      break label66;
      break;
    }
  }
  
  private boolean updateAnchorFromPendingData(RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    boolean bool = false;
    if ((paramState.isPreLayout()) || (this.mPendingScrollPosition == -1)) {
      return false;
    }
    if ((this.mPendingScrollPosition < 0) || (this.mPendingScrollPosition >= paramState.getItemCount()))
    {
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      return false;
    }
    paramAnchorInfo.mPosition = this.mPendingScrollPosition;
    if ((this.mPendingSavedState != null) && (this.mPendingSavedState.hasValidAnchor()))
    {
      paramAnchorInfo.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
      if (paramAnchorInfo.mLayoutFromEnd)
      {
        paramAnchorInfo.mCoordinate = (this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset);
        return true;
      }
      paramAnchorInfo.mCoordinate = (this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset);
      return true;
    }
    if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE)
    {
      paramState = findViewByPosition(this.mPendingScrollPosition);
      int i;
      if (paramState != null)
      {
        if (this.mOrientationHelper.getDecoratedMeasurement(paramState) > this.mOrientationHelper.getTotalSpace())
        {
          paramAnchorInfo.assignCoordinateFromPadding();
          return true;
        }
        if (this.mOrientationHelper.getDecoratedStart(paramState) - this.mOrientationHelper.getStartAfterPadding() < 0)
        {
          paramAnchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
          paramAnchorInfo.mLayoutFromEnd = false;
          return true;
        }
        if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(paramState) < 0)
        {
          paramAnchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
          paramAnchorInfo.mLayoutFromEnd = true;
          return true;
        }
        if (paramAnchorInfo.mLayoutFromEnd) {}
        for (i = this.mOrientationHelper.getDecoratedEnd(paramState) + this.mOrientationHelper.getTotalSpaceChange();; i = this.mOrientationHelper.getDecoratedStart(paramState))
        {
          paramAnchorInfo.mCoordinate = i;
          return true;
        }
      }
      if (getChildCount() > 0)
      {
        i = getPosition(getChildAt(0));
        if (this.mPendingScrollPosition >= i) {
          break label351;
        }
      }
      label351:
      for (int j = 1;; j = 0)
      {
        if (j == this.mShouldReverseLayout) {
          bool = true;
        }
        paramAnchorInfo.mLayoutFromEnd = bool;
        paramAnchorInfo.assignCoordinateFromPadding();
        return true;
      }
    }
    paramAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout;
    if (this.mShouldReverseLayout)
    {
      paramAnchorInfo.mCoordinate = (this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset);
      return true;
    }
    paramAnchorInfo.mCoordinate = (this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset);
    return true;
  }
  
  private void updateAnchorInfoForLayout(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    if (updateAnchorFromPendingData(paramState, paramAnchorInfo)) {}
    while (updateAnchorFromChildren(paramRecycler, paramState, paramAnchorInfo)) {
      return;
    }
    paramAnchorInfo.assignCoordinateFromPadding();
    if (this.mStackFromEnd) {}
    for (int i = paramState.getItemCount() - 1;; i = 0)
    {
      paramAnchorInfo.mPosition = i;
      return;
    }
  }
  
  private void updateLayoutState(int paramInt1, int paramInt2, boolean paramBoolean, RecyclerView.State paramState)
  {
    int i = -1;
    int j = 1;
    this.mLayoutState.mInfinite = resolveIsInfinite();
    this.mLayoutState.mExtra = getExtraLayoutSpace(paramState);
    this.mLayoutState.mLayoutDirection = paramInt1;
    if (paramInt1 == 1)
    {
      paramState = this.mLayoutState;
      paramState.mExtra += this.mOrientationHelper.getEndPadding();
      paramState = getChildClosestToEnd();
      localLayoutState = this.mLayoutState;
      if (this.mShouldReverseLayout) {}
      for (paramInt1 = i;; paramInt1 = 1)
      {
        localLayoutState.mItemDirection = paramInt1;
        this.mLayoutState.mCurrentPosition = (getPosition(paramState) + this.mLayoutState.mItemDirection);
        this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(paramState);
        paramInt1 = this.mOrientationHelper.getDecoratedEnd(paramState) - this.mOrientationHelper.getEndAfterPadding();
        this.mLayoutState.mAvailable = paramInt2;
        if (paramBoolean)
        {
          paramState = this.mLayoutState;
          paramState.mAvailable -= paramInt1;
        }
        this.mLayoutState.mScrollingOffset = paramInt1;
        return;
      }
    }
    paramState = getChildClosestToStart();
    LayoutState localLayoutState = this.mLayoutState;
    localLayoutState.mExtra += this.mOrientationHelper.getStartAfterPadding();
    localLayoutState = this.mLayoutState;
    if (this.mShouldReverseLayout) {}
    for (paramInt1 = j;; paramInt1 = -1)
    {
      localLayoutState.mItemDirection = paramInt1;
      this.mLayoutState.mCurrentPosition = (getPosition(paramState) + this.mLayoutState.mItemDirection);
      this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(paramState);
      paramInt1 = -this.mOrientationHelper.getDecoratedStart(paramState) + this.mOrientationHelper.getStartAfterPadding();
      break;
    }
  }
  
  private void updateLayoutStateToFillEnd(int paramInt1, int paramInt2)
  {
    this.mLayoutState.mAvailable = (this.mOrientationHelper.getEndAfterPadding() - paramInt2);
    LayoutState localLayoutState = this.mLayoutState;
    if (this.mShouldReverseLayout) {}
    for (int i = -1;; i = 1)
    {
      localLayoutState.mItemDirection = i;
      this.mLayoutState.mCurrentPosition = paramInt1;
      this.mLayoutState.mLayoutDirection = 1;
      this.mLayoutState.mOffset = paramInt2;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
      return;
    }
  }
  
  private void updateLayoutStateToFillEnd(AnchorInfo paramAnchorInfo)
  {
    updateLayoutStateToFillEnd(paramAnchorInfo.mPosition, paramAnchorInfo.mCoordinate);
  }
  
  private void updateLayoutStateToFillStart(int paramInt1, int paramInt2)
  {
    this.mLayoutState.mAvailable = (paramInt2 - this.mOrientationHelper.getStartAfterPadding());
    this.mLayoutState.mCurrentPosition = paramInt1;
    LayoutState localLayoutState = this.mLayoutState;
    if (this.mShouldReverseLayout) {}
    for (paramInt1 = 1;; paramInt1 = -1)
    {
      localLayoutState.mItemDirection = paramInt1;
      this.mLayoutState.mLayoutDirection = -1;
      this.mLayoutState.mOffset = paramInt2;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
      return;
    }
  }
  
  private void updateLayoutStateToFillStart(AnchorInfo paramAnchorInfo)
  {
    updateLayoutStateToFillStart(paramAnchorInfo.mPosition, paramAnchorInfo.mCoordinate);
  }
  
  public void assertNotInLayoutOrScroll(String paramString)
  {
    if (this.mPendingSavedState == null) {
      super.assertNotInLayoutOrScroll(paramString);
    }
  }
  
  public boolean canScrollHorizontally()
  {
    return this.mOrientation == 0;
  }
  
  public boolean canScrollVertically()
  {
    return this.mOrientation == 1;
  }
  
  public void collectAdjacentPrefetchPositions(int paramInt1, int paramInt2, RecyclerView.State paramState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    if (this.mOrientation == 0) {}
    while ((getChildCount() == 0) || (paramInt1 == 0))
    {
      return;
      paramInt1 = paramInt2;
    }
    if (paramInt1 > 0) {}
    for (paramInt2 = 1;; paramInt2 = -1)
    {
      updateLayoutState(paramInt2, Math.abs(paramInt1), true, paramState);
      collectPrefetchPositionsForLayoutState(paramState, this.mLayoutState, paramLayoutPrefetchRegistry);
      return;
    }
  }
  
  public void collectInitialPrefetchPositions(int paramInt, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    boolean bool;
    int i;
    if ((this.mPendingSavedState != null) && (this.mPendingSavedState.hasValidAnchor()))
    {
      bool = this.mPendingSavedState.mAnchorLayoutFromEnd;
      i = this.mPendingSavedState.mAnchorPosition;
      if (!bool) {
        break label136;
      }
    }
    label136:
    for (int j = -1;; j = 1)
    {
      int m = 0;
      int k = i;
      i = m;
      while ((i < this.mInitialPrefetchItemCount) && (k >= 0) && (k < paramInt))
      {
        paramLayoutPrefetchRegistry.addPosition(k, 0);
        k += j;
        i += 1;
      }
      resolveShouldLayoutReverse();
      bool = this.mShouldReverseLayout;
      if (this.mPendingScrollPosition == -1)
      {
        if (bool) {}
        for (i = paramInt - 1;; i = 0) {
          break;
        }
      }
      i = this.mPendingScrollPosition;
      break;
    }
  }
  
  void collectPrefetchPositionsForLayoutState(RecyclerView.State paramState, LayoutState paramLayoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    int i = paramLayoutState.mCurrentPosition;
    if ((i >= 0) && (i < paramState.getItemCount())) {
      paramLayoutPrefetchRegistry.addPosition(i, Math.max(0, paramLayoutState.mScrollingOffset));
    }
  }
  
  public int computeHorizontalScrollExtent(RecyclerView.State paramState)
  {
    return computeScrollExtent(paramState);
  }
  
  public int computeHorizontalScrollOffset(RecyclerView.State paramState)
  {
    return computeScrollOffset(paramState);
  }
  
  public int computeHorizontalScrollRange(RecyclerView.State paramState)
  {
    return computeScrollRange(paramState);
  }
  
  public PointF computeScrollVectorForPosition(int paramInt)
  {
    int i = 1;
    int j = 0;
    if (getChildCount() == 0) {
      return null;
    }
    if (paramInt < getPosition(getChildAt(0))) {
      j = 1;
    }
    paramInt = i;
    if (j != this.mShouldReverseLayout) {
      paramInt = -1;
    }
    if (this.mOrientation == 0) {
      return new PointF(paramInt, 0.0F);
    }
    return new PointF(0.0F, paramInt);
  }
  
  public int computeVerticalScrollExtent(RecyclerView.State paramState)
  {
    return computeScrollExtent(paramState);
  }
  
  public int computeVerticalScrollOffset(RecyclerView.State paramState)
  {
    return computeScrollOffset(paramState);
  }
  
  public int computeVerticalScrollRange(RecyclerView.State paramState)
  {
    return computeScrollRange(paramState);
  }
  
  int convertFocusDirectionToLayoutDirection(int paramInt)
  {
    int i = -1;
    int j = Integer.MIN_VALUE;
    int k = 1;
    switch (paramInt)
    {
    default: 
      paramInt = Integer.MIN_VALUE;
    case 1: 
    case 2: 
    case 33: 
    case 130: 
    case 17: 
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return paramInt;
                paramInt = i;
              } while (this.mOrientation == 1);
              paramInt = i;
            } while (!isLayoutRTL());
            return 1;
            if (this.mOrientation == 1) {
              return 1;
            }
            paramInt = i;
          } while (isLayoutRTL());
          return 1;
          paramInt = i;
        } while (this.mOrientation == 1);
        return Integer.MIN_VALUE;
        paramInt = j;
        if (this.mOrientation == 1) {
          paramInt = 1;
        }
        return paramInt;
        paramInt = i;
      } while (this.mOrientation == 0);
      return Integer.MIN_VALUE;
    }
    if (this.mOrientation == 0) {}
    for (paramInt = k;; paramInt = Integer.MIN_VALUE) {
      return paramInt;
    }
  }
  
  LayoutState createLayoutState()
  {
    return new LayoutState();
  }
  
  void ensureLayoutState()
  {
    if (this.mLayoutState == null) {
      this.mLayoutState = createLayoutState();
    }
    if (this.mOrientationHelper == null) {
      this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
    }
  }
  
  int fill(RecyclerView.Recycler paramRecycler, LayoutState paramLayoutState, RecyclerView.State paramState, boolean paramBoolean)
  {
    int k = paramLayoutState.mAvailable;
    if (paramLayoutState.mScrollingOffset != Integer.MIN_VALUE)
    {
      if (paramLayoutState.mAvailable < 0) {
        paramLayoutState.mScrollingOffset += paramLayoutState.mAvailable;
      }
      recycleByLayoutState(paramRecycler, paramLayoutState);
    }
    int i = paramLayoutState.mAvailable + paramLayoutState.mExtra;
    LayoutChunkResult localLayoutChunkResult = this.mLayoutChunkResult;
    if (((paramLayoutState.mInfinite) || (i > 0)) && (paramLayoutState.hasMore(paramState)))
    {
      localLayoutChunkResult.resetInternal();
      layoutChunk(paramRecycler, paramState, paramLayoutState, localLayoutChunkResult);
      if (!localLayoutChunkResult.mFinished) {
        break label108;
      }
    }
    for (;;)
    {
      return k - paramLayoutState.mAvailable;
      label108:
      paramLayoutState.mOffset += localLayoutChunkResult.mConsumed * paramLayoutState.mLayoutDirection;
      int j;
      if ((localLayoutChunkResult.mIgnoreConsumed) && (this.mLayoutState.mScrapList == null))
      {
        j = i;
        if (paramState.isPreLayout()) {}
      }
      else
      {
        paramLayoutState.mAvailable -= localLayoutChunkResult.mConsumed;
        j = i - localLayoutChunkResult.mConsumed;
      }
      if (paramLayoutState.mScrollingOffset != Integer.MIN_VALUE)
      {
        paramLayoutState.mScrollingOffset += localLayoutChunkResult.mConsumed;
        if (paramLayoutState.mAvailable < 0) {
          paramLayoutState.mScrollingOffset += paramLayoutState.mAvailable;
        }
        recycleByLayoutState(paramRecycler, paramLayoutState);
      }
      i = j;
      if (!paramBoolean) {
        break;
      }
      i = j;
      if (!localLayoutChunkResult.mFocusable) {
        break;
      }
    }
  }
  
  public int findFirstCompletelyVisibleItemPosition()
  {
    View localView = findOneVisibleChild(0, getChildCount(), true, false);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findFirstVisibleItemPosition()
  {
    View localView = findOneVisibleChild(0, getChildCount(), false, true);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findLastCompletelyVisibleItemPosition()
  {
    View localView = findOneVisibleChild(getChildCount() - 1, -1, true, false);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findLastVisibleItemPosition()
  {
    View localView = findOneVisibleChild(getChildCount() - 1, -1, false, true);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  View findOnePartiallyOrCompletelyInvisibleChild(int paramInt1, int paramInt2)
  {
    ensureLayoutState();
    if (paramInt2 > paramInt1) {
      i = 1;
    }
    while (i == 0)
    {
      return getChildAt(paramInt1);
      if (paramInt2 < paramInt1) {
        i = -1;
      } else {
        i = 0;
      }
    }
    int j;
    if (this.mOrientationHelper.getDecoratedStart(getChildAt(paramInt1)) < this.mOrientationHelper.getStartAfterPadding()) {
      j = 16644;
    }
    for (int i = 16388; this.mOrientation == 0; i = 4097)
    {
      return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, j, i);
      j = 4161;
    }
    return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, j, i);
  }
  
  View findOneVisibleChild(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = 320;
    ensureLayoutState();
    int i;
    if (paramBoolean1)
    {
      i = 24579;
      if (!paramBoolean2) {
        break label66;
      }
    }
    for (;;)
    {
      if (this.mOrientation == 0)
      {
        return this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
        i = 320;
        break;
      }
      return this.mVerticalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
      label66:
      j = 0;
    }
  }
  
  View findReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, int paramInt3)
  {
    paramState = null;
    ensureLayoutState();
    int j = this.mOrientationHelper.getStartAfterPadding();
    int k = this.mOrientationHelper.getEndAfterPadding();
    int i;
    label35:
    Object localObject1;
    if (paramInt2 > paramInt1)
    {
      i = 1;
      paramRecycler = null;
      if (paramInt1 == paramInt2) {
        break label157;
      }
      localObject1 = getChildAt(paramInt1);
      int m = getPosition((View)localObject1);
      if ((m < 0) || (m >= paramInt3)) {
        break label172;
      }
      if (!((RecyclerView.LayoutParams)((View)localObject1).getLayoutParams()).isItemRemoved()) {
        break label113;
      }
      if (paramRecycler != null) {
        break label172;
      }
      paramRecycler = paramState;
      paramState = (RecyclerView.State)localObject1;
    }
    for (;;)
    {
      paramInt1 += i;
      localObject1 = paramState;
      paramState = paramRecycler;
      paramRecycler = (RecyclerView.Recycler)localObject1;
      break label35;
      i = -1;
      break;
      label113:
      Object localObject2;
      if (this.mOrientationHelper.getDecoratedStart((View)localObject1) < k)
      {
        localObject2 = localObject1;
        if (this.mOrientationHelper.getDecoratedEnd((View)localObject1) >= j) {}
      }
      else
      {
        if (paramState != null) {
          break label172;
        }
        paramState = paramRecycler;
        paramRecycler = (RecyclerView.Recycler)localObject1;
        continue;
        label157:
        if (paramState == null) {
          break label167;
        }
      }
      for (;;)
      {
        localObject2 = paramState;
        return (View)localObject2;
        label167:
        paramState = paramRecycler;
      }
      label172:
      localObject1 = paramRecycler;
      paramRecycler = paramState;
      paramState = (RecyclerView.State)localObject1;
    }
  }
  
  public View findViewByPosition(int paramInt)
  {
    int i = getChildCount();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    View localView;
    do
    {
      return (View)localObject;
      int j = paramInt - getPosition(getChildAt(0));
      if ((j < 0) || (j >= i)) {
        break;
      }
      localView = getChildAt(j);
      localObject = localView;
    } while (getPosition(localView) == paramInt);
    return super.findViewByPosition(paramInt);
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams()
  {
    return new RecyclerView.LayoutParams(-2, -2);
  }
  
  protected int getExtraLayoutSpace(RecyclerView.State paramState)
  {
    if (paramState.hasTargetScrollPosition()) {
      return this.mOrientationHelper.getTotalSpace();
    }
    return 0;
  }
  
  @Deprecated
  public int getInitialItemPrefetchCount()
  {
    return getInitialPrefetchItemCount();
  }
  
  public int getInitialPrefetchItemCount()
  {
    return this.mInitialPrefetchItemCount;
  }
  
  public int getOrientation()
  {
    return this.mOrientation;
  }
  
  public boolean getRecycleChildrenOnDetach()
  {
    return this.mRecycleChildrenOnDetach;
  }
  
  public boolean getReverseLayout()
  {
    return this.mReverseLayout;
  }
  
  public boolean getStackFromEnd()
  {
    return this.mStackFromEnd;
  }
  
  protected boolean isLayoutRTL()
  {
    return getLayoutDirection() == 1;
  }
  
  public boolean isSmoothScrollbarEnabled()
  {
    return this.mSmoothScrollbarEnabled;
  }
  
  void layoutChunk(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LayoutState paramLayoutState, LayoutChunkResult paramLayoutChunkResult)
  {
    paramRecycler = paramLayoutState.next(paramRecycler);
    if (paramRecycler == null)
    {
      paramLayoutChunkResult.mFinished = true;
      return;
    }
    paramState = (RecyclerView.LayoutParams)paramRecycler.getLayoutParams();
    boolean bool2;
    boolean bool1;
    label61:
    int i;
    int j;
    label120:
    int m;
    int k;
    if (paramLayoutState.mScrapList == null)
    {
      bool2 = this.mShouldReverseLayout;
      if (paramLayoutState.mLayoutDirection == -1)
      {
        bool1 = true;
        if (bool2 != bool1) {
          break label195;
        }
        addView(paramRecycler);
        measureChildWithMargins(paramRecycler, 0, 0);
        paramLayoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(paramRecycler);
        if (this.mOrientation != 1) {
          break label298;
        }
        if (!isLayoutRTL()) {
          break label251;
        }
        i = getWidth() - getPaddingRight();
        j = i - this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
        if (paramLayoutState.mLayoutDirection != -1) {
          break label273;
        }
        m = paramLayoutState.mOffset;
        k = paramLayoutState.mOffset - paramLayoutChunkResult.mConsumed;
      }
    }
    for (;;)
    {
      layoutDecoratedWithMargins(paramRecycler, j, k, i, m);
      if ((paramState.isItemRemoved()) || (paramState.isItemChanged())) {
        paramLayoutChunkResult.mIgnoreConsumed = true;
      }
      paramLayoutChunkResult.mFocusable = paramRecycler.hasFocusable();
      return;
      bool1 = false;
      break;
      label195:
      addView(paramRecycler, 0);
      break label61;
      bool2 = this.mShouldReverseLayout;
      if (paramLayoutState.mLayoutDirection == -1) {}
      for (bool1 = true;; bool1 = false)
      {
        if (bool2 != bool1) {
          break label242;
        }
        addDisappearingView(paramRecycler);
        break;
      }
      label242:
      addDisappearingView(paramRecycler, 0);
      break label61;
      label251:
      j = getPaddingLeft();
      i = this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler) + j;
      break label120;
      label273:
      k = paramLayoutState.mOffset;
      m = paramLayoutState.mOffset;
      m = paramLayoutChunkResult.mConsumed + m;
      continue;
      label298:
      k = getPaddingTop();
      m = k + this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
      if (paramLayoutState.mLayoutDirection == -1)
      {
        i = paramLayoutState.mOffset;
        j = paramLayoutState.mOffset - paramLayoutChunkResult.mConsumed;
      }
      else
      {
        j = paramLayoutState.mOffset;
        i = paramLayoutState.mOffset + paramLayoutChunkResult.mConsumed;
      }
    }
  }
  
  void onAnchorReady(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo, int paramInt) {}
  
  public void onDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.Recycler paramRecycler)
  {
    super.onDetachedFromWindow(paramRecyclerView, paramRecycler);
    if (this.mRecycleChildrenOnDetach)
    {
      removeAndRecycleAllViews(paramRecycler);
      paramRecycler.clear();
    }
  }
  
  public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    resolveShouldLayoutReverse();
    if (getChildCount() == 0)
    {
      paramView = null;
      return paramView;
    }
    paramInt = convertFocusDirectionToLayoutDirection(paramInt);
    if (paramInt == Integer.MIN_VALUE) {
      return null;
    }
    ensureLayoutState();
    ensureLayoutState();
    updateLayoutState(paramInt, (int)(0.33333334F * this.mOrientationHelper.getTotalSpace()), false, paramState);
    this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    this.mLayoutState.mRecycle = false;
    fill(paramRecycler, this.mLayoutState, paramState, true);
    if (paramInt == -1)
    {
      paramRecycler = findPartiallyOrCompletelyInvisibleChildClosestToStart(paramRecycler, paramState);
      label100:
      if (paramInt != -1) {
        break label134;
      }
    }
    label134:
    for (paramView = getChildClosestToStart();; paramView = getChildClosestToEnd())
    {
      if (!paramView.hasFocusable()) {
        return paramRecycler;
      }
      if (paramRecycler != null) {
        break;
      }
      return null;
      paramRecycler = findPartiallyOrCompletelyInvisibleChildClosestToEnd(paramRecycler, paramState);
      break label100;
    }
    return paramRecycler;
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    if (getChildCount() > 0)
    {
      paramAccessibilityEvent = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      paramAccessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
      paramAccessibilityEvent.setToIndex(findLastVisibleItemPosition());
    }
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    int k = -1;
    if (((this.mPendingSavedState != null) || (this.mPendingScrollPosition != -1)) && (paramState.getItemCount() == 0))
    {
      removeAndRecycleAllViews(paramRecycler);
      return;
    }
    if ((this.mPendingSavedState != null) && (this.mPendingSavedState.hasValidAnchor())) {
      this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
    }
    ensureLayoutState();
    this.mLayoutState.mRecycle = false;
    resolveShouldLayoutReverse();
    if ((!this.mAnchorInfo.mValid) || (this.mPendingScrollPosition != -1) || (this.mPendingSavedState != null))
    {
      this.mAnchorInfo.reset();
      this.mAnchorInfo.mLayoutFromEnd = (this.mShouldReverseLayout ^ this.mStackFromEnd);
      updateAnchorInfoForLayout(paramRecycler, paramState, this.mAnchorInfo);
      this.mAnchorInfo.mValid = true;
    }
    int i = getExtraLayoutSpace(paramState);
    int j;
    int m;
    int n;
    Object localObject;
    if (this.mLayoutState.mLastScrollDelta >= 0)
    {
      j = 0;
      m = j + this.mOrientationHelper.getStartAfterPadding();
      n = i + this.mOrientationHelper.getEndPadding();
      i = n;
      j = m;
      if (paramState.isPreLayout())
      {
        i = n;
        j = m;
        if (this.mPendingScrollPosition != -1)
        {
          i = n;
          j = m;
          if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE)
          {
            localObject = findViewByPosition(this.mPendingScrollPosition);
            i = n;
            j = m;
            if (localObject != null)
            {
              if (!this.mShouldReverseLayout) {
                break label666;
              }
              i = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd((View)localObject) - this.mPendingScrollPositionOffset;
              label280:
              if (i <= 0) {
                break label698;
              }
              j = m + i;
              i = n;
            }
          }
        }
      }
      label293:
      if (!this.mAnchorInfo.mLayoutFromEnd) {
        break label710;
      }
      if (this.mShouldReverseLayout) {
        k = 1;
      }
      label313:
      onAnchorReady(paramRecycler, paramState, this.mAnchorInfo, k);
      detachAndScrapAttachedViews(paramRecycler);
      this.mLayoutState.mInfinite = resolveIsInfinite();
      this.mLayoutState.mIsPreLayout = paramState.isPreLayout();
      if (!this.mAnchorInfo.mLayoutFromEnd) {
        break label723;
      }
      updateLayoutStateToFillStart(this.mAnchorInfo);
      this.mLayoutState.mExtra = j;
      fill(paramRecycler, this.mLayoutState, paramState, false);
      m = this.mLayoutState.mOffset;
      n = this.mLayoutState.mCurrentPosition;
      j = i;
      if (this.mLayoutState.mAvailable > 0) {
        j = i + this.mLayoutState.mAvailable;
      }
      updateLayoutStateToFillEnd(this.mAnchorInfo);
      this.mLayoutState.mExtra = j;
      localObject = this.mLayoutState;
      ((LayoutState)localObject).mCurrentPosition += this.mLayoutState.mItemDirection;
      fill(paramRecycler, this.mLayoutState, paramState, false);
      k = this.mLayoutState.mOffset;
      if (this.mLayoutState.mAvailable <= 0) {
        break label977;
      }
      i = this.mLayoutState.mAvailable;
      updateLayoutStateToFillStart(n, m);
      this.mLayoutState.mExtra = i;
      fill(paramRecycler, this.mLayoutState, paramState, false);
    }
    label555:
    label625:
    label666:
    label698:
    label710:
    label723:
    label967:
    label977:
    for (i = this.mLayoutState.mOffset;; i = m)
    {
      j = i;
      i = k;
      k = i;
      m = j;
      if (getChildCount() > 0)
      {
        if ((this.mShouldReverseLayout ^ this.mStackFromEnd))
        {
          k = fixLayoutEndGap(i, paramRecycler, paramState, true);
          m = j + k;
          j = fixLayoutStartGap(m, paramRecycler, paramState, false);
          m += j;
          k = i + k + j;
        }
      }
      else
      {
        layoutForPredictiveAnimations(paramRecycler, paramState, m, k);
        if (paramState.isPreLayout()) {
          break label967;
        }
        this.mOrientationHelper.onLayoutComplete();
      }
      for (;;)
      {
        this.mLastStackFromEnd = this.mStackFromEnd;
        return;
        j = i;
        i = 0;
        break;
        i = this.mOrientationHelper.getDecoratedStart((View)localObject);
        j = this.mOrientationHelper.getStartAfterPadding();
        i = this.mPendingScrollPositionOffset - (i - j);
        break label280;
        i = n - i;
        j = m;
        break label293;
        if (this.mShouldReverseLayout) {
          break label313;
        }
        k = 1;
        break label313;
        updateLayoutStateToFillEnd(this.mAnchorInfo);
        this.mLayoutState.mExtra = i;
        fill(paramRecycler, this.mLayoutState, paramState, false);
        k = this.mLayoutState.mOffset;
        n = this.mLayoutState.mCurrentPosition;
        i = j;
        if (this.mLayoutState.mAvailable > 0) {
          i = j + this.mLayoutState.mAvailable;
        }
        updateLayoutStateToFillStart(this.mAnchorInfo);
        this.mLayoutState.mExtra = i;
        localObject = this.mLayoutState;
        ((LayoutState)localObject).mCurrentPosition += this.mLayoutState.mItemDirection;
        fill(paramRecycler, this.mLayoutState, paramState, false);
        m = this.mLayoutState.mOffset;
        i = k;
        j = m;
        if (this.mLayoutState.mAvailable <= 0) {
          break label555;
        }
        i = this.mLayoutState.mAvailable;
        updateLayoutStateToFillEnd(n, k);
        this.mLayoutState.mExtra = i;
        fill(paramRecycler, this.mLayoutState, paramState, false);
        i = this.mLayoutState.mOffset;
        j = m;
        break label555;
        k = fixLayoutStartGap(j, paramRecycler, paramState, true);
        i += k;
        n = fixLayoutEndGap(i, paramRecycler, paramState, false);
        m = j + k + n;
        k = i + n;
        break label625;
        this.mAnchorInfo.reset();
      }
    }
  }
  
  public void onLayoutCompleted(RecyclerView.State paramState)
  {
    super.onLayoutCompleted(paramState);
    this.mPendingSavedState = null;
    this.mPendingScrollPosition = -1;
    this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
    this.mAnchorInfo.reset();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof SavedState))
    {
      this.mPendingSavedState = ((SavedState)paramParcelable);
      requestLayout();
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    if (this.mPendingSavedState != null) {
      return new SavedState(this.mPendingSavedState);
    }
    SavedState localSavedState = new SavedState();
    if (getChildCount() > 0)
    {
      ensureLayoutState();
      boolean bool = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
      localSavedState.mAnchorLayoutFromEnd = bool;
      if (bool)
      {
        localView = getChildClosestToEnd();
        localSavedState.mAnchorOffset = (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(localView));
        localSavedState.mAnchorPosition = getPosition(localView);
        return localSavedState;
      }
      View localView = getChildClosestToStart();
      localSavedState.mAnchorPosition = getPosition(localView);
      localSavedState.mAnchorOffset = (this.mOrientationHelper.getDecoratedStart(localView) - this.mOrientationHelper.getStartAfterPadding());
      return localSavedState;
    }
    localSavedState.invalidateAnchor();
    return localSavedState;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void prepareForDrop(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
    ensureLayoutState();
    resolveShouldLayoutReverse();
    paramInt1 = getPosition(paramView1);
    paramInt2 = getPosition(paramView2);
    if (paramInt1 < paramInt2) {
      paramInt1 = 1;
    }
    while (this.mShouldReverseLayout) {
      if (paramInt1 == 1)
      {
        scrollToPositionWithOffset(paramInt2, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(paramView2) + this.mOrientationHelper.getDecoratedMeasurement(paramView1)));
        return;
        paramInt1 = -1;
      }
      else
      {
        scrollToPositionWithOffset(paramInt2, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(paramView2));
        return;
      }
    }
    if (paramInt1 == -1)
    {
      scrollToPositionWithOffset(paramInt2, this.mOrientationHelper.getDecoratedStart(paramView2));
      return;
    }
    scrollToPositionWithOffset(paramInt2, this.mOrientationHelper.getDecoratedEnd(paramView2) - this.mOrientationHelper.getDecoratedMeasurement(paramView1));
  }
  
  boolean resolveIsInfinite()
  {
    return (this.mOrientationHelper.getMode() == 0) && (this.mOrientationHelper.getEnd() == 0);
  }
  
  int scrollBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if ((getChildCount() == 0) || (paramInt == 0)) {
      return 0;
    }
    this.mLayoutState.mRecycle = true;
    ensureLayoutState();
    if (paramInt > 0) {}
    int j;
    int k;
    for (int i = 1;; i = -1)
    {
      j = Math.abs(paramInt);
      updateLayoutState(i, j, true, paramState);
      k = this.mLayoutState.mScrollingOffset + fill(paramRecycler, this.mLayoutState, paramState, false);
      if (k >= 0) {
        break;
      }
      return 0;
    }
    if (j > k) {
      paramInt = i * k;
    }
    this.mOrientationHelper.offsetChildren(-paramInt);
    this.mLayoutState.mLastScrollDelta = paramInt;
    return paramInt;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mOrientation == 1) {
      return 0;
    }
    return scrollBy(paramInt, paramRecycler, paramState);
  }
  
  public void scrollToPosition(int paramInt)
  {
    this.mPendingScrollPosition = paramInt;
    this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
    if (this.mPendingSavedState != null) {
      this.mPendingSavedState.invalidateAnchor();
    }
    requestLayout();
  }
  
  public void scrollToPositionWithOffset(int paramInt1, int paramInt2)
  {
    this.mPendingScrollPosition = paramInt1;
    this.mPendingScrollPositionOffset = paramInt2;
    if (this.mPendingSavedState != null) {
      this.mPendingSavedState.invalidateAnchor();
    }
    requestLayout();
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mOrientation == 0) {
      return 0;
    }
    return scrollBy(paramInt, paramRecycler, paramState);
  }
  
  public void setInitialPrefetchItemCount(int paramInt)
  {
    this.mInitialPrefetchItemCount = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      throw new IllegalArgumentException("invalid orientation:" + paramInt);
    }
    assertNotInLayoutOrScroll(null);
    if (paramInt == this.mOrientation) {
      return;
    }
    this.mOrientation = paramInt;
    this.mOrientationHelper = null;
    requestLayout();
  }
  
  public void setRecycleChildrenOnDetach(boolean paramBoolean)
  {
    this.mRecycleChildrenOnDetach = paramBoolean;
  }
  
  public void setReverseLayout(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (paramBoolean == this.mReverseLayout) {
      return;
    }
    this.mReverseLayout = paramBoolean;
    requestLayout();
  }
  
  public void setSmoothScrollbarEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollbarEnabled = paramBoolean;
  }
  
  public void setStackFromEnd(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (this.mStackFromEnd == paramBoolean) {
      return;
    }
    this.mStackFromEnd = paramBoolean;
    requestLayout();
  }
  
  boolean shouldMeasureTwice()
  {
    return (getHeightMode() != 1073741824) && (getWidthMode() != 1073741824) && (hasFlexibleChildInBothOrientations());
  }
  
  public void smoothScrollToPosition(RecyclerView paramRecyclerView, RecyclerView.State paramState, int paramInt)
  {
    paramRecyclerView = new LinearSmoothScroller(paramRecyclerView.getContext());
    paramRecyclerView.setTargetPosition(paramInt);
    startSmoothScroll(paramRecyclerView);
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return (this.mPendingSavedState == null) && (this.mLastStackFromEnd == this.mStackFromEnd);
  }
  
  void validateChildOrder()
  {
    boolean bool2 = true;
    boolean bool1 = true;
    Log.d("LinearLayoutManager", "validating child count " + getChildCount());
    if (getChildCount() < 1) {}
    for (;;)
    {
      return;
      int j = getPosition(getChildAt(0));
      int k = this.mOrientationHelper.getDecoratedStart(getChildAt(0));
      int i;
      Object localObject;
      int m;
      int n;
      if (this.mShouldReverseLayout)
      {
        i = 1;
        while (i < getChildCount())
        {
          localObject = getChildAt(i);
          m = getPosition((View)localObject);
          n = this.mOrientationHelper.getDecoratedStart((View)localObject);
          if (m < j)
          {
            logChildren();
            localObject = new StringBuilder().append("detected invalid position. loc invalid? ");
            if (n < k) {}
            for (;;)
            {
              throw new RuntimeException(bool1);
              bool1 = false;
            }
          }
          if (n > k)
          {
            logChildren();
            throw new RuntimeException("detected invalid location");
          }
          i += 1;
        }
      }
      else
      {
        i = 1;
        while (i < getChildCount())
        {
          localObject = getChildAt(i);
          m = getPosition((View)localObject);
          n = this.mOrientationHelper.getDecoratedStart((View)localObject);
          if (m < j)
          {
            logChildren();
            localObject = new StringBuilder().append("detected invalid position. loc invalid? ");
            if (n < k) {}
            for (bool1 = bool2;; bool1 = false) {
              throw new RuntimeException(bool1);
            }
          }
          if (n < k)
          {
            logChildren();
            throw new RuntimeException("detected invalid location");
          }
          i += 1;
        }
      }
    }
  }
  
  class AnchorInfo
  {
    int mCoordinate;
    boolean mLayoutFromEnd;
    int mPosition;
    boolean mValid;
    
    AnchorInfo()
    {
      reset();
    }
    
    void assignCoordinateFromPadding()
    {
      if (this.mLayoutFromEnd) {}
      for (int i = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding();; i = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding())
      {
        this.mCoordinate = i;
        return;
      }
    }
    
    public void assignFromView(View paramView)
    {
      if (this.mLayoutFromEnd) {}
      for (this.mCoordinate = (LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(paramView) + LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange());; this.mCoordinate = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(paramView))
      {
        this.mPosition = LinearLayoutManager.this.getPosition(paramView);
        return;
      }
    }
    
    public void assignFromViewAndKeepVisibleRect(View paramView)
    {
      int j = LinearLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
      if (j >= 0) {
        assignFromView(paramView);
      }
      int i;
      do
      {
        int k;
        do
        {
          do
          {
            do
            {
              return;
              this.mPosition = LinearLayoutManager.this.getPosition(paramView);
              if (!this.mLayoutFromEnd) {
                break;
              }
              i = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - j - LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(paramView);
              this.mCoordinate = (LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - i);
            } while (i <= 0);
            j = LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(paramView);
            k = this.mCoordinate;
            m = LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            j = k - j - (m + Math.min(LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(paramView) - m, 0));
          } while (j >= 0);
          k = this.mCoordinate;
          this.mCoordinate = (Math.min(i, -j) + k);
          return;
          k = LinearLayoutManager.this.mOrientationHelper.getDecoratedStart(paramView);
          i = k - LinearLayoutManager.this.mOrientationHelper.getStartAfterPadding();
          this.mCoordinate = k;
        } while (i <= 0);
        int m = LinearLayoutManager.this.mOrientationHelper.getDecoratedMeasurement(paramView);
        int n = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding();
        int i1 = LinearLayoutManager.this.mOrientationHelper.getDecoratedEnd(paramView);
        j = LinearLayoutManager.this.mOrientationHelper.getEndAfterPadding() - Math.min(0, n - j - i1) - (k + m);
      } while (j >= 0);
      this.mCoordinate -= Math.min(i, -j);
    }
    
    boolean isViewValidAsAnchor(View paramView, RecyclerView.State paramState)
    {
      paramView = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      return (!paramView.isItemRemoved()) && (paramView.getViewLayoutPosition() >= 0) && (paramView.getViewLayoutPosition() < paramState.getItemCount());
    }
    
    void reset()
    {
      this.mPosition = -1;
      this.mCoordinate = Integer.MIN_VALUE;
      this.mLayoutFromEnd = false;
      this.mValid = false;
    }
    
    public String toString()
    {
      return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
    }
  }
  
  protected static class LayoutChunkResult
  {
    public int mConsumed;
    public boolean mFinished;
    public boolean mFocusable;
    public boolean mIgnoreConsumed;
    
    void resetInternal()
    {
      this.mConsumed = 0;
      this.mFinished = false;
      this.mIgnoreConsumed = false;
      this.mFocusable = false;
    }
  }
  
  static class LayoutState
  {
    static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
    static final String TAG = "LLM#LayoutState";
    int mAvailable;
    int mCurrentPosition;
    int mExtra = 0;
    boolean mInfinite;
    boolean mIsPreLayout = false;
    int mItemDirection;
    int mLastScrollDelta;
    int mLayoutDirection;
    int mOffset;
    boolean mRecycle = true;
    List<RecyclerView.ViewHolder> mScrapList = null;
    int mScrollingOffset;
    
    private View nextViewFromScrapList()
    {
      int j = this.mScrapList.size();
      int i = 0;
      if (i < j)
      {
        View localView = ((RecyclerView.ViewHolder)this.mScrapList.get(i)).itemView;
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isItemRemoved()) {}
        while (this.mCurrentPosition != localLayoutParams.getViewLayoutPosition())
        {
          i += 1;
          break;
        }
        assignPositionFromScrapList(localView);
        return localView;
      }
      return null;
    }
    
    public void assignPositionFromScrapList()
    {
      assignPositionFromScrapList(null);
    }
    
    public void assignPositionFromScrapList(View paramView)
    {
      paramView = nextViewInLimitedList(paramView);
      if (paramView == null)
      {
        this.mCurrentPosition = -1;
        return;
      }
      this.mCurrentPosition = ((RecyclerView.LayoutParams)paramView.getLayoutParams()).getViewLayoutPosition();
    }
    
    boolean hasMore(RecyclerView.State paramState)
    {
      return (this.mCurrentPosition >= 0) && (this.mCurrentPosition < paramState.getItemCount());
    }
    
    void log()
    {
      Log.d("LLM#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
    }
    
    View next(RecyclerView.Recycler paramRecycler)
    {
      if (this.mScrapList != null) {
        return nextViewFromScrapList();
      }
      paramRecycler = paramRecycler.getViewForPosition(this.mCurrentPosition);
      this.mCurrentPosition += this.mItemDirection;
      return paramRecycler;
    }
    
    public View nextViewInLimitedList(View paramView)
    {
      int m = this.mScrapList.size();
      Object localObject = null;
      int i = Integer.MAX_VALUE;
      int j = 0;
      if (j < m)
      {
        View localView = ((RecyclerView.ViewHolder)this.mScrapList.get(j)).itemView;
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
        if (localView != paramView) {
          if (!localLayoutParams.isItemRemoved()) {}
        }
        for (;;)
        {
          j += 1;
          break;
          int k = (localLayoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
          if (k >= 0) {
            if (k < i)
            {
              if (k == 0) {
                return localView;
              }
              localObject = localView;
              i = k;
            }
          }
        }
      }
      return (View)localObject;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public LinearLayoutManager.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new LinearLayoutManager.SavedState(paramAnonymousParcel);
      }
      
      public LinearLayoutManager.SavedState[] newArray(int paramAnonymousInt)
      {
        return new LinearLayoutManager.SavedState[paramAnonymousInt];
      }
    };
    boolean mAnchorLayoutFromEnd;
    int mAnchorOffset;
    int mAnchorPosition;
    
    public SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      this.mAnchorPosition = paramParcel.readInt();
      this.mAnchorOffset = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      for (;;)
      {
        this.mAnchorLayoutFromEnd = bool;
        return;
        bool = false;
      }
    }
    
    public SavedState(SavedState paramSavedState)
    {
      this.mAnchorPosition = paramSavedState.mAnchorPosition;
      this.mAnchorOffset = paramSavedState.mAnchorOffset;
      this.mAnchorLayoutFromEnd = paramSavedState.mAnchorLayoutFromEnd;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    boolean hasValidAnchor()
    {
      return this.mAnchorPosition >= 0;
    }
    
    void invalidateAnchor()
    {
      this.mAnchorPosition = -1;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.mAnchorPosition);
      paramParcel.writeInt(this.mAnchorOffset);
      if (this.mAnchorLayoutFromEnd) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\LinearLayoutManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */