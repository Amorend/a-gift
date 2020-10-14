package android.support.design.widget;

import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;

class ViewUtils
{
  static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR = new ValueAnimatorCompat.Creator()
  {
    public ValueAnimatorCompat createAnimator()
    {
      if (Build.VERSION.SDK_INT >= 12) {}
      for (Object localObject = new ValueAnimatorCompatImplHoneycombMr1();; localObject = new ValueAnimatorCompatImplGingerbread()) {
        return new ValueAnimatorCompat((ValueAnimatorCompat.Impl)localObject);
      }
    }
  };
  
  static ValueAnimatorCompat createAnimator()
  {
    return DEFAULT_ANIMATOR_CREATOR.createAnimator();
  }
  
  static boolean objectEquals(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
  {
    switch (paramInt)
    {
    default: 
      return paramMode;
    case 3: 
      return PorterDuff.Mode.SRC_OVER;
    case 5: 
      return PorterDuff.Mode.SRC_IN;
    case 9: 
      return PorterDuff.Mode.SRC_ATOP;
    case 14: 
      return PorterDuff.Mode.MULTIPLY;
    }
    return PorterDuff.Mode.SCREEN;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\ViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */