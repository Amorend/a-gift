package android.support.v4.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.support.annotation.RequiresApi;

@TargetApi(23)
@RequiresApi(23)
class PrintHelperApi23
  extends PrintHelperApi20
{
  PrintHelperApi23(Context paramContext)
  {
    super(paramContext);
    this.mIsMinMarginsHandlingCorrect = false;
  }
  
  protected PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
  {
    PrintAttributes.Builder localBuilder = super.copyAttributes(paramPrintAttributes);
    if (paramPrintAttributes.getDuplexMode() != 0) {
      localBuilder.setDuplexMode(paramPrintAttributes.getDuplexMode());
    }
    return localBuilder;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\print\PrintHelperApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */