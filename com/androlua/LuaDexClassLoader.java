package com.androlua;

import dalvik.system.DexClassLoader;
import java.util.HashMap;

public class LuaDexClassLoader
  extends DexClassLoader
{
  private HashMap<String, Class<?>> a = new HashMap();
  private String b;
  
  public LuaDexClassLoader(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader)
  {
    super(paramString1, paramString2, paramString3, paramClassLoader);
    this.b = paramString1;
  }
  
  protected Class<?> findClass(String paramString)
  {
    Class localClass2 = (Class)this.a.get(paramString);
    Class localClass1 = localClass2;
    if (localClass2 == null)
    {
      localClass1 = super.findClass(paramString);
      this.a.put(paramString, localClass1);
    }
    return localClass1;
  }
  
  public String getDexPath()
  {
    return this.b;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaDexClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */