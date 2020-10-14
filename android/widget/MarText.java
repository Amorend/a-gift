package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;

public class MarText
  extends TextView
{
  public MarText(Context paramContext)
  {
    super(paramContext);
  }
  
  public MarText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MarText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  @ViewDebug.ExportedProperty(category="focus")
  @Override
  public boolean isFocused()
  {
    return true;
  }
  
  @Override
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(true, paramInt, paramRect);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\MarText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */