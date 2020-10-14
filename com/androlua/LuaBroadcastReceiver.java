package com.androlua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LuaBroadcastReceiver
  extends BroadcastReceiver
{
  private OnReceiveListener a;
  
  public LuaBroadcastReceiver(OnReceiveListener paramOnReceiveListener)
  {
    this.a = paramOnReceiveListener;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.a.onReceive(paramContext, paramIntent);
  }
  
  public static abstract interface OnReceiveListener
  {
    public abstract void onReceive(Context paramContext, Intent paramIntent);
  }
  
  public static abstract interface OnReceiveListerer
  {
    public abstract void onReceive(Context paramContext, Intent paramIntent);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */