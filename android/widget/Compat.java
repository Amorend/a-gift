package android.widget;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

public class Compat
{
  private static final int SIXTY_FPS_INTERVAL = 16;
  
  public static int getPointerIndex(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      return getPointerIndexHoneyComb(paramInt);
    }
    return getPointerIndexEclair(paramInt);
  }
  
  @TargetApi(5)
  @SuppressWarnings("deprecation")
  private static int getPointerIndexEclair(int paramInt)
  {
    return (paramInt & 0xFF00) >> 8;
  }
  
  @TargetApi(11)
  private static int getPointerIndexHoneyComb(int paramInt)
  {
    return (paramInt & 0xFF00) >> 8;
  }
  
  public static void postOnAnimation(View paramView, Runnable paramRunnable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      postOnAnimationJellyBean(paramView, paramRunnable);
      return;
    }
    paramView.postDelayed(paramRunnable, 16);
  }
  
  @TargetApi(16)
  private static void postOnAnimationJellyBean(View paramView, Runnable paramRunnable)
  {
    paramView.postOnAnimation(paramRunnable);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\Compat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */