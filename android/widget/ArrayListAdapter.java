package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayListAdapter<T>
  extends BaseAdapter
  implements Filterable
{
  private ArrayList<T> a;
  private final Object b = new Object();
  private int c;
  private int d;
  private int e = 0;
  private boolean f = true;
  private Context g;
  private ArrayList<T> h;
  private ArrayListAdapter<T>.ArrayFilter i;
  private LayoutInflater j;
  private LuaFunction k;
  
  public ArrayListAdapter(Context paramContext)
  {
    a(paramContext, 17367043, 0, new ArrayList());
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt)
  {
    a(paramContext, paramInt, 0, new ArrayList());
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    a(paramContext, paramInt1, paramInt2, new ArrayList());
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt1, int paramInt2, List<T> paramList)
  {
    a(paramContext, paramInt1, paramInt2, paramList);
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt1, int paramInt2, T[] paramArrayOfT)
  {
    a(paramContext, paramInt1, paramInt2, Arrays.asList(paramArrayOfT));
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt, List<T> paramList)
  {
    a(paramContext, paramInt, 0, paramList);
  }
  
  public ArrayListAdapter(Context paramContext, int paramInt, T[] paramArrayOfT)
  {
    a(paramContext, paramInt, 0, Arrays.asList(paramArrayOfT));
  }
  
  public ArrayListAdapter(Context paramContext, List<T> paramList)
  {
    a(paramContext, 17367043, 0, paramList);
  }
  
  public ArrayListAdapter(Context paramContext, T[] paramArrayOfT)
  {
    a(paramContext, 17367043, 0, Arrays.asList(paramArrayOfT));
  }
  
  /* Error */
  private View a(int paramInt1, View paramView, ViewGroup paramViewGroup, int paramInt2)
  {
    // Byte code:
    //   0: aload_2
    //   1: astore 5
    //   3: aload_2
    //   4: ifnonnull +16 -> 20
    //   7: aload_0
    //   8: getfield 80	android/widget/ArrayListAdapter:j	Landroid/view/LayoutInflater;
    //   11: iload 4
    //   13: aload_3
    //   14: iconst_0
    //   15: invokevirtual 86	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   18: astore 5
    //   20: aload_0
    //   21: getfield 45	android/widget/ArrayListAdapter:e	I
    //   24: ifne +12 -> 36
    //   27: aload 5
    //   29: checkcast 88	android/widget/TextView
    //   32: astore_2
    //   33: goto +16 -> 49
    //   36: aload 5
    //   38: aload_0
    //   39: getfield 45	android/widget/ArrayListAdapter:e	I
    //   42: invokevirtual 94	android/view/View:findViewById	(I)Landroid/view/View;
    //   45: checkcast 88	android/widget/TextView
    //   48: astore_2
    //   49: aload_0
    //   50: iload_1
    //   51: invokevirtual 98	android/widget/ArrayListAdapter:getItem	(I)Ljava/lang/Object;
    //   54: astore_3
    //   55: aload_3
    //   56: instanceof 100
    //   59: ifeq +16 -> 75
    //   62: aload_3
    //   63: checkcast 100	java/lang/CharSequence
    //   66: astore_3
    //   67: aload_2
    //   68: aload_3
    //   69: invokevirtual 104	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   72: aload 5
    //   74: areturn
    //   75: aload_3
    //   76: invokevirtual 108	java/lang/Object:toString	()Ljava/lang/String;
    //   79: astore_3
    //   80: goto -13 -> 67
    //   83: astore_2
    //   84: ldc 110
    //   86: ldc 112
    //   88: invokestatic 117	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   91: pop
    //   92: new 119	java/lang/IllegalStateException
    //   95: dup
    //   96: ldc 121
    //   98: aload_2
    //   99: invokespecial 124	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   102: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	ArrayListAdapter
    //   0	103	1	paramInt1	int
    //   0	103	2	paramView	View
    //   0	103	3	paramViewGroup	ViewGroup
    //   0	103	4	paramInt2	int
    //   1	72	5	localView	View
    // Exception table:
    //   from	to	target	type
    //   20	33	83	java/lang/ClassCastException
    //   36	49	83	java/lang/ClassCastException
  }
  
  private void a(Context paramContext, int paramInt1, int paramInt2, List<T> paramList)
  {
    this.g = paramContext;
    this.j = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.d = paramInt1;
    this.c = paramInt1;
    this.a = new ArrayList(paramList);
    this.e = paramInt2;
  }
  
  public static ArrayListAdapter<CharSequence> createFromResource(Context paramContext, int paramInt1, int paramInt2)
  {
    return new ArrayListAdapter(paramContext, paramInt2, paramContext.getResources().getTextArray(paramInt1));
  }
  
  public void add(T paramT)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.add(paramT);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void addAll(Collection<? extends T> paramCollection)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.addAll(paramCollection);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void addAll(T... paramVarArgs)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        Collections.addAll(localArrayList, paramVarArgs);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void clear()
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.clear();
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void filter(CharSequence paramCharSequence)
  {
    getFilter().filter(paramCharSequence);
  }
  
  public Context getContext()
  {
    return this.g;
  }
  
  public int getCount()
  {
    return this.a.size();
  }
  
  public Object getData()
  {
    return this.a;
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.d);
  }
  
  public Filter getFilter()
  {
    if (this.i == null) {
      this.i = new ArrayFilter(null);
    }
    return this.i;
  }
  
  public T getItem(int paramInt)
  {
    return (T)this.a.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt + 1;
  }
  
  public int getPosition(T paramT)
  {
    return this.a.indexOf(paramT);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return a(paramInt, paramView, paramViewGroup, this.c);
  }
  
  public void insert(int paramInt, T paramT)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.add(paramInt, paramT);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.f = true;
  }
  
  public void remove(int paramInt)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.remove(paramInt);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void remove(T paramT)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        localArrayList.remove(paramT);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  public void setDropDownViewResource(int paramInt)
  {
    this.d = paramInt;
  }
  
  public void setFilter(LuaFunction paramLuaFunction)
  {
    this.k = paramLuaFunction;
  }
  
  public void setNotifyOnChange(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public void sort(Comparator<? super T> paramComparator)
  {
    synchronized (this.b)
    {
      if (this.h != null) {}
      for (ArrayList localArrayList = this.h;; localArrayList = this.a)
      {
        Collections.sort(localArrayList, paramComparator);
        break;
      }
      if (this.f) {
        notifyDataSetChanged();
      }
      return;
    }
  }
  
  private class ArrayFilter
    extends Filter
  {
    private ArrayFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence arg1)
    {
      Filter.FilterResults localFilterResults = new Filter.FilterResults();
      if (ArrayListAdapter.a(ArrayListAdapter.this) == null) {
        synchronized (ArrayListAdapter.b(ArrayListAdapter.this))
        {
          ArrayListAdapter.a(ArrayListAdapter.this, new ArrayList(ArrayListAdapter.c(ArrayListAdapter.this)));
        }
      }
      if (TextUtils.isEmpty(???))
      {
        localFilterResults.values = new ArrayList(ArrayListAdapter.a(ArrayListAdapter.this));
        localFilterResults.count = ArrayListAdapter.a(ArrayListAdapter.this).size();
        ArrayListAdapter.a(ArrayListAdapter.this, null);
        return localFilterResults;
      }
      ??? = ArrayListAdapter.d(ArrayListAdapter.this);
      int i = 0;
      if (??? != null)
      {
        ??? = new ArrayList();
        try
        {
          ArrayListAdapter.d(ArrayListAdapter.this).call(new Object[] { new ArrayList(ArrayListAdapter.a(ArrayListAdapter.this)), ???, ??? });
        }
        catch (LuaException ???)
        {
          ???.printStackTrace();
        }
        localFilterResults.values = ???;
        localFilterResults.count = ((ArrayList)???).size();
        return localFilterResults;
      }
      if ((??? != null) && (???.length() != 0))
      {
        ??? = ???.toString().toLowerCase();
        synchronized (ArrayListAdapter.b(ArrayListAdapter.this))
        {
          ??? = new ArrayList(ArrayListAdapter.a(ArrayListAdapter.this));
          int j = ((ArrayList)???).size();
          ??? = new ArrayList();
          while (i < j)
          {
            Object localObject4 = ((ArrayList)???).get(i);
            if (localObject4.toString().toLowerCase().contains(???)) {
              ((ArrayList)???).add(localObject4);
            }
            i += 1;
          }
          localFilterResults.values = ???;
          i = ((ArrayList)???).size();
        }
      }
      synchronized (ArrayListAdapter.b(ArrayListAdapter.this))
      {
        ??? = new ArrayList(ArrayListAdapter.a(ArrayListAdapter.this));
        localFilterResults.values = ???;
        i = ((ArrayList)???).size();
        localFilterResults.count = i;
        return localFilterResults;
      }
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      ArrayListAdapter.b(ArrayListAdapter.this, (ArrayList)paramFilterResults.values);
      if (paramFilterResults.count > 0)
      {
        ArrayListAdapter.this.notifyDataSetChanged();
        return;
      }
      ArrayListAdapter.this.notifyDataSetInvalidated();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\ArrayListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */