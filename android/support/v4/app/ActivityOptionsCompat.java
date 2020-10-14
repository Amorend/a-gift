package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;
import android.view.View;

public class ActivityOptionsCompat
{
  public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
  public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
  
  public static ActivityOptionsCompat makeBasic()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeBasic());
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeBasic());
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeClipRevealAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeClipRevealAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeClipRevealAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeCustomAnimation(Context paramContext, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeCustomAnimation(paramContext, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeCustomAnimation(paramContext, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeCustomAnimation(paramContext, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(paramContext, paramInt1, paramInt2));
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeScaleUpAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4));
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity paramActivity, View paramView, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity paramActivity, Pair<View, String>... paramVarArgs)
  {
    Object localObject = null;
    String[] arrayOfString;
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (paramVarArgs == null) {
        break label136;
      }
      localObject = new View[paramVarArgs.length];
      arrayOfString = new String[paramVarArgs.length];
      int i = 0;
      while (i < paramVarArgs.length)
      {
        localObject[i] = ((View)paramVarArgs[i].first);
        arrayOfString[i] = ((String)paramVarArgs[i].second);
        i += 1;
      }
      paramVarArgs = arrayOfString;
    }
    for (;;)
    {
      if (Build.VERSION.SDK_INT >= 24) {
        return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(paramActivity, (View[])localObject, paramVarArgs));
      }
      if (Build.VERSION.SDK_INT >= 23) {
        return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(paramActivity, (View[])localObject, paramVarArgs));
      }
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(paramActivity, (View[])localObject, paramVarArgs));
      return new ActivityOptionsCompat();
      label136:
      arrayOfString = null;
      paramVarArgs = (Pair<View, String>[])localObject;
      localObject = arrayOfString;
    }
  }
  
  public static ActivityOptionsCompat makeTaskLaunchBehind()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeTaskLaunchBehind());
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeTaskLaunchBehind());
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeTaskLaunchBehind());
    }
    return new ActivityOptionsCompat();
  }
  
  public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View paramView, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(paramView, paramBitmap, paramInt1, paramInt2));
    }
    return new ActivityOptionsCompat();
  }
  
  @Nullable
  public Rect getLaunchBounds()
  {
    return null;
  }
  
  public void requestUsageTimeReport(PendingIntent paramPendingIntent) {}
  
  public ActivityOptionsCompat setLaunchBounds(@Nullable Rect paramRect)
  {
    return null;
  }
  
  public Bundle toBundle()
  {
    return null;
  }
  
  public void update(ActivityOptionsCompat paramActivityOptionsCompat) {}
  
  @TargetApi(21)
  @RequiresApi(21)
  private static class ActivityOptionsImpl21
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompat21 mImpl;
    
    ActivityOptionsImpl21(ActivityOptionsCompat21 paramActivityOptionsCompat21)
    {
      this.mImpl = paramActivityOptionsCompat21;
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImpl21))
      {
        paramActivityOptionsCompat = (ActivityOptionsImpl21)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
  
  @TargetApi(23)
  @RequiresApi(23)
  private static class ActivityOptionsImpl23
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompat23 mImpl;
    
    ActivityOptionsImpl23(ActivityOptionsCompat23 paramActivityOptionsCompat23)
    {
      this.mImpl = paramActivityOptionsCompat23;
    }
    
    public void requestUsageTimeReport(PendingIntent paramPendingIntent)
    {
      this.mImpl.requestUsageTimeReport(paramPendingIntent);
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImpl23))
      {
        paramActivityOptionsCompat = (ActivityOptionsImpl23)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
  
  @TargetApi(24)
  @RequiresApi(24)
  private static class ActivityOptionsImpl24
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompat24 mImpl;
    
    ActivityOptionsImpl24(ActivityOptionsCompat24 paramActivityOptionsCompat24)
    {
      this.mImpl = paramActivityOptionsCompat24;
    }
    
    public Rect getLaunchBounds()
    {
      return this.mImpl.getLaunchBounds();
    }
    
    public void requestUsageTimeReport(PendingIntent paramPendingIntent)
    {
      this.mImpl.requestUsageTimeReport(paramPendingIntent);
    }
    
    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect paramRect)
    {
      return new ActivityOptionsImpl24(this.mImpl.setLaunchBounds(paramRect));
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImpl24))
      {
        paramActivityOptionsCompat = (ActivityOptionsImpl24)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
  
  @TargetApi(16)
  @RequiresApi(16)
  private static class ActivityOptionsImplJB
    extends ActivityOptionsCompat
  {
    private final ActivityOptionsCompatJB mImpl;
    
    ActivityOptionsImplJB(ActivityOptionsCompatJB paramActivityOptionsCompatJB)
    {
      this.mImpl = paramActivityOptionsCompatJB;
    }
    
    public Bundle toBundle()
    {
      return this.mImpl.toBundle();
    }
    
    public void update(ActivityOptionsCompat paramActivityOptionsCompat)
    {
      if ((paramActivityOptionsCompat instanceof ActivityOptionsImplJB))
      {
        paramActivityOptionsCompat = (ActivityOptionsImplJB)paramActivityOptionsCompat;
        this.mImpl.update(paramActivityOptionsCompat.mImpl);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\ActivityOptionsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */