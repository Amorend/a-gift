package com.androlua;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

public class LuaContentObserver
  extends ContentObserver
  implements LuaGcable
{
  private OnChangeListener a;
  
  private LuaContentObserver(Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public LuaContentObserver(LuaContext paramLuaContext, Uri paramUri)
  {
    this(new Handler(LuaApplication.getInstance().getMainLooper()));
    paramLuaContext.regGc(this);
    LuaApplication.getInstance().getContentResolver().registerContentObserver(paramUri, true, this);
  }
  
  public LuaContentObserver(LuaContext paramLuaContext, String paramString)
  {
    this(new Handler(LuaApplication.getInstance().getMainLooper()));
    paramString = Uri.parse(paramString);
    paramLuaContext.regGc(this);
    LuaApplication.getInstance().getContentResolver().registerContentObserver(paramString, true, this);
  }
  
  public void gc()
  {
    LuaApplication.getInstance().getContentResolver().unregisterContentObserver(this);
  }
  
  public void onChange(boolean paramBoolean, Uri paramUri)
  {
    super.onChange(paramBoolean, paramUri);
    if (this.a != null)
    {
      Cursor localCursor = LuaApplication.getInstance().getContentResolver().query(paramUri, null, null, null, null);
      if (localCursor != null) {
        localCursor.moveToFirst();
      }
      this.a.onChange(paramBoolean, paramUri, localCursor);
      if (localCursor != null) {
        localCursor.close();
      }
    }
  }
  
  public void setOnChangeListener(OnChangeListener paramOnChangeListener)
  {
    this.a = paramOnChangeListener;
  }
  
  public static abstract interface OnChangeListener
  {
    public abstract void onChange(boolean paramBoolean, Uri paramUri, Cursor paramCursor);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaContentObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */