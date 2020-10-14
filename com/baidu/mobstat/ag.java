package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;

class ag
  extends x
{
  public ag()
  {
    super("ap_list3", "Create table if not exists ap_list3(_id Integer primary key AUTOINCREMENT,time VARCHAR(50),content TEXT);");
  }
  
  private ArrayList<w> a(Cursor paramCursor)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramCursor == null) {
      return localArrayList;
    }
    if (paramCursor.getCount() == 0) {
      return localArrayList;
    }
    int i = paramCursor.getColumnIndex("_id");
    int j = paramCursor.getColumnIndex("time");
    int k = paramCursor.getColumnIndex("content");
    while (paramCursor.moveToNext()) {
      localArrayList.add(new w(paramCursor.getLong(i), paramCursor.getString(j), paramCursor.getString(k)));
    }
    return localArrayList;
  }
  
  public long a(String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("time", paramString1);
    localContentValues.put("content", paramString2);
    return a(localContentValues);
  }
  
  public ArrayList<w> a(int paramInt1, int paramInt2)
  {
    Cursor localCursor = a("time", paramInt1, paramInt2);
    ArrayList localArrayList = a(localCursor);
    if (localCursor != null) {
      localCursor.close();
    }
    return localArrayList;
  }
  
  public boolean b(long paramLong)
  {
    return a(paramLong);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */