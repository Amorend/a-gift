package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

final class DirectedAcyclicGraph<T>
{
  private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap();
  private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool(10);
  private final ArrayList<T> mSortResult = new ArrayList();
  private final HashSet<T> mSortTmpMarked = new HashSet();
  
  private void dfs(T paramT, ArrayList<T> paramArrayList, HashSet<T> paramHashSet)
  {
    if (paramArrayList.contains(paramT)) {
      return;
    }
    if (paramHashSet.contains(paramT)) {
      throw new RuntimeException("This graph contains cyclic dependencies");
    }
    paramHashSet.add(paramT);
    ArrayList localArrayList = (ArrayList)this.mGraph.get(paramT);
    if (localArrayList != null)
    {
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        dfs(localArrayList.get(i), paramArrayList, paramHashSet);
        i += 1;
      }
    }
    paramHashSet.remove(paramT);
    paramArrayList.add(paramT);
  }
  
  @NonNull
  private ArrayList<T> getEmptyList()
  {
    ArrayList localArrayList2 = (ArrayList)this.mListPool.acquire();
    ArrayList localArrayList1 = localArrayList2;
    if (localArrayList2 == null) {
      localArrayList1 = new ArrayList();
    }
    return localArrayList1;
  }
  
  private void poolList(@NonNull ArrayList<T> paramArrayList)
  {
    paramArrayList.clear();
    this.mListPool.release(paramArrayList);
  }
  
  void addEdge(@NonNull T paramT1, @NonNull T paramT2)
  {
    if ((!this.mGraph.containsKey(paramT1)) || (!this.mGraph.containsKey(paramT2))) {
      throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
    }
    ArrayList localArrayList2 = (ArrayList)this.mGraph.get(paramT1);
    ArrayList localArrayList1 = localArrayList2;
    if (localArrayList2 == null)
    {
      localArrayList1 = getEmptyList();
      this.mGraph.put(paramT1, localArrayList1);
    }
    localArrayList1.add(paramT2);
  }
  
  void addNode(@NonNull T paramT)
  {
    if (!this.mGraph.containsKey(paramT)) {
      this.mGraph.put(paramT, null);
    }
  }
  
  void clear()
  {
    int j = this.mGraph.size();
    int i = 0;
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.valueAt(i);
      if (localArrayList != null) {
        poolList(localArrayList);
      }
      i += 1;
    }
    this.mGraph.clear();
  }
  
  boolean contains(@NonNull T paramT)
  {
    return this.mGraph.containsKey(paramT);
  }
  
  @Nullable
  List getIncomingEdges(@NonNull T paramT)
  {
    return (List)this.mGraph.get(paramT);
  }
  
  @Nullable
  List getOutgoingEdges(@NonNull T paramT)
  {
    Object localObject1 = null;
    int j = this.mGraph.size();
    int i = 0;
    Object localObject2;
    if (i < j)
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.valueAt(i);
      localObject2 = localObject1;
      if (localArrayList != null)
      {
        localObject2 = localObject1;
        if (localArrayList.contains(paramT))
        {
          if (localObject1 != null) {
            break label99;
          }
          localObject1 = new ArrayList();
        }
      }
    }
    label99:
    for (;;)
    {
      ((ArrayList)localObject1).add(this.mGraph.keyAt(i));
      localObject2 = localObject1;
      i += 1;
      localObject1 = localObject2;
      break;
      return (List)localObject1;
    }
  }
  
  @NonNull
  ArrayList<T> getSortedList()
  {
    this.mSortResult.clear();
    this.mSortTmpMarked.clear();
    int i = 0;
    int j = this.mGraph.size();
    while (i < j)
    {
      dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
      i += 1;
    }
    return this.mSortResult;
  }
  
  boolean hasOutgoingEdges(@NonNull T paramT)
  {
    int j = this.mGraph.size();
    int i = 0;
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)this.mGraph.valueAt(i);
      if ((localArrayList != null) && (localArrayList.contains(paramT))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  int size()
  {
    return this.mGraph.size();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\DirectedAcyclicGraph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */