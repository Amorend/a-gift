package android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;

public class PhotoView
  extends ImageView
  implements IPhotoView
{
  private PhotoViewAttacher mAttacher;
  private ImageView.ScaleType mPendingScaleType;
  
  public PhotoView(Context paramContext)
  {
    this(paramContext, (AttributeSet)null);
  }
  
  public PhotoView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public PhotoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    super.setScaleType(ImageView.ScaleType.MATRIX);
    init();
  }
  
  @Override
  public boolean canZoom()
  {
    return this.mAttacher.canZoom();
  }
  
  @Override
  public void getDisplayMatrix(Matrix paramMatrix)
  {
    this.mAttacher.getDisplayMatrix(paramMatrix);
  }
  
  @Override
  public RectF getDisplayRect()
  {
    return this.mAttacher.getDisplayRect();
  }
  
  @Override
  public IPhotoView getIPhotoViewImplementation()
  {
    return this.mAttacher;
  }
  
  @Override
  public Matrix getImageMatrix()
  {
    return this.mAttacher.getImageMatrix();
  }
  
  @Override
  public float getMaximumScale()
  {
    return this.mAttacher.getMaximumScale();
  }
  
  @Override
  public float getMediumScale()
  {
    return this.mAttacher.getMediumScale();
  }
  
  @Override
  public float getMinimumScale()
  {
    return this.mAttacher.getMinimumScale();
  }
  
  @Override
  public float getScale()
  {
    return this.mAttacher.getScale();
  }
  
  @Override
  public ImageView.ScaleType getScaleType()
  {
    return this.mAttacher.getScaleType();
  }
  
  @Override
  public Bitmap getVisibleRectangleBitmap()
  {
    return this.mAttacher.getVisibleRectangleBitmap();
  }
  
  protected void init()
  {
    if ((this.mAttacher == null) || (this.mAttacher.getImageView() == null)) {
      this.mAttacher = new PhotoViewAttacher(this);
    }
    if (this.mPendingScaleType != null)
    {
      setScaleType(this.mPendingScaleType);
      this.mPendingScaleType = ((ImageView.ScaleType)null);
    }
  }
  
  @Override
  protected void onAttachedToWindow()
  {
    init();
    super.onAttachedToWindow();
  }
  
  @Override
  protected void onDetachedFromWindow()
  {
    this.mAttacher.cleanup();
    this.mAttacher = ((PhotoViewAttacher)null);
    super.onDetachedFromWindow();
  }
  
  @Override
  public void setAllowParentInterceptOnEdge(boolean paramBoolean)
  {
    this.mAttacher.setAllowParentInterceptOnEdge(paramBoolean);
  }
  
  @Override
  public boolean setDisplayMatrix(Matrix paramMatrix)
  {
    return this.mAttacher.setDisplayMatrix(paramMatrix);
  }
  
  @Override
  protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mAttacher != null) {
      this.mAttacher.update();
    }
    return bool;
  }
  
  @Override
  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    if (this.mAttacher != null) {
      this.mAttacher.update();
    }
  }
  
  @Override
  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
    if (this.mAttacher != null) {
      this.mAttacher.update();
    }
  }
  
  @Override
  public void setImageURI(Uri paramUri)
  {
    super.setImageURI(paramUri);
    if (this.mAttacher != null) {
      this.mAttacher.update();
    }
  }
  
  @Override
  public void setMaximumScale(float paramFloat)
  {
    this.mAttacher.setMaximumScale(paramFloat);
  }
  
  @Override
  public void setMediumScale(float paramFloat)
  {
    this.mAttacher.setMediumScale(paramFloat);
  }
  
  @Override
  public void setMinimumScale(float paramFloat)
  {
    this.mAttacher.setMinimumScale(paramFloat);
  }
  
  @Override
  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    this.mAttacher.setOnDoubleTapListener(paramOnDoubleTapListener);
  }
  
  @Override
  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    this.mAttacher.setOnLongClickListener(paramOnLongClickListener);
  }
  
  @Override
  public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener paramOnMatrixChangedListener)
  {
    this.mAttacher.setOnMatrixChangeListener(paramOnMatrixChangedListener);
  }
  
  @Override
  public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener paramOnPhotoTapListener)
  {
    this.mAttacher.setOnPhotoTapListener(paramOnPhotoTapListener);
  }
  
  @Override
  public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener paramOnScaleChangeListener)
  {
    this.mAttacher.setOnScaleChangeListener(paramOnScaleChangeListener);
  }
  
  @Override
  public void setOnSingleFlingListener(PhotoViewAttacher.OnSingleFlingListener paramOnSingleFlingListener)
  {
    this.mAttacher.setOnSingleFlingListener(paramOnSingleFlingListener);
  }
  
  @Override
  public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener paramOnViewTapListener)
  {
    this.mAttacher.setOnViewTapListener(paramOnViewTapListener);
  }
  
  @Override
  public void setRotationBy(float paramFloat)
  {
    this.mAttacher.setRotationBy(paramFloat);
  }
  
  @Override
  public void setRotationTo(float paramFloat)
  {
    this.mAttacher.setRotationTo(paramFloat);
  }
  
  @Override
  public void setScale(float paramFloat)
  {
    this.mAttacher.setScale(paramFloat);
  }
  
  @Override
  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    this.mAttacher.setScale(paramFloat1, paramFloat2, paramFloat3, paramBoolean);
  }
  
  @Override
  public void setScale(float paramFloat, boolean paramBoolean)
  {
    this.mAttacher.setScale(paramFloat, paramBoolean);
  }
  
  @Override
  public void setScaleLevels(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.mAttacher.setScaleLevels(paramFloat1, paramFloat2, paramFloat3);
  }
  
  @Override
  public void setScaleType(ImageView.ScaleType paramScaleType)
  {
    if (this.mAttacher != null)
    {
      this.mAttacher.setScaleType(paramScaleType);
      return;
    }
    this.mPendingScaleType = paramScaleType;
  }
  
  @Override
  public void setZoomTransitionDuration(int paramInt)
  {
    this.mAttacher.setZoomTransitionDuration(paramInt);
  }
  
  @Override
  public void setZoomable(boolean paramBoolean)
  {
    this.mAttacher.setZoomable(paramBoolean);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\PhotoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */