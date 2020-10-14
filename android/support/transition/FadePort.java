package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Map;

@TargetApi(14)
@RequiresApi(14)
class FadePort
  extends VisibilityPort
{
  private static boolean DBG = false;
  public static final int IN = 1;
  private static final String LOG_TAG = "Fade";
  public static final int OUT = 2;
  private static final String PROPNAME_SCREEN_X = "android:fade:screenX";
  private static final String PROPNAME_SCREEN_Y = "android:fade:screenY";
  private int mFadingMode;
  
  public FadePort()
  {
    this(3);
  }
  
  public FadePort(int paramInt)
  {
    this.mFadingMode = paramInt;
  }
  
  private void captureValues(TransitionValues paramTransitionValues)
  {
    int[] arrayOfInt = new int[2];
    paramTransitionValues.view.getLocationOnScreen(arrayOfInt);
    paramTransitionValues.values.put("android:fade:screenX", Integer.valueOf(arrayOfInt[0]));
    paramTransitionValues.values.put("android:fade:screenY", Integer.valueOf(arrayOfInt[1]));
  }
  
  private Animator createAnimation(View paramView, float paramFloat1, float paramFloat2, AnimatorListenerAdapter paramAnimatorListenerAdapter)
  {
    ObjectAnimator localObjectAnimator = null;
    if (paramFloat1 == paramFloat2)
    {
      paramView = localObjectAnimator;
      if (paramAnimatorListenerAdapter != null)
      {
        paramAnimatorListenerAdapter.onAnimationEnd(null);
        paramView = localObjectAnimator;
      }
    }
    do
    {
      return paramView;
      localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { paramFloat1, paramFloat2 });
      if (DBG) {
        Log.d("Fade", "Created animator " + localObjectAnimator);
      }
      paramView = localObjectAnimator;
    } while (paramAnimatorListenerAdapter == null);
    localObjectAnimator.addListener(paramAnimatorListenerAdapter);
    return localObjectAnimator;
  }
  
  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    super.captureStartValues(paramTransitionValues);
    captureValues(paramTransitionValues);
  }
  
  public Animator onAppear(ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, final TransitionValues paramTransitionValues2, int paramInt2)
  {
    if (((this.mFadingMode & 0x1) != 1) || (paramTransitionValues2 == null)) {
      return null;
    }
    paramTransitionValues2 = paramTransitionValues2.view;
    if (DBG) {
      if (paramTransitionValues1 == null) {
        break label124;
      }
    }
    label124:
    for (paramViewGroup = paramTransitionValues1.view;; paramViewGroup = null)
    {
      Log.d("Fade", "Fade.onAppear: startView, startVis, endView, endVis = " + paramViewGroup + ", " + paramInt1 + ", " + paramTransitionValues2 + ", " + paramInt2);
      paramTransitionValues2.setAlpha(0.0F);
      addListener(new TransitionPort.TransitionListenerAdapter()
      {
        boolean mCanceled = false;
        float mPausedAlpha;
        
        public void onTransitionCancel(TransitionPort paramAnonymousTransitionPort)
        {
          paramTransitionValues2.setAlpha(1.0F);
          this.mCanceled = true;
        }
        
        public void onTransitionEnd(TransitionPort paramAnonymousTransitionPort)
        {
          if (!this.mCanceled) {
            paramTransitionValues2.setAlpha(1.0F);
          }
        }
        
        public void onTransitionPause(TransitionPort paramAnonymousTransitionPort)
        {
          this.mPausedAlpha = paramTransitionValues2.getAlpha();
          paramTransitionValues2.setAlpha(1.0F);
        }
        
        public void onTransitionResume(TransitionPort paramAnonymousTransitionPort)
        {
          paramTransitionValues2.setAlpha(this.mPausedAlpha);
        }
      });
      return createAnimation(paramTransitionValues2, 0.0F, 1.0F, null);
    }
  }
  
  public Animator onDisappear(final ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, int paramInt1, final TransitionValues paramTransitionValues2, final int paramInt2)
  {
    if ((this.mFadingMode & 0x2) != 2) {
      return null;
    }
    final Object localObject1;
    label22:
    label34:
    Object localObject3;
    final Object localObject2;
    if (paramTransitionValues1 != null)
    {
      localObject1 = paramTransitionValues1.view;
      if (paramTransitionValues2 == null) {
        break label253;
      }
      paramTransitionValues2 = paramTransitionValues2.view;
      if (DBG) {
        Log.d("Fade", "Fade.onDisappear: startView, startVis, endView, endVis = " + localObject1 + ", " + paramInt1 + ", " + paramTransitionValues2 + ", " + paramInt2);
      }
      if ((paramTransitionValues2 != null) && (paramTransitionValues2.getParent() != null)) {
        break label372;
      }
      if (paramTransitionValues2 == null) {
        break label259;
      }
      localObject3 = null;
      localObject1 = paramTransitionValues2;
      localObject2 = paramTransitionValues2;
      paramTransitionValues2 = (TransitionValues)localObject3;
    }
    for (;;)
    {
      label128:
      if (localObject1 != null)
      {
        paramInt1 = ((Integer)paramTransitionValues1.values.get("android:fade:screenX")).intValue();
        int i = ((Integer)paramTransitionValues1.values.get("android:fade:screenY")).intValue();
        paramTransitionValues1 = new int[2];
        paramViewGroup.getLocationOnScreen(paramTransitionValues1);
        ViewCompat.offsetLeftAndRight((View)localObject1, paramInt1 - paramTransitionValues1[0] - ((View)localObject1).getLeft());
        ViewCompat.offsetTopAndBottom((View)localObject1, i - paramTransitionValues1[1] - ((View)localObject1).getTop());
        ViewGroupOverlay.createFrom(paramViewGroup).add((View)localObject1);
        createAnimation((View)localObject2, 1.0F, 0.0F, new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            localObject2.setAlpha(1.0F);
            if (paramTransitionValues2 != null) {
              paramTransitionValues2.setVisibility(paramInt2);
            }
            if (localObject1 != null) {
              ViewGroupOverlay.createFrom(paramViewGroup).remove(localObject1);
            }
          }
        });
        localObject1 = null;
        break label22;
        label253:
        paramTransitionValues2 = null;
        break label34;
        label259:
        if (localObject1 == null) {
          break label493;
        }
        if (((View)localObject1).getParent() == null)
        {
          paramTransitionValues2 = null;
          localObject3 = localObject1;
          localObject2 = localObject1;
          localObject1 = localObject3;
          continue;
        }
        if ((!(((View)localObject1).getParent() instanceof View)) || (((View)localObject1).getParent().getParent() != null)) {
          break label493;
        }
        paramInt1 = ((View)((View)localObject1).getParent()).getId();
        if ((paramInt1 == -1) || (paramViewGroup.findViewById(paramInt1) == null) || (!this.mCanRemoveViews)) {
          break label484;
        }
        paramTransitionValues2 = (TransitionValues)localObject1;
      }
      for (;;)
      {
        Object localObject4 = null;
        localObject3 = paramTransitionValues2;
        localObject2 = localObject1;
        paramTransitionValues2 = (TransitionValues)localObject4;
        localObject1 = localObject3;
        break label128;
        label372:
        if (paramInt2 == 4)
        {
          localObject1 = paramTransitionValues2;
          localObject3 = null;
          localObject2 = paramTransitionValues2;
          paramTransitionValues2 = (TransitionValues)localObject1;
          localObject1 = localObject3;
          break label128;
        }
        if (localObject1 == paramTransitionValues2)
        {
          localObject1 = paramTransitionValues2;
          localObject3 = null;
          localObject2 = paramTransitionValues2;
          paramTransitionValues2 = (TransitionValues)localObject1;
          localObject1 = localObject3;
          break label128;
        }
        paramTransitionValues2 = null;
        localObject3 = localObject1;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break label128;
        if (paramTransitionValues2 == null) {
          break;
        }
        paramTransitionValues2.setVisibility(0);
        createAnimation((View)localObject2, 1.0F, 0.0F, new AnimatorListenerAdapter()
        {
          boolean mCanceled = false;
          float mPausedAlpha = -1.0F;
          
          public void onAnimationCancel(Animator paramAnonymousAnimator)
          {
            this.mCanceled = true;
            if (this.mPausedAlpha >= 0.0F) {
              localObject2.setAlpha(this.mPausedAlpha);
            }
          }
          
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            if (!this.mCanceled) {
              localObject2.setAlpha(1.0F);
            }
            if ((paramTransitionValues2 != null) && (!this.mCanceled)) {
              paramTransitionValues2.setVisibility(paramInt2);
            }
            if (localObject1 != null) {
              ViewGroupOverlay.createFrom(paramViewGroup).add(localObject1);
            }
          }
        });
        label484:
        paramTransitionValues2 = null;
        localObject1 = null;
      }
      label493:
      paramTransitionValues2 = null;
      localObject1 = null;
      localObject2 = null;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\FadePort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */