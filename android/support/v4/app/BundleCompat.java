package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;

public final class BundleCompat
{
  public static IBinder getBinder(Bundle paramBundle, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return BundleCompatJellybeanMR2.getBinder(paramBundle, paramString);
    }
    return BundleCompatGingerbread.getBinder(paramBundle, paramString);
  }
  
  public static void putBinder(Bundle paramBundle, String paramString, IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      BundleCompatJellybeanMR2.putBinder(paramBundle, paramString, paramIBinder);
      return;
    }
    BundleCompatGingerbread.putBinder(paramBundle, paramString, paramIBinder);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */