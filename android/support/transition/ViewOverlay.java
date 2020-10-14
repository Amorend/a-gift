package android.support.transition;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@TargetApi(14)
@RequiresApi(14)
class ViewOverlay
{
  protected OverlayViewGroup mOverlayViewGroup = new OverlayViewGroup(paramContext, paramViewGroup, paramView, this);
  
  ViewOverlay(Context paramContext, ViewGroup paramViewGroup, View paramView) {}
  
  public static ViewOverlay createFrom(View paramView)
  {
    ViewGroup localViewGroup = getContentView(paramView);
    if (localViewGroup != null)
    {
      int j = localViewGroup.getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = localViewGroup.getChildAt(i);
        if ((localView instanceof OverlayViewGroup)) {
          return ((OverlayViewGroup)localView).mViewOverlay;
        }
        i += 1;
      }
      return new ViewGroupOverlay(localViewGroup.getContext(), localViewGroup, paramView);
    }
    return null;
  }
  
  static ViewGroup getContentView(View paramView)
  {
    while (paramView != null)
    {
      if ((paramView.getId() == 16908290) && ((paramView instanceof ViewGroup))) {
        return (ViewGroup)paramView;
      }
      if ((paramView.getParent() instanceof ViewGroup)) {
        paramView = (ViewGroup)paramView.getParent();
      }
    }
    return null;
  }
  
  public void add(Drawable paramDrawable)
  {
    this.mOverlayViewGroup.add(paramDrawable);
  }
  
  public void clear()
  {
    this.mOverlayViewGroup.clear();
  }
  
  ViewGroup getOverlayView()
  {
    return this.mOverlayViewGroup;
  }
  
  boolean isEmpty()
  {
    return this.mOverlayViewGroup.isEmpty();
  }
  
  public void remove(Drawable paramDrawable)
  {
    this.mOverlayViewGroup.remove(paramDrawable);
  }
  
  static class OverlayViewGroup
    extends ViewGroup
  {
    static Method sInvalidateChildInParentFastMethod;
    ArrayList<Drawable> mDrawables = null;
    ViewGroup mHostView;
    View mRequestingView;
    ViewOverlay mViewOverlay;
    
    static
    {
      try
      {
        sInvalidateChildInParentFastMethod = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", new Class[] { Integer.TYPE, Integer.TYPE, Rect.class });
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}
    }
    
    OverlayViewGroup(Context paramContext, ViewGroup paramViewGroup, View paramView, ViewOverlay paramViewOverlay)
    {
      super();
      this.mHostView = paramViewGroup;
      this.mRequestingView = paramView;
      setRight(paramViewGroup.getWidth());
      setBottom(paramViewGroup.getHeight());
      paramViewGroup.addView(this);
      this.mViewOverlay = paramViewOverlay;
    }
    
    private void getOffset(int[] paramArrayOfInt)
    {
      int[] arrayOfInt1 = new int[2];
      int[] arrayOfInt2 = new int[2];
      ViewGroup localViewGroup = (ViewGroup)getParent();
      this.mHostView.getLocationOnScreen(arrayOfInt1);
      this.mRequestingView.getLocationOnScreen(arrayOfInt2);
      arrayOfInt2[0] -= arrayOfInt1[0];
      arrayOfInt2[1] -= arrayOfInt1[1];
    }
    
    public void add(Drawable paramDrawable)
    {
      if (this.mDrawables == null) {
        this.mDrawables = new ArrayList();
      }
      if (!this.mDrawables.contains(paramDrawable))
      {
        this.mDrawables.add(paramDrawable);
        invalidate(paramDrawable.getBounds());
        paramDrawable.setCallback(this);
      }
    }
    
    public void add(View paramView)
    {
      if ((paramView.getParent() instanceof ViewGroup))
      {
        ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
        if ((localViewGroup != this.mHostView) && (localViewGroup.getParent() != null))
        {
          int[] arrayOfInt1 = new int[2];
          int[] arrayOfInt2 = new int[2];
          localViewGroup.getLocationOnScreen(arrayOfInt1);
          this.mHostView.getLocationOnScreen(arrayOfInt2);
          ViewCompat.offsetLeftAndRight(paramView, arrayOfInt1[0] - arrayOfInt2[0]);
          ViewCompat.offsetTopAndBottom(paramView, arrayOfInt1[1] - arrayOfInt2[1]);
        }
        localViewGroup.removeView(paramView);
        if (paramView.getParent() != null) {
          localViewGroup.removeView(paramView);
        }
      }
      super.addView(paramView, getChildCount() - 1);
    }
    
    public void clear()
    {
      removeAllViews();
      if (this.mDrawables != null) {
        this.mDrawables.clear();
      }
    }
    
    protected void dispatchDraw(Canvas paramCanvas)
    {
      int j = 0;
      int[] arrayOfInt1 = new int[2];
      int[] arrayOfInt2 = new int[2];
      ViewGroup localViewGroup = (ViewGroup)getParent();
      this.mHostView.getLocationOnScreen(arrayOfInt1);
      this.mRequestingView.getLocationOnScreen(arrayOfInt2);
      paramCanvas.translate(arrayOfInt2[0] - arrayOfInt1[0], arrayOfInt2[1] - arrayOfInt1[1]);
      paramCanvas.clipRect(new Rect(0, 0, this.mRequestingView.getWidth(), this.mRequestingView.getHeight()));
      super.dispatchDraw(paramCanvas);
      int i;
      if (this.mDrawables == null) {
        i = 0;
      }
      while (j < i)
      {
        ((Drawable)this.mDrawables.get(j)).draw(paramCanvas);
        j += 1;
        continue;
        i = this.mDrawables.size();
      }
    }
    
    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    public void invalidateChildFast(View paramView, Rect paramRect)
    {
      if (this.mHostView != null)
      {
        int i = paramView.getLeft();
        int j = paramView.getTop();
        paramView = new int[2];
        getOffset(paramView);
        paramRect.offset(i + paramView[0], j + paramView[1]);
        this.mHostView.invalidate(paramRect);
      }
    }
    
    public ViewParent invalidateChildInParent(int[] paramArrayOfInt, Rect paramRect)
    {
      if (this.mHostView != null)
      {
        paramRect.offset(paramArrayOfInt[0], paramArrayOfInt[1]);
        if ((this.mHostView instanceof ViewGroup))
        {
          paramArrayOfInt[0] = 0;
          paramArrayOfInt[1] = 0;
          int[] arrayOfInt = new int[2];
          getOffset(arrayOfInt);
          paramRect.offset(arrayOfInt[0], arrayOfInt[1]);
          return super.invalidateChildInParent(paramArrayOfInt, paramRect);
        }
        invalidate(paramRect);
      }
      return null;
    }
    
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    protected ViewParent invalidateChildInParentFast(int paramInt1, int paramInt2, Rect paramRect)
    {
      if (((this.mHostView instanceof ViewGroup)) && (sInvalidateChildInParentFastMethod != null)) {}
      try
      {
        getOffset(new int[2]);
        sInvalidateChildInParentFastMethod.invoke(this.mHostView, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramRect });
        return null;
      }
      catch (IllegalAccessException paramRect)
      {
        for (;;)
        {
          paramRect.printStackTrace();
        }
      }
      catch (InvocationTargetException paramRect)
      {
        for (;;)
        {
          paramRect.printStackTrace();
        }
      }
    }
    
    public void invalidateDrawable(Drawable paramDrawable)
    {
      invalidate(paramDrawable.getBounds());
    }
    
    boolean isEmpty()
    {
      return (getChildCount() == 0) && ((this.mDrawables == null) || (this.mDrawables.size() == 0));
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
    
    public void remove(Drawable paramDrawable)
    {
      if (this.mDrawables != null)
      {
        this.mDrawables.remove(paramDrawable);
        invalidate(paramDrawable.getBounds());
        paramDrawable.setCallback(null);
      }
    }
    
    public void remove(View paramView)
    {
      super.removeView(paramView);
      if (isEmpty()) {
        this.mHostView.removeView(this);
      }
    }
    
    protected boolean verifyDrawable(Drawable paramDrawable)
    {
      return (super.verifyDrawable(paramDrawable)) || ((this.mDrawables != null) && (this.mDrawables.contains(paramDrawable)));
    }
    
    static class TouchInterceptor
      extends View
    {
      TouchInterceptor(Context paramContext)
      {
        super();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\ViewOverlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */