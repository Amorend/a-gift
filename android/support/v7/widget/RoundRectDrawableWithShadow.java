package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.cardview.R.color;
import android.support.v7.cardview.R.dimen;

@TargetApi(9)
@RequiresApi(9)
class RoundRectDrawableWithShadow
  extends Drawable
{
  static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  static final float SHADOW_MULTIPLIER = 1.5F;
  static RoundRectHelper sRoundRectHelper;
  private boolean mAddPaddingForCorners = true;
  private ColorStateList mBackground;
  final RectF mCardBounds;
  float mCornerRadius;
  Paint mCornerShadowPaint;
  Path mCornerShadowPath;
  private boolean mDirty = true;
  Paint mEdgeShadowPaint;
  final int mInsetShadow;
  float mMaxShadowSize;
  Paint mPaint;
  private boolean mPrintedShadowClipWarning = false;
  float mRawMaxShadowSize;
  float mRawShadowSize;
  private final int mShadowEndColor;
  float mShadowSize;
  private final int mShadowStartColor;
  
  RoundRectDrawableWithShadow(Resources paramResources, ColorStateList paramColorStateList, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mShadowStartColor = paramResources.getColor(R.color.cardview_shadow_start_color);
    this.mShadowEndColor = paramResources.getColor(R.color.cardview_shadow_end_color);
    this.mInsetShadow = paramResources.getDimensionPixelSize(R.dimen.cardview_compat_inset_shadow);
    this.mPaint = new Paint(5);
    setBackground(paramColorStateList);
    this.mCornerShadowPaint = new Paint(5);
    this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
    this.mCornerRadius = ((int)(0.5F + paramFloat1));
    this.mCardBounds = new RectF();
    this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
    this.mEdgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect)
  {
    float f = this.mRawMaxShadowSize * 1.5F;
    this.mCardBounds.set(paramRect.left + this.mRawMaxShadowSize, paramRect.top + f, paramRect.right - this.mRawMaxShadowSize, paramRect.bottom - f);
    buildShadowCorners();
  }
  
  private void buildShadowCorners()
  {
    Object localObject1 = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
    Object localObject2 = new RectF((RectF)localObject1);
    ((RectF)localObject2).inset(-this.mShadowSize, -this.mShadowSize);
    if (this.mCornerShadowPath == null) {
      this.mCornerShadowPath = new Path();
    }
    for (;;)
    {
      this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
      this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0F);
      this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0F);
      this.mCornerShadowPath.arcTo((RectF)localObject2, 180.0F, 90.0F, false);
      this.mCornerShadowPath.arcTo((RectF)localObject1, 270.0F, -90.0F, false);
      this.mCornerShadowPath.close();
      float f1 = this.mCornerRadius / (this.mCornerRadius + this.mShadowSize);
      localObject1 = this.mCornerShadowPaint;
      float f2 = this.mCornerRadius;
      float f3 = this.mShadowSize;
      int i = this.mShadowStartColor;
      int j = this.mShadowStartColor;
      int k = this.mShadowEndColor;
      localObject2 = Shader.TileMode.CLAMP;
      ((Paint)localObject1).setShader(new RadialGradient(0.0F, 0.0F, f2 + f3, new int[] { i, j, k }, new float[] { 0.0F, f1, 1.0F }, (Shader.TileMode)localObject2));
      localObject1 = this.mEdgeShadowPaint;
      f1 = -this.mCornerRadius;
      f2 = this.mShadowSize;
      f3 = -this.mCornerRadius;
      float f4 = this.mShadowSize;
      i = this.mShadowStartColor;
      j = this.mShadowStartColor;
      k = this.mShadowEndColor;
      localObject2 = Shader.TileMode.CLAMP;
      ((Paint)localObject1).setShader(new LinearGradient(0.0F, f1 + f2, 0.0F, f3 - f4, new int[] { i, j, k }, new float[] { 0.0F, 0.5F, 1.0F }, (Shader.TileMode)localObject2));
      this.mEdgeShadowPaint.setAntiAlias(false);
      return;
      this.mCornerShadowPath.reset();
    }
  }
  
  static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f = paramFloat1;
    if (paramBoolean) {
      f = (float)(paramFloat1 + (1.0D - COS_45) * paramFloat2);
    }
    return f;
  }
  
  static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return (float)(1.5F * paramFloat1 + (1.0D - COS_45) * paramFloat2);
    }
    return 1.5F * paramFloat1;
  }
  
  private void drawShadow(Canvas paramCanvas)
  {
    float f1 = -this.mCornerRadius - this.mShadowSize;
    float f2 = this.mCornerRadius + this.mInsetShadow + this.mRawShadowSize / 2.0F;
    int i;
    if (this.mCardBounds.width() - 2.0F * f2 > 0.0F)
    {
      i = 1;
      if (this.mCardBounds.height() - 2.0F * f2 <= 0.0F) {
        break label413;
      }
    }
    label413:
    for (int j = 1;; j = 0)
    {
      int k = paramCanvas.save();
      paramCanvas.translate(this.mCardBounds.left + f2, this.mCardBounds.top + f2);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (i != 0) {
        paramCanvas.drawRect(0.0F, f1, this.mCardBounds.width() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(k);
      k = paramCanvas.save();
      paramCanvas.translate(this.mCardBounds.right - f2, this.mCardBounds.bottom - f2);
      paramCanvas.rotate(180.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (i != 0)
      {
        float f3 = this.mCardBounds.width();
        float f4 = -this.mCornerRadius;
        paramCanvas.drawRect(0.0F, f1, f3 - 2.0F * f2, this.mShadowSize + f4, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(k);
      i = paramCanvas.save();
      paramCanvas.translate(this.mCardBounds.left + f2, this.mCardBounds.bottom - f2);
      paramCanvas.rotate(270.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (j != 0) {
        paramCanvas.drawRect(0.0F, f1, this.mCardBounds.height() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(i);
      i = paramCanvas.save();
      paramCanvas.translate(this.mCardBounds.right - f2, this.mCardBounds.top + f2);
      paramCanvas.rotate(90.0F);
      paramCanvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
      if (j != 0) {
        paramCanvas.drawRect(0.0F, f1, this.mCardBounds.height() - 2.0F * f2, -this.mCornerRadius, this.mEdgeShadowPaint);
      }
      paramCanvas.restoreToCount(i);
      return;
      i = 0;
      break;
    }
  }
  
  private void setBackground(ColorStateList paramColorStateList)
  {
    ColorStateList localColorStateList = paramColorStateList;
    if (paramColorStateList == null) {
      localColorStateList = ColorStateList.valueOf(0);
    }
    this.mBackground = localColorStateList;
    this.mPaint.setColor(this.mBackground.getColorForState(getState(), this.mBackground.getDefaultColor()));
  }
  
  private int toEven(float paramFloat)
  {
    int j = (int)(0.5F + paramFloat);
    int i = j;
    if (j % 2 == 1) {
      i = j - 1;
    }
    return i;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (this.mDirty)
    {
      buildComponents(getBounds());
      this.mDirty = false;
    }
    paramCanvas.translate(0.0F, this.mRawShadowSize / 2.0F);
    drawShadow(paramCanvas);
    paramCanvas.translate(0.0F, -this.mRawShadowSize / 2.0F);
    sRoundRectHelper.drawRoundRect(paramCanvas, this.mCardBounds, this.mCornerRadius, this.mPaint);
  }
  
  ColorStateList getColor()
  {
    return this.mBackground;
  }
  
  float getCornerRadius()
  {
    return this.mCornerRadius;
  }
  
  void getMaxShadowAndCornerPadding(Rect paramRect)
  {
    getPadding(paramRect);
  }
  
  float getMaxShadowSize()
  {
    return this.mRawMaxShadowSize;
  }
  
  float getMinHeight()
  {
    return Math.max(this.mRawMaxShadowSize, this.mCornerRadius + this.mInsetShadow + this.mRawMaxShadowSize * 1.5F / 2.0F) * 2.0F + (this.mRawMaxShadowSize * 1.5F + this.mInsetShadow) * 2.0F;
  }
  
  float getMinWidth()
  {
    return Math.max(this.mRawMaxShadowSize, this.mCornerRadius + this.mInsetShadow + this.mRawMaxShadowSize / 2.0F) * 2.0F + (this.mRawMaxShadowSize + this.mInsetShadow) * 2.0F;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    int i = (int)Math.ceil(calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
    int j = (int)Math.ceil(calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
    paramRect.set(j, i, j, i);
    return true;
  }
  
  float getShadowSize()
  {
    return this.mRawShadowSize;
  }
  
  public boolean isStateful()
  {
    return ((this.mBackground != null) && (this.mBackground.isStateful())) || (super.isStateful());
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    this.mDirty = true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    int i = this.mBackground.getColorForState(paramArrayOfInt, this.mBackground.getDefaultColor());
    if (this.mPaint.getColor() == i) {
      return false;
    }
    this.mPaint.setColor(i);
    this.mDirty = true;
    invalidateSelf();
    return true;
  }
  
  public void setAddPaddingForCorners(boolean paramBoolean)
  {
    this.mAddPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
    this.mCornerShadowPaint.setAlpha(paramInt);
    this.mEdgeShadowPaint.setAlpha(paramInt);
  }
  
  void setColor(@Nullable ColorStateList paramColorStateList)
  {
    setBackground(paramColorStateList);
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mPaint.setColorFilter(paramColorFilter);
  }
  
  void setCornerRadius(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("Invalid radius " + paramFloat + ". Must be >= 0");
    }
    paramFloat = (int)(0.5F + paramFloat);
    if (this.mCornerRadius == paramFloat) {
      return;
    }
    this.mCornerRadius = paramFloat;
    this.mDirty = true;
    invalidateSelf();
  }
  
  void setMaxShadowSize(float paramFloat)
  {
    setShadowSize(this.mRawShadowSize, paramFloat);
  }
  
  void setShadowSize(float paramFloat)
  {
    setShadowSize(paramFloat, this.mRawMaxShadowSize);
  }
  
  void setShadowSize(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 < 0.0F) {
      throw new IllegalArgumentException("Invalid shadow size " + paramFloat1 + ". Must be >= 0");
    }
    if (paramFloat2 < 0.0F) {
      throw new IllegalArgumentException("Invalid max shadow size " + paramFloat2 + ". Must be >= 0");
    }
    float f = toEven(paramFloat1);
    paramFloat2 = toEven(paramFloat2);
    paramFloat1 = f;
    if (f > paramFloat2)
    {
      if (!this.mPrintedShadowClipWarning) {
        this.mPrintedShadowClipWarning = true;
      }
      paramFloat1 = paramFloat2;
    }
    if ((this.mRawShadowSize == paramFloat1) && (this.mRawMaxShadowSize == paramFloat2)) {
      return;
    }
    this.mRawShadowSize = paramFloat1;
    this.mRawMaxShadowSize = paramFloat2;
    this.mShadowSize = ((int)(paramFloat1 * 1.5F + this.mInsetShadow + 0.5F));
    this.mMaxShadowSize = (this.mInsetShadow + paramFloat2);
    this.mDirty = true;
    invalidateSelf();
  }
  
  static abstract interface RoundRectHelper
  {
    public abstract void drawRoundRect(Canvas paramCanvas, RectF paramRectF, float paramFloat, Paint paramPaint);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\RoundRectDrawableWithShadow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */