package android.support.v7.app;

import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;

public abstract interface AppCompatCallback
{
  public abstract void onSupportActionModeFinished(ActionMode paramActionMode);
  
  public abstract void onSupportActionModeStarted(ActionMode paramActionMode);
  
  @Nullable
  public abstract ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\app\AppCompatCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */