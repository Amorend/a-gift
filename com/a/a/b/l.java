package com.a.a.b;

public class l
{
  private int a = 0;
  
  public int a(f paramf, String paramString, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString.length() == 0) {
      return -1;
    }
    int i = paramInt1;
    if (paramInt1 < 0)
    {
      r.a("TextBuffer.find: Invalid start position");
      i = 0;
    }
    paramInt1 = paramInt2;
    if (paramInt2 > paramf.g())
    {
      r.a("TextBuffer.find: Invalid end position");
      paramInt1 = paramf.g();
    }
    paramInt1 = Math.min(paramInt1, paramf.g() - paramString.length() + 1);
    while ((i < paramInt1) && ((!a(paramf, paramString, i, paramBoolean1)) || ((paramBoolean2) && (!a(paramf, i, paramString.length())))))
    {
      i += 1;
      this.a += 1;
    }
    if (i < paramInt1) {
      return i;
    }
    return -1;
  }
  
  protected boolean a(f paramf, int paramInt1, int paramInt2)
  {
    h localh = k.a();
    boolean bool1;
    if (paramInt1 == 0) {
      bool1 = true;
    } else {
      bool1 = localh.b(paramf.charAt(paramInt1 - 1));
    }
    paramInt1 += paramInt2;
    boolean bool2;
    if (paramInt1 == paramf.g()) {
      bool2 = true;
    } else {
      bool2 = localh.b(paramf.charAt(paramInt1));
    }
    return (bool1) && (bool2);
  }
  
  protected boolean a(f paramf, String paramString, int paramInt, boolean paramBoolean)
  {
    if (paramf.g() - paramInt < paramString.length()) {
      return false;
    }
    int i = 0;
    while (i < paramString.length())
    {
      if ((paramBoolean) && (paramString.charAt(i) != paramf.charAt(i + paramInt))) {
        return false;
      }
      if ((!paramBoolean) && (Character.toLowerCase(paramString.charAt(i)) != Character.toLowerCase(paramf.charAt(i + paramInt)))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */