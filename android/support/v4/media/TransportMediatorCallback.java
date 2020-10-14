package android.support.v4.media;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;

@TargetApi(18)
@RequiresApi(18)
abstract interface TransportMediatorCallback
{
  public abstract long getPlaybackPosition();
  
  public abstract void handleAudioFocusChange(int paramInt);
  
  public abstract void handleKey(KeyEvent paramKeyEvent);
  
  public abstract void playbackPositionUpdate(long paramLong);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\TransportMediatorCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */