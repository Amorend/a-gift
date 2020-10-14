package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;

@Deprecated
public abstract class TransportPerformer
{
  static final int AUDIOFOCUS_GAIN = 1;
  static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
  static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
  static final int AUDIOFOCUS_LOSS = -1;
  static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
  static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;
  
  @Deprecated
  public void onAudioFocusChange(int paramInt)
  {
    int i = 0;
    switch (paramInt)
    {
    }
    for (paramInt = i;; paramInt = 127)
    {
      if (paramInt != 0)
      {
        long l = SystemClock.uptimeMillis();
        onMediaButtonDown(paramInt, new KeyEvent(l, l, 0, paramInt, 0));
        onMediaButtonUp(paramInt, new KeyEvent(l, l, 1, paramInt, 0));
      }
      return;
    }
  }
  
  @Deprecated
  public int onGetBufferPercentage()
  {
    return 100;
  }
  
  @Deprecated
  public abstract long onGetCurrentPosition();
  
  @Deprecated
  public abstract long onGetDuration();
  
  @Deprecated
  public int onGetTransportControlFlags()
  {
    return 60;
  }
  
  @Deprecated
  public abstract boolean onIsPlaying();
  
  @Deprecated
  public boolean onMediaButtonDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return true;
    case 126: 
      onStart();
      return true;
    case 127: 
      onPause();
      return true;
    case 86: 
      onStop();
      return true;
    }
    if (onIsPlaying())
    {
      onPause();
      return true;
    }
    onStart();
    return true;
  }
  
  @Deprecated
  public boolean onMediaButtonUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return true;
  }
  
  @Deprecated
  public abstract void onPause();
  
  @Deprecated
  public abstract void onSeekTo(long paramLong);
  
  @Deprecated
  public abstract void onStart();
  
  @Deprecated
  public abstract void onStop();
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\TransportPerformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */