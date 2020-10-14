package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(14)
@RequiresApi(14)
class TransitionManagerPort
{
  private static final String[] EMPTY_STRINGS = new String[0];
  private static String LOG_TAG = "TransitionManager";
  private static TransitionPort sDefaultTransition = new AutoTransitionPort();
  static ArrayList<ViewGroup> sPendingTransitions = new ArrayList();
  private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<TransitionPort>>>> sRunningTransitions = new ThreadLocal();
  ArrayMap<String, ArrayMap<ScenePort, TransitionPort>> mNameSceneTransitions = new ArrayMap();
  ArrayMap<ScenePort, ArrayMap<String, TransitionPort>> mSceneNameTransitions = new ArrayMap();
  ArrayMap<ScenePort, ArrayMap<ScenePort, TransitionPort>> mScenePairTransitions = new ArrayMap();
  ArrayMap<ScenePort, TransitionPort> mSceneTransitions = new ArrayMap();
  
  public static void beginDelayedTransition(ViewGroup paramViewGroup)
  {
    beginDelayedTransition(paramViewGroup, null);
  }
  
  public static void beginDelayedTransition(ViewGroup paramViewGroup, TransitionPort paramTransitionPort)
  {
    if ((!sPendingTransitions.contains(paramViewGroup)) && (ViewCompat.isLaidOut(paramViewGroup)))
    {
      sPendingTransitions.add(paramViewGroup);
      TransitionPort localTransitionPort = paramTransitionPort;
      if (paramTransitionPort == null) {
        localTransitionPort = sDefaultTransition;
      }
      paramTransitionPort = localTransitionPort.clone();
      sceneChangeSetup(paramViewGroup, paramTransitionPort);
      ScenePort.setCurrentScene(paramViewGroup, null);
      sceneChangeRunTransition(paramViewGroup, paramTransitionPort);
    }
  }
  
  private static void changeScene(ScenePort paramScenePort, TransitionPort paramTransitionPort)
  {
    ViewGroup localViewGroup = paramScenePort.getSceneRoot();
    TransitionPort localTransitionPort = null;
    if (paramTransitionPort != null)
    {
      localTransitionPort = paramTransitionPort.clone();
      localTransitionPort.setSceneRoot(localViewGroup);
    }
    paramTransitionPort = ScenePort.getCurrentScene(localViewGroup);
    if ((paramTransitionPort != null) && (paramTransitionPort.isCreatedFromLayoutResource())) {
      localTransitionPort.setCanRemoveViews(true);
    }
    sceneChangeSetup(localViewGroup, localTransitionPort);
    paramScenePort.enter();
    sceneChangeRunTransition(localViewGroup, localTransitionPort);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static TransitionPort getDefaultTransition()
  {
    return sDefaultTransition;
  }
  
  static ArrayMap<ViewGroup, ArrayList<TransitionPort>> getRunningTransitions()
  {
    WeakReference localWeakReference2 = (WeakReference)sRunningTransitions.get();
    WeakReference localWeakReference1;
    if (localWeakReference2 != null)
    {
      localWeakReference1 = localWeakReference2;
      if (localWeakReference2.get() != null) {}
    }
    else
    {
      localWeakReference1 = new WeakReference(new ArrayMap());
      sRunningTransitions.set(localWeakReference1);
    }
    return (ArrayMap)localWeakReference1.get();
  }
  
  private TransitionPort getTransition(ScenePort paramScenePort)
  {
    Object localObject = paramScenePort.getSceneRoot();
    if (localObject != null)
    {
      localObject = ScenePort.getCurrentScene((View)localObject);
      if (localObject != null)
      {
        ArrayMap localArrayMap = (ArrayMap)this.mScenePairTransitions.get(paramScenePort);
        if (localArrayMap != null)
        {
          localObject = (TransitionPort)localArrayMap.get(localObject);
          if (localObject != null) {
            paramScenePort = (ScenePort)localObject;
          }
        }
      }
    }
    do
    {
      return paramScenePort;
      localObject = (TransitionPort)this.mSceneTransitions.get(paramScenePort);
      paramScenePort = (ScenePort)localObject;
    } while (localObject != null);
    return sDefaultTransition;
  }
  
  public static void go(ScenePort paramScenePort)
  {
    changeScene(paramScenePort, sDefaultTransition);
  }
  
  public static void go(ScenePort paramScenePort, TransitionPort paramTransitionPort)
  {
    changeScene(paramScenePort, paramTransitionPort);
  }
  
  private static void sceneChangeRunTransition(ViewGroup paramViewGroup, TransitionPort paramTransitionPort)
  {
    if ((paramTransitionPort != null) && (paramViewGroup != null))
    {
      paramTransitionPort = new MultiListener(paramTransitionPort, paramViewGroup);
      paramViewGroup.addOnAttachStateChangeListener(paramTransitionPort);
      paramViewGroup.getViewTreeObserver().addOnPreDrawListener(paramTransitionPort);
    }
  }
  
  private static void sceneChangeSetup(ViewGroup paramViewGroup, TransitionPort paramTransitionPort)
  {
    Object localObject = (ArrayList)getRunningTransitions().get(paramViewGroup);
    if ((localObject != null) && (((ArrayList)localObject).size() > 0))
    {
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((TransitionPort)((Iterator)localObject).next()).pause(paramViewGroup);
      }
    }
    if (paramTransitionPort != null) {
      paramTransitionPort.captureValues(paramViewGroup, true);
    }
    paramViewGroup = ScenePort.getCurrentScene(paramViewGroup);
    if (paramViewGroup != null) {
      paramViewGroup.exit();
    }
  }
  
  public TransitionPort getNamedTransition(ScenePort paramScenePort, String paramString)
  {
    paramScenePort = (ArrayMap)this.mSceneNameTransitions.get(paramScenePort);
    if (paramScenePort != null) {
      return (TransitionPort)paramScenePort.get(paramString);
    }
    return null;
  }
  
  public TransitionPort getNamedTransition(String paramString, ScenePort paramScenePort)
  {
    paramString = (ArrayMap)this.mNameSceneTransitions.get(paramString);
    if (paramString != null) {
      return (TransitionPort)paramString.get(paramScenePort);
    }
    return null;
  }
  
  public String[] getTargetSceneNames(ScenePort paramScenePort)
  {
    paramScenePort = (ArrayMap)this.mSceneNameTransitions.get(paramScenePort);
    if (paramScenePort == null) {
      return EMPTY_STRINGS;
    }
    int j = paramScenePort.size();
    String[] arrayOfString = new String[j];
    int i = 0;
    while (i < j)
    {
      arrayOfString[i] = ((String)paramScenePort.keyAt(i));
      i += 1;
    }
    return arrayOfString;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setDefaultTransition(TransitionPort paramTransitionPort)
  {
    sDefaultTransition = paramTransitionPort;
  }
  
  public void setTransition(ScenePort paramScenePort1, ScenePort paramScenePort2, TransitionPort paramTransitionPort)
  {
    ArrayMap localArrayMap2 = (ArrayMap)this.mScenePairTransitions.get(paramScenePort2);
    ArrayMap localArrayMap1 = localArrayMap2;
    if (localArrayMap2 == null)
    {
      localArrayMap1 = new ArrayMap();
      this.mScenePairTransitions.put(paramScenePort2, localArrayMap1);
    }
    localArrayMap1.put(paramScenePort1, paramTransitionPort);
  }
  
  public void setTransition(ScenePort paramScenePort, TransitionPort paramTransitionPort)
  {
    this.mSceneTransitions.put(paramScenePort, paramTransitionPort);
  }
  
  public void setTransition(ScenePort paramScenePort, String paramString, TransitionPort paramTransitionPort)
  {
    ArrayMap localArrayMap2 = (ArrayMap)this.mSceneNameTransitions.get(paramScenePort);
    ArrayMap localArrayMap1 = localArrayMap2;
    if (localArrayMap2 == null)
    {
      localArrayMap1 = new ArrayMap();
      this.mSceneNameTransitions.put(paramScenePort, localArrayMap1);
    }
    localArrayMap1.put(paramString, paramTransitionPort);
  }
  
  public void setTransition(String paramString, ScenePort paramScenePort, TransitionPort paramTransitionPort)
  {
    ArrayMap localArrayMap2 = (ArrayMap)this.mNameSceneTransitions.get(paramString);
    ArrayMap localArrayMap1 = localArrayMap2;
    if (localArrayMap2 == null)
    {
      localArrayMap1 = new ArrayMap();
      this.mNameSceneTransitions.put(paramString, localArrayMap1);
    }
    localArrayMap1.put(paramScenePort, paramTransitionPort);
  }
  
  public void transitionTo(ScenePort paramScenePort)
  {
    changeScene(paramScenePort, getTransition(paramScenePort));
  }
  
  private static class MultiListener
    implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener
  {
    ViewGroup mSceneRoot;
    TransitionPort mTransition;
    
    MultiListener(TransitionPort paramTransitionPort, ViewGroup paramViewGroup)
    {
      this.mTransition = paramTransitionPort;
      this.mSceneRoot = paramViewGroup;
    }
    
    private void removeListeners()
    {
      this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
      this.mSceneRoot.removeOnAttachStateChangeListener(this);
    }
    
    public boolean onPreDraw()
    {
      removeListeners();
      TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
      final ArrayMap localArrayMap = TransitionManagerPort.getRunningTransitions();
      ArrayList localArrayList = (ArrayList)localArrayMap.get(this.mSceneRoot);
      Object localObject;
      if (localArrayList == null)
      {
        localArrayList = new ArrayList();
        localArrayMap.put(this.mSceneRoot, localArrayList);
        localObject = null;
      }
      for (;;)
      {
        localArrayList.add(this.mTransition);
        this.mTransition.addListener(new TransitionPort.TransitionListenerAdapter()
        {
          public void onTransitionEnd(TransitionPort paramAnonymousTransitionPort)
          {
            ((ArrayList)localArrayMap.get(TransitionManagerPort.MultiListener.this.mSceneRoot)).remove(paramAnonymousTransitionPort);
          }
        });
        this.mTransition.captureValues(this.mSceneRoot, false);
        if (localObject != null)
        {
          localObject = ((ArrayList)localObject).iterator();
          for (;;)
          {
            if (((Iterator)localObject).hasNext())
            {
              ((TransitionPort)((Iterator)localObject).next()).resume(this.mSceneRoot);
              continue;
              if (localArrayList.size() <= 0) {
                break label162;
              }
              localObject = new ArrayList(localArrayList);
              break;
            }
          }
        }
        this.mTransition.playTransition(this.mSceneRoot);
        return true;
        label162:
        localObject = null;
      }
    }
    
    public void onViewAttachedToWindow(View paramView) {}
    
    public void onViewDetachedFromWindow(View paramView)
    {
      removeListeners();
      TransitionManagerPort.sPendingTransitions.remove(this.mSceneRoot);
      paramView = (ArrayList)TransitionManagerPort.getRunningTransitions().get(this.mSceneRoot);
      if ((paramView != null) && (paramView.size() > 0))
      {
        paramView = paramView.iterator();
        while (paramView.hasNext()) {
          ((TransitionPort)paramView.next()).resume(this.mSceneRoot);
        }
      }
      this.mTransition.clearValues(true);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionManagerPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */