package com.a.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.TextView;
import com.a.a.b.g;
import com.a.a.b.h;
import com.a.a.b.j;
import java.util.ArrayList;

public class a
{
  private static h c = ;
  private c a;
  private Context b;
  private ListPopupWindow d;
  private a e;
  private Filter f;
  private int g;
  private int h;
  private int i;
  private CharSequence j;
  private int k;
  private GradientDrawable l;
  private int m;
  
  public a(c paramc)
  {
    this.a = paramc;
    this.b = paramc.getContext();
    d();
  }
  
  private void d()
  {
    this.d = new ListPopupWindow(this.b);
    this.d.setAnchorView(this.a);
    this.e = new a(this.b, 17367043);
    this.d.setAdapter(this.e);
    this.f = this.e.getFilter();
    d(300);
    TypedArray localTypedArray = this.b.getTheme().obtainStyledAttributes(new int[] { 16842801, 16842806 });
    int n = localTypedArray.getColor(0, 16711935);
    int i1 = localTypedArray.getColor(1, 16711935);
    localTypedArray.recycle();
    this.l = new GradientDrawable();
    this.l.setColor(n);
    this.l.setCornerRadius(4.0F);
    this.l.setStroke(1, i1);
    a(i1);
    this.d.setBackgroundDrawable(this.l);
    this.d.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        a.a(a.this).replaceText(a.a(a.this).getCaretPosition() - a.b(a.this).length(), a.b(a.this).length(), ((TextView)paramAnonymousView).getText().toString());
        a.c(a.this).a();
        a.this.b();
      }
    });
  }
  
  private void d(int paramInt)
  {
    if (this.h != paramInt)
    {
      this.h = paramInt;
      this.d.setHeight(paramInt);
    }
  }
  
  private void e(int paramInt)
  {
    paramInt = Math.min(paramInt, this.a.getWidth() / 2);
    if (this.i != paramInt)
    {
      this.i = paramInt;
      this.d.setHorizontalOffset(paramInt);
    }
  }
  
  private void f(int paramInt)
  {
    int i1 = 0 - this.d.getHeight();
    int n = paramInt;
    if (paramInt > i1)
    {
      this.a.scrollBy(0, paramInt - i1);
      n = i1;
    }
    if (this.g != n)
    {
      this.g = n;
      this.d.setVerticalOffset(n);
    }
  }
  
  public void a()
  {
    if (!this.d.isShowing()) {
      this.d.show();
    }
    this.d.getListView().setFadingEdgeLength(0);
  }
  
  public void a(int paramInt)
  {
    this.m = paramInt;
    this.l.setStroke(1, paramInt);
    this.d.setBackgroundDrawable(this.l);
  }
  
  public void a(h paramh)
  {
    try
    {
      c = paramh;
      return;
    }
    finally
    {
      paramh = finally;
      throw paramh;
    }
  }
  
  public void a(CharSequence paramCharSequence)
  {
    this.e.b();
    this.f.filter(paramCharSequence);
  }
  
  public void b()
  {
    if (this.d.isShowing()) {
      this.d.dismiss();
    }
  }
  
  public void b(int paramInt)
  {
    this.k = paramInt;
    this.l.setColor(paramInt);
    this.d.setBackgroundDrawable(this.l);
  }
  
  public void c(int paramInt)
  {
    this.d.setWidth(paramInt);
  }
  
  class a
    extends ArrayAdapter<String>
    implements Filterable
  {
    private int b;
    private g c = new g();
    private DisplayMetrics d;
    
    public a(Context paramContext, int paramInt)
    {
      super(paramInt);
      setNotifyOnChange(false);
      this.d = paramContext.getResources().getDisplayMetrics();
    }
    
    public void a()
    {
      this.c.a();
    }
    
    public void b()
    {
      this.c.b();
    }
    
    public int c()
    {
      if (this.b != 0) {
        return this.b;
      }
      TextView localTextView = (TextView)((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(17367043, null);
      localTextView.measure(0, 0);
      this.b = localTextView.getMeasuredHeight();
      return this.b;
    }
    
    public Filter getFilter()
    {
      new Filter()
      {
        protected Filter.FilterResults performFiltering(CharSequence paramAnonymousCharSequence)
        {
          ArrayList localArrayList = new ArrayList();
          paramAnonymousCharSequence = String.valueOf(paramAnonymousCharSequence).toLowerCase();
          Object localObject1 = paramAnonymousCharSequence.split("\\.");
          int m = localObject1.length;
          int k = 0;
          int j = 0;
          int i = 0;
          Object localObject2;
          if (m == 2)
          {
            localObject2 = localObject1[0];
            localObject1 = localObject1[1];
            paramAnonymousCharSequence = (CharSequence)localObject1;
            if (a.c().f((String)localObject2))
            {
              localObject2 = a.c().a((String)localObject2);
              j = localObject2.length;
              for (;;)
              {
                paramAnonymousCharSequence = (CharSequence)localObject1;
                if (i >= j) {
                  break;
                }
                paramAnonymousCharSequence = localObject2[i];
                if (paramAnonymousCharSequence.toLowerCase().startsWith((String)localObject1)) {
                  localArrayList.add(paramAnonymousCharSequence);
                }
                i += 1;
              }
            }
          }
          else if (localObject1.length == 1)
          {
            if (paramAnonymousCharSequence.charAt(paramAnonymousCharSequence.length() - 1) == '.')
            {
              localObject2 = paramAnonymousCharSequence.substring(0, paramAnonymousCharSequence.length() - 1);
              localObject1 = "";
              paramAnonymousCharSequence = (CharSequence)localObject1;
              if (a.c().f((String)localObject2))
              {
                localObject2 = a.c().a((String)localObject2);
                j = localObject2.length;
                i = k;
                for (;;)
                {
                  paramAnonymousCharSequence = (CharSequence)localObject1;
                  if (i >= j) {
                    break;
                  }
                  localArrayList.add(localObject2[i]);
                  i += 1;
                }
              }
            }
            else
            {
              localObject1 = a.c().b();
              k = localObject1.length;
              i = 0;
              while (i < k)
              {
                localObject2 = localObject1[i];
                if (((String)localObject2).toLowerCase().startsWith(paramAnonymousCharSequence)) {
                  localArrayList.add(localObject2);
                }
                i += 1;
              }
              localObject1 = a.c().d();
              k = localObject1.length;
              i = 0;
              while (i < k)
              {
                localObject2 = localObject1[i];
                if (((String)localObject2).indexOf(paramAnonymousCharSequence) == 0) {
                  localArrayList.add(localObject2);
                }
                i += 1;
              }
              localObject1 = a.c().c();
              k = localObject1.length;
              i = j;
              while (i < k)
              {
                localObject2 = localObject1[i];
                if (((String)localObject2).toLowerCase().startsWith(paramAnonymousCharSequence)) {
                  localArrayList.add(localObject2);
                }
                i += 1;
              }
            }
          }
          a.a(a.this, paramAnonymousCharSequence);
          paramAnonymousCharSequence = new Filter.FilterResults();
          paramAnonymousCharSequence.values = localArrayList;
          paramAnonymousCharSequence.count = localArrayList.size();
          return paramAnonymousCharSequence;
        }
        
        protected void publishResults(CharSequence paramAnonymousCharSequence, Filter.FilterResults paramAnonymousFilterResults)
        {
          if ((paramAnonymousFilterResults != null) && (paramAnonymousFilterResults.count > 0) && (!a.a.a(a.a.this).c()))
          {
            a.a.this.clear();
            a.a.this.addAll((ArrayList)paramAnonymousFilterResults.values);
            int i = a.a(a.this).getCaretY();
            int j = a.a(a.this).a() / 2;
            int k = a.a(a.this).getScrollY();
            a.a(a.this, a.a.this.c() * Math.min(2, paramAnonymousFilterResults.count));
            a.b(a.this, a.a(a.this).getCaretX() - a.a(a.this).getScrollX());
            a.c(a.this, i + j - k - a.a(a.this).getHeight());
            a.a.this.notifyDataSetChanged();
            a.this.a();
            return;
          }
          a.a.this.notifyDataSetInvalidated();
        }
      };
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = (TextView)super.getView(paramInt, paramView, paramViewGroup);
      paramView.setTextColor(a.d(a.this));
      return paramView;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */