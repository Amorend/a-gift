package android.support.design.widget;

import android.graphics.drawable.Drawable;

abstract interface ShadowViewDelegate
{
  public abstract float getRadius();
  
  public abstract boolean isCompatPaddingEnabled();
  
  public abstract void setBackgroundDrawable(Drawable paramDrawable);
  
  public abstract void setShadowPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ShadowViewDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */