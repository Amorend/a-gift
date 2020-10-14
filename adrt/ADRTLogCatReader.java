package adrt;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADRTLogCatReader
  implements Runnable
{
  private static Context context;
  
  public static void onContext(Context paramContext, String paramString)
  {
    if (context != null) {
      return;
    }
    context = paramContext.getApplicationContext();
    if ((paramContext.getApplicationInfo().flags & 0x2) != 0) {}
    for (int i = 1; i == 0; i = 0) {
      return;
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 128);
      ADRTSender.onContext(context, paramString);
      new Thread(new ADRTLogCatReader(), "LogCat").start();
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
  }
  
  public void run()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -v threadtime").getInputStream()), 20);
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null) {
          break;
        }
        ADRTSender.sendLogcatLines(new String[] { str });
      }
      return;
    }
    catch (IOException localIOException) {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\adrt\ADRTLogCatReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */