package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class PaintCompat
{
  public static boolean hasGlyph(@NonNull Paint paramPaint, @NonNull String paramString)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return PaintCompatApi23.hasGlyph(paramPaint, paramString);
    }
    return PaintCompatGingerbread.hasGlyph(paramPaint, paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\graphics\PaintCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */