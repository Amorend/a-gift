package com.androlua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LuaServer
  implements LuaGcable
{
  private ServerSocket a;
  private OnReadLineListener b;
  
  public LuaServer() {}
  
  public LuaServer(LuaContext paramLuaContext)
  {
    paramLuaContext.regGc(this);
  }
  
  public void gc()
  {
    if (this.a == null) {
      return;
    }
    try
    {
      this.a.close();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void setOnReadLineListener(OnReadLineListener paramOnReadLineListener)
  {
    this.b = paramOnReadLineListener;
  }
  
  public boolean start(int paramInt)
  {
    if (this.a != null) {
      return false;
    }
    try
    {
      this.a = new ServerSocket(paramInt);
      new ServerThread(this.a).start();
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public boolean stop()
  {
    try
    {
      this.a.close();
      this.a = null;
      return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static abstract interface OnReadLineListener
  {
    public abstract void onReadLine(LuaServer paramLuaServer, LuaServer.SocketThread paramSocketThread, String paramString);
  }
  
  private class ServerThread
    extends Thread
  {
    private final ServerSocket b;
    
    public ServerThread(ServerSocket paramServerSocket)
    {
      this.b = paramServerSocket;
    }
    
    public void run()
    {
      try
      {
        for (;;)
        {
          Socket localSocket = LuaServer.a(LuaServer.this).accept();
          new LuaServer.SocketThread(LuaServer.this, localSocket).start();
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private class SocketThread
    extends Thread
  {
    private final Socket b;
    private BufferedWriter c;
    
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
        this.c.flush();
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
        this.c.newLine();
        this.c.flush();
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
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.b.getInputStream()));
        this.c = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream()));
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          if (LuaServer.b(LuaServer.this) != null) {
            LuaServer.b(LuaServer.this).onReadLine(LuaServer.this, this, str);
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
        this.c.write(paramString);
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


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */