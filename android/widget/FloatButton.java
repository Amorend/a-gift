package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class FloatButton
  extends ImageView
{
  private PopupWindow a;
  private CircleImageView b;
  private int c;
  private CardView d;
  private DisplayMetrics e;
  private RippleHelper f;
  
  public FloatButton(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }
  
  private int a(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, this.e);
  }
  
  private void a(Context paramContext)
  {
    this.e = paramContext.getResources().getDisplayMetrics();
    FrameLayout localFrameLayout = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(a(16.0F), a(16.0F), a(16.0F), a(16.0F));
    this.d = new CardView(paramContext);
    this.d.setCardElevation(a(8.0F));
    this.b = new CircleImageView(paramContext);
    this.b.setImageResource(2130771968);
    this.f = new RippleHelper(this.b);
    paramContext = new FrameLayout.LayoutParams(a(64.0F), a(64.0F));
    localFrameLayout.addView(this.d, localLayoutParams);
    this.d.addView(this.b, paramContext);
    this.d.setRadius(a(32.0F));
    this.a = new PopupWindow(-2, -2);
    this.a.setContentView(localFrameLayout);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt1, paramInt2);
    if (!this.a.isShowing()) {
      this.a.showAtLocation((View)getParent(), this.c, 0, 0);
    }
    this.a.update();
  }
  
  public void setGravity(int paramInt)
  {
    this.c = paramInt;
  }
  
  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.b.setImageBitmap(paramBitmap);
  }
  
  public void setImageDrawable(Drawable paramDrawable)
  {
    this.b.setImageDrawable(paramDrawable);
  }
  
  public void setImageResource(int paramInt)
  {
    this.b.setImageResource(paramInt);
  }
  
  public void setRippleColor(int paramInt)
  {
    this.f.setRippleColor(paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\FloatButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */