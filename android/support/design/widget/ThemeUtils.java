package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.attr;

class ThemeUtils
{
  private static final int[] APPCOMPAT_CHECK_ATTRS = { R.attr.colorPrimary };
  
  static void checkAppCompatTheme(Context paramContext)
  {
    int i = 0;
    paramContext = paramContext.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
    if (!paramContext.hasValue(0)) {
      i = 1;
    }
    paramContext.recycle();
    if (i != 0) {
      throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ThemeUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */