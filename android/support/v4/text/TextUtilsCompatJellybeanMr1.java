package android.support.v4.text;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import java.util.Locale;

@TargetApi(17)
@RequiresApi(17)
class TextUtilsCompatJellybeanMr1
{
  public static int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
  {
    return TextUtils.getLayoutDirectionFromLocale(paramLocale);
  }
  
  @NonNull
  public static String htmlEncode(@NonNull String paramString)
  {
    return TextUtils.htmlEncode(paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\text\TextUtilsCompatJellybeanMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */