package q.rorbin.badgeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.FrameLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class QBadgeView
  extends View
  implements Badge
{
  protected ViewGroup mActivityRoot;
  protected BadgeAnimator mAnimator;
  protected float mBackgroundBorderWidth;
  protected Paint mBadgeBackgroundBorderPaint;
  protected Paint mBadgeBackgroundPaint;
  protected RectF mBadgeBackgroundRect;
  protected PointF mBadgeCenter;
  protected int mBadgeGravity;
  protected int mBadgeNumber;
  protected float mBadgePadding;
  protected String mBadgeText;
  protected Paint.FontMetrics mBadgeTextFontMetrics;
  protected TextPaint mBadgeTextPaint;
  protected RectF mBadgeTextRect;
  protected float mBadgeTextSize;
  protected Bitmap mBitmapClip;
  protected int mColorBackground;
  protected int mColorBackgroundBorder;
  protected int mColorBadgeText;
  protected PointF mControlPoint;
  protected float mDefalutRadius;
  protected PointF mDragCenter;
  protected boolean mDragOutOfRange;
  protected Path mDragPath;
  protected int mDragQuadrant;
  protected Badge.OnDragStateChangedListener mDragStateChangedListener;
  protected boolean mDraggable;
  protected boolean mDragging;
  protected Drawable mDrawableBackground;
  protected boolean mDrawableBackgroundClip;
  protected boolean mExact;
  protected float mFinalDragDistance;
  protected float mGravityOffsetX;
  protected float mGravityOffsetY;
  protected int mHeight;
  protected List<PointF> mInnertangentPoints;
  protected PointF mRowBadgeCenter;
  protected boolean mShowShadow;
  protected View mTargetView;
  protected int mWidth;
  
  public QBadgeView(Context paramContext)
  {
    this(paramContext, (AttributeSet)null);
  }
  
  QBadgeView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  QBadgeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void createClipLayer()
  {
    if (this.mBadgeText == null) {
      return;
    }
    if (!this.mDrawableBackgroundClip) {
      return;
    }
    if ((this.mBitmapClip != null) && (!this.mBitmapClip.isRecycled())) {
      this.mBitmapClip.recycle();
    }
    float f = getBadgeCircleRadius();
    if ((this.mBadgeText.isEmpty()) || (this.mBadgeText.length() == 1))
    {
      this.mBitmapClip = Bitmap.createBitmap((int)f * 2, (int)f * 2, Bitmap.Config.ARGB_4444);
      localCanvas = new Canvas(this.mBitmapClip);
      localCanvas.drawCircle(localCanvas.getWidth() / 2.0F, localCanvas.getHeight() / 2.0F, localCanvas.getWidth() / 2.0F, this.mBadgeBackgroundPaint);
      return;
    }
    this.mBitmapClip = Bitmap.createBitmap((int)(this.mBadgeTextRect.width() + this.mBadgePadding * 2), (int)(this.mBadgeTextRect.height() + this.mBadgePadding), Bitmap.Config.ARGB_4444);
    Canvas localCanvas = new Canvas(this.mBitmapClip);
    if (Build.VERSION.SDK_INT >= 21)
    {
      localCanvas.drawRoundRect(0, 0, localCanvas.getWidth(), localCanvas.getHeight(), localCanvas.getHeight() / 2.0F, localCanvas.getHeight() / 2.0F, this.mBadgeBackgroundPaint);
      return;
    }
    localCanvas.drawRoundRect(new RectF(0, 0, localCanvas.getWidth(), localCanvas.getHeight()), localCanvas.getHeight() / 2.0F, localCanvas.getHeight() / 2.0F, this.mBadgeBackgroundPaint);
  }
  
  private void drawBadge(Canvas paramCanvas, PointF paramPointF, float paramFloat)
  {
    if ((paramPointF.x == 'ﰘ') && (paramPointF.y == 'ﰘ')) {
      return;
    }
    if ((this.mBadgeText.isEmpty()) || (this.mBadgeText.length() == 1))
    {
      this.mBadgeBackgroundRect.left = (paramPointF.x - (int)paramFloat);
      this.mBadgeBackgroundRect.top = (paramPointF.y - (int)paramFloat);
      this.mBadgeBackgroundRect.right = (paramPointF.x + (int)paramFloat);
      this.mBadgeBackgroundRect.bottom = (paramPointF.y + (int)paramFloat);
      if (this.mDrawableBackground != null) {
        drawBadgeBackground(paramCanvas);
      }
    }
    for (;;)
    {
      if (!this.mBadgeText.isEmpty()) {
        paramCanvas.drawText(this.mBadgeText, paramPointF.x, (this.mBadgeBackgroundRect.bottom + this.mBadgeBackgroundRect.top - this.mBadgeTextFontMetrics.bottom - this.mBadgeTextFontMetrics.top) / 2.0F, this.mBadgeTextPaint);
      }
      return;
      paramCanvas.drawCircle(paramPointF.x, paramPointF.y, paramFloat, this.mBadgeBackgroundPaint);
      if ((this.mColorBackgroundBorder != 0) && (this.mBackgroundBorderWidth > 0))
      {
        paramCanvas.drawCircle(paramPointF.x, paramPointF.y, paramFloat, this.mBadgeBackgroundBorderPaint);
        continue;
        this.mBadgeBackgroundRect.left = (paramPointF.x - (this.mBadgeTextRect.width() / 2.0F + this.mBadgePadding));
        this.mBadgeBackgroundRect.top = (paramPointF.y - (this.mBadgeTextRect.height() / 2.0F + this.mBadgePadding * 0.5F));
        this.mBadgeBackgroundRect.right = (paramPointF.x + (this.mBadgeTextRect.width() / 2.0F + this.mBadgePadding));
        this.mBadgeBackgroundRect.bottom = (paramPointF.y + (this.mBadgeTextRect.height() / 2.0F + this.mBadgePadding * 0.5F));
        paramFloat = this.mBadgeBackgroundRect.height() / 2.0F;
        if (this.mDrawableBackground != null)
        {
          drawBadgeBackground(paramCanvas);
        }
        else
        {
          paramCanvas.drawRoundRect(this.mBadgeBackgroundRect, paramFloat, paramFloat, this.mBadgeBackgroundPaint);
          if ((this.mColorBackgroundBorder != 0) && (this.mBackgroundBorderWidth > 0)) {
            paramCanvas.drawRoundRect(this.mBadgeBackgroundRect, paramFloat, paramFloat, this.mBadgeBackgroundBorderPaint);
          }
        }
      }
    }
  }
  
  private void drawBadgeBackground(Canvas paramCanvas)
  {
    this.mBadgeBackgroundPaint.setShadowLayer(0, 0, 0, 0);
    int k = (int)this.mBadgeBackgroundRect.left;
    int m = (int)this.mBadgeBackgroundRect.top;
    int i = (int)this.mBadgeBackgroundRect.right;
    int j = (int)this.mBadgeBackgroundRect.bottom;
    if (this.mDrawableBackgroundClip)
    {
      i = k + this.mBitmapClip.getWidth();
      j = m + this.mBitmapClip.getHeight();
      paramCanvas.saveLayer(k, m, i, j, (Paint)null, 31);
    }
    this.mDrawableBackground.setBounds(k, m, i, j);
    this.mDrawableBackground.draw(paramCanvas);
    if (this.mDrawableBackgroundClip)
    {
      this.mBadgeBackgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
      paramCanvas.drawBitmap(this.mBitmapClip, k, m, this.mBadgeBackgroundPaint);
      paramCanvas.restore();
      this.mBadgeBackgroundPaint.setXfermode((Xfermode)null);
      if ((this.mBadgeText.isEmpty()) || (this.mBadgeText.length() == 1))
      {
        paramCanvas.drawCircle(this.mBadgeBackgroundRect.centerX(), this.mBadgeBackgroundRect.centerY(), this.mBadgeBackgroundRect.width() / 2.0F, this.mBadgeBackgroundBorderPaint);
        return;
      }
      paramCanvas.drawRoundRect(this.mBadgeBackgroundRect, this.mBadgeBackgroundRect.height() / 2, this.mBadgeBackgroundRect.height() / 2, this.mBadgeBackgroundBorderPaint);
      return;
    }
    paramCanvas.drawRect(this.mBadgeBackgroundRect, this.mBadgeBackgroundBorderPaint);
  }
  
  private void drawDragging(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    float f1 = this.mDragCenter.y;
    float f2 = this.mRowBadgeCenter.y;
    float f3 = this.mDragCenter.x - this.mRowBadgeCenter.x;
    this.mInnertangentPoints.clear();
    Path.Direction localDirection;
    label163:
    label739:
    int i;
    if (f3 != 0)
    {
      double d = (f1 - f2) / f3;
      d = -1 / d;
      MathUtil.getInnertangentPoints(this.mDragCenter, paramFloat2, new Double(d), this.mInnertangentPoints);
      MathUtil.getInnertangentPoints(this.mRowBadgeCenter, paramFloat1, new Double(d), this.mInnertangentPoints);
      this.mDragPath.reset();
      Path localPath = this.mDragPath;
      paramFloat2 = this.mRowBadgeCenter.x;
      f1 = this.mRowBadgeCenter.y;
      if ((this.mDragQuadrant != 1) && (this.mDragQuadrant != 2)) {
        break label894;
      }
      localDirection = Path.Direction.CCW;
      localPath.addCircle(paramFloat2, f1, paramFloat1, localDirection);
      this.mControlPoint.x = ((this.mRowBadgeCenter.x + this.mDragCenter.x) / 2.0F);
      this.mControlPoint.y = ((this.mRowBadgeCenter.y + this.mDragCenter.y) / 2.0F);
      this.mDragPath.moveTo(((PointF)this.mInnertangentPoints.get(2)).x, ((PointF)this.mInnertangentPoints.get(2)).y);
      this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, ((PointF)this.mInnertangentPoints.get(0)).x, ((PointF)this.mInnertangentPoints.get(0)).y);
      this.mDragPath.lineTo(((PointF)this.mInnertangentPoints.get(1)).x, ((PointF)this.mInnertangentPoints.get(1)).y);
      this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, ((PointF)this.mInnertangentPoints.get(3)).x, ((PointF)this.mInnertangentPoints.get(3)).y);
      this.mDragPath.lineTo(((PointF)this.mInnertangentPoints.get(2)).x, ((PointF)this.mInnertangentPoints.get(2)).y);
      this.mDragPath.close();
      paramCanvas.drawPath(this.mDragPath, this.mBadgeBackgroundPaint);
      if ((this.mColorBackgroundBorder != 0) && (this.mBackgroundBorderWidth > 0))
      {
        this.mDragPath.reset();
        this.mDragPath.moveTo(((PointF)this.mInnertangentPoints.get(2)).x, ((PointF)this.mInnertangentPoints.get(2)).y);
        this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, ((PointF)this.mInnertangentPoints.get(0)).x, ((PointF)this.mInnertangentPoints.get(0)).y);
        this.mDragPath.moveTo(((PointF)this.mInnertangentPoints.get(1)).x, ((PointF)this.mInnertangentPoints.get(1)).y);
        this.mDragPath.quadTo(this.mControlPoint.x, this.mControlPoint.y, ((PointF)this.mInnertangentPoints.get(3)).x, ((PointF)this.mInnertangentPoints.get(3)).y);
        if ((this.mDragQuadrant != 1) && (this.mDragQuadrant != 2)) {
          break label902;
        }
        f1 = ((PointF)this.mInnertangentPoints.get(2)).x - this.mRowBadgeCenter.x;
        paramFloat2 = this.mRowBadgeCenter.y - ((PointF)this.mInnertangentPoints.get(2)).y;
        f2 = 'Ũ';
        d = Math.atan(paramFloat2 / f1);
        if (this.mDragQuadrant - 1 != 0) {
          break label956;
        }
        i = 4;
        label767:
        paramFloat2 = f2 - (float)MathUtil.radianToAngle(MathUtil.getTanRadian(d, i));
        if (Build.VERSION.SDK_INT < 21) {
          break label967;
        }
        this.mDragPath.addArc(this.mRowBadgeCenter.x - paramFloat1, this.mRowBadgeCenter.y - paramFloat1, this.mRowBadgeCenter.x + paramFloat1, this.mRowBadgeCenter.y + paramFloat1, paramFloat2, '´');
      }
    }
    for (;;)
    {
      paramCanvas.drawPath(this.mDragPath, this.mBadgeBackgroundBorderPaint);
      return;
      MathUtil.getInnertangentPoints(this.mDragCenter, paramFloat2, new Double(0.0D), this.mInnertangentPoints);
      MathUtil.getInnertangentPoints(this.mRowBadgeCenter, paramFloat1, new Double(0.0D), this.mInnertangentPoints);
      break;
      label894:
      localDirection = Path.Direction.CW;
      break label163;
      label902:
      f1 = ((PointF)this.mInnertangentPoints.get(3)).x - this.mRowBadgeCenter.x;
      paramFloat2 = this.mRowBadgeCenter.y - ((PointF)this.mInnertangentPoints.get(3)).y;
      break label739;
      label956:
      i = this.mDragQuadrant - 1;
      break label767;
      label967:
      this.mDragPath.addArc(new RectF(this.mRowBadgeCenter.x - paramFloat1, this.mRowBadgeCenter.y - paramFloat1, this.mRowBadgeCenter.x + paramFloat1, this.mRowBadgeCenter.y + paramFloat1), paramFloat2, '´');
    }
  }
  
  private void findActivityRoot(View paramView)
  {
    if ((paramView.getParent() != null) && ((paramView.getParent() instanceof View))) {
      findActivityRoot((View)paramView.getParent());
    }
    while (!(paramView instanceof ViewGroup)) {
      return;
    }
    this.mActivityRoot = ((ViewGroup)paramView);
  }
  
  private void findBadgeCenter()
  {
    float f;
    if (this.mBadgeTextRect.height() > this.mBadgeTextRect.width())
    {
      f = this.mBadgeTextRect.height();
      switch (this.mBadgeGravity)
      {
      }
    }
    for (;;)
    {
      initRowBadgeCenter();
      return;
      f = this.mBadgeTextRect.width();
      break;
      this.mBadgeCenter.x = (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F);
      this.mBadgeCenter.y = (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F);
      continue;
      this.mBadgeCenter.x = (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F);
      this.mBadgeCenter.y = (this.mHeight - (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F));
      continue;
      this.mBadgeCenter.x = (this.mWidth - (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F));
      this.mBadgeCenter.y = (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F);
      continue;
      this.mBadgeCenter.x = (this.mWidth - (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F));
      this.mBadgeCenter.y = (this.mHeight - (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F));
      continue;
      this.mBadgeCenter.x = (this.mWidth / 2.0F);
      this.mBadgeCenter.y = (this.mHeight / 2.0F);
      continue;
      this.mBadgeCenter.x = (this.mWidth / 2.0F);
      this.mBadgeCenter.y = (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F);
      continue;
      this.mBadgeCenter.x = (this.mWidth / 2.0F);
      this.mBadgeCenter.y = (this.mHeight - (this.mGravityOffsetY + this.mBadgePadding + this.mBadgeTextRect.height() / 2.0F));
      continue;
      this.mBadgeCenter.x = (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F);
      this.mBadgeCenter.y = (this.mHeight / 2.0F);
      continue;
      this.mBadgeCenter.x = (this.mWidth - (this.mGravityOffsetX + this.mBadgePadding + f / 2.0F));
      this.mBadgeCenter.y = (this.mHeight / 2.0F);
    }
  }
  
  private float getBadgeCircleRadius()
  {
    if (this.mBadgeText.isEmpty()) {
      return this.mBadgePadding;
    }
    if (this.mBadgeText.length() == 1)
    {
      if (this.mBadgeTextRect.height() > this.mBadgeTextRect.width()) {}
      for (float f = this.mBadgeTextRect.height() / 2.0F + this.mBadgePadding * 0.5F;; f = this.mBadgeTextRect.width() / 2.0F + this.mBadgePadding * 0.5F) {
        return f;
      }
    }
    return this.mBadgeBackgroundRect.height() / 2.0F;
  }
  
  private void init()
  {
    setLayerType(1, (Paint)null);
    this.mBadgeTextRect = new RectF();
    this.mBadgeBackgroundRect = new RectF();
    this.mDragPath = new Path();
    this.mBadgeCenter = new PointF();
    this.mDragCenter = new PointF();
    this.mRowBadgeCenter = new PointF();
    this.mControlPoint = new PointF();
    this.mInnertangentPoints = new ArrayList();
    this.mBadgeTextPaint = new TextPaint();
    this.mBadgeTextPaint.setAntiAlias(true);
    this.mBadgeTextPaint.setSubpixelText(true);
    this.mBadgeTextPaint.setFakeBoldText(true);
    this.mBadgeTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    this.mBadgeBackgroundPaint = new Paint();
    this.mBadgeBackgroundPaint.setAntiAlias(true);
    this.mBadgeBackgroundPaint.setStyle(Paint.Style.FILL);
    this.mBadgeBackgroundBorderPaint = new Paint();
    this.mBadgeBackgroundBorderPaint.setAntiAlias(true);
    this.mBadgeBackgroundBorderPaint.setStyle(Paint.Style.STROKE);
    this.mColorBackground = -1552832;
    this.mColorBadgeText = -1;
    this.mBadgeTextSize = DisplayUtil.dp2px(getContext(), 11);
    this.mBadgePadding = DisplayUtil.dp2px(getContext(), 5);
    this.mBadgeNumber = 0;
    this.mBadgeGravity = 8388661;
    this.mGravityOffsetX = DisplayUtil.dp2px(getContext(), 5);
    this.mGravityOffsetY = DisplayUtil.dp2px(getContext(), 5);
    this.mFinalDragDistance = DisplayUtil.dp2px(getContext(), 90);
    this.mShowShadow = true;
    this.mDrawableBackgroundClip = false;
    if (Build.VERSION.SDK_INT >= 21) {
      setTranslationZ('Ϩ');
    }
  }
  
  private void initPaints()
  {
    showShadowImpl(this.mShowShadow);
    this.mBadgeBackgroundPaint.setColor(this.mColorBackground);
    this.mBadgeBackgroundBorderPaint.setColor(this.mColorBackgroundBorder);
    this.mBadgeBackgroundBorderPaint.setStrokeWidth(this.mBackgroundBorderWidth);
    this.mBadgeTextPaint.setColor(this.mColorBadgeText);
    this.mBadgeTextPaint.setTextAlign(Paint.Align.CENTER);
  }
  
  private void initRowBadgeCenter()
  {
    int[] arrayOfInt = new int[2];
    getLocationOnScreen(arrayOfInt);
    this.mRowBadgeCenter.x = (this.mBadgeCenter.x + arrayOfInt[0]);
    this.mRowBadgeCenter.y = (this.mBadgeCenter.y + arrayOfInt[1]);
  }
  
  private void measureText()
  {
    this.mBadgeTextRect.left = 0;
    this.mBadgeTextRect.top = 0;
    if (TextUtils.isEmpty(this.mBadgeText)) {
      this.mBadgeTextRect.right = 0;
    }
    for (this.mBadgeTextRect.bottom = 0;; this.mBadgeTextRect.bottom = (this.mBadgeTextFontMetrics.descent - this.mBadgeTextFontMetrics.ascent))
    {
      createClipLayer();
      return;
      this.mBadgeTextPaint.setTextSize(this.mBadgeTextSize);
      this.mBadgeTextRect.right = this.mBadgeTextPaint.measureText(this.mBadgeText);
      this.mBadgeTextFontMetrics = this.mBadgeTextPaint.getFontMetrics();
    }
  }
  
  private void onPointerUp()
  {
    if (this.mDragOutOfRange)
    {
      animateHide(this.mDragCenter);
      updataListener(5);
      return;
    }
    reset();
    updataListener(4);
  }
  
  private void showShadowImpl(boolean paramBoolean)
  {
    int i = DisplayUtil.dp2px(getContext(), 1);
    int j = DisplayUtil.dp2px(getContext(), 1.5F);
    Paint localPaint;
    switch (this.mDragQuadrant)
    {
    default: 
      localPaint = this.mBadgeBackgroundPaint;
      if (!paramBoolean) {
        break;
      }
    }
    for (int k = DisplayUtil.dp2px(getContext(), 2.0F);; k = 0)
    {
      localPaint.setShadowLayer(k, i, j, 855638016);
      return;
      i = DisplayUtil.dp2px(getContext(), 1);
      j = DisplayUtil.dp2px(getContext(), -1.5F);
      break;
      i = DisplayUtil.dp2px(getContext(), -1);
      j = DisplayUtil.dp2px(getContext(), -1.5F);
      break;
      i = DisplayUtil.dp2px(getContext(), -1);
      j = DisplayUtil.dp2px(getContext(), 1.5F);
      break;
      i = DisplayUtil.dp2px(getContext(), 1);
      j = DisplayUtil.dp2px(getContext(), 1.5F);
      break;
    }
  }
  
  private void updataListener(int paramInt)
  {
    if (this.mDragStateChangedListener != null) {
      this.mDragStateChangedListener.onDragStateChanged(paramInt, this, this.mTargetView);
    }
  }
  
  protected void animateHide(PointF paramPointF)
  {
    if (this.mBadgeText == null) {
      return;
    }
    if ((this.mAnimator == null) || (!this.mAnimator.isRunning()))
    {
      screenFromWindow(true);
      this.mAnimator = new BadgeAnimator(createBadgeBitmap(), paramPointF, this);
      this.mAnimator.start();
      setBadgeNumber(0);
    }
  }
  
  @Override
  public Badge bindTarget(View paramView)
  {
    if (paramView == null) {
      throw new IllegalStateException("targetView can not be null");
    }
    if (getParent() != null) {
      ((ViewGroup)getParent()).removeView(this);
    }
    Object localObject = paramView.getParent();
    if ((localObject != null) && ((localObject instanceof ViewGroup)))
    {
      this.mTargetView = paramView;
      if ((localObject instanceof BadgeContainer)) {
        ((BadgeContainer)localObject).addView(this);
      }
      for (;;)
      {
        return this;
        localObject = (ViewGroup)localObject;
        int i = ((ViewGroup)localObject).indexOfChild(paramView);
        ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
        ((ViewGroup)localObject).removeView(paramView);
        BadgeContainer localBadgeContainer = new BadgeContainer(getContext());
        localBadgeContainer.setId(paramView.getId());
        ((ViewGroup)localObject).addView(localBadgeContainer, i, localLayoutParams);
        localBadgeContainer.addView(paramView);
        localBadgeContainer.addView(this);
      }
    }
    throw new IllegalStateException("targetView must have a parent");
  }
  
  protected Bitmap createBadgeBitmap()
  {
    Bitmap localBitmap = Bitmap.createBitmap((int)this.mBadgeBackgroundRect.width() + DisplayUtil.dp2px(getContext(), 3), (int)this.mBadgeBackgroundRect.height() + DisplayUtil.dp2px(getContext(), 3), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    drawBadge(localCanvas, new PointF(localCanvas.getWidth() / 2.0F, localCanvas.getHeight() / 2.0F), getBadgeCircleRadius());
    return localBitmap;
  }
  
  @Override
  public Drawable getBadgeBackground()
  {
    return this.mDrawableBackground;
  }
  
  @Override
  public int getBadgeBackgroundColor()
  {
    return this.mColorBackground;
  }
  
  @Override
  public int getBadgeGravity()
  {
    return this.mBadgeGravity;
  }
  
  @Override
  public int getBadgeNumber()
  {
    return this.mBadgeNumber;
  }
  
  @Override
  public float getBadgePadding(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = DisplayUtil.px2dp(getContext(), this.mBadgePadding);; f = this.mBadgePadding) {
      return f;
    }
  }
  
  @Override
  public String getBadgeText()
  {
    return this.mBadgeText;
  }
  
  @Override
  public int getBadgeTextColor()
  {
    return this.mColorBadgeText;
  }
  
  @Override
  public float getBadgeTextSize(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = DisplayUtil.px2dp(getContext(), this.mBadgeTextSize);; f = this.mBadgeTextSize) {
      return f;
    }
  }
  
  @Override
  public PointF getDragCenter()
  {
    if ((this.mDraggable) && (this.mDragging)) {
      return this.mDragCenter;
    }
    return (PointF)null;
  }
  
  @Override
  public float getGravityOffsetX(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = DisplayUtil.px2dp(getContext(), this.mGravityOffsetX);; f = this.mGravityOffsetX) {
      return f;
    }
  }
  
  @Override
  public float getGravityOffsetY(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (float f = DisplayUtil.px2dp(getContext(), this.mGravityOffsetY);; f = this.mGravityOffsetY) {
      return f;
    }
  }
  
  @Override
  public View getTargetView()
  {
    return this.mTargetView;
  }
  
  @Override
  public void hide(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mActivityRoot != null))
    {
      animateHide(this.mRowBadgeCenter);
      return;
    }
    setBadgeNumber(0);
  }
  
  @Override
  public boolean isDraggable()
  {
    return this.mDraggable;
  }
  
  @Override
  public boolean isExactMode()
  {
    return this.mExact;
  }
  
  @Override
  public boolean isShowShadow()
  {
    return this.mShowShadow;
  }
  
  @Override
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.mActivityRoot == null) {
      findActivityRoot(this.mTargetView);
    }
  }
  
  @Override
  protected void onDraw(Canvas paramCanvas)
  {
    if ((this.mAnimator != null) && (this.mAnimator.isRunning()))
    {
      this.mAnimator.draw(paramCanvas);
      return;
    }
    float f1;
    float f2;
    if (this.mBadgeText != null)
    {
      initPaints();
      f1 = getBadgeCircleRadius();
      f2 = this.mDefalutRadius * (1 - MathUtil.getPointDistance(this.mRowBadgeCenter, this.mDragCenter) / this.mFinalDragDistance);
      if ((!this.mDraggable) || (!this.mDragging)) {
        break label179;
      }
      this.mDragQuadrant = MathUtil.getQuadrant(this.mDragCenter, this.mRowBadgeCenter);
      showShadowImpl(this.mShowShadow);
      if (f2 < DisplayUtil.dp2px(getContext(), 1.5F)) {
        break label150;
      }
    }
    label150:
    for (boolean bool = false;; bool = true)
    {
      this.mDragOutOfRange = bool;
      if (!bool) {
        break;
      }
      updataListener(3);
      drawBadge(paramCanvas, this.mDragCenter, f1);
      return;
    }
    updataListener(2);
    drawDragging(paramCanvas, f2, f1);
    drawBadge(paramCanvas, this.mDragCenter, f1);
    return;
    label179:
    findBadgeCenter();
    drawBadge(paramCanvas, this.mBadgeCenter, f1);
  }
  
  @Override
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getActionMasked())
    {
    case 4: 
    default: 
      if ((this.mDragging) || (super.onTouchEvent(paramMotionEvent))) {
        break;
      }
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      if ((this.mDraggable) && (paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex()) == 0) && (f1 > this.mBadgeBackgroundRect.left) && (f1 < this.mBadgeBackgroundRect.right) && (f2 > this.mBadgeBackgroundRect.top) && (f2 < this.mBadgeBackgroundRect.bottom) && (this.mBadgeText != null))
      {
        initRowBadgeCenter();
        this.mDragging = true;
        updataListener(1);
        this.mDefalutRadius = DisplayUtil.dp2px(getContext(), 7);
        getParent().requestDisallowInterceptTouchEvent(true);
        screenFromWindow(true);
        this.mDragCenter.x = paramMotionEvent.getRawX();
        this.mDragCenter.y = paramMotionEvent.getRawY();
      }
      break;
      if (this.mDragging)
      {
        this.mDragCenter.x = paramMotionEvent.getRawX();
        this.mDragCenter.y = paramMotionEvent.getRawY();
        invalidate();
      }
      break;
      if ((paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex()) == 0) && (this.mDragging))
      {
        this.mDragging = false;
        onPointerUp();
      }
      break;
    }
  }
  
  public void reset()
  {
    this.mDragCenter.x = 'ﰘ';
    this.mDragCenter.y = 'ﰘ';
    this.mDragQuadrant = 4;
    screenFromWindow(false);
    getParent().requestDisallowInterceptTouchEvent(false);
    invalidate();
  }
  
  protected void screenFromWindow(boolean paramBoolean)
  {
    if (getParent() != null) {
      ((ViewGroup)getParent()).removeView(this);
    }
    if (paramBoolean)
    {
      this.mActivityRoot.addView(this, new FrameLayout.LayoutParams(-1, -1));
      return;
    }
    bindTarget(this.mTargetView);
  }
  
  @Override
  public Badge setBadgeBackground(Drawable paramDrawable)
  {
    return setBadgeBackground(paramDrawable, false);
  }
  
  @Override
  public Badge setBadgeBackground(Drawable paramDrawable, boolean paramBoolean)
  {
    this.mDrawableBackgroundClip = paramBoolean;
    this.mDrawableBackground = paramDrawable;
    createClipLayer();
    invalidate();
    return this;
  }
  
  @Override
  public Badge setBadgeBackgroundColor(int paramInt)
  {
    this.mColorBackground = paramInt;
    if (this.mColorBackground == 0) {
      this.mBadgeTextPaint.setXfermode((Xfermode)null);
    }
    for (;;)
    {
      invalidate();
      return this;
      this.mBadgeTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }
  }
  
  @Override
  public Badge setBadgeGravity(int paramInt)
  {
    if ((paramInt == 8388659) || (paramInt == 8388661) || (paramInt == 8388691) || (paramInt == 8388693) || (paramInt == 17) || (paramInt == 49) || (paramInt == 81) || (paramInt == 8388627) || (paramInt == 8388629))
    {
      this.mBadgeGravity = paramInt;
      invalidate();
      return this;
    }
    throw new IllegalStateException(new StringBuffer().append(new StringBuffer().append("only support Gravity.START | Gravity.TOP , Gravity.END | Gravity.TOP , ").append("Gravity.START | Gravity.BOTTOM , Gravity.END | Gravity.BOTTOM , Gravity.CENTER").toString()).append(" , Gravity.CENTER | Gravity.TOP , Gravity.CENTER | Gravity.BOTTOM ,").toString() + "Gravity.CENTER | Gravity.START , Gravity.CENTER | Gravity.END");
  }
  
  @Override
  public Badge setBadgeNumber(int paramInt)
  {
    this.mBadgeNumber = paramInt;
    if (this.mBadgeNumber < 0) {
      this.mBadgeText = "";
    }
    for (;;)
    {
      measureText();
      invalidate();
      return this;
      if (this.mBadgeNumber > 99)
      {
        if (this.mExact) {}
        for (String str = String.valueOf(this.mBadgeNumber);; str = "99+")
        {
          this.mBadgeText = str;
          break;
        }
      }
      if ((this.mBadgeNumber > 0) && (this.mBadgeNumber <= 99)) {
        this.mBadgeText = String.valueOf(this.mBadgeNumber);
      } else if (this.mBadgeNumber == 0) {
        this.mBadgeText = ((String)null);
      }
    }
  }
  
  @Override
  public Badge setBadgePadding(float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramFloat = DisplayUtil.dp2px(getContext(), paramFloat);
    }
    for (;;)
    {
      this.mBadgePadding = paramFloat;
      createClipLayer();
      invalidate();
      return this;
    }
  }
  
  @Override
  public Badge setBadgeText(String paramString)
  {
    this.mBadgeText = paramString;
    this.mBadgeNumber = 1;
    measureText();
    invalidate();
    return this;
  }
  
  @Override
  public Badge setBadgeTextColor(int paramInt)
  {
    this.mColorBadgeText = paramInt;
    invalidate();
    return this;
  }
  
  @Override
  public Badge setBadgeTextSize(float paramFloat, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramFloat = DisplayUtil.dp2px(getContext(), paramFloat);
    }
    for (;;)
    {
      this.mBadgeTextSize = paramFloat;
      measureText();
      invalidate();
      return this;
    }
  }
  
  @Override
  public Badge setExactMode(boolean paramBoolean)
  {
    this.mExact = paramBoolean;
    if (this.mBadgeNumber > 99) {
      setBadgeNumber(this.mBadgeNumber);
    }
    return this;
  }
  
  @Override
  public Badge setGravityOffset(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramFloat1 = DisplayUtil.dp2px(getContext(), paramFloat1);
      this.mGravityOffsetX = paramFloat1;
      if (!paramBoolean) {
        break label47;
      }
    }
    label47:
    for (paramFloat1 = DisplayUtil.dp2px(getContext(), paramFloat2);; paramFloat1 = paramFloat2)
    {
      this.mGravityOffsetY = paramFloat1;
      invalidate();
      return this;
      break;
    }
  }
  
  @Override
  public Badge setGravityOffset(float paramFloat, boolean paramBoolean)
  {
    return setGravityOffset(paramFloat, paramFloat, paramBoolean);
  }
  
  @Override
  public Badge setOnDragStateChangedListener(Badge.OnDragStateChangedListener paramOnDragStateChangedListener)
  {
    if (paramOnDragStateChangedListener == null) {}
    for (boolean bool = false;; bool = true)
    {
      this.mDraggable = bool;
      this.mDragStateChangedListener = paramOnDragStateChangedListener;
      return this;
    }
  }
  
  @Override
  public Badge setShowShadow(boolean paramBoolean)
  {
    this.mShowShadow = paramBoolean;
    invalidate();
    return this;
  }
  
  @Override
  public Badge stroke(int paramInt, float paramFloat, boolean paramBoolean)
  {
    this.mColorBackgroundBorder = paramInt;
    if (paramBoolean) {
      paramFloat = DisplayUtil.dp2px(getContext(), paramFloat);
    }
    for (;;)
    {
      this.mBackgroundBorderWidth = paramFloat;
      invalidate();
      return this;
    }
  }
  
  private class BadgeContainer
    extends ViewGroup
  {
    public BadgeContainer(Context paramContext)
    {
      super();
    }
    
    @Override
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      paramInt1 = 0;
      for (;;)
      {
        if (paramInt1 >= getChildCount()) {
          return;
        }
        View localView = getChildAt(paramInt1);
        localView.layout(0, 0, localView.getMeasuredWidth(), localView.getMeasuredHeight());
        paramInt1 += 1;
      }
    }
    
    @Override
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      Object localObject1 = (View)null;
      Object localObject2 = (View)null;
      int i = 0;
      if (i >= getChildCount())
      {
        if (localObject1 == null) {
          super.onMeasure(paramInt1, paramInt2);
        }
      }
      else
      {
        View localView = getChildAt(i);
        if (!(localView instanceof QBadgeView)) {
          localObject1 = localView;
        }
        for (;;)
        {
          i += 1;
          break;
          localObject2 = localView;
        }
      }
      ((View)localObject1).measure(paramInt1, paramInt2);
      if (localObject2 != null) {
        ((View)localObject2).measure(View.MeasureSpec.makeMeasureSpec(((View)localObject1).getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(((View)localObject1).getMeasuredHeight(), 1073741824));
      }
      setMeasuredDimension(((View)localObject1).getMeasuredWidth(), ((View)localObject1).getMeasuredHeight());
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\q\rorbin\badgeview\QBadgeView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */