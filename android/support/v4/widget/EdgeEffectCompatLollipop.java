package android.support.v4.widget;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

@TargetApi(21)
@RequiresApi(21)
class EdgeEffectCompatLollipop
{
  public static boolean onPull(Object paramObject, float paramFloat1, float paramFloat2)
  {
    ((EdgeEffect)paramObject).onPull(paramFloat1, paramFloat2);
    return true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\widget\EdgeEffectCompatLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */