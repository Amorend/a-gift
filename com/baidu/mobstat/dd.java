package com.baidu.mobstat;

import android.net.wifi.ScanResult;
import java.util.Comparator;

final class dd
  implements Comparator<ScanResult>
{
  public int a(ScanResult paramScanResult1, ScanResult paramScanResult2)
  {
    return paramScanResult2.level - paramScanResult1.level;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\dd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */