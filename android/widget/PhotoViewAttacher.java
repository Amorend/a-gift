package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.gestures.OnGestureListener;
import android.widget.gestures.VersionedGestureDetector;
import android.widget.log.LogManager;
import android.widget.log.Logger;
import android.widget.scrollerproxy.ScrollerProxy;
import java.lang.ref.WeakReference;

public class PhotoViewAttacher
  implements IPhotoView, View.OnTouchListener, OnGestureListener, ViewTreeObserver.OnGlobalLayoutListener
{
  private static final boolean DEBUG = Log.isLoggable("PhotoViewAttacher", 3);
  static final int EDGE_BOTH = 2;
  static final int EDGE_LEFT = 0;
  static final int EDGE_NONE = -1;
  static final int EDGE_RIGHT = 1;
  private static final String LOG_TAG = "PhotoViewAttacher";
  static int SINGLE_TOUCH = 1;
  int ZOOM_DURATION = 200;
  private boolean mAllowParentInterceptOnEdge = true;
  private final Matrix mBaseMatrix = new Matrix();
  private float mBaseRotation;
  private boolean mBlockParentIntercept = false;
  private FlingRunnable mCurrentFlingRunnable;
  private final RectF mDisplayRect = new RectF();
  private final Matrix mDrawMatrix = new Matrix();
  private android.view.GestureDetector mGestureDetector;
  private WeakReference<ImageView> mImageView;
  private Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
  private int mIvBottom;
  private int mIvLeft;
  private int mIvRight;
  private int mIvTop;
  private View.OnLongClickListener mLongClickListener;
  private OnMatrixChangedListener mMatrixChangeListener;
  private final float[] mMatrixValues = new float[9];
  private float mMaxScale = 3.0F;
  private float mMidScale = 1.75F;
  private float mMinScale = 1.0F;
  private OnPhotoTapListener mPhotoTapListener;
  private OnScaleChangeListener mScaleChangeListener;
  private android.widget.gestures.GestureDetector mScaleDragDetector;
  private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
  private int mScrollEdge = 2;
  private OnSingleFlingListener mSingleFlingListener;
  private final Matrix mSuppMatrix = new Matrix();
  private OnViewTapListener mViewTapListener;
  private boolean mZoomEnabled;
  
  public PhotoViewAttacher(ImageView paramImageView)
  {
    this(paramImageView, true);
  }
  
  public PhotoViewAttacher(ImageView paramImageView, boolean paramBoolean)
  {
    this.mImageView = new WeakReference(paramImageView);
    paramImageView.setDrawingCacheEnabled(true);
    paramImageView.setOnTouchListener(this);
    ViewTreeObserver localViewTreeObserver = paramImageView.getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.addOnGlobalLayoutListener(this);
    }
    setImageViewScaleTypeMatrix(paramImageView);
    if (paramImageView.isInEditMode()) {
      return;
    }
    this.mScaleDragDetector = VersionedGestureDetector.newInstance(paramImageView.getContext(), this);
    this.mGestureDetector = new android.view.GestureDetector(paramImageView.getContext(), new GestureDetector.SimpleOnGestureListener()
    {
      @Override
      public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (PhotoViewAttacher.access$L1000025(PhotoViewAttacher.this) != null)
        {
          if (PhotoViewAttacher.this.getScale() > 1.0F) {
            return false;
          }
          if ((MotionEventCompat.getPointerCount(paramAnonymousMotionEvent1) > PhotoViewAttacher.SINGLE_TOUCH) || (MotionEventCompat.getPointerCount(paramAnonymousMotionEvent2) > PhotoViewAttacher.SINGLE_TOUCH)) {
            return false;
          }
          return PhotoViewAttacher.access$L1000025(PhotoViewAttacher.this).onFling(paramAnonymousMotionEvent1, paramAnonymousMotionEvent2, paramAnonymousFloat1, paramAnonymousFloat2);
        }
        return false;
      }
      
      @Override
      public void onLongPress(MotionEvent paramAnonymousMotionEvent)
      {
        if (PhotoViewAttacher.access$L1000023(PhotoViewAttacher.this) != null) {
          PhotoViewAttacher.access$L1000023(PhotoViewAttacher.this).onLongClick(PhotoViewAttacher.this.getImageView());
        }
      }
    });
    this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
    this.mBaseRotation = 0.0F;
    setZoomable(paramBoolean);
  }
  
  private void cancelFling()
  {
    if (this.mCurrentFlingRunnable != null)
    {
      this.mCurrentFlingRunnable.cancelFling();
      this.mCurrentFlingRunnable = ((FlingRunnable)null);
    }
  }
  
  private void checkAndDisplayMatrix()
  {
    if (checkMatrixBounds()) {
      setImageViewMatrix(getDrawMatrix());
    }
  }
  
  private void checkImageViewScaleType()
  {
    ImageView localImageView = getImageView();
    if ((localImageView != null) && (!(localImageView instanceof IPhotoView)) && (!ImageView.ScaleType.MATRIX.equals(localImageView.getScaleType()))) {
      throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher. You should call setScaleType on the PhotoViewAttacher instead of on the ImageView");
    }
  }
  
  private boolean checkMatrixBounds()
  {
    Object localObject = getImageView();
    if (localObject == null) {
      return false;
    }
    RectF localRectF = getDisplayRect(getDrawMatrix());
    if (localRectF == null) {
      return false;
    }
    float f4 = localRectF.height();
    float f3 = localRectF.width();
    float f2 = 0;
    float f1 = 0;
    int i = getImageViewHeight((ImageView)localObject);
    ImageView.ScaleType localScaleType;
    if (f4 <= i)
    {
      localScaleType = this.mScaleType;
      if (localScaleType == ImageView.ScaleType.FIT_START)
      {
        f1 = -localRectF.top;
        i = getImageViewWidth((ImageView)localObject);
        if (f3 > i) {
          break label271;
        }
        localObject = this.mScaleType;
        if (localObject != ImageView.ScaleType.FIT_START) {
          break label230;
        }
        f2 = -localRectF.left;
        label124:
        this.mScrollEdge = 2;
      }
    }
    for (;;)
    {
      this.mSuppMatrix.postTranslate(f2, f1);
      return true;
      if (localScaleType == ImageView.ScaleType.FIT_END)
      {
        f1 = i - f4 - localRectF.top;
        break;
      }
      f1 = (i - f4) / 2 - localRectF.top;
      break;
      if (localRectF.top > 0)
      {
        f1 = -localRectF.top;
        break;
      }
      if (localRectF.bottom >= i) {
        break;
      }
      f1 = i - localRectF.bottom;
      break;
      label230:
      if (localObject == ImageView.ScaleType.FIT_END)
      {
        f2 = i - f3 - localRectF.left;
        break label124;
      }
      f2 = (i - f3) / 2 - localRectF.left;
      break label124;
      label271:
      if (localRectF.left > 0)
      {
        this.mScrollEdge = 0;
        f2 = -localRectF.left;
      }
      else if (localRectF.right < i)
      {
        f2 = i - localRectF.right;
        this.mScrollEdge = 1;
      }
      else
      {
        this.mScrollEdge = -1;
      }
    }
  }
  
  private static void checkZoomLevels(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 >= paramFloat2) {
      throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
    }
    if (paramFloat2 >= paramFloat3) {
      throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
    }
  }
  
  private RectF getDisplayRect(Matrix paramMatrix)
  {
    Object localObject = getImageView();
    if (localObject != null)
    {
      localObject = ((ImageView)localObject).getDrawable();
      if (localObject != null)
      {
        this.mDisplayRect.set(0, 0, ((Drawable)localObject).getIntrinsicWidth(), ((Drawable)localObject).getIntrinsicHeight());
        paramMatrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
      }
    }
    return (RectF)null;
  }
  
  private Matrix getDrawMatrix()
  {
    this.mDrawMatrix.set(this.mBaseMatrix);
    this.mDrawMatrix.postConcat(this.mSuppMatrix);
    return this.mDrawMatrix;
  }
  
  private int getImageViewHeight(ImageView paramImageView)
  {
    if (paramImageView == null) {
      return 0;
    }
    return paramImageView.getHeight() - paramImageView.getPaddingTop() - paramImageView.getPaddingBottom();
  }
  
  private int getImageViewWidth(ImageView paramImageView)
  {
    if (paramImageView == null) {
      return 0;
    }
    return paramImageView.getWidth() - paramImageView.getPaddingLeft() - paramImageView.getPaddingRight();
  }
  
  private float getValue(Matrix paramMatrix, int paramInt)
  {
    paramMatrix.getValues(this.mMatrixValues);
    return this.mMatrixValues[paramInt];
  }
  
  private static boolean hasDrawable(ImageView paramImageView)
  {
    if ((paramImageView == null) || (paramImageView.getDrawable() == null)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private static boolean isSupportedScaleType(ImageView.ScaleType paramScaleType)
  {
    if (paramScaleType == null) {
      return false;
    }
    if (paramScaleType == ImageView.ScaleType.MATRIX) {
      throw new IllegalArgumentException(paramScaleType.name() + " is not supported in PhotoView");
    }
    return true;
  }
  
  private void resetMatrix()
  {
    this.mSuppMatrix.reset();
    setRotationBy(this.mBaseRotation);
    setImageViewMatrix(getDrawMatrix());
    checkMatrixBounds();
  }
  
  private void setImageViewMatrix(Matrix paramMatrix)
  {
    ImageView localImageView = getImageView();
    if (localImageView != null)
    {
      checkImageViewScaleType();
      localImageView.setImageMatrix(paramMatrix);
      if (this.mMatrixChangeListener != null)
      {
        paramMatrix = getDisplayRect(paramMatrix);
        if (paramMatrix != null) {
          this.mMatrixChangeListener.onMatrixChanged(paramMatrix);
        }
      }
    }
  }
  
  private static void setImageViewScaleTypeMatrix(ImageView paramImageView)
  {
    if ((paramImageView != null) && (!(paramImageView instanceof IPhotoView)) && (!ImageView.ScaleType.MATRIX.equals(paramImageView.getScaleType()))) {
      paramImageView.setScaleType(ImageView.ScaleType.MATRIX);
    }
  }
  
  private void updateBaseMatrix(Drawable paramDrawable)
  {
    Object localObject = getImageView();
    if ((localObject == null) || (paramDrawable == null)) {
      return;
    }
    float f1 = getImageViewWidth((ImageView)localObject);
    float f2 = getImageViewHeight((ImageView)localObject);
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    this.mBaseMatrix.reset();
    float f3 = f1 / i;
    float f4 = f2 / j;
    if (this.mScaleType == ImageView.ScaleType.CENTER) {
      this.mBaseMatrix.postTranslate((f1 - i) / 2.0F, (f2 - j) / 2.0F);
    }
    for (;;)
    {
      resetMatrix();
      return;
      if (this.mScaleType == ImageView.ScaleType.CENTER_CROP)
      {
        f3 = Math.max(f3, f4);
        this.mBaseMatrix.postScale(f3, f3);
        this.mBaseMatrix.postTranslate((f1 - i * f3) / 2.0F, (f2 - j * f3) / 2.0F);
      }
      else if (this.mScaleType == ImageView.ScaleType.CENTER_INSIDE)
      {
        f3 = Math.min(1.0F, Math.min(f3, f4));
        this.mBaseMatrix.postScale(f3, f3);
        this.mBaseMatrix.postTranslate((f1 - i * f3) / 2.0F, (f2 - j * f3) / 2.0F);
      }
      else
      {
        paramDrawable = new RectF(0, 0, i, j);
        localObject = new RectF(0, 0, f1, f2);
        if ((int)this.mBaseRotation % 180 != 0) {
          paramDrawable = new RectF(0, 0, j, i);
        }
        ImageView.ScaleType localScaleType = this.mScaleType;
        if (localScaleType == ImageView.ScaleType.FIT_CENTER) {
          this.mBaseMatrix.setRectToRect(paramDrawable, (RectF)localObject, Matrix.ScaleToFit.CENTER);
        } else if (localScaleType == ImageView.ScaleType.FIT_START) {
          this.mBaseMatrix.setRectToRect(paramDrawable, (RectF)localObject, Matrix.ScaleToFit.START);
        } else if (localScaleType == ImageView.ScaleType.FIT_END) {
          this.mBaseMatrix.setRectToRect(paramDrawable, (RectF)localObject, Matrix.ScaleToFit.END);
        } else if (localScaleType == ImageView.ScaleType.FIT_XY) {
          this.mBaseMatrix.setRectToRect(paramDrawable, (RectF)localObject, Matrix.ScaleToFit.FILL);
        }
      }
    }
  }
  
  @Override
  public boolean canZoom()
  {
    return this.mZoomEnabled;
  }
  
  @SuppressWarnings("deprecation")
  public void cleanup()
  {
    if (this.mImageView == null) {
      return;
    }
    ImageView localImageView = (ImageView)this.mImageView.get();
    if (localImageView != null)
    {
      ViewTreeObserver localViewTreeObserver = localImageView.getViewTreeObserver();
      if ((localViewTreeObserver != null) && (localViewTreeObserver.isAlive())) {
        localViewTreeObserver.removeGlobalOnLayoutListener(this);
      }
      localImageView.setOnTouchListener((View.OnTouchListener)null);
      cancelFling();
    }
    if (this.mGestureDetector != null) {
      this.mGestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener)null);
    }
    this.mMatrixChangeListener = ((OnMatrixChangedListener)null);
    this.mPhotoTapListener = ((OnPhotoTapListener)null);
    this.mViewTapListener = ((OnViewTapListener)null);
    this.mImageView = ((WeakReference)null);
  }
  
  @Override
  public void getDisplayMatrix(Matrix paramMatrix)
  {
    paramMatrix.set(getDrawMatrix());
  }
  
  @Override
  public RectF getDisplayRect()
  {
    checkMatrixBounds();
    return getDisplayRect(getDrawMatrix());
  }
  
  @Override
  public IPhotoView getIPhotoViewImplementation()
  {
    return this;
  }
  
  public Matrix getImageMatrix()
  {
    return this.mDrawMatrix;
  }
  
  public ImageView getImageView()
  {
    ImageView localImageView = (ImageView)null;
    if (this.mImageView != null) {
      localImageView = (ImageView)this.mImageView.get();
    }
    if (localImageView == null)
    {
      cleanup();
      LogManager.getLogger().i("PhotoViewAttacher", "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
    }
    return localImageView;
  }
  
  @Override
  public float getMaximumScale()
  {
    return this.mMaxScale;
  }
  
  @Override
  public float getMediumScale()
  {
    return this.mMidScale;
  }
  
  @Override
  public float getMinimumScale()
  {
    return this.mMinScale;
  }
  
  @Nullable
  OnPhotoTapListener getOnPhotoTapListener()
  {
    return this.mPhotoTapListener;
  }
  
  @Nullable
  OnViewTapListener getOnViewTapListener()
  {
    return this.mViewTapListener;
  }
  
  @Override
  public float getScale()
  {
    return (float)Math.sqrt((float)Math.pow(getValue(this.mSuppMatrix, 0), 2) + (float)Math.pow(getValue(this.mSuppMatrix, 3), 2));
  }
  
  @Override
  public ImageView.ScaleType getScaleType()
  {
    return this.mScaleType;
  }
  
  public void getSuppMatrix(Matrix paramMatrix)
  {
    paramMatrix.set(this.mSuppMatrix);
  }
  
  public Bitmap getVisibleRectangleBitmap()
  {
    Object localObject = getImageView();
    if (localObject == null) {}
    for (localObject = (Bitmap)null;; localObject = ((ImageView)localObject).getDrawingCache()) {
      return (Bitmap)localObject;
    }
  }
  
  @Override
  public void onDrag(float paramFloat1, float paramFloat2)
  {
    if (this.mScaleDragDetector.isScaling()) {
      return;
    }
    if (DEBUG) {
      LogManager.getLogger().d("PhotoViewAttacher", String.format("onDrag: dx: %.2f. dy: %.2f", new Object[] { new Float(paramFloat1), new Float(paramFloat2) }));
    }
    Object localObject = getImageView();
    this.mSuppMatrix.postTranslate(paramFloat1, paramFloat2);
    checkAndDisplayMatrix();
    localObject = ((ImageView)localObject).getParent();
    if ((this.mAllowParentInterceptOnEdge) && (!this.mScaleDragDetector.isScaling()) && (!this.mBlockParentIntercept)) {
      if (((this.mScrollEdge == 2) || ((this.mScrollEdge == 0) && (paramFloat1 >= 1.0F)) || ((this.mScrollEdge == 1) && (paramFloat1 <= -1.0F))) && (localObject != null)) {
        ((ViewParent)localObject).requestDisallowInterceptTouchEvent(false);
      }
    }
    while (localObject == null) {
      return;
    }
    ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
  }
  
  @Override
  public void onFling(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (DEBUG) {
      LogManager.getLogger().d("PhotoViewAttacher", new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("onFling. sX: ").append(paramFloat1).toString()).append(" sY: ").toString()).append(paramFloat2).toString()).append(" Vx: ").toString()).append(paramFloat3).toString()).append(" Vy: ").toString() + paramFloat4);
    }
    ImageView localImageView = getImageView();
    this.mCurrentFlingRunnable = new FlingRunnable(localImageView.getContext());
    this.mCurrentFlingRunnable.fling(getImageViewWidth(localImageView), getImageViewHeight(localImageView), (int)paramFloat3, (int)paramFloat4);
    localImageView.post(this.mCurrentFlingRunnable);
  }
  
  @Override
  public void onGlobalLayout()
  {
    ImageView localImageView = getImageView();
    if (localImageView != null)
    {
      if (!this.mZoomEnabled) {
        break label107;
      }
      int i = localImageView.getTop();
      int j = localImageView.getRight();
      int k = localImageView.getBottom();
      int m = localImageView.getLeft();
      if ((i != this.mIvTop) || (k != this.mIvBottom) || (m != this.mIvLeft) || (j != this.mIvRight))
      {
        updateBaseMatrix(localImageView.getDrawable());
        this.mIvTop = i;
        this.mIvRight = j;
        this.mIvBottom = k;
        this.mIvLeft = m;
      }
    }
    return;
    label107:
    updateBaseMatrix(localImageView.getDrawable());
  }
  
  @Override
  public void onScale(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (DEBUG) {
      LogManager.getLogger().d("PhotoViewAttacher", String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[] { new Float(paramFloat1), new Float(paramFloat2), new Float(paramFloat3) }));
    }
    if (((getScale() < this.mMaxScale) || (paramFloat1 < 1.0F)) && ((getScale() > this.mMinScale) || (paramFloat1 > 1.0F)))
    {
      if (this.mScaleChangeListener != null) {
        this.mScaleChangeListener.onScaleChange(paramFloat1, paramFloat2, paramFloat3);
      }
      this.mSuppMatrix.postScale(paramFloat1, paramFloat1, paramFloat2, paramFloat3);
      checkAndDisplayMatrix();
    }
  }
  
  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool4 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = bool4;
    Object localObject;
    int i;
    label148:
    int j;
    if (this.mZoomEnabled)
    {
      bool1 = bool4;
      if (hasDrawable((ImageView)paramView))
      {
        localObject = paramView.getParent();
        bool1 = bool3;
        switch (paramMotionEvent.getAction())
        {
        default: 
          bool1 = bool3;
        case 2: 
          bool2 = bool1;
          if (this.mScaleDragDetector != null)
          {
            bool1 = this.mScaleDragDetector.isScaling();
            bool3 = this.mScaleDragDetector.isDragging();
            bool2 = this.mScaleDragDetector.onTouchEvent(paramMotionEvent);
            if ((!bool1) && (!this.mScaleDragDetector.isScaling())) {
              break label327;
            }
            i = 0;
            if ((!bool3) && (!this.mScaleDragDetector.isDragging())) {
              break label332;
            }
            j = 0;
            label168:
            if ((i != 0) && (j != 0)) {
              break label338;
            }
          }
          break;
        }
      }
    }
    label327:
    label332:
    label338:
    for (bool1 = false;; bool1 = true)
    {
      this.mBlockParentIntercept = bool1;
      bool1 = bool2;
      if (this.mGestureDetector != null)
      {
        bool1 = bool2;
        if (this.mGestureDetector.onTouchEvent(paramMotionEvent)) {
          bool1 = true;
        }
      }
      return bool1;
      if (localObject != null) {
        ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
      }
      for (;;)
      {
        cancelFling();
        bool1 = bool3;
        break;
        LogManager.getLogger().i("PhotoViewAttacher", "onTouch getParent() returned null");
      }
      bool1 = bool2;
      if (getScale() < this.mMinScale)
      {
        localObject = getDisplayRect();
        bool1 = bool2;
        if (localObject != null)
        {
          paramView.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, ((RectF)localObject).centerX(), ((RectF)localObject).centerY()));
          bool1 = true;
        }
      }
      break;
      i = 1;
      break label148;
      j = 1;
      break label168;
    }
  }
  
  @Override
  public void setAllowParentInterceptOnEdge(boolean paramBoolean)
  {
    this.mAllowParentInterceptOnEdge = paramBoolean;
  }
  
  public void setBaseRotation(float paramFloat)
  {
    this.mBaseRotation = (paramFloat % 'Ũ');
    update();
    setRotationBy(this.mBaseRotation);
    checkAndDisplayMatrix();
  }
  
  @Override
  public boolean setDisplayMatrix(Matrix paramMatrix)
  {
    if (paramMatrix == null) {
      throw new IllegalArgumentException("Matrix cannot be null");
    }
    ImageView localImageView = getImageView();
    if (localImageView == null) {
      return false;
    }
    if (localImageView.getDrawable() == null) {
      return false;
    }
    this.mSuppMatrix.set(paramMatrix);
    setImageViewMatrix(getDrawMatrix());
    checkMatrixBounds();
    return true;
  }
  
  @Override
  public void setMaximumScale(float paramFloat)
  {
    checkZoomLevels(this.mMinScale, this.mMidScale, paramFloat);
    this.mMaxScale = paramFloat;
  }
  
  @Override
  public void setMediumScale(float paramFloat)
  {
    checkZoomLevels(this.mMinScale, paramFloat, this.mMaxScale);
    this.mMidScale = paramFloat;
  }
  
  @Override
  public void setMinimumScale(float paramFloat)
  {
    checkZoomLevels(paramFloat, this.mMidScale, this.mMaxScale);
    this.mMinScale = paramFloat;
  }
  
  @Override
  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    if (paramOnDoubleTapListener != null)
    {
      this.mGestureDetector.setOnDoubleTapListener(paramOnDoubleTapListener);
      return;
    }
    this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
  }
  
  @Override
  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    this.mLongClickListener = paramOnLongClickListener;
  }
  
  @Override
  public void setOnMatrixChangeListener(OnMatrixChangedListener paramOnMatrixChangedListener)
  {
    this.mMatrixChangeListener = paramOnMatrixChangedListener;
  }
  
  @Override
  public void setOnPhotoTapListener(OnPhotoTapListener paramOnPhotoTapListener)
  {
    this.mPhotoTapListener = paramOnPhotoTapListener;
  }
  
  @Override
  public void setOnScaleChangeListener(OnScaleChangeListener paramOnScaleChangeListener)
  {
    this.mScaleChangeListener = paramOnScaleChangeListener;
  }
  
  @Override
  public void setOnSingleFlingListener(OnSingleFlingListener paramOnSingleFlingListener)
  {
    this.mSingleFlingListener = paramOnSingleFlingListener;
  }
  
  @Override
  public void setOnViewTapListener(OnViewTapListener paramOnViewTapListener)
  {
    this.mViewTapListener = paramOnViewTapListener;
  }
  
  @Override
  public void setRotationBy(float paramFloat)
  {
    this.mSuppMatrix.postRotate(paramFloat % 'Ũ');
    checkAndDisplayMatrix();
  }
  
  @Override
  public void setRotationTo(float paramFloat)
  {
    this.mSuppMatrix.setRotate(paramFloat % 'Ũ');
    checkAndDisplayMatrix();
  }
  
  @Override
  public void setScale(float paramFloat)
  {
    setScale(paramFloat, false);
  }
  
  @Override
  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    ImageView localImageView = getImageView();
    if (localImageView != null)
    {
      if ((paramFloat1 < this.mMinScale) || (paramFloat1 > this.mMaxScale))
      {
        LogManager.getLogger().i("PhotoViewAttacher", "Scale must be within the range of minScale and maxScale");
        return;
      }
      if (paramBoolean) {
        localImageView.post(new AnimatedZoomRunnable(getScale(), paramFloat1, paramFloat2, paramFloat3));
      }
    }
    else
    {
      return;
    }
    this.mSuppMatrix.setScale(paramFloat1, paramFloat1, paramFloat2, paramFloat3);
    checkAndDisplayMatrix();
  }
  
  @Override
  public void setScale(float paramFloat, boolean paramBoolean)
  {
    ImageView localImageView = getImageView();
    if (localImageView != null) {
      setScale(paramFloat, localImageView.getRight() / 2, localImageView.getBottom() / 2, paramBoolean);
    }
  }
  
  @Override
  public void setScaleLevels(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    checkZoomLevels(paramFloat1, paramFloat2, paramFloat3);
    this.mMinScale = paramFloat1;
    this.mMidScale = paramFloat2;
    this.mMaxScale = paramFloat3;
  }
  
  @Override
  public void setScaleType(ImageView.ScaleType paramScaleType)
  {
    if ((isSupportedScaleType(paramScaleType)) && (paramScaleType != this.mScaleType))
    {
      this.mScaleType = paramScaleType;
      update();
    }
  }
  
  public void setZoomInterpolator(Interpolator paramInterpolator)
  {
    this.mInterpolator = paramInterpolator;
  }
  
  @Override
  public void setZoomTransitionDuration(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 200;
    }
    this.ZOOM_DURATION = i;
  }
  
  @Override
  public void setZoomable(boolean paramBoolean)
  {
    this.mZoomEnabled = paramBoolean;
    update();
  }
  
  public void update()
  {
    ImageView localImageView = getImageView();
    if (localImageView != null)
    {
      if (this.mZoomEnabled)
      {
        setImageViewScaleTypeMatrix(localImageView);
        updateBaseMatrix(localImageView.getDrawable());
      }
    }
    else {
      return;
    }
    resetMatrix();
  }
  
  private class AnimatedZoomRunnable
    implements Runnable
  {
    private final float mFocalX;
    private final float mFocalY;
    private final long mStartTime;
    private final float mZoomEnd;
    private final float mZoomStart;
    
    public AnimatedZoomRunnable(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.mFocalX = paramFloat3;
      this.mFocalY = paramFloat4;
      this.mStartTime = System.currentTimeMillis();
      this.mZoomStart = paramFloat1;
      this.mZoomEnd = paramFloat2;
    }
    
    private float interpolate()
    {
      float f = Math.min(1.0F, 1.0F * (float)(System.currentTimeMillis() - this.mStartTime) / PhotoViewAttacher.this.ZOOM_DURATION);
      return PhotoViewAttacher.access$L1000002(PhotoViewAttacher.this).getInterpolation(f);
    }
    
    @Override
    public void run()
    {
      ImageView localImageView = PhotoViewAttacher.this.getImageView();
      if (localImageView == null) {
        return;
      }
      float f1 = interpolate();
      float f2 = (this.mZoomStart + f1 * (this.mZoomEnd - this.mZoomStart)) / PhotoViewAttacher.this.getScale();
      PhotoViewAttacher.this.onScale(f2, this.mFocalX, this.mFocalY);
      if (f1 < 1.0F) {
        Compat.postOnAnimation(localImageView, this);
      }
    }
  }
  
  private class FlingRunnable
    implements Runnable
  {
    private int mCurrentX;
    private int mCurrentY;
    private final ScrollerProxy mScroller;
    
    public FlingRunnable(Context paramContext)
    {
      this.mScroller = ScrollerProxy.getScroller(paramContext);
    }
    
    public void cancelFling()
    {
      if (PhotoViewAttacher.access$L1000001()) {
        LogManager.getLogger().d("PhotoViewAttacher", "Cancel Fling");
      }
      this.mScroller.forceFinished(true);
    }
    
    public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      RectF localRectF = PhotoViewAttacher.this.getDisplayRect();
      if (localRectF == null) {
        return;
      }
      int i = Math.round(-localRectF.left);
      int k;
      int j;
      int n;
      int m;
      if (paramInt1 < localRectF.width())
      {
        k = 0;
        j = Math.round(localRectF.width() - paramInt1);
        paramInt1 = k;
        k = Math.round(-localRectF.top);
        if (paramInt2 >= localRectF.height()) {
          break label301;
        }
        n = 0;
        m = Math.round(localRectF.height() - paramInt2);
      }
      for (paramInt2 = n;; paramInt2 = k)
      {
        this.mCurrentX = i;
        this.mCurrentY = k;
        if (PhotoViewAttacher.access$L1000001()) {
          LogManager.getLogger().d("PhotoViewAttacher", new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("fling. StartX:").append(i).toString()).append(" StartY:").toString()).append(k).toString()).append(" MaxX:").toString()).append(j).toString()).append(" MaxY:").toString() + m);
        }
        if ((i != j) || (k != m)) {
          this.mScroller.fling(i, k, paramInt3, paramInt4, paramInt1, j, paramInt2, m, 0, 0);
        }
        return;
        j = i;
        paramInt1 = i;
        break;
        label301:
        m = k;
      }
    }
    
    @Override
    public void run()
    {
      if (this.mScroller.isFinished()) {
        return;
      }
      ImageView localImageView = PhotoViewAttacher.this.getImageView();
      if ((localImageView != null) && (this.mScroller.computeScrollOffset()))
      {
        int i = this.mScroller.getCurrX();
        int j = this.mScroller.getCurrY();
        if (PhotoViewAttacher.access$L1000001()) {
          LogManager.getLogger().d("PhotoViewAttacher", new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append("fling run(). CurrentX:").append(this.mCurrentX).toString()).append(" CurrentY:").toString()).append(this.mCurrentY).toString()).append(" NewX:").toString()).append(i).toString()).append(" NewY:").toString() + j);
        }
        PhotoViewAttacher.access$L1000017(PhotoViewAttacher.this).postTranslate(this.mCurrentX - i, this.mCurrentY - j);
        PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.access$1000037(PhotoViewAttacher.this));
        this.mCurrentX = i;
        this.mCurrentY = j;
        Compat.postOnAnimation(localImageView, this);
      }
    }
  }
  
  public static interface OnMatrixChangedListener
  {
    public abstract void onMatrixChanged(RectF paramRectF);
  }
  
  public static interface OnPhotoTapListener
  {
    public abstract void onOutsidePhotoTap();
    
    public abstract void onPhotoTap(View paramView, float paramFloat1, float paramFloat2);
  }
  
  public static interface OnScaleChangeListener
  {
    public abstract void onScaleChange(float paramFloat1, float paramFloat2, float paramFloat3);
  }
  
  public static interface OnSingleFlingListener
  {
    public abstract boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2);
  }
  
  public static interface OnViewTapListener
  {
    public abstract void onViewTap(View paramView, float paramFloat1, float paramFloat2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\PhotoViewAttacher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */