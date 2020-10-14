package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import android.view.View;

@TargetApi(21)
@RequiresApi(21)
class ActivityOptionsCompat21
{
  private final ActivityOptions mActivityOptions;
  
  private ActivityOptionsCompat21(ActivityOptions paramActivityOptions)
  {
    this.mActivityOptions = paramActivityOptions;
  }
  
  public static ActivityOptionsCompat21 makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2)
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeCustomAnimation(paramContext, paramInt1, paramInt2));
  }
  
  public static ActivityOptionsCompat21 makeScaleUpAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity paramActivity, View paramView, String paramString)
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
  }
  
  public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity paramActivity, View[] paramArrayOfView, String[] paramArrayOfString)
  {
    Pair[] arrayOfPair = null;
    if (paramArrayOfView != null)
    {
      arrayOfPair = new Pair[paramArrayOfView.length];
      int i = 0;
      while (i < arrayOfPair.length)
      {
        arrayOfPair[i] = Pair.create(paramArrayOfView[i], paramArrayOfString[i]);
        i += 1;
      }
    }
    return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(paramActivity, arrayOfPair));
  }
  
  public static ActivityOptionsCompat21 makeTaskLaunchBehind()
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeTaskLaunchBehind());
  }
  
  public static ActivityOptionsCompat21 makeThumbnailScaleUpAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
  }
  
  public Bundle toBundle()
  {
    return this.mActivityOptions.toBundle();
  }
  
  public void update(ActivityOptionsCompat21 paramActivityOptionsCompat21)
  {
    this.mActivityOptions.update(paramActivityOptionsCompat21.mActivityOptions);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\ActivityOptionsCompat21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */