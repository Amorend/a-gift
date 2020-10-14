package android.support.v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi(23)
class ContextCompatApi23
{
  public static int getColor(Context paramContext, int paramInt)
  {
    return paramContext.getColor(paramInt);
  }
  
  public static ColorStateList getColorStateList(Context paramContext, int paramInt)
  {
    return paramContext.getColorStateList(paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\content\ContextCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */