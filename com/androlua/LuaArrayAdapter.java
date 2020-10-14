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
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ArrayListAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import java.io.IOException;
import java.util.HashMap;

public class LuaArrayAdapter
  extends ArrayListAdapter
{
  private Resources a;
  private LuaContext b;
  private LuaState c;
  private LuaObject d;
  private LuaObject e;
  private Animation f;
  private Handler g = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      LuaArrayAdapter.this.notifyDataSetChanged();
    }
  };
  private HashMap<String, Boolean> h = new HashMap();
  
  public LuaArrayAdapter(LuaContext paramLuaContext, LuaObject paramLuaObject)
  {
    this(paramLuaContext, paramLuaObject, new String[0]);
  }
  
  public LuaArrayAdapter(LuaContext paramLuaContext, LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    super(paramLuaContext.getContext(), 0, paramArrayOfObject);
    this.b = paramLuaContext;
    this.d = paramLuaObject;
    this.a = this.b.getContext().getResources();
    this.c = paramLuaContext.getLuaState();
    this.e = this.c.getLuaObject("loadlayout");
    this.c.newTable();
    this.e.call(new Object[] { this.d, this.c.getLuaObject(-1), AbsListView.class });
    this.c.pop(1);
  }
  
  private void a(View paramView, Object paramObject)
  {
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
        ImageView localImageView = (ImageView)paramView;
        boolean bool = paramObject instanceof Bitmap;
        paramView = null;
        if (bool) {
          paramView = new BitmapDrawable(this.a, (Bitmap)paramObject);
        } else if ((paramObject instanceof String)) {
          paramView = new AsyncLoader(null).getBitmap(this.b, (String)paramObject);
        } else if ((paramObject instanceof Drawable)) {
          paramView = (Drawable)paramObject;
        } else if ((paramObject instanceof Number)) {
          paramView = this.a.getDrawable(((Number)paramObject).intValue());
        }
        localImageView.setImageDrawable(paramView);
        if ((paramView instanceof BitmapDrawable))
        {
          paramView = ((BitmapDrawable)paramView).getBitmap();
          int i = paramView.getWidth();
          int j = paramView.getHeight();
          if (localImageView.getScaleType() == ImageView.ScaleType.FIT_XY)
          {
            i = (int)(this.b.getWidth() * j / i);
            localImageView.setLayoutParams(new ViewGroup.LayoutParams(this.b.getWidth(), i));
            return;
          }
        }
      }
      catch (Exception paramView)
      {
        Log.i("lua", paramView.getMessage());
      }
    }
  }
  
  public Animation getAnimation()
  {
    return this.f;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getView(paramInt, paramView, paramViewGroup);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null)
    {
      this.c.newTable();
      paramView = this.c.getLuaObject(-1);
      this.c.pop(1);
    }
    try
    {
      paramViewGroup = (View)this.e.call(new Object[] { this.d, paramView, AbsListView.class });
    }
    catch (LuaException paramView)
    {
      for (;;) {}
    }
    return new View(this.b.getContext());
    a(paramViewGroup, getItem(paramInt));
    if (this.f != null) {
      paramViewGroup.startAnimation(this.f);
    }
    return paramViewGroup;
  }
  
  public void setAnimation(Animation paramAnimation)
  {
    this.f = paramAnimation;
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
        return new BitmapDrawable(LuaArrayAdapter.a(LuaArrayAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (LuaBitmap.checkCache(paramLuaContext, paramString)) {
        return new BitmapDrawable(LuaArrayAdapter.a(LuaArrayAdapter.this), LuaBitmap.getBitmap(paramLuaContext, paramString));
      }
      if (!LuaArrayAdapter.b(LuaArrayAdapter.this).containsKey(this.b))
      {
        start();
        LuaArrayAdapter.b(LuaArrayAdapter.this).put(this.b, Boolean.valueOf(true));
      }
      return new LoadingDrawable(this.c.getContext());
    }
    
    public void run()
    {
      try
      {
        LuaBitmap.getBitmap(this.c, this.b);
        LuaArrayAdapter.c(LuaArrayAdapter.this).sendEmptyMessage(0);
        return;
      }
      catch (IOException localIOException)
      {
        this.c.sendError("AsyncLoader", localIOException);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaArrayAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */