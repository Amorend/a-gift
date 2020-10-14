package com.tencent.open.utils;

public final class l
  implements Cloneable
{
  private long a;
  
  public l(long paramLong)
  {
    this.a = paramLong;
  }
  
  public byte[] a()
  {
    return new byte[] { (byte)(int)(this.a & 0xFF), (byte)(int)((this.a & 0xFF00) >> 8), (byte)(int)((this.a & 0xFF0000) >> 16), (byte)(int)((this.a & 0xFF000000) >> 24) };
  }
  
  public long b()
  {
    return this.a;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof l))) {
      return false;
    }
    if (this.a == ((l)paramObject).b()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return (int)this.a;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */