package android.support.v4.os;

import android.content.Context;

public class UserManagerCompat
{
  public static boolean isUserUnlocked(Context paramContext)
  {
    if (BuildCompat.isAtLeastN()) {
      return UserManagerCompatApi24.isUserUnlocked(paramContext);
    }
    return true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\os\UserManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */