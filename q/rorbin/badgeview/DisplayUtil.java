package q.rorbin.badgeview;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayUtil
{
  public static int dp2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static int px2dp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\q\rorbin\badgeview\DisplayUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */