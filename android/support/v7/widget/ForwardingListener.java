package android.support.v7.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ForwardingListener
  implements View.OnTouchListener
{
  private int mActivePointerId;
  private Runnable mDisallowIntercept;
  private boolean mForwarding;
  private final int mLongPressTimeout;
  private final float mScaledTouchSlop;
  final View mSrc;
  private final int mTapTimeout;
  private final int[] mTmpLocation = new int[2];
  private Runnable mTriggerLongPress;
  
  public ForwardingListener(View paramView)
  {
    this.mSrc = paramView;
    paramView.setLongClickable(true);
    if (Build.VERSION.SDK_INT >= 12) {
      addDetachListenerApi12(paramView);
    }
    for (;;)
    {
      this.mScaledTouchSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
      this.mTapTimeout = ViewConfiguration.getTapTimeout();
      this.mLongPressTimeout = ((this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2);
      return;
      addDetachListenerBase(paramView);
    }
  }
  
  @TargetApi(12)
  @RequiresApi(12)
  private void addDetachListenerApi12(View paramView)
  {
    paramView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener()
    {
      public void onViewAttachedToWindow(View paramAnonymousView) {}
      
      public void onViewDetachedFromWindow(View paramAnonymousView)
      {
        ForwardingListener.this.onDetachedFromWindow();
      }
    });
  }
  
  private void addDetachListenerBase(View paramView)
  {
    paramView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      boolean mIsAttached = ViewCompat.isAttachedToWindow(ForwardingListener.this.mSrc);
      
      public void onGlobalLayout()
      {
        boolean bool = this.mIsAttached;
        this.mIsAttached = ViewCompat.isAttachedToWindow(ForwardingListener.this.mSrc);
        if ((bool) && (!this.mIsAttached)) {
          ForwardingListener.this.onDetachedFromWindow();
        }
      }
    });
  }
  
  private void clearCallbacks()
  {
    if (this.mTriggerLongPress != null) {
      this.mSrc.removeCallbacks(this.mTriggerLongPress);
    }
    if (this.mDisallowIntercept != null) {
      this.mSrc.removeCallbacks(this.mDisallowIntercept);
    }
  }
  
  private void onDetachedFromWindow()
  {
    this.mForwarding = false;
    this.mActivePointerId = -1;
    if (this.mDisallowIntercept != null) {
      this.mSrc.removeCallbacks(this.mDisallowIntercept);
    }
  }
  
  private boolean onTouchForwarded(MotionEvent paramMotionEvent)
  {
    View localView = this.mSrc;
    Object localObject = getPopup();
    if ((localObject == null) || (!((ShowableListMenu)localObject).isShowing())) {}
    do
    {
      return false;
      localObject = (DropDownListView)((ShowableListMenu)localObject).getListView();
    } while ((localObject == null) || (!((DropDownListView)localObject).isShown()));
    MotionEvent localMotionEvent = MotionEvent.obtainNoHistory(paramMotionEvent);
    toGlobalMotionEvent(localView, localMotionEvent);
    toLocalMotionEvent((View)localObject, localMotionEvent);
    boolean bool = ((DropDownListView)localObject).onForwardedEvent(localMotionEvent, this.mActivePointerId);
    localMotionEvent.recycle();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((i != 1) && (i != 3))
    {
      i = 1;
      if ((!bool) || (i == 0)) {
        break label129;
      }
    }
    label129:
    for (bool = true;; bool = false)
    {
      return bool;
      i = 0;
      break;
    }
  }
  
  private boolean onTouchObserved(MotionEvent paramMotionEvent)
  {
    View localView = this.mSrc;
    if (!localView.isEnabled()) {}
    int i;
    do
    {
      return false;
      switch (MotionEventCompat.getActionMasked(paramMotionEvent))
      {
      default: 
        return false;
      case 0: 
        this.mActivePointerId = paramMotionEvent.getPointerId(0);
        if (this.mDisallowIntercept == null) {
          this.mDisallowIntercept = new DisallowIntercept();
        }
        localView.postDelayed(this.mDisallowIntercept, this.mTapTimeout);
        if (this.mTriggerLongPress == null) {
          this.mTriggerLongPress = new TriggerLongPress();
        }
        localView.postDelayed(this.mTriggerLongPress, this.mLongPressTimeout);
        return false;
      case 2: 
        i = paramMotionEvent.findPointerIndex(this.mActivePointerId);
      }
    } while ((i < 0) || (pointInView(localView, paramMotionEvent.getX(i), paramMotionEvent.getY(i), this.mScaledTouchSlop)));
    clearCallbacks();
    localView.getParent().requestDisallowInterceptTouchEvent(true);
    return true;
    clearCallbacks();
    return false;
  }
  
  private static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat1 >= -paramFloat3) && (paramFloat2 >= -paramFloat3) && (paramFloat1 < paramView.getRight() - paramView.getLeft() + paramFloat3) && (paramFloat2 < paramView.getBottom() - paramView.getTop() + paramFloat3);
  }
  
  private boolean toGlobalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = this.mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
    return true;
  }
  
  private boolean toLocalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = this.mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(-arrayOfInt[0], -arrayOfInt[1]);
    return true;
  }
  
  public abstract ShowableListMenu getPopup();
  
  protected boolean onForwardingStarted()
  {
    ShowableListMenu localShowableListMenu = getPopup();
    if ((localShowableListMenu != null) && (!localShowableListMenu.isShowing())) {
      localShowableListMenu.show();
    }
    return true;
  }
  
  protected boolean onForwardingStopped()
  {
    ShowableListMenu localShowableListMenu = getPopup();
    if ((localShowableListMenu != null) && (localShowableListMenu.isShowing())) {
      localShowableListMenu.dismiss();
    }
    return true;
  }
  
  void onLongPress()
  {
    clearCallbacks();
    View localView = this.mSrc;
    if ((!localView.isEnabled()) || (localView.isLongClickable())) {}
    while (!onForwardingStarted()) {
      return;
    }
    localView.getParent().requestDisallowInterceptTouchEvent(true);
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
    localView.onTouchEvent(localMotionEvent);
    localMotionEvent.recycle();
    this.mForwarding = true;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    boolean bool3 = this.mForwarding;
    if (bool3)
    {
      if ((onTouchForwarded(paramMotionEvent)) || (!onForwardingStopped())) {}
      for (bool1 = true;; bool1 = false)
      {
        this.mForwarding = bool1;
        if (!bool1)
        {
          bool1 = bool2;
          if (!bool3) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      }
    }
    if ((onTouchObserved(paramMotionEvent)) && (onForwardingStarted())) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      if (bool1)
      {
        long l = SystemClock.uptimeMillis();
        paramView = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        this.mSrc.onTouchEvent(paramView);
        paramView.recycle();
      }
      break;
    }
  }
  
  private class DisallowIntercept
    implements Runnable
  {
    DisallowIntercept() {}
    
    public void run()
    {
      ViewParent localViewParent = ForwardingListener.this.mSrc.getParent();
      if (localViewParent != null) {
        localViewParent.requestDisallowInterceptTouchEvent(true);
      }
    }
  }
  
  private class TriggerLongPress
    implements Runnable
  {
    TriggerLongPress() {}
    
    public void run()
    {
      ForwardingListener.this.onLongPress();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\ForwardingListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */