package android.support.v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.RequiresApi;

@TargetApi(24)
@RequiresApi(24)
class PrintHelperApi24
  extends PrintHelperApi23
{
  PrintHelperApi24(Context paramContext)
  {
    super(paramContext);
    this.mIsMinMarginsHandlingCorrect = true;
    this.mPrintActivityRespectsOrientation = true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\print\PrintHelperApi24.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */