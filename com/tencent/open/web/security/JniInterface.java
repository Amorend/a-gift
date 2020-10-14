package com.tencent.open.web.security;

import android.content.Context;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import java.io.File;

public class JniInterface
{
  public static boolean isJniOk = false;
  
  public static native boolean BackSpaceChar(boolean paramBoolean, int paramInt);
  
  public static native boolean clearAllPWD();
  
  public static native String getPWDKeyToMD5(String paramString);
  
  public static native boolean insetTextToArray(int paramInt1, String paramString, int paramInt2);
  
  public static void loadSo()
  {
    if (isJniOk) {
      return;
    }
    try
    {
      Context localContext = e.a();
      if (localContext != null) {
        if (new File(localContext.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists())
        {
          System.load(localContext.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
          isJniOk = true;
          f.c("openSDK_LOG.JniInterface", "-->load lib success:" + AuthAgent.SECURE_LIB_NAME);
        }
        else
        {
          f.c("openSDK_LOG.JniInterface", "-->fail, because so is not exists:" + AuthAgent.SECURE_LIB_NAME);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      f.b("openSDK_LOG.JniInterface", "-->load lib error:" + AuthAgent.SECURE_LIB_NAME, localThrowable);
    }
    f.c("openSDK_LOG.JniInterface", "-->load lib fail, because context is null:" + AuthAgent.SECURE_LIB_NAME);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\web\security\JniInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */