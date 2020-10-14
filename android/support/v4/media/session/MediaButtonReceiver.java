package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

public class MediaButtonReceiver
  extends BroadcastReceiver
{
  private static final String TAG = "MediaButtonReceiver";
  
  public static PendingIntent buildMediaButtonPendingIntent(Context paramContext, long paramLong)
  {
    ComponentName localComponentName = getMediaButtonReceiverComponent(paramContext);
    if (localComponentName == null)
    {
      Log.w("MediaButtonReceiver", "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
      return null;
    }
    return buildMediaButtonPendingIntent(paramContext, localComponentName, paramLong);
  }
  
  public static PendingIntent buildMediaButtonPendingIntent(Context paramContext, ComponentName paramComponentName, long paramLong)
  {
    if (paramComponentName == null)
    {
      Log.w("MediaButtonReceiver", "The component name of media button receiver should be provided.");
      return null;
    }
    int i = PlaybackStateCompat.toKeyCode(paramLong);
    if (i == 0)
    {
      Log.w("MediaButtonReceiver", "Cannot build a media button pending intent with the given action: " + paramLong);
      return null;
    }
    Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
    localIntent.setComponent(paramComponentName);
    localIntent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i));
    return PendingIntent.getBroadcast(paramContext, i, localIntent, 0);
  }
  
  static ComponentName getMediaButtonReceiverComponent(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext = paramContext.getPackageManager().queryBroadcastReceivers(localIntent, 0);
    if (paramContext.size() == 1)
    {
      paramContext = (ResolveInfo)paramContext.get(0);
      return new ComponentName(paramContext.activityInfo.packageName, paramContext.activityInfo.name);
    }
    if (paramContext.size() > 1) {
      Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
    }
    return null;
  }
  
  public static KeyEvent handleIntent(MediaSessionCompat paramMediaSessionCompat, Intent paramIntent)
  {
    if ((paramMediaSessionCompat == null) || (paramIntent == null) || (!"android.intent.action.MEDIA_BUTTON".equals(paramIntent.getAction())) || (!paramIntent.hasExtra("android.intent.extra.KEY_EVENT"))) {
      return null;
    }
    paramIntent = (KeyEvent)paramIntent.getParcelableExtra("android.intent.extra.KEY_EVENT");
    paramMediaSessionCompat.getController().dispatchMediaButtonEvent(paramIntent);
    return paramIntent;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
    localIntent.setPackage(paramContext.getPackageName());
    PackageManager localPackageManager = paramContext.getPackageManager();
    List localList = localPackageManager.queryIntentServices(localIntent, 0);
    Object localObject = localList;
    if (localList.isEmpty())
    {
      localIntent.setAction("android.media.browse.MediaBrowserService");
      localObject = localPackageManager.queryIntentServices(localIntent, 0);
    }
    if (((List)localObject).isEmpty()) {
      throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or a media browser service implementation");
    }
    if (((List)localObject).size() != 1) {
      throw new IllegalStateException("Expected 1 Service that handles " + localIntent.getAction() + ", found " + ((List)localObject).size());
    }
    localObject = (ResolveInfo)((List)localObject).get(0);
    paramIntent.setComponent(new ComponentName(((ResolveInfo)localObject).serviceInfo.packageName, ((ResolveInfo)localObject).serviceInfo.name));
    paramContext.startService(paramIntent);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\session\MediaButtonReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */