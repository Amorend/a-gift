package com.tencent.tauth;

public abstract interface IUiListener
{
  public abstract void onCancel();
  
  public abstract void onComplete(Object paramObject);
  
  public abstract void onError(UiError paramUiError);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\tauth\IUiListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */