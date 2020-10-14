package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TargetApi(14)
@RequiresApi(14)
abstract class TransitionPort
  implements Cloneable
{
  static final boolean DBG = false;
  private static final String LOG_TAG = "Transition";
  private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal();
  ArrayList<Animator> mAnimators = new ArrayList();
  boolean mCanRemoveViews = false;
  ArrayList<Animator> mCurrentAnimators = new ArrayList();
  long mDuration = -1L;
  private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
  private boolean mEnded = false;
  TimeInterpolator mInterpolator = null;
  ArrayList<TransitionListener> mListeners = null;
  private String mName = getClass().getName();
  int mNumInstances = 0;
  TransitionSetPort mParent = null;
  boolean mPaused = false;
  ViewGroup mSceneRoot = null;
  long mStartDelay = -1L;
  private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
  ArrayList<View> mTargetChildExcludes = null;
  ArrayList<View> mTargetExcludes = null;
  ArrayList<Integer> mTargetIdChildExcludes = null;
  ArrayList<Integer> mTargetIdExcludes = null;
  ArrayList<Integer> mTargetIds = new ArrayList();
  ArrayList<Class> mTargetTypeChildExcludes = null;
  ArrayList<Class> mTargetTypeExcludes = null;
  ArrayList<View> mTargets = new ArrayList();
  
  private void captureHierarchy(View paramView, boolean paramBoolean)
  {
    if (paramView == null) {}
    label178:
    label180:
    label205:
    label355:
    label372:
    label427:
    label429:
    label464:
    for (;;)
    {
      return;
      if ((paramView.getParent() instanceof ListView)) {}
      for (int i = 1;; i = 0)
      {
        if ((i != 0) && (!((ListView)paramView.getParent()).getAdapter().hasStableIds())) {
          break label464;
        }
        int j;
        long l;
        if (i == 0)
        {
          j = paramView.getId();
          l = -1L;
        }
        for (;;)
        {
          if (((this.mTargetIdExcludes != null) && (this.mTargetIdExcludes.contains(Integer.valueOf(j)))) || ((this.mTargetExcludes != null) && (this.mTargetExcludes.contains(paramView)))) {
            break label178;
          }
          if ((this.mTargetTypeExcludes == null) || (paramView == null)) {
            break label180;
          }
          int m = this.mTargetTypeExcludes.size();
          int k = 0;
          for (;;)
          {
            if (k >= m) {
              break label180;
            }
            if (((Class)this.mTargetTypeExcludes.get(k)).isInstance(paramView)) {
              break;
            }
            k += 1;
          }
          localObject = (ListView)paramView.getParent();
          l = ((ListView)localObject).getItemIdAtPosition(((ListView)localObject).getPositionForView(paramView));
          j = -1;
        }
        break;
        Object localObject = new TransitionValues();
        ((TransitionValues)localObject).view = paramView;
        if (paramBoolean)
        {
          captureStartValues((TransitionValues)localObject);
          if (!paramBoolean) {
            break label372;
          }
          if (i != 0) {
            break label355;
          }
          this.mStartValues.viewValues.put(paramView, localObject);
          if (j >= 0) {
            this.mStartValues.idValues.put(j, localObject);
          }
        }
        for (;;)
        {
          if ((!(paramView instanceof ViewGroup)) || ((this.mTargetIdChildExcludes != null) && (this.mTargetIdChildExcludes.contains(Integer.valueOf(j)))) || ((this.mTargetChildExcludes != null) && (this.mTargetChildExcludes.contains(paramView)))) {
            break label427;
          }
          if ((this.mTargetTypeChildExcludes == null) || (paramView == null)) {
            break label429;
          }
          j = this.mTargetTypeChildExcludes.size();
          i = 0;
          for (;;)
          {
            if (i >= j) {
              break label429;
            }
            if (((Class)this.mTargetTypeChildExcludes.get(i)).isInstance(paramView)) {
              break;
            }
            i += 1;
          }
          captureEndValues((TransitionValues)localObject);
          break label205;
          this.mStartValues.itemIdValues.put(l, localObject);
          continue;
          if (i == 0)
          {
            this.mEndValues.viewValues.put(paramView, localObject);
            if (j >= 0) {
              this.mEndValues.idValues.put(j, localObject);
            }
          }
          else
          {
            this.mEndValues.itemIdValues.put(l, localObject);
          }
        }
        break;
        paramView = (ViewGroup)paramView;
        i = 0;
        while (i < paramView.getChildCount())
        {
          captureHierarchy(paramView.getChildAt(i), paramBoolean);
          i += 1;
        }
        break;
      }
    }
  }
  
  private ArrayList<Integer> excludeId(ArrayList<Integer> paramArrayList, int paramInt, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramInt > 0)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, Integer.valueOf(paramInt));
      }
    }
    else {
      return (ArrayList<Integer>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, Integer.valueOf(paramInt));
  }
  
  private ArrayList<Class> excludeType(ArrayList<Class> paramArrayList, Class paramClass, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramClass != null)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, paramClass);
      }
    }
    else {
      return (ArrayList<Class>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, paramClass);
  }
  
  private ArrayList<View> excludeView(ArrayList<View> paramArrayList, View paramView, boolean paramBoolean)
  {
    Object localObject = paramArrayList;
    if (paramView != null)
    {
      if (paramBoolean) {
        localObject = ArrayListManager.add(paramArrayList, paramView);
      }
    }
    else {
      return (ArrayList<View>)localObject;
    }
    return ArrayListManager.remove(paramArrayList, paramView);
  }
  
  private static ArrayMap<Animator, AnimationInfo> getRunningAnimators()
  {
    ArrayMap localArrayMap2 = (ArrayMap)sRunningAnimators.get();
    ArrayMap localArrayMap1 = localArrayMap2;
    if (localArrayMap2 == null)
    {
      localArrayMap1 = new ArrayMap();
      sRunningAnimators.set(localArrayMap1);
    }
    return localArrayMap1;
  }
  
  private void runAnimator(Animator paramAnimator, final ArrayMap<Animator, AnimationInfo> paramArrayMap)
  {
    if (paramAnimator != null)
    {
      paramAnimator.addListener(new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          paramArrayMap.remove(paramAnonymousAnimator);
          TransitionPort.this.mCurrentAnimators.remove(paramAnonymousAnimator);
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          TransitionPort.this.mCurrentAnimators.add(paramAnonymousAnimator);
        }
      });
      animate(paramAnimator);
    }
  }
  
  public TransitionPort addListener(TransitionListener paramTransitionListener)
  {
    if (this.mListeners == null) {
      this.mListeners = new ArrayList();
    }
    this.mListeners.add(paramTransitionListener);
    return this;
  }
  
  public TransitionPort addTarget(int paramInt)
  {
    if (paramInt > 0) {
      this.mTargetIds.add(Integer.valueOf(paramInt));
    }
    return this;
  }
  
  public TransitionPort addTarget(View paramView)
  {
    this.mTargets.add(paramView);
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void animate(Animator paramAnimator)
  {
    if (paramAnimator == null)
    {
      end();
      return;
    }
    if (getDuration() >= 0L) {
      paramAnimator.setDuration(getDuration());
    }
    if (getStartDelay() >= 0L) {
      paramAnimator.setStartDelay(getStartDelay());
    }
    if (getInterpolator() != null) {
      paramAnimator.setInterpolator(getInterpolator());
    }
    paramAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        TransitionPort.this.end();
        paramAnonymousAnimator.removeListener(this);
      }
    });
    paramAnimator.start();
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void cancel()
  {
    int i = this.mCurrentAnimators.size() - 1;
    while (i >= 0)
    {
      ((Animator)this.mCurrentAnimators.get(i)).cancel();
      i -= 1;
    }
    if ((this.mListeners != null) && (this.mListeners.size() > 0))
    {
      ArrayList localArrayList = (ArrayList)this.mListeners.clone();
      int j = localArrayList.size();
      i = 0;
      while (i < j)
      {
        ((TransitionListener)localArrayList.get(i)).onTransitionCancel(this);
        i += 1;
      }
    }
  }
  
  public abstract void captureEndValues(TransitionValues paramTransitionValues);
  
  public abstract void captureStartValues(TransitionValues paramTransitionValues);
  
  void captureValues(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    int j = 0;
    clearValues(paramBoolean);
    if ((this.mTargetIds.size() > 0) || (this.mTargets.size() > 0))
    {
      int i;
      Object localObject;
      if (this.mTargetIds.size() > 0)
      {
        i = 0;
        if (i < this.mTargetIds.size())
        {
          int k = ((Integer)this.mTargetIds.get(i)).intValue();
          localObject = paramViewGroup.findViewById(k);
          TransitionValues localTransitionValues;
          if (localObject != null)
          {
            localTransitionValues = new TransitionValues();
            localTransitionValues.view = ((View)localObject);
            if (!paramBoolean) {
              break label151;
            }
            captureStartValues(localTransitionValues);
            label106:
            if (!paramBoolean) {
              break label160;
            }
            this.mStartValues.viewValues.put(localObject, localTransitionValues);
            if (k >= 0) {
              this.mStartValues.idValues.put(k, localTransitionValues);
            }
          }
          for (;;)
          {
            i += 1;
            break;
            label151:
            captureEndValues(localTransitionValues);
            break label106;
            label160:
            this.mEndValues.viewValues.put(localObject, localTransitionValues);
            if (k >= 0) {
              this.mEndValues.idValues.put(k, localTransitionValues);
            }
          }
        }
      }
      if (this.mTargets.size() > 0)
      {
        i = j;
        if (i < this.mTargets.size())
        {
          paramViewGroup = (View)this.mTargets.get(i);
          if (paramViewGroup != null)
          {
            localObject = new TransitionValues();
            ((TransitionValues)localObject).view = paramViewGroup;
            if (!paramBoolean) {
              break label287;
            }
            captureStartValues((TransitionValues)localObject);
            label262:
            if (!paramBoolean) {
              break label296;
            }
            this.mStartValues.viewValues.put(paramViewGroup, localObject);
          }
          for (;;)
          {
            i += 1;
            break;
            label287:
            captureEndValues((TransitionValues)localObject);
            break label262;
            label296:
            this.mEndValues.viewValues.put(paramViewGroup, localObject);
          }
        }
      }
    }
    else
    {
      captureHierarchy(paramViewGroup, paramBoolean);
    }
  }
  
  void clearValues(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mStartValues.viewValues.clear();
      this.mStartValues.idValues.clear();
      this.mStartValues.itemIdValues.clear();
      return;
    }
    this.mEndValues.viewValues.clear();
    this.mEndValues.idValues.clear();
    this.mEndValues.itemIdValues.clear();
  }
  
  public TransitionPort clone()
  {
    try
    {
      TransitionPort localTransitionPort = (TransitionPort)super.clone();
      return localCloneNotSupportedException1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException1)
    {
      try
      {
        localTransitionPort.mAnimators = new ArrayList();
        localTransitionPort.mStartValues = new TransitionValuesMaps();
        localTransitionPort.mEndValues = new TransitionValuesMaps();
        return localTransitionPort;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException2) {}
      localCloneNotSupportedException1 = localCloneNotSupportedException1;
      return null;
    }
  }
  
  public Animator createAnimator(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    return null;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void createAnimators(ViewGroup paramViewGroup, TransitionValuesMaps paramTransitionValuesMaps1, TransitionValuesMaps paramTransitionValuesMaps2)
  {
    ArrayMap localArrayMap = new ArrayMap(paramTransitionValuesMaps2.viewValues);
    SparseArray localSparseArray = new SparseArray(paramTransitionValuesMaps2.idValues.size());
    int i = 0;
    while (i < paramTransitionValuesMaps2.idValues.size())
    {
      localSparseArray.put(paramTransitionValuesMaps2.idValues.keyAt(i), paramTransitionValuesMaps2.idValues.valueAt(i));
      i += 1;
    }
    Object localObject5 = new LongSparseArray(paramTransitionValuesMaps2.itemIdValues.size());
    i = 0;
    while (i < paramTransitionValuesMaps2.itemIdValues.size())
    {
      ((LongSparseArray)localObject5).put(paramTransitionValuesMaps2.itemIdValues.keyAt(i), paramTransitionValuesMaps2.itemIdValues.valueAt(i));
      i += 1;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator1 = paramTransitionValuesMaps1.viewValues.keySet().iterator();
    View localView;
    Object localObject3;
    label248:
    Object localObject2;
    if (localIterator1.hasNext())
    {
      localView = (View)localIterator1.next();
      i = 0;
      if ((localView.getParent() instanceof ListView)) {
        i = 1;
      }
      if (i == 0)
      {
        i = localView.getId();
        if (paramTransitionValuesMaps1.viewValues.get(localView) != null)
        {
          localObject3 = (TransitionValues)paramTransitionValuesMaps1.viewValues.get(localView);
          if (paramTransitionValuesMaps2.viewValues.get(localView) == null) {
            break label337;
          }
          localObject2 = (TransitionValues)paramTransitionValuesMaps2.viewValues.get(localView);
          localArrayMap.remove(localView);
        }
      }
    }
    for (;;)
    {
      label282:
      localSparseArray.remove(i);
      if (!isValidTarget(localView, i)) {
        break;
      }
      localArrayList1.add(localObject3);
      localArrayList2.add(localObject2);
      break;
      localObject3 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(i);
      break label248;
      label337:
      if (i != -1)
      {
        Object localObject4 = (TransitionValues)paramTransitionValuesMaps2.idValues.get(i);
        Object localObject1 = null;
        Iterator localIterator2 = localArrayMap.keySet().iterator();
        label372:
        if (localIterator2.hasNext())
        {
          localObject2 = (View)localIterator2.next();
          if (((View)localObject2).getId() != i) {
            break label1332;
          }
          localObject1 = localObject2;
        }
        label1121:
        label1281:
        label1290:
        label1307:
        label1320:
        label1332:
        for (;;)
        {
          break label372;
          localObject2 = localObject4;
          if (localObject1 == null) {
            break label282;
          }
          localArrayMap.remove(localObject1);
          localObject2 = localObject4;
          break label282;
          localObject1 = (ListView)localView.getParent();
          if (!((ListView)localObject1).getAdapter().hasStableIds()) {
            break;
          }
          long l = ((ListView)localObject1).getItemIdAtPosition(((ListView)localObject1).getPositionForView(localView));
          localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
          ((LongSparseArray)localObject5).remove(l);
          localArrayList1.add(localObject1);
          localArrayList2.add(null);
          break;
          int j = paramTransitionValuesMaps1.itemIdValues.size();
          i = 0;
          while (i < j)
          {
            l = paramTransitionValuesMaps1.itemIdValues.keyAt(i);
            if (isValidTarget(null, l))
            {
              localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
              localObject2 = (TransitionValues)paramTransitionValuesMaps2.itemIdValues.get(l);
              ((LongSparseArray)localObject5).remove(l);
              localArrayList1.add(localObject1);
              localArrayList2.add(localObject2);
            }
            i += 1;
          }
          localObject2 = localArrayMap.keySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (View)((Iterator)localObject2).next();
            i = ((View)localObject3).getId();
            if (isValidTarget((View)localObject3, i))
            {
              if (paramTransitionValuesMaps1.viewValues.get(localObject3) != null) {}
              for (localObject1 = (TransitionValues)paramTransitionValuesMaps1.viewValues.get(localObject3);; localObject1 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(i))
              {
                localObject3 = (TransitionValues)localArrayMap.get(localObject3);
                localSparseArray.remove(i);
                localArrayList1.add(localObject1);
                localArrayList2.add(localObject3);
                break;
              }
            }
          }
          j = localSparseArray.size();
          i = 0;
          int k;
          while (i < j)
          {
            k = localSparseArray.keyAt(i);
            if (isValidTarget(null, k))
            {
              localObject1 = (TransitionValues)paramTransitionValuesMaps1.idValues.get(k);
              localObject2 = (TransitionValues)localSparseArray.get(k);
              localArrayList1.add(localObject1);
              localArrayList2.add(localObject2);
            }
            i += 1;
          }
          j = ((LongSparseArray)localObject5).size();
          i = 0;
          while (i < j)
          {
            l = ((LongSparseArray)localObject5).keyAt(i);
            localObject1 = (TransitionValues)paramTransitionValuesMaps1.itemIdValues.get(l);
            localObject2 = (TransitionValues)((LongSparseArray)localObject5).get(l);
            localArrayList1.add(localObject1);
            localArrayList2.add(localObject2);
            i += 1;
          }
          localObject4 = getRunningAnimators();
          i = 0;
          if (i < localArrayList1.size())
          {
            localObject1 = (TransitionValues)localArrayList1.get(i);
            localObject2 = (TransitionValues)localArrayList2.get(i);
            if (((localObject1 != null) || (localObject2 != null)) && ((localObject1 == null) || (!((TransitionValues)localObject1).equals(localObject2))))
            {
              paramTransitionValuesMaps1 = createAnimator(paramViewGroup, (TransitionValues)localObject1, (TransitionValues)localObject2);
              if (paramTransitionValuesMaps1 != null)
              {
                if (localObject2 == null) {
                  break label1290;
                }
                localObject3 = ((TransitionValues)localObject2).view;
                localObject2 = getTransitionProperties();
                if ((localObject3 == null) || (localObject2 == null) || (localObject2.length <= 0)) {
                  break label1320;
                }
                localObject1 = new TransitionValues();
                ((TransitionValues)localObject1).view = ((View)localObject3);
                localObject5 = (TransitionValues)paramTransitionValuesMaps2.viewValues.get(localObject3);
                if (localObject5 != null)
                {
                  j = 0;
                  while (j < localObject2.length)
                  {
                    ((TransitionValues)localObject1).values.put(localObject2[j], ((TransitionValues)localObject5).values.get(localObject2[j]));
                    j += 1;
                  }
                }
                k = ((ArrayMap)localObject4).size();
                j = 0;
                if (j >= k) {
                  break label1307;
                }
                localObject2 = (AnimationInfo)((ArrayMap)localObject4).get((Animator)((ArrayMap)localObject4).keyAt(j));
                if ((((AnimationInfo)localObject2).values == null) || (((AnimationInfo)localObject2).view != localObject3) || (((((AnimationInfo)localObject2).name != null) || (getName() != null)) && ((!((AnimationInfo)localObject2).name.equals(getName())) || (!((AnimationInfo)localObject2).values.equals(localObject1))))) {
                  break label1281;
                }
                localObject2 = null;
                paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject1;
                localObject1 = localObject2;
              }
            }
          }
          for (;;)
          {
            localObject2 = localObject3;
            localObject3 = localObject1;
            localObject1 = paramTransitionValuesMaps1;
            for (;;)
            {
              if (localObject3 != null)
              {
                ((ArrayMap)localObject4).put(localObject3, new AnimationInfo((View)localObject2, getName(), WindowIdPort.getWindowId(paramViewGroup), (TransitionValues)localObject1));
                this.mAnimators.add(localObject3);
              }
              i += 1;
              break;
              j += 1;
              break label1121;
              localObject2 = ((TransitionValues)localObject1).view;
              localObject1 = null;
              localObject3 = paramTransitionValuesMaps1;
            }
            return;
            localObject2 = paramTransitionValuesMaps1;
            paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject1;
            localObject1 = localObject2;
            continue;
            localObject2 = null;
            localObject1 = paramTransitionValuesMaps1;
            paramTransitionValuesMaps1 = (TransitionValuesMaps)localObject2;
          }
        }
      }
      localObject2 = null;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void end()
  {
    int k = 0;
    this.mNumInstances -= 1;
    if (this.mNumInstances == 0)
    {
      Object localObject;
      int j;
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        localObject = (ArrayList)this.mListeners.clone();
        j = ((ArrayList)localObject).size();
        i = 0;
        while (i < j)
        {
          ((TransitionListener)((ArrayList)localObject).get(i)).onTransitionEnd(this);
          i += 1;
        }
      }
      int i = 0;
      for (;;)
      {
        j = k;
        if (i >= this.mStartValues.itemIdValues.size()) {
          break;
        }
        localObject = ((TransitionValues)this.mStartValues.itemIdValues.valueAt(i)).view;
        i += 1;
      }
      while (j < this.mEndValues.itemIdValues.size())
      {
        localObject = ((TransitionValues)this.mEndValues.itemIdValues.valueAt(j)).view;
        j += 1;
      }
      this.mEnded = true;
    }
  }
  
  public TransitionPort excludeChildren(int paramInt, boolean paramBoolean)
  {
    this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, paramInt, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeChildren(View paramView, boolean paramBoolean)
  {
    this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, paramView, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeChildren(Class paramClass, boolean paramBoolean)
  {
    this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, paramClass, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(int paramInt, boolean paramBoolean)
  {
    this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, paramInt, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(View paramView, boolean paramBoolean)
  {
    this.mTargetExcludes = excludeView(this.mTargetExcludes, paramView, paramBoolean);
    return this;
  }
  
  public TransitionPort excludeTarget(Class paramClass, boolean paramBoolean)
  {
    this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, paramClass, paramBoolean);
    return this;
  }
  
  public long getDuration()
  {
    return this.mDuration;
  }
  
  public TimeInterpolator getInterpolator()
  {
    return this.mInterpolator;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public long getStartDelay()
  {
    return this.mStartDelay;
  }
  
  public List<Integer> getTargetIds()
  {
    return this.mTargetIds;
  }
  
  public List<View> getTargets()
  {
    return this.mTargets;
  }
  
  public String[] getTransitionProperties()
  {
    return null;
  }
  
  public TransitionValues getTransitionValues(View paramView, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getTransitionValues(paramView, paramBoolean);
      return (TransitionValues)localObject;
    }
    if (paramBoolean) {}
    for (TransitionValuesMaps localTransitionValuesMaps = this.mStartValues;; localTransitionValuesMaps = this.mEndValues)
    {
      TransitionValues localTransitionValues = (TransitionValues)localTransitionValuesMaps.viewValues.get(paramView);
      localObject = localTransitionValues;
      if (localTransitionValues != null) {
        break;
      }
      int i = paramView.getId();
      if (i >= 0) {
        localTransitionValues = (TransitionValues)localTransitionValuesMaps.idValues.get(i);
      }
      localObject = localTransitionValues;
      if (localTransitionValues != null) {
        break;
      }
      localObject = localTransitionValues;
      if (!(paramView.getParent() instanceof ListView)) {
        break;
      }
      localObject = (ListView)paramView.getParent();
      long l = ((ListView)localObject).getItemIdAtPosition(((ListView)localObject).getPositionForView(paramView));
      return (TransitionValues)localTransitionValuesMaps.itemIdValues.get(l);
    }
  }
  
  boolean isValidTarget(View paramView, long paramLong)
  {
    if ((this.mTargetIdExcludes != null) && (this.mTargetIdExcludes.contains(Integer.valueOf((int)paramLong)))) {}
    for (;;)
    {
      return false;
      if ((this.mTargetExcludes == null) || (!this.mTargetExcludes.contains(paramView)))
      {
        int i;
        if ((this.mTargetTypeExcludes != null) && (paramView != null))
        {
          int j = this.mTargetTypeExcludes.size();
          i = 0;
          for (;;)
          {
            if (i >= j) {
              break label100;
            }
            if (((Class)this.mTargetTypeExcludes.get(i)).isInstance(paramView)) {
              break;
            }
            i += 1;
          }
        }
        label100:
        if ((this.mTargetIds.size() == 0) && (this.mTargets.size() == 0)) {
          return true;
        }
        if (this.mTargetIds.size() > 0)
        {
          i = 0;
          while (i < this.mTargetIds.size())
          {
            if (((Integer)this.mTargetIds.get(i)).intValue() == paramLong) {
              return true;
            }
            i += 1;
          }
        }
        if ((paramView != null) && (this.mTargets.size() > 0))
        {
          i = 0;
          while (i < this.mTargets.size())
          {
            if (this.mTargets.get(i) == paramView) {
              return true;
            }
            i += 1;
          }
        }
      }
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void pause(View paramView)
  {
    if (!this.mEnded)
    {
      ArrayMap localArrayMap = getRunningAnimators();
      int i = localArrayMap.size();
      paramView = WindowIdPort.getWindowId(paramView);
      i -= 1;
      while (i >= 0)
      {
        AnimationInfo localAnimationInfo = (AnimationInfo)localArrayMap.valueAt(i);
        if ((localAnimationInfo.view != null) && (paramView.equals(localAnimationInfo.windowId))) {
          ((Animator)localArrayMap.keyAt(i)).cancel();
        }
        i -= 1;
      }
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        paramView = (ArrayList)this.mListeners.clone();
        int j = paramView.size();
        i = 0;
        while (i < j)
        {
          ((TransitionListener)paramView.get(i)).onTransitionPause(this);
          i += 1;
        }
      }
      this.mPaused = true;
    }
  }
  
  void playTransition(ViewGroup paramViewGroup)
  {
    ArrayMap localArrayMap = getRunningAnimators();
    int i = localArrayMap.size() - 1;
    Animator localAnimator;
    Object localObject1;
    TransitionValues localTransitionValues;
    Object localObject2;
    if (i >= 0)
    {
      localAnimator = (Animator)localArrayMap.keyAt(i);
      if (localAnimator != null)
      {
        localObject1 = (AnimationInfo)localArrayMap.get(localAnimator);
        if ((localObject1 != null) && (((AnimationInfo)localObject1).view != null) && (((AnimationInfo)localObject1).view.getContext() == paramViewGroup.getContext()))
        {
          localTransitionValues = ((AnimationInfo)localObject1).values;
          localObject2 = ((AnimationInfo)localObject1).view;
          if (this.mEndValues.viewValues == null) {
            break label270;
          }
          localObject1 = (TransitionValues)this.mEndValues.viewValues.get(localObject2);
          label114:
          if (localObject1 != null) {
            break label310;
          }
          localObject1 = (TransitionValues)this.mEndValues.idValues.get(((View)localObject2).getId());
        }
      }
    }
    label270:
    label276:
    label310:
    for (;;)
    {
      if ((localTransitionValues != null) && (localObject1 != null))
      {
        localObject2 = localTransitionValues.values.keySet().iterator();
        Object localObject4;
        Object localObject3;
        do
        {
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
          localObject4 = (String)((Iterator)localObject2).next();
          localObject3 = localTransitionValues.values.get(localObject4);
          localObject4 = ((TransitionValues)localObject1).values.get(localObject4);
        } while ((localObject3 == null) || (localObject4 == null) || (localObject3.equals(localObject4)));
      }
      for (int j = 1;; j = 0)
      {
        if (j != 0)
        {
          if ((!localAnimator.isRunning()) && (!localAnimator.isStarted())) {
            break label276;
          }
          localAnimator.cancel();
        }
        for (;;)
        {
          i -= 1;
          break;
          localObject1 = null;
          break label114;
          localArrayMap.remove(localAnimator);
        }
        createAnimators(paramViewGroup, this.mStartValues, this.mEndValues);
        runAnimators();
        return;
      }
    }
  }
  
  public TransitionPort removeListener(TransitionListener paramTransitionListener)
  {
    if (this.mListeners == null) {}
    do
    {
      return this;
      this.mListeners.remove(paramTransitionListener);
    } while (this.mListeners.size() != 0);
    this.mListeners = null;
    return this;
  }
  
  public TransitionPort removeTarget(int paramInt)
  {
    if (paramInt > 0) {
      this.mTargetIds.remove(Integer.valueOf(paramInt));
    }
    return this;
  }
  
  public TransitionPort removeTarget(View paramView)
  {
    if (paramView != null) {
      this.mTargets.remove(paramView);
    }
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void resume(View paramView)
  {
    if (this.mPaused)
    {
      if (!this.mEnded)
      {
        ArrayMap localArrayMap = getRunningAnimators();
        int i = localArrayMap.size();
        paramView = WindowIdPort.getWindowId(paramView);
        i -= 1;
        while (i >= 0)
        {
          AnimationInfo localAnimationInfo = (AnimationInfo)localArrayMap.valueAt(i);
          if ((localAnimationInfo.view != null) && (paramView.equals(localAnimationInfo.windowId))) {
            ((Animator)localArrayMap.keyAt(i)).end();
          }
          i -= 1;
        }
        if ((this.mListeners != null) && (this.mListeners.size() > 0))
        {
          paramView = (ArrayList)this.mListeners.clone();
          int j = paramView.size();
          i = 0;
          while (i < j)
          {
            ((TransitionListener)paramView.get(i)).onTransitionResume(this);
            i += 1;
          }
        }
      }
      this.mPaused = false;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void runAnimators()
  {
    start();
    ArrayMap localArrayMap = getRunningAnimators();
    Iterator localIterator = this.mAnimators.iterator();
    while (localIterator.hasNext())
    {
      Animator localAnimator = (Animator)localIterator.next();
      if (localArrayMap.containsKey(localAnimator))
      {
        start();
        runAnimator(localAnimator, localArrayMap);
      }
    }
    this.mAnimators.clear();
    end();
  }
  
  void setCanRemoveViews(boolean paramBoolean)
  {
    this.mCanRemoveViews = paramBoolean;
  }
  
  public TransitionPort setDuration(long paramLong)
  {
    this.mDuration = paramLong;
    return this;
  }
  
  public TransitionPort setInterpolator(TimeInterpolator paramTimeInterpolator)
  {
    this.mInterpolator = paramTimeInterpolator;
    return this;
  }
  
  TransitionPort setSceneRoot(ViewGroup paramViewGroup)
  {
    this.mSceneRoot = paramViewGroup;
    return this;
  }
  
  public TransitionPort setStartDelay(long paramLong)
  {
    this.mStartDelay = paramLong;
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void start()
  {
    if (this.mNumInstances == 0)
    {
      if ((this.mListeners != null) && (this.mListeners.size() > 0))
      {
        ArrayList localArrayList = (ArrayList)this.mListeners.clone();
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          ((TransitionListener)localArrayList.get(i)).onTransitionStart(this);
          i += 1;
        }
      }
      this.mEnded = false;
    }
    this.mNumInstances += 1;
  }
  
  public String toString()
  {
    return toString("");
  }
  
  String toString(String paramString)
  {
    int j = 0;
    String str = paramString + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
    paramString = str;
    if (this.mDuration != -1L) {
      paramString = str + "dur(" + this.mDuration + ") ";
    }
    str = paramString;
    if (this.mStartDelay != -1L) {
      str = paramString + "dly(" + this.mStartDelay + ") ";
    }
    paramString = str;
    if (this.mInterpolator != null) {
      paramString = str + "interp(" + this.mInterpolator + ") ";
    }
    if (this.mTargetIds.size() <= 0)
    {
      str = paramString;
      if (this.mTargets.size() <= 0) {}
    }
    else
    {
      paramString = paramString + "tgts(";
      int i;
      if (this.mTargetIds.size() > 0)
      {
        i = 0;
        for (;;)
        {
          str = paramString;
          if (i >= this.mTargetIds.size()) {
            break;
          }
          str = paramString;
          if (i > 0) {
            str = paramString + ", ";
          }
          paramString = str + this.mTargetIds.get(i);
          i += 1;
        }
      }
      str = paramString;
      paramString = str;
      if (this.mTargets.size() > 0)
      {
        i = j;
        for (;;)
        {
          paramString = str;
          if (i >= this.mTargets.size()) {
            break;
          }
          paramString = str;
          if (i > 0) {
            paramString = str + ", ";
          }
          str = paramString + this.mTargets.get(i);
          i += 1;
        }
      }
      str = paramString + ")";
    }
    return str;
  }
  
  private static class AnimationInfo
  {
    String name;
    TransitionValues values;
    View view;
    WindowIdPort windowId;
    
    AnimationInfo(View paramView, String paramString, WindowIdPort paramWindowIdPort, TransitionValues paramTransitionValues)
    {
      this.view = paramView;
      this.name = paramString;
      this.values = paramTransitionValues;
      this.windowId = paramWindowIdPort;
    }
  }
  
  private static class ArrayListManager
  {
    static <T> ArrayList<T> add(ArrayList<T> paramArrayList, T paramT)
    {
      Object localObject = paramArrayList;
      if (paramArrayList == null) {
        localObject = new ArrayList();
      }
      if (!((ArrayList)localObject).contains(paramT)) {
        ((ArrayList)localObject).add(paramT);
      }
      return (ArrayList<T>)localObject;
    }
    
    static <T> ArrayList<T> remove(ArrayList<T> paramArrayList, T paramT)
    {
      ArrayList<T> localArrayList = paramArrayList;
      if (paramArrayList != null)
      {
        paramArrayList.remove(paramT);
        localArrayList = paramArrayList;
        if (paramArrayList.isEmpty()) {
          localArrayList = null;
        }
      }
      return localArrayList;
    }
  }
  
  public static abstract interface TransitionListener
  {
    public abstract void onTransitionCancel(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionEnd(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionPause(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionResume(TransitionPort paramTransitionPort);
    
    public abstract void onTransitionStart(TransitionPort paramTransitionPort);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class TransitionListenerAdapter
    implements TransitionPort.TransitionListener
  {
    public void onTransitionCancel(TransitionPort paramTransitionPort) {}
    
    public void onTransitionEnd(TransitionPort paramTransitionPort) {}
    
    public void onTransitionPause(TransitionPort paramTransitionPort) {}
    
    public void onTransitionResume(TransitionPort paramTransitionPort) {}
    
    public void onTransitionStart(TransitionPort paramTransitionPort) {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */