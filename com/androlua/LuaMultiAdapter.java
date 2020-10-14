package com.androlua;

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

public class LuaMultiAdapter
  extends BaseAdapter
{
  private Resources a;
  private LuaState b;
  private LuaContext c;
  private LuaTable<Integer, LuaTable> d;
  private LuaTable<Integer, LuaTable<String, Object>> e;
  private LuaTable<String, Object> f;
  private LuaFunction<View> g;
  private LuaFunction h;
  private LuaFunction i;
  private LuaTable<Integer, LuaFunction<Animation>> j;
  private HashMap<View, Animation> k = new HashMap();
  private HashMap<View, Boolean> l = new HashMap();
  private boolean m = true;
  private boolean n;
  private Handler o = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      LuaMultiAdapter.this.notifyDataSetChanged();
    }
  };
  private HashMap<String, Boolean> p = new HashMap();
  
  public LuaMultiAdapter(LuaContext paramLuaContext, LuaTable paramLuaTable)
  {
    this(paramLuaContext, null, paramLuaTable);
  }
  
  public LuaMultiAdapter(LuaContext paramLuaContext, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable, LuaTable<Integer, LuaTable> paramLuaTable1)
  {
    this.c = paramLuaContext;
    this.d = paramLuaTable1;
    this.a = this.c.getContext().getResources();
    this.b = paramLuaContext.getLuaState();
    paramLuaContext = paramLuaTable;
    if (paramLuaTable == null) {
      paramLuaContext = new LuaTable(this.b);
    }
    this.e = paramLuaContext;
    this.g = this.b.getLuaObject("loadlayout").getFunction();
    this.h = this.b.getLuaObject("table").getField("insert").getFunction();
    this.i = this.b.getLuaObject("table").getField("remove").getFunction();
    int i2 = this.d.length();
    int i1 = 1;
    while (i1 <= i2)
    {
      this.b.newTable();
      this.g.call(new Object[] { this.d.get(Integer.valueOf(i1)), this.b.getLuaObject(-1), AbsListView.class });
      this.b.pop(1);
      i1 += 1;
    }
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
        for (paramObject = new AsyncLoader(null).getBitmap(this.c, (String)paramObject);; paramObject = (Drawable)paramObject)
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
      this.c.sendError("setHelper", paramView);
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
        this.b.newTable();
        this.b.pushObjectValue(paramObject2);
        this.b.setField(-2, paramString);
        try
        {
          localMethod.invoke(paramObject1, new Object[] { this.b.getLuaObject(-1).createProxy(arrayOfClass[0]) });
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
    this.h.call(new Object[] { this.e, paramLuaTable });
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public void addAll(LuaTable<Integer, LuaTable<String, Object>> paramLuaTable)
  {
    int i2 = paramLuaTable.length();
    int i1 = 1;
    while (i1 <= i2)
    {
      this.h.call(new Object[] { this.e, paramLuaTable.get(Integer.valueOf(i1)) });
      i1 += 1;
    }
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public void clear()
  {
    this.e.clear();
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public int getCount()
  {
    return this.e.length();
  }
  
  public LuaTable<Integer, LuaTable<String, Object>> getData()
  {
    return this.e;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getView(paramInt, paramView, paramViewGroup);
  }
  
  public Object getItem(int paramInt)
  {
    return this.e.get(Integer.valueOf(paramInt + 1));
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt + 1;
  }
  
  public int getItemViewType(int paramInt)
  {
    int i1 = ((Long)((LuaTable)this.e.get(Integer.valueOf(paramInt + 1))).get("__type")).intValue() - 1;
    paramInt = i1;
    if (i1 < 0) {
      paramInt = 0;
    }
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = this.e;
    int i3 = paramInt + 1;
    int i2 = ((Long)((LuaTable)paramViewGroup.get(Integer.valueOf(i3))).get("__type")).intValue();
    int i1 = i2;
    if (i2 < 1) {
      i1 = 1;
    }
    if (paramView == null) {}
    try
    {
      localObject1 = (LuaTable)this.d.get(Integer.valueOf(i1));
      this.b.newTable();
      paramViewGroup = this.b.getLuaObject(-1);
      this.b.pop(1);
      localObject1 = (View)this.g.call(new Object[] { localObject1, paramViewGroup, AbsListView.class });
      ((View)localObject1).setTag(paramViewGroup);
    }
    catch (LuaException paramView)
    {
      Object localObject1;
      Object localObject2;
      for (;;) {}
    }
    return new View(this.c.getContext());
    paramViewGroup = (LuaObject)paramView.getTag();
    localObject1 = paramView;
    localObject2 = (LuaTable)this.e.get(Integer.valueOf(i3));
    if (localObject2 == null)
    {
      paramView = new StringBuilder();
      paramView.append(paramInt);
      paramView.append(" is null");
      Log.i("lua", paramView.toString());
      return (View)localObject1;
    }
    if (this.l.get(localObject1) == null) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    if (paramInt != 0) {
      this.l.put(localObject1, Boolean.valueOf(true));
    }
    localObject2 = ((LuaTable)localObject2).entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
      try
      {
        String str = (String)((Map.Entry)localObject3).getKey();
        if (!str.equals("type"))
        {
          localObject3 = ((Map.Entry)localObject3).getValue();
          LuaObject localLuaObject = paramViewGroup.getField(str);
          if (localLuaObject.isJavaObject())
          {
            if ((this.f != null) && (paramInt != 0)) {
              a((View)localLuaObject.getObject(), this.f.get(str));
            }
            a((View)localLuaObject.getObject(), localObject3);
          }
        }
      }
      catch (Exception localException2)
      {
        Log.i("lua", localException2.getMessage());
      }
    }
    if (this.n) {
      return (View)localObject1;
    }
    if ((this.j != null) && (paramView != null))
    {
      localObject2 = (Animation)this.k.get(paramView);
      paramViewGroup = (ViewGroup)localObject2;
      if (localObject2 == null)
      {
        try
        {
          paramViewGroup = (Animation)((LuaFunction)this.j.get(Integer.valueOf(i1))).call(new Object[0]);
          try
          {
            this.k.put(paramView, paramViewGroup);
          }
          catch (Exception localException1)
          {
            paramView = paramViewGroup;
            paramViewGroup = localException1;
          }
          this.c.sendError("setAnimation", paramViewGroup);
        }
        catch (Exception paramViewGroup)
        {
          paramView = localException1;
        }
        paramViewGroup = paramView;
      }
      if (paramViewGroup != null)
      {
        ((View)localObject1).clearAnimation();
        ((View)localObject1).startAnimation(paramViewGroup);
      }
    }
    return (View)localObject1;
  }
  
  public int getViewTypeCount()
  {
    return this.d.length();
  }
  
  public void insert(int paramInt, LuaTable<String, Object> paramLuaTable)
  {
    this.h.call(new Object[] { this.e, Integer.valueOf(paramInt + 1), paramLuaTable });
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    if (!this.n)
    {
      this.n = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LuaMultiAdapter.a(LuaMultiAdapter.this, false);
        }
      }, 500L);
    }
  }
  
  public void remove(int paramInt)
  {
    this.i.call(new Object[] { this.e, Integer.valueOf(paramInt + 1) });
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public void setAnimation(LuaTable<Integer, LuaFunction<Animation>> paramLuaTable)
  {
    setAnimationUtil(paramLuaTable);
  }
  
  public void setAnimationUtil(LuaTable<Integer, LuaFunction<Animation>> paramLuaTable)
  {
    this.k.clear();
    this.j = paramLuaTable;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.m = paramBoolean;
    if (this.m) {
      notifyDataSetChanged();
    }
  }
  
  public void setStyle(LuaTable<String, Object> paramLuaTable)
  {
    this.l.clear();
    this.f = paramLuaTable;
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
        return new BitmapDrawable(LuaMultiAdapter.a(LuaMultiAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (LuaBitmap.checkCache(paramLuaContext, paramString)) {
        return new BitmapDrawable(LuaMultiAdapter.a(LuaMultiAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (!LuaMultiAdapter.b(LuaMultiAdapter.this).containsKey(this.b))
      {
        start();
        LuaMultiAdapter.b(LuaMultiAdapter.this).put(this.b, Boolean.valueOf(true));
      }
      return new LoadingDrawable(this.c.getContext());
    }
    
    public void run()
    {
      try
      {
        LuaBitmap.getBitmap(this.c, this.b);
        LuaMultiAdapter.c(LuaMultiAdapter.this).sendEmptyMessage(0);
        return;
      }
      catch (IOException localIOException)
      {
        this.c.sendError("AsyncLoader", localIOException);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaMultiAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */