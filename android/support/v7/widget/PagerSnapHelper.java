package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;

public class PagerSnapHelper
  extends SnapHelper
{
  private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
  @Nullable
  private OrientationHelper mHorizontalHelper;
  @Nullable
  private OrientationHelper mVerticalHelper;
  
  private int distanceToCenter(@NonNull RecyclerView.LayoutManager paramLayoutManager, @NonNull View paramView, OrientationHelper paramOrientationHelper)
  {
    int j = paramOrientationHelper.getDecoratedStart(paramView);
    int k = paramOrientationHelper.getDecoratedMeasurement(paramView) / 2;
    if (paramLayoutManager.getClipToPadding()) {}
    for (int i = paramOrientationHelper.getStartAfterPadding() + paramOrientationHelper.getTotalSpace() / 2;; i = paramOrientationHelper.getEnd() / 2) {
      return k + j - i;
    }
  }
  
  @Nullable
  private View findCenterView(RecyclerView.LayoutManager paramLayoutManager, OrientationHelper paramOrientationHelper)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    int n = paramLayoutManager.getChildCount();
    if (n == 0) {}
    int j;
    int i;
    int k;
    label46:
    do
    {
      return (View)localObject2;
      if (!paramLayoutManager.getClipToPadding()) {
        break;
      }
      j = paramOrientationHelper.getStartAfterPadding() + paramOrientationHelper.getTotalSpace() / 2;
      i = Integer.MAX_VALUE;
      k = 0;
      localObject2 = localObject1;
    } while (k >= n);
    localObject2 = paramLayoutManager.getChildAt(k);
    int m = Math.abs(paramOrientationHelper.getDecoratedStart((View)localObject2) + paramOrientationHelper.getDecoratedMeasurement((View)localObject2) / 2 - j);
    if (m < i)
    {
      localObject1 = localObject2;
      i = m;
    }
    for (;;)
    {
      k += 1;
      break label46;
      j = paramOrientationHelper.getEnd() / 2;
      break;
    }
  }
  
  @Nullable
  private View findStartView(RecyclerView.LayoutManager paramLayoutManager, OrientationHelper paramOrientationHelper)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    int m = paramLayoutManager.getChildCount();
    if (m == 0) {}
    int i;
    int j;
    do
    {
      return (View)localObject2;
      i = Integer.MAX_VALUE;
      j = 0;
      localObject2 = localObject1;
    } while (j >= m);
    localObject2 = paramLayoutManager.getChildAt(j);
    int k = paramOrientationHelper.getDecoratedStart((View)localObject2);
    if (k < i)
    {
      localObject1 = localObject2;
      i = k;
    }
    for (;;)
    {
      j += 1;
      break;
    }
  }
  
  @NonNull
  private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager paramLayoutManager)
  {
    if ((this.mHorizontalHelper == null) || (this.mHorizontalHelper.mLayoutManager != paramLayoutManager)) {
      this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(paramLayoutManager);
    }
    return this.mHorizontalHelper;
  }
  
  @NonNull
  private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager paramLayoutManager)
  {
    if ((this.mVerticalHelper == null) || (this.mVerticalHelper.mLayoutManager != paramLayoutManager)) {
      this.mVerticalHelper = OrientationHelper.createVerticalHelper(paramLayoutManager);
    }
    return this.mVerticalHelper;
  }
  
  @Nullable
  public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager paramLayoutManager, @NonNull View paramView)
  {
    int[] arrayOfInt = new int[2];
    if (paramLayoutManager.canScrollHorizontally()) {
      arrayOfInt[0] = distanceToCenter(paramLayoutManager, paramView, getHorizontalHelper(paramLayoutManager));
    }
    while (paramLayoutManager.canScrollVertically())
    {
      arrayOfInt[1] = distanceToCenter(paramLayoutManager, paramView, getVerticalHelper(paramLayoutManager));
      return arrayOfInt;
      arrayOfInt[0] = 0;
    }
    arrayOfInt[1] = 0;
    return arrayOfInt;
  }
  
  protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager paramLayoutManager)
  {
    if (!(paramLayoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
      return null;
    }
    new LinearSmoothScroller(this.mRecyclerView.getContext())
    {
      protected float calculateSpeedPerPixel(DisplayMetrics paramAnonymousDisplayMetrics)
      {
        return 100.0F / paramAnonymousDisplayMetrics.densityDpi;
      }
      
      protected int calculateTimeForScrolling(int paramAnonymousInt)
      {
        return Math.min(100, super.calculateTimeForScrolling(paramAnonymousInt));
      }
      
      protected void onTargetFound(View paramAnonymousView, RecyclerView.State paramAnonymousState, RecyclerView.SmoothScroller.Action paramAnonymousAction)
      {
        paramAnonymousView = PagerSnapHelper.this.calculateDistanceToFinalSnap(PagerSnapHelper.this.mRecyclerView.getLayoutManager(), paramAnonymousView);
        int i = paramAnonymousView[0];
        int j = paramAnonymousView[1];
        int k = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(j)));
        if (k > 0) {
          paramAnonymousAction.update(i, j, k, this.mDecelerateInterpolator);
        }
      }
    };
  }
  
  @Nullable
  public View findSnapView(RecyclerView.LayoutManager paramLayoutManager)
  {
    if (paramLayoutManager.canScrollVertically()) {
      return findCenterView(paramLayoutManager, getVerticalHelper(paramLayoutManager));
    }
    if (paramLayoutManager.canScrollHorizontally()) {
      return findCenterView(paramLayoutManager, getHorizontalHelper(paramLayoutManager));
    }
    return null;
  }
  
  public int findTargetSnapPosition(RecyclerView.LayoutManager paramLayoutManager, int paramInt1, int paramInt2)
  {
    int i = 0;
    int k = paramLayoutManager.getItemCount();
    if (k == 0) {}
    View localView;
    label38:
    int j;
    do
    {
      return -1;
      localView = null;
      if (!paramLayoutManager.canScrollVertically()) {
        break;
      }
      localView = findStartView(paramLayoutManager, getVerticalHelper(paramLayoutManager));
      if (localView == null) {
        break label157;
      }
      j = paramLayoutManager.getPosition(localView);
    } while (j == -1);
    if (paramLayoutManager.canScrollHorizontally()) {
      if (paramInt1 > 0) {
        paramInt1 = 1;
      }
    }
    for (;;)
    {
      paramInt2 = i;
      if ((paramLayoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))
      {
        paramLayoutManager = ((RecyclerView.SmoothScroller.ScrollVectorProvider)paramLayoutManager).computeScrollVectorForPosition(k - 1);
        paramInt2 = i;
        if (paramLayoutManager != null) {
          if (paramLayoutManager.x >= 0.0F)
          {
            paramInt2 = i;
            if (paramLayoutManager.y >= 0.0F) {}
          }
          else
          {
            paramInt2 = 1;
          }
        }
      }
      if (paramInt2 == 0) {
        break label181;
      }
      if (paramInt1 == 0) {
        break label178;
      }
      return j - 1;
      if (!paramLayoutManager.canScrollHorizontally()) {
        break label38;
      }
      localView = findStartView(paramLayoutManager, getHorizontalHelper(paramLayoutManager));
      break label38;
      label157:
      break;
      paramInt1 = 0;
      continue;
      if (paramInt2 > 0) {
        paramInt1 = 1;
      } else {
        paramInt1 = 0;
      }
    }
    label178:
    return j;
    label181:
    if (paramInt1 != 0) {
      return j + 1;
    }
    return j;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\PagerSnapHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */