package com.androlua;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class LuaAdapter
  extends BaseAdapter
  implements Filterable
{
  private final LuaTable<Integer, LuaTable<String, Object>> a;
  private Resources b;
  private LuaState c;
  private LuaContext d;
  private final Object e = new Object();
  private LuaTable f;
  private LuaTable<Integer, LuaTable<String, Object>> g;
  private LuaTable<String, Object> h;
  private CharSequence i;
  private LuaFunction<View> j;
  private LuaFunction k;
  private LuaFunction l;
  private LuaFunction<Animation> m;
  private HashMap<View, Animation> n = new HashMap();
  private HashMap<View, Boolean> o = new HashMap();
  private boolean p = true;
  private boolean q;
  @SuppressLint({"HandlerLeak"})
  private Handler r = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 0)
      {
        LuaAdapter.this.notifyDataSetChanged();
        return;
      }
      try
      {
        paramAnonymousMessage = new LuaTable(LuaAdapter.a(LuaAdapter.this).getLuaState());
        LuaAdapter.d(LuaAdapter.this).call(new Object[] { LuaAdapter.b(LuaAdapter.this), paramAnonymousMessage, LuaAdapter.c(LuaAdapter.this) });
        LuaAdapter.a(LuaAdapter.this, paramAnonymousMessage);
        LuaAdapter.this.notifyDataSetChanged();
        return;
      }
      catch (LuaException paramAnonymousMessage)
      {
        paramAnonymousMessage.printStackTrace();
        LuaAdapter.e(LuaAdapter.this).sendError("performFiltering", paramAnonymousMessage);
      }
    }
  };
  private HashMap<String, Boolean> s = new HashMap();
  private ArrayFilter t;
  private LuaFunction u;
  
  public LuaAdapter(LuaContext paramLuaContext, LuaTable paramLuaTable)
  {
    this(paramLuaContext, null, paramLuaTable);
  }
  
  public LuaAdapter(LuaContext paramLuaContext, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable, LuaTable paramLuaTable1)
  {
    this.d = paramLuaContext;
    this.f = paramLuaTable1;
    this.b = this.d.getContext().getResources();
    this.c = paramLuaContext.getLuaState();
    paramLuaContext = paramLuaTable;
    if (paramLuaTable == null) {
      paramLuaContext = new LuaTable(this.c);
    }
    this.g = paramLuaContext;
    this.a = this.g;
    this.j = this.c.getLuaObject("loadlayout").getFunction();
    this.k = this.c.getLuaObject("table").getField("insert").getFunction();
    this.l = this.c.getLuaObject("table").getField("remove").getFunction();
    this.c.newTable();
    this.j.call(new Object[] { this.f, this.c.getLuaObject(-1), AbsListView.class });
    this.c.pop(1);
  }
  
  private int a(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramString.length() > 2) && (paramString.substring(0, 2).equals("on")) && ((paramObject2 instanceof LuaFunction))) {
      return b(paramObject1, paramString, paramObject2);
    }
    return c(paramObject1, paramString, paramObject2);
  }
  
  private void a(View paramView, LuaTable<String, Object> paramLuaTable)
  {
    paramLuaTable = paramLuaTable.entrySet().iterator();
    while (paramLuaTable.hasNext())
    {
      Object localObject = (Map.Entry)paramLuaTable.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      if (str.toLowerCase().equals("src")) {
        a(paramView, localObject);
      } else {
        a(paramView, str, localObject);
      }
    }
  }
  
  private void a(View paramView, Object paramObject)
  {
    try
    {
      if ((paramObject instanceof LuaTable))
      {
        a(paramView, (LuaTable)paramObject);
        return;
      }
      if ((paramView instanceof TextView))
      {
        if ((paramObject instanceof CharSequence)) {
          paramView = (TextView)paramView;
        }
        for (paramObject = (CharSequence)paramObject;; paramObject = paramObject.toString())
        {
          paramView.setText((CharSequence)paramObject);
          return;
          paramView = (TextView)paramView;
        }
      }
      if ((paramView instanceof ImageView))
      {
        if ((paramObject instanceof Bitmap))
        {
          ((ImageView)paramView).setImageBitmap((Bitmap)paramObject);
          return;
        }
        if ((paramObject instanceof String)) {
          paramView = (ImageView)paramView;
        }
        for (paramObject = new AsyncLoader(null).getBitmap(this.d, (String)paramObject);; paramObject = (Drawable)paramObject)
        {
          paramView.setImageDrawable((Drawable)paramObject);
          return;
          if (!(paramObject instanceof Drawable)) {
            break;
          }
          paramView = (ImageView)paramView;
        }
        if ((paramObject instanceof Number))
        {
          ((ImageView)paramView).setImageResource(((Number)paramObject).intValue());
          return;
        }
      }
    }
    catch (Exception paramView)
    {
      this.d.sendError("setHelper", paramView);
    }
  }
  
  private int b(Object paramObject1, String paramString, Object paramObject2)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("setOn");
    ((StringBuilder)localObject).append(paramString.substring(2));
    ((StringBuilder)localObject).append("Listener");
    localObject = ((StringBuilder)localObject).toString();
    localObject = LuaJavaAPI.getMethod(paramObject1.getClass(), (String)localObject, false).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Method localMethod = (Method)((Iterator)localObject).next();
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 1) && (arrayOfClass[0].isInterface()))
      {
        this.c.newTable();
        this.c.pushObjectValue(paramObject2);
        this.c.setField(-2, paramString);
        try
        {
          localMethod.invoke(paramObject1, new Object[] { this.c.getLuaObject(-1).createProxy(arrayOfClass[0]) });
          return 1;
        }
        catch (Exception paramObject1)
        {
          throw new LuaException((Exception)paramObject1);
        }
      }
    }
    return 0;
  }
  
  private int c(Object paramObject1, String paramString, Object paramObject2)
  {
    Object localObject = paramString;
    if (Character.isLowerCase(paramString.charAt(0)))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Character.toUpperCase(paramString.charAt(0)));
      ((StringBuilder)localObject).append(paramString.substring(1));
      localObject = ((StringBuilder)localObject).toString();
    }
    paramString = new StringBuilder();
    paramString.append("set");
    paramString.append((String)localObject);
    paramString = paramString.toString();
    Class localClass = paramObject2.getClass();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = LuaJavaAPI.getMethod(paramObject1.getClass(), paramString, false).iterator();
    while (localIterator.hasNext())
    {
      Method localMethod = (Method)localIterator.next();
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if (arrayOfClass.length == 1) {
        if (arrayOfClass[0].isPrimitive())
        {
          try
          {
            if ((!(paramObject2 instanceof Double)) && (!(paramObject2 instanceof Float)))
            {
              if ((!(paramObject2 instanceof Long)) && (!(paramObject2 instanceof Integer)))
              {
                if (!(paramObject2 instanceof Boolean)) {
                  continue;
                }
                localMethod.invoke(paramObject1, new Object[] { (Boolean)paramObject2 });
                return 1;
              }
              paramString = new Object[1];
              paramString[0] = LuaState.convertLuaNumber(Long.valueOf(((Number)paramObject2).longValue()), arrayOfClass[0]);
            }
            for (;;)
            {
              localMethod.invoke(paramObject1, paramString);
              return 1;
              paramString = new Object[1];
              paramString[0] = LuaState.convertLuaNumber(Double.valueOf(((Number)paramObject2).doubleValue()), arrayOfClass[0]);
            }
          }
          catch (Exception paramString)
          {
            localStringBuilder.append(paramString.getMessage());
            localStringBuilder.append("\n");
          }
        }
        else if (arrayOfClass[0].isAssignableFrom(localClass))
        {
          localMethod.invoke(paramObject1, new Object[] { paramObject2 });
          return 1;
        }
      }
    }
    if (localStringBuilder.length() > 0)
    {
      paramObject1 = new StringBuilder();
      ((StringBuilder)paramObject1).append("Invalid setter ");
      ((StringBuilder)paramObject1).append((String)localObject);
      ((StringBuilder)paramObject1).append(". Invalid Parameters.\n");
      ((StringBuilder)paramObject1).append(localStringBuilder.toString());
      ((StringBuilder)paramObject1).append(localClass.toString());
      throw new LuaException(((StringBuilder)paramObject1).toString());
    }
    paramObject1 = new StringBuilder();
    ((StringBuilder)paramObject1).append("Invalid setter ");
    ((StringBuilder)paramObject1).append((String)localObject);
    ((StringBuilder)paramObject1).append(" is not a method.\n");
    throw new LuaException(((StringBuilder)paramObject1).toString());
  }
  
  public void add(LuaTable<String, Object> paramLuaTable)
  {
    this.k.call(new Object[] { this.a, paramLuaTable });
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void addAll(LuaTable<Integer, LuaTable<String, Object>> paramLuaTable)
  {
    int i2 = paramLuaTable.length();
    int i1 = 1;
    while (i1 <= i2)
    {
      this.k.call(new Object[] { this.a, paramLuaTable.get(Integer.valueOf(i1)) });
      i1 += 1;
    }
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void clear()
  {
    this.a.clear();
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void filter(CharSequence paramCharSequence)
  {
    getFilter().filter(paramCharSequence);
  }
  
  public int getCount()
  {
    return this.g.length();
  }
  
  public LuaTable<Integer, LuaTable<String, Object>> getData()
  {
    return this.g;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getView(paramInt, paramView, paramViewGroup);
  }
  
  public Filter getFilter()
  {
    if (this.t == null) {
      this.t = new ArrayFilter(null);
    }
    return this.t;
  }
  
  public Object getItem(int paramInt)
  {
    return this.g.get(Integer.valueOf(paramInt + 1));
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt + 1;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {}
    try
    {
      this.c.newTable();
      paramViewGroup = this.c.getLuaObject(-1);
      this.c.pop(1);
      localView = (View)this.j.call(new Object[] { this.f, paramViewGroup, AbsListView.class });
      localView.setTag(paramViewGroup);
    }
    catch (LuaException paramView)
    {
      View localView;
      Object localObject1;
      for (;;) {}
    }
    return new View(this.d.getContext());
    paramViewGroup = (LuaObject)paramView.getTag();
    localView = paramView;
    localObject1 = (LuaTable)this.g.get(Integer.valueOf(paramInt + 1));
    if (localObject1 == null)
    {
      paramView = new StringBuilder();
      paramView.append(paramInt);
      paramView.append(" is null");
      Log.i("lua", paramView.toString());
      return localView;
    }
    if (this.o.get(localView) == null) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    if (paramInt != 0) {
      this.o.put(localView, Boolean.valueOf(true));
    }
    localObject1 = ((LuaTable)localObject1).entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
      try
      {
        String str = (String)((Map.Entry)localObject2).getKey();
        localObject2 = ((Map.Entry)localObject2).getValue();
        LuaObject localLuaObject = paramViewGroup.getField(str);
        if (localLuaObject.isJavaObject())
        {
          if ((this.h != null) && (paramInt != 0)) {
            a((View)localLuaObject.getObject(), this.h.get(str));
          }
          a((View)localLuaObject.getObject(), localObject2);
        }
      }
      catch (Exception localException2)
      {
        Log.i("lua", localException2.getMessage());
      }
    }
    if (this.q) {
      return localView;
    }
    if ((this.m != null) && (paramView != null))
    {
      localObject1 = (Animation)this.n.get(paramView);
      paramViewGroup = (ViewGroup)localObject1;
      if (localObject1 == null)
      {
        try
        {
          paramViewGroup = (Animation)this.m.call(new Object[0]);
          try
          {
            this.n.put(paramView, paramViewGroup);
          }
          catch (Exception localException1)
          {
            paramView = paramViewGroup;
            paramViewGroup = localException1;
          }
          this.d.sendError("setAnimation", paramViewGroup);
        }
        catch (Exception paramViewGroup)
        {
          paramView = localException1;
        }
        paramViewGroup = paramView;
      }
      if (paramViewGroup != null)
      {
        localView.clearAnimation();
        localView.startAnimation(paramViewGroup);
      }
    }
    return localView;
  }
  
  public void insert(int paramInt, LuaTable<String, Object> paramLuaTable)
  {
    this.k.call(new Object[] { this.a, Integer.valueOf(paramInt + 1), paramLuaTable });
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    if (!this.q)
    {
      this.q = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LuaAdapter.a(LuaAdapter.this, false);
        }
      }, 500L);
    }
  }
  
  public void remove(int paramInt)
  {
    this.l.call(new Object[] { this.a, Integer.valueOf(paramInt + 1) });
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void setAnimation(LuaFunction<Animation> paramLuaFunction)
  {
    setAnimationUtil(paramLuaFunction);
  }
  
  public void setAnimationUtil(LuaFunction<Animation> paramLuaFunction)
  {
    this.n.clear();
    this.m = paramLuaFunction;
  }
  
  public void setFilter(LuaFunction paramLuaFunction)
  {
    this.u = paramLuaFunction;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.p = paramBoolean;
    if (this.p) {
      notifyDataSetChanged();
    }
  }
  
  public void setStyle(LuaTable<String, Object> paramLuaTable)
  {
    this.o.clear();
    this.h = paramLuaTable;
  }
  
  private class ArrayFilter
    extends Filter
  {
    private ArrayFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
    {
      Filter.FilterResults localFilterResults = new Filter.FilterResults();
      LuaAdapter.a(LuaAdapter.this, paramCharSequence);
      if (LuaAdapter.a(LuaAdapter.this) == null) {
        return localFilterResults;
      }
      if (LuaAdapter.d(LuaAdapter.this) != null)
      {
        LuaAdapter.h(LuaAdapter.this).sendEmptyMessage(1);
        return null;
      }
      localFilterResults.values = LuaAdapter.a(LuaAdapter.this);
      localFilterResults.count = LuaAdapter.a(LuaAdapter.this).size();
      return localFilterResults;
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults) {}
  }
  
  private class AsyncLoader
    extends Thread
  {
    private String b;
    private LuaContext c;
    
    private AsyncLoader() {}
    
    public Drawable getBitmap(LuaContext paramLuaContext, String paramString)
    {
      this.c = paramLuaContext;
      this.b = paramString;
      if ((!paramString.toLowerCase().startsWith("http://")) && (!paramString.toLowerCase().startsWith("https://"))) {
        return new BitmapDrawable(LuaAdapter.f(LuaAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (LuaBitmap.checkCache(paramLuaContext, paramString)) {
        return new BitmapDrawable(LuaAdapter.f(LuaAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (!LuaAdapter.g(LuaAdapter.this).containsKey(this.b))
      {
        start();
        LuaAdapter.g(LuaAdapter.this).put(this.b, Boolean.valueOf(true));
      }
      return new LoadingDrawable(this.c.getContext());
    }
    
    public void run()
    {
      try
      {
        LuaBitmap.getBitmap(this.c, this.b);
        LuaAdapter.h(LuaAdapter.this).sendEmptyMessage(0);
        return;
      }
      catch (IOException localIOException)
      {
        this.c.sendError("AsyncLoader", localIOException);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */