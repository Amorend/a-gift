package com.tencent.tauth;

import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract interface IRequestListener
{
  public abstract void onComplete(JSONObject paramJSONObject);
  
  public abstract void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException);
  
  public abstract void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException);
  
  public abstract void onIOException(IOException paramIOException);
  
  public abstract void onJSONException(JSONException paramJSONException);
  
  public abstract void onMalformedURLException(MalformedURLException paramMalformedURLException);
  
  public abstract void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException);
  
  public abstract void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException);
  
  public abstract void onUnknowException(Exception paramException);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\tauth\IRequestListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */