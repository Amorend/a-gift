package com.androlua;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
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

public class LuaExpandableListAdapter
  extends BaseExpandableListAdapter
{
  private BitmapDrawable a;
  private Resources b;
  private LuaState c;
  private LuaContext d;
  private LuaTable<Integer, LuaTable<String, Object>> e;
  private LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> f;
  private HashMap<View, Animation> g = new HashMap();
  private LuaTable h;
  private LuaTable i;
  private LuaFunction<View> j;
  private LuaFunction<?> k;
  private LuaFunction<?> l;
  private boolean m;
  private LuaFunction<Animation> n;
  private boolean o;
  private Handler p = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      LuaExpandableListAdapter.this.notifyDataSetChanged();
    }
  };
  private HashMap<String, Boolean> q = new HashMap();
  
  public LuaExpandableListAdapter(LuaContext paramLuaContext, LuaTable paramLuaTable1, LuaTable paramLuaTable2)
  {
    this(paramLuaContext, null, null, paramLuaTable1, paramLuaTable2);
  }
  
  public LuaExpandableListAdapter(LuaContext paramLuaContext, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable, LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> paramLuaTable1, LuaTable paramLuaTable2, LuaTable paramLuaTable3)
  {
    this.d = paramLuaContext;
    this.c = paramLuaContext.getLuaState();
    this.b = this.d.getContext().getResources();
    this.a = new BitmapDrawable(this.b, getClass().getResourceAsStream("/res/drawable/icon.png"));
    this.a.setColorFilter(-1996488705, PorterDuff.Mode.SRC_ATOP);
    this.h = paramLuaTable2;
    this.i = paramLuaTable3;
    paramLuaContext = paramLuaTable;
    if (paramLuaTable == null) {
      paramLuaContext = new LuaTable(this.c);
    }
    paramLuaTable = paramLuaTable1;
    if (paramLuaTable1 == null) {
      paramLuaTable = new LuaTable(this.c);
    }
    this.e = paramLuaContext;
    this.f = paramLuaTable;
    this.j = this.c.getLuaObject("loadlayout").getFunction();
    this.k = this.c.getLuaObject("table").getField("insert").getFunction();
    this.l = this.c.getLuaObject("table").getField("remove").getFunction();
    this.c.newTable();
    this.j.call(new Object[] { this.h, this.c.getLuaObject(-1), AbsListView.class });
    this.j.call(new Object[] { this.i, this.c.getLuaObject(-1), AbsListView.class });
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
      try
      {
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = ((Map.Entry)localObject).getValue();
        if (str.toLowerCase().equals("src")) {
          a(paramView, localObject);
        } else {
          a(paramView, str, localObject);
        }
      }
      catch (Exception localException)
      {
        Log.i("lua", localException.getMessage());
      }
    }
  }
  
  private void a(View paramView, Object paramObject)
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
    if ((paramView instanceof ImageView)) {
      try
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
      catch (Exception paramView)
      {
        Log.i("lua", paramView.getMessage());
      }
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
  
  public GroupItem add(LuaTable<String, Object> paramLuaTable)
  {
    this.e.put(Integer.valueOf(this.e.length() + 1), paramLuaTable);
    paramLuaTable = new LuaTable(this.c);
    this.f.put(Integer.valueOf(this.e.length()), paramLuaTable);
    if (this.o) {
      notifyDataSetChanged();
    }
    return new GroupItem(paramLuaTable);
  }
  
  public GroupItem add(LuaTable<String, Object> paramLuaTable, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable1)
  {
    this.e.put(Integer.valueOf(this.e.length() + 1), paramLuaTable);
    this.f.put(Integer.valueOf(this.e.length()), paramLuaTable1);
    if (this.o) {
      notifyDataSetChanged();
    }
    return new GroupItem(paramLuaTable1);
  }
  
  public void clear()
  {
    this.e.clear();
    this.f.clear();
    if (this.o) {
      notifyDataSetChanged();
    }
  }
  
  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((LuaTable)this.f.get(Integer.valueOf(paramInt1 + 1))).get(Integer.valueOf(paramInt2 + 1));
  }
  
  public LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> getChildData()
  {
    return this.f;
  }
  
  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2 + 1;
  }
  
  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {}
    try
    {
      paramViewGroup = new LuaTable(this.c);
      localView = (View)this.j.call(new Object[] { this.i, paramViewGroup, AbsListView.class });
      localView.setTag(paramViewGroup);
    }
    catch (LuaException paramView)
    {
      View localView;
      Object localObject1;
      for (;;) {}
    }
    return new View(this.d.getContext());
    paramViewGroup = (LuaTable)paramView.getTag();
    localView = paramView;
    localObject1 = (LuaTable)((LuaTable)this.f.get(Integer.valueOf(paramInt1 + 1))).get(Integer.valueOf(paramInt2 + 1));
    if (localObject1 == null)
    {
      paramView = new StringBuilder();
      paramView.append(paramInt2);
      paramView.append(" is null");
      Log.i("lua", paramView.toString());
      return localView;
    }
    localObject1 = ((LuaTable)localObject1).entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      try
      {
        Object localObject2 = (String)((Map.Entry)localObject3).getKey();
        localObject3 = ((Map.Entry)localObject3).getValue();
        localObject2 = (View)paramViewGroup.get(localObject2);
        if (localObject2 != null) {
          a((View)localObject2, localObject3);
        }
      }
      catch (Exception localException2)
      {
        Log.i("lua", localException2.getMessage());
      }
    }
    if (this.m) {
      return localView;
    }
    if ((this.n != null) && (paramView != null))
    {
      localObject1 = (Animation)this.g.get(paramView);
      paramViewGroup = (ViewGroup)localObject1;
      if (localObject1 == null)
      {
        try
        {
          paramViewGroup = (Animation)this.n.call(new Object[0]);
          try
          {
            this.g.put(paramView, paramViewGroup);
          }
          catch (Exception localException1)
          {
            paramView = paramViewGroup;
            paramViewGroup = localException1;
          }
          Log.i("lua", paramViewGroup.getMessage());
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
  
  public int getChildrenCount(int paramInt)
  {
    return ((LuaTable)this.f.get(Integer.valueOf(paramInt + 1))).length();
  }
  
  public Object getGroup(int paramInt)
  {
    return this.e.get(Integer.valueOf(paramInt + 1));
  }
  
  public int getGroupCount()
  {
    return this.e.length();
  }
  
  public LuaTable<Integer, LuaTable<String, Object>> getGroupData()
  {
    return this.e;
  }
  
  public long getGroupId(int paramInt)
  {
    return paramInt + 1;
  }
  
  public GroupItem getGroupItem(int paramInt)
  {
    return new GroupItem((LuaTable)this.f.get(Integer.valueOf(paramInt + 1)));
  }
  
  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {}
    try
    {
      paramViewGroup = new LuaTable(this.c);
      localView = (View)this.j.call(new Object[] { this.h, paramViewGroup, AbsListView.class });
      localView.setTag(paramViewGroup);
    }
    catch (LuaException paramView)
    {
      View localView;
      Object localObject1;
      for (;;) {}
    }
    return new View(this.d.getContext());
    paramViewGroup = (LuaTable)paramView.getTag();
    localView = paramView;
    localObject1 = (LuaTable)this.e.get(Integer.valueOf(paramInt + 1));
    if (localObject1 == null)
    {
      paramView = new StringBuilder();
      paramView.append(paramInt);
      paramView.append(" is null");
      Log.i("lua", paramView.toString());
      return localView;
    }
    localObject1 = ((LuaTable)localObject1).entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      try
      {
        Object localObject2 = (String)((Map.Entry)localObject3).getKey();
        localObject3 = ((Map.Entry)localObject3).getValue();
        localObject2 = (View)paramViewGroup.get(localObject2);
        if (localObject2 != null) {
          a((View)localObject2, localObject3);
        }
      }
      catch (Exception localException2)
      {
        Log.i("lua", localException2.getMessage());
      }
    }
    if (this.m) {
      return localView;
    }
    if ((this.n != null) && (paramView != null))
    {
      localObject1 = (Animation)this.g.get(paramView);
      paramViewGroup = (ViewGroup)localObject1;
      if (localObject1 == null)
      {
        try
        {
          paramViewGroup = (Animation)this.n.call(new Object[0]);
          try
          {
            this.g.put(paramView, paramViewGroup);
          }
          catch (Exception localException1)
          {
            paramView = paramViewGroup;
            paramViewGroup = localException1;
          }
          Log.i("lua", paramViewGroup.getMessage());
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
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public GroupItem insert(int paramInt, LuaTable<String, Object> paramLuaTable, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable1)
  {
    LuaFunction localLuaFunction = this.k;
    LuaTable localLuaTable = this.e;
    paramInt += 1;
    localLuaFunction.call(new Object[] { localLuaTable, Integer.valueOf(paramInt), paramLuaTable });
    this.k.call(new Object[] { this.f, Integer.valueOf(paramInt), paramLuaTable1 });
    if (this.o) {
      notifyDataSetChanged();
    }
    return new GroupItem(paramLuaTable1);
  }
  
  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public void remove(int paramInt)
  {
    this.l.call(new Object[] { this.e, Integer.valueOf(paramInt + 1) });
    if (this.o) {
      notifyDataSetChanged();
    }
  }
  
  public void setAnimationUtil(LuaFunction<Animation> paramLuaFunction)
  {
    this.g.clear();
    this.n = paramLuaFunction;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.o = paramBoolean;
    if (this.o) {
      notifyDataSetChanged();
    }
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
        return new BitmapDrawable(LuaExpandableListAdapter.d(LuaExpandableListAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (LuaBitmap.checkCache(paramLuaContext, paramString)) {
        return new BitmapDrawable(LuaExpandableListAdapter.d(LuaExpandableListAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (!LuaExpandableListAdapter.e(LuaExpandableListAdapter.this).containsKey(this.b))
      {
        start();
        LuaExpandableListAdapter.e(LuaExpandableListAdapter.this).put(this.b, Boolean.valueOf(true));
      }
      return LuaExpandableListAdapter.f(LuaExpandableListAdapter.this);
    }
    
    public void run()
    {
      try
      {
        LuaBitmap.getBitmap(this.c, this.b);
        LuaExpandableListAdapter.g(LuaExpandableListAdapter.this).sendEmptyMessage(0);
        return;
      }
      catch (IOException localIOException)
      {
        this.c.sendError("AsyncLoader", localIOException);
      }
    }
  }
  
  private class GroupItem
  {
    private LuaTable<Integer, LuaTable<String, Object>> b;
    
    public GroupItem()
    {
      LuaTable localLuaTable;
      this.b = localLuaTable;
    }
    
    public void add(LuaTable<String, Object> paramLuaTable)
    {
      this.b.put(Integer.valueOf(this.b.length() + 1), paramLuaTable);
      if (LuaExpandableListAdapter.a(LuaExpandableListAdapter.this)) {
        LuaExpandableListAdapter.this.notifyDataSetChanged();
      }
    }
    
    public void clear()
    {
      this.b.clear();
      if (LuaExpandableListAdapter.a(LuaExpandableListAdapter.this)) {
        LuaExpandableListAdapter.this.notifyDataSetChanged();
      }
    }
    
    public LuaTable<Integer, LuaTable<String, Object>> getData()
    {
      return this.b;
    }
    
    public void insert(int paramInt, LuaTable<String, Object> paramLuaTable)
    {
      LuaExpandableListAdapter.b(LuaExpandableListAdapter.this).call(new Object[] { this.b, Integer.valueOf(paramInt + 1), paramLuaTable });
      if (LuaExpandableListAdapter.a(LuaExpandableListAdapter.this)) {
        LuaExpandableListAdapter.this.notifyDataSetChanged();
      }
    }
    
    public void remove(int paramInt)
    {
      LuaExpandableListAdapter.c(LuaExpandableListAdapter.this).call(new Object[] { this.b, Integer.valueOf(paramInt + 1) });
      if (LuaExpandableListAdapter.a(LuaExpandableListAdapter.this)) {
        LuaExpandableListAdapter.this.notifyDataSetChanged();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaExpandableListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */