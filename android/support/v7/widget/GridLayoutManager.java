package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Arrays;

public class GridLayoutManager
  extends LinearLayoutManager
{
  private static final boolean DEBUG = false;
  public static final int DEFAULT_SPAN_COUNT = -1;
  private static final String TAG = "GridLayoutManager";
  int[] mCachedBorders;
  final Rect mDecorInsets = new Rect();
  boolean mPendingSpanCountChange = false;
  final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
  final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
  View[] mSet;
  int mSpanCount = -1;
  SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();
  
  public GridLayoutManager(Context paramContext, int paramInt)
  {
    super(paramContext);
    setSpanCount(paramInt);
  }
  
  public GridLayoutManager(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramContext, paramInt2, paramBoolean);
    setSpanCount(paramInt1);
  }
  
  public GridLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setSpanCount(getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2).spanCount);
  }
  
  private void assignSpans(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j;
    int i;
    if (paramBoolean)
    {
      paramInt2 = 1;
      j = 0;
      i = paramInt1;
      paramInt1 = j;
    }
    for (;;)
    {
      j = 0;
      while (paramInt1 != i)
      {
        View localView = this.mSet[paramInt1];
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        localLayoutParams.mSpanSize = getSpanSize(paramRecycler, paramState, getPosition(localView));
        localLayoutParams.mSpanIndex = j;
        j += localLayoutParams.mSpanSize;
        paramInt1 += paramInt2;
      }
      i = -1;
      paramInt1 -= 1;
      paramInt2 = -1;
    }
  }
  
  private void cachePreLayoutSpanMapping()
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      LayoutParams localLayoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
      int k = localLayoutParams.getViewLayoutPosition();
      this.mPreLayoutSpanSizeCache.put(k, localLayoutParams.getSpanSize());
      this.mPreLayoutSpanIndexCache.put(k, localLayoutParams.getSpanIndex());
      i += 1;
    }
  }
  
  private void calculateItemBorders(int paramInt)
  {
    this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, paramInt);
  }
  
  static int[] calculateItemBorders(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int k = 0;
    int[] arrayOfInt;
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length == paramInt1 + 1))
    {
      arrayOfInt = paramArrayOfInt;
      if (paramArrayOfInt[(paramArrayOfInt.length - 1)] == paramInt2) {}
    }
    else
    {
      arrayOfInt = new int[paramInt1 + 1];
    }
    arrayOfInt[0] = 0;
    int m = paramInt2 / paramInt1;
    int n = paramInt2 % paramInt1;
    int i = 1;
    int j = 0;
    paramInt2 = k;
    if (i <= paramInt1)
    {
      paramInt2 += n;
      if ((paramInt2 <= 0) || (paramInt1 - paramInt2 >= n)) {
        break label113;
      }
      k = m + 1;
      paramInt2 -= paramInt1;
    }
    for (;;)
    {
      j += k;
      arrayOfInt[i] = j;
      i += 1;
      break;
      return arrayOfInt;
      label113:
      k = m;
    }
  }
  
  private void clearPreLayoutSpanMappingCache()
  {
    this.mPreLayoutSpanSizeCache.clear();
    this.mPreLayoutSpanIndexCache.clear();
  }
  
  private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt)
  {
    int i = 1;
    if (paramInt == 1) {}
    for (;;)
    {
      paramInt = getSpanIndex(paramRecycler, paramState, paramAnchorInfo.mPosition);
      if (i == 0) {
        break;
      }
      while ((paramInt > 0) && (paramAnchorInfo.mPosition > 0))
      {
        paramAnchorInfo.mPosition -= 1;
        paramInt = getSpanIndex(paramRecycler, paramState, paramAnchorInfo.mPosition);
      }
      i = 0;
    }
    int k = paramState.getItemCount();
    i = paramAnchorInfo.mPosition;
    while (i < k - 1)
    {
      int j = getSpanIndex(paramRecycler, paramState, i + 1);
      if (j <= paramInt) {
        break;
      }
      i += 1;
      paramInt = j;
    }
    paramAnchorInfo.mPosition = i;
  }
  
  private void ensureViewSet()
  {
    if ((this.mSet == null) || (this.mSet.length != this.mSpanCount)) {
      this.mSet = new View[this.mSpanCount];
    }
  }
  
  private int getSpanGroupIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt)
  {
    if (!paramState.isPreLayout()) {
      return this.mSpanSizeLookup.getSpanGroupIndex(paramInt, this.mSpanCount);
    }
    int i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + paramInt);
      return 0;
    }
    return this.mSpanSizeLookup.getSpanGroupIndex(i, this.mSpanCount);
  }
  
  private int getSpanIndex(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt)
  {
    if (!paramState.isPreLayout()) {
      i = this.mSpanSizeLookup.getCachedSpanIndex(paramInt, this.mSpanCount);
    }
    int j;
    do
    {
      return i;
      j = this.mPreLayoutSpanIndexCache.get(paramInt, -1);
      i = j;
    } while (j != -1);
    int i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + paramInt);
      return 0;
    }
    return this.mSpanSizeLookup.getCachedSpanIndex(i, this.mSpanCount);
  }
  
  private int getSpanSize(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt)
  {
    if (!paramState.isPreLayout()) {
      i = this.mSpanSizeLookup.getSpanSize(paramInt);
    }
    int j;
    do
    {
      return i;
      j = this.mPreLayoutSpanSizeCache.get(paramInt, -1);
      i = j;
    } while (j != -1);
    int i = paramRecycler.convertPreLayoutPositionToPostLayout(paramInt);
    if (i == -1)
    {
      Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + paramInt);
      return 1;
    }
    return this.mSpanSizeLookup.getSpanSize(i);
  }
  
  private void guessMeasurement(float paramFloat, int paramInt)
  {
    calculateItemBorders(Math.max(Math.round(this.mSpanCount * paramFloat), paramInt));
  }
  
  private void measureChild(View paramView, int paramInt, boolean paramBoolean)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect localRect = localLayoutParams.mDecorInsets;
    int j = localRect.top + localRect.bottom + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
    int i = localRect.left;
    int k = localRect.right;
    int m = localLayoutParams.leftMargin;
    i = localLayoutParams.rightMargin + (k + i + m);
    k = getSpaceForSpanRange(localLayoutParams.mSpanIndex, localLayoutParams.mSpanSize);
    if (this.mOrientation == 1)
    {
      i = getChildMeasureSpec(k, paramInt, i, localLayoutParams.width, false);
      paramInt = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), j, localLayoutParams.height, true);
    }
    for (;;)
    {
      measureChildWithDecorationsAndMargin(paramView, i, paramInt, paramBoolean);
      return;
      paramInt = getChildMeasureSpec(k, paramInt, j, localLayoutParams.height, false);
      i = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getWidthMode(), i, localLayoutParams.width, true);
    }
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    if (paramBoolean) {}
    for (paramBoolean = shouldReMeasureChild(paramView, paramInt1, paramInt2, localLayoutParams);; paramBoolean = shouldMeasureChild(paramView, paramInt1, paramInt2, localLayoutParams))
    {
      if (paramBoolean) {
        paramView.measure(paramInt1, paramInt2);
      }
      return;
    }
  }
  
  private void updateMeasurements()
  {
    if (getOrientation() == 1) {}
    for (int i = getWidth() - getPaddingRight() - getPaddingLeft();; i = getHeight() - getPaddingBottom() - getPaddingTop())
    {
      calculateItemBorders(i);
      return;
    }
  }
  
  public boolean checkLayoutParams(RecyclerView.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void collectPrefetchPositionsForLayoutState(RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    int j = this.mSpanCount;
    int i = 0;
    while ((i < this.mSpanCount) && (paramLayoutState.hasMore(paramState)) && (j > 0))
    {
      int k = paramLayoutState.mCurrentPosition;
      paramLayoutPrefetchRegistry.addPosition(k, Math.max(0, paramLayoutState.mScrollingOffset));
      j -= this.mSpanSizeLookup.getSpanSize(k);
      paramLayoutState.mCurrentPosition += paramLayoutState.mItemDirection;
      i += 1;
    }
  }
  
  View findReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject2 = null;
    ensureLayoutState();
    int j = this.mOrientationHelper.getStartAfterPadding();
    int k = this.mOrientationHelper.getEndAfterPadding();
    int i;
    Object localObject1;
    label37:
    Object localObject3;
    if (paramInt2 > paramInt1)
    {
      i = 1;
      localObject1 = null;
      if (paramInt1 == paramInt2) {
        break label197;
      }
      localObject3 = getChildAt(paramInt1);
      int m = getPosition((View)localObject3);
      if ((m < 0) || (m >= paramInt3)) {
        break label216;
      }
      if (getSpanIndex(paramRecycler, paramState, m) == 0) {
        break label119;
      }
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject3;
    }
    for (;;)
    {
      paramInt1 += i;
      localObject3 = localObject2;
      localObject2 = localObject1;
      localObject1 = localObject3;
      break label37;
      i = -1;
      break;
      label119:
      if (((RecyclerView.LayoutParams)((View)localObject3).getLayoutParams()).isItemRemoved())
      {
        if (localObject1 == null)
        {
          localObject1 = localObject2;
          localObject2 = localObject3;
        }
      }
      else
      {
        Object localObject4;
        if (this.mOrientationHelper.getDecoratedStart((View)localObject3) < k)
        {
          localObject4 = localObject3;
          if (this.mOrientationHelper.getDecoratedEnd((View)localObject3) >= j) {}
        }
        else
        {
          if (localObject2 != null) {
            break label216;
          }
          localObject2 = localObject1;
          localObject1 = localObject3;
          continue;
          label197:
          if (localObject2 == null) {
            break label209;
          }
        }
        for (;;)
        {
          localObject4 = localObject2;
          return (View)localObject4;
          label209:
          localObject2 = localObject1;
        }
      }
      label216:
      localObject3 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams()
  {
    if (this.mOrientation == 0) {
      return new LayoutParams(-2, -1);
    }
    return new LayoutParams(-1, -2);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new LayoutParams(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getColumnCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mOrientation == 1) {
      return this.mSpanCount;
    }
    if (paramState.getItemCount() < 1) {
      return 0;
    }
    return getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1;
  }
  
  public int getRowCountForAccessibility(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (this.mOrientation == 0) {
      return this.mSpanCount;
    }
    if (paramState.getItemCount() < 1) {
      return 0;
    }
    return getSpanGroupIndex(paramRecycler, paramState, paramState.getItemCount() - 1) + 1;
  }
  
  int getSpaceForSpanRange(int paramInt1, int paramInt2)
  {
    if ((this.mOrientation == 1) && (isLayoutRTL())) {
      return this.mCachedBorders[(this.mSpanCount - paramInt1)] - this.mCachedBorders[(this.mSpanCount - paramInt1 - paramInt2)];
    }
    return this.mCachedBorders[(paramInt1 + paramInt2)] - this.mCachedBorders[paramInt1];
  }
  
  public int getSpanCount()
  {
    return this.mSpanCount;
  }
  
  public SpanSizeLookup getSpanSizeLookup()
  {
    return this.mSpanSizeLookup;
  }
  
  void layoutChunk(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.LayoutState paramLayoutState, LinearLayoutManager.LayoutChunkResult paramLayoutChunkResult)
  {
    int i3 = this.mOrientationHelper.getModeInOther();
    int j;
    int m;
    label38:
    boolean bool;
    label58:
    int i1;
    int i2;
    int n;
    if (i3 != 1073741824)
    {
      j = 1;
      if (getChildCount() <= 0) {
        break label226;
      }
      m = this.mCachedBorders[this.mSpanCount];
      if (j != 0) {
        updateMeasurements();
      }
      if (paramLayoutState.mItemDirection != 1) {
        break label232;
      }
      bool = true;
      i1 = 0;
      i2 = 0;
      i = this.mSpanCount;
      n = i1;
      k = i2;
      if (!bool)
      {
        i = getSpanIndex(paramRecycler, paramState, paramLayoutState.mCurrentPosition) + getSpanSize(paramRecycler, paramState, paramLayoutState.mCurrentPosition);
        k = i2;
        n = i1;
      }
    }
    label226:
    label232:
    label262:
    Object localObject;
    for (;;)
    {
      if ((n < this.mSpanCount) && (paramLayoutState.hasMore(paramState)) && (i > 0))
      {
        i2 = paramLayoutState.mCurrentPosition;
        i1 = getSpanSize(paramRecycler, paramState, i2);
        if (i1 > this.mSpanCount)
        {
          throw new IllegalArgumentException("Item at position " + i2 + " requires " + i1 + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
          j = 0;
          break;
          m = 0;
          break label38;
          bool = false;
          break label58;
        }
        i -= i1;
        if (i >= 0) {
          break label262;
        }
      }
      do
      {
        if (n != 0) {
          break;
        }
        paramLayoutChunkResult.mFinished = true;
        return;
        localObject = paramLayoutState.next(paramRecycler);
      } while (localObject == null);
      k += i1;
      this.mSet[n] = localObject;
      n += 1;
    }
    assignSpans(paramRecycler, paramState, n, k, bool);
    int k = 0;
    float f1 = 0.0F;
    int i = 0;
    if (k < n)
    {
      paramRecycler = this.mSet[k];
      if (paramLayoutState.mScrapList == null) {
        if (bool)
        {
          addView(paramRecycler);
          label352:
          calculateItemDecorationsForChild(paramRecycler, this.mDecorInsets);
          measureChild(paramRecycler, i3, false);
          i2 = this.mOrientationHelper.getDecoratedMeasurement(paramRecycler);
          i1 = i;
          if (i2 > i) {
            i1 = i2;
          }
          paramState = (LayoutParams)paramRecycler.getLayoutParams();
          float f2 = this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler) * 1.0F / paramState.mSpanSize;
          if (f2 <= f1) {
            break label1106;
          }
          f1 = f2;
        }
      }
    }
    label724:
    label825:
    label1030:
    label1062:
    label1094:
    label1103:
    label1106:
    for (;;)
    {
      k += 1;
      i = i1;
      break;
      addView(paramRecycler, 0);
      break label352;
      if (bool)
      {
        addDisappearingView(paramRecycler);
        break label352;
      }
      addDisappearingView(paramRecycler, 0);
      break label352;
      k = i;
      if (j != 0)
      {
        guessMeasurement(f1, m);
        i = 0;
        j = 0;
        k = i;
        if (j < n)
        {
          paramRecycler = this.mSet[j];
          measureChild(paramRecycler, 1073741824, true);
          k = this.mOrientationHelper.getDecoratedMeasurement(paramRecycler);
          if (k <= i) {
            break label1103;
          }
          i = k;
        }
      }
      for (;;)
      {
        j += 1;
        break;
        i = 0;
        if (i < n)
        {
          paramRecycler = this.mSet[i];
          if (this.mOrientationHelper.getDecoratedMeasurement(paramRecycler) != k)
          {
            paramState = (LayoutParams)paramRecycler.getLayoutParams();
            localObject = paramState.mDecorInsets;
            j = ((Rect)localObject).top + ((Rect)localObject).bottom + paramState.topMargin + paramState.bottomMargin;
            m = ((Rect)localObject).left;
            m = ((Rect)localObject).right + m + paramState.leftMargin + paramState.rightMargin;
            i1 = getSpaceForSpanRange(paramState.mSpanIndex, paramState.mSpanSize);
            if (this.mOrientation != 1) {
              break label724;
            }
            m = getChildMeasureSpec(i1, 1073741824, m, paramState.width, false);
          }
          for (j = View.MeasureSpec.makeMeasureSpec(k - j, 1073741824);; j = getChildMeasureSpec(i1, 1073741824, j, paramState.height, false))
          {
            measureChildWithDecorationsAndMargin(paramRecycler, m, j, true);
            i += 1;
            break;
            m = View.MeasureSpec.makeMeasureSpec(k - m, 1073741824);
          }
        }
        paramLayoutChunkResult.mConsumed = k;
        m = 0;
        if (this.mOrientation == 1) {
          if (paramLayoutState.mLayoutDirection == -1)
          {
            m = paramLayoutState.mOffset;
            i = m - k;
            j = 0;
            k = 0;
            i2 = k;
            i3 = 0;
            k = m;
            i1 = i;
            i = i2;
            m = i3;
            if (m >= n) {
              break label1094;
            }
            paramRecycler = this.mSet[m];
            paramState = (LayoutParams)paramRecycler.getLayoutParams();
            if (this.mOrientation != 1) {
              break label1062;
            }
            if (!isLayoutRTL()) {
              break label1030;
            }
            j = getPaddingLeft() + this.mCachedBorders[(this.mSpanCount - paramState.mSpanIndex)];
            i = j - this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
          }
        }
        for (;;)
        {
          layoutDecoratedWithMargins(paramRecycler, i, i1, j, k);
          if ((paramState.isItemRemoved()) || (paramState.isItemChanged())) {
            paramLayoutChunkResult.mIgnoreConsumed = true;
          }
          paramLayoutChunkResult.mFocusable |= paramRecycler.hasFocusable();
          m += 1;
          break label825;
          i = paramLayoutState.mOffset;
          m = i + k;
          j = 0;
          k = 0;
          break;
          if (paramLayoutState.mLayoutDirection == -1)
          {
            i = paramLayoutState.mOffset;
            j = i;
            k = i - k;
            i = 0;
            break;
          }
          i1 = paramLayoutState.mOffset;
          j = k + i1;
          i = 0;
          k = i1;
          break;
          i = getPaddingLeft() + this.mCachedBorders[paramState.mSpanIndex];
          j = i + this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
          continue;
          i1 = getPaddingTop() + this.mCachedBorders[paramState.mSpanIndex];
          k = i1 + this.mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
        }
        Arrays.fill(this.mSet, null);
        return;
      }
    }
  }
  
  void onAnchorReady(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LinearLayoutManager.AnchorInfo paramAnchorInfo, int paramInt)
  {
    super.onAnchorReady(paramRecycler, paramState, paramAnchorInfo, paramInt);
    updateMeasurements();
    if ((paramState.getItemCount() > 0) && (!paramState.isPreLayout())) {
      ensureAnchorIsInCorrectSpan(paramRecycler, paramState, paramAnchorInfo, paramInt);
    }
    ensureViewSet();
  }
  
  public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    View localView = findContainingItemView(paramView);
    Object localObject3;
    if (localView == null)
    {
      localObject3 = null;
      return (View)localObject3;
    }
    Object localObject1 = (LayoutParams)localView.getLayoutParams();
    int i6 = ((LayoutParams)localObject1).mSpanIndex;
    int i7 = ((LayoutParams)localObject1).mSpanIndex + ((LayoutParams)localObject1).mSpanSize;
    if (super.onFocusSearchFailed(paramView, paramInt, paramRecycler, paramState) == null) {
      return null;
    }
    int i11;
    label74:
    label85:
    int i1;
    int n;
    label102:
    int i2;
    label120:
    int m;
    int k;
    int j;
    int i;
    int i8;
    int i3;
    label150:
    Object localObject2;
    if (convertFocusDirectionToLayoutDirection(paramInt) == 1)
    {
      i11 = 1;
      if (i11 == this.mShouldReverseLayout) {
        break label194;
      }
      paramInt = 1;
      if (paramInt == 0) {
        break label199;
      }
      paramInt = getChildCount() - 1;
      i1 = -1;
      n = -1;
      if ((this.mOrientation != 1) || (!isLayoutRTL())) {
        break label213;
      }
      i2 = 1;
      paramView = null;
      m = -1;
      k = 0;
      localObject1 = null;
      j = -1;
      i = 0;
      i8 = getSpanGroupIndex(paramRecycler, paramState, paramInt);
      i3 = paramInt;
      if (i3 != n)
      {
        paramInt = getSpanGroupIndex(paramRecycler, paramState, i3);
        localObject2 = getChildAt(i3);
        if (localObject2 != localView) {
          break label219;
        }
      }
      label182:
      if (paramView == null) {
        break label652;
      }
    }
    for (;;)
    {
      return paramView;
      i11 = 0;
      break label74;
      label194:
      paramInt = 0;
      break label85;
      label199:
      n = getChildCount();
      paramInt = 0;
      i1 = 1;
      break label102;
      label213:
      i2 = 0;
      break label120;
      label219:
      if ((((View)localObject2).hasFocusable()) && (paramInt != i8))
      {
        if (paramView != null) {
          break label182;
        }
        paramInt = i;
        i = j;
        localObject2 = localObject1;
        j = k;
        localObject1 = paramView;
        k = m;
        paramView = (View)localObject2;
      }
      for (;;)
      {
        int i4 = i3 + i1;
        localObject2 = localObject1;
        m = j;
        i3 = k;
        j = i;
        i = paramInt;
        localObject1 = paramView;
        k = m;
        m = i3;
        paramView = (View)localObject2;
        i3 = i4;
        break label150;
        LayoutParams localLayoutParams = (LayoutParams)((View)localObject2).getLayoutParams();
        int i9 = localLayoutParams.mSpanIndex;
        int i10 = localLayoutParams.mSpanIndex + localLayoutParams.mSpanSize;
        if ((((View)localObject2).hasFocusable()) && (i9 == i6))
        {
          localObject3 = localObject2;
          if (i10 == i7) {
            break;
          }
        }
        int i5 = 0;
        if (((((View)localObject2).hasFocusable()) && (paramView == null)) || ((!((View)localObject2).hasFocusable()) && (localObject1 == null))) {
          paramInt = 1;
        }
        for (;;)
        {
          label395:
          if (paramInt != 0)
          {
            if (!((View)localObject2).hasFocusable()) {
              break label612;
            }
            k = localLayoutParams.mSpanIndex;
            paramInt = Math.min(i10, i7);
            m = Math.max(i9, i6);
            paramView = (View)localObject1;
            m = paramInt - m;
            paramInt = i;
            localObject1 = localObject2;
            i = j;
            j = m;
            break;
            paramInt = Math.max(i9, i6);
            i4 = Math.min(i10, i7) - paramInt;
            if (((View)localObject2).hasFocusable())
            {
              if (i4 > k)
              {
                paramInt = 1;
              }
              else
              {
                paramInt = i5;
                if (i4 == k)
                {
                  if (i9 > m) {}
                  for (i4 = 1;; i4 = 0)
                  {
                    paramInt = i5;
                    if (i2 != i4) {
                      break;
                    }
                    paramInt = 1;
                    break;
                  }
                }
              }
            }
            else
            {
              paramInt = i5;
              if (paramView == null)
              {
                paramInt = i5;
                if (isViewPartiallyVisible((View)localObject2, false, true)) {
                  if (i4 > i)
                  {
                    paramInt = 1;
                  }
                  else
                  {
                    paramInt = i5;
                    if (i4 == i) {
                      if (i9 <= j) {
                        break label606;
                      }
                    }
                  }
                }
              }
            }
          }
        }
        label606:
        for (i4 = 1;; i4 = 0)
        {
          paramInt = i5;
          if (i2 != i4) {
            break label395;
          }
          paramInt = 1;
          break label395;
          break;
        }
        label612:
        i = localLayoutParams.mSpanIndex;
        paramInt = Math.min(i10, i7) - Math.max(i9, i6);
        j = k;
        k = m;
        localObject1 = paramView;
        paramView = (View)localObject2;
      }
      label652:
      paramView = (View)localObject1;
    }
  }
  
  public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (!(localLayoutParams instanceof LayoutParams))
    {
      super.onInitializeAccessibilityNodeInfoForItem(paramView, paramAccessibilityNodeInfoCompat);
      return;
    }
    paramView = (LayoutParams)localLayoutParams;
    int i = getSpanGroupIndex(paramRecycler, paramState, paramView.getViewLayoutPosition());
    if (this.mOrientation == 0)
    {
      j = paramView.getSpanIndex();
      k = paramView.getSpanSize();
      if ((this.mSpanCount > 1) && (paramView.getSpanSize() == this.mSpanCount)) {}
      for (bool = true;; bool = false)
      {
        paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(j, k, i, 1, bool, false));
        return;
      }
    }
    int j = paramView.getSpanIndex();
    int k = paramView.getSpanSize();
    if ((this.mSpanCount > 1) && (paramView.getSpanSize() == this.mSpanCount)) {}
    for (boolean bool = true;; bool = false)
    {
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, j, k, bool, false));
      return;
    }
  }
  
  public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsChanged(RecyclerView paramRecyclerView)
  {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject)
  {
    this.mSpanSizeLookup.invalidateSpanIndexCache();
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (paramState.isPreLayout()) {
      cachePreLayoutSpanMapping();
    }
    super.onLayoutChildren(paramRecycler, paramState);
    clearPreLayoutSpanMappingCache();
  }
  
  public void onLayoutCompleted(RecyclerView.State paramState)
  {
    super.onLayoutCompleted(paramState);
    this.mPendingSpanCountChange = false;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    updateMeasurements();
    ensureViewSet();
    return super.scrollHorizontallyBy(paramInt, paramRecycler, paramState);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    updateMeasurements();
    ensureViewSet();
    return super.scrollVerticallyBy(paramInt, paramRecycler, paramState);
  }
  
  public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2)
  {
    if (this.mCachedBorders == null) {
      super.setMeasuredDimension(paramRect, paramInt1, paramInt2);
    }
    int i = getPaddingLeft();
    int j = getPaddingRight() + i;
    int k = getPaddingTop() + getPaddingBottom();
    if (this.mOrientation == 1)
    {
      i = chooseSize(paramInt2, k + paramRect.height(), getMinimumHeight());
      paramInt2 = chooseSize(paramInt1, j + this.mCachedBorders[(this.mCachedBorders.length - 1)], getMinimumWidth());
      paramInt1 = i;
    }
    for (;;)
    {
      setMeasuredDimension(paramInt2, paramInt1);
      return;
      i = chooseSize(paramInt1, j + paramRect.width(), getMinimumWidth());
      paramInt1 = chooseSize(paramInt2, k + this.mCachedBorders[(this.mCachedBorders.length - 1)], getMinimumHeight());
      paramInt2 = i;
    }
  }
  
  public void setSpanCount(int paramInt)
  {
    if (paramInt == this.mSpanCount) {
      return;
    }
    this.mPendingSpanCountChange = true;
    if (paramInt < 1) {
      throw new IllegalArgumentException("Span count should be at least 1. Provided " + paramInt);
    }
    this.mSpanCount = paramInt;
    this.mSpanSizeLookup.invalidateSpanIndexCache();
    requestLayout();
  }
  
  public void setSpanSizeLookup(SpanSizeLookup paramSpanSizeLookup)
  {
    this.mSpanSizeLookup = paramSpanSizeLookup;
  }
  
  public void setStackFromEnd(boolean paramBoolean)
  {
    if (paramBoolean) {
      throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }
    super.setStackFromEnd(false);
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return (this.mPendingSavedState == null) && (!this.mPendingSpanCountChange);
  }
  
  public static final class DefaultSpanSizeLookup
    extends GridLayoutManager.SpanSizeLookup
  {
    public int getSpanIndex(int paramInt1, int paramInt2)
    {
      return paramInt1 % paramInt2;
    }
    
    public int getSpanSize(int paramInt)
    {
      return 1;
    }
  }
  
  public static class LayoutParams
    extends RecyclerView.LayoutParams
  {
    public static final int INVALID_SPAN_ID = -1;
    int mSpanIndex = -1;
    int mSpanSize = 0;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(RecyclerView.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public int getSpanIndex()
    {
      return this.mSpanIndex;
    }
    
    public int getSpanSize()
    {
      return this.mSpanSize;
    }
  }
  
  public static abstract class SpanSizeLookup
  {
    private boolean mCacheSpanIndices = false;
    final SparseIntArray mSpanIndexCache = new SparseIntArray();
    
    int findReferenceIndexFromCache(int paramInt)
    {
      int i = 0;
      int j = this.mSpanIndexCache.size() - 1;
      while (i <= j)
      {
        int k = i + j >>> 1;
        if (this.mSpanIndexCache.keyAt(k) < paramInt) {
          i = k + 1;
        } else {
          j = k - 1;
        }
      }
      paramInt = i - 1;
      if ((paramInt >= 0) && (paramInt < this.mSpanIndexCache.size())) {
        return this.mSpanIndexCache.keyAt(paramInt);
      }
      return -1;
    }
    
    int getCachedSpanIndex(int paramInt1, int paramInt2)
    {
      int i;
      if (!this.mCacheSpanIndices) {
        i = getSpanIndex(paramInt1, paramInt2);
      }
      int j;
      do
      {
        return i;
        j = this.mSpanIndexCache.get(paramInt1, -1);
        i = j;
      } while (j != -1);
      paramInt2 = getSpanIndex(paramInt1, paramInt2);
      this.mSpanIndexCache.put(paramInt1, paramInt2);
      return paramInt2;
    }
    
    public int getSpanGroupIndex(int paramInt1, int paramInt2)
    {
      int n = getSpanSize(paramInt1);
      int k = 0;
      int i = 0;
      int j = 0;
      int m;
      if (k < paramInt1)
      {
        m = getSpanSize(k);
        j += m;
        if (j == paramInt2)
        {
          j = i + 1;
          i = 0;
        }
      }
      for (;;)
      {
        m = k + 1;
        k = i;
        i = j;
        j = k;
        k = m;
        break;
        if (j > paramInt2)
        {
          j = i + 1;
          i = m;
          continue;
          paramInt1 = i;
          if (j + n > paramInt2) {
            paramInt1 = i + 1;
          }
          return paramInt1;
        }
        else
        {
          m = j;
          j = i;
          i = m;
        }
      }
    }
    
    public int getSpanIndex(int paramInt1, int paramInt2)
    {
      int n = getSpanSize(paramInt1);
      if (n == paramInt2) {
        return 0;
      }
      int j;
      int i;
      if ((this.mCacheSpanIndices) && (this.mSpanIndexCache.size() > 0))
      {
        j = findReferenceIndexFromCache(paramInt1);
        if (j >= 0)
        {
          i = this.mSpanIndexCache.get(j) + getSpanSize(j);
          j += 1;
        }
      }
      for (;;)
      {
        if (j < paramInt1)
        {
          int k = getSpanSize(j);
          int m = i + k;
          if (m == paramInt2) {
            i = 0;
          }
          for (;;)
          {
            j += 1;
            break;
            i = k;
            if (m <= paramInt2) {
              i = m;
            }
          }
        }
        if (i + n > paramInt2) {
          break;
        }
        return i;
        j = 0;
        i = 0;
      }
    }
    
    public abstract int getSpanSize(int paramInt);
    
    public void invalidateSpanIndexCache()
    {
      this.mSpanIndexCache.clear();
    }
    
    public boolean isSpanIndexCacheEnabled()
    {
      return this.mCacheSpanIndices;
    }
    
    public void setSpanIndexCacheEnabled(boolean paramBoolean)
    {
      this.mCacheSpanIndices = paramBoolean;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\GridLayoutManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */