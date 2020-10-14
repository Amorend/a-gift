package com.nirenr;

public class Point
{
  public int t;
  public int x;
  public int y;
  
  public Point(int paramInt1, int paramInt2)
  {
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public Point(int paramInt1, int paramInt2, int paramInt3)
  {
    this.x = paramInt1;
    this.y = paramInt2;
    this.t = paramInt3;
  }
  
  public Point(Point paramPoint)
  {
    this.x = paramPoint.x;
    this.y = paramPoint.y;
  }
  
  public final boolean equals(int paramInt1, int paramInt2)
  {
    return (this.x == paramInt1) && (this.y == paramInt2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Point)paramObject;
      if (this.x != ((Point)paramObject).x) {
        return false;
      }
      return this.y == ((Point)paramObject).y;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.x * 31 + this.y;
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
  }
  
  public final void offset(int paramInt1, int paramInt2)
  {
    this.x += paramInt1;
    this.y += paramInt2;
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    this.x = paramInt1;
    this.y = paramInt2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Point(");
    localStringBuilder.append(this.x);
    localStringBuilder.append(", ");
    localStringBuilder.append(this.y);
    localStringBuilder.append(": ");
    localStringBuilder.append(this.t);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\nirenr\Point.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */