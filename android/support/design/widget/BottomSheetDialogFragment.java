package android.support.design.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class BottomSheetDialogFragment
  extends AppCompatDialogFragment
{
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return new BottomSheetDialog(getContext(), getTheme());
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\BottomSheetDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */