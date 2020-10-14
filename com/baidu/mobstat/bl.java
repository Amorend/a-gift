package com.baidu.mobstat;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONArray;
import org.json.JSONObject;

class bl
  implements Thread.UncaughtExceptionHandler
{
  private static final bl a = new bl();
  private Thread.UncaughtExceptionHandler b = null;
  private Context c = null;
  private bu d = new bu();
  
  public static bl a()
  {
    return a;
  }
  
  public void a(long paramLong, String paramString1, String paramString2, int paramInt)
  {
    ch.a().b(this.c, System.currentTimeMillis());
    if ((this.c != null) && (paramString1 != null))
    {
      if (paramString1.trim().equals("")) {
        return;
      }
      try
      {
        String str = CooperService.a().getAppVersionName(this.c);
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("t", paramLong);
        localJSONObject.put("c", paramString1);
        localJSONObject.put("y", paramString2);
        localJSONObject.put("v", str);
        localJSONObject.put("ct", paramInt);
        paramString2 = new JSONArray();
        paramString2.put(localJSONObject);
        localJSONObject = new JSONObject();
        this.d.a(this.c, localJSONObject);
        localJSONObject.put("ss", 0);
        paramString1 = new JSONObject();
        paramString1.put("he", localJSONObject);
        paramString1.put("pr", new JSONArray());
        paramString1.put("ev", new JSONArray());
        paramString1.put("ex", paramString2);
        paramString2 = new StringBuilder();
        paramString2.append("__send_data_");
        paramString2.append(System.currentTimeMillis());
        paramString2 = paramString2.toString();
        cs.a(this.c, paramString2, paramString1.toString(), false);
        cz.a("Dump exception successlly");
        return;
      }
      catch (Exception paramString1)
      {
        cz.b(paramString1);
      }
    }
  }
  
  public void a(Context paramContext)
  {
    if (this.b == null)
    {
      this.b = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(this);
    }
    if (this.c == null) {
      this.c = paramContext.getApplicationContext();
    }
    this.d.a(this.c);
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Object localObject3 = paramThrowable.toString();
    String str = "";
    Object localObject1 = str;
    if (localObject3 != null)
    {
      localObject1 = str;
      if (!((String)localObject3).equals("")) {
        try
        {
          localObject1 = ((String)localObject3).split(":");
          if (((String)localObject3).length() > 1) {
            localObject1 = localObject1[0];
          } else {
            localObject1 = localObject3;
          }
        }
        catch (Exception localException)
        {
          cz.c(localException);
          localObject2 = "";
        }
      }
    }
    if ((localObject2 != null) && (!((String)localObject2).equals(""))) {
      break label91;
    }
    Object localObject2 = localObject3;
    label91:
    localObject3 = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter((Writer)localObject3));
    localObject3 = localObject3.toString();
    cz.a((String)localObject3);
    a(System.currentTimeMillis(), (String)localObject3, (String)localObject2, 0);
    if (!this.b.equals(this)) {
      this.b.uncaughtException(paramThread, paramThrowable);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\bl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */