package q.rorbin.badgeview;

import android.graphics.PointF;
import java.util.List;

public class MathUtil
{
  public static final double CIRCLE_RADIAN = 6.283185307179586D;
  
  public static void getInnertangentPoints(PointF paramPointF, float paramFloat, Double paramDouble, List<PointF> paramList)
  {
    float f1;
    if (paramDouble != null)
    {
      f1 = (float)Math.atan(((Double)paramDouble).doubleValue());
      float f2 = (float)(Math.cos(f1) * paramFloat);
      f1 = (float)(Math.sin(f1) * paramFloat);
      paramFloat = f2;
    }
    for (;;)
    {
      paramList.add(new PointF(paramPointF.x + paramFloat, paramPointF.y + f1));
      paramList.add(new PointF(paramPointF.x - paramFloat, paramPointF.y - f1));
      return;
      f1 = 0;
    }
  }
  
  public static float getPointDistance(PointF paramPointF1, PointF paramPointF2)
  {
    return (float)Math.sqrt(Math.pow(paramPointF1.x - paramPointF2.x, 2) + Math.pow(paramPointF1.y - paramPointF2.y, 2));
  }
  
  public static int getQuadrant(PointF paramPointF1, PointF paramPointF2)
  {
    if (paramPointF1.x > paramPointF2.x)
    {
      if (paramPointF1.y > paramPointF2.y) {
        return 4;
      }
      if (paramPointF1.y < paramPointF2.y) {
        return 1;
      }
    }
    do
    {
      do
      {
        return -1;
      } while (paramPointF1.x >= paramPointF2.x);
      if (paramPointF1.y > paramPointF2.y) {
        return 3;
      }
    } while (paramPointF1.y >= paramPointF2.y);
    return 2;
  }
  
  public static double getTanRadian(double paramDouble, int paramInt)
  {
    double d = paramDouble;
    if (paramDouble < 0) {
      d = paramDouble + 1.5707963267948966D;
    }
    return d + 1.5707963267948966D * (paramInt - 1);
  }
  
  public static double radianToAngle(double paramDouble)
  {
    return 'Ũ' * (paramDouble / 6.283185307179586D);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\q\rorbin\badgeview\MathUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */