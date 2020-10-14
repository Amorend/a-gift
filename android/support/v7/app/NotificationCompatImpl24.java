package android.support.v7.app;

import android.annotation.TargetApi;
import android.app.Notification.Builder;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.DecoratedMediaCustomViewStyle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(24)
@RequiresApi(24)
class NotificationCompatImpl24
{
  public static void addDecoratedCustomViewStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
  {
    paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
  }
  
  public static void addDecoratedMediaCustomViewStyle(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
  {
    paramNotificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedMediaCustomViewStyle());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\app\NotificationCompatImpl24.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */