package com.androlua;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LuaClient
  implements LuaGcable
{
  private OnReadLineListener a;
  private Socket b;
  private BufferedReader c;
  private BufferedWriter d;
  
  public LuaClient() {}
  
  public LuaClient(LuaContext paramLuaContext)
  {
    paramLuaContext.regGc(this);
  }
  
  public boolean flush()
  {
    try
    {
      this.d.flush();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void gc()
  {
    stop();
  }
  
  public boolean newLine()
  {
    try
    {
      this.d.newLine();
      this.d.flush();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void setOnReadLineListener(OnReadLineListener paramOnReadLineListener)
  {
    this.a = paramOnReadLineListener;
  }
  
  public boolean start(String paramString, int paramInt)
  {
    if (this.b != null) {
      return false;
    }
    try
    {
      this.b = new Socket(paramString, paramInt);
      this.c = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
      this.d = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream()));
      new SocketThread(this.b).start();
      return true;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public boolean stop()
  {
    if (this.b == null) {
      return false;
    }
    try
    {
      this.b.close();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public boolean write(String paramString)
  {
    try
    {
      this.d.write(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static abstract interface OnReadLineListener
  {
    public abstract void onReadLine(LuaClient paramLuaClient, LuaClient.SocketThread paramSocketThread, String paramString);
  }
  
  private class SocketThread
    extends Thread
  {
    private final Socket b;
    
    public SocketThread(Socket paramSocket)
    {
      this.b = paramSocket;
    }
    
    public boolean close()
    {
      try
      {
        this.b.close();
        return true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return false;
    }
    
    public boolean flush()
    {
      try
      {
        LuaClient.c(LuaClient.this).flush();
        return true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return false;
    }
    
    public boolean newLine()
    {
      try
      {
        LuaClient.c(LuaClient.this).newLine();
        LuaClient.c(LuaClient.this).flush();
        return true;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return false;
    }
    
    public void run()
    {
      try
      {
        for (;;)
        {
          String str = LuaClient.a(LuaClient.this).readLine();
          if (str == null) {
            break;
          }
          if (LuaClient.b(LuaClient.this) != null) {
            LuaClient.b(LuaClient.this).onReadLine(LuaClient.this, this, str);
          }
        }
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    
    public boolean write(String paramString)
    {
      try
      {
        Log.i("lua", paramString);
        LuaClient.c(LuaClient.this).write(paramString);
        return true;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return false;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */