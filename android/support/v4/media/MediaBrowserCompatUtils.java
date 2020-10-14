package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class MediaBrowserCompatUtils
{
  public static boolean areSameOptions(Bundle paramBundle1, Bundle paramBundle2)
  {
    if (paramBundle1 == paramBundle2) {}
    do
    {
      do
      {
        do
        {
          return true;
          if (paramBundle1 != null) {
            break;
          }
        } while ((paramBundle2.getInt("android.media.browse.extra.PAGE", -1) == -1) && (paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1));
        return false;
        if (paramBundle2 != null) {
          break;
        }
      } while ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == -1) && (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1));
      return false;
    } while ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE", -1)) && (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)));
    return false;
  }
  
  public static boolean hasDuplicatedItems(Bundle paramBundle1, Bundle paramBundle2)
  {
    int i1 = Integer.MAX_VALUE;
    int k;
    int i;
    label17:
    int m;
    label24:
    int j;
    if (paramBundle1 == null)
    {
      k = -1;
      if (paramBundle2 != null) {
        break label90;
      }
      i = -1;
      if (paramBundle1 != null) {
        break label101;
      }
      m = -1;
      if (paramBundle2 != null) {
        break label113;
      }
      j = -1;
      label30:
      if ((k != -1) && (m != -1)) {
        break label124;
      }
      k = Integer.MAX_VALUE;
      m = 0;
      label49:
      if ((i != -1) && (j != -1)) {
        break label151;
      }
      j = 0;
      i = i1;
      label64:
      if ((m > j) || (j > k)) {
        break label169;
      }
    }
    label90:
    label101:
    label113:
    label124:
    label151:
    label169:
    while ((m <= i) && (i <= k))
    {
      return true;
      k = paramBundle1.getInt("android.media.browse.extra.PAGE", -1);
      break;
      i = paramBundle2.getInt("android.media.browse.extra.PAGE", -1);
      break label17;
      m = paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      break label24;
      j = paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      break label30;
      k *= m;
      int n = k;
      k = k + m - 1;
      m = n;
      break label49;
      n = j * i;
      i = n + j - 1;
      j = n;
      break label64;
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\MediaBrowserCompatUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */