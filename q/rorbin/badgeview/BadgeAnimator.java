package q.rorbin.badgeview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Random;

public class BadgeAnimator
  extends ValueAnimator
{
  private BitmapFragment[][] mFragments;
  private WeakReference<QBadgeView> mWeakBadge;
  
  public BadgeAnimator(Bitmap paramBitmap, PointF paramPointF, QBadgeView paramQBadgeView)
  {
    this.mWeakBadge = new WeakReference(paramQBadgeView);
    setFloatValues(new float[] { 0.0F, 1.0F });
    setDuration('Ǵ');
    this.mFragments = getFragments(paramBitmap, paramPointF);
    addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      @Override
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        paramAnonymousValueAnimator = (QBadgeView)BadgeAnimator.access$L1000001(BadgeAnimator.this).get();
        if ((paramAnonymousValueAnimator == null) || (!paramAnonymousValueAnimator.isShown()))
        {
          BadgeAnimator.this.cancel();
          return;
        }
        paramAnonymousValueAnimator.invalidate();
      }
    });
    addListener(new AnimatorListenerAdapter()
    {
      @Override
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        paramAnonymousAnimator = (QBadgeView)BadgeAnimator.access$L1000001(BadgeAnimator.this).get();
        if (paramAnonymousAnimator != null) {
          paramAnonymousAnimator.reset();
        }
      }
    });
  }
  
  private BitmapFragment[][] getFragments(Bitmap paramBitmap, PointF paramPointF)
  {
    int k = paramBitmap.getWidth();
    int m = paramBitmap.getHeight();
    float f1 = Math.min(k, m) / 6.0F;
    float f2 = paramPointF.x;
    float f3 = paramBitmap.getWidth() / 2.0F;
    float f4 = paramPointF.y;
    float f5 = paramBitmap.getHeight() / 2.0F;
    paramPointF = (BitmapFragment[][])Array.newInstance(BitmapFragment.class, new int[] { (int)(m / f1), (int)(k / f1) });
    int i = 0;
    if (i >= paramPointF.length)
    {
      paramBitmap.recycle();
      return paramPointF;
    }
    int j = 0;
    for (;;)
    {
      if (j >= paramPointF[i].length)
      {
        i += 1;
        break;
      }
      BitmapFragment localBitmapFragment = new BitmapFragment();
      localBitmapFragment.color = paramBitmap.getPixel((int)(j * f1), (int)(i * f1));
      localBitmapFragment.x = (f2 - f3 + j * f1);
      localBitmapFragment.y = (f4 - f5 + i * f1);
      localBitmapFragment.size = f1;
      localBitmapFragment.maxSize = Math.max(k, m);
      paramPointF[i][j] = localBitmapFragment;
      j += 1;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i = 0;
    if (i >= this.mFragments.length) {
      return;
    }
    int j = 0;
    for (;;)
    {
      if (j >= this.mFragments[i].length)
      {
        i += 1;
        break;
      }
      this.mFragments[i][j].updata(Float.parseFloat(getAnimatedValue().toString()), paramCanvas);
      j += 1;
    }
  }
  
  private class BitmapFragment
  {
    int color;
    int maxSize;
    Paint paint = new Paint();
    Random random;
    float size;
    float x;
    float y;
    
    public BitmapFragment()
    {
      this.paint.setAntiAlias(true);
      this.paint.setStyle(Paint.Style.FILL);
      this.random = new Random();
    }
    
    public void updata(float paramFloat, Canvas paramCanvas)
    {
      this.paint.setColor(this.color);
      this.x += 0.1F * this.random.nextInt(this.maxSize) * (this.random.nextFloat() - 0.5F);
      this.y += 0.1F * this.random.nextInt(this.maxSize) * (this.random.nextFloat() - 0.5F);
      paramCanvas.drawCircle(this.x, this.y, this.size - paramFloat * this.size, this.paint);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\q\rorbin\badgeview\BadgeAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */