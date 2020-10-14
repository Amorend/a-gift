package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.ViewPropertyAnimator;

@TargetApi(14)
@RequiresApi(14)
class FloatingActionButtonIcs
  extends FloatingActionButtonGingerbread
{
  private float mRotation = this.mView.getRotation();
  
  FloatingActionButtonIcs(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate, ValueAnimatorCompat.Creator paramCreator)
  {
    super(paramVisibilityAwareImageButton, paramShadowViewDelegate, paramCreator);
  }
  
  private boolean shouldAnimateVisibilityChange()
  {
    return (ViewCompat.isLaidOut(this.mView)) && (!this.mView.isInEditMode());
  }
  
  private void updateFromViewRotation()
  {
    if (Build.VERSION.SDK_INT == 19)
    {
      if (this.mRotation % 90.0F == 0.0F) {
        break label79;
      }
      if (this.mView.getLayerType() != 1) {
        this.mView.setLayerType(1, null);
      }
    }
    for (;;)
    {
      if (this.mShadowDrawable != null) {
        this.mShadowDrawable.setRotation(-this.mRotation);
      }
      if (this.mBorderDrawable != null) {
        this.mBorderDrawable.setRotation(-this.mRotation);
      }
      return;
      label79:
      if (this.mView.getLayerType() != 0) {
        this.mView.setLayerType(0, null);
      }
    }
  }
  
  void hide(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener, final boolean paramBoolean)
  {
    if (isOrWillBeHidden()) {
      return;
    }
    this.mView.animate().cancel();
    if (shouldAnimateVisibilityChange())
    {
      this.mAnimState = 1;
      this.mView.animate().scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
      {
        private boolean mCancelled;
        
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
          this.mCancelled = true;
        }
        
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          FloatingActionButtonIcs.this.mAnimState = 0;
          if (!this.mCancelled)
          {
            paramAnonymousAnimator = FloatingActionButtonIcs.this.mView;
            if (!paramBoolean) {
              break label59;
            }
          }
          label59:
          for (int i = 8;; i = 4)
          {
            paramAnonymousAnimator.internalSetVisibility(i, paramBoolean);
            if (paramInternalVisibilityChangedListener != null) {
              paramInternalVisibilityChangedListener.onHidden();
            }
            return;
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          FloatingActionButtonIcs.this.mView.internalSetVisibility(0, paramBoolean);
          this.mCancelled = false;
        }
      });
      return;
    }
    VisibilityAwareImageButton localVisibilityAwareImageButton = this.mView;
    if (paramBoolean) {}
    for (int i = 8;; i = 4)
    {
      localVisibilityAwareImageButton.internalSetVisibility(i, paramBoolean);
      if (paramInternalVisibilityChangedListener == null) {
        break;
      }
      paramInternalVisibilityChangedListener.onHidden();
      return;
    }
  }
  
  void onPreDraw()
  {
    float f = this.mView.getRotation();
    if (this.mRotation != f)
    {
      this.mRotation = f;
      updateFromViewRotation();
    }
  }
  
  boolean requirePreDrawListener()
  {
    return true;
  }
  
  void show(@Nullable final FloatingActionButtonImpl.InternalVisibilityChangedListener paramInternalVisibilityChangedListener, final boolean paramBoolean)
  {
    if (isOrWillBeShown()) {}
    do
    {
      return;
      this.mView.animate().cancel();
      if (shouldAnimateVisibilityChange())
      {
        this.mAnimState = 2;
        if (this.mView.getVisibility() != 0)
        {
          this.mView.setAlpha(0.0F);
          this.mView.setScaleY(0.0F);
          this.mView.setScaleX(0.0F);
        }
        this.mView.animate().scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            FloatingActionButtonIcs.this.mAnimState = 0;
            if (paramInternalVisibilityChangedListener != null) {
              paramInternalVisibilityChangedListener.onShown();
            }
          }
          
          public void onAnimationStart(Animator paramAnonymousAnimator)
          {
            FloatingActionButtonIcs.this.mView.internalSetVisibility(0, paramBoolean);
          }
        });
        return;
      }
      this.mView.internalSetVisibility(0, paramBoolean);
      this.mView.setAlpha(1.0F);
      this.mView.setScaleY(1.0F);
      this.mView.setScaleX(1.0F);
    } while (paramInternalVisibilityChangedListener == null);
    paramInternalVisibilityChangedListener.onShown();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\FloatingActionButtonIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */