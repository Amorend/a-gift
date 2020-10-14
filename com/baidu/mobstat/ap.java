package com.baidu.mobstat;

import android.content.Context;
import android.content.Intent;

 enum ap
{
  ap(int paramInt1)
  {
    super(paramString, paramInt, paramInt1, null);
  }
  
  public void a(Context paramContext)
  {
    if (!au.a(paramContext).b(paramContext)) {
      return;
    }
    try
    {
      Intent localIntent = new Intent(paramContext, Class.forName("com.baidu.bottom.service.BottomService"));
      localIntent.putExtra("SDK_PRODUCT_LY", "MS");
      paramContext.startService(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
      cz.b(paramContext);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */