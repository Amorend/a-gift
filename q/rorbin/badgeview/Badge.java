package q.rorbin.badgeview;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface Badge
{
  public abstract Badge bindTarget(View paramView);
  
  public abstract Drawable getBadgeBackground();
  
  public abstract int getBadgeBackgroundColor();
  
  public abstract int getBadgeGravity();
  
  public abstract int getBadgeNumber();
  
  public abstract float getBadgePadding(boolean paramBoolean);
  
  public abstract String getBadgeText();
  
  public abstract int getBadgeTextColor();
  
  public abstract float getBadgeTextSize(boolean paramBoolean);
  
  public abstract PointF getDragCenter();
  
  public abstract float getGravityOffsetX(boolean paramBoolean);
  
  public abstract float getGravityOffsetY(boolean paramBoolean);
  
  public abstract View getTargetView();
  
  public abstract void hide(boolean paramBoolean);
  
  public abstract boolean isDraggable();
  
  public abstract boolean isExactMode();
  
  public abstract boolean isShowShadow();
  
  public abstract Badge setBadgeBackground(Drawable paramDrawable);
  
  public abstract Badge setBadgeBackground(Drawable paramDrawable, boolean paramBoolean);
  
  public abstract Badge setBadgeBackgroundColor(int paramInt);
  
  public abstract Badge setBadgeGravity(int paramInt);
  
  public abstract Badge setBadgeNumber(int paramInt);
  
  public abstract Badge setBadgePadding(float paramFloat, boolean paramBoolean);
  
  public abstract Badge setBadgeText(String paramString);
  
  public abstract Badge setBadgeTextColor(int paramInt);
  
  public abstract Badge setBadgeTextSize(float paramFloat, boolean paramBoolean);
  
  public abstract Badge setExactMode(boolean paramBoolean);
  
  public abstract Badge setGravityOffset(float paramFloat1, float paramFloat2, boolean paramBoolean);
  
  public abstract Badge setGravityOffset(float paramFloat, boolean paramBoolean);
  
  public abstract Badge setOnDragStateChangedListener(OnDragStateChangedListener paramOnDragStateChangedListener);
  
  public abstract Badge setShowShadow(boolean paramBoolean);
  
  public abstract Badge stroke(int paramInt, float paramFloat, boolean paramBoolean);
  
  public static interface OnDragStateChangedListener
  {
    public static final int STATE_CANCELED = 4;
    public static final int STATE_DRAGGING = 2;
    public static final int STATE_DRAGGING_OUT_OF_RANGE = 3;
    public static final int STATE_START = 1;
    public static final int STATE_SUCCEED = 5;
    
    public abstract void onDragStateChanged(int paramInt, Badge paramBadge, View paramView);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\q\rorbin\badgeview\Badge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */