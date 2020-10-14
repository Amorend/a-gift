package com.andlua.string;

public class number
{
  public static int getCount(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || ("".equals(paramString1.trim())) || ("".equals(paramString2.trim()))) {
      return 0;
    }
    int i = 0;
    int j = 0;
    for (;;)
    {
      j = paramString1.indexOf(paramString2, j);
      if (j == -1) {
        return i;
      }
      j += paramString2.length();
      i += 1;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\andlua\string\number.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */