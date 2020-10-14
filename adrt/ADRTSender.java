package adrt;

import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

public class ADRTSender
{
  private static Context context;
  private static String debuggerPackageName;
  
  public static void onContext(Context paramContext, String paramString)
  {
    context = paramContext;
    debuggerPackageName = paramString;
  }
  
  public static void sendBreakpointHit(String paramString, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, ArrayList<String> paramArrayList3, ArrayList<String> paramArrayList4, ArrayList<String> paramArrayList5, ArrayList<String> paramArrayList6)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(debuggerPackageName);
    localIntent.setAction("com.adrt.BREAKPOINT_HIT");
    localIntent.putExtra("package", paramString);
    localIntent.putExtra("variables", paramArrayList1);
    localIntent.putExtra("variableValues", paramArrayList2);
    localIntent.putExtra("variableKinds", paramArrayList3);
    localIntent.putExtra("stackMethods", paramArrayList4);
    localIntent.putExtra("stackLocations", paramArrayList5);
    localIntent.putExtra("stackLocationKinds", paramArrayList6);
    context.sendBroadcast(localIntent);
  }
  
  public static void sendConnect(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(debuggerPackageName);
    localIntent.setAction("com.adrt.CONNECT");
    localIntent.putExtra("package", paramString);
    context.sendBroadcast(localIntent);
  }
  
  public static void sendFields(String paramString1, String paramString2, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2, ArrayList<String> paramArrayList3)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(debuggerPackageName);
    localIntent.setAction("com.adrt.FIELDS");
    localIntent.putExtra("package", paramString1);
    localIntent.putExtra("path", paramString2);
    localIntent.putExtra("fields", paramArrayList1);
    localIntent.putExtra("fieldValues", paramArrayList2);
    localIntent.putExtra("fieldKinds", paramArrayList3);
    context.sendBroadcast(localIntent);
  }
  
  public static void sendLogcatLines(String[] paramArrayOfString)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(debuggerPackageName);
    localIntent.setAction("com.adrt.LOGCAT_ENTRIES");
    localIntent.putExtra("lines", paramArrayOfString);
    context.sendBroadcast(localIntent);
  }
  
  public static void sendStop(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(debuggerPackageName);
    localIntent.setAction("com.adrt.STOP");
    localIntent.putExtra("package", paramString);
    context.sendBroadcast(localIntent);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\adrt\ADRTSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */