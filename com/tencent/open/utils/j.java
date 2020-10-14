package com.tencent.open.utils;

import android.annotation.TargetApi;
import android.net.SSLCertificateSocketFactory;
import android.os.Build.VERSION;
import com.tencent.open.a.f;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.params.HttpParams;

@TargetApi(17)
public class j
  implements LayeredSocketFactory
{
  static final HostnameVerifier a = new StrictHostnameVerifier();
  SSLCertificateSocketFactory b = (SSLCertificateSocketFactory)SSLCertificateSocketFactory.getInsecure(0, null);
  
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException
  {
    paramSocket.connect(new InetSocketAddress(paramString, paramInt1));
    return paramSocket;
  }
  
  public Socket createSocket()
  {
    return new Socket();
  }
  
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException
  {
    f.a("SNISocketFactory", "createSocket " + paramSocket.toString() + " host:" + paramString + " port:" + paramInt + " autoClose:" + paramBoolean);
    paramSocket = (SSLSocket)this.b.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    paramSocket.setEnabledProtocols(paramSocket.getSupportedProtocols());
    if (Build.VERSION.SDK_INT >= 17)
    {
      f.a("SNISocketFactory", "Setting SNI hostname");
      this.b.setHostname(paramSocket, paramString);
    }
    for (;;)
    {
      SSLSession localSSLSession = paramSocket.getSession();
      if (a.verify(paramString, localSSLSession)) {
        break;
      }
      throw new SSLPeerUnverifiedException("Cannot verify hostname: " + paramString);
      f.a("SNISocketFactory", "No documented SNI support on Android <4.2, trying with reflection");
      try
      {
        paramSocket.getClass().getMethod("setHostname", new Class[] { String.class }).invoke(paramSocket, new Object[] { paramString });
      }
      catch (Exception localException)
      {
        f.a("SNISocketFactory", "SNI not useable");
      }
    }
    return paramSocket;
  }
  
  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if ((paramSocket instanceof SSLSocket)) {
      return ((SSLSocket)paramSocket).isConnected();
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */