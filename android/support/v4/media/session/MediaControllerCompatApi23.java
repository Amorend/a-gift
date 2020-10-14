package android.support.v4.media.session;

import android.annotation.TargetApi;
import android.media.session.MediaController.TransportControls;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi(23)
class MediaControllerCompatApi23
{
  public static class TransportControls
    extends MediaControllerCompatApi21.TransportControls
  {
    public static void playFromUri(Object paramObject, Uri paramUri, Bundle paramBundle)
    {
      ((MediaController.TransportControls)paramObject).playFromUri(paramUri, paramBundle);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\session\MediaControllerCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */