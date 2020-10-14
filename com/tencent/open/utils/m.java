package com.tencent.open.utils;

public final class m
  implements Cloneable
{
  private int a;
  
  public m(int paramInt)
  {
    this.a = paramInt;
  }
  
  public m(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0);
  }
  
  public m(byte[] paramArrayOfByte, int paramInt)
  {
    this.a = (paramArrayOfByte[(paramInt + 1)] << 8 & 0xFF00);
    this.a += (paramArrayOfByte[paramInt] & 0xFF);
  }
  
  public byte[] a()
  {
    return new byte[] { (byte)(this.a & 0xFF), (byte)((this.a & 0xFF00) >> 8) };
  }
  
  public int b()
  {
    return this.a;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof m))) {
      return false;
    }
    if (this.a == ((m)paramObject).b()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return this.a;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */