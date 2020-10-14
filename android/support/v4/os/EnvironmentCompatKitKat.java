package android.support.v4.os;

import android.annotation.TargetApi;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import java.io.File;

@TargetApi(19)
@RequiresApi(19)
class EnvironmentCompatKitKat
{
  public static String getStorageState(File paramFile)
  {
    return Environment.getStorageState(paramFile);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\os\EnvironmentCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */