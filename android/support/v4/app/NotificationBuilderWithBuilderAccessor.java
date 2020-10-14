package android.support.v4.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

@TargetApi(11)
@RequiresApi(11)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface NotificationBuilderWithBuilderAccessor
{
  public abstract Notification build();
  
  public abstract Notification.Builder getBuilder();
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\NotificationBuilderWithBuilderAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */