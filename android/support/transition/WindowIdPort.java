package android.support.transition;

import android.annotation.TargetApi;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@TargetApi(14)
@RequiresApi(14)
class WindowIdPort
{
  private final IBinder mToken;
  
  private WindowIdPort(IBinder paramIBinder)
  {
    this.mToken = paramIBinder;
  }
  
  static WindowIdPort getWindowId(@NonNull View paramView)
  {
    return new WindowIdPort(paramView.getWindowToken());
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WindowIdPort)) && (((WindowIdPort)paramObject).mToken.equals(this.mToken));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\WindowIdPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */