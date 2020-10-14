package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.v4.os.BuildCompat;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl
  extends FragmentManager
  implements LayoutInflaterFactory
{
  static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
  static final Interpolator ACCELERATE_QUINT;
  static final int ANIM_DUR = 220;
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  static boolean DEBUG = false;
  static final Interpolator DECELERATE_CUBIC;
  static final Interpolator DECELERATE_QUINT;
  static final boolean HONEYCOMB;
  static final String TAG = "FragmentManager";
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  static final String TARGET_STATE_TAG = "android:target_state";
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  static final String VIEW_STATE_TAG = "android:view_state";
  static Field sAnimationListenerField;
  ArrayList<Fragment> mActive;
  ArrayList<Fragment> mAdded;
  ArrayList<Integer> mAvailBackStackIndices;
  ArrayList<Integer> mAvailIndices;
  ArrayList<BackStackRecord> mBackStack;
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  ArrayList<BackStackRecord> mBackStackIndices;
  FragmentContainer mContainer;
  ArrayList<Fragment> mCreatedMenus;
  int mCurState = 0;
  boolean mDestroyed;
  Runnable mExecCommit = new Runnable()
  {
    public void run()
    {
      FragmentManagerImpl.this.execPendingActions();
    }
  };
  boolean mExecutingActions;
  boolean mHavePendingDeferredStart;
  FragmentHostCallback mHost;
  private CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks;
  boolean mNeedMenuInvalidate;
  String mNoTransactionsBecause;
  Fragment mParent;
  ArrayList<OpGenerator> mPendingActions;
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  SparseArray<Parcelable> mStateArray = null;
  Bundle mStateBundle = null;
  boolean mStateSaved;
  Runnable[] mTmpActions;
  ArrayList<Fragment> mTmpAddedFragments;
  ArrayList<Boolean> mTmpIsPop;
  ArrayList<BackStackRecord> mTmpRecords;
  
  static
  {
    boolean bool = false;
    DEBUG = false;
    if (Build.VERSION.SDK_INT >= 11) {
      bool = true;
    }
    HONEYCOMB = bool;
    sAnimationListenerField = null;
    DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
    DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
  }
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet)
  {
    if (this.mCurState < 1) {
      return;
    }
    int k = Math.min(this.mCurState, 4);
    if (this.mAdded == null) {}
    for (int i = 0;; i = this.mAdded.size())
    {
      int j = 0;
      while (j < i)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(j);
        if (localFragment.mState < k)
        {
          moveToState(localFragment, k, localFragment.getNextAnim(), localFragment.getNextTransition(), false);
          if ((localFragment.mView != null) && (!localFragment.mHidden) && (localFragment.mIsNewlyAdded)) {
            paramArraySet.add(localFragment);
          }
        }
        j += 1;
      }
      break;
    }
  }
  
  private void checkStateLoss()
  {
    if (this.mStateSaved) {
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
    if (this.mNoTransactionsBecause != null) {
      throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
    }
  }
  
  private void cleanupExec()
  {
    this.mExecutingActions = false;
    this.mTmpIsPop.clear();
    this.mTmpRecords.clear();
  }
  
  private void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Object localObject = new ArrayList(1);
    ArrayList localArrayList = new ArrayList(1);
    ((ArrayList)localObject).add(paramBackStackRecord);
    localArrayList.add(Boolean.valueOf(paramBoolean1));
    executeOps((ArrayList)localObject, localArrayList, 0, 1);
    if (paramBoolean2) {
      FragmentTransition.startTransitions(this, (ArrayList)localObject, localArrayList, 0, 1, true);
    }
    if (paramBoolean3) {
      moveToState(this.mCurState, true);
    }
    if (this.mActive != null)
    {
      int j = this.mActive.size();
      int i = 0;
      if (i < j)
      {
        localObject = (Fragment)this.mActive.get(i);
        if ((localObject != null) && (((Fragment)localObject).mView != null) && (((Fragment)localObject).mIsNewlyAdded) && (paramBackStackRecord.interactsWith(((Fragment)localObject).mContainerId)))
        {
          if ((Build.VERSION.SDK_INT >= 11) && (((Fragment)localObject).mPostponedAlpha > 0.0F)) {
            ((Fragment)localObject).mView.setAlpha(((Fragment)localObject).mPostponedAlpha);
          }
          if (!paramBoolean3) {
            break label199;
          }
          ((Fragment)localObject).mPostponedAlpha = 0.0F;
        }
        for (;;)
        {
          i += 1;
          break;
          label199:
          ((Fragment)localObject).mPostponedAlpha = -1.0F;
          ((Fragment)localObject).mIsNewlyAdded = false;
        }
      }
    }
  }
  
  private void endAnimatingAwayFragments()
  {
    if (this.mActive == null) {}
    for (int i = 0;; i = this.mActive.size())
    {
      int j = 0;
      while (j < i)
      {
        Fragment localFragment = (Fragment)this.mActive.get(j);
        if ((localFragment != null) && (localFragment.getAnimatingAway() != null))
        {
          int k = localFragment.getStateAfterAnimating();
          Object localObject = localFragment.getAnimatingAway();
          localFragment.setAnimatingAway(null);
          localObject = ((View)localObject).getAnimation();
          if (localObject != null) {
            ((Animation)localObject).cancel();
          }
          moveToState(localFragment, k, 0, 0, false);
        }
        j += 1;
      }
    }
  }
  
  private void ensureExecReady(boolean paramBoolean)
  {
    if (this.mExecutingActions) {
      throw new IllegalStateException("FragmentManager is already executing transactions");
    }
    if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
      throw new IllegalStateException("Must be called from main thread of fragment host");
    }
    if (!paramBoolean) {
      checkStateLoss();
    }
    if (this.mTmpRecords == null)
    {
      this.mTmpRecords = new ArrayList();
      this.mTmpIsPop = new ArrayList();
    }
    this.mExecutingActions = true;
    try
    {
      executePostponedTransaction(null, null);
      return;
    }
    finally
    {
      this.mExecutingActions = false;
    }
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(paramInt1);
      boolean bool;
      if (((Boolean)paramArrayList1.get(paramInt1)).booleanValue())
      {
        localBackStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 == paramInt2 - 1)
        {
          bool = true;
          label45:
          localBackStackRecord.executePopOps(bool);
        }
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        bool = false;
        break label45;
        localBackStackRecord.bumpBackStackNesting(1);
        localBackStackRecord.executeOps();
      }
    }
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2)
  {
    boolean bool = ((BackStackRecord)paramArrayList.get(paramInt1)).mAllowOptimization;
    int j;
    label56:
    Object localObject;
    if (this.mTmpAddedFragments == null)
    {
      this.mTmpAddedFragments = new ArrayList();
      if (this.mAdded != null) {
        this.mTmpAddedFragments.addAll(this.mAdded);
      }
      j = paramInt1;
      i = 0;
      if (j >= paramInt2) {
        break label151;
      }
      localObject = (BackStackRecord)paramArrayList.get(j);
      if (((Boolean)paramArrayList1.get(j)).booleanValue()) {
        break label133;
      }
      ((BackStackRecord)localObject).expandReplaceOps(this.mTmpAddedFragments);
      label98:
      if ((i == 0) && (!((BackStackRecord)localObject).mAddToBackStack)) {
        break label145;
      }
    }
    label133:
    label145:
    for (int i = 1;; i = 0)
    {
      j += 1;
      break label56;
      this.mTmpAddedFragments.clear();
      break;
      ((BackStackRecord)localObject).trackAddedFragmentsInPop(this.mTmpAddedFragments);
      break label98;
    }
    label151:
    this.mTmpAddedFragments.clear();
    if (!bool) {
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, paramInt2, false);
    }
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    if (bool)
    {
      localObject = new ArraySet();
      addAddedFragments((ArraySet)localObject);
      j = postponePostponableTransactions(paramArrayList, paramArrayList1, paramInt1, paramInt2, (ArraySet)localObject);
      makeRemovedFragmentsInvisible((ArraySet)localObject);
    }
    for (;;)
    {
      int k = paramInt1;
      if (j != paramInt1)
      {
        k = paramInt1;
        if (bool)
        {
          FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, paramInt1, j, true);
          moveToState(this.mCurState, true);
          k = paramInt1;
        }
      }
      while (k < paramInt2)
      {
        localObject = (BackStackRecord)paramArrayList.get(k);
        if ((((Boolean)paramArrayList1.get(k)).booleanValue()) && (((BackStackRecord)localObject).mIndex >= 0))
        {
          freeBackStackIndex(((BackStackRecord)localObject).mIndex);
          ((BackStackRecord)localObject).mIndex = -1;
        }
        k += 1;
      }
      if (i != 0) {
        reportBackStackChanged();
      }
      return;
      j = paramInt2;
    }
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    int i;
    int j;
    label12:
    StartEnterTransitionListener localStartEnterTransitionListener;
    int k;
    if (this.mPostponedTransactions == null)
    {
      i = 0;
      j = 0;
      if (j >= i) {
        return;
      }
      localStartEnterTransitionListener = (StartEnterTransitionListener)this.mPostponedTransactions.get(j);
      if ((paramArrayList == null) || (localStartEnterTransitionListener.mIsBack)) {
        break label101;
      }
      k = paramArrayList.indexOf(localStartEnterTransitionListener.mRecord);
      if ((k == -1) || (!((Boolean)paramArrayList1.get(k)).booleanValue())) {
        break label101;
      }
      localStartEnterTransitionListener.cancelTransaction();
    }
    for (;;)
    {
      j += 1;
      break label12;
      i = this.mPostponedTransactions.size();
      break;
      label101:
      int m;
      if (!localStartEnterTransitionListener.isReady())
      {
        m = j;
        k = i;
        if (paramArrayList != null)
        {
          m = j;
          k = i;
          if (!localStartEnterTransitionListener.mRecord.interactsWith(paramArrayList, 0, paramArrayList.size())) {}
        }
      }
      else
      {
        this.mPostponedTransactions.remove(j);
        m = j - 1;
        k = i - 1;
        if ((paramArrayList != null) && (!localStartEnterTransitionListener.mIsBack))
        {
          i = paramArrayList.indexOf(localStartEnterTransitionListener.mRecord);
          if ((i != -1) && (((Boolean)paramArrayList1.get(i)).booleanValue()))
          {
            localStartEnterTransitionListener.cancelTransaction();
            j = m;
            i = k;
            continue;
          }
        }
        localStartEnterTransitionListener.completeTransaction();
      }
      j = m;
      i = k;
    }
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment)
  {
    ViewGroup localViewGroup = paramFragment.mContainer;
    Object localObject = paramFragment.mView;
    if ((localViewGroup == null) || (localObject == null))
    {
      paramFragment = null;
      return paramFragment;
    }
    int i = this.mAdded.indexOf(paramFragment) - 1;
    for (;;)
    {
      if (i < 0) {
        break label76;
      }
      localObject = (Fragment)this.mAdded.get(i);
      if (((Fragment)localObject).mContainer == localViewGroup)
      {
        paramFragment = (Fragment)localObject;
        if (((Fragment)localObject).mView != null) {
          break;
        }
      }
      i -= 1;
    }
    label76:
    return null;
  }
  
  private void forcePostponedTransactions()
  {
    if (this.mPostponedTransactions != null) {
      while (!this.mPostponedTransactions.isEmpty()) {
        ((StartEnterTransitionListener)this.mPostponedTransactions.remove(0)).completeTransaction();
      }
    }
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    try
    {
      if ((this.mPendingActions == null) || (this.mPendingActions.size() == 0)) {
        return false;
      }
      int j = this.mPendingActions.size();
      int i = 0;
      while (i < j)
      {
        ((OpGenerator)this.mPendingActions.get(i)).generateOps(paramArrayList, paramArrayList1);
        i += 1;
      }
      this.mPendingActions.clear();
      this.mHost.getHandler().removeCallbacks(this.mExecCommit);
      if (j > 0) {
        return true;
      }
    }
    finally {}
    return false;
  }
  
  static Animation makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2)
  {
    paramContext = new AlphaAnimation(paramFloat1, paramFloat2);
    paramContext.setInterpolator(DECELERATE_CUBIC);
    paramContext.setDuration(220L);
    return paramContext;
  }
  
  static Animation makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramContext = new AnimationSet(false);
    Object localObject = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    ((ScaleAnimation)localObject).setInterpolator(DECELERATE_QUINT);
    ((ScaleAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    localObject = new AlphaAnimation(paramFloat3, paramFloat4);
    ((AlphaAnimation)localObject).setInterpolator(DECELERATE_CUBIC);
    ((AlphaAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    return paramContext;
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet)
  {
    int j = paramArraySet.size();
    int i = 0;
    if (i < j)
    {
      Fragment localFragment = (Fragment)paramArraySet.valueAt(i);
      View localView;
      if (!localFragment.mAdded)
      {
        localView = localFragment.getView();
        if (Build.VERSION.SDK_INT >= 11) {
          break label61;
        }
        localFragment.getView().setVisibility(4);
      }
      for (;;)
      {
        i += 1;
        break;
        label61:
        localFragment.mPostponedAlpha = localView.getAlpha();
        localView.setAlpha(0.0F);
      }
    }
  }
  
  static boolean modifiesAlpha(Animation paramAnimation)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramAnimation instanceof AlphaAnimation)) {
      bool1 = true;
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (!(paramAnimation instanceof AnimationSet));
    paramAnimation = ((AnimationSet)paramAnimation).getAnimations();
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= paramAnimation.size()) {
        break;
      }
      if ((paramAnimation.get(i) instanceof AlphaAnimation)) {
        return true;
      }
      i += 1;
    }
  }
  
  private void optimizeAndExecuteOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    int i = 0;
    if ((paramArrayList == null) || (paramArrayList.isEmpty())) {
      return;
    }
    if ((paramArrayList1 == null) || (paramArrayList.size() != paramArrayList1.size())) {
      throw new IllegalStateException("Internal error with the back stack records");
    }
    executePostponedTransaction(paramArrayList, paramArrayList1);
    int m = paramArrayList.size();
    int j = 0;
    label55:
    if (i < m)
    {
      if (((BackStackRecord)paramArrayList.get(i)).mAllowOptimization) {
        break label220;
      }
      if (j != i) {
        executeOpsTogether(paramArrayList, paramArrayList1, j, i);
      }
      int k = i + 1;
      j = k;
      if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
        for (;;)
        {
          j = k;
          if (k >= m) {
            break;
          }
          j = k;
          if (!((Boolean)paramArrayList1.get(k)).booleanValue()) {
            break;
          }
          j = k;
          if (((BackStackRecord)paramArrayList.get(k)).mAllowOptimization) {
            break;
          }
          k += 1;
        }
      }
      executeOpsTogether(paramArrayList, paramArrayList1, i, j);
      i = j;
      k = j - 1;
      j = i;
      i = k;
    }
    label220:
    for (;;)
    {
      i += 1;
      break label55;
      if (j == m) {
        break;
      }
      executeOpsTogether(paramArrayList, paramArrayList1, j, m);
      return;
    }
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2)
  {
    execPendingActions();
    ensureExecReady(true);
    boolean bool = popBackStackState(this.mTmpRecords, this.mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      this.mExecutingActions = true;
    }
    try
    {
      optimizeAndExecuteOps(this.mTmpRecords, this.mTmpIsPop);
      cleanupExec();
      doPendingDeferredStart();
      return bool;
    }
    finally
    {
      cleanupExec();
    }
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet)
  {
    int j = paramInt2 - 1;
    int i = paramInt2;
    BackStackRecord localBackStackRecord;
    int k;
    if (j >= paramInt1)
    {
      localBackStackRecord = (BackStackRecord)paramArrayList.get(j);
      boolean bool = ((Boolean)paramArrayList1.get(j)).booleanValue();
      if ((localBackStackRecord.isPostponed()) && (!localBackStackRecord.interactsWith(paramArrayList, j + 1, paramInt2)))
      {
        k = 1;
        label67:
        if (k == 0) {
          break label191;
        }
        if (this.mPostponedTransactions == null) {
          this.mPostponedTransactions = new ArrayList();
        }
        StartEnterTransitionListener localStartEnterTransitionListener = new StartEnterTransitionListener(localBackStackRecord, bool);
        this.mPostponedTransactions.add(localStartEnterTransitionListener);
        localBackStackRecord.setOnStartPostponedListener(localStartEnterTransitionListener);
        if (!bool) {
          break label179;
        }
        localBackStackRecord.executeOps();
        label130:
        i -= 1;
        if (j != i)
        {
          paramArrayList.remove(j);
          paramArrayList.add(i, localBackStackRecord);
        }
        addAddedFragments(paramArraySet);
      }
    }
    label179:
    label191:
    for (;;)
    {
      j -= 1;
      break;
      k = 0;
      break label67;
      localBackStackRecord.executePopOps(false);
      break label130;
      return i;
    }
  }
  
  public static int reverseTransit(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 4097: 
      return 8194;
    case 8194: 
      return 4097;
    }
    return 4099;
  }
  
  private void scheduleCommit()
  {
    int j = 1;
    label44:
    label73:
    label92:
    label97:
    label100:
    for (;;)
    {
      int i;
      try
      {
        if ((this.mPostponedTransactions == null) || (this.mPostponedTransactions.isEmpty())) {
          break label92;
        }
        i = 1;
        if ((this.mPendingActions == null) || (this.mPendingActions.size() != 1)) {
          break label97;
        }
      }
      finally {}
      this.mHost.getHandler().removeCallbacks(this.mExecCommit);
      this.mHost.getHandler().post(this.mExecCommit);
      return;
      for (;;)
      {
        if (i != 0) {
          break label100;
        }
        if (j == 0) {
          break label73;
        }
        break label44;
        i = 0;
        break;
        j = 0;
      }
    }
  }
  
  private void setHWLayerAnimListenerIfAlpha(View paramView, Animation paramAnimation)
  {
    if ((paramView == null) || (paramAnimation == null)) {}
    while (!shouldRunOnHWLayer(paramView, paramAnimation)) {
      return;
    }
    try
    {
      if (sAnimationListenerField == null)
      {
        sAnimationListenerField = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField.setAccessible(true);
      }
      localAnimationListener = (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Animation.AnimationListener localAnimationListener;
        Log.e("FragmentManager", "No field with the name mListener is found in Animation class", localNoSuchFieldException);
        Object localObject1 = null;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.e("FragmentManager", "Cannot access Animation's mListener field", localIllegalAccessException);
        Object localObject2 = null;
      }
    }
    ViewCompat.setLayerType(paramView, 2, null);
    paramAnimation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, paramAnimation, localAnimationListener));
  }
  
  static boolean shouldRunOnHWLayer(View paramView, Animation paramAnimation)
  {
    return (Build.VERSION.SDK_INT >= 19) && (ViewCompat.getLayerType(paramView) == 0) && (ViewCompat.hasOverlappingRendering(paramView)) && (modifiesAlpha(paramAnimation));
  }
  
  private void throwException(RuntimeException paramRuntimeException)
  {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
    if (this.mHost != null) {}
    for (;;)
    {
      try
      {
        this.mHost.onDump("  ", null, localPrintWriter, new String[0]);
        throw paramRuntimeException;
      }
      catch (Exception localException1)
      {
        Log.e("FragmentManager", "Failed dumping state", localException1);
        continue;
      }
      try
      {
        dump("  ", null, localException1, new String[0]);
      }
      catch (Exception localException2)
      {
        Log.e("FragmentManager", "Failed dumping state", localException2);
      }
    }
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default: 
      return -1;
    case 4097: 
      if (paramBoolean) {
        return 1;
      }
      return 2;
    case 8194: 
      if (paramBoolean) {
        return 3;
      }
      return 4;
    }
    if (paramBoolean) {
      return 5;
    }
    return 6;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord)
  {
    if (this.mBackStack == null) {
      this.mBackStack = new ArrayList();
    }
    this.mBackStack.add(paramBackStackRecord);
    reportBackStackChanged();
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean)
  {
    if (this.mAdded == null) {
      this.mAdded = new ArrayList();
    }
    if (DEBUG) {
      Log.v("FragmentManager", "add: " + paramFragment);
    }
    makeActive(paramFragment);
    if (!paramFragment.mDetached)
    {
      if (this.mAdded.contains(paramFragment)) {
        throw new IllegalStateException("Fragment already added: " + paramFragment);
      }
      this.mAdded.add(paramFragment);
      paramFragment.mAdded = true;
      paramFragment.mRemoving = false;
      if (paramFragment.mView == null) {
        paramFragment.mHiddenChanged = false;
      }
      if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
        this.mNeedMenuInvalidate = true;
      }
      if (paramBoolean) {
        moveToState(paramFragment);
      }
    }
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (this.mBackStackChangeListeners == null) {
      this.mBackStackChangeListeners = new ArrayList();
    }
    this.mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord)
  {
    try
    {
      if ((this.mAvailBackStackIndices == null) || (this.mAvailBackStackIndices.size() <= 0))
      {
        if (this.mBackStackIndices == null) {
          this.mBackStackIndices = new ArrayList();
        }
        i = this.mBackStackIndices.size();
        if (DEBUG) {
          Log.v("FragmentManager", "Setting back stack index " + i + " to " + paramBackStackRecord);
        }
        this.mBackStackIndices.add(paramBackStackRecord);
        return i;
      }
      int i = ((Integer)this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1)).intValue();
      if (DEBUG) {
        Log.v("FragmentManager", "Adding back stack index " + i + " with " + paramBackStackRecord);
      }
      this.mBackStackIndices.set(i, paramBackStackRecord);
      return i;
    }
    finally {}
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment)
  {
    if (this.mHost != null) {
      throw new IllegalStateException("Already attached");
    }
    this.mHost = paramFragmentHostCallback;
    this.mContainer = paramFragmentContainer;
    this.mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "attach: " + paramFragment);
    }
    if (paramFragment.mDetached)
    {
      paramFragment.mDetached = false;
      if (!paramFragment.mAdded)
      {
        if (this.mAdded == null) {
          this.mAdded = new ArrayList();
        }
        if (this.mAdded.contains(paramFragment)) {
          throw new IllegalStateException("Fragment already added: " + paramFragment);
        }
        if (DEBUG) {
          Log.v("FragmentManager", "add from attach: " + paramFragment);
        }
        this.mAdded.add(paramFragment);
        paramFragment.mAdded = true;
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
      }
    }
  }
  
  public FragmentTransaction beginTransaction()
  {
    return new BackStackRecord(this);
  }
  
  void completeShowHideFragment(Fragment paramFragment)
  {
    boolean bool;
    if (paramFragment.mView != null)
    {
      i = paramFragment.getNextTransition();
      if (paramFragment.mHidden) {
        break label150;
      }
      bool = true;
      Animation localAnimation = loadAnimation(paramFragment, i, bool, paramFragment.getNextTransitionStyle());
      if (localAnimation != null)
      {
        setHWLayerAnimListenerIfAlpha(paramFragment.mView, localAnimation);
        paramFragment.mView.startAnimation(localAnimation);
        setHWLayerAnimListenerIfAlpha(paramFragment.mView, localAnimation);
        localAnimation.start();
      }
      if ((!paramFragment.mHidden) || (paramFragment.isHideReplaced())) {
        break label155;
      }
    }
    label150:
    label155:
    for (int i = 8;; i = 0)
    {
      paramFragment.mView.setVisibility(i);
      if (paramFragment.isHideReplaced()) {
        paramFragment.setHideReplaced(false);
      }
      if ((paramFragment.mAdded) && (paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
        this.mNeedMenuInvalidate = true;
      }
      paramFragment.mHiddenChanged = false;
      paramFragment.onHiddenChanged(paramFragment.mHidden);
      return;
      bool = false;
      break;
    }
  }
  
  public void detachFragment(Fragment paramFragment)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "detach: " + paramFragment);
    }
    if (!paramFragment.mDetached)
    {
      paramFragment.mDetached = true;
      if (paramFragment.mAdded)
      {
        if (this.mAdded != null)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "remove from detach: " + paramFragment);
          }
          this.mAdded.remove(paramFragment);
        }
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
        paramFragment.mAdded = false;
      }
    }
  }
  
  public void dispatchActivityCreated()
  {
    this.mStateSaved = false;
    this.mExecutingActions = true;
    moveToState(2, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.mAdded != null)
    {
      int i = 0;
      while (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performConfigurationChanged(paramConfiguration);
        }
        i += 1;
      }
    }
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (this.mAdded != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if ((localFragment != null) && (localFragment.performContextItemSelected(paramMenuItem))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public void dispatchCreate()
  {
    this.mStateSaved = false;
    this.mExecutingActions = true;
    moveToState(1, false);
    this.mExecutingActions = false;
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    int j = 0;
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    if (this.mAdded != null)
    {
      i = 0;
      boolean bool1 = false;
      for (;;)
      {
        localObject2 = localObject1;
        bool2 = bool1;
        if (i >= this.mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        localObject2 = localObject1;
        bool2 = bool1;
        if (localFragment != null)
        {
          localObject2 = localObject1;
          bool2 = bool1;
          if (localFragment.performCreateOptionsMenu(paramMenu, paramMenuInflater))
          {
            bool2 = true;
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = new ArrayList();
            }
            ((ArrayList)localObject2).add(localFragment);
          }
        }
        i += 1;
        bool1 = bool2;
        localObject1 = localObject2;
      }
    }
    boolean bool2 = false;
    if (this.mCreatedMenus != null)
    {
      i = j;
      while (i < this.mCreatedMenus.size())
      {
        paramMenu = (Fragment)this.mCreatedMenus.get(i);
        if ((localObject2 == null) || (!((ArrayList)localObject2).contains(paramMenu))) {
          paramMenu.onDestroyOptionsMenu();
        }
        i += 1;
      }
    }
    this.mCreatedMenus = ((ArrayList)localObject2);
    return bool2;
  }
  
  public void dispatchDestroy()
  {
    this.mDestroyed = true;
    execPendingActions();
    this.mExecutingActions = true;
    moveToState(0, false);
    this.mExecutingActions = false;
    this.mHost = null;
    this.mContainer = null;
    this.mParent = null;
  }
  
  public void dispatchDestroyView()
  {
    this.mExecutingActions = true;
    moveToState(1, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchLowMemory()
  {
    if (this.mAdded != null)
    {
      int i = 0;
      while (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performLowMemory();
        }
        i += 1;
      }
    }
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean)
  {
    if (this.mAdded == null) {}
    for (;;)
    {
      return;
      int i = this.mAdded.size() - 1;
      while (i >= 0)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performMultiWindowModeChanged(paramBoolean);
        }
        i -= 1;
      }
    }
  }
  
  void dispatchOnFragmentActivityCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentActivityCreated(this, paramFragment, paramBundle);
        }
      }
    }
  }
  
  void dispatchOnFragmentAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentAttached(paramFragment, paramContext, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentAttached(this, paramFragment, paramContext);
        }
      }
    }
  }
  
  void dispatchOnFragmentCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentCreated(paramFragment, paramBundle, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentCreated(this, paramFragment, paramBundle);
        }
      }
    }
  }
  
  void dispatchOnFragmentDestroyed(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentDestroyed(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentDestroyed(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentDetached(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentDetached(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentDetached(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentPaused(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentPaused(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentPaused(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentPreAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentPreAttached(paramFragment, paramContext, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentPreAttached(this, paramFragment, paramContext);
        }
      }
    }
  }
  
  void dispatchOnFragmentResumed(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentResumed(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentResumed(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentSaveInstanceState(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentSaveInstanceState(this, paramFragment, paramBundle);
        }
      }
    }
  }
  
  void dispatchOnFragmentStarted(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentStarted(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentStarted(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentStopped(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentStopped(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentStopped(this, paramFragment);
        }
      }
    }
  }
  
  void dispatchOnFragmentViewCreated(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentViewCreated(this, paramFragment, paramView, paramBundle);
        }
      }
    }
  }
  
  void dispatchOnFragmentViewDestroyed(Fragment paramFragment, boolean paramBoolean)
  {
    Object localObject;
    if (this.mParent != null)
    {
      localObject = this.mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentViewDestroyed(paramFragment, true);
      }
    }
    if (this.mLifecycleCallbacks == null) {}
    for (;;)
    {
      return;
      localObject = this.mLifecycleCallbacks.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Pair localPair = (Pair)((Iterator)localObject).next();
        if ((!paramBoolean) || (((Boolean)localPair.second).booleanValue())) {
          ((FragmentManager.FragmentLifecycleCallbacks)localPair.first).onFragmentViewDestroyed(this, paramFragment);
        }
      }
    }
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int i;
    if (this.mAdded != null) {
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if ((localFragment != null) && (localFragment.performOptionsItemSelected(paramMenuItem))) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    if (this.mAdded != null)
    {
      int i = 0;
      while (i < this.mAdded.size())
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performOptionsMenuClosed(paramMenu);
        }
        i += 1;
      }
    }
  }
  
  public void dispatchPause()
  {
    this.mExecutingActions = true;
    moveToState(4, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean)
  {
    if (this.mAdded == null) {}
    for (;;)
    {
      return;
      int i = this.mAdded.size() - 1;
      while (i >= 0)
      {
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        if (localFragment != null) {
          localFragment.performPictureInPictureModeChanged(paramBoolean);
        }
        i -= 1;
      }
    }
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.mAdded != null)
    {
      int i = 0;
      for (boolean bool1 = false;; bool1 = bool2)
      {
        bool2 = bool1;
        if (i >= this.mAdded.size()) {
          break;
        }
        Fragment localFragment = (Fragment)this.mAdded.get(i);
        bool2 = bool1;
        if (localFragment != null)
        {
          bool2 = bool1;
          if (localFragment.performPrepareOptionsMenu(paramMenu)) {
            bool2 = true;
          }
        }
        i += 1;
      }
    }
    boolean bool2 = false;
    return bool2;
  }
  
  public void dispatchReallyStop()
  {
    this.mExecutingActions = true;
    moveToState(2, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchResume()
  {
    this.mStateSaved = false;
    this.mExecutingActions = true;
    moveToState(5, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchStart()
  {
    this.mStateSaved = false;
    this.mExecutingActions = true;
    moveToState(4, false);
    this.mExecutingActions = false;
  }
  
  public void dispatchStop()
  {
    this.mStateSaved = true;
    this.mExecutingActions = true;
    moveToState(3, false);
    this.mExecutingActions = false;
  }
  
  void doPendingDeferredStart()
  {
    if (this.mHavePendingDeferredStart)
    {
      int i = 0;
      boolean bool2;
      for (boolean bool1 = false; i < this.mActive.size(); bool1 = bool2)
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        bool2 = bool1;
        if (localFragment != null)
        {
          bool2 = bool1;
          if (localFragment.mLoaderManager != null) {
            bool2 = bool1 | localFragment.mLoaderManager.hasRunningLoaders();
          }
        }
        i += 1;
      }
      if (!bool1)
      {
        this.mHavePendingDeferredStart = false;
        startPendingDeferredFragments();
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    int j = 0;
    String str = paramString + "    ";
    int k;
    int i;
    Object localObject;
    if (this.mActive != null)
    {
      k = this.mActive.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("Active Fragments in ");
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
        paramPrintWriter.println(":");
        i = 0;
        while (i < k)
        {
          localObject = (Fragment)this.mActive.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localObject);
          if (localObject != null) {
            ((Fragment)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          }
          i += 1;
        }
      }
    }
    if (this.mAdded != null)
    {
      k = this.mAdded.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Added Fragments:");
        i = 0;
        while (i < k)
        {
          localObject = (Fragment)this.mAdded.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
          i += 1;
        }
      }
    }
    if (this.mCreatedMenus != null)
    {
      k = this.mCreatedMenus.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Fragments Created Menus:");
        i = 0;
        while (i < k)
        {
          localObject = (Fragment)this.mCreatedMenus.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject).toString());
          i += 1;
        }
      }
    }
    if (this.mBackStack != null)
    {
      k = this.mBackStack.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Back Stack:");
        i = 0;
        while (i < k)
        {
          localObject = (BackStackRecord)this.mBackStack.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((BackStackRecord)localObject).toString());
          ((BackStackRecord)localObject).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          i += 1;
        }
      }
    }
    try
    {
      if (this.mBackStackIndices != null)
      {
        k = this.mBackStackIndices.size();
        if (k > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Back Stack Indices:");
          i = 0;
          while (i < k)
          {
            paramFileDescriptor = (BackStackRecord)this.mBackStackIndices.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      if ((this.mAvailBackStackIndices != null) && (this.mAvailBackStackIndices.size() > 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mAvailBackStackIndices: ");
        paramPrintWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
      }
      if (this.mPendingActions != null)
      {
        k = this.mPendingActions.size();
        if (k > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Pending Actions:");
          i = j;
          while (i < k)
          {
            paramFileDescriptor = (OpGenerator)this.mPendingActions.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      paramPrintWriter.print(paramString);
    }
    finally {}
    paramPrintWriter.println("FragmentManager misc state:");
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mHost=");
    paramPrintWriter.println(this.mHost);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mContainer=");
    paramPrintWriter.println(this.mContainer);
    if (this.mParent != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mParent=");
      paramPrintWriter.println(this.mParent);
    }
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("  mCurState=");
    paramPrintWriter.print(this.mCurState);
    paramPrintWriter.print(" mStateSaved=");
    paramPrintWriter.print(this.mStateSaved);
    paramPrintWriter.print(" mDestroyed=");
    paramPrintWriter.println(this.mDestroyed);
    if (this.mNeedMenuInvalidate)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNeedMenuInvalidate=");
      paramPrintWriter.println(this.mNeedMenuInvalidate);
    }
    if (this.mNoTransactionsBecause != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mNoTransactionsBecause=");
      paramPrintWriter.println(this.mNoTransactionsBecause);
    }
    if ((this.mAvailIndices != null) && (this.mAvailIndices.size() > 0))
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mAvailIndices: ");
      paramPrintWriter.println(Arrays.toString(this.mAvailIndices.toArray()));
    }
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean)
  {
    if (!paramBoolean) {
      checkStateLoss();
    }
    try
    {
      if ((this.mDestroyed) || (this.mHost == null)) {
        throw new IllegalStateException("Activity has been destroyed");
      }
    }
    finally
    {
      throw paramOpGenerator;
      if (this.mPendingActions == null) {
        this.mPendingActions = new ArrayList();
      }
      this.mPendingActions.add(paramOpGenerator);
    }
  }
  
  /* Error */
  public boolean execPendingActions()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokespecial 532	android/support/v4/app/FragmentManagerImpl:ensureExecReady	(Z)V
    //   5: iconst_0
    //   6: istore_1
    //   7: aload_0
    //   8: aload_0
    //   9: getfield 259	android/support/v4/app/FragmentManagerImpl:mTmpRecords	Ljava/util/ArrayList;
    //   12: aload_0
    //   13: getfield 254	android/support/v4/app/FragmentManagerImpl:mTmpIsPop	Ljava/util/ArrayList;
    //   16: invokespecial 1056	android/support/v4/app/FragmentManagerImpl:generateOpsForPendingActions	(Ljava/util/ArrayList;Ljava/util/ArrayList;)Z
    //   19: ifeq +36 -> 55
    //   22: aload_0
    //   23: iconst_1
    //   24: putfield 252	android/support/v4/app/FragmentManagerImpl:mExecutingActions	Z
    //   27: aload_0
    //   28: aload_0
    //   29: getfield 259	android/support/v4/app/FragmentManagerImpl:mTmpRecords	Ljava/util/ArrayList;
    //   32: aload_0
    //   33: getfield 254	android/support/v4/app/FragmentManagerImpl:mTmpIsPop	Ljava/util/ArrayList;
    //   36: invokespecial 538	android/support/v4/app/FragmentManagerImpl:optimizeAndExecuteOps	(Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    //   39: aload_0
    //   40: invokespecial 540	android/support/v4/app/FragmentManagerImpl:cleanupExec	()V
    //   43: iconst_1
    //   44: istore_1
    //   45: goto -38 -> 7
    //   48: astore_2
    //   49: aload_0
    //   50: invokespecial 540	android/support/v4/app/FragmentManagerImpl:cleanupExec	()V
    //   53: aload_2
    //   54: athrow
    //   55: aload_0
    //   56: invokevirtual 543	android/support/v4/app/FragmentManagerImpl:doPendingDeferredStart	()V
    //   59: iload_1
    //   60: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	FragmentManagerImpl
    //   6	54	1	bool	boolean
    //   48	6	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   27	39	48	finally
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean)
  {
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
      this.mExecutingActions = true;
    }
    try
    {
      optimizeAndExecuteOps(this.mTmpRecords, this.mTmpIsPop);
      cleanupExec();
      doPendingDeferredStart();
      return;
    }
    finally
    {
      cleanupExec();
    }
  }
  
  public boolean executePendingTransactions()
  {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  public Fragment findFragmentById(int paramInt)
  {
    int i;
    Object localObject;
    if (this.mAdded != null)
    {
      i = this.mAdded.size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)this.mAdded.get(i);
        if ((localObject != null) && (((Fragment)localObject).mFragmentId == paramInt)) {
          return (Fragment)localObject;
        }
        i -= 1;
      }
    }
    if (this.mActive != null)
    {
      i = this.mActive.size() - 1;
      for (;;)
      {
        if (i < 0) {
          break label112;
        }
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null)
        {
          localObject = localFragment;
          if (localFragment.mFragmentId == paramInt) {
            break;
          }
        }
        i -= 1;
      }
    }
    label112:
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString)
  {
    int i;
    Object localObject;
    if ((this.mAdded != null) && (paramString != null))
    {
      i = this.mAdded.size() - 1;
      while (i >= 0)
      {
        localObject = (Fragment)this.mAdded.get(i);
        if ((localObject != null) && (paramString.equals(((Fragment)localObject).mTag))) {
          return (Fragment)localObject;
        }
        i -= 1;
      }
    }
    if ((this.mActive != null) && (paramString != null))
    {
      i = this.mActive.size() - 1;
      for (;;)
      {
        if (i < 0) {
          break label126;
        }
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null)
        {
          localObject = localFragment;
          if (paramString.equals(localFragment.mTag)) {
            break;
          }
        }
        i -= 1;
      }
    }
    label126:
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    if ((this.mActive != null) && (paramString != null))
    {
      int i = this.mActive.size() - 1;
      while (i >= 0)
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null)
        {
          localFragment = localFragment.findFragmentByWho(paramString);
          if (localFragment != null) {
            return localFragment;
          }
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public void freeBackStackIndex(int paramInt)
  {
    try
    {
      this.mBackStackIndices.set(paramInt, null);
      if (this.mAvailBackStackIndices == null) {
        this.mAvailBackStackIndices = new ArrayList();
      }
      if (DEBUG) {
        Log.v("FragmentManager", "Freeing back stack index " + paramInt);
      }
      this.mAvailBackStackIndices.add(Integer.valueOf(paramInt));
      return;
    }
    finally {}
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt)
  {
    return (FragmentManager.BackStackEntry)this.mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount()
  {
    if (this.mBackStack != null) {
      return this.mBackStack.size();
    }
    return 0;
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1) {
      paramBundle = null;
    }
    Fragment localFragment;
    do
    {
      return paramBundle;
      if (i >= this.mActive.size()) {
        throwException(new IllegalStateException("Fragment no longer exists for key " + paramString + ": index " + i));
      }
      localFragment = (Fragment)this.mActive.get(i);
      paramBundle = localFragment;
    } while (localFragment != null);
    throwException(new IllegalStateException("Fragment no longer exists for key " + paramString + ": index " + i));
    return localFragment;
  }
  
  public List<Fragment> getFragments()
  {
    return this.mActive;
  }
  
  LayoutInflaterFactory getLayoutInflaterFactory()
  {
    return this;
  }
  
  public void hideFragment(Fragment paramFragment)
  {
    boolean bool = true;
    if (DEBUG) {
      Log.v("FragmentManager", "hide: " + paramFragment);
    }
    if (!paramFragment.mHidden)
    {
      paramFragment.mHidden = true;
      if (paramFragment.mHiddenChanged) {
        break label59;
      }
    }
    for (;;)
    {
      paramFragment.mHiddenChanged = bool;
      return;
      label59:
      bool = false;
    }
  }
  
  public boolean isDestroyed()
  {
    return this.mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt)
  {
    return this.mCurState >= paramInt;
  }
  
  Animation loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    Animation localAnimation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, paramFragment.getNextAnim());
    if (localAnimation != null) {
      paramFragment = localAnimation;
    }
    do
    {
      return paramFragment;
      if (paramFragment.getNextAnim() == 0) {
        break;
      }
      localAnimation = AnimationUtils.loadAnimation(this.mHost.getContext(), paramFragment.getNextAnim());
      paramFragment = localAnimation;
    } while (localAnimation != null);
    if (paramInt1 == 0) {
      return null;
    }
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0) {
      return null;
    }
    switch (paramInt1)
    {
    default: 
      paramInt1 = paramInt2;
      if (paramInt2 == 0)
      {
        paramInt1 = paramInt2;
        if (this.mHost.onHasWindowAnimations()) {
          paramInt1 = this.mHost.onGetWindowAnimations();
        }
      }
      if (paramInt1 == 0) {
        return null;
      }
      break;
    case 1: 
      return makeOpenCloseAnimation(this.mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
    case 2: 
      return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
    case 3: 
      return makeOpenCloseAnimation(this.mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
    case 4: 
      return makeOpenCloseAnimation(this.mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
    case 5: 
      return makeFadeAnimation(this.mHost.getContext(), 0.0F, 1.0F);
    case 6: 
      return makeFadeAnimation(this.mHost.getContext(), 1.0F, 0.0F);
    }
    return null;
  }
  
  void makeActive(Fragment paramFragment)
  {
    if (paramFragment.mIndex >= 0) {}
    for (;;)
    {
      return;
      if ((this.mAvailIndices == null) || (this.mAvailIndices.size() <= 0))
      {
        if (this.mActive == null) {
          this.mActive = new ArrayList();
        }
        paramFragment.setIndex(this.mActive.size(), this.mParent);
        this.mActive.add(paramFragment);
      }
      while (DEBUG)
      {
        Log.v("FragmentManager", "Allocated fragment index " + paramFragment);
        return;
        paramFragment.setIndex(((Integer)this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.mParent);
        this.mActive.set(paramFragment.mIndex, paramFragment);
      }
    }
  }
  
  void makeInactive(Fragment paramFragment)
  {
    if (paramFragment.mIndex < 0) {
      return;
    }
    if (DEBUG) {
      Log.v("FragmentManager", "Freeing fragment index " + paramFragment);
    }
    this.mActive.set(paramFragment.mIndex, null);
    if (this.mAvailIndices == null) {
      this.mAvailIndices = new ArrayList();
    }
    this.mAvailIndices.add(Integer.valueOf(paramFragment.mIndex));
    this.mHost.inactivateFragment(paramFragment.mWho);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment)
  {
    if (paramFragment == null) {
      return;
    }
    int j = this.mCurState;
    int i = j;
    label32:
    Object localObject;
    if (paramFragment.mRemoving)
    {
      if (paramFragment.isInBackStack()) {
        i = Math.min(j, 1);
      }
    }
    else
    {
      moveToState(paramFragment, i, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
      if (paramFragment.mView != null)
      {
        localObject = findFragmentUnder(paramFragment);
        if (localObject != null)
        {
          localObject = ((Fragment)localObject).mView;
          ViewGroup localViewGroup = paramFragment.mContainer;
          i = localViewGroup.indexOfChild((View)localObject);
          j = localViewGroup.indexOfChild(paramFragment.mView);
          if (j < i)
          {
            localViewGroup.removeViewAt(j);
            localViewGroup.addView(paramFragment.mView, i);
          }
        }
        if ((paramFragment.mIsNewlyAdded) && (paramFragment.mContainer != null))
        {
          if (Build.VERSION.SDK_INT >= 11) {
            break label220;
          }
          paramFragment.mView.setVisibility(0);
        }
      }
    }
    for (;;)
    {
      paramFragment.mPostponedAlpha = 0.0F;
      paramFragment.mIsNewlyAdded = false;
      localObject = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
      if (localObject != null)
      {
        setHWLayerAnimListenerIfAlpha(paramFragment.mView, (Animation)localObject);
        paramFragment.mView.startAnimation((Animation)localObject);
      }
      if (!paramFragment.mHiddenChanged) {
        break;
      }
      completeShowHideFragment(paramFragment);
      return;
      i = Math.min(j, 0);
      break label32;
      label220:
      if (paramFragment.mPostponedAlpha > 0.0F) {
        paramFragment.mView.setAlpha(paramFragment.mPostponedAlpha);
      }
    }
  }
  
  void moveToState(int paramInt, boolean paramBoolean)
  {
    if ((this.mHost == null) && (paramInt != 0)) {
      throw new IllegalStateException("No activity");
    }
    if ((!paramBoolean) && (paramInt == this.mCurState)) {}
    do
    {
      return;
      this.mCurState = paramInt;
    } while (this.mActive == null);
    int k;
    int j;
    label68:
    int i;
    Fragment localFragment;
    if (this.mAdded != null)
    {
      k = this.mAdded.size();
      j = 0;
      paramInt = 0;
      i = paramInt;
      if (j >= k) {
        break label127;
      }
      localFragment = (Fragment)this.mAdded.get(j);
      moveFragmentToExpectedState(localFragment);
      if (localFragment.mLoaderManager == null) {
        break label273;
      }
      paramInt = localFragment.mLoaderManager.hasRunningLoaders() | paramInt;
    }
    label127:
    label139:
    label268:
    label273:
    for (;;)
    {
      j += 1;
      break label68;
      i = 0;
      k = this.mActive.size();
      j = 0;
      if (j < k)
      {
        localFragment = (Fragment)this.mActive.get(j);
        if ((localFragment == null) || ((!localFragment.mRemoving) && (!localFragment.mDetached)) || (localFragment.mIsNewlyAdded)) {
          break label268;
        }
        moveFragmentToExpectedState(localFragment);
        if (localFragment.mLoaderManager == null) {
          break label268;
        }
      }
      for (paramInt = localFragment.mLoaderManager.hasRunningLoaders() | i;; paramInt = i)
      {
        j += 1;
        i = paramInt;
        break label139;
        if (i == 0) {
          startPendingDeferredFragments();
        }
        if ((!this.mNeedMenuInvalidate) || (this.mHost == null) || (this.mCurState != 5)) {
          break;
        }
        this.mHost.onSupportInvalidateOptionsMenu();
        this.mNeedMenuInvalidate = false;
        return;
      }
    }
  }
  
  void moveToState(Fragment paramFragment)
  {
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  void moveToState(final Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    boolean bool = true;
    int i;
    if (paramFragment.mAdded)
    {
      i = paramInt1;
      if (!paramFragment.mDetached) {}
    }
    else
    {
      i = paramInt1;
      if (paramInt1 > 1) {
        i = 1;
      }
    }
    int j = i;
    if (paramFragment.mRemoving)
    {
      j = i;
      if (i > paramFragment.mState) {
        j = paramFragment.mState;
      }
    }
    paramInt1 = j;
    if (paramFragment.mDeferStart)
    {
      paramInt1 = j;
      if (paramFragment.mState < 4)
      {
        paramInt1 = j;
        if (j > 3) {
          paramInt1 = 3;
        }
      }
    }
    Object localObject1;
    label545:
    label583:
    label650:
    ViewGroup localViewGroup;
    if (paramFragment.mState < paramInt1)
    {
      if ((paramFragment.mFromLayout) && (!paramFragment.mInLayout)) {}
      do
      {
        return;
        if (paramFragment.getAnimatingAway() != null)
        {
          paramFragment.setAnimatingAway(null);
          moveToState(paramFragment, paramFragment.getStateAfterAnimating(), 0, 0, true);
        }
        paramInt2 = paramInt1;
        i = paramInt1;
        j = paramInt1;
        paramInt3 = paramInt1;
        switch (paramFragment.mState)
        {
        default: 
          i = paramInt1;
        }
      } while (paramFragment.mState == i);
      Log.w("FragmentManager", "moveToState: Fragment state for " + paramFragment + " not updated inline; " + "expected state " + i + " found " + paramFragment.mState);
      paramFragment.mState = i;
      return;
      if (DEBUG) {
        Log.v("FragmentManager", "moveto CREATED: " + paramFragment);
      }
      paramInt3 = paramInt1;
      if (paramFragment.mSavedFragmentState != null)
      {
        paramFragment.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
        paramFragment.mSavedViewState = paramFragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        paramFragment.mTarget = getFragment(paramFragment.mSavedFragmentState, "android:target_state");
        if (paramFragment.mTarget != null) {
          paramFragment.mTargetRequestCode = paramFragment.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        paramFragment.mUserVisibleHint = paramFragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        paramInt3 = paramInt1;
        if (!paramFragment.mUserVisibleHint)
        {
          paramFragment.mDeferStart = true;
          paramInt3 = paramInt1;
          if (paramInt1 > 3) {
            paramInt3 = 3;
          }
        }
      }
      paramFragment.mHost = this.mHost;
      paramFragment.mParentFragment = this.mParent;
      if (this.mParent != null) {}
      for (localObject1 = this.mParent.mChildFragmentManager;; localObject1 = this.mHost.getFragmentManagerImpl())
      {
        paramFragment.mFragmentManager = ((FragmentManagerImpl)localObject1);
        dispatchOnFragmentPreAttached(paramFragment, this.mHost.getContext(), false);
        paramFragment.mCalled = false;
        paramFragment.onAttach(this.mHost.getContext());
        if (paramFragment.mCalled) {
          break;
        }
        throw new SuperNotCalledException("Fragment " + paramFragment + " did not call through to super.onAttach()");
      }
      if (paramFragment.mParentFragment == null)
      {
        this.mHost.onAttachFragment(paramFragment);
        dispatchOnFragmentAttached(paramFragment, this.mHost.getContext(), false);
        if (paramFragment.mRetaining) {
          break label1243;
        }
        paramFragment.performCreate(paramFragment.mSavedFragmentState);
        dispatchOnFragmentCreated(paramFragment, paramFragment.mSavedFragmentState, false);
        paramFragment.mRetaining = false;
        paramInt2 = paramInt3;
        if (paramFragment.mFromLayout)
        {
          paramFragment.mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(paramFragment.mSavedFragmentState), null, paramFragment.mSavedFragmentState);
          if (paramFragment.mView == null) {
            break label1273;
          }
          paramFragment.mInnerView = paramFragment.mView;
          if (Build.VERSION.SDK_INT < 11) {
            break label1259;
          }
          ViewCompat.setSaveFromParentEnabled(paramFragment.mView, false);
          if (paramFragment.mHidden) {
            paramFragment.mView.setVisibility(8);
          }
          paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
          dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
          paramInt2 = paramInt3;
        }
        i = paramInt2;
        if (paramInt2 > 1)
        {
          if (DEBUG) {
            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + paramFragment);
          }
          if (!paramFragment.mFromLayout)
          {
            if (paramFragment.mContainerId == 0) {
              break label1905;
            }
            if (paramFragment.mContainerId == -1) {
              throwException(new IllegalArgumentException("Cannot create fragment " + paramFragment + " for a container view with no id"));
            }
            localViewGroup = (ViewGroup)this.mContainer.onFindViewById(paramFragment.mContainerId);
            localObject1 = localViewGroup;
            if (localViewGroup == null)
            {
              localObject1 = localViewGroup;
              if (paramFragment.mRestored) {}
            }
          }
        }
      }
    }
    for (;;)
    {
      label1243:
      label1259:
      label1273:
      Object localObject2;
      try
      {
        localObject1 = paramFragment.getResources().getResourceName(paramFragment.mContainerId);
        throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(paramFragment.mContainerId) + " (" + (String)localObject1 + ") for fragment " + paramFragment));
        localObject1 = localViewGroup;
        paramFragment.mContainer = ((ViewGroup)localObject1);
        paramFragment.mView = paramFragment.performCreateView(paramFragment.getLayoutInflater(paramFragment.mSavedFragmentState), (ViewGroup)localObject1, paramFragment.mSavedFragmentState);
        if (paramFragment.mView != null)
        {
          paramFragment.mInnerView = paramFragment.mView;
          if (Build.VERSION.SDK_INT >= 11)
          {
            ViewCompat.setSaveFromParentEnabled(paramFragment.mView, false);
            if (localObject1 != null) {
              ((ViewGroup)localObject1).addView(paramFragment.mView);
            }
            if (paramFragment.mHidden) {
              paramFragment.mView.setVisibility(8);
            }
            paramFragment.onViewCreated(paramFragment.mView, paramFragment.mSavedFragmentState);
            dispatchOnFragmentViewCreated(paramFragment, paramFragment.mView, paramFragment.mSavedFragmentState, false);
            if ((paramFragment.mView.getVisibility() != 0) || (paramFragment.mContainer == null)) {
              continue;
            }
            paramBoolean = bool;
            paramFragment.mIsNewlyAdded = paramBoolean;
            paramFragment.performActivityCreated(paramFragment.mSavedFragmentState);
            dispatchOnFragmentActivityCreated(paramFragment, paramFragment.mSavedFragmentState, false);
            if (paramFragment.mView != null) {
              paramFragment.restoreViewState(paramFragment.mSavedFragmentState);
            }
            paramFragment.mSavedFragmentState = null;
            i = paramInt2;
            j = i;
            if (i > 2)
            {
              paramFragment.mState = 3;
              j = i;
            }
            paramInt3 = j;
            if (j > 3)
            {
              if (DEBUG) {
                Log.v("FragmentManager", "moveto STARTED: " + paramFragment);
              }
              paramFragment.performStart();
              dispatchOnFragmentStarted(paramFragment, false);
              paramInt3 = j;
            }
            i = paramInt3;
            if (paramInt3 <= 4) {
              break;
            }
            if (DEBUG) {
              Log.v("FragmentManager", "moveto RESUMED: " + paramFragment);
            }
            paramFragment.performResume();
            dispatchOnFragmentResumed(paramFragment, false);
            paramFragment.mSavedFragmentState = null;
            paramFragment.mSavedViewState = null;
            i = paramInt3;
            break;
            paramFragment.mParentFragment.onAttachFragment(paramFragment);
            break label545;
            paramFragment.restoreChildFragmentState(paramFragment.mSavedFragmentState);
            paramFragment.mState = 1;
            break label583;
            paramFragment.mView = NoSaveStateFrameLayout.wrap(paramFragment.mView);
            break label650;
            paramFragment.mInnerView = null;
            paramInt2 = paramInt3;
          }
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        localObject2 = "unknown";
        continue;
        paramFragment.mView = NoSaveStateFrameLayout.wrap(paramFragment.mView);
        continue;
        paramBoolean = false;
        continue;
        paramFragment.mInnerView = null;
        continue;
      }
      i = paramInt1;
      if (paramFragment.mState <= paramInt1) {
        break;
      }
      switch (paramFragment.mState)
      {
      default: 
        i = paramInt1;
        break;
      case 1: 
      case 5: 
      case 4: 
      case 3: 
      case 2: 
        label1378:
        do
        {
          i = paramInt1;
          if (paramInt1 >= 1) {
            break;
          }
          if ((this.mDestroyed) && (paramFragment.getAnimatingAway() != null))
          {
            localObject2 = paramFragment.getAnimatingAway();
            paramFragment.setAnimatingAway(null);
            ((View)localObject2).clearAnimation();
          }
          if (paramFragment.getAnimatingAway() == null) {
            break label1785;
          }
          paramFragment.setStateAfterAnimating(paramInt1);
          i = 1;
          break;
          if (paramInt1 < 5)
          {
            if (DEBUG) {
              Log.v("FragmentManager", "movefrom RESUMED: " + paramFragment);
            }
            paramFragment.performPause();
            dispatchOnFragmentPaused(paramFragment, false);
          }
          if (paramInt1 < 4)
          {
            if (DEBUG) {
              Log.v("FragmentManager", "movefrom STARTED: " + paramFragment);
            }
            paramFragment.performStop();
            dispatchOnFragmentStopped(paramFragment, false);
          }
          if (paramInt1 < 3)
          {
            if (DEBUG) {
              Log.v("FragmentManager", "movefrom STOPPED: " + paramFragment);
            }
            paramFragment.performReallyStop();
          }
        } while (paramInt1 >= 2);
        if (DEBUG) {
          Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + paramFragment);
        }
        if ((paramFragment.mView != null) && (this.mHost.onShouldSaveFragmentState(paramFragment)) && (paramFragment.mSavedViewState == null)) {
          saveFragmentViewState(paramFragment);
        }
        paramFragment.performDestroyView();
        dispatchOnFragmentViewDestroyed(paramFragment, false);
        if ((paramFragment.mView != null) && (paramFragment.mContainer != null)) {
          if ((this.mCurState <= 0) || (this.mDestroyed) || (paramFragment.mView.getVisibility() != 0) || (paramFragment.mPostponedAlpha < 0.0F)) {
            break label1899;
          }
        }
        label1785:
        label1878:
        label1899:
        for (localObject2 = loadAnimation(paramFragment, paramInt2, false, paramInt3);; localObject2 = null)
        {
          paramFragment.mPostponedAlpha = 0.0F;
          if (localObject2 != null)
          {
            paramFragment.setAnimatingAway(paramFragment.mView);
            paramFragment.setStateAfterAnimating(paramInt1);
            ((Animation)localObject2).setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramFragment.mView, (Animation)localObject2)
            {
              public void onAnimationEnd(Animation paramAnonymousAnimation)
              {
                super.onAnimationEnd(paramAnonymousAnimation);
                if (paramFragment.getAnimatingAway() != null)
                {
                  paramFragment.setAnimatingAway(null);
                  FragmentManagerImpl.this.moveToState(paramFragment, paramFragment.getStateAfterAnimating(), 0, 0, false);
                }
              }
            });
            paramFragment.mView.startAnimation((Animation)localObject2);
          }
          paramFragment.mContainer.removeView(paramFragment.mView);
          paramFragment.mContainer = null;
          paramFragment.mView = null;
          paramFragment.mInnerView = null;
          break label1378;
          if (DEBUG) {
            Log.v("FragmentManager", "movefrom CREATED: " + paramFragment);
          }
          if (!paramFragment.mRetaining)
          {
            paramFragment.performDestroy();
            dispatchOnFragmentDestroyed(paramFragment, false);
          }
          for (;;)
          {
            paramFragment.performDetach();
            dispatchOnFragmentDetached(paramFragment, false);
            i = paramInt1;
            if (paramBoolean) {
              break;
            }
            if (paramFragment.mRetaining) {
              break label1878;
            }
            makeInactive(paramFragment);
            i = paramInt1;
            break;
            paramFragment.mState = 0;
          }
          paramFragment.mHost = null;
          paramFragment.mParentFragment = null;
          paramFragment.mFragmentManager = null;
          i = paramInt1;
          break;
        }
        label1905:
        localObject2 = null;
      }
    }
  }
  
  public void noteStateNotSaved()
  {
    this.mStateSaved = false;
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if (!"fragment".equals(paramString)) {
      return null;
    }
    String str1 = paramAttributeSet.getAttributeValue(null, "class");
    paramString = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    if (str1 == null) {
      str1 = paramString.getString(0);
    }
    for (;;)
    {
      int k = paramString.getResourceId(1, -1);
      String str2 = paramString.getString(2);
      paramString.recycle();
      if (!Fragment.isSupportFragmentClass(this.mHost.getContext(), str1)) {
        return null;
      }
      if (paramView != null) {}
      for (int i = paramView.getId(); (i == -1) && (k == -1) && (str2 == null); i = 0) {
        throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str1);
      }
      int j;
      if (k != -1)
      {
        paramString = findFragmentById(k);
        paramView = paramString;
        if (paramString == null)
        {
          paramView = paramString;
          if (str2 != null) {
            paramView = findFragmentByTag(str2);
          }
        }
        paramString = paramView;
        if (paramView == null)
        {
          paramString = paramView;
          if (i != -1) {
            paramString = findFragmentById(i);
          }
        }
        if (DEBUG) {
          Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(k) + " fname=" + str1 + " existing=" + paramString);
        }
        if (paramString != null) {
          break label428;
        }
        paramView = Fragment.instantiate(paramContext, str1);
        paramView.mFromLayout = true;
        if (k == 0) {
          break label421;
        }
        j = k;
        label291:
        paramView.mFragmentId = j;
        paramView.mContainerId = i;
        paramView.mTag = str2;
        paramView.mInLayout = true;
        paramView.mFragmentManager = this;
        paramView.mHost = this.mHost;
        paramView.onInflate(this.mHost.getContext(), paramAttributeSet, paramView.mSavedFragmentState);
        addFragment(paramView, true);
        label350:
        if ((this.mCurState >= 1) || (!paramView.mFromLayout)) {
          break label555;
        }
        moveToState(paramView, 1, 0, 0, false);
      }
      for (;;)
      {
        if (paramView.mView != null) {
          break label563;
        }
        throw new IllegalStateException("Fragment " + str1 + " did not create a view.");
        paramString = null;
        break;
        label421:
        j = i;
        break label291;
        label428:
        if (paramString.mInLayout) {
          throw new IllegalArgumentException(paramAttributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(k) + ", tag " + str2 + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + str1);
        }
        paramString.mInLayout = true;
        paramString.mHost = this.mHost;
        if (!paramString.mRetaining) {
          paramString.onInflate(this.mHost.getContext(), paramAttributeSet, paramString.mSavedFragmentState);
        }
        paramView = paramString;
        break label350;
        label555:
        moveToState(paramView);
      }
      label563:
      if (k != 0) {
        paramView.mView.setId(k);
      }
      if (paramView.mView.getTag() == null) {
        paramView.mView.setTag(str2);
      }
      return paramView.mView;
    }
  }
  
  public void performPendingDeferredStart(Fragment paramFragment)
  {
    if (paramFragment.mDeferStart)
    {
      if (this.mExecutingActions) {
        this.mHavePendingDeferredStart = true;
      }
    }
    else {
      return;
    }
    paramFragment.mDeferStart = false;
    moveToState(paramFragment, this.mCurState, 0, 0, false);
  }
  
  public void popBackStack()
  {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
  }
  
  public void popBackStack(String paramString, int paramInt)
  {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate()
  {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2)
  {
    checkStateLoss();
    execPendingActions();
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Bad id: " + paramInt1);
    }
    return popBackStackImmediate(null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt)
  {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2)
  {
    if (this.mBackStack == null) {
      return false;
    }
    if ((paramString == null) && (paramInt1 < 0) && ((paramInt2 & 0x1) == 0))
    {
      paramInt1 = this.mBackStack.size() - 1;
      if (paramInt1 < 0) {
        return false;
      }
      paramArrayList.add(this.mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
    }
    for (;;)
    {
      return true;
      int i = -1;
      if ((paramString != null) || (paramInt1 >= 0))
      {
        int j = this.mBackStack.size() - 1;
        BackStackRecord localBackStackRecord;
        for (;;)
        {
          if (j >= 0)
          {
            localBackStackRecord = (BackStackRecord)this.mBackStack.get(j);
            if ((paramString == null) || (!paramString.equals(localBackStackRecord.getName()))) {
              break label133;
            }
          }
          label133:
          while ((paramInt1 >= 0) && (paramInt1 == localBackStackRecord.mIndex))
          {
            if (j >= 0) {
              break;
            }
            return false;
          }
          j -= 1;
        }
        i = j;
        if ((paramInt2 & 0x1) != 0)
        {
          paramInt2 = j - 1;
          for (;;)
          {
            i = paramInt2;
            if (paramInt2 < 0) {
              break;
            }
            localBackStackRecord = (BackStackRecord)this.mBackStack.get(paramInt2);
            if ((paramString == null) || (!paramString.equals(localBackStackRecord.getName())))
            {
              i = paramInt2;
              if (paramInt1 < 0) {
                break;
              }
              i = paramInt2;
              if (paramInt1 != localBackStackRecord.mIndex) {
                break;
              }
            }
            paramInt2 -= 1;
          }
        }
      }
      if (i == this.mBackStack.size() - 1) {
        return false;
      }
      paramInt1 = this.mBackStack.size() - 1;
      while (paramInt1 > i)
      {
        paramArrayList.add(this.mBackStack.remove(paramInt1));
        paramArrayList1.add(Boolean.valueOf(true));
        paramInt1 -= 1;
      }
    }
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    if (paramFragment.mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    paramBundle.putInt(paramString, paramFragment.mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean)
  {
    if (this.mLifecycleCallbacks == null) {
      this.mLifecycleCallbacks = new CopyOnWriteArrayList();
    }
    this.mLifecycleCallbacks.add(new Pair(paramFragmentLifecycleCallbacks, Boolean.valueOf(paramBoolean)));
  }
  
  public void removeFragment(Fragment paramFragment)
  {
    if (DEBUG) {
      Log.v("FragmentManager", "remove: " + paramFragment + " nesting=" + paramFragment.mBackStackNesting);
    }
    if (!paramFragment.isInBackStack()) {}
    for (int i = 1;; i = 0)
    {
      if ((!paramFragment.mDetached) || (i != 0))
      {
        if (this.mAdded != null) {
          this.mAdded.remove(paramFragment);
        }
        if ((paramFragment.mHasMenu) && (paramFragment.mMenuVisible)) {
          this.mNeedMenuInvalidate = true;
        }
        paramFragment.mAdded = false;
        paramFragment.mRemoving = true;
      }
      return;
    }
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (this.mBackStackChangeListeners != null) {
      this.mBackStackChangeListeners.remove(paramOnBackStackChangedListener);
    }
  }
  
  void reportBackStackChanged()
  {
    if (this.mBackStackChangeListeners != null)
    {
      int i = 0;
      while (i < this.mBackStackChangeListeners.size())
      {
        ((FragmentManager.OnBackStackChangedListener)this.mBackStackChangeListeners.get(i)).onBackStackChanged();
        i += 1;
      }
    }
  }
  
  void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig)
  {
    if (paramParcelable == null) {}
    FragmentManagerState localFragmentManagerState;
    do
    {
      return;
      localFragmentManagerState = (FragmentManagerState)paramParcelable;
    } while (localFragmentManagerState.mActive == null);
    Object localObject1;
    int i;
    int j;
    Object localObject2;
    if (paramFragmentManagerNonConfig != null)
    {
      localObject1 = paramFragmentManagerNonConfig.getFragments();
      paramParcelable = paramFragmentManagerNonConfig.getChildNonConfigs();
      if (localObject1 != null) {}
      for (i = ((List)localObject1).size();; i = 0)
      {
        j = 0;
        while (j < i)
        {
          localObject2 = (Fragment)((List)localObject1).get(j);
          if (DEBUG) {
            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + localObject2);
          }
          FragmentState localFragmentState = localFragmentManagerState.mActive[localObject2.mIndex];
          localFragmentState.mInstance = ((Fragment)localObject2);
          ((Fragment)localObject2).mSavedViewState = null;
          ((Fragment)localObject2).mBackStackNesting = 0;
          ((Fragment)localObject2).mInLayout = false;
          ((Fragment)localObject2).mAdded = false;
          ((Fragment)localObject2).mTarget = null;
          if (localFragmentState.mSavedFragmentState != null)
          {
            localFragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
            ((Fragment)localObject2).mSavedViewState = localFragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            ((Fragment)localObject2).mSavedFragmentState = localFragmentState.mSavedFragmentState;
          }
          j += 1;
        }
      }
    }
    for (;;)
    {
      this.mActive = new ArrayList(localFragmentManagerState.mActive.length);
      if (this.mAvailIndices != null) {
        this.mAvailIndices.clear();
      }
      i = 0;
      if (i < localFragmentManagerState.mActive.length)
      {
        localObject2 = localFragmentManagerState.mActive[i];
        if (localObject2 != null) {
          if ((paramParcelable == null) || (i >= paramParcelable.size())) {
            break label957;
          }
        }
      }
      label481:
      label552:
      label599:
      label957:
      for (localObject1 = (FragmentManagerNonConfig)paramParcelable.get(i);; localObject1 = null)
      {
        localObject1 = ((FragmentState)localObject2).instantiate(this.mHost, this.mParent, (FragmentManagerNonConfig)localObject1);
        if (DEBUG) {
          Log.v("FragmentManager", "restoreAllState: active #" + i + ": " + localObject1);
        }
        this.mActive.add(localObject1);
        ((FragmentState)localObject2).mInstance = null;
        for (;;)
        {
          i += 1;
          break;
          this.mActive.add(null);
          if (this.mAvailIndices == null) {
            this.mAvailIndices = new ArrayList();
          }
          if (DEBUG) {
            Log.v("FragmentManager", "restoreAllState: avail #" + i);
          }
          this.mAvailIndices.add(Integer.valueOf(i));
        }
        if (paramFragmentManagerNonConfig != null)
        {
          paramParcelable = paramFragmentManagerNonConfig.getFragments();
          if (paramParcelable != null)
          {
            i = paramParcelable.size();
            j = 0;
            if (j >= i) {
              break label599;
            }
            paramFragmentManagerNonConfig = (Fragment)paramParcelable.get(j);
            if (paramFragmentManagerNonConfig.mTargetIndex >= 0) {
              if (paramFragmentManagerNonConfig.mTargetIndex >= this.mActive.size()) {
                break label552;
              }
            }
          }
          for (paramFragmentManagerNonConfig.mTarget = ((Fragment)this.mActive.get(paramFragmentManagerNonConfig.mTargetIndex));; paramFragmentManagerNonConfig.mTarget = null)
          {
            j += 1;
            break label481;
            i = 0;
            break;
            Log.w("FragmentManager", "Re-attaching retained fragment " + paramFragmentManagerNonConfig + " target no longer exists: " + paramFragmentManagerNonConfig.mTargetIndex);
          }
        }
        if (localFragmentManagerState.mAdded != null)
        {
          this.mAdded = new ArrayList(localFragmentManagerState.mAdded.length);
          i = 0;
          while (i < localFragmentManagerState.mAdded.length)
          {
            paramParcelable = (Fragment)this.mActive.get(localFragmentManagerState.mAdded[i]);
            if (paramParcelable == null) {
              throwException(new IllegalStateException("No instantiated fragment for index #" + localFragmentManagerState.mAdded[i]));
            }
            paramParcelable.mAdded = true;
            if (DEBUG) {
              Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + paramParcelable);
            }
            if (this.mAdded.contains(paramParcelable)) {
              throw new IllegalStateException("Already added!");
            }
            this.mAdded.add(paramParcelable);
            i += 1;
          }
        }
        this.mAdded = null;
        if (localFragmentManagerState.mBackStack != null)
        {
          this.mBackStack = new ArrayList(localFragmentManagerState.mBackStack.length);
          i = 0;
          while (i < localFragmentManagerState.mBackStack.length)
          {
            paramParcelable = localFragmentManagerState.mBackStack[i].instantiate(this);
            if (DEBUG)
            {
              Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + paramParcelable.mIndex + "): " + paramParcelable);
              paramFragmentManagerNonConfig = new PrintWriter(new LogWriter("FragmentManager"));
              paramParcelable.dump("  ", paramFragmentManagerNonConfig, false);
              paramFragmentManagerNonConfig.close();
            }
            this.mBackStack.add(paramParcelable);
            if (paramParcelable.mIndex >= 0) {
              setBackStackIndex(paramParcelable.mIndex, paramParcelable);
            }
            i += 1;
          }
          break;
        }
        this.mBackStack = null;
        return;
      }
      paramParcelable = null;
    }
  }
  
  FragmentManagerNonConfig retainNonConfig()
  {
    int i;
    Object localObject2;
    Object localObject1;
    Object localObject4;
    Object localObject3;
    Object localObject5;
    int j;
    if (this.mActive != null)
    {
      i = 0;
      localObject2 = null;
      localObject1 = null;
      localObject4 = localObject2;
      localObject3 = localObject1;
      if (i >= this.mActive.size()) {
        break label303;
      }
      Fragment localFragment = (Fragment)this.mActive.get(i);
      localObject5 = localObject2;
      localObject4 = localObject1;
      if (localFragment != null)
      {
        localObject3 = localObject1;
        if (localFragment.mRetainInstance)
        {
          localObject4 = localObject1;
          if (localObject1 == null) {
            localObject4 = new ArrayList();
          }
          ((ArrayList)localObject4).add(localFragment);
          localFragment.mRetaining = true;
          if (localFragment.mTarget == null) {
            break label223;
          }
        }
        label223:
        for (j = localFragment.mTarget.mIndex;; j = -1)
        {
          localFragment.mTargetIndex = j;
          localObject3 = localObject4;
          if (DEBUG)
          {
            Log.v("FragmentManager", "retainNonConfig: keeping retained " + localFragment);
            localObject3 = localObject4;
          }
          if (localFragment.mChildFragmentManager == null) {
            break label327;
          }
          localObject4 = localFragment.mChildFragmentManager.retainNonConfig();
          if (localObject4 == null) {
            break label327;
          }
          if (localObject2 != null) {
            break;
          }
          localObject2 = new ArrayList();
          j = 0;
          for (;;)
          {
            localObject1 = localObject2;
            if (j >= i) {
              break;
            }
            ((ArrayList)localObject2).add(null);
            j += 1;
          }
        }
        localObject1 = localObject2;
        ((ArrayList)localObject1).add(localObject4);
        j = 1;
        localObject2 = localObject1;
      }
    }
    for (;;)
    {
      localObject5 = localObject2;
      localObject4 = localObject3;
      if (localObject2 != null)
      {
        localObject5 = localObject2;
        localObject4 = localObject3;
        if (j == 0)
        {
          ((ArrayList)localObject2).add(null);
          localObject4 = localObject3;
          localObject5 = localObject2;
        }
      }
      i += 1;
      localObject1 = localObject4;
      localObject2 = localObject5;
      break;
      localObject4 = null;
      localObject3 = null;
      label303:
      if ((localObject3 == null) && (localObject4 == null)) {
        return null;
      }
      return new FragmentManagerNonConfig((List)localObject3, (List)localObject4);
      label327:
      j = 0;
    }
  }
  
  Parcelable saveAllState()
  {
    Object localObject3 = null;
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    if (HONEYCOMB) {
      this.mStateSaved = true;
    }
    if ((this.mActive == null) || (this.mActive.size() <= 0)) {
      return null;
    }
    int k = this.mActive.size();
    FragmentState[] arrayOfFragmentState = new FragmentState[k];
    int j = 0;
    int i = 0;
    label64:
    Object localObject1;
    Object localObject2;
    if (j < k)
    {
      localObject1 = (Fragment)this.mActive.get(j);
      if (localObject1 == null) {
        break label724;
      }
      if (((Fragment)localObject1).mIndex < 0) {
        throwException(new IllegalStateException("Failure saving state: active " + localObject1 + " has cleared index: " + ((Fragment)localObject1).mIndex));
      }
      localObject2 = new FragmentState((Fragment)localObject1);
      arrayOfFragmentState[j] = localObject2;
      if ((((Fragment)localObject1).mState > 0) && (((FragmentState)localObject2).mSavedFragmentState == null))
      {
        ((FragmentState)localObject2).mSavedFragmentState = saveFragmentBasicState((Fragment)localObject1);
        if (((Fragment)localObject1).mTarget != null)
        {
          if (((Fragment)localObject1).mTarget.mIndex < 0) {
            throwException(new IllegalStateException("Failure saving state: " + localObject1 + " has target not in fragment manager: " + ((Fragment)localObject1).mTarget));
          }
          if (((FragmentState)localObject2).mSavedFragmentState == null) {
            ((FragmentState)localObject2).mSavedFragmentState = new Bundle();
          }
          putFragment(((FragmentState)localObject2).mSavedFragmentState, "android:target_state", ((Fragment)localObject1).mTarget);
          if (((Fragment)localObject1).mTargetRequestCode != 0) {
            ((FragmentState)localObject2).mSavedFragmentState.putInt("android:target_req_state", ((Fragment)localObject1).mTargetRequestCode);
          }
        }
        label309:
        if (DEBUG) {
          Log.v("FragmentManager", "Saved state of " + localObject1 + ": " + ((FragmentState)localObject2).mSavedFragmentState);
        }
        i = 1;
      }
    }
    label724:
    for (;;)
    {
      j += 1;
      break label64;
      ((FragmentState)localObject2).mSavedFragmentState = ((Fragment)localObject1).mSavedFragmentState;
      break label309;
      if (i == 0)
      {
        if (!DEBUG) {
          break;
        }
        Log.v("FragmentManager", "saveAllState: no fragments!");
        return null;
      }
      if (this.mAdded != null)
      {
        j = this.mAdded.size();
        if (j > 0)
        {
          localObject2 = new int[j];
          i = 0;
          for (;;)
          {
            localObject1 = localObject2;
            if (i >= j) {
              break;
            }
            localObject2[i] = ((Fragment)this.mAdded.get(i)).mIndex;
            if (localObject2[i] < 0) {
              throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i) + " has cleared index: " + localObject2[i]));
            }
            if (DEBUG) {
              Log.v("FragmentManager", "saveAllState: adding fragment #" + i + ": " + this.mAdded.get(i));
            }
            i += 1;
          }
        }
      }
      localObject1 = null;
      localObject2 = localObject3;
      if (this.mBackStack != null)
      {
        j = this.mBackStack.size();
        localObject2 = localObject3;
        if (j > 0)
        {
          localObject3 = new BackStackState[j];
          i = 0;
          for (;;)
          {
            localObject2 = localObject3;
            if (i >= j) {
              break;
            }
            localObject3[i] = new BackStackState((BackStackRecord)this.mBackStack.get(i));
            if (DEBUG) {
              Log.v("FragmentManager", "saveAllState: adding back stack #" + i + ": " + this.mBackStack.get(i));
            }
            i += 1;
          }
        }
      }
      localObject3 = new FragmentManagerState();
      ((FragmentManagerState)localObject3).mActive = arrayOfFragmentState;
      ((FragmentManagerState)localObject3).mAdded = ((int[])localObject1);
      ((FragmentManagerState)localObject3).mBackStack = ((BackStackState[])localObject2);
      return (Parcelable)localObject3;
    }
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment)
  {
    if (this.mStateBundle == null) {
      this.mStateBundle = new Bundle();
    }
    paramFragment.performSaveInstanceState(this.mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, this.mStateBundle, false);
    Object localObject2;
    if (!this.mStateBundle.isEmpty())
    {
      localObject2 = this.mStateBundle;
      this.mStateBundle = null;
    }
    for (;;)
    {
      if (paramFragment.mView != null) {
        saveFragmentViewState(paramFragment);
      }
      Object localObject1 = localObject2;
      if (paramFragment.mSavedViewState != null)
      {
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new Bundle();
        }
        ((Bundle)localObject1).putSparseParcelableArray("android:view_state", paramFragment.mSavedViewState);
      }
      localObject2 = localObject1;
      if (!paramFragment.mUserVisibleHint)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new Bundle();
        }
        ((Bundle)localObject2).putBoolean("android:user_visible_hint", paramFragment.mUserVisibleHint);
      }
      return (Bundle)localObject2;
      localObject2 = null;
    }
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment)
  {
    Object localObject2 = null;
    if (paramFragment.mIndex < 0) {
      throwException(new IllegalStateException("Fragment " + paramFragment + " is not currently in the FragmentManager"));
    }
    Object localObject1 = localObject2;
    if (paramFragment.mState > 0)
    {
      paramFragment = saveFragmentBasicState(paramFragment);
      localObject1 = localObject2;
      if (paramFragment != null) {
        localObject1 = new Fragment.SavedState(paramFragment);
      }
    }
    return (Fragment.SavedState)localObject1;
  }
  
  void saveFragmentViewState(Fragment paramFragment)
  {
    if (paramFragment.mInnerView == null) {
      return;
    }
    if (this.mStateArray == null) {
      this.mStateArray = new SparseArray();
    }
    for (;;)
    {
      paramFragment.mInnerView.saveHierarchyState(this.mStateArray);
      if (this.mStateArray.size() <= 0) {
        break;
      }
      paramFragment.mSavedViewState = this.mStateArray;
      this.mStateArray = null;
      return;
      this.mStateArray.clear();
    }
  }
  
  /* Error */
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   6: ifnonnull +14 -> 20
    //   9: aload_0
    //   10: new 183	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 352	java/util/ArrayList:<init>	()V
    //   17: putfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   20: aload_0
    //   21: getfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   24: invokevirtual 222	java/util/ArrayList:size	()I
    //   27: istore 4
    //   29: iload 4
    //   31: istore_3
    //   32: iload_1
    //   33: iload 4
    //   35: if_icmpge +58 -> 93
    //   38: getstatic 119	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   41: ifeq +39 -> 80
    //   44: ldc 55
    //   46: new 238	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 239	java/lang/StringBuilder:<init>	()V
    //   53: ldc_w 715
    //   56: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: iload_1
    //   60: invokevirtual 718	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   63: ldc_w 720
    //   66: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_2
    //   70: invokevirtual 672	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 249	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokestatic 675	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: aload_0
    //   81: getfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   84: iload_1
    //   85: aload_2
    //   86: invokevirtual 733	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   89: pop
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: iload_3
    //   94: iload_1
    //   95: if_icmpge +81 -> 176
    //   98: aload_0
    //   99: getfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   102: aconst_null
    //   103: invokevirtual 263	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   106: pop
    //   107: aload_0
    //   108: getfield 711	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   111: ifnonnull +14 -> 125
    //   114: aload_0
    //   115: new 183	java/util/ArrayList
    //   118: dup
    //   119: invokespecial 352	java/util/ArrayList:<init>	()V
    //   122: putfield 711	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   125: getstatic 119	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   128: ifeq +29 -> 157
    //   131: ldc 55
    //   133: new 238	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 239	java/lang/StringBuilder:<init>	()V
    //   140: ldc_w 1686
    //   143: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: iload_3
    //   147: invokevirtual 718	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   150: invokevirtual 249	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 675	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload_0
    //   158: getfield 711	android/support/v4/app/FragmentManagerImpl:mAvailBackStackIndices	Ljava/util/ArrayList;
    //   161: iload_3
    //   162: invokestatic 1081	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   165: invokevirtual 263	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   168: pop
    //   169: iload_3
    //   170: iconst_1
    //   171: iadd
    //   172: istore_3
    //   173: goto -80 -> 93
    //   176: getstatic 119	android/support/v4/app/FragmentManagerImpl:DEBUG	Z
    //   179: ifeq +39 -> 218
    //   182: ldc 55
    //   184: new 238	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 239	java/lang/StringBuilder:<init>	()V
    //   191: ldc_w 727
    //   194: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: iload_1
    //   198: invokevirtual 718	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   201: ldc_w 729
    //   204: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_2
    //   208: invokevirtual 672	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   211: invokevirtual 249	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 675	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   217: pop
    //   218: aload_0
    //   219: getfield 713	android/support/v4/app/FragmentManagerImpl:mBackStackIndices	Ljava/util/ArrayList;
    //   222: aload_2
    //   223: invokevirtual 263	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   226: pop
    //   227: goto -137 -> 90
    //   230: astore_2
    //   231: aload_0
    //   232: monitorexit
    //   233: aload_2
    //   234: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	235	0	this	FragmentManagerImpl
    //   0	235	1	paramInt	int
    //   0	235	2	paramBackStackRecord	BackStackRecord
    //   31	142	3	i	int
    //   27	9	4	j	int
    // Exception table:
    //   from	to	target	type
    //   2	20	230	finally
    //   20	29	230	finally
    //   38	80	230	finally
    //   80	90	230	finally
    //   90	92	230	finally
    //   98	125	230	finally
    //   125	157	230	finally
    //   157	169	230	finally
    //   176	218	230	finally
    //   218	227	230	finally
    //   231	233	230	finally
  }
  
  public void showFragment(Fragment paramFragment)
  {
    boolean bool = false;
    if (DEBUG) {
      Log.v("FragmentManager", "show: " + paramFragment);
    }
    if (paramFragment.mHidden)
    {
      paramFragment.mHidden = false;
      if (!paramFragment.mHiddenChanged) {
        bool = true;
      }
      paramFragment.mHiddenChanged = bool;
    }
  }
  
  void startPendingDeferredFragments()
  {
    if (this.mActive == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < this.mActive.size())
      {
        Fragment localFragment = (Fragment)this.mActive.get(i);
        if (localFragment != null) {
          performPendingDeferredStart(localFragment);
        }
        i += 1;
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("FragmentManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    if (this.mParent != null) {
      DebugUtils.buildShortClassTag(this.mParent, localStringBuilder);
    }
    for (;;)
    {
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
      DebugUtils.buildShortClassTag(this.mHost, localStringBuilder);
    }
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    if (this.mLifecycleCallbacks == null) {
      return;
    }
    for (;;)
    {
      int i;
      synchronized (this.mLifecycleCallbacks)
      {
        int j = this.mLifecycleCallbacks.size();
        i = 0;
        if (i < j)
        {
          if (((Pair)this.mLifecycleCallbacks.get(i)).first == paramFragmentLifecycleCallbacks) {
            this.mLifecycleCallbacks.remove(i);
          }
        }
        else {
          return;
        }
      }
      i += 1;
    }
  }
  
  static class AnimateOnHWLayerIfNeededListener
    implements Animation.AnimationListener
  {
    private Animation.AnimationListener mOriginalListener;
    private boolean mShouldRunOnHWLayer;
    View mView;
    
    public AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation)
    {
      if ((paramView == null) || (paramAnimation == null)) {
        return;
      }
      this.mView = paramView;
    }
    
    public AnimateOnHWLayerIfNeededListener(View paramView, Animation paramAnimation, Animation.AnimationListener paramAnimationListener)
    {
      if ((paramView == null) || (paramAnimation == null)) {
        return;
      }
      this.mOriginalListener = paramAnimationListener;
      this.mView = paramView;
      this.mShouldRunOnHWLayer = true;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation paramAnimation)
    {
      if ((this.mView != null) && (this.mShouldRunOnHWLayer))
      {
        if ((!ViewCompat.isAttachedToWindow(this.mView)) && (!BuildCompat.isAtLeastN())) {
          break label64;
        }
        this.mView.post(new Runnable()
        {
          public void run()
          {
            ViewCompat.setLayerType(FragmentManagerImpl.AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
          }
        });
      }
      for (;;)
      {
        if (this.mOriginalListener != null) {
          this.mOriginalListener.onAnimationEnd(paramAnimation);
        }
        return;
        label64:
        ViewCompat.setLayerType(this.mView, 0, null);
      }
    }
    
    public void onAnimationRepeat(Animation paramAnimation)
    {
      if (this.mOriginalListener != null) {
        this.mOriginalListener.onAnimationRepeat(paramAnimation);
      }
    }
    
    @CallSuper
    public void onAnimationStart(Animation paramAnimation)
    {
      if (this.mOriginalListener != null) {
        this.mOriginalListener.onAnimationStart(paramAnimation);
      }
    }
  }
  
  static class FragmentTag
  {
    public static final int[] Fragment = { 16842755, 16842960, 16842961 };
    public static final int Fragment_id = 1;
    public static final int Fragment_name = 0;
    public static final int Fragment_tag = 2;
  }
  
  static abstract interface OpGenerator
  {
    public abstract boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1);
  }
  
  private class PopBackStackState
    implements FragmentManagerImpl.OpGenerator
  {
    final int mFlags;
    final int mId;
    final String mName;
    
    PopBackStackState(String paramString, int paramInt1, int paramInt2)
    {
      this.mName = paramString;
      this.mId = paramInt1;
      this.mFlags = paramInt2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
    {
      return FragmentManagerImpl.this.popBackStackState(paramArrayList, paramArrayList1, this.mName, this.mId, this.mFlags);
    }
  }
  
  static class StartEnterTransitionListener
    implements Fragment.OnStartEnterTransitionListener
  {
    private final boolean mIsBack;
    private int mNumPostponed;
    private final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord paramBackStackRecord, boolean paramBoolean)
    {
      this.mIsBack = paramBoolean;
      this.mRecord = paramBackStackRecord;
    }
    
    public void cancelTransaction()
    {
      this.mRecord.mManager.completeExecute(this.mRecord, this.mIsBack, false, false);
    }
    
    public void completeTransaction()
    {
      boolean bool1 = false;
      if (this.mNumPostponed > 0) {}
      for (int i = 1;; i = 0)
      {
        localFragmentManagerImpl = this.mRecord.mManager;
        int k = localFragmentManagerImpl.mAdded.size();
        int j = 0;
        while (j < k)
        {
          localObject = (Fragment)localFragmentManagerImpl.mAdded.get(j);
          ((Fragment)localObject).setOnStartEnterTransitionListener(null);
          if ((i != 0) && (((Fragment)localObject).isPostponed())) {
            ((Fragment)localObject).startPostponedEnterTransition();
          }
          j += 1;
        }
      }
      FragmentManagerImpl localFragmentManagerImpl = this.mRecord.mManager;
      Object localObject = this.mRecord;
      boolean bool2 = this.mIsBack;
      if (i == 0) {
        bool1 = true;
      }
      localFragmentManagerImpl.completeExecute((BackStackRecord)localObject, bool2, bool1, true);
    }
    
    public boolean isReady()
    {
      return this.mNumPostponed == 0;
    }
    
    public void onStartEnterTransition()
    {
      this.mNumPostponed -= 1;
      if (this.mNumPostponed != 0) {
        return;
      }
      this.mRecord.mManager.scheduleCommit();
    }
    
    public void startListening()
    {
      this.mNumPostponed += 1;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\app\FragmentManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */