package android.support.v7.app;

import android.annotation.TargetApi;
import android.app.Notification.BigTextStyle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(16)
@RequiresApi(16)
class NotificationCompatImplJellybean
{
  public static void addBigTextStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, CharSequence paramCharSequence)
  {
    new Notification.BigTextStyle(paramNotificationBuilderWithBuilderAccessor.getBuilder()).bigText(paramCharSequence);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\app\NotificationCompatImplJellybean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */