package com.baidu.mobstat;

import android.a.a.a.a;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import java.lang.ref.WeakReference;

class cn
  implements Runnable
{
  private long b;
  private WeakReference<Context> c;
  private WeakReference<a> d;
  private WeakReference<Object> e;
  private long f;
  private WeakReference<Context> g;
  private WeakReference<a> h;
  private WeakReference<Object> i;
  private int j;
  private String k;
  private String l;
  private boolean m;
  private ExtraInfo n;
  private cl o;
  
  public cn(ch paramch, long paramLong1, Context paramContext1, a parama1, long paramLong2, Context paramContext2, a parama2, int paramInt, String paramString1, Object paramObject1, Object paramObject2, String paramString2, boolean paramBoolean, ExtraInfo paramExtraInfo, cl paramcl)
  {
    this.b = paramLong1;
    this.f = paramLong2;
    this.c = new WeakReference(paramContext1);
    this.g = new WeakReference(paramContext2);
    this.d = new WeakReference(parama1);
    this.h = new WeakReference(parama2);
    this.i = new WeakReference(paramObject1);
    this.e = new WeakReference(paramObject2);
    this.j = paramInt;
    this.k = paramString1;
    this.l = paramString2;
    this.m = paramBoolean;
    this.n = paramExtraInfo;
    this.o = paramcl;
  }
  
  public void run()
  {
    Object localObject3;
    long l1;
    long l2;
    if (this.j == 1)
    {
      localObject2 = (Context)this.c.get();
      localObject1 = (Context)this.g.get();
      if ((localObject2 != null) && (localObject1 != null))
      {
        if (localObject2 != localObject1)
        {
          if (this.k != null) {}
          for (localObject1 = "onPageStart() or onPageEnd() install error.";; localObject1 = "onPause() or onResume() install error.")
          {
            cz.b((String)localObject1);
            return;
          }
        }
        localObject3 = "";
        l1 = this.b;
        l2 = this.f;
        localObject4 = new StringBuilder();
        if (this.k != null)
        {
          ((StringBuilder)localObject4).append(this.k);
          if (this.o == null) {
            break label263;
          }
          l1 = this.o.d - this.o.c;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("page time = ");
          ((StringBuilder)localObject1).append(this.o.a);
          ((StringBuilder)localObject1).append("; time = ");
          ((StringBuilder)localObject1).append(l1);
          cz.c(((StringBuilder)localObject1).toString());
          if (l1 < 20L) {
            localObject1 = "page time little than 20 mills.";
          }
        }
      }
    }
    for (;;)
    {
      cz.c((String)localObject1);
      return;
      if (!(localObject2 instanceof Activity))
      {
        localObject1 = "onPause, pause is not a Activity";
      }
      else
      {
        ((StringBuilder)localObject4).append(((Activity)localObject2).getComponentName().getShortClassName());
        if (((StringBuilder)localObject4).charAt(0) == '.') {
          ((StringBuilder)localObject4).deleteCharAt(0);
        }
        label263:
        l1 -= l2;
        localObject1 = localObject3;
        if ((localObject2 instanceof Activity))
        {
          localObject5 = ((Activity)localObject2).getTitle();
          localObject1 = localObject3;
          if (localObject5 != null) {
            localObject1 = ((CharSequence)localObject5).toString();
          }
        }
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("new page view, page name = ");
        ((StringBuilder)localObject3).append(((StringBuilder)localObject4).toString());
        ((StringBuilder)localObject3).append(", stay time = ");
        ((StringBuilder)localObject3).append(l1);
        ((StringBuilder)localObject3).append("(ms)");
        cz.a(((StringBuilder)localObject3).toString());
        localObject3 = ((StringBuilder)localObject4).toString();
        if (this.k == null) {
          this.l = ((String)localObject3);
        }
        localObject1 = new cg((String)localObject3, (String)localObject1, this.l, l1, this.f, this.m, this.n);
        ch.a(this.a).a((cg)localObject1);
        if (this.k != null)
        {
          if (this.o == null) {
            return;
          }
          localObject1 = ch.a(this.a);
          l1 = this.o.d;
        }
        else
        {
          localObject1 = ch.a(this.a);
          l1 = this.b;
        }
        ((cf)localObject1).d(l1);
        localObject3 = this.a;
        localObject1 = localObject2;
        localObject2 = localObject3;
        break label755;
        label499:
        do
        {
          localObject1 = "onPause, WeakReference is already been released";
          break;
          if (this.j != 2) {
            break label763;
          }
          localObject3 = (a)this.d.get();
          localObject1 = (a)this.h.get();
        } while ((localObject3 == null) || (localObject1 == null));
        if (localObject3 != localObject1) {}
        label755:
        label763:
        do
        {
          localObject1 = "onPause() or onResume() install error.";
          break;
          localObject1 = "";
          localObject2 = ((a)localObject3).getActivity();
          if (localObject2 != null) {
            localObject1 = ((Activity)localObject2).getTitle().toString();
          }
          l1 = this.b - this.f;
          localObject2 = localObject3.getClass().getName();
          localObject4 = ((String)localObject2).substring(((String)localObject2).lastIndexOf(".") + 1);
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append("Fragment new page view, page name = ");
          ((StringBuilder)localObject5).append(((String)localObject2).toString());
          ((StringBuilder)localObject5).append(", stay time = ");
          ((StringBuilder)localObject5).append(l1);
          ((StringBuilder)localObject5).append("(ms)");
          cz.a(((StringBuilder)localObject5).toString());
          localObject1 = new cg((String)localObject4, (String)localObject1, (String)localObject4, l1, this.f, this.m, this.n);
          ch.a(this.a).a((cg)localObject1);
          ch.a(this.a).d(this.b);
          localObject2 = this.a;
          localObject1 = ((a)localObject3).getActivity();
          ch.a((ch)localObject2, (Context)localObject1);
          return;
          if (this.j != 3) {
            return;
          }
          localObject2 = (Fragment)this.e.get();
          localObject1 = (Fragment)this.i.get();
          if ((localObject2 == null) || (localObject1 == null)) {
            break label499;
          }
        } while (localObject2 != localObject1);
        localObject1 = "";
        localObject3 = ((Fragment)localObject2).getActivity();
        if (localObject3 != null) {
          localObject1 = ((Activity)localObject3).getTitle().toString();
        }
        l1 = this.b - this.f;
        localObject3 = ch.a(localObject2);
        if (localObject3 != null) {
          break;
        }
        localObject1 = "getContxtFromReverse faild.";
      }
    }
    Object localObject2 = localObject2.getClass().getName();
    Object localObject4 = ((String)localObject2).substring(((String)localObject2).lastIndexOf(".") + 1);
    Object localObject5 = new StringBuilder();
    ((StringBuilder)localObject5).append("android.app.Fragment new page view, page name = ");
    ((StringBuilder)localObject5).append(((String)localObject2).toString());
    ((StringBuilder)localObject5).append("; stay time = ");
    ((StringBuilder)localObject5).append(l1);
    ((StringBuilder)localObject5).append("(ms)");
    cz.a(((StringBuilder)localObject5).toString());
    Object localObject1 = new cg((String)localObject4, (String)localObject1, (String)localObject4, l1, this.f, this.m, this.n);
    ch.a(this.a).a((cg)localObject1);
    ch.a(this.a).d(this.b);
    ch.a(this.a, (Context)localObject3);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */