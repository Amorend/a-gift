package android.support.design.widget;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.R.attr;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.design.R.style;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;

public class BottomSheetDialog
  extends AppCompatDialog
{
  private BottomSheetBehavior<FrameLayout> mBehavior;
  private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback()
  {
    public void onSlide(@NonNull View paramAnonymousView, float paramAnonymousFloat) {}
    
    public void onStateChanged(@NonNull View paramAnonymousView, int paramAnonymousInt)
    {
      if (paramAnonymousInt == 5) {
        BottomSheetDialog.this.cancel();
      }
    }
  };
  boolean mCancelable = true;
  private boolean mCanceledOnTouchOutside = true;
  private boolean mCanceledOnTouchOutsideSet;
  
  public BottomSheetDialog(@NonNull Context paramContext)
  {
    this(paramContext, 0);
  }
  
  public BottomSheetDialog(@NonNull Context paramContext, @StyleRes int paramInt)
  {
    super(paramContext, getThemeResId(paramContext, paramInt));
    supportRequestWindowFeature(1);
  }
  
  protected BottomSheetDialog(@NonNull Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    supportRequestWindowFeature(1);
    this.mCancelable = paramBoolean;
  }
  
  private static int getThemeResId(Context paramContext, int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0)
    {
      TypedValue localTypedValue = new TypedValue();
      if (paramContext.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, localTypedValue, true)) {
        i = localTypedValue.resourceId;
      }
    }
    else
    {
      return i;
    }
    return R.style.Theme_Design_Light_BottomSheetDialog;
  }
  
  private View wrapInBottomSheet(int paramInt, View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    CoordinatorLayout localCoordinatorLayout = (CoordinatorLayout)View.inflate(getContext(), R.layout.design_bottom_sheet_dialog, null);
    View localView = paramView;
    if (paramInt != 0)
    {
      localView = paramView;
      if (paramView == null) {
        localView = getLayoutInflater().inflate(paramInt, localCoordinatorLayout, false);
      }
    }
    paramView = (FrameLayout)localCoordinatorLayout.findViewById(R.id.design_bottom_sheet);
    this.mBehavior = BottomSheetBehavior.from(paramView);
    this.mBehavior.setBottomSheetCallback(this.mBottomSheetCallback);
    this.mBehavior.setHideable(this.mCancelable);
    if (paramLayoutParams == null) {
      paramView.addView(localView);
    }
    for (;;)
    {
      localCoordinatorLayout.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((BottomSheetDialog.this.mCancelable) && (BottomSheetDialog.this.isShowing()) && (BottomSheetDialog.this.shouldWindowCloseOnTouchOutside())) {
            BottomSheetDialog.this.cancel();
          }
        }
      });
      ViewCompat.setAccessibilityDelegate(paramView, new AccessibilityDelegateCompat()
      {
        public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, AccessibilityNodeInfoCompat paramAnonymousAccessibilityNodeInfoCompat)
        {
          super.onInitializeAccessibilityNodeInfo(paramAnonymousView, paramAnonymousAccessibilityNodeInfoCompat);
          if (BottomSheetDialog.this.mCancelable)
          {
            paramAnonymousAccessibilityNodeInfoCompat.addAction(1048576);
            paramAnonymousAccessibilityNodeInfoCompat.setDismissable(true);
            return;
          }
          paramAnonymousAccessibilityNodeInfoCompat.setDismissable(false);
        }
        
        public boolean performAccessibilityAction(View paramAnonymousView, int paramAnonymousInt, Bundle paramAnonymousBundle)
        {
          if ((paramAnonymousInt == 1048576) && (BottomSheetDialog.this.mCancelable))
          {
            BottomSheetDialog.this.cancel();
            return true;
          }
          return super.performAccessibilityAction(paramAnonymousView, paramAnonymousInt, paramAnonymousBundle);
        }
      });
      return localCoordinatorLayout;
      paramView.addView(localView, paramLayoutParams);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().setLayout(-1, -1);
  }
  
  protected void onStart()
  {
    super.onStart();
    if (this.mBehavior != null) {
      this.mBehavior.setState(4);
    }
  }
  
  public void setCancelable(boolean paramBoolean)
  {
    super.setCancelable(paramBoolean);
    if (this.mCancelable != paramBoolean)
    {
      this.mCancelable = paramBoolean;
      if (this.mBehavior != null) {
        this.mBehavior.setHideable(paramBoolean);
      }
    }
  }
  
  public void setCanceledOnTouchOutside(boolean paramBoolean)
  {
    super.setCanceledOnTouchOutside(paramBoolean);
    if ((paramBoolean) && (!this.mCancelable)) {
      this.mCancelable = true;
    }
    this.mCanceledOnTouchOutside = paramBoolean;
    this.mCanceledOnTouchOutsideSet = true;
  }
  
  public void setContentView(@LayoutRes int paramInt)
  {
    super.setContentView(wrapInBottomSheet(paramInt, null, null));
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(wrapInBottomSheet(0, paramView, null));
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(wrapInBottomSheet(0, paramView, paramLayoutParams));
  }
  
  boolean shouldWindowCloseOnTouchOutside()
  {
    if (!this.mCanceledOnTouchOutsideSet)
    {
      if (Build.VERSION.SDK_INT >= 11) {
        break label30;
      }
      this.mCanceledOnTouchOutside = true;
    }
    for (;;)
    {
      this.mCanceledOnTouchOutsideSet = true;
      return this.mCanceledOnTouchOutside;
      label30:
      TypedArray localTypedArray = getContext().obtainStyledAttributes(new int[] { 16843611 });
      this.mCanceledOnTouchOutside = localTypedArray.getBoolean(0, true);
      localTypedArray.recycle();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\BottomSheetDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */