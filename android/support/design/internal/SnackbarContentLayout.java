package android.support.design.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.design.R.dimen;
import android.support.design.R.id;
import android.support.design.R.styleable;
import android.support.design.widget.BaseTransientBottomBar.ContentViewCallback;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SnackbarContentLayout
  extends LinearLayout
  implements BaseTransientBottomBar.ContentViewCallback
{
  private Button mActionView;
  private int mMaxInlineActionWidth;
  private int mMaxWidth;
  private TextView mMessageView;
  
  public SnackbarContentLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SnackbarContentLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SnackbarLayout);
    this.mMaxWidth = paramContext.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
    this.mMaxInlineActionWidth = paramContext.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
    paramContext.recycle();
  }
  
  private static void updateTopBottomPadding(View paramView, int paramInt1, int paramInt2)
  {
    if (ViewCompat.isPaddingRelative(paramView))
    {
      ViewCompat.setPaddingRelative(paramView, ViewCompat.getPaddingStart(paramView), paramInt1, ViewCompat.getPaddingEnd(paramView), paramInt2);
      return;
    }
    paramView.setPadding(paramView.getPaddingLeft(), paramInt1, paramView.getPaddingRight(), paramInt2);
  }
  
  private boolean updateViewsWithinLayout(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    if (paramInt1 != getOrientation())
    {
      setOrientation(paramInt1);
      bool = true;
    }
    if ((this.mMessageView.getPaddingTop() != paramInt2) || (this.mMessageView.getPaddingBottom() != paramInt3))
    {
      updateTopBottomPadding(this.mMessageView, paramInt2, paramInt3);
      bool = true;
    }
    return bool;
  }
  
  public void animateContentIn(int paramInt1, int paramInt2)
  {
    ViewCompat.setAlpha(this.mMessageView, 0.0F);
    ViewCompat.animate(this.mMessageView).alpha(1.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
    if (this.mActionView.getVisibility() == 0)
    {
      ViewCompat.setAlpha(this.mActionView, 0.0F);
      ViewCompat.animate(this.mActionView).alpha(1.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
    }
  }
  
  public void animateContentOut(int paramInt1, int paramInt2)
  {
    ViewCompat.setAlpha(this.mMessageView, 1.0F);
    ViewCompat.animate(this.mMessageView).alpha(0.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
    if (this.mActionView.getVisibility() == 0)
    {
      ViewCompat.setAlpha(this.mActionView, 1.0F);
      ViewCompat.animate(this.mActionView).alpha(0.0F).setDuration(paramInt2).setStartDelay(paramInt1).start();
    }
  }
  
  public Button getActionView()
  {
    return this.mActionView;
  }
  
  public TextView getMessageView()
  {
    return this.mMessageView;
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mMessageView = ((TextView)findViewById(R.id.snackbar_text));
    this.mActionView = ((Button)findViewById(R.id.snackbar_action));
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = paramInt1;
    if (this.mMaxWidth > 0)
    {
      i = paramInt1;
      if (getMeasuredWidth() > this.mMaxWidth)
      {
        i = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, 1073741824);
        super.onMeasure(i, paramInt2);
      }
    }
    int j = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
    int k = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
    if (this.mMessageView.getLayout().getLineCount() > 1)
    {
      paramInt1 = 1;
      if ((paramInt1 == 0) || (this.mMaxInlineActionWidth <= 0) || (this.mActionView.getMeasuredWidth() <= this.mMaxInlineActionWidth)) {
        break label142;
      }
      if (!updateViewsWithinLayout(1, j, j - k)) {
        break label170;
      }
      paramInt1 = 1;
    }
    for (;;)
    {
      if (paramInt1 != 0) {
        super.onMeasure(i, paramInt2);
      }
      return;
      paramInt1 = 0;
      break;
      label142:
      if (paramInt1 != 0) {}
      for (paramInt1 = j;; paramInt1 = k)
      {
        if (!updateViewsWithinLayout(0, paramInt1, paramInt1)) {
          break label170;
        }
        paramInt1 = 1;
        break;
      }
      label170:
      paramInt1 = 0;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\internal\SnackbarContentLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */