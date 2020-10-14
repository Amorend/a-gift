package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.RequiresApi;

@TargetApi(16)
@RequiresApi(16)
class NavUtilsJB
{
  public static Intent getParentActivityIntent(Activity paramActivity)
  {
    return paramActivity.getParentActivityIntent();
  }
  
  public static String getParentActivityName(ActivityInfo paramActivityInfo)
  {
    return paramActivityInfo.parentActivityName;
  }
  
  public static void navigateUpTo(Activity paramActivity, Intent paramIntent)
  {
    paramActivity.navigateUpTo(paramIntent);
  }
  
  public static boolean shouldUpRecreateTask(Activity paramActivity, Intent paramIntent)
  {
    return paramActivity.shouldUpRecreateTask(paramIntent);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\NavUtilsJB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */