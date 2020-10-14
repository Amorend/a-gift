package android.support.v4.media.session;

import android.annotation.TargetApi;
import android.media.session.MediaSession;
import android.support.annotation.RequiresApi;

@TargetApi(22)
@RequiresApi(22)
class MediaSessionCompatApi22
{
  public static void setRatingType(Object paramObject, int paramInt)
  {
    ((MediaSession)paramObject).setRatingType(paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\session\MediaSessionCompatApi22.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */