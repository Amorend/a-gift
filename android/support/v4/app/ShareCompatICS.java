package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

@TargetApi(14)
@RequiresApi(14)
class ShareCompatICS
{
  private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";
  
  public static void configureMenuItem(MenuItem paramMenuItem, Activity paramActivity, Intent paramIntent)
  {
    Object localObject = paramMenuItem.getActionProvider();
    if (!(localObject instanceof ShareActionProvider)) {}
    for (localObject = new ShareActionProvider(paramActivity);; localObject = (ShareActionProvider)localObject)
    {
      ((ShareActionProvider)localObject).setShareHistoryFileName(".sharecompat_" + paramActivity.getClass().getName());
      ((ShareActionProvider)localObject).setShareIntent(paramIntent);
      paramMenuItem.setActionProvider((ActionProvider)localObject);
      return;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\ShareCompatICS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */