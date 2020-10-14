package com.baidu.mobstat;

public final class ct
{
  private static final byte[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  
  public static String a(byte[] paramArrayOfByte, String paramString)
  {
    int i = paramArrayOfByte.length * 4 / 3;
    byte[] arrayOfByte1 = new byte[i + (i / 76 + 3)];
    int n = paramArrayOfByte.length - paramArrayOfByte.length % 3;
    int k = 0;
    i = 0;
    int m;
    byte[] arrayOfByte2;
    for (int j = 0; k < n; j = m)
    {
      m = i + 1;
      arrayOfByte1[i] = a[((paramArrayOfByte[k] & 0xFF) >> 2)];
      i = m + 1;
      arrayOfByte2 = a;
      int i2 = paramArrayOfByte[k];
      int i1 = k + 1;
      arrayOfByte1[m] = arrayOfByte2[((i2 & 0x3) << 4 | (paramArrayOfByte[i1] & 0xFF) >> 4)];
      m = i + 1;
      arrayOfByte2 = a;
      i2 = paramArrayOfByte[i1];
      i1 = k + 2;
      arrayOfByte1[i] = arrayOfByte2[((i2 & 0xF) << 2 | (paramArrayOfByte[i1] & 0xFF) >> 6)];
      i = m + 1;
      arrayOfByte1[m] = a[(paramArrayOfByte[i1] & 0x3F)];
      m = j;
      if ((i - j) % 76 == 0)
      {
        m = j;
        if (i != 0) {
          m = j + 1;
        }
      }
      k += 3;
    }
    switch (paramArrayOfByte.length % 3)
    {
    default: 
      break;
    case 2: 
      j = i + 1;
      arrayOfByte1[i] = a[((paramArrayOfByte[n] & 0xFF) >> 2)];
      i = j + 1;
      arrayOfByte2 = a;
      k = paramArrayOfByte[n];
      m = n + 1;
      arrayOfByte1[j] = arrayOfByte2[((k & 0x3) << 4 | (paramArrayOfByte[m] & 0xFF) >> 4)];
      j = i + 1;
      arrayOfByte1[i] = a[((paramArrayOfByte[m] & 0xF) << 2)];
      i = j + 1;
      arrayOfByte1[j] = 61;
      break;
    case 1: 
      j = i + 1;
      arrayOfByte1[i] = a[((paramArrayOfByte[n] & 0xFF) >> 2)];
      i = j + 1;
      arrayOfByte1[j] = a[((paramArrayOfByte[n] & 0x3) << 4)];
      j = i + 1;
      arrayOfByte1[i] = 61;
      i = j + 1;
      arrayOfByte1[j] = 61;
    }
    return new String(arrayOfByte1, 0, i, paramString);
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, paramArrayOfByte.length);
  }
  
  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    int i = paramInt / 4 * 3;
    if (i == 0) {
      return new byte[0];
    }
    byte[] arrayOfByte = new byte[i];
    int j = 0;
    int k = paramInt;
    for (;;)
    {
      i = paramArrayOfByte[(k - 1)];
      paramInt = j;
      if (i != 10)
      {
        paramInt = j;
        if (i != 13)
        {
          paramInt = j;
          if (i != 32) {
            if (i == 9)
            {
              paramInt = j;
            }
            else if (i == 61)
            {
              paramInt = j + 1;
            }
            else
            {
              int n = 0;
              int i1 = 0;
              i = 0;
              int i3;
              for (int m = 0; n < k; m = i3)
              {
                int i4 = paramArrayOfByte[n];
                int i2 = i1;
                paramInt = i;
                i3 = m;
                if (i4 != 10)
                {
                  i2 = i1;
                  paramInt = i;
                  i3 = m;
                  if (i4 != 13)
                  {
                    i2 = i1;
                    paramInt = i;
                    i3 = m;
                    if (i4 != 32) {
                      if (i4 == 9)
                      {
                        i2 = i1;
                        paramInt = i;
                        i3 = m;
                      }
                      else
                      {
                        if ((i4 >= 65) && (i4 <= 90))
                        {
                          paramInt = i4 - 65;
                        }
                        else if ((i4 >= 97) && (i4 <= 122))
                        {
                          paramInt = i4 - 71;
                        }
                        else if ((i4 >= 48) && (i4 <= 57))
                        {
                          paramInt = i4 + 4;
                        }
                        else if (i4 == 43)
                        {
                          paramInt = 62;
                        }
                        else
                        {
                          if (i4 != 47) {
                            break label343;
                          }
                          paramInt = 63;
                        }
                        i2 = (byte)paramInt | i1 << 6;
                        paramInt = i;
                        if (m % 4 == 3)
                        {
                          paramInt = i + 1;
                          arrayOfByte[i] = ((byte)((i2 & 0xFF0000) >> 16));
                          i = paramInt + 1;
                          arrayOfByte[paramInt] = ((byte)((i2 & 0xFF00) >> 8));
                          paramInt = i + 1;
                          arrayOfByte[i] = ((byte)(i2 & 0xFF));
                        }
                        i3 = m + 1;
                        break label345;
                        label343:
                        return null;
                      }
                    }
                  }
                }
                label345:
                n += 1;
                i1 = i2;
                i = paramInt;
              }
              paramInt = i;
              if (j > 0)
              {
                k = i1 << j * 6;
                paramInt = i + 1;
                arrayOfByte[i] = ((byte)((k & 0xFF0000) >> 16));
                if (j == 1)
                {
                  i = paramInt + 1;
                  arrayOfByte[paramInt] = ((byte)((k & 0xFF00) >> 8));
                  paramInt = i;
                }
              }
              paramArrayOfByte = new byte[paramInt];
              System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, paramInt);
              return paramArrayOfByte;
            }
          }
        }
      }
      k -= 1;
      j = paramInt;
    }
  }
  
  public static String b(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, "utf-8");
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */