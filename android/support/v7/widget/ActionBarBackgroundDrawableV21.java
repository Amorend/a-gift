package android.support.v7.widget;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@TargetApi(21)
@RequiresApi(21)
class ActionBarBackgroundDrawableV21
  extends ActionBarBackgroundDrawable
{
  public ActionBarBackgroundDrawableV21(ActionBarContainer paramActionBarContainer)
  {
    super(paramActionBarContainer);
  }
  
  public void getOutline(@NonNull Outline paramOutline)
  {
    if (this.mContainer.mIsSplit) {
      if (this.mContainer.mSplitBackground != null) {
        this.mContainer.mSplitBackground.getOutline(paramOutline);
      }
    }
    while (this.mContainer.mBackground == null) {
      return;
    }
    this.mContainer.mBackground.getOutline(paramOutline);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\ActionBarBackgroundDrawableV21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */