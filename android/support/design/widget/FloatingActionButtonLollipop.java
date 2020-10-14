package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

@TargetApi(21)
@RequiresApi(21)
class FloatingActionButtonLollipop
  extends FloatingActionButtonIcs
{
  private InsetDrawable mInsetDrawable;
  
  FloatingActionButtonLollipop(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate, ValueAnimatorCompat.Creator paramCreator)
  {
    super(paramVisibilityAwareImageButton, paramShadowViewDelegate, paramCreator);
  }
  
  public float getElevation()
  {
    return this.mView.getElevation();
  }
  
  void getPadding(Rect paramRect)
  {
    if (this.mShadowViewDelegate.isCompatPaddingEnabled())
    {
      float f1 = this.mShadowViewDelegate.getRadius();
      float f2 = getElevation() + this.mPressedTranslationZ;
      int i = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(f2, f1, false));
      int j = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(f2, f1, false));
      paramRect.set(i, j, i, j);
      return;
    }
    paramRect.set(0, 0, 0, 0);
  }
  
  void jumpDrawableToCurrentState() {}
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawableLollipop();
  }
  
  GradientDrawable newGradientDrawableForShape()
  {
    return new AlwaysStatefulGradientDrawable();
  }
  
  void onCompatShadowChanged()
  {
    updatePadding();
  }
  
  void onDrawableStateChanged(int[] paramArrayOfInt) {}
  
  void onElevationsChanged(float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT == 21) {
      if (this.mView.isEnabled())
      {
        this.mView.setElevation(paramFloat1);
        if ((this.mView.isFocused()) || (this.mView.isPressed())) {
          this.mView.setTranslationZ(paramFloat2);
        }
      }
    }
    for (;;)
    {
      if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
        updatePadding();
      }
      return;
      this.mView.setTranslationZ(0.0F);
      continue;
      this.mView.setElevation(0.0F);
      this.mView.setTranslationZ(0.0F);
      continue;
      StateListAnimator localStateListAnimator = new StateListAnimator();
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[] { paramFloat2 }).setDuration(100L));
      localAnimatorSet.setInterpolator(ANIM_INTERPOLATOR);
      localStateListAnimator.addState(PRESSED_ENABLED_STATE_SET, localAnimatorSet);
      localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[] { paramFloat2 }).setDuration(100L));
      localAnimatorSet.setInterpolator(ANIM_INTERPOLATOR);
      localStateListAnimator.addState(FOCUSED_ENABLED_STATE_SET, localAnimatorSet);
      localAnimatorSet = new AnimatorSet();
      localAnimatorSet.playSequentially(new Animator[] { ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { paramFloat1 }).setDuration(0L), ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[] { this.mView.getTranslationZ() }).setDuration(100L), ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[] { 0.0F }).setDuration(100L) });
      localAnimatorSet.setInterpolator(ANIM_INTERPOLATOR);
      localStateListAnimator.addState(ENABLED_STATE_SET, localAnimatorSet);
      localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play(ObjectAnimator.ofFloat(this.mView, "elevation", new float[] { 0.0F }).setDuration(0L)).with(ObjectAnimator.ofFloat(this.mView, View.TRANSLATION_Z, new float[] { 0.0F }).setDuration(0L));
      localAnimatorSet.setInterpolator(ANIM_INTERPOLATOR);
      localStateListAnimator.addState(EMPTY_STATE_SET, localAnimatorSet);
      this.mView.setStateListAnimator(localStateListAnimator);
    }
  }
  
  void onPaddingUpdated(Rect paramRect)
  {
    if (this.mShadowViewDelegate.isCompatPaddingEnabled())
    {
      this.mInsetDrawable = new InsetDrawable(this.mRippleDrawable, paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
      this.mShadowViewDelegate.setBackgroundDrawable(this.mInsetDrawable);
      return;
    }
    this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
  }
  
  boolean requirePreDrawListener()
  {
    return false;
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int paramInt1, int paramInt2)
  {
    this.mShapeDrawable = DrawableCompat.wrap(createShapeDrawable());
    DrawableCompat.setTintList(this.mShapeDrawable, paramColorStateList);
    if (paramMode != null) {
      DrawableCompat.setTintMode(this.mShapeDrawable, paramMode);
    }
    if (paramInt2 > 0) {
      this.mBorderDrawable = createBorderDrawable(paramInt2, paramColorStateList);
    }
    for (paramColorStateList = new LayerDrawable(new Drawable[] { this.mBorderDrawable, this.mShapeDrawable });; paramColorStateList = this.mShapeDrawable)
    {
      this.mRippleDrawable = new RippleDrawable(ColorStateList.valueOf(paramInt1), paramColorStateList, null);
      this.mContentBackground = this.mRippleDrawable;
      this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
      return;
      this.mBorderDrawable = null;
    }
  }
  
  void setRippleColor(int paramInt)
  {
    if ((this.mRippleDrawable instanceof RippleDrawable))
    {
      ((RippleDrawable)this.mRippleDrawable).setColor(ColorStateList.valueOf(paramInt));
      return;
    }
    super.setRippleColor(paramInt);
  }
  
  static class AlwaysStatefulGradientDrawable
    extends GradientDrawable
  {
    public boolean isStateful()
    {
      return true;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\FloatingActionButtonLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */