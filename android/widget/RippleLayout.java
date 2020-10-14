package android.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class RippleLayout
  extends FrameLayout
{
  private int a;
  private int b;
  private int c = 1152035498;
  private boolean d;
  private boolean e;
  private int f;
  
  public RippleLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  private void setRippleDrawable(View paramView)
  {
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int j = paramView.getChildCount();
      int i = 0;
      while (i < j)
      {
        localObject = paramView.getChildAt(i);
        if (!(localObject instanceof RippleLayout)) {
          setRippleDrawable((View)localObject);
        }
        i += 1;
      }
    }
    Object localObject = paramView.getBackground();
    if ((localObject instanceof RippleHelper)) {
      paramView = (RippleHelper)localObject;
    } else {
      paramView = new RippleHelper(paramView);
    }
    paramView.setRippleColor(this.c);
    paramView.setRippleLineColor(this.f);
    paramView.setCircle(this.d);
    paramView.setSingle(this.e);
  }
  
  public boolean isCircle()
  {
    return this.d;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.b = getChildCount();
    if (this.a == this.b) {
      return;
    }
    this.a = this.b;
    setRippleDrawable(this);
  }
  
  public void setCircle(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
  
  public void setRippleColor(int paramInt)
  {
    this.c = paramInt;
  }
  
  public void setRippleLineColor(int paramInt)
  {
    this.f = paramInt;
  }
  
  public void setSingle(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\RippleLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */