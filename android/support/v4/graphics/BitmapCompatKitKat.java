package android.support.v4.graphics;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.support.annotation.RequiresApi;

@TargetApi(19)
@RequiresApi(19)
class BitmapCompatKitKat
{
  static int getAllocationByteCount(Bitmap paramBitmap)
  {
    return paramBitmap.getAllocationByteCount();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\graphics\BitmapCompatKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */