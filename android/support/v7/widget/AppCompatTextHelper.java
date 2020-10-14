package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

@TargetApi(9)
@RequiresApi(9)
class AppCompatTextHelper
{
  private TintInfo mDrawableBottomTint;
  private TintInfo mDrawableLeftTint;
  private TintInfo mDrawableRightTint;
  private TintInfo mDrawableTopTint;
  final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView)
  {
    this.mView = paramTextView;
  }
  
  static AppCompatTextHelper create(TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return new AppCompatTextHelperV17(paramTextView);
    }
    return new AppCompatTextHelper(paramTextView);
  }
  
  protected static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt)
  {
    paramContext = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (paramContext != null)
    {
      paramAppCompatDrawableManager = new TintInfo();
      paramAppCompatDrawableManager.mHasTintList = true;
      paramAppCompatDrawableManager.mTintList = paramContext;
      return paramAppCompatDrawableManager;
    }
    return null;
  }
  
  final void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo)
  {
    if ((paramDrawable != null) && (paramTintInfo != null)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, this.mView.getDrawableState());
    }
  }
  
  void applyCompoundDrawablesTints()
  {
    if ((this.mDrawableLeftTint != null) || (this.mDrawableTopTint != null) || (this.mDrawableRightTint != null) || (this.mDrawableBottomTint != null))
    {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], this.mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], this.mDrawableBottomTint);
    }
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject4 = this.mView.getContext();
    Object localObject1 = AppCompatDrawableManager.get();
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes((Context)localObject4, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int i = localTintTypedArray.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (localTintTypedArray.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
      this.mDrawableLeftTint = createTintInfo((Context)localObject4, (AppCompatDrawableManager)localObject1, localTintTypedArray.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
    }
    if (localTintTypedArray.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
      this.mDrawableTopTint = createTintInfo((Context)localObject4, (AppCompatDrawableManager)localObject1, localTintTypedArray.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
    }
    if (localTintTypedArray.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
      this.mDrawableRightTint = createTintInfo((Context)localObject4, (AppCompatDrawableManager)localObject1, localTintTypedArray.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
    }
    if (localTintTypedArray.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
      this.mDrawableBottomTint = createTintInfo((Context)localObject4, (AppCompatDrawableManager)localObject1, localTintTypedArray.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
    }
    localTintTypedArray.recycle();
    boolean bool3 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
    boolean bool1;
    if (i != -1)
    {
      localTintTypedArray = TintTypedArray.obtainStyledAttributes((Context)localObject4, i, R.styleable.TextAppearance);
      if ((!bool3) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)))
      {
        bool1 = localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        i = 1;
        if (Build.VERSION.SDK_INT < 23) {
          if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor))
          {
            localObject1 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
            label258:
            localObject3 = localObject1;
            if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorHint))
            {
              localObject2 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
              localObject3 = localObject1;
            }
            label287:
            localTintTypedArray.recycle();
            localObject1 = localObject3;
          }
        }
      }
    }
    for (;;)
    {
      localObject4 = TintTypedArray.obtainStyledAttributes((Context)localObject4, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
      paramInt = i;
      boolean bool2 = bool1;
      if (!bool3)
      {
        paramInt = i;
        bool2 = bool1;
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_textAllCaps))
        {
          bool2 = ((TintTypedArray)localObject4).getBoolean(R.styleable.TextAppearance_textAllCaps, false);
          paramInt = 1;
        }
      }
      paramAttributeSet = (AttributeSet)localObject1;
      localObject3 = localObject2;
      if (Build.VERSION.SDK_INT < 23)
      {
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColor)) {
          localObject1 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColor);
        }
        paramAttributeSet = (AttributeSet)localObject1;
        localObject3 = localObject2;
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColorHint))
        {
          localObject3 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColorHint);
          paramAttributeSet = (AttributeSet)localObject1;
        }
      }
      ((TintTypedArray)localObject4).recycle();
      if (paramAttributeSet != null) {
        this.mView.setTextColor(paramAttributeSet);
      }
      if (localObject3 != null) {
        this.mView.setHintTextColor((ColorStateList)localObject3);
      }
      if ((!bool3) && (paramInt != 0)) {
        setAllCaps(bool2);
      }
      return;
      localObject1 = null;
      break label258;
      localObject3 = null;
      break label287;
      i = 0;
      bool1 = false;
      break;
      localObject1 = null;
      i = 0;
      bool1 = false;
      localObject2 = localObject3;
    }
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt)
  {
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (paramContext.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(paramContext.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    if ((Build.VERSION.SDK_INT < 23) && (paramContext.hasValue(R.styleable.TextAppearance_android_textColor)))
    {
      ColorStateList localColorStateList = paramContext.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (localColorStateList != null) {
        this.mView.setTextColor(localColorStateList);
      }
    }
    paramContext.recycle();
  }
  
  void setAllCaps(boolean paramBoolean)
  {
    TextView localTextView = this.mView;
    if (paramBoolean) {}
    for (AllCapsTransformationMethod localAllCapsTransformationMethod = new AllCapsTransformationMethod(this.mView.getContext());; localAllCapsTransformationMethod = null)
    {
      localTextView.setTransformationMethod(localAllCapsTransformationMethod);
      return;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\AppCompatTextHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */