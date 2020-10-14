package android.support.design.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.R.styleable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;

public final class TabItem
  extends View
{
  final int mCustomLayout;
  final Drawable mIcon;
  final CharSequence mText;
  
  public TabItem(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TabItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.TabItem);
    this.mText = paramContext.getText(R.styleable.TabItem_android_text);
    this.mIcon = paramContext.getDrawable(R.styleable.TabItem_android_icon);
    this.mCustomLayout = paramContext.getResourceId(R.styleable.TabItem_android_layout, 0);
    paramContext.recycle();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\TabItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */