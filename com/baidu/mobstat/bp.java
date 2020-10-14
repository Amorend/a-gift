package com.baidu.mobstat;

import java.util.HashMap;

class bp
  implements Runnable
{
  bp(bm parambm, long paramLong, String paramString1, String paramString2) {}
  
  public void run()
  {
    bv.a().d();
    Object localObject = new bs();
    ((bs)localObject).c = this.a;
    ((bs)localObject).a = this.b;
    ((bs)localObject).b = this.c;
    String str = this.d.a(this.b, this.c);
    if (this.d.a.get(str) != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("EventStat: event_id[");
      localStringBuilder.append(this.b);
      localStringBuilder.append("] with label[");
      localStringBuilder.append(this.c);
      localStringBuilder.append("] is duplicated, older is removed");
      cz.b(localStringBuilder.toString());
    }
    this.d.a.put(str, localObject);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("put a keyword[");
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append("] into durationlist");
    cz.a(((StringBuilder)localObject).toString());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\bp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */