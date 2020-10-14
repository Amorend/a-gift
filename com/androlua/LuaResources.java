package com.androlua;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.luajava.LuaMetaTable;
import java.util.HashMap;

public class LuaResources
  extends Resources
  implements LuaMetaTable
{
  private static SparseArray<String> a = new SparseArray();
  private static SparseArray<Drawable> b = new SparseArray();
  private static SparseIntArray c = new SparseIntArray();
  private static int d = 2131034112;
  private static HashMap<String, Integer> e = new HashMap();
  
  public LuaResources(AssetManager paramAssetManager, DisplayMetrics paramDisplayMetrics, Configuration paramConfiguration)
  {
    super(paramAssetManager, paramDisplayMetrics, paramConfiguration);
  }
  
  public static Object get(String paramString)
  {
    return e.get(paramString);
  }
  
  public static int put(String paramString, Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException();
    }
    int i = d;
    d = i + 1;
    if ((paramObject instanceof Drawable))
    {
      setDrawable(i, (Drawable)paramObject);
    }
    else if ((paramObject instanceof String))
    {
      setText(i, (String)paramObject);
    }
    else
    {
      if (!(paramObject instanceof Number)) {
        break label90;
      }
      setColor(i, ((Number)paramObject).intValue());
    }
    e.put(paramString, Integer.valueOf(i));
    return i;
    label90:
    throw new IllegalArgumentException();
  }
  
  public static void setColor(int paramInt1, int paramInt2)
  {
    c.put(paramInt1, paramInt2);
  }
  
  public static void setDrawable(int paramInt, Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      throw new NullPointerException();
    }
    b.put(paramInt, paramDrawable);
  }
  
  public static void setText(int paramInt, String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    a.put(paramInt, paramString);
  }
  
  public Object __call(Object... paramVarArgs)
  {
    return null;
  }
  
  public Object __index(String paramString)
  {
    return get(paramString);
  }
  
  public void __newIndex(String paramString, Object paramObject)
  {
    put(paramString, paramObject);
  }
  
  public int getColor(int paramInt)
  {
    int i = c.get(paramInt);
    if (i != 0) {
      return i;
    }
    return super.getColor(paramInt);
  }
  
  public Drawable getDrawable(int paramInt)
  {
    Drawable localDrawable = (Drawable)b.get(paramInt);
    if (localDrawable != null) {
      return localDrawable;
    }
    return super.getDrawable(paramInt);
  }
  
  public Drawable getDrawable(int paramInt, Resources.Theme paramTheme)
  {
    Drawable localDrawable = (Drawable)b.get(paramInt);
    if (localDrawable != null) {
      return localDrawable;
    }
    return super.getDrawable(paramInt, paramTheme);
  }
  
  public CharSequence getText(int paramInt)
  {
    String str = (String)a.get(paramInt);
    if (str != null) {
      return str;
    }
    return super.getText(paramInt);
  }
  
  public CharSequence getText(int paramInt, CharSequence paramCharSequence)
  {
    String str = (String)a.get(paramInt);
    if (str != null) {
      return str;
    }
    return super.getText(paramInt, paramCharSequence);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */