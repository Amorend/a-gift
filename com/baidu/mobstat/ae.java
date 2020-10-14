package com.baidu.mobstat;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

class ae
  extends ContextWrapper
{
  public ae()
  {
    super(null);
  }
  
  public File getDatabasePath(String paramString)
  {
    if (!"mounted".equals(cs.a())) {
      return null;
    }
    String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append(File.separator);
    ((StringBuilder)localObject).append("backups/system");
    str = ((StringBuilder)localObject).toString();
    localObject = new File(str);
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append(File.separator);
    ((StringBuilder)localObject).append(paramString);
    paramString = new File(((StringBuilder)localObject).toString());
    if (!paramString.exists()) {
      try
      {
        paramString.createNewFile();
      }
      catch (IOException localIOException)
      {
        bd.b(localIOException);
      }
    }
    if (paramString.exists()) {
      return paramString;
    }
    return null;
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory)
  {
    paramString = getDatabasePath(paramString);
    if ((paramString != null) && (paramString.canWrite())) {
      return SQLiteDatabase.openOrCreateDatabase(paramString, null);
    }
    throw new RuntimeException("db path is null or path can not be write");
  }
  
  public SQLiteDatabase openOrCreateDatabase(String paramString, int paramInt, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler)
  {
    paramString = getDatabasePath(paramString);
    if ((paramString != null) && (paramString.canWrite())) {
      return SQLiteDatabase.openOrCreateDatabase(paramString, null);
    }
    throw new RuntimeException("db path is null or path can not be write");
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */