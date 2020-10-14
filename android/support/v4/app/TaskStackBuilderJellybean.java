package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@TargetApi(16)
@RequiresApi(16)
class TaskStackBuilderJellybean
{
  public static PendingIntent getActivitiesPendingIntent(Context paramContext, int paramInt1, Intent[] paramArrayOfIntent, int paramInt2, Bundle paramBundle)
  {
    return PendingIntent.getActivities(paramContext, paramInt1, paramArrayOfIntent, paramInt2, paramBundle);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\TaskStackBuilderJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */