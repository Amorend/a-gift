package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

abstract class BaseFragmentActivityJB
  extends BaseFragmentActivityHoneycomb
{
  boolean mStartedActivityFromFragment;
  
  @RequiresApi(16)
  public void startActivityForResult(Intent paramIntent, int paramInt, @Nullable Bundle paramBundle)
  {
    if ((!this.mStartedActivityFromFragment) && (paramInt != -1)) {
      checkForValidRequestCode(paramInt);
    }
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
  
  @RequiresApi(16)
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle)
    throws IntentSender.SendIntentException
  {
    if ((!this.mStartedIntentSenderFromFragment) && (paramInt1 != -1)) {
      checkForValidRequestCode(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\BaseFragmentActivityJB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */