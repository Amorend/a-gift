package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class af
  extends SQLiteOpenHelper
{
  private String a;
  private SQLiteDatabase b;
  
  public af(Context paramContext, String paramString)
  {
    super(paramContext, ".confd", null, 1);
    this.a = paramString;
  }
  
  public int a(String paramString, String[] paramArrayOfString)
  {
    return this.b.delete(this.a, paramString, paramArrayOfString);
  }
  
  public long a(String paramString, ContentValues paramContentValues)
  {
    return this.b.insert(this.a, paramString, paramContentValues);
  }
  
  public Cursor a(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return this.b.query(this.a, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramString3, paramString4, paramString5);
  }
  
  public void a(String paramString)
  {
    getWritableDatabase().execSQL(paramString);
  }
  
  public boolean a()
  {
    for (;;)
    {
      boolean bool2;
      try
      {
        SQLiteDatabase localSQLiteDatabase = this.b;
        bool2 = false;
        if (localSQLiteDatabase == null) {
          break label104;
        }
        bool1 = this.b.isOpen();
        if (!bool1) {
          break label104;
        }
        i = 0;
        if (i == 0) {}
      }
      finally {}
      try
      {
        this.b = getWritableDatabase();
      }
      catch (NullPointerException localNullPointerException)
      {
        continue;
      }
      throw new NullPointerException("db path is null");
      boolean bool1 = bool2;
      if (this.b != null)
      {
        bool1 = this.b.isOpen();
        if (!bool1) {
          bool1 = bool2;
        } else {
          bool1 = true;
        }
      }
      return bool1;
      label104:
      int i = 1;
    }
  }
  
  public final int b()
  {
    Object localObject3 = null;
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.b;
      Object localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("SELECT COUNT(*) FROM ");
      ((StringBuilder)localObject4).append(this.a);
      localObject4 = localSQLiteDatabase.rawQuery(((StringBuilder)localObject4).toString(), null);
      if (localObject4 != null) {
        try
        {
          if (((Cursor)localObject4).moveToNext())
          {
            int i = ((Cursor)localObject4).getInt(0);
            return i;
          }
        }
        finally
        {
          localObject3 = localObject4;
          break label106;
        }
      }
      return 0;
    }
    finally
    {
      label106:
      if (localObject3 != null) {
        ((Cursor)localObject3).close();
      }
    }
  }
  
  public void close()
  {
    try
    {
      super.close();
      if (this.b != null)
      {
        this.b.close();
        this.b = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public SQLiteDatabase getReadableDatabase()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = super.getReadableDatabase();
      return localSQLiteDatabase;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public SQLiteDatabase getWritableDatabase()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = super.getWritableDatabase();
      return localSQLiteDatabase;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    this.b = paramSQLiteDatabase;
  }
  
  public void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    super.onOpen(paramSQLiteDatabase);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */