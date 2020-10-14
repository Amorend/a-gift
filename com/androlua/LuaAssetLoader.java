package com.androlua;

import android.content.Context;
import android.content.res.AssetManager;
import com.luajava.JavaFunction;
import com.luajava.LuaState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LuaAssetLoader
  extends JavaFunction
{
  private LuaState a;
  private Context c;
  
  public LuaAssetLoader(LuaContext paramLuaContext, LuaState paramLuaState)
  {
    super(paramLuaState);
    this.a = paramLuaState;
    this.c = paramLuaContext.getContext();
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (-1 == i) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  public int execute()
  {
    String str = this.a.toString(-1);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str.replace('.', '/'));
    ((StringBuilder)localObject).append(".lua");
    str = ((StringBuilder)localObject).toString();
    try
    {
      localObject = readAsset(str);
      if (this.a.LloadBuffer((byte[])localObject, str) != 0)
      {
        localObject = this.a;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("\n\t");
        localStringBuilder.append(this.a.toString(-1));
        ((LuaState)localObject).pushString(localStringBuilder.toString());
      }
      return 1;
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localObject = this.a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n\tno file '/assets/");
    localStringBuilder.append(str);
    localStringBuilder.append("'");
    ((LuaState)localObject).pushString(localStringBuilder.toString());
    return 1;
  }
  
  public byte[] readAsset(String paramString)
  {
    paramString = this.c.getAssets().open(paramString);
    byte[] arrayOfByte = a(paramString);
    paramString.close();
    return arrayOfByte;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaAssetLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */